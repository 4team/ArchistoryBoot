package kkamnyang.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

public class QuickSignInAdapter implements SignInAdapter {
    private final RequestCache requestCache;
    
    @Autowired
    public QuickSignInAdapter(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
    @Override
    public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
        String providerUserId = connection.getKey().getProviderUserId();
        signin(providerUserId);
        return extractOriginalUrl(request);
    }
    private String extractOriginalUrl(NativeWebRequest request) {
		return null;
    }
    
    private void removeAutheticationAttributes(HttpSession session) {
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
    private void signin(String userId) {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userId, null, null));
    }
}