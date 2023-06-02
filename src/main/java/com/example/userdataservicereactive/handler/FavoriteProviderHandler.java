package com.example.userdataservicereactive.handler;

import com.example.userdataservicereactive.dto.FavoriteProviderDto;
import com.example.userdataservicereactive.exception.ClientIdCannotBeNullException;
import com.example.userdataservicereactive.exception.ProviderIdCannotBeNullException;
import com.example.userdataservicereactive.service.FavoriteProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class FavoriteProviderHandler {
    private final FavoriteProviderService service;


    public Mono<ServerResponse> addFavoriteProvider(ServerRequest request) {
        String clientId = request.queryParam("clientId").orElseThrow(ClientIdCannotBeNullException::new);
        String providerId = request.queryParam("providerId").orElseThrow(ProviderIdCannotBeNullException::new);
        return ServerResponse.ok().body(service.addFavoriteProvider(clientId,providerId), FavoriteProviderDto.class).log();
    }

    public Mono<ServerResponse> removeFavoriteProvider(ServerRequest request) {
        String clientId = request.queryParam("clientId").orElseThrow(ClientIdCannotBeNullException::new);
        String providerId = request.queryParam("providerId").orElseThrow(ProviderIdCannotBeNullException::new);
        return service.removeFavoriteProvider(clientId, providerId)
                .then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> getListOfFavoriteProviders(ServerRequest request) {
        String clientId = request.pathVariable("id");

        return service.getClientsFavoriteProvidersList(clientId)
                .collectList()
                .map(favoriteProviderDtos -> favoriteProviderDtos.stream().map(FavoriteProviderDto::getProviderId).toList())
                .flatMap(list -> ServerResponse.ok().bodyValue(list));
    }
}
