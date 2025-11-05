package com.ahmeterdogdu.services;

import java.util.List;

import com.ahmeterdogdu.dto.DtoProduct;
import com.ahmeterdogdu.dto.DtoProductIU;

public interface IProductService {
	
	public List<DtoProduct> getAllProducts();
	
	public DtoProduct findProductByID(Integer id);
	
	public DtoProduct saveProduct(DtoProductIU dtoProductIU);
	
	public boolean deleteProduct(Integer id);
	
	public DtoProduct updateProduct(Integer id,DtoProductIU dtoProductIU);
	

}
