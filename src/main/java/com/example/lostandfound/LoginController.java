package com.example.lostandfound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/")
    public String home() {
        return "home";
    }
    
    @GetMapping("/student-login")
    public String studentLoginPage() {
        return "student-login";
    }
    
    @GetMapping("/agent-login")
    public String agentLoginPage() {
        return "agent-login";
    }
    
    @GetMapping("/student-register")
    public String studentRegisterPage() {
        return "student-register";
    }
    
    @PostMapping("/student-register")
    public String studentRegister(@RequestParam String registrationNumber, @RequestParam String password,
                                 @RequestParam String confirmPassword, @RequestParam String email, 
                                 @RequestParam String section, @RequestParam String year) {
        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            return "redirect:/student-register?error=password";
        }
        
        // Check if student already exists
        User existingUser = userRepository.findByUsernameAndPassword(registrationNumber, password);
        if (existingUser != null) {
            return "redirect:/student-register?error=exists";
        }
        
        // Create new student
        User student = new User(registrationNumber, password, "STUDENT");
        student.setEmail(email);
        student.setSection(section);
        student.setYear(year);
        userRepository.save(student);
        return "redirect:/student-login?registered=true";
    }
    
    @GetMapping("/agent-register")
    public String agentRegisterPage() {
        return "agent-register";
    }
    
    @PostMapping("/agent-register")
    public String agentRegister(@RequestParam String agentId, @RequestParam String password, 
                               @RequestParam String confirmPassword) {
        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            return "redirect:/agent-register?error=password";
        }
        
        User existingUser = userRepository.findByUsernameAndPassword(agentId, password);
        if (existingUser != null) {
            return "redirect:/agent-register?error=exists";
        }
        
        userRepository.save(new User(agentId, password, "AGENT"));
        return "redirect:/agent-login?registered=true";
    }
    
    @PostMapping("/student-login")
    public String studentLogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null && "STUDENT".equals(user.getRole())) {
            session.setAttribute("user", user);
            return "redirect:/student-dashboard";
        }
        return "redirect:/student-login?error=true";
    }
    
    @PostMapping("/agent-login")
    public String agentLogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null && "AGENT".equals(user.getRole())) {
            session.setAttribute("user", user);
            return "redirect:/agent-dashboard";
        }
        return "redirect:/agent-login?error=true";
    }
    
    @GetMapping("/agent-dashboard")
    public String agentDashboard(HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/login";
        return "agent-dashboard";
    }
    
    @GetMapping("/student-dashboard")
    public String studentDashboard(HttpSession session) {
        if (session.getAttribute("user") == null) return "redirect:/login";
        return "student-dashboard";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}