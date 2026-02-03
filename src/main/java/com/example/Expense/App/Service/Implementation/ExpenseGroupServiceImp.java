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
import java.util.Objects;
import java.util.Optional;

@Service
public class ExpenseGroupServiceImp implements ExpenseGroupService {
    @Autowired
    ExpenseGroupRepo expenseGroupRepo;

    @Autowired
    UserRepo userRepo;

    public ExpenseGroup convertRequestToEntity(ExpenseGroupRequest request){
        return ExpenseGroup.builder()
                .name(request.getName())
                .build();
    }

    public ExpenseGroupResponse convertEntityToResponse(ExpenseGroup entity){
        List<UserEntity> users=entity.getUsers();
        List<MinimalUserDTO> userDTOS=convertUserToMinimalUserDto(users);

        return ExpenseGroupResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .members(userDTOS)
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    private List<MinimalUserDTO> convertUserToMinimalUserDto(List<UserEntity> users) {
        List<MinimalUserDTO> userDTOS=new ArrayList<>();
        for(UserEntity user:users){
            MinimalUserDTO m= MinimalUserDTO.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .build();

            userDTOS.add(m);
        }
        return userDTOS;
    }

    @Override
    public ExpenseGroupResponse createGroup(ExpenseGroupRequest request) {
        ExpenseGroup newGroup=convertRequestToEntity(request);
        expenseGroupRepo.save(newGroup);

        return convertEntityToResponse(newGroup);
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
            ExpenseGroupResponse response = convertEntityToResponse(group);
            responses.add(response);
        }

        return responses;
    }


    @Override
    public ExpenseGroupResponse addUsers(List<Long> userIds, Long id) {
        List<UserEntity> users=new ArrayList<>();

        for(Long userId:userIds){
            UserEntity u=userRepo.findById(userId).orElse(null);
            users.add(u);
        }

        ExpenseGroup group=expenseGroupRepo.findById(id).orElse(null);
        assert group != null;
        group.setUsers(users);
        group=expenseGroupRepo.save(group);

        return convertEntityToResponse(group);
    }

    @Override
    public ExpenseGroupResponse getGroupById(Long id) {
        ExpenseGroup group=expenseGroupRepo.findById(id).orElse(null);
        return convertEntityToResponse(group);
    }

    @Override
    public ExpenseGroupResponse updateGroup(Long id, ExpenseGroupRequest request) {
        ExpenseGroup group=convertRequestToEntity(request);
        expenseGroupRepo.save(group);
        return convertEntityToResponse(group);
    }

    @Override
    public ExpenseGroupResponse removeUser(Long groupId, Long userId) {
        ExpenseGroup group=expenseGroupRepo.findById(groupId).orElse(null);
        List<UserEntity> newList=new ArrayList<>();
        assert group != null;
        for(UserEntity user:group.getUsers()){
            if(!Objects.equals(user.getId(), userId))
                newList.add(user);
        }
        group.setUsers(newList);
        expenseGroupRepo.save(group);

        return convertEntityToResponse(group);
    }

    @Override
    public List<MinimalUserDTO> getGroupMembers(Long id) {
        ExpenseGroup group=expenseGroupRepo.findById(id).orElse(null);
        assert group != null;
        return convertUserToMinimalUserDto(group.getUsers());
    }
}