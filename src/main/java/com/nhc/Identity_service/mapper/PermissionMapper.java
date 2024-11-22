package com.nhc.Identity_service.mapper;

import com.nhc.Identity_service.dto.request.PermissionRequest;
import com.nhc.Identity_service.dto.request.UserCreationRequest;
import com.nhc.Identity_service.dto.request.UserUpdateRequest;
import com.nhc.Identity_service.dto.response.PermissionResponse;
import com.nhc.Identity_service.dto.response.UserResponse;
import com.nhc.Identity_service.entity.Permission;
import com.nhc.Identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPermission(PermissionRequest user);

    PermissionResponse toPermissionResponse(Permission permission);
}
