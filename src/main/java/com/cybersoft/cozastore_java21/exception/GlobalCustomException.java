package com.cybersoft.cozastore_java21.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cybersoft.cozastore_java21.payload.response.BaseResponse;

@RestControllerAdvice
public class GlobalCustomException {

	@ExceptionHandler(CustomFileNotFoundException.class) //mỗi khi ném CustomFileNotFoundException thì sẽ chạy code trong hàm này
	public ResponseEntity<?> handleCustomFileNotFound(Exception e){ // Exception e
		BaseResponse response = new BaseResponse(500, "", e.getMessage()); //gọi được đường dẫn nhưng file ko tồn tại trả mã 500
		return new ResponseEntity<>(response,HttpStatus.OK); //gọi vô được đường dẫn thì trả status 200
	}
	
	 @ExceptionHandler(CustomException.class)
	    public ResponseEntity<?> handleCustomException(Exception e){
	        BaseResponse response = new BaseResponse();
	        response.setStatuscode(500);
	        response.setData(e.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}
