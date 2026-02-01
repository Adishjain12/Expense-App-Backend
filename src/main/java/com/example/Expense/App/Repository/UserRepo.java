package com.example.Expense.App.Repository;

import com.example.Expense.App.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long> {
    UserEntity findByName(String name);
    Optional<UserEntity> findByEmail(String email);
}
