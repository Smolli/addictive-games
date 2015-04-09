package de.igeri.labs.spiele.web;

import de.igeri.labs.spiele.game.Board;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@Scope // TODO remove @Scope
public class GameController {

    private Board board = Board.init();

    @RequestMapping("/board")
    public Board getBoard() {
        return board;
    }

}
