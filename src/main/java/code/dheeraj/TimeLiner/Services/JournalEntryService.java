package code.dheeraj.TimeLiner.Services;

import code.dheeraj.TimeLiner.Entity.JournalEntry;
import code.dheeraj.TimeLiner.Repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public JournalEntry createEntry(JournalEntry entry) {
        entry.setDate(LocalDate.now());
        return journalEntryRepository.save(entry);
    }

    public List<JournalEntry> findAllEntries() {
        return journalEntryRepository.findAll();
    }

    public JournalEntry findByEntryId(ObjectId id) {
        return journalEntryRepository.findById(id).orElse(null);
    }

    public JournalEntry updateEntryById(ObjectId id, JournalEntry newEntry) {
        JournalEntry old = journalEntryRepository.findById(id).orElse(null);
        if(old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : old.getContent());
        }
        journalEntryRepository.save(old);
        return old;
    }

    public Boolean deleteEntryById(ObjectId id) {
        Optional<JournalEntry> optional = journalEntryRepository.findById(id);
        if(optional.isPresent()) {
            journalEntryRepository.deleteById(id);
            return true;
        }else
            return false;
    }
}
