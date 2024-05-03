package com.revature.Models.DTOs;

public class incomingUserDTO {
    private String userName;
    private String userPass;
    private String firstName;
    private String lastName;
    private String userEmail;

    public incomingUserDTO() {
    }

    public incomingUserDTO(String userName, String userPass, String firstName, String lastName, String userEmail) {
        this.userName = userName;
        this.userPass = userPass;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userEmail = userEmail;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "incomingUserDTO{" +
                "userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
