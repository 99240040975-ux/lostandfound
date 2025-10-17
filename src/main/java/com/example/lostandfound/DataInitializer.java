package com.example.lostandfound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private LostItemRepository itemRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Create default users if they don't exist
        if (userRepository.findByUsernameAndPassword("agent1", "password") == null) {
            userRepository.save(new User("agent1", "password", "AGENT"));
            System.out.println("Created default agent: agent1/password");
        }
        
        if (userRepository.findByUsernameAndPassword("student1", "password") == null) {
            userRepository.save(new User("student1", "password", "STUDENT"));
            System.out.println("Created default student: student1/password");
        }
        
        // Create sample items if they don't exist
        if (itemRepository.count() == 0) {
            itemRepository.save(new LostItem("iPhone 13", "Black iPhone with cracked screen", "Library", "2024-01-15", "john@email.com"));
            itemRepository.save(new LostItem("Wallet", "Brown leather wallet with ID cards", "Cafeteria", "2024-01-16", "mary@email.com"));
            itemRepository.save(new LostItem("Keys", "Car keys with blue keychain", "Parking Lot", "2024-01-17", "bob@email.com"));
            System.out.println("Created sample lost items");
        }
        
        System.out.println("Total users: " + userRepository.count() + ", Total items: " + itemRepository.count());
    }
}