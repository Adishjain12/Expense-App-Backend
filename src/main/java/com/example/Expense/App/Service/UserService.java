package com.example.Expense.App.Service;

import com.example.Expense.App.Dto.UserRequest;
import com.example.Expense.App.Dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);
    List<UserResponse> readUsers();
    void deleteUser(Long id);
}
