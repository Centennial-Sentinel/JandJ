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
public class HomeProfileController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;

        @GetMapping("/home_profile")
        public String showTransactionPage(HttpSession session, Model model) {
            String username = (String) session.getAttribute("username");
            if (username == null) {
                model.addAttribute("errorMessage", "You must log in first.");
                return "login";
            }
            UserProfile profile = userProfileRepository.findByUsername(username);
            System.out.println(profile.getMyuser());
            if (profile == null || profile.getMyuser() == null) {
                model.addAttribute("errorMessage", "User not found or not linked properly.");
                return "home_transactions";
            }

        return "home_profile";
    }

    // updates a profile
    @PostMapping("/home_profile/update")
    public String updateUserProfile(HttpSession session,
                                    @RequestParam("email") String email,
                                    @RequestParam("password") String password
    ) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }

        UserProfile profile = userProfileRepository.findByUsername(username);
        if (profile != null) {
            profile.setEmail(email);
            profile.setPassword(password); // Consider encoding the password
            userProfileRepository.save(profile);
        }

        return "redirect:/home_profile?success";
    }

    // delets an account
    @PostMapping("/home_profile/delete")
    public String deleteUser(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            UserProfile profile = userProfileRepository.findByUsername(username);
            User user = userRepository.findByProfile(profile);
            if (user != null) {
                profile.setMyuser(null);
                user.setProfile(null);
                userRepository.delete(user); // Cascading delete will handle all user data
                userProfileRepository.delete(profile);
                session.invalidate();
            }
        }
    
        return "redirect:/login?deleted";
    }
}