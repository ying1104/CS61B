package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private final int BLANK = 0;
    private int[][] tiles;
    private int[][] tilesCopy;
    private int tileSize;

    /** Constructs a board from an N-by-N array of tiles where.
     * tiles[i][j] = tile at row i, column j。
     */
    public Board(int[][] tiles) {
        this.tileSize = tiles.length;
        this.tiles = new int[tileSize][tileSize];
        this.tilesCopy = new int[tileSize][tileSize];
        for (int i = 0; i < tileSize; i++) {
            for (int j = 0; j < tileSize; j++) {
                this.tiles[i][j] = tiles[i][j];
                this.tilesCopy[i][j] = tiles[i][j];
            }
        }

    }

    /** Returns value of tile at row i, column j (or 0 if blank). */
    public int tileAt(int i, int j) {
        if (i < 0 || i >= tileSize
            || j < 0 || j >= tileSize) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }


    /** Returns the board size N. */
    public int size() {
        return tileSize;
    }

    /** Returns the neighbors of the current board. */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    /** Hamming estimate described below. */
    public int hamming() {
        int wrongNum = 0;
        for (int row = 0; row < tileSize; row++) {
            for (int col = 0; col < tileSize; col++) {
                int rightNum = row * tileSize + col + 1;
                if (tilesCopy[row][col] != BLANK) {
                    if (tilesCopy[row][col] != rightNum) {
                        wrongNum++;
                    }
                }
            }
        }
        return wrongNum;
    }

    /** Manhattan estimate described below. */
    public int manhattan() {
        int wrongNum = 0;
        for (int row = 0; row < tileSize; row++) {
            for (int col = 0; col < tileSize; col++) {
                if (tilesCopy[row][col] != BLANK) {
                    int rightNum = tilesCopy[row][col];
                    int rightCol = (rightNum - 1) % tileSize;
                    int rightRow = (rightNum - 1 - rightCol) / tileSize;
                    wrongNum = wrongNum + Math.abs(rightCol - col)
                            + Math.abs(rightRow - row);
                }
            }
        }
        return wrongNum;
    }

    /** Estimated distance to goal. This method should
     *  simply return the results of manhattan() when submitted to
     *  Gradescope.
     */
    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    /** Returns true if this board's tile values are the same
     * position as y's.
     */
    @Override
    public boolean equals(Object y) {
        Board boardY = (Board) y;
        if (this.tileSize != boardY.tileSize) {
            return false;
        }
        for (int row = 0; row < tileSize; row++) {
            for (int col = 0; col < tileSize; col++) {
                if (this.tiles[row][col] != boardY.tiles[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }


    /** Returns the string representation of the board. 
     * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
