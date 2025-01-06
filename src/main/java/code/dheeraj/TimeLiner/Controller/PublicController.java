package code.dheeraj.TimeLiner.Controller;

import code.dheeraj.TimeLiner.Entity.User;
import code.dheeraj.TimeLiner.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK...";
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
