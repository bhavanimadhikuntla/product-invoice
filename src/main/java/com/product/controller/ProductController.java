package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.entity.Product;
import com.product.repo.ProductRepo;
import com.product.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
@Autowired
private ProductService prodservice;
@PostMapping("/save")
   public String saveProd(@RequestBody Product prod) {
	   Product p=prodservice.createProduct(prod);
	   String msg=null;
	   if(p!=null) {
		   return "Inserted Successfully";
	   }
	   else {
		   return "Not Inserted";
	   }		   

}
@DeleteMapping("/delete/{prodId}")
public String deleteProd(@PathVariable Integer prodId) {
	return prodservice.deleteProduct(prodId);	
}
@GetMapping("/getAll")
public List<Product> getAllRecords(){
	List<Product> p=prodservice.getAllProduct();
	return p;
}
@GetMapping("/get/{prodId}")
public Product getOneRecord(@PathVariable Integer prodId) {
    return prodservice.getOneProduct(prodId);
}
@PutMapping("/update/{prodId}")
public Product updateProduct(@PathVariable Integer prodId,
                             @RequestBody Product product) {
    return prodservice.updateProduct(product,prodId);
}
}