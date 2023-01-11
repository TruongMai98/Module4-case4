package com.codegym.cms.model.repository;


import com.codegym.cms.model.dto.CategoryDto;
import com.codegym.cms.model.entity.Category;
import com.codegym.cms.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findAllByNameContaining(String firstname, Pageable pageable);
    Iterable<Product> findAllByCategory(Category category);

}
