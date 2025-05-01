package it326.financialtracker.Controller;

//import java.security.Principal;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

//import it326.financialtracker.Model.UserProfile;
//import it326.financialtracker.Repository.UserProfileRepository;
import it326.financialtracker.Service.UserService;
//import jakarta.websocket.server.PathParam;

@Controller
public class AuthController {

    @Autowired
    private UserService service;
    
    @GetMapping("/index")
    public ResponseEntity<String> home(){
        return new ResponseEntity<>(service.loadByUsername("frog").toString(), HttpStatus.OK);
    }
    
    @GetMapping("/load")
    public ResponseEntity<String> load(){
    	//service.init();
    	service.del();
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    //query setup 1 both of these work more or less the same it's just a difference of what you put in the url
    //yes we make queries/the application takes data via the url
    @GetMapping("/test")
    public ResponseEntity<String> loadWithQuery(@RequestParam String name){//test?name=test
    	return new ResponseEntity<String>(name, HttpStatus.OK);
    }
    //query setup 2
    @GetMapping("/test/{id}")
    public ResponseEntity<Integer> loadWithQuery2(@PathVariable int id){//test/20
    	return new ResponseEntity<Integer>(id, HttpStatus.OK);
    }
    
}
