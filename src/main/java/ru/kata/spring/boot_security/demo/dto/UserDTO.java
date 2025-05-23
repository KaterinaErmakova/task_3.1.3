package ru.kata.spring.boot_security.demo.dto;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO {
    private int id;
    private String name;
    private String surname;
    private byte age;
    private Long phoneNumber;
    private String password;
    private Set<RoleDto> roles; // только имена ролей


    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.age = user.getAge();
        this.phoneNumber = user.getPhoneNumber();
        this.password = user.getPassword();
        this.roles = user.getRoles().stream()
                .map(RoleDto::new)
                .collect(Collectors.toSet());
    }

    public UserDTO(){}

    public User toEntity(RoleService roleService) {
        User user = new User();
        user.setId(this.id);
        user.setName(this.name);
        user.setSurname(this.surname);
        user.setAge(this.age);
        user.setPhoneNumber(this.phoneNumber);
        user.setPassword(this.password);
        user.setRoles(this.roles.stream()
                .map(RoleDto::getName).map(roleService::findByName)
                .collect(Collectors.toSet()));
        return user;
    }

    public Set<Role> toRolesSet(RoleService roleService){

        return this.roles.stream().map(RoleDto::getName)
                .map(roleService::findByName)
                .collect(Collectors.toSet());
    }

    public UserDTO getUser() {
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

