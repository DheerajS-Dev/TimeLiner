package code.dheeraj.TimeLiner.Controller;

import code.dheeraj.TimeLiner.Entity.JournalEntry;
import code.dheeraj.TimeLiner.Entity.User;
import code.dheeraj.TimeLiner.Services.JournalEntryService;
import code.dheeraj.TimeLiner.Services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<User>> getAll() {
        try {
            return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userName}")
    public ResponseEntity<User> getEntryById(@PathVariable String userName) {
        try {
            return new ResponseEntity<>(userService.findByUserName(userName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateUser/{name}")
    public ResponseEntity<User> updateUserDetail(@PathVariable String name, @RequestBody User newUser) {
        try {
            User existingUser = userService.findByUserName(name);
            if(existingUser != null)
                return new ResponseEntity<>(userService.updateUserDetail(existingUser, newUser), HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<?> deleteEntry(@PathVariable String userName) {
        try {
            User existingUser = userService.findByUserName(userName);
            if(existingUser != null)
                return new ResponseEntity<>(userService.deleteUserByUserName(existingUser), HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
