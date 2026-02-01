package com.example.Expense.App.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;

    private GroupSummaryDTO group;
    private MinimalUserDTO user;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
