package com.example.userdataservicereactive.service.impl;

import com.example.userdataservicereactive.domain.FavoriteProvider;
import com.example.userdataservicereactive.dto.FavoriteProviderDto;
import com.example.userdataservicereactive.exception.FavoriteProviderAlreadyExistsException;
import com.example.userdataservicereactive.exception.FavoriteProviderNotFoundException;
import com.example.userdataservicereactive.mapper.FavoriteProviderMapper;
import com.example.userdataservicereactive.repository.FavoriteProviderRepository;
import com.example.userdataservicereactive.service.FavoriteProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoriteProviderServiceImpl implements FavoriteProviderService {

    private final FavoriteProviderRepository repository;
    private final FavoriteProviderMapper mapper;

    @Override
    public Mono<Object> addFavoriteProvider(String clientId, String providerId){
        return repository.findByClientIdAndProviderId(clientId, providerId)
                .flatMap(existingProvider ->Mono.error(FavoriteProviderAlreadyExistsException::new))
                .switchIfEmpty(
                        repository.save(
                                FavoriteProvider.builder()
                                        .clientId(clientId)
                                        .providerId(providerId)
                                        .build()
                        )
                                .map(mapper::entityToDto));
    }

    @Override
    public Mono<Void> removeFavoriteProvider(String clientId, String providerId){
       return  repository.findByClientIdAndProviderId(clientId, providerId)
                       .flatMap(favoriteProvider -> {
                           return repository.deleteById(favoriteProvider.getId());
                       })
               .switchIfEmpty(Mono.error(FavoriteProviderNotFoundException::new));

    }

    @Override
    public Flux<FavoriteProviderDto> getClientsFavoriteProvidersList(String clientId){
        return repository.findAllByClientId(clientId)
                .map(mapper::entityToDto);
    }
}
