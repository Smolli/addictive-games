package de.igeri.labs.games.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_TILE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tile {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(length = 10)
    private String values;
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;


    public int getValue(int index) {
        return Integer.valueOf(values.charAt(index));
    }

    public void setValue(int index, int value) {
        final StringBuilder builder;
        if (this.values == null) {
            builder = new StringBuilder("");
        } else {
            builder = new StringBuilder(this.values);
        }

        this.values = builder.insert(index, String.valueOf(value)).toString();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValues() {
        return this.values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
