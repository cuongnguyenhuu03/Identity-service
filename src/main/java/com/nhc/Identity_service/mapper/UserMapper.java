package com.nhc.Identity_service.mapper;

import com.nhc.Identity_service.dto.request.UserCreationRequest;
import com.nhc.Identity_service.dto.request.UserUpdateRequest;
import com.nhc.Identity_service.dto.response.UserResponse;
import com.nhc.Identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest user);
    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdate);
    UserResponse toUserResponse(User user);
}
