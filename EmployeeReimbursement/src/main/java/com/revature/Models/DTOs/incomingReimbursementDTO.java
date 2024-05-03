package com.revature.Models.DTOs;

public class incomingReimbursementDTO {
    private String reimbDesc;
    private int reimbAmount;

    public incomingReimbursementDTO(String reimbDesc, int reimbAmount) {
        this.reimbDesc = reimbDesc;
        this.reimbAmount = reimbAmount;
    }

    public incomingReimbursementDTO() {
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

    @Override
    public String toString() {
        return "incomingReimbursementDTO{" +
                "reimbDesc='" + reimbDesc + '\'' +
                ", reimbAmount=" + reimbAmount +
                '}';
    }
}
