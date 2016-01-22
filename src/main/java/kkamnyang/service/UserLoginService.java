package kkamnyang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import kkamnyang.domain.MemberVO;
import kkamnyang.persistence.MemberMapper;
import kkamnyang.persistence.OurUserDetails;

@Service
public class UserLoginService implements UserDetailsService {

	@Autowired
	private MemberMapper mapper;
	
	public void setMemberMapper(MemberMapper map){
		this.mapper = map;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("[USER 로그?�� ?��?��] - loadUserByUsername");
		OurUserDetails user = null;
		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
		
		try{
			MemberVO vo = mapper.findByEmail(email);
			System.out.println("?��출된 MemberVO : "+vo);
			user = new OurUserDetails(vo.getMemberNo(),vo.getUserName(),vo.getEmail(),encoder.encode(vo.getmPassword()),vo.getImg(),vo.getUserNo());
		}catch(Exception e){
			System.out.println("계정?�� ?��?�� Member?�� 로그?�� ?��?��???��.");
			return null;
		}
		System.out.println("로그?�� Member : " + user.toString());
		return user;
	}

}
