package com.cybersoft.cozastore_java21.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cybersoft.cozastore_java21.filter.JwtFilter;
import com.cybersoft.cozastore_java21.provider.CustomAuthenticationProvider;



@Configuration     
@EnableWebSecurity  
public class SecurityConfig {

	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); //là chuẩn mã hóa ko thể giải mã được, chỉ có thể so sánh là có giống nhau hay ko
	}
	
	@Autowired
	private CustomAuthenticationProvider authenticationProvider;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean 
	public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class) 
				.authenticationProvider(authenticationProvider)
				.build();
	}	
	
	@Bean 
	// Filter dùng để config các rule liên quan tới link hoặc cấu hình Security
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf().disable()   
			  .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			  .and()
			  .authorizeHttpRequests()           
			  .requestMatchers("/signin","/signup","/demo/**").permitAll()  
			  .anyRequest().authenticated()    
			  .and()
			  .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
			  .build();
}
}
