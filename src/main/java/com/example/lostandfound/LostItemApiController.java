package com.example.lostandfound;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/items")
public class LostItemApiController {
    private final LostItemService service;

    public LostItemApiController(LostItemService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<LostItem>> getItems(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(service.getAllItems());
    }

    @PostMapping
    public ResponseEntity<LostItem> addItem(@RequestBody LostItem item, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(service.saveItem(item));
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<LostItem> updateItemStatus(@PathVariable Long id, @RequestBody Map<String, String> statusUpdate, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || !"AGENT".equals(user.getRole())) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(service.updateItemStatus(id, statusUpdate.get("status")));
    }
}