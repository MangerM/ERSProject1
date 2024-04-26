package com.revature.Controller;


import com.revature.DAOs.UserDao;
import com.revature.Models.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/Users")
@ResponseBody
@CrossOrigin
public class UserController {

    UserDao UD;

    @Autowired
    public UserController(UserDao UD) {
        this.UD = UD;
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){

        List<User> Users = UD.findAll();

        return ResponseEntity.ok(Users);
    }

    @PostMapping("/Login")
    public ResponseEntity<Object> getUserByUsernamePassword(@RequestBody String[] usernamePassword){
        List<User> Users = UD.findAll();

        for(User u : Users){
            if(u.getUserName().equals(usernamePassword[0])){
                if(u.getUserPass().equals(usernamePassword[1])) {
                    return ResponseEntity.status(201).body(u);
                }else{
                    return ResponseEntity.status(404).body("Incorrect Password please try again.");
                }
            } else{
                return ResponseEntity.status(404).body("No User found with the Username Provided "+ usernamePassword[0]);
            }
        }
        return ResponseEntity.status(404).body("No Users found in Database");
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User newUser){
        User u = UD.save(newUser);
        return ResponseEntity.status(201).body(u);
    }


    @DeleteMapping("/{UserID}")
    public ResponseEntity<Object> deleteUser(@PathVariable int UserID){
        Optional<User> returnedUser = UD.findById(UserID);

        if(returnedUser.isEmpty()){
            return ResponseEntity.status(404).body("No User found with the Id Provided "+ UserID);
        }

        UD.delete(returnedUser.get());
        return  ResponseEntity.accepted().body("User " + UserID + " has ben deleted.");
    }


    @PatchMapping("/Promote/{UserID}")
    public ResponseEntity<Object> promoteUser(@PathVariable int UserID, @RequestBody String newRole){
        Optional<User> returnedUser = UD.findById(UserID);

        if(returnedUser.isEmpty()){
            return ResponseEntity.status(404).body("No user with ID " + UserID + " found.");
        }

        User u = returnedUser.get();
        u.setUserRole(newRole);
        UD.save(u);

        return  ResponseEntity.ok("User " + UserID + " promoted to " + newRole);
    }

}
