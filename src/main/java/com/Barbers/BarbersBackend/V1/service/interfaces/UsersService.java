package com.Barbers.BarbersBackend.V1.service.interfaces;

import com.Barbers.BarbersBackend.V1.dto.UsersPatchRequest;
import com.Barbers.BarbersBackend.V1.dto.UsersPutRequest;
import com.Barbers.BarbersBackend.V1.dto.UsersRequest;
import com.Barbers.BarbersBackend.V1.dto.UsersResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsersService {
    UsersResponse create(UsersRequest usersRequest);

    List<UsersResponse> getAllUsers(Pageable pageable);

    void deleteUsers(UUID id);

    UsersResponse putUsers(UsersPutRequest usersPutRequest);

    UsersResponse patchUsers(UsersPatchRequest usersPatchRequest);

    UsersResponse getUserEmail(String email);

    UsersResponse getUserId(UUID uuid);
}
