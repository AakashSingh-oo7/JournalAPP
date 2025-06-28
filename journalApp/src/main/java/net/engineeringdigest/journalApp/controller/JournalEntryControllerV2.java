package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
        User user = userService.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntry();
        if (all.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(all);
    }

    @PostMapping("{userName}")
    public Boolean createEntry(@RequestBody JournalEntry entry, @PathVariable String userName) {

        entry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(entry,userName);
        return true;
    }

    @GetMapping("id/{id}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId id){
        return journalEntryService.findById(id).orElse(null);
    }

    @DeleteMapping("id/{userName}/{id}")
    public boolean deleteJournalEntryById(@PathVariable ObjectId id,@PathVariable String userName){
         journalEntryService.deleteById(id,userName);
         return true;
    }

    @PutMapping("id/{userName}/{id}")
    public JournalEntry updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry entry,@PathVariable String userName) {
        JournalEntry old = journalEntryService.findById(id).orElse(null);
        if(old != null){
            old.setTitle(entry.getTitle() !=null && !entry.getTitle().equals("") ? entry.getTitle():old.getTitle());
            old.setContent(entry.getContent() !=null && !entry.getContent().equals("") ? entry.getContent():old.getContent());
        }
        journalEntryService.saveEntry(entry);
        return old;
    }

}
