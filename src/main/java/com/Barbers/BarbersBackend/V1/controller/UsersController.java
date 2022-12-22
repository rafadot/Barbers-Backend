package com.Barbers.BarbersBackend.V1.controller;

import com.Barbers.BarbersBackend.V1.dto.UsersPatchRequest;
import com.Barbers.BarbersBackend.V1.dto.UsersPutRequest;
import com.Barbers.BarbersBackend.V1.dto.UsersRequest;
import com.Barbers.BarbersBackend.V1.dto.UsersResponse;
import com.Barbers.BarbersBackend.V1.service.interfaces.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping("/register")
    public ResponseEntity<UsersResponse> createUser(@RequestBody UsersRequest usersRequest){
        return new ResponseEntity<>(usersService.create(usersRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UsersResponse>> getAllUsers(Pageable pageable){
        return new ResponseEntity<>(usersService.getAllUsers(pageable) , HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UsersResponse> putUser(@RequestBody UsersPutRequest usersPutRequest){
        return new ResponseEntity<>(usersService.putUsers(usersPutRequest) , HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<UsersResponse> patchUser(@RequestBody UsersPatchRequest usersPatchRequest){
        return new ResponseEntity<>(usersService.patchUsers(usersPatchRequest) , HttpStatus.CREATED);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam UUID uuid){
        usersService.deleteUsers(uuid);
    }

}
