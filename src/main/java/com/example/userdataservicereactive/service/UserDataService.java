package com.example.userdataservicereactive.service;

import com.example.userdataservicereactive.dto.UserDataDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface UserDataService {
    Mono<UserDataDto> getUserData(String userId);

    Flux<UserDataDto> getAllUserData();

    Mono<UserDataDto> saveUserData(@Validated UserDataDto userDataDto);

    Mono<UserDataDto> updateUserData(UserDataDto userDataDto);

    Flux<UserDataDto> getUserDataByFirstName(String firstName);

}
