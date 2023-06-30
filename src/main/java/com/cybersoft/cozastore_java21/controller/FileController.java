package com.cybersoft.cozastore_java21.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
 import com.cybersoft.cozastore_java21.exception.CustomFileNotFoundException;

@RestController
@RequestMapping("/demo")
public class FileController {
	
	// <class> Path: chứa toàn bộ hàm hỗ trợ liên quan đến đường dẫn
	
	@Value("${path.root}")
	private String spath;
		
	@PostMapping("/uploadfile")
	public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file){
		Path rootPath = Paths.get(spath); //Biến đường dẫn kiểu String (spath) về class Path để xử lí đường dẫn
		try {
			if(!Files.exists(rootPath))  //nếu đường dẫn không tồn tại: tạo folder ứng với lại đường dẫn
				Files.createDirectory(rootPath);
			//file.getOriginalFilename(): lấy tên file và định dạng (vd: img.jpeg)
			//resolve() <=> \   => D:\\image_java21\\cat.jpeg
			Files.copy(file.getInputStream(), rootPath.resolve(file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			return new ResponseEntity<>("Upload "+file.getOriginalFilename()+" successfully!",HttpStatus.OK);
		}
		catch(Exception e) {
			throw new CustomFileNotFoundException("File not found");
		}
	}

	@GetMapping("/{filename}")
	public ResponseEntity<?> downloadFile(@PathVariable("filename") String filename){
		try {
			Path rootPath = Paths.get(spath); //Đường dẫn folder root lưu hình
			Resource resource = new UrlResource(rootPath.resolve(filename).toUri());
			if(resource.exists()) {
				//nếu tồn tại thì cho phép download
				System.out.println();
				return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
			}
			else {
				//khi ném Exception thì code sẽ dừng và văng ra lỗi
				throw new CustomFileNotFoundException("File not found");
			}
		}
		catch(Exception e) {
			throw new CustomFileNotFoundException("File not found");
		}
	}
	
	//BT vê nhà: viết API upload Product(image,name,price,description,quantity,image_detail)


}
