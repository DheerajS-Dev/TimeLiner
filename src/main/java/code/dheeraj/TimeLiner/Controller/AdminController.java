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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private JournalEntryService journalEntryService;

    //User Specific Admin Controllers

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAll() {
        try {
            return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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

    @PostMapping("/create-admin-user")
    public ResponseEntity<User> createAdminUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.createAdmin(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Journal Specific Admin Controllers

    @PostMapping("/createJournalEntry")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry entry) {
        try {
            return new ResponseEntity<>(journalEntryService.createEntry(entry), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllJournalEntry")
    public ResponseEntity<List<JournalEntry>> getAllEntries() {
        try {
            return new ResponseEntity<>(journalEntryService.findAllEntries(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable ObjectId id) {
        try {
            return new ResponseEntity<>(journalEntryService.findByEntryId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry entry) {
        try {
            return new ResponseEntity<>(journalEntryService.updateEntryById(id, entry), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteJournalEntry/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId id) {
        try {
            return new ResponseEntity<>(journalEntryService.deleteEntryById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
