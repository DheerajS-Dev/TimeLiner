package code.dheeraj.TimeLiner.Services;

import code.dheeraj.TimeLiner.Entity.User;
import code.dheeraj.TimeLiner.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean createUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Collections.singletonList("USER"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public User createAdmin(User adminUser) {
        User user = userRepository.findByUserName(adminUser.getUserName());
        if (user != null) {
            user.getRoles().add("ADMIN");
            userRepository.save(user);
            return user;
        } else {
            adminUser.setPassword(passwordEncoder.encode(adminUser.getPassword()));
            adminUser.setRoles(Arrays.asList("ADMIN", "USER"));
            userRepository.save(adminUser);
        }
        return adminUser;
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User updateUserDetail(User oldUser, User newUser) {
        oldUser.setUserName(newUser.getUserName());
        oldUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(oldUser);
    }

    public Long deleteUserByUserName(String userName) {
        return userRepository.deleteByUserName(userName);
    }

    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty())
            return users;
        else throw new RuntimeException();
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
