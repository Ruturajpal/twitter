package com.jwt.token.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.UserDetailsAwareConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jwt.token.services.CustomeUserDetailServices;


@Configuration
@EnableMethodSecurity
public class MySecurity {
	@Autowired
	private CustomeUserDetailServices customeUserDetailServices;
	
	@Bean
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.csrf().disable()
		.authorizeHttpRequests()
//		.requestMatchers("/home/admin")
//		.hasRole("ADMIN")
//		.requestMatchers("/home/normal")
//		.hasRole("NORMAL")
		.requestMatchers("/token")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin();
		
	}

	
	protected  void configure(AuthenticationManagerBuilder auth)throws Exception {
     auth.inMemoryAuthentication()
     .withUser("/token")
     .password("rutu")
     .roles("all");
	}
	



	@Bean
	public PasswordEncoder passwordEncoder() {
		return passwordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return authenticationManagerBean();
		
	}
	
}
