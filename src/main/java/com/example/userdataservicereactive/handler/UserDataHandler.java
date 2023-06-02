package com.example.userdataservicereactive.handler;

import com.example.userdataservicereactive.dto.UserDataDto;
import com.example.userdataservicereactive.service.UserDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Component
@Slf4j
@RequiredArgsConstructor
public class UserDataHandler {
    private final UserDataService service;


    public Mono<ServerResponse> saveUserData(ServerRequest serverRequest) {
        Mono<UserDataDto> userDataDtoMono = serverRequest.bodyToMono(UserDataDto.class);
        return userDataDtoMono.flatMap(service::saveUserData)
                .flatMap(userDataDto -> ServerResponse.ok().bodyValue(userDataDto));
    }

    public Mono<ServerResponse> seeAllUserData(ServerRequest serverRequest) {
        return ServerResponse.ok().body(service.getAllUserData(), Flux.class).log();
    }

    public Mono<ServerResponse> seeUserData(ServerRequest serverRequest) {
        String userId = serverRequest.pathVariable("id");
        return ServerResponse.ok().body(service.getUserData(userId), UserDataDto.class).log();
    }

    public Mono<ServerResponse> updateUserData(ServerRequest serverRequest) {
        Mono<UserDataDto> userDataDtoMono = serverRequest.bodyToMono(UserDataDto.class);
        return userDataDtoMono.flatMap(service::updateUserData)
                .flatMap(userDataDto -> ServerResponse.ok().bodyValue(userDataDto));
    }

    public Mono<ServerResponse> searchFirstByName(ServerRequest serverRequest) {
        String firstName = serverRequest.queryParam("firstName").orElseThrow();
        return ServerResponse.ok().body(service.getUserDataByFirstName(firstName), Flux.class);
    }
}
