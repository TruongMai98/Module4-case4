package com.codegym.case4.model.service.product;

import com.codegym.case4.model.dto.ProductDto;
import com.codegym.case4.model.entity.Product;
import com.codegym.case4.model.repository.IProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService implements IProductService{
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Iterable<ProductDto> findAll() {
        Iterable<Product> entities = productRepository.findAll();
        return StreamSupport.stream(entities.spliterator(), true)
                .map(entity -> modelMapper.map(entity, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDto> findById(Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        return Optional.ofNullable(modelMapper.map(product, ProductDto.class));
//        Optional<Product> product = productRepository.findById(id);
//        return Optional.ofNullable(modelMapper.map(product.get(), ProductDto.class));

    }

    @Override
    public void save(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        productRepository.save(product);
    }

    @Override
    public void remove(Integer id) {
        productRepository.deleteById(id);
    }

}
