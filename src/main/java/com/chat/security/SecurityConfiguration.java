package com.chat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetails customUserDetails;

	@Bean
	public CustomUserDetails CustomUserDetails() {
	    return new CustomUserDetails();
	}
	 
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(CustomUserDetails()).passwordEncoder(encoder());
    }
	

	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		 http
         .authorizeRequests()
         .antMatchers("/", "/reg","/save","/error","/index").permitAll()
         .anyRequest().authenticated()
         .and()
		 .formLogin()
         .loginPage("/login").usernameParameter("username").passwordParameter("password")
         .defaultSuccessUrl("/chat")
         .permitAll()
         .and();
}
}
	

