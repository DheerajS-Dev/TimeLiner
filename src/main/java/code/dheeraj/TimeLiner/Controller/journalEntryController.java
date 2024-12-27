package code.dheeraj.TimeLiner.Controller;

import code.dheeraj.TimeLiner.Entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class journalEntryController {

    @GetMapping
    public List<JournalEntry> getAll() {
        return null;
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry entry) {
        return true;
    }

    @GetMapping("/id/{id}")
    public JournalEntry getEntryById(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateEntry(@PathVariable Long id, @RequestBody JournalEntry entry) {
        return null;
    }

    @DeleteMapping("/id/{id}")
    public boolean deleteEntry(@PathVariable Long id) {
        return true;
    }
}
