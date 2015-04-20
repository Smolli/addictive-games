package de.igeri.labs.games.repository;

import de.igeri.labs.games.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByUserId(long user_id);

}
