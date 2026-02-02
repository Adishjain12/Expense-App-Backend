package com.example.Expense.App.Service.Implementation;

import com.example.Expense.App.Dto.ExpenseGroupRequest;
import com.example.Expense.App.Dto.ExpenseGroupResponse;
import com.example.Expense.App.Dto.MinimalUserDTO;
import com.example.Expense.App.Entity.ExpenseGroup;
import com.example.Expense.App.Entity.UserEntity;
import com.example.Expense.App.Repository.ExpenseGroupRepo;
import com.example.Expense.App.Repository.UserRepo;
import com.example.Expense.App.Service.ExpenseGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseGroupServiceImp implements ExpenseGroupService {
    @Autowired
    ExpenseGroupRepo expenseGroupRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public ExpenseGroupResponse createGroup(ExpenseGroupRequest request) {
        ExpenseGroup newGroup=ExpenseGroup.builder()
                .name(request.getName())
                .build();
        expenseGroupRepo.save(newGroup);
        ExpenseGroupResponse response=ExpenseGroupResponse.builder()
                .id(newGroup.getId())
                .name(newGroup.getName())
                .createdAt(newGroup.getCreatedAt())
                .updatedAt(newGroup.getUpdatedAt())
                .build();
        return response;
    }

    @Override
    public String deleteGroup(Long id) {
        expenseGroupRepo.deleteById(id);
        return "Deleted";
    }

    @Override
    public List<ExpenseGroupResponse> allGroups() {

        List<ExpenseGroup> groups = expenseGroupRepo.findAll();
        List<ExpenseGroupResponse> responses = new ArrayList<>();

        for (ExpenseGroup group : groups) {

            List<MinimalUserDTO> userDtos = new ArrayList<>();

            if (group.getUsers() != null) {
                for (UserEntity user : group.getUsers()) {
                    MinimalUserDTO dto = MinimalUserDTO.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .build();
                    userDtos.add(dto);
                }
            }

            ExpenseGroupResponse response = ExpenseGroupResponse.builder()
                    .id(group.getId())
                    .name(group.getName())
                    .members(userDtos)
                    .createdAt(group.getCreatedAt())
                    .updatedAt(group.getUpdatedAt())
                    .build();

            responses.add(response);
        }

        return responses;
    }


    @Override
    public ExpenseGroupResponse addUsers(List<Long> userIds, Long id) {
        List<UserEntity> users=new ArrayList<>();
        List<MinimalUserDTO> userDto=new ArrayList<>();
        for(Long userId:userIds){
            UserEntity u=userRepo.findById(userId).orElse(null);
            users.add(u);
            MinimalUserDTO dto= MinimalUserDTO.builder()
                    .id(u.getId())
                    .name(u.getName())
                    .build();
            userDto.add(dto);
        }
        ExpenseGroup group=expenseGroupRepo.findById(id).orElse(null);
        group.setUsers(users);
        group=expenseGroupRepo.save(group);

        ExpenseGroupResponse response=ExpenseGroupResponse.builder()
                .id(group.getId())
                .name(group.getName())
                .members(userDto)
                .updatedAt(group.getUpdatedAt())
                .createdAt(group.getCreatedAt())
                .build();
        return response;
    }

    @Override
    public ExpenseGroupResponse getGroupById(Long id) {
        ExpenseGroup group=expenseGroupRepo.findById(id).orElse(null);
        ExpenseGroupResponse response=null;

        List<MinimalUserDTO> userDtos = new ArrayList<>();

        if (group.getUsers() != null) {
            for (UserEntity user : group.getUsers()) {
                MinimalUserDTO dto = MinimalUserDTO.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .build();
                userDtos.add(dto);
            }
        }

        if(group!=null){
            response=ExpenseGroupResponse.builder()
                    .id(group.getId())
                    .name(group.getName())
                    .members(userDtos)
                    .createdAt(group.getCreatedAt())
                    .updatedAt(group.getUpdatedAt())
                    .build();
        }
        return response;
    }

    @Override
    public ExpenseGroupResponse updateGroup(Long id, ExpenseGroupRequest request) {
        return null;
    }

}
