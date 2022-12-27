package com.Barbers.BarbersBackend.V1.service.interfaces;

import com.Barbers.BarbersBackend.V1.dto.userDto.UsersPatchRequest;
import com.Barbers.BarbersBackend.V1.dto.userDto.UsersPutRequest;
import com.Barbers.BarbersBackend.V1.dto.userDto.UsersRequest;
import com.Barbers.BarbersBackend.V1.dto.userDto.UsersResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface UsersService {
    UsersResponse create(UsersRequest usersRequest);

    List<UsersResponse> getAllUsers(Pageable pageable);

    void deleteUsers(UUID id);

    UsersResponse putUsers(UsersPutRequest usersPutRequest);

    UsersResponse patchUsers(UsersPatchRequest usersPatchRequest);

    UsersResponse getUserId(UUID uuid);

    Boolean emailExists(String email);

    Boolean userNameExists(String email);
}
