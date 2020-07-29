package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.awt.*;

public class MapVisualTest {
//    private static final int STARTX = 30;
//    private static final int STARTY = 6;
//    private static final int MAXW = 8;
//    private static final int MAXH = 8;
//    private static final int MAX_OFFSET = 3;
//    private static final Room.Side ENTRY_SIDE = Room.Side.Bottom;
//    private static final TETile ENTRY_TILE = Tileset.LOCKED_DOOR;
//    private static final TETile EXIT_TILE = Tileset.FLOOR;


    public static void main(String[] args) {
        Game game = new Game();
        // 23483
        game.playWithInputString("N23485422Q");

//        TERenderer ter = new TERenderer();
//        MapGenerationParameters mgp = MapGenerationPatarmeters.defaultParameters();
//        ter.initialize(mgp.width, mgp.height);
//        WorldState ws = MapGenerator.generate(mgp);
//        ter.renderFrame(ws.terrainGrid());

    }
}
