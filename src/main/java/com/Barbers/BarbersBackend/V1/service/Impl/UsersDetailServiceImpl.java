package com.Barbers.BarbersBackend.V1.service.Impl;

import com.Barbers.BarbersBackend.V1.model.Users;
import com.Barbers.BarbersBackend.V1.repositories.UsersRepository;
import com.Barbers.BarbersBackend.data.UsersDetailData;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UsersDetailServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> users = usersRepository.findByEmail(username);

        if(!users.isPresent()){
            throw new UsernameNotFoundException("Email " + username + "não encontrado");
        }

        return new UsersDetailData(users);
    }
}
