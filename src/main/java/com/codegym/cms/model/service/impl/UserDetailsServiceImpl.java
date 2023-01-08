package com.codegym.cms.model.service.impl;

import com.codegym.cms.model.entity.User;
import com.codegym.cms.model.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository IUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = IUserRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User " + username + "was not found in database!");
        }

        List<String> roles = IUserRepository.findRolesByUsername(username);

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (String role: roles) {
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            grantedAuthorities.add(authority);
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        grantedAuthorities);

        return userDetails;
    }
}
