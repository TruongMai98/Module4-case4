package com.codegym.cms.controller;


import com.codegym.cms.model.dto.CategoryDto;
import com.codegym.cms.model.dto.ProductDto;
import com.codegym.cms.model.entity.Product;

import com.codegym.cms.model.service.ICategoryService;
import com.codegym.cms.model.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<CategoryDto> categories() {
        return categoryService.findAll();
    }

    @GetMapping("/list")
    public ModelAndView listProduct(@PageableDefault(value = 5) Pageable pageable,
                                    @RequestParam("search") Optional<String> search) {
        Page<Product> products;
        if (search.isPresent()) {
            products = productService.findAllByNameContaining(search.get(), pageable);
        } else {
            products = productService.findAllProduct(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        return new ModelAndView("/product/create", "productDto", new ProductDto());
    }

    @PostMapping("/create")
    public ModelAndView saveProduct(@ModelAttribute("productDto") ProductDto productDto) {
        productService.save(productDto);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("productDto", new ProductDto());
        modelAndView.addObject("message", "created product");
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewProduct(@PathVariable("id") Integer id) {
        return new ModelAndView("/product/view", "productDto", productService.findByIdProduct(id).get());
    }


    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Integer id) {
        Optional<ProductDto> productDto = productService.findById(id);

//       Optional<Product> product = productService.findByIdProduct(id);
        ModelAndView modelAndView;
        if (productDto.isPresent()) {
            modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("productDto", productDto.get());
        } else {
            modelAndView = new ModelAndView("/error-404");
        }
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView updateProduct(@ModelAttribute("productDto") ProductDto productDto) {
        productService.save(productDto);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("product", productDto);
        modelAndView.addObject("message", "product updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Integer id) {
        Optional<Product> product = productService.findByIdProduct(id);
        ModelAndView modelAndView;
        if (product.isPresent()) {
            modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("productDto", product.get());
        } else {
            modelAndView = new ModelAndView("/error-404");
        }
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView deleteProduct(@ModelAttribute("productDto") ProductDto productDto) {
        productService.remove(productDto.getId());
        return new ModelAndView("redirect:/product/list");
    }
}
