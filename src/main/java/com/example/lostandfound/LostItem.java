package com.example.lostandfound;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class LostItem {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String location;
    private String date;
    private String contact;
    private String category;
    private String status = "Lost"; // Lost, Found, Matched, Resolved, Unclaimed
    private Long matchedItemId; // ID of matched item
    private String reporterType; // STUDENT or AGENT
    private String reporterId; // Who reported it

    public LostItem() {}
    
    public LostItem(String name, String description, String location, String date, String contact) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.date = date;
        this.contact = contact;
    }

    // Getters and setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getMatchedItemId() { return matchedItemId; }
    public void setMatchedItemId(Long matchedItemId) { this.matchedItemId = matchedItemId; }
    public String getReporterType() { return reporterType; }
    public void setReporterType(String reporterType) { this.reporterType = reporterType; }
    public String getReporterId() { return reporterId; }
    public void setReporterId(String reporterId) { this.reporterId = reporterId; }
}