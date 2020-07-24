package byog.lab5;

import byog.TileEngine.*;

import java.awt.*;
import java.util.Random;

public class AddHexagon {

    private static final int WIDTH = 29;
    private static final int HEIGHT = 30;

    private static final long SEED = 1234;
    Random RANDOM = new Random(SEED);

    /** Picks a RANDOM tile with a 33% change of being
     *  a wall, 33% chance of being a flower, and 33%
     *  chance of being empty space.
     */
    private static TETile randomTile(Random r) {
        int tileNum = r.nextInt(4);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.MOUNTAIN;
            case 3: return Tileset.GRASS;
            default: return Tileset.NOTHING;

        }
    }

    /** Adds a hexagon of side length s. */
    public static void addHexagon(TETile[][] tile, int size,
                                  int startX, int startY) {
        Random r = new Random();
        TETile tel = randomTile(r);
        int height = tile[0].length;
        int width = tile.length;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                boolean toDraw = determineHexagon(size, x, y, startX, startY);
                if (toDraw) {
                    tile[x][y] = tel;
                } else if (tile[x][y] == Tileset.NOTHING
                        || tile[x][y] == null) {
                    tile[x][y] = Tileset.NOTHING;
                }

            }
        }

    }

    /** Determine whether this cell should be hexagon or nothing. */
    private static boolean determineHexagon(int size, int x, int y,
                                          int startX, int startY) {
        if (y >= startY && y <= startY + size - 1) {
            int diffY = y - startY;
            if (x >= startX - diffY && x <= startX + size - 1 + diffY) {
                return true;
            }
        } else if (y >= startY + size && y <= startY + 2 * size - 1) {
                int diffY = y - startY - 2 * size + 1;
                if (x >= startX + diffY && x <= startX + size - 1 - diffY) {
                    return true;
                }
            }
        return false;

    }

    /** Draws 19 hexagons, size = 3. */
    public static void draw19Hexagons(TETile[][] H) {
        for (int i = 0; i < HEIGHT; i += 6) {
            addHexagon(H, 3, 12, i);
        }

        for (int i = 3; i + 6 < HEIGHT; i += 6 ) {
            addHexagon(H, 3, 7, i);
            addHexagon(H, 3, 17, i);
        }

        for (int i = 6; i + 7 < HEIGHT; i += 6 ) {
            addHexagon(H, 3, 2, i);
            addHexagon(H, 3, 22, i);
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] hexagon = new TETile[WIDTH][HEIGHT];
        draw19Hexagons(hexagon);


        ter.renderFrame(hexagon);



    }
}
