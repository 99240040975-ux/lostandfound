package com.example.lostandfound;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
public class LostItemController {
    private final LostItemService service;

    public LostItemController(LostItemService service) {
        this.service = service;
    }



    @PostMapping("/report")
    public String reportItem(@ModelAttribute LostItem item) {
        service.saveItem(item);
        return "redirect:/";
    }
}