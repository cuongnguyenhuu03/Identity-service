package com.nhc.Identity_service.controller;


import com.nhc.Identity_service.dto.request.PermissionRequest;
import com.nhc.Identity_service.dto.response.ApiResponse;
import com.nhc.Identity_service.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/permissions")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {

    PermissionService permissionService;

    @PostMapping
    ApiResponse create(@RequestBody PermissionRequest request){
        return ApiResponse.builder()
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse getAll(){
        return ApiResponse.builder()
                .result(permissionService.fetchAll())
                .build();
    }

    @DeleteMapping("/{permission}")
    ApiResponse delete(@PathVariable String permission){
        permissionService.delete(permission);
        return ApiResponse.builder()
                .result("Delete successfully")
                .build();
    }
}
