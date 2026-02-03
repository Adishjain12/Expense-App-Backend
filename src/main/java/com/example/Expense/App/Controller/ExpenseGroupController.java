package com.example.Expense.App.Controller;

import com.example.Expense.App.Dto.ExpenseGroupRequest;
import com.example.Expense.App.Dto.ExpenseGroupResponse;
import com.example.Expense.App.Dto.MinimalUserDTO;
import com.example.Expense.App.Service.Implementation.ExpenseGroupServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class ExpenseGroupController {

    @Autowired
    ExpenseGroupServiceImp expenseGroupServiceImp;

    @PostMapping("/create")
    public ExpenseGroupResponse createGroup(@RequestBody ExpenseGroupRequest request){
        return expenseGroupServiceImp.createGroup(request);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteGroup(@PathVariable String id){
        return expenseGroupServiceImp.deleteGroup(Long.parseLong(id));
    }

    @GetMapping("/allgroups")
    public List<ExpenseGroupResponse> allGroups(){
        return expenseGroupServiceImp.allGroups();
    }

    @PostMapping("/addusers/{id}")
    public ExpenseGroupResponse addUsers(@RequestBody List<Long> userIds, @PathVariable String id){
        return expenseGroupServiceImp.addUsers(userIds,Long.parseLong(id));
    }

    @GetMapping("/{id}")
    public ExpenseGroupResponse getGroupById(@PathVariable Long id) {
        return expenseGroupServiceImp.getGroupById(id);
    }

    @PutMapping("/{id}")
    public ExpenseGroupResponse updateGroup(
            @PathVariable Long id,
            @RequestBody ExpenseGroupRequest request) {

        return expenseGroupServiceImp.updateGroup(id, request);
    }

    @DeleteMapping("/{groupId}/users/{userId}")
    public ExpenseGroupResponse removeUser(
            @PathVariable Long groupId,
            @PathVariable Long userId) {

        return expenseGroupServiceImp.removeUser(groupId, userId);
    }

    @GetMapping("/{id}/users")
    public List<MinimalUserDTO> getGroupMembers(@PathVariable Long id) {
        return expenseGroupServiceImp.getGroupMembers(id);
    }


}
