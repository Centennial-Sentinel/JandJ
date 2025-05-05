package it326.financialtracker.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import it326.financialtracker.Model.User;
import it326.financialtracker.Model.UserProfile;
import it326.financialtracker.Repository.UserProfileRepository;
import it326.financialtracker.Repository.UserRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String redirectToProperPage(HttpSession session) {
        if (session.getAttribute("username") != null) {
            return "redirect:/home_transactions";  // Redirect to home page if already logged in
        }
        return "redirect:/login"; 
    }

    @GetMapping("/login")
    public String showLoginPage(HttpSession session) {
        if (session.getAttribute("username") != null) {
            return "redirect:/home_transactions";  // Redirect to home page if already logged in
        }
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              Model model,
                              HttpSession session) {
        UserProfile userProfile = userProfileRepository.findByUsername(username);
        
        //checks if credentials are valid
        if (userProfile != null && userProfile.getPassword().equals(password)) {
            session.setAttribute("username", username);
            return "redirect:/home_transactions";
        } else {
            model.addAttribute("loginMessage", "Invalid username or password.");
            return "login";
        }
    }

    //"Create Account" button redirect
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // refers to register.html
    }

    @PostMapping("/register")
    public String handleRegister(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String email,
                                 Model model) {
        // Check if username is already taken
        if (userProfileRepository.findByUsername(username) != null) {
            model.addAttribute("registerMessage", "Username already exists.");
            return "register";
        }

        System.out.println(username);
    
        // Create the UserProfile
        UserProfile profile = new UserProfile();
        profile.setUsername(username);
        profile.setPassword(password); // consider hashing this in production
        profile.setEmail(email);
        userProfileRepository.save(profile);
    
        // Create the User and link it
        User user = new User();
        user.setProfile(profile);  // owns the relationship
    
        // Set the back-reference
        profile.setMyuser(user);
    
        // Save the user (which will also save the profile due to cascade)
        userRepository.save(user);
    
        model.addAttribute("registerMessage", "Account created successfully. You can now log in.");
        return "register";
    }

    //logout function
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
