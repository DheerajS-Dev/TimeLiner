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
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User updateUserDetail(User oldUser, User newUser) {
        oldUser.setUserName(newUser.getUserName());
        oldUser.setPassword(newUser.getPassword());
        return userRepository.save(oldUser);
    }

    public boolean deleteUserByUserName(User user) {
        userRepository.delete(user);
        return true;
    }

    public User findByUserName(String userName) {
        return userRepository.findByuserName(userName);
    }
}
