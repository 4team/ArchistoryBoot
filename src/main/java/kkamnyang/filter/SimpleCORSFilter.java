package kkamnyang.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class SimpleCORSFilter implements Filter {

      public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {
    	  System.out.println("CORS Filter.... doFilterInternal...");
            response.addHeader("Access-Control-Allow-Origin", "*");
            if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())); {
                // CORS "pre-flight" request
                response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
                response.addHeader("Access-Control-Allow-Headers", "Authorization");
                response.addHeader("Access-Control-Max-Age", "1728000");
            }
            filterChain.doFilter(request, response);
        }
   public void init(FilterConfig filterConfig) {}
   public void destroy() {}
   
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	 
    	System.out.println("CORS Filter.... doFilter...");
        HttpServletResponse res = (HttpServletResponse) response;
        
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Headers", "X-CSRF-TOKEN,Access-Control-Allow-Origin,Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,X-HTTP-Method-Override");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE,OPTIONS");
    	res.setHeader("Access-Control-Max-Age", "3600");
    	
            chain.doFilter(request, response);
            
        }
    
}
