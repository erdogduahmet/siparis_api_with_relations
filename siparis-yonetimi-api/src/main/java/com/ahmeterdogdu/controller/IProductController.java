package com.ahmeterdogdu.controller;

import java.util.List;

import com.ahmeterdogdu.dto.DtoProduct;
import com.ahmeterdogdu.dto.DtoProductIU;

public interface IProductController {
	
	public List<DtoProduct> getAllProducts();
	
	public DtoProduct findProductByID(Integer id);
	
	public DtoProduct saveProduct(DtoProductIU dtoProductIU);
	
	public boolean deleteProduct(Integer id);
	
	public DtoProduct updateProduct(Integer id, DtoProductIU dtoProductIU);

}
