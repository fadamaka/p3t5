package com.fada.p3t5.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.fada.p3t5.domain.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
//  We create a `@SpringBootTest`, starting an actual server on a `RANDOM_PORT`
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlayerRepositoryTest {
    
    @Autowired
    PlayerRepository repository;
    
    @Test
    public void givenValue_whenfindAllByName_thenFindPlayer() {
        repository.save(new Player(null, "Billy", "Sdada")).block();
        Flux<Player> PlayerFlux = repository.findAllByName("Billy");

        StepVerifier
                .create(PlayerFlux)
                .assertNext(Player -> {
                    assertEquals("Billy", Player.getName());
                    assertEquals("Sdada", Player.getApiKey());
                    assertNotNull(Player.getId());
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void givenOwner_whenFindFirstByOwner_thenFindPlayer() {
        repository.save(new Player(null, "Bill", "Sdada")).block();
        Mono<Player> PlayerMono = repository
                .findFirstByApiKey(Mono.just("Sdada"));

        StepVerifier
                .create(PlayerMono)
                .assertNext(Player -> {
                    assertEquals("Bill", Player.getName());
                    assertEquals("Sdada", Player.getApiKey());
                    assertNotNull(Player.getId());
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void givenPlayer_whenSave_thenSavePlayer() {
        Mono<Player> PlayerMono = repository.save(new Player(null, "Bill", "Sdada"));

        StepVerifier
                .create(PlayerMono)
                .assertNext(Player -> assertNotNull(Player.getId()))
                .expectComplete()
                .verify();
    }
}