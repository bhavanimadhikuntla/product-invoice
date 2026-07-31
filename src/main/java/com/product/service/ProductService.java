package com.product.service;

import java.util.List;

import com.product.entity.Product;

public interface ProductService {
      public Product createProduct(Product prod);
      public Product updateProduct(Product prod,Integer prodId);
      public String deleteProduct(Integer prodId);
      public Product getOneProduct(Integer prodId);
      public List<Product> getAllProduct();
}
