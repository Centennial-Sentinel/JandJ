package it326.financialtracker.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import it326.financialtracker.Model.Transaction;
import it326.financialtracker.Model.UserProfile;
import it326.financialtracker.Repository.TransactionRepository;
import it326.financialtracker.Repository.UserProfileRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeTransactionController {

    @Autowired
    private UserProfileRepository userProfileRepository;
    @Autowired
    private TransactionRepository transactionRepository;

        //loads the page
        @GetMapping("/home_transactions")
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

        List<Transaction> transactions = transactionRepository.findByMyuser(profile.getMyuser());
        model.addAttribute("transactions", transactions);

        return "home_transactions";
    }

    //creates a transaction
    @PostMapping("/transactions/create")
    public String createTransaction(@RequestParam Double amount,
                                    @RequestParam String description,
                                    @RequestParam String tag,
                                    @RequestParam String date,
                                    @RequestParam String type,
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
    
        LocalDate txDate = LocalDate.parse(date);
    
        // Create and populate the Transaction object
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTag(tag);
        transaction.setDate(txDate);
        transaction.setMyUser(user);
        transaction.setType(type);
    
        // Save the transaction
        transactionRepository.save(transaction);
    
        // Redirect to home page
        return "redirect:/home_transactions";
    }

    //updates a transaction
    @PostMapping("/transactions/update")
    public String updateTransaction(@RequestParam Long id,
                                    @RequestParam Double amount,
                                    @RequestParam String description,
                                    @RequestParam String tag,
                                    @RequestParam String date,
                                    @RequestParam String type,
                                    HttpSession session) {

        String username = (String) session.getAttribute("username");
        UserProfile profile = userProfileRepository.findByUsername(username);

        //finds the transaction in the database and overwrites its data
        if (profile != null && profile.getMyuser() != null) {
            Transaction existing = transactionRepository.findById(id).orElse(null);
            if (existing != null && existing.getMyUser().getId() == (profile.getMyuser().getId())) {
                existing.setAmount(amount);
                existing.setDescription(description);
                existing.setTag(tag);
                existing.setDate(LocalDate.parse(date));
                existing.setType(type);
                transactionRepository.save(existing);
            }
        }
        return "redirect:/home_transactions";
    }

    //deletes a transaction
    @PostMapping("/transactions/delete/{id}")
    public String deleteTransaction(@PathVariable Long id, HttpSession session) {
        if (session == null) {
            return "redirect:/login";
        }

        String username = (String) session.getAttribute("username");
        UserProfile profile = userProfileRepository.findByUsername(username);

        //finds the transaction and deletes it
        if (profile != null && profile.getMyuser() != null) {
            Transaction tx = transactionRepository.findById(id).orElse(null);
            if (tx != null && tx.getMyUser().getId() == (profile.getMyuser().getId())) {
                transactionRepository.delete(tx);
            }
        }

        return "redirect:/home_transactions";
    }

    @GetMapping("/transactions/search")
    public String searchTransactions(@RequestParam String searchBy, @RequestParam String value, Model model, HttpSession session) {
        UserProfile profile = userProfileRepository.findByUsername((String) session.getAttribute("username"));
        List<Transaction> results = new ArrayList<>();

        switch (searchBy) {
            case "amount":
                results = transactionRepository.findByMyuserAndAmount(profile.getMyuser(), Double.parseDouble(value));
                break;
            case "description":
                results = transactionRepository.findByMyuserAndDescriptionContainingIgnoreCase(profile.getMyuser(), value);
                break;
            case "tag":
                results = transactionRepository.findByMyuserAndTagContainingIgnoreCase(profile.getMyuser(), value);
                break;
            case "type":
                results = transactionRepository.findByMyuserAndType(profile.getMyuser(), value);
                break;
            case "date":
                results = transactionRepository.findByMyuserAndDate(profile.getMyuser(), LocalDate.parse(value));
                break;
        }

        model.addAttribute("transactions", results);
        return "home_transactions";
    }

    @GetMapping("/transactions/sort")
        public String sortTransactions(
                @RequestParam String sortAttribute,
                @RequestParam String sortOrder,
                Model model,
                HttpSession session) {

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
        
                List<Transaction> transactions = transactionRepository.findByMyuser(profile.getMyuser());

                if (sortAttribute != null && sortOrder != null) {
                    Comparator<Transaction> comparator = switch (sortAttribute) {
                        case "amount" -> Comparator.comparing(Transaction::getAmount);
                        case "date" -> Comparator.comparing(Transaction::getDate);
                        default -> null;
                    };
                
                    if (comparator != null) {
                        if (sortOrder.equalsIgnoreCase("desc")) {
                            comparator = comparator.reversed();
                        }
                        transactions.sort(comparator);
                    }
                }

                model.addAttribute("transactions", transactions);
                return "home_transactions";
    }
}
