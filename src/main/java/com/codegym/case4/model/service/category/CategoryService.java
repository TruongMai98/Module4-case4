package com.codegym.case4.model.service.category;

import com.codegym.case4.model.dto.CategoryDto;
import com.codegym.case4.model.entity.Category;
import com.codegym.case4.model.repository.ICategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryService implements ICategoryService{
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
