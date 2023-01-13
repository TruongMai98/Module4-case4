package com.codegym.cms.configuration;

//import com.codegym.cms.formatter.CategoryFormatter;
//import com.codegym.cms.formatter.RoleFormatter;
import com.codegym.cms.formatter.CategoryFormatter;
import com.codegym.cms.formatter.RoleFormatter;
import com.codegym.cms.model.dto.CategoryDto;
import com.codegym.cms.model.dto.RoleDto;
import com.codegym.cms.model.service.impl.CategoryServiceImpl;
import com.codegym.cms.model.service.impl.RoleServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
//@ComponentScan("com.codegym.cms.formatter")
public class WebAppConfiguration implements WebMvcConfigurer {
//    @Autowired
//    ApplicationContext applicationContext;

    @Value("${upload.path}")
    private String fileUpload;

//    @Value("${css.path}")
//    private String cssPath;
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:" + fileUpload);
//        registry.addResourceHandler("/aaaa/**").addResourceLocations("/static/css/login.css/");

    }
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatter(new CategoryFormatter(applicationContext.getBean(CategoryServiceImpl.class)));
//        registry.addFormatter(new RoleFormatter(applicationContext.getBean(RoleServiceImpl.class)));
//    }

//    @Autowired
//    RoleFormatter roleFormatter;
//    @Autowired
//    CategoryFormatter categoryFormatter;
//
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatter(roleFormatter);
//        registry.addFormatter(categoryFormatter);
//    }
}
