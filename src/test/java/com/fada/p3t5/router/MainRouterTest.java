package com.fada.p3t5.router;

import static org.assertj.core.api.Assertions.assertThat;

import com.fada.p3t5.domain.Greeting;
import com.fada.p3t5.domain.Player;
import com.fada.p3t5.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
//  We create a `@SpringBootTest`, starting an actual server on a `RANDOM_PORT`
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainRouterTest {

    // Spring Boot will create a `WebTestClient` for you,
    // already configure and ready to issue requests against "localhost:RANDOM_PORT"
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void testHello() {
        webTestClient
                // Create a GET request to test an endpoint
                .get().uri("/hello")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                // and use the dedicated DSL to test assertions against the response
                .expectStatus().isOk()
                .expectBody(Greeting.class).value(greeting -> {
            assertThat(greeting.getMessage()).isEqualTo("Hello, Spring!");
        });
    }


    @Test
    public void testCreatePlayer() {
        webTestClient
                // Create a GET request to test an endpoint
                .post().uri("/createPlayer").body(Mono.just(new Player(null, "dummy","dummy")),Player.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                // and use the dedicated DSL to test assertions against the response
                .expectStatus().isOk()
                .expectBody(Player.class).value(player -> {
            assertThat(player.getName()).isEqualTo("dummy");
        });
        assertThat(playerRepository.findAllByName("dummy").blockFirst().getName()).isEqualTo("dummy");
    }
}