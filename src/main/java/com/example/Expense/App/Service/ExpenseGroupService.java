package com.example.Expense.App.Service;

import com.example.Expense.App.Dto.ExpenseGroupRequest;
import com.example.Expense.App.Dto.ExpenseGroupResponse;
import com.example.Expense.App.Dto.MinimalUserDTO;

import java.util.List;

public interface ExpenseGroupService {
    ExpenseGroupResponse createGroup(ExpenseGroupRequest request);

    String deleteGroup(Long id);

    List<ExpenseGroupResponse> allGroups();

    ExpenseGroupResponse addUsers(List<Long> userIds, Long id);

    ExpenseGroupResponse getGroupById(Long id);

    ExpenseGroupResponse updateGroup(Long id, ExpenseGroupRequest request);

    ExpenseGroupResponse removeUser(Long groupId, Long userId);

    List<MinimalUserDTO> getGroupMembers(Long id);
}
