package kkamnyang.domain;

import javax.persistence.Entity;
import javax.persistence.Table;


public class AdminDTO {
	private java.lang.String email;
	private java.lang.String password;
	private boolean useCookie;
	public java.lang.String getEmail() {
		return email;
	}
	public void setEmail(java.lang.String email) {
		this.email = email;
	}
	public java.lang.String getPassword() {
		return password;
	}
	public void setPassword(java.lang.String password) {
		this.password = password;
	}
	public boolean isUseCookie() {
		return useCookie;
	}
	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}
	
	@Override
	public String toString() {
		return "AdminDTO [email=" + email + ", password=" + password + ", useCookie=" + useCookie + "]\n";
	}
	
}
