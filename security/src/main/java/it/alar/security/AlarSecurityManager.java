package it.alar.security;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class AlarSecurityManager {

    public static final String CONTEXT_ATTRIBUTE = "SECURITY_CONTEXT_ATTRIBUTE";
   
    @PostConstruct
    public void init(){
    	final String version = getClass().getPackage().getImplementationVersion();
    	StringBuilder banner = new StringBuilder("\n")
    		.append("╔═╗┬  ┌─┐┬─┐  ╔═╗┌─┐┌─┐┬ ┬┬─┐┬┌┬┐┬ ┬").append("\n")
    		.append("╠═╣│  ├─┤├┬┘  ╚═╗├┤ │  │ │├┬┘│ │ └┬┘").append("\n")
    		.append("╩ ╩┴─┘┴ ┴┴└─  ╚═╝└─┘└─┘└─┘┴└─┴ ┴  ┴ ").append("\n")
    		.append(" :: Alar security :: (v" + version + ")");
    	System.out.println(banner.toString());
    }
    
    public void setAuthenticated(SecurityContext context) {
        ContextHolder.setContext(context);
        getHttpRequest().getSession().setAttribute(CONTEXT_ATTRIBUTE, context);
    }
    
    public void updateContext(SecurityContext context) {
        ContextHolder.setContext(context);
        HttpServletRequest request = getHttpRequest();
        request.getSession().removeAttribute(CONTEXT_ATTRIBUTE);
        request.getSession().setAttribute(CONTEXT_ATTRIBUTE, context);
    }
    
    public void unauthenticate() {
    	getHttpRequest().getSession().removeAttribute(CONTEXT_ATTRIBUTE);
    }
    
    private HttpServletRequest getHttpRequest() {
    	return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
}