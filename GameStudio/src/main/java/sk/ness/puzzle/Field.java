package sk.ness.puzzle;

import java.io.Serializable;
import java.util.Random;

public class Field implements Serializable{
    private Tile[][] field;
    private int maxRiadkov;
    private int maxStlpcov;

    public Field(int maxRiadkov,int maxStlpcov) {
        this.maxRiadkov = maxRiadkov;
        this.maxStlpcov = maxStlpcov;
        field = new Tile[maxRiadkov][maxStlpcov];
        generate();
        shuffle();
    }



    private void shuffle() {
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            int nahodneCislo = r.nextInt(4);
            moveTile(MoveDirection.values()[nahodneCislo]);
        }
    }


    public boolean isSolved() {
        boolean b = true;
        for (int r = 0; r < maxRiadkov; r++) {
            for (int c = 0; c < maxStlpcov; c++) {
                if (r == maxRiadkov -1 && c == maxStlpcov -1) {
                    if (field[r][c] instanceof NumberedTile) {
                        b = false;
                    }
                } else {
                    if (field[r][c] instanceof NumberedTile) {
                        if (((NumberedTile) field[r][c]).getNumber() != r * maxRiadkov + c + 1) {
                            b = false;
                        }
                    } else {
                        b = false;
                    }
                }
            }
        }
        return b;
    }


    public void moveTile(MoveDirection m) {
        Position p = findEmptyTile();
        switch (m) {
            case LEFT:
                if (p.stlpec < maxStlpcov-1)
                    vymen(new Position(p.riadok, p.stlpec), new Position(p.riadok, p.stlpec + 1));
                break;
            case RIGHT:
                if (p.stlpec > 0)
                    vymen(new Position(p.riadok, p.stlpec), new Position(p.riadok, p.stlpec - 1));
                break;
            case UP:
                if (p.riadok < maxRiadkov-1)
                    vymen(new Position(p.riadok, p.stlpec), new Position(p.riadok + 1, p.stlpec));
                break;
            case DOWN:
                if (p.riadok > 0)
                    vymen(new Position(p.riadok, p.stlpec), new Position(p.riadok - 1, p.stlpec));
                break;
        }
    }

    private void vymen(Position p1, Position p2) {
        Tile t = field[p1.riadok][p1.stlpec];
        field[p1.riadok][p1.stlpec] = field[p2.riadok][p2.stlpec];
        field[p2.riadok][p2.stlpec] = t;
    }

    private void generate() {
        for (int r = 0; r < maxRiadkov; r++) {
            for (int c = 0; c < maxStlpcov; c++) {
                if ((r != maxRiadkov-1 || c != maxStlpcov-1)) {
                    field[r][c] = new NumberedTile(r * maxRiadkov + c + 1);
                }
            }
        }
        field[maxRiadkov-1][maxStlpcov-1] = new EmptyTile();
    }

    private Position findEmptyTile() {
        for (int r = 0; r < maxRiadkov; r++)
            for (int c = 0; c < maxStlpcov; c++)
                if (field[r][c] instanceof EmptyTile) {
                    Position p = new Position(r, c);
                    return p;
                }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < maxRiadkov; r++) {
            for (int c = 0; c < maxStlpcov; c++) {
                if (field[r][c] != null)
                    sb.append(field[r][c].toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }


}
