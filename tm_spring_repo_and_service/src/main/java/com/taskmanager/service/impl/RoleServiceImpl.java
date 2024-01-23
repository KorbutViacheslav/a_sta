package com.taskmanager.service.impl;

import com.taskmanager.model.Role;
import com.taskmanager.repository.RoleRepository;
import com.taskmanager.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role readById(long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Role update(Role role) {
        Role currentRole = readById(role.getId());
        currentRole.setName(role.getName());
        return roleRepository.save(currentRole);
    }

    @Override
    public void delete(long id) {
        roleRepository.delete(readById(id));
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
