package ttadm.config;

import java.io.IOException;
import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import ttadm.bean.SessionBean;


@PropertySource("classpath:app.properties")
@Service
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Resource
    private Environment env;

	private static final String SITE = "SITE";
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth)
			throws IOException, ServletException 
	{
        
        handle( req, resp, auth);
        clearAuthenticationAttributes(req);
       
	}
	
	protected void handle( HttpServletRequest request, 
		      HttpServletResponse response, Authentication auth) throws IOException {
		
		        String targetUrl = determineTargetUrl(auth);
		 
		        if (response.isCommitted()) {
		            //logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
		            return;
		        }
		        
		        HttpSession session = request.getSession();
		        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		        
		        
		        session.setAttribute("authUser", authUser);
		        
		 
		        redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	
	 /** Builds the target URL according to the logic defined in the main class Javadoc. */
    protected String determineTargetUrl(Authentication authentication) {

    	String role = "";
        
    	Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    	GrantedAuthority[] GA = new GrantedAuthority[authorities.size()]; 
    	
    	if(authorities.size() >0) 
    	{
    		authorities.toArray(GA);
    		role = GA[0].getAuthority();
    	}

    	return determineTargetUrl(role);
    	
        
    
    }
 
    public String determineTargetUrl(String role) {
        
    	switch(role) 
        {
        	case "ROLE_ADMIN": 
        	return 	"/admin";
        	
        	case "ROLE_USER": 
        	return 	env.getRequiredProperty(SITE);
        	
        	case "ROLE_ORDERS":
        	return "/eshop";
        	
        	default:
        	return "/login";
        	
        }
        
    }

    
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
 
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}
