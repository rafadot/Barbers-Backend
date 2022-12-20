package com.Barbers.BarbersBackend.V1.service.Impl;

import com.Barbers.BarbersBackend.V1.dto.UsersRequest;
import com.Barbers.BarbersBackend.V1.model.Users;
import com.Barbers.BarbersBackend.V1.repositorie.UsersRepository;
import com.Barbers.BarbersBackend.V1.service.interfaces.UsersService;
import com.Barbers.BarbersBackend.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersServicesImpl implements UsersService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder encoder;

    @Override
    public Users create(UsersRequest usersRequest) {
        Optional<Users> optUsers = usersRepository.findByEmail(usersRequest.getEmail());

        if(optUsers.isPresent())
            throw new BadRequestException("Email já existe.");

        Users users = new Users();
        BeanUtils.copyProperties(usersRequest,users);

        users.setPassword(encoder.encode(users.getPassword()));
        return usersRepository.save(users);
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Users deleteUsers(UUID id) {
        Optional<Users> users = usersRepository.findById(id);

        if(!users.isPresent())
            throw new BadRequestException("Usuário informado não existe.");

        usersRepository.deleteById(id);
        return null;

    }
}
