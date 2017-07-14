package it.alar.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;

import it.alar.security.AlarSecurityManager;
import it.alar.security.filter.AuthenticationFilter;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ComponentScan(basePackageClasses = {AlarSecurityManager.class, AuthenticationFilter.class})
public @interface EnableAlarSecurity {

}