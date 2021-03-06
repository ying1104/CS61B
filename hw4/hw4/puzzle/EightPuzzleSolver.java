package hw4.puzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class EightPuzzleSolver {
    /***********************************************************************
     * Test routine for your Solver class. Uncomment and run to test
     * your basic functionality.
    **********************************************************************/
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = in.readInt();
            }
        }
        Board initial = new Board(tiles);
        Board initial2 = new Board(tiles);
        System.out.println("equals or not: " + initial.equals(initial2));
        System.out.println("initial estimate dist to goal is: "
                + initial.estimatedDistanceToGoal());
        // System.out.println("initial tiles is: " + initial.toString());
        Solver solver = new Solver(initial);
        StdOut.println("Minimum number of moves = " + solver.moves());
        solver.solution();
        solver.solution();
        for (WorldState ws : solver.solution()) {
            StdOut.println(ws);
        }
    }
}
