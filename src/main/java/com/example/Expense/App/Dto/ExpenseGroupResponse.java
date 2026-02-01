package com.example.Expense.App.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseGroupResponse {
    private Long id;
    private String name;

    private List<MinimalUserDTO> members;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
