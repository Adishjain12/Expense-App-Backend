package com.example.Expense.App.Controller;

import com.example.Expense.App.Dto.UserRequest;
import com.example.Expense.App.Dto.UserResponse;
import com.example.Expense.App.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerUser(@RequestBody UserRequest request){
        System.out.println("adish");
        try {
            return userService.createUser(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Unable to create user");
        }
    }

    @GetMapping("/users")
    public List<UserResponse> readUsers(){
        System.out.println("Reading");
        return userService.readUsers();
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id){
        try{
            userService.deleteUser(Long.valueOf(id));
        }catch (Exception e){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");
        }
    }
}
