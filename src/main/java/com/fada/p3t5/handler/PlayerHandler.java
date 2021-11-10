package com.fada.p3t5.handler;

import com.fada.p3t5.dto.Player;
import com.fada.p3t5.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class PlayerHandler {

    @Autowired
    PlayerRepository playerRepository;

    public Mono<ServerResponse> createPlayer(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(request.bodyToMono(Player.class).flatMap(playerRepository::save), Player.class));
    }

    public Mono<ServerResponse> getAllPlayers(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(playerRepository.findAll(), Player.class));
    }
}
