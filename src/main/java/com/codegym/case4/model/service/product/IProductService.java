package com.codegym.case4.model.service.product;

import com.codegym.case4.model.dto.CategoryDto;
import com.codegym.case4.model.dto.ProductDto;

import com.codegym.case4.model.entity.Category;
import com.codegym.case4.model.entity.Product;
import com.codegym.case4.model.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IProductService extends IGeneralService<ProductDto> {
    Optional<Product> findByIdProduct(Integer id);
    Page<Product> findAllProduct(Pageable pageable);
    Page<Product> findAllByNameContaining(String firstname, Pageable pageable);



}
