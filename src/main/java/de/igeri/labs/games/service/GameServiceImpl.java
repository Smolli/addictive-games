package de.igeri.labs.games.service;

import de.igeri.labs.games.domain.Board;
import de.igeri.labs.games.domain.Game;
import de.igeri.labs.games.domain.Tile;
import de.igeri.labs.games.domain.User;
import de.igeri.labs.games.repository.BoardRepository;
import de.igeri.labs.games.repository.GameRepository;
import de.igeri.labs.games.repository.TileRepository;
import de.igeri.labs.games.repository.UserRepository;
import de.igeri.labs.games.security.SecurityUtils;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private TileRepository tileRepository;

    @Autowired
    private BoardService boardService;

    @Override
    public Game createGame() {
        final User user = getUser();
        Game game = new Game();

        game.setStarted(LocalDate.now());
        game.setUser(user);

        game = this.gameRepository.save(game);

        Board board = new Board();

        board.setGame(game);
        board.setCardsRemaining(30);

        board = this.boardRepository.save(board);

        for (Tile tile : this.boardService.createTiles()) {
            tile.setBoard(board);
            this.tileRepository.save(tile);
        }

        return game;
    }

    private User getUser() {
        return this.userRepository.findByLogin(SecurityUtils.getCurrentLogin());
    }

    @Override
    public List<Game> listGames() {
        final User user = this.getUser();

        final List<Game> games = this.gameRepository.findByUserId(user.getId());

        return games;
    }

    @Override
    public Board getBoard(long gameId) {
        return this.boardRepository.findByGameId(gameId);
    }

    @Override
    public List<Tile> getTiles(long boardId) {
        return this.tileRepository.findByBoardId(boardId);
    }

}
