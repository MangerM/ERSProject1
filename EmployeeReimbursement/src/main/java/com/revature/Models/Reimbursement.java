package com.revature.Models;


import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "Reimbursements")
@Component
public class Reimbursement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reimbID;

    @Column(nullable = false)
    private String reimbDesc;

    @Column(nullable = false)
    private int reimbAmount;

    @Column(nullable = false)
    private String reimbStatus;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="UserID", nullable = false)
    private User assignedUser;


    //---------------Getter/Setter code ---------------------------------------------


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

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    //--------------------Constructors-----------------------------


    public Reimbursement() {
    }

    public Reimbursement(int reimbID, String reimbDesc, int reimbAmount, String reimbStatus, User assignedUser) {
        this.reimbID = reimbID;
        this.reimbDesc = reimbDesc;
        this.reimbAmount = reimbAmount;
        this.reimbStatus = reimbStatus;
        this.assignedUser = assignedUser;
    }

    public Reimbursement(String reimbDesc, int reimbAmount, String reimbStatus, User assignedUser) {
        this.reimbDesc = reimbDesc;
        this.reimbAmount = reimbAmount;
        this.reimbStatus = reimbStatus;
        this.assignedUser = assignedUser;
    }

    //------------------To string-----------------------------------

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbID=" + reimbID +
                ", reimbDesc='" + reimbDesc + '\'' +
                ", reimbAmount=" + reimbAmount +
                ", reimbStatus='" + reimbStatus + '\'' +
                ", assignedUser=" + assignedUser +
                '}';
    }
}
