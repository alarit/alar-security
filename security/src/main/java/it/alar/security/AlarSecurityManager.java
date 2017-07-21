/*
 * Copyright 2017 the original author or authors.
 */

package it.alar.security;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * This file is part of Alar-Security
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser Public License for more details.
 *
 * You should have received a copy of the GNU Lesser Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *	@author Alessandro Arici
 *  @since 1.0
 */


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