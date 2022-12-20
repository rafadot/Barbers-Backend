package com.Barbers.BarbersBackend.V1.service.interfaces;

import com.Barbers.BarbersBackend.V1.dto.UsersRequest;
import com.Barbers.BarbersBackend.V1.model.Users;

import java.util.List;
import java.util.UUID;

public interface UsersService {
    Users create(UsersRequest usersRequest);

    List<Users> getAllUsers();

    Users deleteUsers(UUID id);
}
