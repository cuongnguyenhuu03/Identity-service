package com.nhc.Identity_service.dto.request;

import com.nhc.Identity_service.validator.DobConstraint;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String password;
    String firstName;
    String lastName;

    @DobConstraint(min = 18)
    LocalDate dob;
    List<String> roles;
}
