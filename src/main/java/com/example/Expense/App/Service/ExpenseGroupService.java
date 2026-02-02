package com.example.Expense.App.Service;

import com.example.Expense.App.Dto.ExpenseGroupRequest;
import com.example.Expense.App.Dto.ExpenseGroupResponse;

import java.util.List;

public interface ExpenseGroupService {
    ExpenseGroupResponse createGroup(ExpenseGroupRequest request);

    String deleteGroup(Long id);

    List<ExpenseGroupResponse> allGroups();

    ExpenseGroupResponse addUsers(List<Long> userIds, Long id);

    ExpenseGroupResponse getGroupById(Long id);

    ExpenseGroupResponse updateGroup(Long id, ExpenseGroupRequest request);
}
