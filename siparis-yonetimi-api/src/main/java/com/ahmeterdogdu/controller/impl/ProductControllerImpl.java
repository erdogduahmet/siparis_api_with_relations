package com.ahmeterdogdu.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahmeterdogdu.controller.IProductController;
import com.ahmeterdogdu.dto.DtoProduct;
import com.ahmeterdogdu.dto.DtoProductIU;
import com.ahmeterdogdu.services.IProductService;

@RestController
@RequestMapping("/rest/api/product")
public class ProductControllerImpl implements IProductController {

	
	@Autowired
	private IProductService productService;

	@GetMapping("/list")
	@Override
	public List<DtoProduct> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/list/{id}")
	@Override
	public DtoProduct findProductByID(@PathVariable(name = "id") Integer id) {
		return productService.findProductByID(id);
	}

	@PostMapping("/save")
	@Override
	public DtoProduct saveProduct(@RequestBody DtoProductIU dtoProductIU) {
		return productService.saveProduct(dtoProductIU);
	}

	@DeleteMapping("delete/{id}")
	@Override
	public boolean deleteProduct(@PathVariable(name = "id") Integer id) {
		return productService.deleteProduct(id);
	}

	@PutMapping("/update/{id}")
	@Override
	public DtoProduct updateProduct(@PathVariable(name = "id") Integer id,@RequestBody DtoProductIU dtoProductIU) {
		return productService.updateProduct(id, dtoProductIU);
	}

}
