package com.revature.Models.DTOs;

public class outgoingReimbursementDTO {
    private int reimbID;
    private String reimbDesc;
    private int reimbAmount;
    private String reimbStatus;
    private int reimbUserID;
    private String reimbUserName;

    public outgoingReimbursementDTO() {
    }

    public outgoingReimbursementDTO(int reimbID, String reimbDesc, int reimbAmount, String reimbStatus, int reimbUserID, String reimbUserName) {
        this.reimbID = reimbID;
        this.reimbDesc = reimbDesc;
        this.reimbAmount = reimbAmount;
        this.reimbStatus = reimbStatus;
        this.reimbUserID = reimbUserID;
        this.reimbUserName = reimbUserName;
    }

    public int getReimbID() {
        return reimbID;
    }

    public void setReimbID(int reimbID) {
        this.reimbID = reimbID;
    }

    public String getReimbDesc() {
        return reimbDesc;
    }

    public void setReimbDesc(String reimbDesc) {
        this.reimbDesc = reimbDesc;
    }

    public int getReimbAmount() {
        return reimbAmount;
    }

    public void setReimbAmount(int reimbAmount) {
        this.reimbAmount = reimbAmount;
    }

    public String getReimbStatus() {
        return reimbStatus;
    }

    public void setReimbStatus(String reimbStatus) {
        this.reimbStatus = reimbStatus;
    }

    public int getReimbUserID() {
        return reimbUserID;
    }

    public void setReimbUserID(int reimbUserID) {
        this.reimbUserID = reimbUserID;
    }

    public String getReimbUserName() {
        return reimbUserName;
    }

    public void setReimbUserName(String reimbUserName) {
        this.reimbUserName = reimbUserName;
    }

    @Override
    public String toString() {
        return "outgoingReimbursementDTO{" +
                "reimbID=" + reimbID +
                ", reimbDesc='" + reimbDesc + '\'' +
                ", reimbAmount=" + reimbAmount +
                ", reimbStatus='" + reimbStatus + '\'' +
                ", reimbUserID=" + reimbUserID +
                ", reimbUserName='" + reimbUserName + '\'' +
                '}';
    }
}
