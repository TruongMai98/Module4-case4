package com.codegym.case4.model.repository;

import com.codegym.case4.model.dto.CategoryDto;
import com.codegym.case4.model.entity.Category;
import com.codegym.case4.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findAllByNameContaining(String firstname, Pageable pageable);

}
