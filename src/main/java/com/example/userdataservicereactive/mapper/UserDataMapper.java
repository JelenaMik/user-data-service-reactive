package com.example.userdataservicereactive.mapper;

import com.example.userdataservicereactive.domain.UserData;
import com.example.userdataservicereactive.dto.UserDataDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDataMapper {
    UserData dtoToEntity(UserDataDto userDataDto);
    UserDataDto entityToDto(UserData userData);
}
