package com.Barbers.BarbersBackend.V1.dto.userDto;

import com.Barbers.BarbersBackend.V1.enunms.Roles;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersResponse {

    private UUID id;

    private String userName;

    private String cnpj;

    private String fullName;

    private String email;

    private Roles type;

}
