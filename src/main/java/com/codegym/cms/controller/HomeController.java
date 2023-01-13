package com.codegym.cms.controller;

import com.codegym.cms.model.dto.RoleDto;
import com.codegym.cms.model.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private IUserRepository userRepository;
//
    @GetMapping({ "/","/home"})
    public ModelAndView home() {
//        String name =  SecurityContextHolder.getContext().getAuthentication().getName();
//        return new ModelAndView("/index","username", userRepository.findByUsername(name).getUsername());
        return new ModelAndView("/index");
    }

}