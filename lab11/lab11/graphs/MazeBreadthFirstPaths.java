package lab11.graphs;

import java.util.Comparator;
import java.util.PriorityQueue;;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private PriorityQueue<Integer> q;


    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        Comparator<Integer> c = new Comparator<>() {
            @Override
            public int compare(Integer i, Integer j) {
                if (edgeTo[i] == edgeTo[j]) {
                    return 0;
                } else if (edgeTo[i] > edgeTo[j]) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };
        q = new PriorityQueue<>(c);
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int v) {
        marked[v] = true;
        announce();

        if (v == t) {
            targetFound = true;
        }

        if (targetFound) {
            return;
        }


        for (int w : maze.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                announce();
                distTo[w] = distTo[v] + 1;
                if (targetFound) {
                    return;
                }
                q.add(w);
            }
        }
        int nextMaze = q.peek();
        q.remove(nextMaze);
        bfs(nextMaze);
    }


    @Override
    public void solve() {
        bfs(s);
    }
}

