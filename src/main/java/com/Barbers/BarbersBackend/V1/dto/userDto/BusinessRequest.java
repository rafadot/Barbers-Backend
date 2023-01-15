package com.Barbers.BarbersBackend.V1.dto.userDto;

import com.Barbers.BarbersBackend.V1.enunms.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessRequest {

    private String cnpj;

    private String fullName;

    private String email;

    private Roles type;
}
