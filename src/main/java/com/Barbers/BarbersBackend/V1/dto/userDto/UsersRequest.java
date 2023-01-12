package com.Barbers.BarbersBackend.V1.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersRequest {

    @NotBlank
    private String userName;

    @NotBlank
    private String fullName;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
