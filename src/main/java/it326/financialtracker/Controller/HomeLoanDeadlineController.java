package it326.financialtracker.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import it326.financialtracker.Model.LoanDeadline;
import it326.financialtracker.Model.UserProfile;
import it326.financialtracker.Repository.LoanDeadlineRepository;
import it326.financialtracker.Repository.UserProfileRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeLoanDeadlineController {

    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private LoanDeadlineRepository loanDeadlineRepository;

        //loads the page
        @GetMapping("/home_loan_deadlines")
        public String showLoanDeadlinePage(HttpSession session, Model model) {
            String username = (String) session.getAttribute("username");
            if (username == null) {
                model.addAttribute("errorMessage", "You must log in first.");
                return "login";
            }
            UserProfile profile = userProfileRepository.findByUsername(username);
            System.out.println(profile.getMyuser());
            if (profile == null || profile.getMyuser() == null) {
                model.addAttribute("errorMessage", "User not found or not linked properly.");
                return "home_loan_deadlines";
            }


        List<LoanDeadline> loanDeadlines = loanDeadlineRepository.findByMyuser(profile.getMyuser());
        model.addAttribute("loanDeadlines", loanDeadlines);

        return "home_loan_deadlines";
    }

    //creates a loan deadline
    @PostMapping("/loan_deadlines/create")
    public String createLoanDeadline(@RequestParam Double amount,
                                    @RequestParam String deadline,
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
    
        LocalDate ldDate = LocalDate.parse(deadline);
    
        // Create and populate the LoanDeadline object
        LoanDeadline loanDeadline = new LoanDeadline();
        loanDeadline.setAmount(amount);
        loanDeadline.setDueDate(ldDate);
        loanDeadline.setMyUser(user);
    
        // Save the Loan Deadline
        loanDeadlineRepository.save(loanDeadline);
    
        // Redirect to home page
        return "redirect:/home_loan_deadlines";
    }

    //updates a loan deadline
    @PostMapping("/loan_deadlines/update")
    public String updateTLoanDeadline(@RequestParam Long id,
                                    @RequestParam Double amount,
                                    @RequestParam String deadline,
                                    HttpSession session) {

        String username = (String) session.getAttribute("username");
        UserProfile profile = userProfileRepository.findByUsername(username);

        //gets the loan deadline from the database and overwrites its data
        if (profile != null && profile.getMyuser() != null) {
            LoanDeadline existing = loanDeadlineRepository.findById(id).orElse(null);
            if (existing != null && existing.getMyUser().getId() == (profile.getMyuser().getId())) {
                existing.setAmount(amount);
                existing.setDueDate(LocalDate.parse(deadline));
                loanDeadlineRepository.save(existing);
            }
        }
        return "redirect:/home_loan_deadlines";
    }

    //delete a loan deadline
    @PostMapping("/loan_deadlines/delete/{id}")
    public String deleteLoanDeadline(@PathVariable Long id, HttpSession session) {
        if (session == null) {
            return "redirect:/login";
        }

        String username = (String) session.getAttribute("username");
        UserProfile profile = userProfileRepository.findByUsername(username);

        //deletes loan deadline if it can be found
        if (profile != null && profile.getMyuser() != null) {
            LoanDeadline tx = loanDeadlineRepository.findById(id).orElse(null);
            if (tx != null && tx.getMyUser().getId() == (profile.getMyuser().getId())) {
                loanDeadlineRepository.delete(tx);
            }
        }

        return "redirect:/home_loan_deadlines";
    }
}
