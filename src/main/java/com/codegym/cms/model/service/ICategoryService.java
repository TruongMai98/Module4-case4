package com.codegym.cms.model.service;



import com.codegym.cms.model.dto.CategoryDto;
import com.codegym.cms.model.entity.Category;

import java.util.Optional;

public interface ICategoryService extends IGeneralService<CategoryDto> {
    Optional<Category> findByIdCategory(Integer id);
}
