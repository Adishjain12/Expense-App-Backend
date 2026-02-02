package com.example.Expense.App.Repository;

import com.example.Expense.App.Entity.ExpenseGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseGroupRepo extends JpaRepository<ExpenseGroup,Long> {
}
