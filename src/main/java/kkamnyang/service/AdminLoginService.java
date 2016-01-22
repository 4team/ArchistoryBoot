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
		System.out.println("[ADMIN Î°úÍ∑∏?ù∏ ?ãú?èÑ] - loadUserByUsername");
		
		try {
			int adminno = mapper.getNo(useremail);
			String name = mapper.getName(useremail);
			String password = mapper.getPass(useremail);
			
			System.out.println("E-mail based Password in query : " + password);
			
			Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
			
			roles.add(new SimpleGrantedAuthority("ROLE_USER"));
			admin = new AdminDetails(adminno,useremail,name,encoder.encode(password));
		} catch (Exception e) {
			System.out.println("Í≥ÑÏ†ï?ù¥ ?óÜ?äî ADMIN?ùò Î°úÍ∑∏?ù∏ ?ãú?èÑ... LoginService(loadUserByUserName");
			return null;
		}
		
		
		boolean enable;
		try {
			enable = mapper.isEnable(useremail);
			
			if(enable == false){
				System.out.println("?ù¥?ö©?ù¥ ÎßâÌûå ?Ç¨?ö©?ûêÍ∞? Î°úÍ∑∏?ù∏?ïò???ã§.");
				return null;
			}
			
			
		} catch (Exception e) {
			System.out.println("enableÍ∞??†∏?ò§?äî?ç∞ ?ã§?å® ");
			e.printStackTrace();
		}
		
		
		
		System.out.println("LoginService..."+admin);
		return admin;
	}

}
