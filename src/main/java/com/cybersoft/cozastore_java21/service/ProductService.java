package com.cybersoft.cozastore_java21.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybersoft.cozastore_java21.entity.CategoryEntity;
import com.cybersoft.cozastore_java21.entity.ColorEntity;
import com.cybersoft.cozastore_java21.entity.ProductEntity;
import com.cybersoft.cozastore_java21.entity.SizeEntity;
import com.cybersoft.cozastore_java21.entity.UserEntity;
import com.cybersoft.cozastore_java21.exception.CustomException;
import com.cybersoft.cozastore_java21.payload.request.UploadProductRequest;
import com.cybersoft.cozastore_java21.repository.ProductRepository;
import com.cybersoft.cozastore_java21.service.imp.ProductServiceImp;

@Service
public class ProductService implements ProductServiceImp{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public boolean addProduct(UploadProductRequest request) {
		boolean isSuccess = false;
		try {
			ProductEntity product = new ProductEntity();
			product.setImage(request.getImage().getOriginalFilename());
			product.setName(request.getName());
			product.setPrice(Double.parseDouble(request.getPrice()));
			product.setDescription(request.getDescription());
			product.setQuantity(Integer.parseInt(request.getQuantity()));
			product.setImageDetail(request.getImage_detail());
			if(request.getSize_id() == null || request.getSize_id().isEmpty())
				product.setSize(null);
			else
				product.setSize(new SizeEntity(Integer.parseInt(request.getSize_id())));
			if(request.getColor_id() == null || request.getColor_id().isEmpty())
				product.setColor(null);
			else
				product.setColor(new ColorEntity(Integer.parseInt(request.getColor_id())));
			product.setCategory(new CategoryEntity(Integer.parseInt(request.getCategory_id())));
			productRepository.save(product);
			isSuccess = true;
		}
		catch(Exception e) {
			throw new CustomException("Error addProduct():"+e.getMessage());
		}
		return isSuccess;
	}

}
