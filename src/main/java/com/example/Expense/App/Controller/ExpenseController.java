package com.example.Expense.App.Controller;

import com.example.Expense.App.Dto.ExpenseRequest;
import com.example.Expense.App.Dto.ExpenseResponse;
import com.example.Expense.App.Service.ExpenseService;
import com.example.Expense.App.Service.Implementation.ExpenseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @PostMapping("/expense/create")
    public ResponseEntity<ExpenseResponse> createExpense(@RequestBody ExpenseRequest request){
        return new ResponseEntity<>(expenseService.createExpense(request), HttpStatus.CREATED);
    }
}
