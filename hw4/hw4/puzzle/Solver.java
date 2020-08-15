package hw4.puzzle;

import edu.princeton.cs.algs4.*;




public class Solver {
    public MinPQ<SearchNode> pqOfNodes = new MinPQ<>();
    public Stack<WorldState> solution;
    public int totalInsert;
    public WorldState initial;

    public class SearchNode implements Comparable<SearchNode> {
        public WorldState worldState;
        public int movesMade;
        public SearchNode preNode;
        public int estimateMoveToGoal;

        public SearchNode(WorldState w, int m, SearchNode s) {
            worldState = w;
            movesMade = m;
            preNode = s;
            estimateMoveToGoal = w.estimatedDistanceToGoal();

        }

        @Override
        public int compareTo(SearchNode o) {
            int thisEstimate = this.estimateMoveToGoal;
            int oEstimate = o.estimateMoveToGoal;
            int thisTotal = thisEstimate + this.movesMade;
            int oTotal = oEstimate + o.movesMade;
            return thisTotal - oTotal;
        }
    }

    /** Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     */
    public Solver(WorldState initial) {
        pqOfNodes.insert(new SearchNode(initial, 0, null));
        totalInsert = 0;
        this.initial = initial;
    }

    /** Returns the minimum number of moves to solve the
     * puzzle starting at the initial WorldState.
     */
    public int moves() {
        Solver solverMoves = new Solver(this.initial);
        Stack<WorldState> n = (Stack<WorldState>) solverMoves.solution();
        return n.size() - 1;

    }

    /** Returns a sequence of WorldStates from the initial
     *  WorldState to the solution.
     */
    public Iterable<WorldState> solution() {
        solution = new Stack<>();
        SearchNode current = pqOfNodes.min();
        pqOfNodes.delMin();
        while (!current.worldState.isGoal()) {
            for (WorldState w : current.worldState.neighbors()) {
                SearchNode toInsert = new SearchNode(w, current.movesMade + 1, current);
//                for (WorldState v : solution) {
//                    if (w.equals(v)) {
//                        repeat = true;
//                    }
//                }
                if (toInsert.preNode.preNode != null
                && toInsert.preNode.preNode.worldState.equals(toInsert.worldState)) {
                    continue;
                } else {
                    pqOfNodes.insert(toInsert);
                    totalInsert++;
                }

            }

            current = pqOfNodes.min();
            pqOfNodes.delMin();
        }
        while (current != null) {
            solution.push(current.worldState);
            current = current.preNode;
        }
        System.out.println(solution.size());
        return solution;
    }



}
