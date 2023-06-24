package com.cybersoft.cozastore_java21.provider;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cybersoft.cozastore_java21.entity.UserEntity;
import com.cybersoft.cozastore_java21.repository.UserRespository;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UserRespository userRespository;
	
	@Autowired
	@Lazy //sử dụng sau cùng
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//Lấy username người dùng truyền vào:
		String username = authentication.getName();
		//Lấy password người dùng truyền vào:
		String password = authentication.getCredentials().toString();
		UserEntity user = userRespository.findByEmail(username).get(0);
			//passwordEncoder.matches() gồm: password do người dùng nhập vào và password được lưu trữ dạng BCrypt trong database.
		if(user != null && passwordEncoder.matches(password, user.getPassword())) { 
			//đăng nhập thành công:
			 return new UsernamePasswordAuthenticationToken(username, user.getPassword(),new ArrayList<>());
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		//khai báo loại chứng thực sử dụng để so sánh
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
