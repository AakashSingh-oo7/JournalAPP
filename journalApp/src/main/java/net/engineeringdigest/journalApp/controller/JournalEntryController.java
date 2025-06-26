//package net.engineeringdigest.journalApp.controller;
//
//import net.engineeringdigest.journalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/journal")
//public class JournalEntryController {
//    private Map<Long, JournalEntry> journalEntries = new HashMap<>();
//
//
//    @GetMapping
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>(journalEntries.values());
//    }
//
//
//    @PostMapping
//    public Boolean createEntry(@RequestBody JournalEntry entry) {
//        journalEntries.put(entry.getId(), entry);
//        return true;
//    }
//
//    @GetMapping("id/{id}")
//    public JournalEntry getJournalEntryById(@PathVariable Long id){
//        return journalEntries.get(id);
//    }
//
//    @DeleteMapping("id/{id}")
//    public JournalEntry deleteJournalEntryById(@PathVariable Long id){
//        return journalEntries.remove(id);
//    }
//
//    @PutMapping("id/{id}")
//    public JournalEntry updateJournalEntry(@PathVariable Long id, @RequestBody JournalEntry entry){
//        return journalEntries.put(id, entry);
//    }
//
//}
