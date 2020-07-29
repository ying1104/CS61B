package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
    private static final int MAX_LEAF_SIZE = 20;
    public Random RANDOM;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        int inputLength = input.length();
        input = input.substring(1, inputLength - 1);
        System.out.println(input);
        RANDOM = new Random(Long.parseLong(input));

        /** Initialize TERenderer */
        ter.initialize(WIDTH, HEIGHT);
        fillWithNothing();

        /** Actually create my leafs and add root leaf to it. */
        Vector<Leaf> leafs = new Vector<>();
        Leaf root = new Leaf(0, 0, WIDTH, HEIGHT);
        leafs.add(root);
        leafsVector(leafs);
//        for (Leaf f : leafs) {
//            System.out.println(f.x + " " + f.y + " " + f.width + " "+ f.height );
//        }


        /**Iterate through each leaf and create a room in each of them. */
//        System.out.println(root.x +"and" + root.leftChild.x + "and"+ root.rightChild.x);
        root.createRooms();


        /** draws the world to the screen. */
        ter.renderFrame(finalWorldFrame);
        return finalWorldFrame;
    }

    /** Initialize all the cells in TETile[][] with Nothing. */
    private void fillWithNothing() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                finalWorldFrame[i][j] = Tileset.NOTHING;
            }
        }
    }

    /** Fill Room with Wall AND Floor. */
    private void fillRoom(Rectangle room) {
        if (room == null) {
            return;
        }
//        System.out.println("A room has been filled." + room.height + " " +room.y + " " + room.width + " " +room.x);
        for (int i = room.x; i < room.x + room.width ; i++) {
            for (int j = room.y; j < room.y + room.height ; j++) {
                if (j == room.y || j == room.y + room.height - 1
                || i == room.x || i == room.x + room.width - 1) {
                    finalWorldFrame[i][j] = Tileset.WALL;
                } else {
                    finalWorldFrame[i][j] = Tileset.FLOOR;
                }
            }
        }
    }

    /** Fill hallways. */
    private void fillHall(Rectangle hall) {
        if (hall == null) {
            return;
        }

//        System.out.println("A room has been filled." + room.height + " " +room.y + " " + room.width + " " +room.x);
        for (int i = hall.x; i < hall.x + hall.width ; i++) {
            for (int j = hall.y; j < hall.y + hall.height ; j++) {
                if (finalWorldFrame[i][j] == Tileset.FLOOR || finalWorldFrame[i][j] == Tileset.WALL) {
                        continue;
                    }

                if (j == hall.y || j == hall.y + hall.height - 1
                        || i == hall.x || i == hall.x + hall.width - 1) {
//                    if (finalWorldFrame[i][j] == Tileset.FLOOR) {
//                        continue;
//                    }
                    finalWorldFrame[i][j] = Tileset.GRASS;
                } else {
                    finalWorldFrame[i][j] = Tileset.FLOWER;
                }
            }
        }
    }

    /** Create a vector to store all the leafs that have been split.
     * First, create a leaf to be the 'root' of all leafs.
     * Then loop over until the splitting finishes, which returns
     * a vector(a typed array) full of all your leafs.
     */
    public void leafsVector(Vector<Leaf> leafs) {


        boolean did_split = true;
        /** We loop through every leaf in our Vector over and over again,
         * until no more Leafs can be split.
         */
        while (did_split) {
            did_split = false;
            for (int i = 0; i < leafs.size(); i++) {
                Leaf l = leafs.get(i);
                if (l.leftChild == null && l.rightChild == null) {
                    if (l.width > MAX_LEAF_SIZE || l.height > MAX_LEAF_SIZE || RANDOM.nextDouble() > 0.25) {
                        if (l.split()) {
                            leafs.add(l.leftChild);
                            leafs.add(l.rightChild);
                            did_split = true;
                        }
                    }
                }
            }
        }

    }

    /** The first thing we need is to create our Leaf class. Basically, our Leaf is going to be a
     * Rectangle, with some extra functionality. Each Leaf will either contain a pair of children Leafs,
     * or a pair of Rooms, as well as a hallway or two.
     */
    public class Leaf {
        private final int MIN_LEAF_SIZE = 6;
        /** Position and size of this leaf. */
        public int x;
        public int y;
        public int width;
        public int height;
        /** the Leaf's left child leaf. */
        public Leaf leftChild;
        /** the Leaf's right child leaf. */
        public Leaf rightChild;
        /** the room that is inside this leaf. */
        public Rectangle room;
        /** Hallways to connect this leaf to the other leafs. */
        public Vector halls;


        public Leaf(int x, int y, int width, int height) {
            /** Initialize our leaf. */
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;

        }

        public boolean split() {
            /** Begin splitting the leaf into two children. */
            if (leftChild != null || rightChild != null) {
                return false;
            }
            /** determine direction of split.
             * if the width is >15% larger than height, we split vertically
             * if the height is >15% larger than the width, we split horizontally
             * otherwise we split randomly
             */
            boolean splitH = RANDOM.nextBoolean();

            if (width > height &&
                    Double.valueOf(width) / Double.valueOf(height) > 1.15) {
                splitH = false;
            } else if (width < height &&
                    Double.valueOf(height) / Double.valueOf(width) > 1.15) {
                splitH = true;
            }

            /** Next to determine the maximum height or width. */
            int max;
            if (splitH) {
                max = height - MIN_LEAF_SIZE;
            } else {
                max = width - MIN_LEAF_SIZE;
            }

            if (max <= MIN_LEAF_SIZE) {
                /** The area is too small to split anymore. */
                return false;
            }

            /** Determine where we are going to split. */
            int split = RandomUtils.uniform(RANDOM, MIN_LEAF_SIZE, max);

            /** Create our left and right children based on the direction of the split. */
            if (splitH) {
                leftChild = new Leaf(x, y, width, split);
                rightChild = new Leaf(x, y + split, width, height - split);
            } else {
                leftChild = new Leaf(x, y, split, height);
                rightChild = new Leaf(x + split, y, width - split, height);
            }
//            System.out.println(leftChild.x +" " + leftChild.width + " " + leftChild.y + " " + rightChild.height);
            /** Return true to indicate split successful! */
            return true;

        }

        /** Now that your leafs are defined, we need to make the rooms. We want a sort
         * of 'trickle-down' effect where we go from our biggest, 'root' leaf all the way
         * to our smallest leafs with no children. and then make a room in each one of those.
         */
        public void createRooms() {
            /** this function generates all the rooms and hallways for each leaf. */
            if (leftChild != null || rightChild != null) {
                /** this leaf has been split, so go into the child leaf. */
                if (leftChild != null) {
                    leftChild.createRooms();
                }
                if (rightChild != null) {
                    rightChild.createRooms();
                }
                if (leftChild != null && rightChild != null) {
                    createHall(leftChild.getRoom(), rightChild.getRoom());
                }
            } else {
                /** This leaf is ready to make a room. */
                Point roomSize;
                Point roomPos;

                /** the room can be between 3 x 3 tiles to the size of the leaf. */
                roomSize = new Point(RandomUtils.uniform(RANDOM, 3, width),
                        RandomUtils.uniform(RANDOM, 3, height));

                /** place the room within the leaf, but don't put against the side
                 * of the Leaf. that would merge.
                 */
                roomPos = new Point(RandomUtils.uniform(RANDOM, 1, width - roomSize.x + 1),
                        RandomUtils.uniform(RANDOM, 1, height - roomSize.y + 1));
                room = new Rectangle(x + roomPos.x, y + roomPos.y,
                        roomSize.x, roomSize.y);
//                System.out.println(RANDOM.nextBoolean() + "" + room.height + " " + room.y + " " + room.width + " " + room.x);
                fillRoom(room);
            }


        }

        /** Now, all we need to do is connect each room. All we have to do is to make
         * sure that each Leaf that has child Leafs has a hallway that connects it's children.
         */
        public Rectangle getRoom() {
            /** Iterate all the way through these leafs to find a room. */
            if (room != null) {
                return room;
            } else {
                Rectangle lRoom = new Rectangle();
                Rectangle rRoom = new Rectangle();
                if (leftChild != null) {
                    lRoom = leftChild.getRoom();
                }
                if (rightChild != null) {
                    rRoom = rightChild.getRoom();
                }
                if (lRoom == null && rRoom == null) {
                   // System.out.println("I got here.");

                    return null;
                } else if (rRoom == null) {
                    return lRoom;
                } else if (lRoom == null) {
                    return rRoom;
                } else if (RANDOM.nextBoolean()) {
                    return lRoom;
                } else {
                    return rRoom;
                }


            }

        }

        /** Next, we need a function that takes a pair of rooms, pick a random point
         * inside both rooms, and then creates one-tile-thick rectangles
         * to connect the points together.
         */
        public void createHall(Rectangle l, Rectangle r) {
            /** Now we connect these two rooms together with hallways.
             * we just need to figure out which point is where and then either draw
             * a line, or a pair of lines ot make a right-angle to connect them.
             */
            halls = new Vector<Rectangle>();

            Point point1 = new Point(RandomUtils.uniform(RANDOM, l.x + 1, l.x + l.width - 1),
                    RandomUtils.uniform(RANDOM, l.y + 1, l.y + l.height - 1));
            Point point2 = new Point(RandomUtils.uniform(RANDOM, r.x + 1, r.x + r.width - 1),
                    RandomUtils.uniform(RANDOM, r.y + 1, r.y + r.height - 1));

            int w = point2.x - point1.x;
            int h = point2.y - point1.y;
//            System.out.println("y pos of the l rect is: " + l.y + "/ width of l rect is: " + l.height);
//            System.out.println("point1 x is " + point1.x + ", point 1 y is " + point1.y);
//            System.out.println("y pos of the r rect is: " + r.y + "/ width of r rect is: " + r.height);
//            System.out.println("point2 x is " + point2.x + ", point 2 y is " + point2.y);
//            System.out.println("w is " + w + " h is " + h);

            Rectangle hall1 = null;
            Rectangle hall2 = null;

            if (w < 0) {
                if (h < 0) {
                    if (RANDOM.nextBoolean()) {
                        hall1 = new Rectangle(point2.x, point1.y - 1, Math.abs(w) + 1, 3);
                        hall2 = new Rectangle(point2.x - 1, point2.y, 3, Math.abs(h) + 1);
                    } else {
                        hall1 = new Rectangle(point2.x, point2.y - 1, Math.abs(w) + 1, 3);
                        hall2 = new Rectangle(point1.x - 1, point2.y, 3, Math.abs(h) + 1);
                    }
                } else if (h > 0) {
                    if (RANDOM.nextBoolean()) {
                        hall1 = new Rectangle(point2.x, point1.y - 1, Math.abs(w) + 1, 3);
                        hall2 = new Rectangle(point2.x - 1, point1.y, 3, Math.abs(h) + 1);
                    } else {
                        hall1 = new Rectangle(point2.x, point2.y - 1, Math.abs(w) + 1, 3);
                        hall2 = new Rectangle(point1.x - 1, point1.y, 3, Math.abs(h) + 1);
                    }
                } else {
                    hall1 = new Rectangle(point2.x, point2.y - 1, Math.abs(w) + 1, 3);
                }
            } else if (w > 0) {
                if (h < 0) {
                    if (RANDOM.nextBoolean()) {
                        hall1 = new Rectangle(point1.x, point2.y - 1, Math.abs(w) + 1, 3);
                        hall2 = new Rectangle(point1.x - 1, point2.y, 3, Math.abs(h) + 1);
                    } else {
                        hall1 = new Rectangle(point1.x, point1.y - 1, Math.abs(w) + 1, 3);
                        hall2 = new Rectangle(point2.x - 1, point2.y, 3, Math.abs(h) + 1);
                    }
                } else if (h > 0) {
                    if (RANDOM.nextBoolean()) {
                        hall1 = new Rectangle(point1.x, point1.y - 1, Math.abs(w) + 1, 3);
                        hall2 = new Rectangle(point2.x - 1, point1.y, 3, Math.abs(h) + 1);
                    } else {
                        hall1 = new Rectangle(point1.x, point2.y - 1, Math.abs(w) + 1, 3);
                        hall2 = new Rectangle(point1.x - 1, point1.y, 3, Math.abs(h) + 1);
                    }
                } else {
                    hall1 = new Rectangle(point1.x, point1.y - 1, Math.abs(w) + 1, 3);
                }
            } else {
                if (h < 0) {
                    hall1 = new Rectangle(point2.x - 1, point2.y, 3, Math.abs(h) + 1);
                } else if (h > 0) {
                    hall1 = new Rectangle(point1.x - 1, point1.y, 3, Math.abs(h) + 1);
                }
            }

//            System.out.println("width is: "+ hall1.width + "/ x is: " + hall1.x + "/ height is: " + hall1.height + "/ y is: " + hall1.y);
//            System.out.println();
            fillHall(hall1);
            fillHall(hall2);
            halls.add(hall1);
            if (hall2 != null) {
                halls.add(hall2);
            }


        }



    }
}

