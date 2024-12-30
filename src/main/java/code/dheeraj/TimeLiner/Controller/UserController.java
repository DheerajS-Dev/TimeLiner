package code.dheeraj.TimeLiner.Controller;

import code.dheeraj.TimeLiner.Entity.JournalEntry;
import code.dheeraj.TimeLiner.Entity.User;
import code.dheeraj.TimeLiner.Services.JournalEntryService;
import code.dheeraj.TimeLiner.Services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Security;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUserDetail(@RequestBody User newUser) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String name = authentication.getName();
            User existingUser = userService.findByUserName(name);
            return new ResponseEntity<>(userService.updateUserDetail(existingUser, newUser), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteEntry() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return new ResponseEntity<>(userService.deleteUserByUserName(name), HttpStatus.OK);
    }

    //Optional Endpoint(Only for Admin As User doesn't need it due to security purpose)
    @GetMapping("/getAllUser")
    public ResponseEntity<List<User>> getAll() {
        try {
            return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String userName) {
        try {
            return new ResponseEntity<>(userService.findByUserName(userName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
