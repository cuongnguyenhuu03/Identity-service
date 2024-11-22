package com.nhc.Identity_service.mapper;

import com.nhc.Identity_service.dto.request.RoleRequest;
import com.nhc.Identity_service.dto.response.RoleResponse;
import com.nhc.Identity_service.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);

}