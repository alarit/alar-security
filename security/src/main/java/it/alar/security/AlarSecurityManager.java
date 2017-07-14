package it.alar.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class AlarSecurityManager {

    public static final String CONTEXT_ATTRIBUTE = "SECURITY_CONTEXT_ATTRIBUTE";

    public void setAuthenticated(SecurityContext context) {
        ContextHolder.setContext(context);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        request.getSession().setAttribute(CONTEXT_ATTRIBUTE, context);
    }

    public void unauthenticate() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        request.getSession().removeAttribute(CONTEXT_ATTRIBUTE);
    }

}