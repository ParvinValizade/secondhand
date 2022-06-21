package com.company.secondhand.user.repository;

import com.company.secondhand.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByMail(String mail);
}
