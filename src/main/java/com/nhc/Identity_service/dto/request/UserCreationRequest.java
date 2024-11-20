package com.nhc.Identity_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults( level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @NotBlank(message = "USERNAME_INVALID")
    String username;
    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;
    @NotBlank(message = "first name can not be blank")
    String firstName;
    @NotBlank(message = "last name can not be blank")
    String lastName;
    LocalDate dob;
}
