package sk.ness.puzzle;

import java.io.Serializable;

public class EmptyTile extends Tile implements Serializable {

    @Override
    public String toString() {
        return "    ";
    }
}
