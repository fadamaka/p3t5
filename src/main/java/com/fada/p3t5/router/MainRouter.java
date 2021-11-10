package com.fada.p3t5.router;

import com.fada.p3t5.handler.GreetingHandler;
import com.fada.p3t5.handler.PlayerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class MainRouter {

    @Bean
    public RouterFunction<ServerResponse> routeGreeting(GreetingHandler greetingHandler) {

        return RouterFunctions
                .route(GET("/hello").and(accept(MediaType.APPLICATION_JSON)), greetingHandler::hello);
    }


    @Bean
    public RouterFunction<ServerResponse> routePlayer(PlayerHandler playerHandler) {

        return RouterFunctions
                .route(POST("/createPlayer").and(accept(MediaType.APPLICATION_JSON)), playerHandler::createPlayer);
    }


    @Bean
    public RouterFunction<ServerResponse> getAllPlayers(PlayerHandler playerHandler) {

        return RouterFunctions
                .route(GET("/getAllPlayers").and(accept(MediaType.APPLICATION_JSON)), playerHandler::getAllPlayers);
    }
}
