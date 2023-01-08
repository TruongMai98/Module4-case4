package com.codegym.cms.model.service.impl;


import com.codegym.cms.model.dto.CategoryDto;
import com.codegym.cms.model.entity.Category;
import com.codegym.cms.model.repository.ICategoryRepository;
import com.codegym.cms.model.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Iterable<CategoryDto> findAll() {
        Iterable<Category> entities = categoryRepository.findAll();
        return StreamSupport.stream(entities.spliterator(), true)
                .map(entity -> modelMapper.map(entity, CategoryDto.class))
                .collect(Collectors.toList());
    }

//    @Override
//    public Iterable<Category> findAllCategory() {
//        return categoryRepository.findAll();
//    }

    @Override
    public Optional<CategoryDto> findById(Integer id) {
//        Optional<Category> category = categoryRepository.findById(id);
//        return Optional.ofNullable(modelMapper.map(category.get(), CategoryDto.class));
        Category category = categoryRepository.findById(id).orElse(null);
        return Optional.of(modelMapper.map(category, CategoryDto.class));

    }

    @Override
    public Optional<Category> findByIdCategory(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void save(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryRepository.save(category);
    }

    @Override
    public void remove(Integer id) {
        categoryRepository.deleteById(id);
    }
}
