package com.revature.Controller;


import com.revature.DAOs.UserDao;
import com.revature.Models.DTOs.incomingUserDTO;
import com.revature.Models.DTOs.incomingUserLoginDTO;
import com.revature.Models.DTOs.outgoingUserDTO;
import com.revature.Models.DTOs.outgoingUserLimitedDTO;
import com.revature.Models.User;
import com.revature.Services.UserService;
import jakarta.servlet.http.HttpSession;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/Users")
@ResponseBody
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {

    UserDao UD;
    UserService US;

    @Autowired
    public UserController(UserDao UD, UserService US) {
        this.UD = UD;
        this.US = US;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(HttpSession session){

        if(session.getAttribute("userID")== null){
            return ResponseEntity.status(401).body("You must be logged in to attempt to view users");
        }

        try{
            return ResponseEntity.ok(US.fetchAllUsers((int)session.getAttribute("userID")));
        }catch(Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    @PostMapping("/Login")
    public ResponseEntity<Object> getUserByUsernamePassword(@RequestBody incomingUserLoginDTO incoming, HttpSession session){
        Optional<User> optionalUser = US.loginUser(incoming);

        if(optionalUser.isEmpty()){
            return ResponseEntity.status(401).body("Login Failed");
        }
        User u = optionalUser.get();

        session.setAttribute("userID", u.getUserID());
        //Don't have role here but here's how it would look
        //session.setAttribute("userRole", u.getUserRole());

        return ResponseEntity.ok(new outgoingUserLimitedDTO(u.getUserName(), u.getUserRole()));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody incomingUserDTO newUser){

        try{
            US.createUser(newUser);
            return ResponseEntity.status(201).body(newUser.getUserName() + " was created!");
        }catch(Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }


    @DeleteMapping("/{UserID}")
    public ResponseEntity<Object> deleteUser(@PathVariable int UserID, HttpSession session){

        if(session.getAttribute("userID")== null){
            return ResponseEntity.status(401).body("You must be logged in to attempt to fire users");
        }

        try{
            return ResponseEntity.ok(
                    US.fireUser(UserID, (int)session.getAttribute("userID")));
        }catch(Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


    @PatchMapping("/Promote/{UserID}")
    public ResponseEntity<Object> promoteUser(@PathVariable int UserID, @RequestBody String newRole, HttpSession session){

        if(session.getAttribute("userID")== null){
            return ResponseEntity.status(401).body("You must be logged in to promote users");
        }

        try{
            return ResponseEntity.ok(
                    US.promoteUser(UserID, newRole, (int)session.getAttribute("userID")));
        }catch(Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
