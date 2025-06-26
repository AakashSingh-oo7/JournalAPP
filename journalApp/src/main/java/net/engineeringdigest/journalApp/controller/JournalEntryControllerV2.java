package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }

    @PostMapping
    public Boolean createEntry(@RequestBody JournalEntry entry) {
        entry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(entry);
        return true;
    }

    @GetMapping("id/{id}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId id){
        return journalEntryService.findById(id).orElse(null);
    }

    @DeleteMapping("id/{id}")
    public boolean deleteJournalEntryById(@PathVariable ObjectId id){
         journalEntryService.deleteById(id);
         return true;
    }

    @PutMapping("id/{id}")
    public JournalEntry updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry entry){
        JournalEntry old = journalEntryService.findById(id).orElse(null);
        if(old != null){
            old.setTitle(entry.getTitle() !=null && !entry.getTitle().equals("") ? entry.getTitle():old.getTitle());
            old.setContent(entry.getContent() !=null && !entry.getContent().equals("") ? entry.getContent():old.getContent());
        }
        journalEntryService.saveEntry(entry);
        return old;
    }

}
