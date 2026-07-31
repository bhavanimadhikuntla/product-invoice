package com.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.entity.Product;
import com.product.repo.ProductRepo;
@Service
public class ProductServiceImp implements ProductService {
@Autowired
private ProductRepo prodrepo;
	@Override
	public Product createProduct(Product prod) {
		double total=prod.getPrice()*prod.getQuantity();
		double discount=0.0;
		if(total==5000)
			discount=(total*10)/100;
		else if(total>5000&&total<=20000)
			discount=(total*15)/100;
		else
			discount=(total*20)/100;
		prod.setTotal(total);
		prod.setDiscount(discount);
		Product p=prodrepo.save(prod);
		return p;
	}

	@Override
	public Product updateProduct(Product prod, Integer prodId) {
		Product oldprod=prodrepo.findById(prodId).get();
		oldprod.setProdName(prod.getProdName());
		oldprod.setPrice(prod.getPrice());
		oldprod.setQuantity(prod.getQuantity());
		// Recalculate
        double total = prod.getPrice() * prod.getQuantity();
        oldprod.setTotal(total);
        double discount=0.0;
        if(total==5000)
			discount=(total*10)/100;
		else if(total>5000&&total<=20000)
			discount=(total*15)/100;
		else
			discount=(total*20)/100;
        oldprod.setDiscount(discount);
		return prodrepo.save(oldprod);
	}

	@Override
	public String deleteProduct(Integer prodId) {
		prodrepo.deleteById(prodId);
		return "Deleted Succesfully";

		
	}

@Override
	public Product getOneProduct(Integer prodId) {
		Product p1=prodrepo.findById(prodId).get();
		return p1;
	}

	@Override
	public List<Product> getAllProduct() {
	
		List<Product> list=prodrepo.findAll();
		return list;
	}

}
