package com.fc.myWork.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.myWork.Service.ProductService;
import com.fc.myWork.model.DTO.ProductDto;

import com.fc.myWork.model.DTO.ProductPropertiesToUpdate;
import com.fc.myWork.model.Entity.Product;
import com.fc.myWork.response.BaseResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "商品模块")
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 增加商品
     * @param productDto
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<ProductDto> addProduct(@RequestBody  ProductDto productDto) {
        Product product = new Product();
        // 将DTO转换为实体
        BeanUtils.copyProperties(productDto, product);
        // 添加商品
        int savedCount = productService.addProduct(product);
        // 创建返回的ProductDto对象
        ProductDto savedProductDto = new ProductDto();
        // 将保存的实体转换回DTO
        BeanUtils.copyProperties(product, savedProductDto);
        // 构建成功的响应
        return new BaseResponse<>(200, savedProductDto, "商品添加成功");
    }

    /**
     * @param userId 用户id
     * @param updateProperties 更新传入的参数
     * @return
     */
    @PreAuthorize("hasAuthority('product:update')")
    @PutMapping("/update/{userId}")
    public BaseResponse<ProductDto> updateProduct(@PathVariable Long userId,
                                                  @RequestBody ProductPropertiesToUpdate updateProperties) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        // 身份标识，如有需要使用
//        String username = authentication.getName();
//        // 权限校验逻辑省略...

        Product product = productService.getProductById(userId);
        if (product != null) {
            // 使用传入的属性字段更新productToUpdate对象
             BeanUtils.copyProperties(updateProperties, product);

            boolean updated = productService.updateProduct(product);
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(product,productDto);
            return updated
                    ? new BaseResponse<>(200, productDto, "商品更新成功")
                    : new BaseResponse<>(500, null, "商品更新失败");
        } else {
            return new BaseResponse<>(404, null, "商品不存在");
        }
    }
    // 删除商品
    @PreAuthorize("hasAuthority('product:delete')")
    @DeleteMapping("/delete/{userId}&{productId}")
    public BaseResponse<String> deleteProduct(@PathVariable Long userId,@PathVariable Long productId) {
        // 删除商品
        boolean deleted = productService.deleteProduct(productId);
        if (deleted) {
            return new BaseResponse<>(200, "商品删除成功");
        } else {
            return new BaseResponse<>(500, "商品删除失败");
        }
    }

    /**
     * 查询商品（按商品录入时间降序）
     * @param name
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/query")
    public BaseResponse<Page<ProductDto>> queryProduct(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Product> productPage = productService.queryProduct(name, page, size);
        Page<ProductDto> productDtoPage = new Page<>();
        BeanUtils.copyProperties(productPage, productDtoPage, "records");
        List<ProductDto> productDtoList = productPage.getRecords().stream().map(product -> {
            ProductDto dto = new ProductDto();
            BeanUtils.copyProperties(product, dto);
            return dto;
        }).collect(Collectors.toList());
        productDtoPage.setRecords(productDtoList);
        return new BaseResponse<>(200, productDtoPage, "查询成功");
    }
}