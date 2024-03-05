package com.mandacarubroker.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mandacarubroker.custimnotation.AgeLimit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

/**
 * Request User.
 */
public record RequestUserDTO(
    @NotBlank(message = "Username cannot be blank")
    String username,
    @Pattern(regexp = "(?=\\w*\\d)(?=\\w*\\p{Lu})[\\p{L}\\d]{8,}",
        message = "Password must contain a uppercase letter, one digit and 8 or more characters")
    String password,
    @Pattern(regexp = "[a-z0-9.]+@[a-z0-9]+.[a-z]+.([a-z]+)?",
        message = "Email must be valid")
    String email,
    @NotBlank(message = "Price cannot be null")
    String firstName,
    @NotBlank(message = "Price cannot be null")
    String lastName,
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @AgeLimit(message = "User must be over 18")
    LocalDate birthDate
) {
}
