package com.revature.Controller;


import com.revature.DAOs.ReimbursementDao;
import com.revature.DAOs.UserDao;
import com.revature.Models.Reimbursement;
import com.revature.Models.User;
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
@CrossOrigin
@ResponseBody
public class ReimbursementController {

    ReimbursementDao RD;
    UserDao UD;

    @Autowired
    public ReimbursementController(ReimbursementDao RD, UserDao UD) {
        this.RD = RD;
        this.UD = UD;
    }


    @GetMapping("/{userID}")
    public ResponseEntity<Object> getAllReimbursements(@PathVariable int userID){

        Optional<User> signedUser = UD.findById(userID);

        List<Reimbursement> reimbursements = RD.findAll();
        List<Reimbursement> ownedReimbursements = new ArrayList<>();

        if(signedUser.isPresent()){
            if(signedUser.get().getUserRole().equals("Manager")) {
                return ResponseEntity.ok(reimbursements);
            }else{
                for(Reimbursement r : reimbursements){
                    if(r.getAssignedUser() == signedUser.get()){
                        ownedReimbursements.add(r);
                    }
                }
                return ResponseEntity.ok(ownedReimbursements);
            }
        }

        //This response should never return since only signed in users can access the reimbursement information.
        return ResponseEntity.status(404).body("Cannot fetch reimbursements if non valid user.");
    }

    @GetMapping("/Pending/{userID}")
    public ResponseEntity<Object> getAllPendingReimbursements(@PathVariable int userID){

        Optional<User> signedUser = UD.findById(userID);

        List<Reimbursement> reimbursements = RD.findAll();
        List<Reimbursement> ownedReimbursements = new ArrayList<>();
        List<Reimbursement> pendingReimbursements = new ArrayList<>();

        for(Reimbursement r : reimbursements){
            if(r.getReimbStatus().equals("Pending")){
                pendingReimbursements.add(r);
            }
        }

        if(signedUser.isPresent()){
            if(signedUser.get().getUserRole().equals("Manager")) {
                return ResponseEntity.ok(pendingReimbursements);
            }else{
                for(Reimbursement r : pendingReimbursements){
                    if(r.getAssignedUser() == signedUser.get()){
                        ownedReimbursements.add(r);
                    }
                }
                return ResponseEntity.ok(ownedReimbursements);
            }
        }

        //This response should never return since only signed in users can access the reimbursement information.
        return ResponseEntity.status(404).body("Cannot fetch reimbursements if non valid user.");
    }



    //Need 2 patch requests, one to update desc one to resolve and change status
    //Need 2 post requests, one to create a brand new request, one to copy an existing request

    @PostMapping("/{UserID}")
    public ResponseEntity<Object> newReimbursement(@RequestBody Reimbursement submittedReimbursement, @PathVariable int UserID){

        Optional<User> signedUser = UD.findById(UserID);

        if(signedUser.isEmpty()){
            return  ResponseEntity.status(404).body("Invalid User ID.");
        }
        submittedReimbursement.setAssignedUser(signedUser.get());
        RD.save(submittedReimbursement);
        return ResponseEntity.accepted().body(submittedReimbursement);
    }

    @PostMapping("/Renew/{reimbursementID}")
    public ResponseEntity<Object> cloneReimbursement(@PathVariable int reimbursementID){
        Optional<Reimbursement> existingReimbursement = RD.findById(reimbursementID);

        if(existingReimbursement.isEmpty()){
            return ResponseEntity.status(404).body("Invalid ReimbursementID");
        }

        Reimbursement oldReimbursement = existingReimbursement.get();
        Reimbursement newReimbursement = new Reimbursement();
        newReimbursement.setReimbDesc(oldReimbursement.getReimbDesc());
        newReimbursement.setReimbStatus("Pending");
        newReimbursement.setAssignedUser(oldReimbursement.getAssignedUser());
        newReimbursement.setReimbAmount(oldReimbursement.getReimbAmount());
        Reimbursement newReimbursement2 = RD.save(newReimbursement);

        return ResponseEntity.status(201).body(newReimbursement2);
    }

    @PatchMapping("/{ReimbursementID}")
    public ResponseEntity<Object> updateDesc(@PathVariable int ReimbursementID, @RequestBody String newDesc){
        Optional<Reimbursement> existingReimbursement = RD.findById(ReimbursementID);

        if(existingReimbursement.isEmpty()){
            return ResponseEntity.status(404).body("Invalid ReimbursementID");
        }
        Reimbursement oldReimbursement = existingReimbursement.get();
        if(!oldReimbursement.getReimbStatus().equals("Pending")){
            return ResponseEntity.status(404).body("Cannot change description of non-pending request.");
        }
        oldReimbursement.setReimbDesc(newDesc);
        Reimbursement newReimbursement = RD.save(oldReimbursement);
        return ResponseEntity.status(201).body(newReimbursement);
    }

    @PatchMapping("/Resolve/{ReimbursementID}")
    public ResponseEntity<Object> resolveReimbursement(@PathVariable int ReimbursementID, @RequestBody String newStatus){
        Optional<Reimbursement> existingReimbursement = RD.findById(ReimbursementID);

        if(existingReimbursement.isEmpty()){
            return ResponseEntity.status(404).body("Invalid ReimbursementID");
        }
        Reimbursement oldReimbursement = existingReimbursement.get();

        if(!oldReimbursement.getReimbStatus().equals("Pending")){
            return ResponseEntity.status(404).body("Reimbursement not pending resolution");
        }

        oldReimbursement.setReimbStatus(newStatus);
        Reimbursement newReimbursement = RD.save(oldReimbursement);
        return ResponseEntity.status(201).body(newReimbursement);
    }
}
