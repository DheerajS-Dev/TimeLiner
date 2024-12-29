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
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    //API to access Journal Entries with a User Association

    @PostMapping("/createEntry/{userName}")
    public ResponseEntity<?> createEntry(@PathVariable String userName, @RequestBody JournalEntry entry) {
        try {
            return new ResponseEntity<>(journalEntryService.createEntry(userName, entry), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllEntry/{userName}")
    public ResponseEntity<?> getAllEntriesByUser(@PathVariable String userName) {
        try {
            User user = userService.findByUserName(userName);
            List<JournalEntry> all = user.getJournalEntries();
            if(all != null && !all.isEmpty())
                return new ResponseEntity<>(all, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteUserEntry/{userName}/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable String userName, @PathVariable ObjectId id) {
        try {
            return new ResponseEntity<>(journalEntryService.deleteEntryByUserName(userName, id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{userName}/{id}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable String userName, @PathVariable ObjectId id, @RequestBody JournalEntry entry) {
        try {
            return new ResponseEntity<>(journalEntryService.updateEntryById(id, entry), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //API to access Journal Entries directly without any Specific User

    @PostMapping("/createEntry")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry entry) {
        try {
            return new ResponseEntity<>(journalEntryService.createEntry(entry), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllEntry")
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

    @DeleteMapping("/deleteEntry/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId id) {
        try {
            return new ResponseEntity<>(journalEntryService.deleteEntryById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
