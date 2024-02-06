package ru.kata.spring.boot_security.demo.repositories;

import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;


public interface RoleRepository {

    Role findRoleById(long id);

    List<Role> findAllRole();
}
