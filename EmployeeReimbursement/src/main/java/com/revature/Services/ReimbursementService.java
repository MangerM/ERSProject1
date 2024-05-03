package com.revature.Services;

import com.revature.DAOs.ReimbursementDao;
import com.revature.DAOs.UserDao;
import com.revature.Models.DTOs.incomingReimbursementDTO;
import com.revature.Models.DTOs.outgoingReimbursementDTO;
import com.revature.Models.Reimbursement;
import org.springframework.stereotype.Service;
import com.revature.Models.User;

import java.util.*;

@Service
public class ReimbursementService {

    private ReimbursementDao reimbursementDao;

    private UserDao userDao;

    public ReimbursementService(ReimbursementDao reimbursementDao, UserDao userDao) {
        this.reimbursementDao = reimbursementDao;
        this.userDao = userDao;
    }

    public ReimbursementDao getReimbursementDao() {
        return reimbursementDao;
    }

    public void setReimbursementDao(ReimbursementDao reimbursementDao) {
        this.reimbursementDao = reimbursementDao;
    }

    public List<outgoingReimbursementDTO> getAllReimbursementsByUser(int UserID){

        List<Reimbursement> reimbursements = reimbursementDao.findByAssignedUserUserID(UserID);
        List<outgoingReimbursementDTO> results = new ArrayList<>();

        for(Reimbursement r : reimbursements){
            results.add(new outgoingReimbursementDTO(r.getReimbID(), r.getReimbDesc(), r.getReimbAmount(), r.getReimbStatus(), r.getAssignedUser().getUserID(), r.getAssignedUser().getUserName()));
        }
        Collections.sort(results, Comparator.comparing(outgoingReimbursementDTO::getReimbID));
        return results;
    }

    public List<outgoingReimbursementDTO> getAllReimbursements(){

        List<Reimbursement> reimbursements = reimbursementDao.findAll();
        List<outgoingReimbursementDTO> results = new ArrayList<>();

        for(Reimbursement r : reimbursements){
            results.add(new outgoingReimbursementDTO(r.getReimbID(), r.getReimbDesc(), r.getReimbAmount(), r.getReimbStatus(), r.getAssignedUser().getUserID(), r.getAssignedUser().getUserName()));
        }
        Collections.sort(results, Comparator.comparing(outgoingReimbursementDTO::getReimbID));
        return results;
    }

    public List<outgoingReimbursementDTO> getAllReimbursementsByUserByStatus(int UserID, String status){

        List<Reimbursement> reimbursements = reimbursementDao.findByAssignedUserUserIDAndReimbStatus(UserID, status);
        List<outgoingReimbursementDTO> results = new ArrayList<>();

        for(Reimbursement r : reimbursements){
            results.add(new outgoingReimbursementDTO(r.getReimbID(), r.getReimbDesc(), r.getReimbAmount(), r.getReimbStatus(), r.getAssignedUser().getUserID(), r.getAssignedUser().getUserName()));
        }
        Collections.sort(results, Comparator.comparing(outgoingReimbursementDTO::getReimbID));
        return results;
    }

    public List<outgoingReimbursementDTO> getAllReimbursementsByStatus(String status){

        List<Reimbursement> reimbursements = reimbursementDao.findByReimbStatus(status);
        List<outgoingReimbursementDTO> results = new ArrayList<>();

        for(Reimbursement r : reimbursements){
            results.add(new outgoingReimbursementDTO(r.getReimbID(), r.getReimbDesc(), r.getReimbAmount(), r.getReimbStatus(), r.getAssignedUser().getUserID(), r.getAssignedUser().getUserName()));
        }
        Collections.sort(results, Comparator.comparing(outgoingReimbursementDTO::getReimbID));
        return results;
    }

    public String newReimbursement(int userID, incomingReimbursementDTO incoming){

        Optional<User> user = userDao.findById(userID);

        if(user.isEmpty()){
            throw new NoSuchElementException("User does not exist");
        }

        Reimbursement r = new Reimbursement(incoming.getReimbDesc(), incoming.getReimbAmount(), "Pending", user.get());

        Reimbursement r2 = reimbursementDao.save(r);
        return "New reimbursement request " + r2.getReimbID() + " Submitted";
    }

    public String cloneReimbursement(int userID, int reimbID){
        Optional<User> user = userDao.findById(userID);
        Optional<Reimbursement> reimb = reimbursementDao.findById(reimbID);

        if(user.isEmpty()){
            throw new NoSuchElementException("User does not exist");
        }
        if(reimb.isEmpty()){
            throw new NoSuchElementException("Reimbursement to clone does not exist");
        }

        Reimbursement r = reimb.get();
        User u = user.get();

        if(r.getAssignedUser() != u){
            throw new IllegalArgumentException("Cannot clone reimbursement of someone other than yourself");
        }

        Reimbursement newR = new Reimbursement(r.getReimbDesc(), r.getReimbAmount(), "Pending", u);

        Reimbursement result = reimbursementDao.save(newR);

        return "Reimbursement " + result.getReimbID() + " submitted";

    }

    public String updateDesc(int userID, int reimbID, String newDesc){
        Optional<User> user = userDao.findById(userID);
        Optional<Reimbursement> reimb = reimbursementDao.findById(reimbID);

        if(user.isEmpty()){
            throw new NoSuchElementException("User does not exist");
        }
        if(reimb.isEmpty()){
            throw new NoSuchElementException("Reimbursement to modify does not exist");
        }

        Reimbursement r = reimb.get();
        User u = user.get();

        if(r.getAssignedUser() != u){
            throw new IllegalArgumentException("Cannot modify reimbursement of someone other than yourself");
        }
        if(!r.getReimbStatus().equals("Pending")){
            throw new IllegalArgumentException("Cannot modify description of resolved reimbursement");
        }

        r.setReimbDesc(newDesc);
        reimbursementDao.save(r);
        return  "Reimbursement " + r.getReimbID() + " description changed to " + newDesc;
    }

    public String resolve(int userID, int reimbID, String newStatus){
        Optional<User> user = userDao.findById(userID);
        Optional<Reimbursement> reimb = reimbursementDao.findById(reimbID);

        if(user.isEmpty()){
            throw new NoSuchElementException("User does not exist");
        }
        if(reimb.isEmpty()){
            throw new NoSuchElementException("Reimbursement to modify does not exist");
        }

        Reimbursement r = reimb.get();
        User u = user.get();

        if(!u.getUserRole().equals("Manager")){
            throw new IllegalArgumentException("Only Managers can resolve reimbursements");
        }
        if(r.getAssignedUser() == u){
            throw new IllegalArgumentException("Cannot resolve reimbursement for yourself");
        }
        if(!r.getReimbStatus().equals("Pending")){
            throw new IllegalArgumentException("Cannot resolve already resolved reimbursement");
        }

        r.setReimbStatus(newStatus);
        reimbursementDao.save(r);
        return  "Reimbursement " + r.getReimbID() + " " + r.getReimbStatus();
    }
}
