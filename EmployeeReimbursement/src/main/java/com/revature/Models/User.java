package com.revature.Models;


import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "Users", uniqueConstraints = { @UniqueConstraint(name = "UniqueFirstAndLastName", columnNames = { "firstName", "LastName" }) })
@Component
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserID;


    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String userPass;

    @Column(nullable = false)
    private String userRole;

    @Column(nullable = false)
    private String userEmail;


    //--------------------Getter/Setter code------------------------


    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    //--------------Constructors--------------------------------


    public User() {
    }


    public User(int userID, String firstName, String lastName, String userName, String userPass, String userRole, String userEmail) {
        UserID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userPass = userPass;
        this.userRole = userRole;
        this.userEmail = userEmail;
    }


    //--------------ToString-------------------

    @Override
    public String toString() {
        return "User{" +
                "UserID=" + UserID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
