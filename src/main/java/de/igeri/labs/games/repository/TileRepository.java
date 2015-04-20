package de.igeri.labs.games.repository;

import de.igeri.labs.games.domain.Tile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TileRepository extends JpaRepository<Tile,Long> {
    List<Tile> findByBoardId(long boardId);
}
