package kkamnyang.persistence;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class OurUserDetails implements UserDetails {

	private final Integer memberno;
	private final String username;
	private final String email;
	private final String password;
	private final String img;
	private final int userno;
	private final Collection<? extends GrantedAuthority> authorities;

	
	public OurUserDetails(Integer memberno,String username, String email,String password ,String img,int userno){
		this.memberno = memberno;
		this.username = username;
		this.email = email;
		this.password=password;
		this.img = img;
		this.userno = userno;
		this.authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
	}


	public Integer getMemberno() {
		return memberno;
	}


	public String getUsername() {
		return username;
	}


	public String getEmail() {
		return email;
	}


	public String getPassword() {
		return password;
	}


	public String getImg() {
		return img;
	}


	public int getUserno() {
		return userno;
	}


	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}


	@Override
	public String toString() {
		return "UserDetails [memberno=" + memberno + ", username=" + username + ", email=" + email + ", password="
				+ password + ", img=" + img + ", userno=" + userno + ", authorities=" + authorities + "]\n";
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


	@Override
	public boolean isEnabled() {
		return true;
	}
	



}
