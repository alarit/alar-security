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

package it.alar.security.annotation;

/**
 *  @author Alessandro Arici
 *  @since 1.0
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;

import it.alar.security.AlarSecurityManager;
import it.alar.security.filter.AuthenticationFilter;
import it.alar.security.hasrole.HasRoleInterceptor;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ComponentScan(basePackageClasses = {AlarSecurityManager.class, AuthenticationFilter.class, HasRoleInterceptor.class})
public @interface EnableAlarSecurity {

}