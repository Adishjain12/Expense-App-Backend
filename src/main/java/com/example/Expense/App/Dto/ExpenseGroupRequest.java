package com.example.Expense.App.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpenseGroupRequest {
    private String name;
}
