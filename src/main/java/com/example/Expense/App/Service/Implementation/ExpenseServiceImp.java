package com.example.Expense.App.Service.Implementation;

import com.example.Expense.App.Dto.ExpenseRequest;
import com.example.Expense.App.Dto.ExpenseResponse;
import com.example.Expense.App.Entity.Expense;
import com.example.Expense.App.Service.ExpenseService;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImp implements ExpenseService {
    @Override
    public ExpenseResponse createExpense(ExpenseRequest request) {
        return null;
    }
}
