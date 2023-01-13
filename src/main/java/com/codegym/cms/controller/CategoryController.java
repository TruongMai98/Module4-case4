package com.codegym.cms.controller;


import com.codegym.cms.model.dto.CategoryDto;
import com.codegym.cms.model.entity.Category;
import com.codegym.cms.model.entity.Product;
import com.codegym.cms.model.service.ICategoryService;
import com.codegym.cms.model.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IProductService productService;

    @GetMapping("/list")
    public ModelAndView listCategory() {
        return new ModelAndView("/category/list", "categories", categoryService.findAll());
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        return new ModelAndView("/category/create", "categoryDto", new Category());
    }

    @PostMapping("/create")
    public ModelAndView saveCategory(@ModelAttribute("categoryDto") CategoryDto categoryDto) {
        categoryService.save(categoryDto);
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("categoryDto", new CategoryDto());
        modelAndView.addObject("message", "New category created successfully");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Integer id) {
        Optional<CategoryDto> categoryDto = categoryService.findById(id);
        ModelAndView modelAndView;
        if (categoryDto.isPresent()) {
            modelAndView = new ModelAndView("/category/edit");
            modelAndView.addObject("categoryDto", categoryDto.get());
        } else {
            modelAndView = new ModelAndView("/error-404");
        }
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView updateCategory(@ModelAttribute("categoryDto") CategoryDto categoryDto) {
        categoryService.save(categoryDto);
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        modelAndView.addObject("category", categoryDto);
        modelAndView.addObject("message", "category updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Integer id) {
        Optional<CategoryDto> categoryDto = categoryService.findById(id);
        ModelAndView modelAndView;
        if (categoryDto.isPresent()) {
            modelAndView = new ModelAndView("/category/delete");
            modelAndView.addObject("categoryDto", categoryDto.get());
        } else {
            modelAndView = new ModelAndView("/error-404");
        }
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView deleteCategory(@ModelAttribute("categoryDto") CategoryDto categoryDto) {
        categoryService.remove(categoryDto.getId());
        return new ModelAndView("redirect:list");
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewCategory(@PathVariable("id") Integer id) {
        Optional<CategoryDto> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return  new ModelAndView("/error-404");
        }

        Iterable<Product> products = productService.findAllByCategory(categoryOptional.get());

        ModelAndView modelAndView = new ModelAndView("/category/view");
        modelAndView.addObject("categoryDto", categoryOptional.get());
        modelAndView.addObject("products", products);
        return modelAndView;
    }
}
