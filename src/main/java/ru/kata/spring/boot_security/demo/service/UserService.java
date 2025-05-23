package ru.kata.spring.boot_security.demo.service;




import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(int id);
    void saveUser(UserDTO userDTO);
    void updateUser(int id, UserDTO userDTO);
    void deleteUser(int id);
    Optional<User> findByUsername(String username);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
