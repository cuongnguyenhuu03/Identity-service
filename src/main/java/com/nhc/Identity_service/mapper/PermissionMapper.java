package com.nhc.Identity_service.mapper;

import com.nhc.Identity_service.dto.request.PermissionRequest;
import com.nhc.Identity_service.dto.response.PermissionResponse;
import com.nhc.Identity_service.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPermission(PermissionRequest user);

    PermissionResponse toPermissionResponse(Permission permission);
}
