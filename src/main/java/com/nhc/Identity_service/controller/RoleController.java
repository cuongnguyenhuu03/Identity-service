package com.nhc.Identity_service.controller;

import com.nhc.Identity_service.dto.request.RoleRequest;
import com.nhc.Identity_service.dto.response.ApiResponse;
import com.nhc.Identity_service.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse create(@RequestBody RoleRequest request) {
        return ApiResponse.builder()
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse getAll() {
        return ApiResponse.builder()
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{role}")
    ApiResponse delete(@PathVariable String role) {
        roleService.delete(role);
        return ApiResponse.builder()
                .result("delete successfully")
                .build();
    }
}