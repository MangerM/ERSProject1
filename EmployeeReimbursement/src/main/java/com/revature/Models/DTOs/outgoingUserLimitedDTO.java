package com.revature.Models.DTOs;

public class outgoingUserLimitedDTO {
    private String userName;
    private String userRole;

    public outgoingUserLimitedDTO() {
    }

    public outgoingUserLimitedDTO(String userName, String userRole) {
        this.userName = userName;
        this.userRole = userRole;
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

    @Override
    public String toString() {
        return "outgoingUserLimitedDTO{" +
                "userName='" + userName + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
