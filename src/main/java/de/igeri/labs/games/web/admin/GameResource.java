package de.igeri.labs.games.web.admin;

import com.codahale.metrics.annotation.Timed;
import de.igeri.labs.games.domain.Game;
import de.igeri.labs.games.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Game.
 */
@RestController
@RequestMapping("/app")
public class GameResource {

    private final Logger log = LoggerFactory.getLogger(GameResource.class);

    @Inject
    private GameRepository gameRepository;

    /**
     * POST  /rest/games -> Create a new game.
     */
    @RequestMapping(value = "/rest/games",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Game game) {
        log.debug("REST request to save Game : {}", game);
        gameRepository.save(game);
    }

    /**
     * GET  /rest/games -> get all the games.
     */
    @RequestMapping(value = "/rest/games",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Game> getAll() {
        log.debug("REST request to get all Games");
        return gameRepository.findAll();
    }

    /**
     * GET  /rest/games/:id -> get the "id" game.
     */
    @RequestMapping(value = "/rest/games/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Game> get(@PathVariable Long id) {
        log.debug("REST request to get Game : {}", id);
        return Optional.ofNullable(gameRepository.findOne(id))
            .map(game -> new ResponseEntity<>(
                game,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /rest/games/:id -> delete the "id" game.
     */
    @RequestMapping(value = "/rest/games/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Game : {}", id);
        gameRepository.delete(id);
    }
}
