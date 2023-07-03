package com.cybersoft.cozastore_java21.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybersoft.cozastore_java21.exception.CustomException;
import com.cybersoft.cozastore_java21.payload.request.UploadProductRequest;
import com.cybersoft.cozastore_java21.payload.response.BaseResponse;
import com.cybersoft.cozastore_java21.service.imp.ProductServiceImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductServiceImp productServiceImp;
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadProduct(@Valid UploadProductRequest request,BindingResult result){
		List<FieldError> list = result.getFieldErrors(); 
		for(FieldError error:list) {
			throw new CustomException("Error Validation:"+error.getDefaultMessage());
		}
		boolean isSuccess = productServiceImp.addProduct(request);
		BaseResponse response = new BaseResponse(200,"Signup successfully",isSuccess);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
