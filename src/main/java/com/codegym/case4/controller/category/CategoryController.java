package com.codegym.case4.controller.category;

import com.codegym.case4.model.dto.CategoryDto;
import com.codegym.case4.model.dto.ProductDto;
import com.codegym.case4.model.entity.Category;
import com.codegym.case4.model.entity.Product;
import com.codegym.case4.model.repository.ICategoryRepository;
import com.codegym.case4.model.service.category.ICategoryService;
import com.codegym.case4.model.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
            modelAndView = new ModelAndView("/error.404");
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
            modelAndView = new ModelAndView("/error.404");
        }
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView deleteCategory(@ModelAttribute("categoryDto") CategoryDto categoryDto) {
        categoryService.remove(categoryDto.getId());
        return new ModelAndView("redirect:/category/list");
    }


}
