/*
 * Copyright 2017 the original author or authors.
 * 
 * This file is part of Alar-Security
 * 
 * Alar-security is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alar-security is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser Public License for more details.
 *
 * You should have received a copy of the GNU Lesser Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package it.alar.security;

/**
 *	@author Alessandro Arici
 *  @since 1.0
 */

public class ThreadLocalCurrentUserContext {
	
	private final ThreadLocal<SecurityContext> contextHolder = new ThreadLocal<SecurityContext>();
	
	public void clearContext() {
		contextHolder.remove();
	}

	public SecurityContext getContext() {
		SecurityContext ctx = contextHolder.get();

		if (ctx == null) {
			ctx = createEmptyContext();
			contextHolder.set(ctx);
		}

		return ctx;
	}

	public void setContext(SecurityContext user) {
		if(user == null){
			throw new NullPointerException();
		}
		contextHolder.set(user);
	}

	public SecurityContext createEmptyContext() {
		return new SecurityContext();
	}
}
