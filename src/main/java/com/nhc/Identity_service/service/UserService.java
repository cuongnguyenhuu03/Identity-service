package com.nhc.Identity_service.service;

import com.nhc.Identity_service.dto.request.UserCreationRequest;
import com.nhc.Identity_service.dto.request.UserUpdateRequest;
import com.nhc.Identity_service.entity.User;
import com.nhc.Identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createRequestUser(UserCreationRequest user){
        User newUser = new User();

        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setDob(user.getDob());

        return userRepository.save(newUser);
    }

    public List<User> fetchAllUser(){
        return userRepository.findAll();
    }

    public User fetchUser(String id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(String id, UserUpdateRequest user){
        User updateUser = fetchUser(id);

        updateUser.setPassword(user.getPassword());
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setDob(user.getDob());

        return userRepository.save(updateUser);
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
}
