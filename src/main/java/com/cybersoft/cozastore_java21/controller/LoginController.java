package com.cybersoft.cozastore_java21.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cybersoft.cozastore_java21.exception.CustomException;
import com.cybersoft.cozastore_java21.payload.request.SignupRequest;
import com.cybersoft.cozastore_java21.payload.response.BaseResponse;
import com.cybersoft.cozastore_java21.service.imp.UserServiceImp;
import com.cybersoft.cozastore_java21.utils.JwtHelper;

import jakarta.validation.Valid;

@RestController
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@Autowired
	private UserServiceImp userServiceImp;
	
	/* Khi viết 1 API ko được response về 1 chuỗi, phải có cấu trúc nhất định:
	 * {
	 *	 statusCode:200
	 * 	 message:"" : nội dung thông báo cho biết server có lỗi gì ko
	 *   data: dữ liệu trả ra (String,Integer,...) -> dùng Object
	 *  }
	 *  
	 *  Những cái liên quan request,response (tham số, cấu trúc trả về) -> quản lý trong package Payload
	 *  + request: nơi qui định những tham số (raw data, form data), nơi khai báo các đối tượng để người dùng truyền nó như 1 tham số
	 *  + response: khai báo cấu trúc sẽ trả ra cho phía Client
	 */
	@RequestMapping(value="/signin",method = RequestMethod.POST)
	public ResponseEntity<?> singin(@RequestParam String email, @RequestParam String password){
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email,password);
		authenticationManager.authenticate(token);
		String jwt = jwtHelper.generateToken(email);
		BaseResponse response = new BaseResponse(200, "", jwt);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value="/signup",method = RequestMethod.POST)
	public ResponseEntity<?> singup(@Valid SignupRequest request, BindingResult result){
		List<FieldError> list = result.getFieldErrors(); //nếu vi phạm rule liên quan validation thì list này sẽ có giá trị
		for(FieldError error:list) {
			throw new CustomException(error.getDefaultMessage());
		}
		boolean isSuccess = userServiceImp.addUser(request);
		BaseResponse response = new BaseResponse(200,"Signup successfully",isSuccess);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
