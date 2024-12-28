package code.dheeraj.TimeLiner.Controller;

import code.dheeraj.TimeLiner.Entity.JournalEntry;
import code.dheeraj.TimeLiner.Services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping("/createEntry")
    public JournalEntry createEntry(@RequestBody JournalEntry entry) {
        return journalEntryService.createEntry(entry);
    }

    @GetMapping("/getAll")
    public List<JournalEntry> getAll() {
        return journalEntryService.findAllEntries();
    }

    @GetMapping("/id/{id}")
    public JournalEntry getEntryById(@PathVariable ObjectId id) {
        return journalEntryService.findByEntryId(id);
    }

    @PutMapping("/id/{id}")
    public JournalEntry updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry entry) {
        return journalEntryService.updateEntryById(id, entry);
    }

    @DeleteMapping("/id/{id}")
    public boolean deleteEntry(@PathVariable ObjectId id) {
        return journalEntryService.deleteEntryById(id);
    }
}
