package ru.kata.spring.boot_security.demo.repositories;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;



public interface UserRepository {

    public List<User> findAll();

    public void save(User user);

    public User findById(long id);

    public void updateUser(long id, User updatedUser);

    public void deleteById(long id);

    User findByEmail(String email);
}
