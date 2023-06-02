package com.example.userdataservicereactive.service.impl;

import com.example.userdataservicereactive.dto.UserDataDto;
import com.example.userdataservicereactive.exception.UserDataNotFoundException;
import com.example.userdataservicereactive.mapper.UserDataMapper;
import com.example.userdataservicereactive.repository.UserDataRepository;
import com.example.userdataservicereactive.service.UserDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDataServiceImpl implements UserDataService {
    private final UserDataRepository repository;
    private final UserDataMapper mapper;

    @Override
    public Mono<UserDataDto> getUserData(String userId){
        return repository.findByUserId(userId)
                .switchIfEmpty(Mono.error(UserDataNotFoundException::new))
            .map(mapper::entityToDto);
    }

    @Override
    public Flux<UserDataDto> getAllUserData(){
        return repository.findAll()
                .map(mapper::entityToDto);
    }

    @Override
    public Mono<UserDataDto> saveUserData(@Validated UserDataDto userDataDto){
        return Mono.just(userDataDto)
                .flatMap(userDataDto1 -> {
                    userDataDto1.setRegistrationDate(LocalDateTime.now());
                    return Mono.just(userDataDto1);
                })
                .map(mapper::dtoToEntity)
                .flatMap(repository::save)
                .map(mapper::entityToDto);
    }

    @Override
    public Mono<UserDataDto> updateUserData(UserDataDto userDataDto){
        return repository.findByUserId(userDataDto.getUserId())
                .switchIfEmpty(Mono.error(UserDataNotFoundException::new))
                .flatMap(userData -> {
                    userData.setFirstName(userDataDto.getFirstName());
                    userData.setLastName(userDataDto.getLastName());
                    return Mono.just(userData);
                })
                .flatMap(repository::save)
                .map(mapper::entityToDto);
    }

    @Override
    public  Flux<UserDataDto> getUserDataByFirstName(String firstName){
        return repository.findFirst10ByFirstNameContaining(firstName)
                .map(mapper::entityToDto);
    }




}
