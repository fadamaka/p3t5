package com.fada.p3t5.repository;

import com.fada.p3t5.domain.Player;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PlayerRepository extends ReactiveCrudRepository<Player,String> {

    Flux<Player> findAllByName(String name);
    Mono<Player> findFirstByApiKey(Mono<String> apiKey);
}
