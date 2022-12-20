package com.Barbers.BarbersBackend.V1.dto;

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

    private String fullName;

    private String email;
}
