package com.codegym.cms.model.service;

import com.codegym.cms.model.dto.RoleDto;
import com.codegym.cms.model.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService extends IGeneralService<UserDto> {
    Iterable<UserDto> findAllByRole(RoleDto roleDto);
    Iterable<UserDto> findAll();
    Page<UserDto> findAll(Pageable pageable);
    Page<UserDto> findAllByFullNameContaining(String fullName, Pageable pageable);

    boolean isAuthenticated();
}
