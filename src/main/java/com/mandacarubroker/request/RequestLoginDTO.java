package com.mandacarubroker.request;

import jakarta.validation.constraints.NotBlank;

public record RequestLoginDTO(
    @NotBlank
    String username,
    @NotBlank
    String password
) {
}
