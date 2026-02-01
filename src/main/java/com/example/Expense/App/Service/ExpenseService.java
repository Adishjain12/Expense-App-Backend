package com.example.Expense.App.Service;

import com.example.Expense.App.Dto.ExpenseRequest;
import com.example.Expense.App.Dto.ExpenseResponse;

public interface ExpenseService {
    ExpenseResponse createExpense(ExpenseRequest request);
}
