package com.cybersoft.cozastore_java21.filter;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cybersoft.cozastore_java21.utils.JwtHelper;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter { //Tất cả request đều phải chạy vào Filter.

	/*
	 * Giải quyết: + Nhận được token truyền trên header
	 *             + Giải mã Token
	 *             + Nếu giải mã thành công thì hợp lệ
	 *             + Tạo chứng thực và cho đi vào link người dùng gọi
	 */
	@Autowired
	private JwtHelper jwtHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String header = request.getHeader("Authorization");  //lấy giá trị của header có key là Authorization
			if(header != null)
				System.out.println("Check bearer token:"+header);
			if(header.startsWith("Bearer ")) {
				String token = header.replace("Bearer", "");
				Claims claims = jwtHelper.decodeToken(token.trim());
				System.out.println(claims);
				if(claims != null) { // giải mã thành công == đăng nhập thành công
					//Tạo chứng thực cho Security
					SecurityContext securityContext = SecurityContextHolder.getContext();
					UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken("","",new ArrayList<>());
					securityContext.setAuthentication(user);
				}
			}
		}
		catch(Exception e) {
			System.out.println("Error doFilterInternal():"+e.getMessage());
		}
		filterChain.doFilter(request, response);
		
	}

}
