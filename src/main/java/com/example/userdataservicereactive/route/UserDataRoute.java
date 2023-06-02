package com.example.userdataservicereactive.route;

import com.example.userdataservicereactive.handler.UserDataHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserDataRoute {
    @Bean
    public RouterFunction<ServerResponse> UserDataHandlerRoute(UserDataHandler handler){
        return route()
                .nest(path("api/v1/userdata"), builder ->
                        builder
                                .POST("", handler::saveUserData)
                                .GET("/all-user-data", handler::seeAllUserData)
                                .GET("/get-data/{id}", handler::seeUserData)
                                .PUT("", handler::updateUserData )
                                .GET("/search-users", handler::searchFirstByName)
                        ).build();
    }
}
