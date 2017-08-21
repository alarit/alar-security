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

package it.alar.security.hasrole;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import it.alar.security.ContextHolder;
import it.alar.security.example.model.ApplicationContext;

/**
 *  @author Alessandro Arici
 *  @since 1.0
 */

@Aspect
@Component
public class HasRoleInterceptor {

	@Pointcut("@annotation(it.alar.security.hasrole.HasRole)")
	private void hasRole() {}

	@Around("hasRole()")
	public Object doCheckOfficePermission(ProceedingJoinPoint pjp) throws Throwable {
		MethodSignature signature = (MethodSignature) pjp.getSignature();
	    Method method = signature.getMethod();
	    final String role = method.getAnnotation(HasRole.class).value();
		
		ApplicationContext context = ContextHolder.getContext().getApplicationContext();
		if(!context.getRoles().contains(role)) {
			throw new Exception("Not allowed");
		}
		
		return pjp.proceed();
	}
}
