package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.awt.*;

/**
 *  Draws a world that is mostly empty except for a small region.
 */
public class BoringWorldDemo {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        Rectangle a = new Rectangle(10, 10, 10, 10);
        // fills in a block 14 tiles wide by 4 tiles tall
        for (int x = a.x; x < a.x + a.width; x++) {
            for (int y = a.y; y < a.y + a.height; y += 1) {
                world[x][y] = Tileset.GRASS;
            }
        }

        // draws the world to the screen
        ter.renderFrame(world);
    }


}
