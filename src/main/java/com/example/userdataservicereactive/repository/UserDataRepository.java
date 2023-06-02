package com.example.userdataservicereactive.repository;

import com.example.userdataservicereactive.domain.UserData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserDataRepository extends ReactiveMongoRepository<UserData, String> {
    Mono<UserData> findByUserId(String userId);
    Flux<UserData> findFirst10ByFirstNameContaining(String firstName);

}
