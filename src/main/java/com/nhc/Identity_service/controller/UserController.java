package com.nhc.Identity_service.controller;

import com.nhc.Identity_service.dto.request.UserCreationRequest;
import com.nhc.Identity_service.dto.request.UserUpdateRequest;
import com.nhc.Identity_service.dto.response.ApiResponse;
import com.nhc.Identity_service.dto.response.UserResponse;
import com.nhc.Identity_service.entity.User;
import com.nhc.Identity_service.mapper.UserMapper;
import com.nhc.Identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping("/users")
    public ApiResponse createUser(
            @Valid @RequestBody UserCreationRequest user
            ){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(userService.createRequestUser(user));
        return apiResponse;
    }

    @GetMapping("/users")
    public List<User> fetchAllUser(){
        return userService.fetchAllUser() ;
    }

    @GetMapping("/users/{id}")
    public UserResponse fetchUser(@PathVariable("id") String id){
        return  userService.fetchUser(id);
    }

    @PutMapping("/users/{id}")
    public UserResponse updateUser(@PathVariable("id") String id, @RequestBody UserUpdateRequest user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") String id){
        userService.deleteUser(id);
        return "delete user successfully";
    }

}
