package com.codegym.cms.model.service;

import com.codegym.cms.model.dto.CategoryDto;
import com.codegym.cms.model.dto.ProductDto;
import com.codegym.cms.model.entity.Category;
import com.codegym.cms.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IProductService extends IGeneralService<ProductDto> {
    Optional<Product> findByIdProduct(Integer id);
    Page<Product> findAllProduct(Pageable pageable);
    Page<Product> findAllByNameContaining(String firstname, Pageable pageable);

    Iterable<Product> findAllByCategory(CategoryDto categoryDto);

}
