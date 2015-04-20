package de.igeri.labs.games.web.dto;

import de.igeri.labs.games.domain.Game;

import java.io.Serializable;

public class GameDTO implements Serializable {

    private long id;
    private long started;
    private long userId;

    public GameDTO(final Game game) {
        super();

        this.id = game.getId();
        this.started = game.getStarted().toDate().getTime();
        this.userId = game.getUser().getId();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStarted() {
        return this.started;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
