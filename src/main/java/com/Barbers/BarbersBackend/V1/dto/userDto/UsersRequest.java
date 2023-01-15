package com.Barbers.BarbersBackend.V1.dto.userDto;

import com.Barbers.BarbersBackend.V1.enunms.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersRequest {

    private String userName;

    private String cnpj;

    @NotBlank
    private String fullName;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private Roles type;
}
