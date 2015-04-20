package de.igeri.labs.games.web;

import de.igeri.labs.games.service.GameService;
import de.igeri.labs.games.web.dto.BoardDTO;
import de.igeri.labs.games.web.dto.GameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/game/triples",
//    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
public class GameController {

    @Autowired
    private GameService gameService;

//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<GameDTO> getBoard() {
//        return Optional.ofNullable(this.gameService.getCurrentGame())
//            .map(game -> new ResponseEntity<>(
//                new GameDTO(),
//                HttpStatus.OK
//            )).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
//    }

//    @RequestMapping(method = RequestMethod.POST)
//    public boolean check(@RequestBody final String[] ids) {
////        return this.board.checkIds(ids);
//        return true;
//    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<GameDTO> createNewGame() {
        return Optional.ofNullable(this.gameService.createGame())
            .map(game -> new ResponseEntity<>(
                new GameDTO(game),
                HttpStatus.OK
            )).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<GameDTO>> listGames() {
        return Optional.ofNullable(this.gameService.listGames())
            .map(games -> new ResponseEntity<>(
                games.stream()
                    .map(GameDTO::new)
                    .collect(Collectors.toList()),
                HttpStatus.OK
            )).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BoardDTO> getBoard(@PathVariable Long id) {
        return Optional.ofNullable(this.gameService.getBoard(id))
            .map(board -> new ResponseEntity<>(
                new BoardDTO(board, this.gameService.getTiles(board.getId())),
                HttpStatus.OK
            )).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

}
