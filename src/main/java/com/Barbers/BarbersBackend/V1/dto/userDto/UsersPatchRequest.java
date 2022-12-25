package com.Barbers.BarbersBackend.V1.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersPatchRequest {

    @NotNull
    private UUID id;

    private String userName;

    private String fullName;

    private String email;

    private String password;
}
