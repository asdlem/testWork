package com.fc.myWork.Service.ServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.myWork.Service.ProductService;
import com.fc.myWork.mapper.ProductMapper;
import com.fc.myWork.model.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public boolean updateProduct(Product product) {

        int result = productMapper.updateById(product);
        return result > 0;
    }
    @Override
    public boolean deleteProduct(Long id) {
        int result = productMapper.deleteById(id);
        return result > 0;
    }
    @Override
    public Page<Product> queryProduct(String name, int pageNumber, int pageSize) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            queryWrapper.like("name", name);
        }
        queryWrapper.orderByDesc("created_time");
        Page<Product> page = new Page<>(pageNumber, pageSize);
        Page<Product> resultPage = productMapper.selectPage(page, queryWrapper);
        return resultPage;
    }
    @Override
    public int addProduct(Product product) {
        int insert = productMapper.insert(product);
        return insert;
    }

    @Override
    public Product getProductById(Long id) {
        Product product = productMapper.selectById(id);
        return product;
    }
}