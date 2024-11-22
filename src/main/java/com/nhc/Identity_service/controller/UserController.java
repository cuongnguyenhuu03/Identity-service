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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
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
    public ApiResponse fetchAllUser(){

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username : {}", authentication.getName());
        authentication.getAuthorities()
                .forEach(grantedAuthority -> log.info("Roles : {}", grantedAuthority.getAuthority()));

        return ApiResponse.builder()
                .result(userService.fetchAllUser())
                .build();
    }

    @GetMapping("/users/{id}")
    public ApiResponse fetchUser(@PathVariable("id") String id){
        return ApiResponse.builder()
                .result(userService.fetchUser(id))
                .build();
    }

    @PutMapping("/users/{id}")
    public ApiResponse updateUser(@PathVariable("id") String id, @RequestBody UserUpdateRequest user){
        return ApiResponse.builder()
                .result(userService.updateUser(id, user))
                .build();
    }

    @DeleteMapping("/users/{id}")
    public ApiResponse deleteUser(@PathVariable("id") String id){
        userService.deleteUser(id);
        return ApiResponse.builder()
                .result("delete user successfully")
                .build();
    }

    @GetMapping("/users/info")
    public ApiResponse getInfo(){
        return ApiResponse.builder()
                .result(userService.getMyInfo())
                .build();
    }

}
