package com.example.lostandfound;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LostItemService {
    private final LostItemRepository repository;

    public LostItemService(LostItemRepository repository) {
        this.repository = repository;
    }

    public List<LostItem> getAllItems() {
        return repository.findAll();
    }

    public LostItem saveItem(LostItem item) {
        return repository.save(item);
    }

    public List<LostItem> searchItems(String query) {
        return repository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
    }
    
    public LostItem updateItemStatus(Long id, String status) {
        Optional<LostItem> optionalItem = repository.findById(id);
        if (optionalItem.isPresent()) {
            LostItem item = optionalItem.get();
            item.setStatus(status);
            return repository.save(item);
        }
        return null;
    }
}