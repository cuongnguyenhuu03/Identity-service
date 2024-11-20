package com.nhc.Identity_service.controller;


import com.nhc.Identity_service.dto.request.AuthenticationRequest;
import com.nhc.Identity_service.dto.request.IntrospectRequest;
import com.nhc.Identity_service.dto.response.ApiResponse;
import com.nhc.Identity_service.dto.response.AuthenticationResponse;
import com.nhc.Identity_service.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse authenticate(@RequestBody AuthenticationRequest request){
        var result =  authenticationService.authenticate(request);
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
}
