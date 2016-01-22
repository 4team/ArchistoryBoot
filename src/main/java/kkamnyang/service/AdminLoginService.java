package kkamnyang.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import kkamnyang.persistence.AdminDetails;
import kkamnyang.persistence.AdminMapper;

@Service
public class AdminLoginService implements UserDetailsService{

	@Autowired
	private AdminMapper mapper;
	
	public void setAdminMapper(AdminMapper map){
		this.mapper = map;
	}
	
	@Override
	public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
	
		AdminDetails admin = null;
		
		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
		System.out.println("[ADMIN λ‘κ·Έ?Έ ??] - loadUserByUsername");
		
		try {
			int adminno = mapper.getNo(useremail);
			String name = mapper.getName(useremail);
			String password = mapper.getPass(useremail);
			
			System.out.println("E-mail based Password in query : " + password);
			
			Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
			
			roles.add(new SimpleGrantedAuthority("ROLE_USER"));
			admin = new AdminDetails(adminno,useremail,name,encoder.encode(password));
		} catch (Exception e) {
			System.out.println("κ³μ ?΄ ?? ADMIN? λ‘κ·Έ?Έ ??... LoginService(loadUserByUserName");
			return null;
		}
		
		
		boolean enable;
		try {
			enable = mapper.isEnable(useremail);
			
			if(enable == false){
				System.out.println("?΄?©?΄ λ§ν ?¬?©?κ°? λ‘κ·Έ?Έ????€.");
				return null;
			}
			
			
		} catch (Exception e) {
			System.out.println("enableκ°?? Έ?€??° ?€?¨ ");
			e.printStackTrace();
		}
		
		
		
		System.out.println("LoginService..."+admin);
		return admin;
	}

}
