package com.Barbers.BarbersBackend.V1.controller;

import com.Barbers.BarbersBackend.V1.dto.userDto.*;
import com.Barbers.BarbersBackend.V1.model.Users;
import com.Barbers.BarbersBackend.V1.service.interfaces.UsersService;
import com.Barbers.BarbersBackend.security.JWTAuthenticateFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping
    public ResponseEntity<UsersResponse> createUser(@RequestBody UsersRequest usersRequest){
        return new ResponseEntity<>(usersService.create(usersRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<UsersPaginationResponse> getAllUsers(Pageable pageable){
        return new ResponseEntity<>(usersService.getAllUsers(pageable) , HttpStatus.OK);
    }

    @GetMapping("/getUsersById")
    public ResponseEntity<UsersResponse> getUserId(@RequestParam UUID uuid){
        return new ResponseEntity<>(usersService.getUserId(uuid) , HttpStatus.OK);
    }

    @GetMapping("/emailExists")
    public ResponseEntity<Boolean> emailExist(@RequestParam String email){
        return new ResponseEntity<>(usersService.emailExists(email) , HttpStatus.OK);
    }

    @GetMapping("/userNameExists")
    public ResponseEntity<Boolean> userNameExists(@RequestParam String userName){
        return new ResponseEntity<>(usersService.userNameExists(userName) , HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UsersResponse> putUser(@RequestBody UsersPutRequest usersPutRequest){
        return new ResponseEntity<>(usersService.putUsers(usersPutRequest) , HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<UsersResponse> patchUser(@RequestBody UsersPatchRequest usersPatchRequest){
        return new ResponseEntity<>(usersService.patchUsers(usersPatchRequest) , HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public void deleteUser(@RequestParam UUID uuid){
        usersService.deleteUsers(uuid);
    }

}
