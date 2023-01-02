package com.Barbers.BarbersBackend.V1.dto.userDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersPaginationResponse {

    private int page;
    private int totalPages;
    private int size;
    private List<UsersResponse> users;
}
