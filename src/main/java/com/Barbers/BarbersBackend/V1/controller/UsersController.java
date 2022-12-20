package com.Barbers.BarbersBackend.V1.controller;

import com.Barbers.BarbersBackend.V1.dto.UsersRequest;
import com.Barbers.BarbersBackend.V1.model.Users;
import com.Barbers.BarbersBackend.V1.service.interfaces.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*" , maxAge = 3600)
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping
    public ResponseEntity<Users> createUser(UsersRequest usersRequest){
        return new ResponseEntity<>(usersService.create(usersRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers(){
        return new ResponseEntity<>(usersService.getAllUsers() , HttpStatus.OK);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam UUID uuid){
        usersService.deleteUsers(uuid);
    }

    //
}
