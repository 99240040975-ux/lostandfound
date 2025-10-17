package com.example.lostandfound;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LostItemRepository extends JpaRepository<LostItem, Long> {
    List<LostItem> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
}