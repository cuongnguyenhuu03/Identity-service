package com.nhc.Identity_service.controller;


import com.nhc.Identity_service.dto.request.AuthenticationRequest;
import com.nhc.Identity_service.dto.request.IntrospectRequest;
import com.nhc.Identity_service.dto.request.LogoutRequest;
import com.nhc.Identity_service.dto.request.RefreshRequest;
import com.nhc.Identity_service.dto.response.ApiResponse;
import com.nhc.Identity_service.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse authenticate(@RequestBody AuthenticationRequest request){
        var result =  authenticationService.authenticate(request);

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username : {}", authentication.getName());
        authentication.getAuthorities()
                .forEach(grantedAuthority -> log.info("Roles : {}", grantedAuthority.getAuthority()));
        return ApiResponse.builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.builder()
                .result(result)
                .build();
    }

    @PostMapping("/refresh")
    ApiResponse refresh(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        var result =  authenticationService.refreshToken(request);
        return ApiResponse.builder()
                .result(result)
                .build();
    }

    @PostMapping("/logout")
    ApiResponse logout(@RequestBody LogoutRequest request)
            throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.builder()
                .build();
    }
}
