package code.dheeraj.TimeLiner.Services;

import code.dheeraj.TimeLiner.Entity.JournalEntry;
import code.dheeraj.TimeLiner.Entity.User;
import code.dheeraj.TimeLiner.Repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    //Services to access Journal Entries when there is some kind of user

    //    @Transactional //(Commented as not compatible with Mongo Client)
    public JournalEntry createEntryForUser(String userName, JournalEntry entry) {
        try {
            User user = userService.findByUserName(userName);
            entry.setDate(LocalDate.now());
            JournalEntry saved = journalEntryRepository.save(entry);
            user.getJournalEntries().add(saved);
//            user.setUserName(null); // To check Transactional Features
            userService.addUser(user);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return entry;
    }

    public Boolean deleteEntryForUserName(String userName, ObjectId id) {
        try {
            User user = userService.findByUserName(userName);
            user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            userService.addUser(user);
            journalEntryRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the user");
        }
        return true;
    }

    public JournalEntry updateEntryForUser(ObjectId id, JournalEntry newEntry) {
        try {
            JournalEntry old = journalEntryRepository.findById(id).orElse(null);
            if (old != null) {
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : old.getContent());
            }
            assert old != null;
            journalEntryRepository.save(old);
            return old;
        } catch (Exception e) {
            throw new RuntimeException("Journal Not Found");
        }
    }


    //Services to access Journal Entries directly without any user Access

    public JournalEntry createEntry(JournalEntry entry) {
        try {
            entry.setDate(LocalDate.now());
            JournalEntry saved = journalEntryRepository.save(entry);
        } catch (Exception e) {
            throw new NullPointerException();
        }
        return entry;
    }

    public List<JournalEntry> findAllEntries() {
        return journalEntryRepository.findAll();
    }

    public JournalEntry findByEntryId(ObjectId id) {
        return journalEntryRepository.findById(id).orElse(null);
    }

    public JournalEntry updateEntryById(ObjectId id, JournalEntry newEntry) {
        JournalEntry old = journalEntryRepository.findById(id).orElse(null);
        if (old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : old.getContent());
        }
        assert old != null;
        journalEntryRepository.save(old);
        return old;
    }

    public Boolean deleteEntryById(ObjectId id) {
        Optional<JournalEntry> optional = journalEntryRepository.findById(id);
        if (optional.isPresent()) {
            journalEntryRepository.deleteById(id);
            return true;
        } else
            return false;
    }
}
