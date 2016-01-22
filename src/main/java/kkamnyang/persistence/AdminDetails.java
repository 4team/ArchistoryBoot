package kkamnyang.persistence;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class AdminDetails implements UserDetails {
	private final int adminno;
	private final String email;
	private final String name;
	private final String password;
	private final Collection<? extends GrantedAuthority> authorities;

	public AdminDetails(int adminno, String email, String name, String password) {
		this.adminno = adminno;
		this.email = email;
		this.name = name;
		this.password = password;
		this.authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public int getAdminno(){
		return adminno;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public String toString() {
		return "User [adminno = " + adminno + ", email=" + email + ", name=" + name + ", password=" + password + ", authorities="
				+ authorities + "]\n";
	}

	
	

}