    package ru.kata.spring.boot_security.demo.service;


    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;
    import org.springframework.web.server.ResponseStatusException;
    import ru.kata.spring.boot_security.demo.dao.UserDao;
    import ru.kata.spring.boot_security.demo.models.User;


    import javax.transaction.Transactional;
    import java.util.*;


    @Service
    public class UserServiceImpl implements UserService {

        private final UserDao userDao;
        private final RoleService roleService;

        @Autowired
        public UserServiceImpl(UserDao userDao, RoleService roleService) {
            this.userDao = userDao;
            this.roleService = roleService;
        }

        @Override
        @Transactional
        public List<User> getAllUsers() {
            return userDao.getAllUsers();
        }

        @Override
        @Transactional
        public User getUserById(int id) {
            return userDao.getUserById(id);
        }

        @Override
        @Transactional
        public void saveUser(User user) {
            userDao.saveUser(user);
        }

        @Override
        @Transactional
        public void updateUser(int id, User user) {
            User updatedUser = userDao.getUserById(id);
            if (updatedUser == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found");
            }
            updatedUser.setName(user.getUsername());
            updatedUser.setSurname(user.getSurname());
            updatedUser.setAge(user.getAge());
            updatedUser.setPhoneNumber(user.getPhoneNumber());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setRoles(user.getRoles());
            userDao.updateUser(updatedUser);

        }

        @Override
        @Transactional
        public void deleteUser(int id) {
            User user = getUserById(id);
            if (user != null) {
                userDao.deleteUser(user);
            }
        }

        @Override
        public Optional<User> findByUsername(String username) {
            return userDao.findByUsername(username);
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return userDao.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        }
    }
