package com.nhc.Identity_service.service;

import com.nhc.Identity_service.dto.request.UserCreationRequest;
import com.nhc.Identity_service.dto.request.UserUpdateRequest;
import com.nhc.Identity_service.dto.response.UserResponse;
import com.nhc.Identity_service.entity.Role;
import com.nhc.Identity_service.entity.User;
//import com.nhc.Identity_service.enums.Role;
import com.nhc.Identity_service.exception.AppException;
import com.nhc.Identity_service.exception.ErrorCode;
import com.nhc.Identity_service.mapper.UserMapper;
import com.nhc.Identity_service.repository.RoleRepository;
import com.nhc.Identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    public User createRequestUser(UserCreationRequest user){
        if(userRepository.existsByUsername(user.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User newUser = userMapper.toUser(user);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        HashSet<String> roles = new HashSet<>();
        //roles.add(Role.USER.name());
        //newUser.setRoles(roles);

        return userRepository.save(newUser);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('APPROVE_POST')")
    public List<User> fetchAllUser(){
        log.info("in method get users");
        return userRepository.findAll();
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse fetchUser(String id){
        log.info("in method get user");
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserResponse updateUser(String id, UserUpdateRequest user){
        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(updateUser, user);

        updateUser.setPassword(passwordEncoder.encode(user.getPassword()));
        var roles = roleRepository.findAllById(user.getRoles());
        updateUser.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(updateUser));
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }

    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }

}
