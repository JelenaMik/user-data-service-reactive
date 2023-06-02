package com.example.userdataservicereactive.mapper;

import com.example.userdataservicereactive.domain.FavoriteProvider;
import com.example.userdataservicereactive.dto.FavoriteProviderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FavoriteProviderMapper {
    FavoriteProvider dtoToEntity(FavoriteProviderDto favoriteProviderDto);
    FavoriteProviderDto entityToDto(FavoriteProvider favoriteProvider);
}
