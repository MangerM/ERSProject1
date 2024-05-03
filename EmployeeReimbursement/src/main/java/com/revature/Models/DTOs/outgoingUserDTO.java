package com.revature.Models.DTOs;

public class outgoingUserDTO {
    private int UserID;
    private String firstName;
    private String lastName;
    private String userName;
    private String userRole;
    private String userEmail;


    public outgoingUserDTO() {
    }


    public outgoingUserDTO(int userID, String firstName, String lastName, String userName, String userRole, String userEmail) {
        UserID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userRole = userRole;
        this.userEmail = userEmail;
    }

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

    @Override
    public String toString() {
        return "outgoingUserDTO{" +
                "UserID=" + UserID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}

