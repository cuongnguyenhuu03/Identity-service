package com.nhc.Identity_service.service;

import com.nhc.Identity_service.dto.request.UserCreationRequest;
import com.nhc.Identity_service.dto.request.UserUpdateRequest;
import com.nhc.Identity_service.dto.response.UserResponse;
import com.nhc.Identity_service.entity.User;
import com.nhc.Identity_service.exception.AppException;
import com.nhc.Identity_service.exception.ErrorCode;
import com.nhc.Identity_service.mapper.UserMapper;
import com.nhc.Identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public User createRequestUser(UserCreationRequest user){
        if(userRepository.existsByUsername(user.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User newUser = userMapper.toUser(user);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }

    public List<User> fetchAllUser(){
        return userRepository.findAll();
    }

    public UserResponse fetchUser(String id){
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserResponse updateUser(String id, UserUpdateRequest user){
        User updateUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(updateUser, user);
        return userMapper.toUserResponse(userRepository.save(updateUser));
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
}
