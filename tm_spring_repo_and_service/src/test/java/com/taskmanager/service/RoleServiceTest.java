package com.taskmanager.service;

import com.taskmanager.model.Role;
import com.taskmanager.repository.RoleRepository;
import com.taskmanager.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {
    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Test
    void create() {
        Role role = new Role(1L, "ROLE_USER", new ArrayList<>());

        when(roleRepository.save(role)).thenReturn(role);

        Role result = roleService.create(role);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("ROLE_USER");

        verify(roleRepository, times(1)).save(role);
    }

    @Test
    void readById() {
        long roleId = 1L;
        Role role = new Role(roleId, "ROLE_USER", new ArrayList<>());

        when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));

        Role result = roleService.readById(roleId);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(roleId);
        assertThat(result.getName()).isEqualTo("ROLE_USER");

        verify(roleRepository, times(1)).findById(roleId);
    }

    @Test
    void update() {
        long roleId = 1L;
        Role existingRole = new Role(roleId, "ROLE_USER", new ArrayList<>());
        Role updatedRole = new Role(roleId, "ROLE_ADMIN", new ArrayList<>());

        when(roleRepository.findById(roleId)).thenReturn(Optional.of(existingRole));
        when(roleRepository.save(existingRole)).thenReturn(updatedRole);

        Role result = roleService.update(updatedRole);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(roleId);
        assertThat(result.getName()).isEqualTo("ROLE_ADMIN");

        verify(roleRepository, times(1)).findById(roleId);
        verify(roleRepository, times(1)).save(existingRole);
    }

    @Test
    void delete() {
        long roleId = 1L;
        Role role = new Role(roleId, "ROLE_USER", new ArrayList<>());

        RoleRepository mockedRepository = Mockito.mock(RoleRepository.class);
        when(mockedRepository.findById(roleId)).thenReturn(Optional.of(role));

        RoleService roleService = new RoleServiceImpl(mockedRepository);
        roleService.delete(roleId);


        verify(mockedRepository, times(1)).findById(roleId);
        verify(mockedRepository, times(1)).delete(role);
    }

    @Test
    void getAll() {
        List<Role> allRoles = Arrays.asList(
                new Role(1L, "ROLE_USER", new ArrayList<>()),
                new Role(2L, "ROLE_ADMIN", new ArrayList<>())
        );

        when(roleRepository.findAll()).thenReturn(allRoles);

        List<Role> result = roleService.getAll();

        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);

        verify(roleRepository, times(1)).findAll();
    }
}