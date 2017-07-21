/*
 * Copyright 2017 the original author or authors.
 */

package it.alar.security.filter;

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

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import it.alar.security.AlarSecurityManager;
import it.alar.security.ContextHolder;
import it.alar.security.SecurityContext;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthenticationFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

	@Value("#{'${alar.security.urls:}'.split(',')}")
	private Set<String> filteredUrls;

	private PathMatcher pathMacher;

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
		StringBuilder sb = new StringBuilder("Filtering following Urls: ");
		sb.append(filteredUrls);
		LOGGER.info(sb.toString());
		pathMacher = new AntPathMatcher();
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		boolean unauthorized = false;
		for (String filteredUrl : filteredUrls) {
			if (!filteredUrl.isEmpty()) {
				StringBuilder url = new StringBuilder(httpServletRequest.getContextPath()).append(filteredUrl.trim());
				if (pathMacher.match(url.toString(), httpServletRequest.getRequestURI())) {
					if (httpServletRequest.getSession().getAttribute(AlarSecurityManager.CONTEXT_ATTRIBUTE) != null) {
						SecurityContext context = (SecurityContext) httpServletRequest.getSession()
								.getAttribute(AlarSecurityManager.CONTEXT_ATTRIBUTE);

						ContextHolder.setContext(context);
					} else {
						unauthorized = true;
						HttpServletResponse httpServletResonse = (HttpServletResponse) response;
						httpServletResonse.sendError(401, "Unauthorized");
					}
				}
			}
		}
		if (!unauthorized) {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		LOGGER.info("Destroy filter");
	}
}