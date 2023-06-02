package com.example.userdataservicereactive.route;

import com.example.userdataservicereactive.handler.FavoriteProviderHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
@Configuration
public class FavoriteProviderRoute {
    @Bean
    public RouterFunction<ServerResponse> FavoriteProvidersRoute(FavoriteProviderHandler handler){
        return route()
                .nest(path("api/v1/favorite"), builder ->
                        builder
                                .POST("", handler::addFavoriteProvider)
                                .DELETE("", handler::removeFavoriteProvider)
                                .GET("/{id}", handler::getListOfFavoriteProviders)
                ).build();
    }
}
