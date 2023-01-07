package com.codegym.case4.controller.product;

import com.codegym.case4.model.dto.CategoryDto;
import com.codegym.case4.model.dto.ProductDto;
import com.codegym.case4.model.service.category.ICategoryService;
import com.codegym.case4.model.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ModelAndView listProduct() {
        return new ModelAndView("/product/list", "products", productService.findAll());
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
        return new ModelAndView("/product/view", "productDto", productService.findById(id).get());
    }


    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Integer id) {
       Optional<ProductDto> productDto = productService.findById(id);
       ModelAndView modelAndView;
       if (productDto.isPresent()) {
           modelAndView = new ModelAndView("/product/edit");
           modelAndView.addObject("productDto", productDto.get());
       } else {
           modelAndView = new ModelAndView("/error.404");
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
        Optional<ProductDto> product = productService.findById(id);
        ModelAndView modelAndView;
        if (product.isPresent()) {
            modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("productDto", product.get());
        } else {
            modelAndView = new ModelAndView("/error.404");
        }
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView deleteRole(@ModelAttribute("productDto") ProductDto productDto) {
        productService.remove(productDto.getId());
        return new ModelAndView("redirect:/product/list");
    }
}
