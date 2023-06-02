package com.example.userdataservicereactive.service;

import com.example.userdataservicereactive.domain.FavoriteProvider;
import com.example.userdataservicereactive.dto.FavoriteProviderDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public interface FavoriteProviderService {
    Mono<Object> addFavoriteProvider(String clientId, String providerId);

    Mono<Void> removeFavoriteProvider(String clientId, String providerId);

    Flux<FavoriteProviderDto> getClientsFavoriteProvidersList(String clientId);
}
