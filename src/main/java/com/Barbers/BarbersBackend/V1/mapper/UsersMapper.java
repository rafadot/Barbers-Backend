package com.Barbers.BarbersBackend.V1.mapper;

import com.Barbers.BarbersBackend.V1.dto.userDto.UsersResponse;
import com.Barbers.BarbersBackend.V1.model.Users;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UsersMapper {

    ModelMapper modelMapper;

    public void usersResponseMapper(Users users , UsersResponse usersResponse){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(users,usersResponse);
    }
}
