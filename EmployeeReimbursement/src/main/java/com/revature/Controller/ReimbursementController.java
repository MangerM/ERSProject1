package com.revature.Controller;


import com.revature.DAOs.ReimbursementDao;
import com.revature.DAOs.UserDao;
import com.revature.Models.DTOs.incomingReimbursementDTO;
import com.revature.Models.Reimbursement;
import com.revature.Models.User;
import com.revature.Services.ReimbursementService;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value="/Reimbursement")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@ResponseBody
public class ReimbursementController {

    ReimbursementDao RD;
    UserDao UD;
    ReimbursementService RS;

    @Autowired
    public ReimbursementController(ReimbursementDao RD, UserDao UD, ReimbursementService RS) {
        this.RD = RD;
        this.UD = UD;
        this.RS = RS;
    }

    @GetMapping
    public ResponseEntity<Object> getAllReimbursements(HttpSession session){

        if(session.getAttribute("userID")== null){
            return ResponseEntity.status(401).body("You must be logged in to view reimbursements");
        }

        Optional<User> u = UD.findById((int)session.getAttribute("userID"));

        if(u.isEmpty()){
            return ResponseEntity.status(401).body("Signed in user does not exist");
            //This should never happen
        }

        User person = u.get();

        if(person.getUserRole().equals("Manager")){
            return ResponseEntity.ok(RS.getAllReimbursements());
        }else{
            return ResponseEntity.ok(RS.getAllReimbursementsByUser(person.getUserID()));
        }
    }

    @GetMapping("/Pending")
    public ResponseEntity<Object> getAllPendingReimbursements(HttpSession session){


        if(session.getAttribute("userID")== null){
            return ResponseEntity.status(401).body("You must be logged in to view reimbursements");
        }

        Optional<User> u = UD.findById((int)session.getAttribute("userID"));

        if(u.isEmpty()){
            return ResponseEntity.status(401).body("Signed in user does not exist");
            //This should never happen
        }

        User person = u.get();

        if(person.getUserRole().equals("Manager")){
            return ResponseEntity.ok(RS.getAllReimbursementsByStatus("Pending"));
        }else{
            return ResponseEntity.ok(RS.getAllReimbursementsByUserByStatus(person.getUserID(), "Pending"));
        }
    }



    @PostMapping
    public ResponseEntity<Object> newReimbursement(@RequestBody incomingReimbursementDTO submittedReimbursement, HttpSession session){

        if(session.getAttribute("userID")== null){
            return ResponseEntity.status(401).body("You must be logged in to submit reimbursements");
        }

        return ResponseEntity.ok(RS.newReimbursement((int)session.getAttribute("userID"),submittedReimbursement));

    }

    @PostMapping("/Renew/{reimbursementID}")
    public ResponseEntity<Object> cloneReimbursement(@PathVariable int reimbursementID, HttpSession session){
        if(session.getAttribute("userID")== null){
            return ResponseEntity.status(401).body("You must be logged in to clone reimbursements");
        }

        try{
            return ResponseEntity.ok(RS.cloneReimbursement((int)session.getAttribute("userID"),reimbursementID));
        }catch(Exception e){
            return ResponseEntity.status(401).body(e.getMessage());
        }

    }

    @PatchMapping("/{ReimbursementID}")
    public ResponseEntity<Object> updateDesc(@PathVariable int ReimbursementID, @RequestBody String newDesc, HttpSession session){


        if(session.getAttribute("userID")== null){
            return ResponseEntity.status(401).body("You must be logged in to modify reimbursements");
        }

        try{
            return ResponseEntity.ok(RS.updateDesc((int)session.getAttribute("userID"), ReimbursementID, newDesc));
        }catch(Exception e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PatchMapping("/Resolve/{ReimbursementID}")
    public ResponseEntity<Object> resolveReimbursement(@PathVariable int ReimbursementID, @RequestBody String newStatus, HttpSession session){

        if(session.getAttribute("userID")== null){
            return ResponseEntity.status(401).body("You must be logged in to modify reimbursements");
        }

        try{
            return ResponseEntity.ok(RS.resolve((int)session.getAttribute("userID"), ReimbursementID, newStatus));
        }catch(Exception e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
