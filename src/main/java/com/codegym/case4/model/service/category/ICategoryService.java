package com.codegym.case4.model.service.category;

import com.codegym.case4.model.dto.CategoryDto;
import com.codegym.case4.model.entity.Category;
import com.codegym.case4.model.service.IGeneralService;

import java.util.Optional;

public interface ICategoryService extends IGeneralService<CategoryDto> {
    Optional<Category> findByIdCategory(Integer id);
}
