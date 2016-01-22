package kkamnyang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SocialLoginService implements SocialUserDetailsService {
	

	private UserDetailsService userDetailsService;
	
	public SocialLoginService(){
		
	}
    public SocialLoginService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
	
	
	@Override
	public SocialUserDetails loadUserByUserId(String email) throws UsernameNotFoundException, DataAccessException {

		System.out.println("[?Üå?Öú Î°úÍ∑∏?ù∏ ?ãú?èÑ]");
		UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return (SocialUserDetails) userDetails;
	}

}
