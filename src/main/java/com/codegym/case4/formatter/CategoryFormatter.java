//package com.codegym.case4.formatter;
//
//import com.codegym.case4.model.dto.CategoryDto;
//import com.codegym.case4.service.category.ICategoryService;
//import org.springframework.format.Formatter;
//import org.springframework.stereotype.Component;
//
//import java.text.ParseException;
//import java.util.Locale;
//import java.util.Optional;
//
//@Component
//public class CategoryFormatter implements Formatter<CategoryDto> {
//    private ICategoryService categoryService;
//
//    public CategoryFormatter(ICategoryService categoryService) {
//        this.categoryService = categoryService;
//    }
//
//    @Override
//    public CategoryDto parse(String text, Locale locale) throws ParseException {
//        Optional<CategoryDto> categoryDto = categoryService.findById(Integer.parseInt(text));
//        return categoryDto.orElse(null);
//    }
//
//    @Override
//    public String print(CategoryDto object, Locale locale) {
//        return "[" + object.getId() + ", "
//                + object.getName()
//                + "]";
//    }
//}
