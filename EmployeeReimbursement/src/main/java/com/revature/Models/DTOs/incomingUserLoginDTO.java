package com.revature.Models.DTOs;

public class incomingUserLoginDTO {
    private String userName;
    private String userPass;

    public incomingUserLoginDTO() {
    }

    public incomingUserLoginDTO(String userName, String userPass) {
        this.userName = userName;
        this.userPass = userPass;
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

    @Override
    public String toString() {
        return "incomingUserLoginDTO{" +
                "userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                '}';
    }
}
