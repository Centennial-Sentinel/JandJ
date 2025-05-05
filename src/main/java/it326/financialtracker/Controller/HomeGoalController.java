package it326.financialtracker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import it326.financialtracker.Model.Goal;
import it326.financialtracker.Model.UserProfile;
import it326.financialtracker.Repository.GoalRepository;
import it326.financialtracker.Repository.UserProfileRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeGoalController {

    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private GoalRepository goalRepository;

        // loads the page
        @GetMapping("/home_goals")
        public String showGoalPage(HttpSession session, Model model) {
            String username = (String) session.getAttribute("username");
            if (username == null) {
                model.addAttribute("errorMessage", "You must log in first.");
                return "login";
            }
            UserProfile profile = userProfileRepository.findByUsername(username);
            System.out.println(profile.getMyuser());
            if (profile == null || profile.getMyuser() == null) {
                model.addAttribute("errorMessage", "User not found or not linked properly.");
                return "home_goals";
            }

            // get all goals for the user
            List<Goal> goals = goalRepository.findByMyuser(profile.getMyuser());
    

        model.addAttribute("goals", goals);

        return "home_goals";
    }

        @PostMapping("/goals/create")
    public String createGoal(@RequestParam Double currentAmount,
                                    @RequestParam Double targetAmount,
                                    @RequestParam String description,
                                    HttpSession session) {
    
        // Ensure the user is authenticated
        if (session == null) {
            return "redirect:/login";
        }
    
        // Get the username from the authenticated principal
        String username = (String) session.getAttribute("username");
    
        // Fetch the UserProfile and associated User entity
        var userProfile = userProfileRepository.findByUsername(username);
        var user = userProfile.getMyuser();
    
    
        // Create and populate the Goal object
        Goal goal = new Goal();
        goal.setCurrentAmount(currentAmount);
        goal.setTargetAmount(targetAmount);
        goal.setDescription(description);
        goal.setMyUser(user);
    
        // Save the goal
        goalRepository.save(goal);
    
        // Redirect to home page
        return "redirect:/home_goals";
    }

    //update a goal
    @PostMapping("/goals/update")
    public String updateGoal(@RequestParam Long id,
                                    @RequestParam Double currentAmount,
                                    @RequestParam Double targetAmount,
                                    @RequestParam String description,
                                    HttpSession session) {

        String username = (String) session.getAttribute("username");
        UserProfile profile = userProfileRepository.findByUsername(username);
        
        //get goal from database and overwrite its data
        if (profile != null && profile.getMyuser() != null) {
            Goal existing = goalRepository.findById(id).orElse(null);
            if (existing != null && existing.getMyUser().getId() == (profile.getMyuser().getId())) {
                existing.setCurrentAmount(currentAmount);
                existing.setTargetAmount(targetAmount);
                existing.setDescription(description);
                goalRepository.save(existing);
            }
        }
        return "redirect:/home_goals";
    }

    //delete goal
    @PostMapping("/goals/delete/{id}")
    public String deleteGoal(@PathVariable Long id, HttpSession session) {
        if (session == null) {
            return "redirect:/login";
        }

        String username = (String) session.getAttribute("username");
        UserProfile profile = userProfileRepository.findByUsername(username);

        //deletes if goal can be found
        if (profile != null && profile.getMyuser() != null) {
            Goal gl = goalRepository.findById(id).orElse(null);
            if (gl != null && gl.getMyUser().getId() == (profile.getMyuser().getId())) {
                goalRepository.delete(gl);
            }
        }

        return "redirect:/home_goals";
    }
}
