package com.revature.DAOs;


import com.revature.Models.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

import java.util.Optional;

@Repository
public interface ReimbursementDao extends JpaRepository<Reimbursement, Integer> {

    public List<Reimbursement> findByAssignedUserUserID(int userID);

    public List<Reimbursement> findByAssignedUserUserIDAndReimbStatus(int userID, String status);

    public List<Reimbursement> findByReimbStatus(String status);
}
