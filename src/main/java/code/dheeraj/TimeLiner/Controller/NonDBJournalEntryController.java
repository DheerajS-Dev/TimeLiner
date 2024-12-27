package code.dheeraj.TimeLiner.Controller;

import code.dheeraj.TimeLiner.Entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/_journal")
public class NonDBJournalEntryController {

    private final Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll() {
        return new ArrayList<JournalEntry>(journalEntries.values());
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry entry) {
        return journalEntries.put(entry.getId(), entry);
    }

    @GetMapping("/id/{id}")
    public JournalEntry getEntryById(@PathVariable Long id) {
        JournalEntry data = journalEntries.get(id);
        if(data != null)
            return data;
        else
            System.out.println("Invalid ID.");
        return null;
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateEntry(@PathVariable Long id, @RequestBody JournalEntry entry) {
        JournalEntry data = journalEntries.get(id);
        if (data != null)
            return journalEntries.put(id, entry);
        else
            System.out.println("Invalid ID.");
        return null;
    }

    @DeleteMapping("/id/{id}")
    public JournalEntry deleteEntry(@PathVariable Long id) {
        JournalEntry data = journalEntries.get(id);
        if (data != null)
            return journalEntries.remove(id);
        else
            System.out.println("Invalid ID.");
        return null;
    }
}
