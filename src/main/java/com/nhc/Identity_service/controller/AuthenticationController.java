package com.nhc.Identity_service.controller;


import com.nhc.Identity_service.dto.request.AuthenticationRequest;
import com.nhc.Identity_service.dto.response.ApiResponse;
import com.nhc.Identity_service.dto.response.AuthenticationResponse;
import com.nhc.Identity_service.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse authenticate(@RequestBody AuthenticationRequest request){
        boolean result =  authenticationService.authenticate(request);
        return ApiResponse.builder()
                .result(AuthenticationResponse.builder()
                        .isAuthenticated(result).build())
                .build();
    }
}
