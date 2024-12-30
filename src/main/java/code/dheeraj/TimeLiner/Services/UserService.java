package code.dheeraj.TimeLiner.Services;

import code.dheeraj.TimeLiner.Entity.JournalEntry;
import code.dheeraj.TimeLiner.Entity.User;
import code.dheeraj.TimeLiner.Repository.JournalEntryRepository;
import code.dheeraj.TimeLiner.Repository.UserRepository;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singletonList("USER"));
        return userRepository.save(user);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUserDetail(User oldUser, User newUser) {
        oldUser.setUserName(newUser.getUserName());
        oldUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(oldUser);
    }

    public Long deleteUserByUserName(String userName) {
        return userRepository.deleteByUserName(userName);
    }

    //Optional Endpoint(Only for Admin As User doesn't need it due to security purpose)

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
