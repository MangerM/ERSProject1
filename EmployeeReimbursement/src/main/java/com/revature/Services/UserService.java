package com.revature.Services;

import com.revature.DAOs.UserDao;
import com.revature.Models.DTOs.incomingUserDTO;
import com.revature.Models.DTOs.incomingUserLoginDTO;
import com.revature.Models.DTOs.outgoingReimbursementDTO;
import com.revature.Models.DTOs.outgoingUserDTO;
import com.revature.Models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.*;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User createUser(incomingUserDTO incoming){

        if(incoming.getUserName().isBlank()){
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if(incoming.getUserPass().isBlank()){
            throw new IllegalArgumentException("Password cannot be empty");
        }

        if(incoming.getUserEmail().isBlank()){
            throw new IllegalArgumentException("Email cannot be empty");
        }

        if(incoming.getFirstName().isBlank()){
            throw new IllegalArgumentException("First name cannot be empty");
        }

        if(incoming.getLastName().isBlank()){
            throw new IllegalArgumentException("Last name cannot be empty");
        }

        Optional<User> possibleUser = userDao.findByUserEmail(incoming.getUserEmail());

        if(possibleUser.isPresent()){
            throw new IllegalArgumentException("User with that email already exists");
        }

        possibleUser = userDao.findByUserNameAndUserPass(incoming.getUserName(), incoming.getUserPass());

        if(possibleUser.isPresent()){
            throw new IllegalArgumentException("User with that username and password already exists");
        }

        possibleUser = userDao.findByFirstNameAndLastName(incoming.getFirstName(), incoming.getLastName());

        if(possibleUser.isPresent()){
            throw new IllegalArgumentException("User with that First and Last name already exists");
        }

        User u = new User(incoming.getFirstName(), incoming.getLastName(), incoming.getUserName(), incoming.getUserPass(), "Employee", incoming.getUserEmail());

        return userDao.save(u);

    }

    public Optional<User> loginUser (incomingUserLoginDTO incoming) throws IllegalArgumentException{

        return userDao.findByUserNameAndUserPass(incoming.getUserName(), incoming.getUserPass());
    }

    public String fireUser (int userFiredID, int userID) {

        Optional<User> optionalUser = userDao.findById(userFiredID);

        if(optionalUser.isEmpty()){
            throw new NoSuchElementException("User to fire doesn't exist");
        }

        User u = optionalUser.get();

        Optional<User> manager = userDao.findById(userID);

        if(manager.isEmpty()){
            throw new NoSuchElementException("Manager does not exist");
        }
        if(manager.get() == u){
            throw new IllegalArgumentException("You cannot fire yourself");
        }

        if(!manager.get().getUserRole().equals("Manager")){
            throw new IllegalArgumentException("You do not have permission to fire users");
        }else{
            userDao.delete(u);
        }

        return "User " + u.getUserID() + " " + u.getFirstName() + " " + u.getLastName() + " Fired";
    }

    public String promoteUser (int userID, String newRole, int managerID){
        Optional<User> userToUpdate = userDao.findById(userID);

        if(userToUpdate.isEmpty()){
            throw new NoSuchElementException("User to update doesn't exist");
        }

        User u = userToUpdate.get();

        Optional<User> manager = userDao.findById(managerID);

        if(manager.isEmpty()){
            throw new NoSuchElementException("Manager not signed in");
        }

        User m = manager.get();

        if(!m.getUserRole().equals("Manager")) {
            throw new NoSuchElementException("As a non-manager you cannot update user roles");
        }else{
            u.setUserRole(newRole);
            userDao.save(u);
            return u.getUserName() + " was promoted to " + u.getUserRole();
        }
    }

    public List<outgoingUserDTO> fetchAllUsers(int managerID){
        Optional<User> manager = userDao.findById(managerID);

        if(manager.isEmpty()){
            throw new NoSuchElementException("Manager not signed in");
        }

        User m = manager.get();

        if(!m.getUserRole().equals("Manager")) {
            throw new NoSuchElementException("As a non-manager you cannot view users");
        }else{

            List<User> userList = userDao.findAll();
            List<outgoingUserDTO> resultList = new ArrayList<>();

            for(User u : userList){
                resultList.add(new outgoingUserDTO(u.getUserID(), u.getFirstName(), u.getLastName(), u.getUserName(), u.getUserRole(), u.getUserEmail()));
            }
            Collections.sort(resultList, Comparator.comparing(outgoingUserDTO::getUserID));
            return resultList;
        }
    }
}
