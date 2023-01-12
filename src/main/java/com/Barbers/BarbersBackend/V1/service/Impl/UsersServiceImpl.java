package com.Barbers.BarbersBackend.V1.service.Impl;

import com.Barbers.BarbersBackend.V1.dto.userDto.*;
import com.Barbers.BarbersBackend.V1.mapper.UsersMapper;
import com.Barbers.BarbersBackend.V1.model.Users;
import com.Barbers.BarbersBackend.V1.repositorie.UsersRepository;
import com.Barbers.BarbersBackend.V1.service.interfaces.UsersService;
import com.Barbers.BarbersBackend.exceptions.gerenciament.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder encoder;

    private final UsersMapper usersMapper;

    @Override
    public UsersResponse create(UsersRequest usersRequest){
        Optional<Users> optEmail = usersRepository.findByEmail(usersRequest.getEmail());
        Optional<Users> optUserNAme = usersRepository.findByUserName(usersRequest.getUserName());

        if(optEmail.isPresent() && optUserNAme.isPresent()) {
            throw new BadRequestException("Email e nome de usuário usuário já cadastrados");
        }

        if(optEmail.isPresent()) {
            throw new BadRequestException("Email já cadastrado");
        }

        if(optUserNAme.isPresent()) {
            throw new BadRequestException("Nome de usuário já cadastrado");
        }

        Users users = new Users();
        BeanUtils.copyProperties(usersRequest,users);

        users.setPassword(encoder.encode(users.getPassword()));
        usersRepository.save(users);

        return UsersResponse.builder()
                .id(users.getId())
                .userName(users.getUserName())
                .fullName(users.getFullName())
                .email(users.getEmail())
                .build();
    }

    @Override
    public UsersPaginationResponse getAllUsers(Pageable pageable) {
        Page<Users> users = usersRepository.findAll(pageable);
        UsersPaginationResponse response = new UsersPaginationResponse();
        response.setPage(pageable.getPageNumber());
        response.setSize(pageable.getPageSize());
        response.setTotalPages(users.getTotalPages());
        response.setUsers(users.stream()
                .map(m -> {
                    UsersResponse usersResponse = new UsersResponse();
                    usersMapper.usersResponseMapper(m, usersResponse);
                    return usersResponse;
                }).collect(Collectors.toList()));
        return response;
    }

    @Override
    public void deleteUsers(UUID id) {
        Optional<Users> users = usersRepository.findById(id);

        if(!users.isPresent())
            throw new BadRequestException("Usuário informado não existe.");

        usersRepository.deleteById(id);
    }

    @Override
    public UsersResponse putUsers(UsersPutRequest usersPutRequest) {
        Optional<Users> optionalUsers = usersRepository.findById(usersPutRequest.getId());

        if(!optionalUsers.isPresent()){
            throw new BadRequestException("Id não corresponde a nenhum usuário");
        }else{
            Users users = new Users();

            BeanUtils.copyProperties(usersPutRequest , users);
            users.setPassword(encoder.encode(users.getPassword()));
            usersRepository.save(users);

            return UsersResponse.builder()
                    .id(users.getId())
                    .userName(users.getUserName())
                    .fullName(users.getFullName())
                    .email(users.getEmail())
                    .build();
        }
    }

    @Override
    public UsersResponse patchUsers(UsersPatchRequest usersPatchRequest) {
        Optional<Users> optionalUsers = usersRepository.findById(usersPatchRequest.getId());

        if(!optionalUsers.isPresent()){
            throw new BadRequestException("Id não corresponde a nenhum usuário");
        }else{
            Users users = Users.builder()
                    .id(usersPatchRequest.getId())
                    .userName(usersPatchRequest.getUserName() == null ? optionalUsers.get().getUserName() : usersPatchRequest.getUserName())
                    .fullName(usersPatchRequest.getFullName() == null ? optionalUsers.get().getFullName() : usersPatchRequest.getFullName())
                    .email(usersPatchRequest.getEmail() == null ? optionalUsers.get().getEmail() : usersPatchRequest.getEmail())
                    .password(usersPatchRequest.getPassword() == null ? optionalUsers.get().getPassword() : encoder.encode(usersPatchRequest.getPassword()))
                    .build();

            UsersResponse usersResponse = new UsersResponse();
            BeanUtils.copyProperties(users , usersResponse);
            usersRepository.save(users);

            return usersResponse;
        }
    }

    @Override
    public UsersResponse getUserId(UUID uuid) {
        Optional<Users> users = usersRepository.findById(uuid);

        return UsersResponse.builder()
                .id(users.get().getId())
                .fullName(users.get().getFullName())
                .userName(users.get().getUserName())
                .email(users.get().getEmail())
                .build();
    }

    @Override
    public Boolean emailExists(String email) {
        Optional<Users> users = usersRepository.findByEmail(email);

        boolean exists;

        return exists = users.isPresent();
    }

    @Override
    public Boolean userNameExists(String userName) {
        Optional<Users> users = usersRepository.findByUserName(userName);

        boolean exists;

        return exists = users.isPresent();
    }


}
