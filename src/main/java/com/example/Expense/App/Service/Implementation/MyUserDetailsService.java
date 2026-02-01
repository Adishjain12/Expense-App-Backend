package com.example.Expense.App.Service.Implementation;


import com.example.Expense.App.Entity.UserEntity;
import com.example.Expense.App.Entity.UserPrincipal;
import com.example.Expense.App.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity existingUser=userRepo.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("Email not found for the email:"+email));
        System.out.println(existingUser);

        return new UserPrincipal(existingUser);

    }
}
