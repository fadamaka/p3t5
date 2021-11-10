package com.fada.p3t5.client;

import com.fada.p3t5.dto.Greeting;
import com.fada.p3t5.dto.Player;
import com.fada.p3t5.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class MainClient {

    private final WebClient client;

    @Autowired
    private PlayerRepository playerRepository;

    // Spring Boot auto-configures a `WebClient.Builder` instance with nice defaults and customizations.
    // We can use it to create a dedicated `WebClient` for our component.
    public MainClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("http://localhost:8080").build();
    }

    public Mono<String> getGreeting() {
        return this.client.get().uri("/hello").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Greeting.class)
                .map(Greeting::getMessage);
    }

    public Mono<Player> createPlayer(Player player){
        log.info("vmi");

        return this.client.post().uri("/createPlayer").accept(MediaType.APPLICATION_JSON).body(Mono.just(player),Player.class).retrieve().bodyToMono(Player.class);
    }

    public Flux<Player> getAllPlayers(){
        log.info("vmi");
        return this.client.get().uri("/getAllPlayers").accept(MediaType.APPLICATION_JSON).retrieve().bodyToFlux(Player.class);
    }

}
