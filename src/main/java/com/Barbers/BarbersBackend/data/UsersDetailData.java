package com.Barbers.BarbersBackend.data;

import com.Barbers.BarbersBackend.V1.model.Users;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class UsersDetailData implements UserDetails {

    private final Optional<Users> users;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return users.orElse(new Users()).getPassword();
    }

    public UUID getId(){
        return users.get().getId();
    }

    @Override
    public String getUsername() {
        return users.orElse(new Users()).getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
