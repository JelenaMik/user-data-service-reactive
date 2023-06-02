package com.example.userdataservicereactive.repository;

import com.example.userdataservicereactive.domain.FavoriteProvider;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FavoriteProviderRepository extends ReactiveMongoRepository<FavoriteProvider, String> {
    Mono<FavoriteProvider> findByClientIdAndProviderId(String clientId, String providerId);
    Flux<FavoriteProvider> findAllByClientId(String clientId);
}
