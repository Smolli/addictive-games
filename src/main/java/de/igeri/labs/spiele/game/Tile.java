package de.igeri.labs.spiele.game;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Tile implements Serializable {

    private int[] dimensions;

    public Tile(int dim) {
        this.dimensions = new int[dim];
    }

    public int[] getDimensions() {
        return dimensions;
    }

    public void setDimensions(int[] dimensions) {
        this.dimensions = dimensions;
    }

    void setValue(int dim, int value) {
        this.dimensions[dim] = value;
    }

    int getValue(int dim) {
        return this.dimensions[dim];
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this.dimensions);
    }

}
