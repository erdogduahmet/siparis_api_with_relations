package com.ahmeterdogdu.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahmeterdogdu.dto.DtoProduct;
import com.ahmeterdogdu.dto.DtoProductIU;
import com.ahmeterdogdu.entities.Product;
import com.ahmeterdogdu.repository.ProductRepository;
import com.ahmeterdogdu.services.IProductService;

@Service
public class ProductServiceImpl implements IProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<DtoProduct> getAllProducts() {
		List<DtoProduct> dtoProductList=new ArrayList<>();
		List<Product> dbProducts=productRepository.findAll();
		if (dbProducts.isEmpty()) {
			return null;
		}
		for (Product product : dbProducts) {
			DtoProduct dtoProduct=new DtoProduct();
			BeanUtils.copyProperties(product, dtoProduct);
			dtoProductList.add(dtoProduct);
		}
		
		return dtoProductList;
	}

	@Override
	public DtoProduct findProductByID(Integer id) {
		Optional<Product> optional =productRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		Product dbProduct=optional.get();
		DtoProduct dtoProduct=new DtoProduct();
		BeanUtils.copyProperties(dbProduct, dtoProduct);
		return dtoProduct;
		
	}

	@Override
	public DtoProduct saveProduct(DtoProductIU dtoProductIU) {
		Product product=new Product();
		BeanUtils.copyProperties(dtoProductIU, product);
		
		Product dbProduct=productRepository.save(product);
		DtoProduct dtoProduct=new DtoProduct();
		BeanUtils.copyProperties(dbProduct, dtoProduct);
		
		return dtoProduct;
	}

	@Override
	public boolean deleteProduct(Integer id) {
		Optional<Product> optional =productRepository.findById(id);
		if (optional.isEmpty()) {
			return false;
		}
		productRepository.delete(optional.get());
		return true;
	}

	@Override
	public DtoProduct updateProduct(Integer id, DtoProductIU dtoProductIU) {
		Optional<Product> optional =productRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		
		Product dbProduct=optional.get();
		BeanUtils.copyProperties(dtoProductIU, dbProduct);
		Product updatedProduct=productRepository.save(dbProduct);
		DtoProduct dtoProduct=new DtoProduct();
		BeanUtils.copyProperties(updatedProduct, dtoProduct);
		return dtoProduct;
	}

	
	
	
}
