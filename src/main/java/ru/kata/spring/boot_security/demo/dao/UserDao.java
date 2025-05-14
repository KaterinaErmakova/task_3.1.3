package ru.kata.spring.boot_security.demo.dao;


import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.User;


import java.util.List;
import java.util.Optional;

@Component
public interface UserDao {
    List<User> getAllUsers();
    User getUserById(int id);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    Optional<User> findByUsername(String username);
}
