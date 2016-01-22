package kkamnyang.persistence;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException quth)
			throws IOException, ServletException {
		System.out.println("Î°úÍ∑∏?ù∏ ?ã§?å®?ùº?ä•");
		 response.sendRedirect(request.getContextPath() + "/login");		
	}
	
	

}
