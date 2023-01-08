package com.codegym.case4.model.service.product;

import com.codegym.case4.model.dto.CategoryDto;
import com.codegym.case4.model.dto.ProductDto;
import com.codegym.case4.model.entity.Category;
import com.codegym.case4.model.entity.Product;
import com.codegym.case4.model.repository.IProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    @Value("${upload.path}")
    private String fileUpload;

    @Override
    public Iterable<ProductDto> findAll() {
        Iterable<Product> entities = productRepository.findAll();
        return StreamSupport.stream(entities.spliterator(), true)
                .map(entity -> modelMapper.map(entity, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<Product> findAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findAllByNameContaining(String firstname, Pageable pageable) {
        return productRepository.findAllByNameContaining(firstname, pageable);
    }

    @Override
    public Optional<ProductDto> findById(Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        return Optional.ofNullable(modelMapper.map(product, ProductDto.class));
//        Optional<Product> product = productRepository.findById(id);
//        return Optional.ofNullable(modelMapper.map(product.get(), ProductDto.class));

    }

    @Override
    public Optional<Product> findByIdProduct(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(ProductDto productDto) {
        if (!productDto.getImage().isEmpty()) {
            MultipartFile multipartFile = productDto.getImage();
            String filename = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(productDto.getImage().getBytes(), new File(fileUpload + filename));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Product product = modelMapper.map(productDto, Product.class);
            product.setImage(filename);
            productRepository.save(product);
        } else {
            Product product = modelMapper.map(productDto, Product.class);
            productRepository.save(product);
        }
    }

    @Override
    public void remove(Integer id) {
        productRepository.deleteById(id);
    }

}
