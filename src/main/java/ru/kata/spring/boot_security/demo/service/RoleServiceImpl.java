package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dto.RoleDto;
import ru.kata.spring.boot_security.demo.models.Role;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }

    @Override
    public List<RoleDto> getAllRoles() {
        return roleDao.getAllRoles().stream().map(RoleDto::new).collect(Collectors.toList());
    }

}
