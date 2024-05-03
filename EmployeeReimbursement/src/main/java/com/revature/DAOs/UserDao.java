package com.revature.DAOs;


import com.revature.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    public Optional<User> findByUserNameAndUserPass (String userName, String userPass) ;

    public Optional<User> findByUserEmail (String userEmail);

    public Optional<User> findByFirstNameAndLastName(String firstName, String lastName);

}
