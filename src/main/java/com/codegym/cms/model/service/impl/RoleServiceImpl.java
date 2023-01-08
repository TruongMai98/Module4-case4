package com.codegym.cms.model.service.impl;

import com.codegym.cms.model.dto.RoleDto;
import com.codegym.cms.model.entity.Role;
import com.codegym.cms.model.repository.IRoleRepository;
import com.codegym.cms.model.service.IRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository IRoleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Iterable<RoleDto> findAll() {
        Iterable<Role> entities = IRoleRepository.findAll();
        return StreamSupport.stream(entities.spliterator(), true)
                            .map(entity -> modelMapper.map(entity, RoleDto.class))
                            .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleDto> findById(Integer id) {
        Role entity = IRoleRepository.findById(id).orElse(null);
        return Optional.of(modelMapper.map(entity, RoleDto.class));
    }

    @Override
    public void save(RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        IRoleRepository.save(role);
    }

    @Override
    public void remove(Integer id) {
        IRoleRepository.deleteById(id);
    }
}
