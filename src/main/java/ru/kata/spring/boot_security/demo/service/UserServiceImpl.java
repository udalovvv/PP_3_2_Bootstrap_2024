package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user);
        userRepository.save(user);
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateUser(long id, User updatedUser) {
        userRepository.updateUser(id, updatedUser);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepository.findByEmail(username) == null) {
            throw new UsernameNotFoundException("User is not found");
        }
        return userRepository.findByEmail(username);
    }


}
