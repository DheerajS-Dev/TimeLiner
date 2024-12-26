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

    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll() {
        return new ArrayList<JournalEntry>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry entry) {
        journalEntries.put(entry.getId(), entry);
        return true;
    }

    @GetMapping("/id/{id}")
    public JournalEntry getEntryById(@PathVariable Long id) {
        return journalEntries.get(id);
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateEntry(@PathVariable Long id, @RequestBody JournalEntry entry) {
        return journalEntries.put(id, entry);
    }

    @DeleteMapping("/id/{id}")
    public boolean deleteEntry(@PathVariable Long id) {
        journalEntries.remove(id);
        return true;
    }
}
