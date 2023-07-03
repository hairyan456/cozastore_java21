package com.cybersoft.cozastore_java21.payload.request;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UploadProductRequest {
	
	@NotNull
	private MultipartFile image;
	
	@NotNull(message = "Name cannot be null")
	@NotEmpty(message = "Name cannot be empty")
	private String name;
	
	@NotNull(message = "Price cannot be null")
	@NotEmpty(message = "Price cannot be empty")
	@DecimalMin(value = "0.0",message = "Price must be >= 0.0")
	private String price;
	
	private String description;
	
	@NotNull(message = "Quantity cannot be null")
	@NotEmpty(message = "Quantity cannot be empty")
	@Min(value = 0,message = "Quantity must be >= 0")
	private String quantity;
	
	private String image_detail;
	private String size_id;
	private String color_id;
	
	
	@NotNull(message = "Category_id cannot be null")
	@NotEmpty(message = "Category_id cannot be empty")
	private String category_id;

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getImage_detail() {
		return image_detail;
	}

	public void setImage_detail(String image_detail) {
		this.image_detail = image_detail;
	}

	public String getSize_id() {
		return size_id;
	}

	public void setSize_id(String size_id) {
		this.size_id = size_id;
	}

	public String getColor_id() {
		return color_id;
	}

	public void setColor_id(String color_id) {
		this.color_id = color_id;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public UploadProductRequest() {
	}
	
	
}
