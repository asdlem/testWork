package com.fc.myWork.Service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.myWork.model.Entity.Product;

public interface ProductService {

    boolean updateProduct(Product product);
    boolean deleteProduct(Long id);
    Page<Product> queryProduct(String name, int pageNumber, int pageSize);
    int addProduct(Product product);

    Product getProductById(Long id);
}