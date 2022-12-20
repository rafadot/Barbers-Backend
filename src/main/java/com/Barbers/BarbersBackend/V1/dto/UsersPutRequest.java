package com.Barbers.BarbersBackend.V1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UsersPutRequest {

    @NotNull
    private UUID id;

    @NotBlank
    private String userName;

    @NotBlank
    private String fullName;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
