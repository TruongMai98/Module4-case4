package com.codegym.cms.model.dto;



import com.codegym.cms.model.entity.Product;

import java.util.Collection;

public class CategoryDto {
    private Integer id;
    private String name;
    Collection<Product> products;

    public CategoryDto() {
    }

    public CategoryDto(Integer id, String name, Collection<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }
}
