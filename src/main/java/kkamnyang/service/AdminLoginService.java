package kkamnyang.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import kkamnyang.persistence.AdminDetails;
import kkamnyang.persistence.AdminMapper;

@Service("adminLoginService")
public class AdminLoginService implements UserDetailsService{

	@Autowired
	private AdminMapper mapper;
	
	public void setAdminMapper(AdminMapper map){
		this.mapper = map;
	}
	
	@Override
	public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
	
		AdminDetails admin = null;
		
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("[ADMIN 로그인] - loadUserByUsername");
		
		try {
			int adminno = mapper.getNo(useremail);
			String name = mapper.getName(useremail);
			String password = mapper.getPass(useremail);
			
			System.out.println("E-mail based Password in query : " + password);
			
			Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
			
			roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			admin = new AdminDetails(adminno,useremail,name,encoder.encode(password));
		} catch (Exception e) {
			System.out.println("계정이 없는 ADMIN이 로그인하였다.... LoginService(loadUserByUserName");
			return null;
		}
		
		
		boolean enable;
		try {
			enable = mapper.isEnable(useremail);
			
			if(enable == false){
				System.out.println("계정이용이 막힌 사용자가 로그인을 시도했었다.");
				return null;
			}
			
			
		} catch (Exception e) {
			System.out.println("enabled 가져오는 데 실패 ");
			e.printStackTrace();
		}
		
		System.out.println("LoginService..."+admin);
		return admin;
	}

}
