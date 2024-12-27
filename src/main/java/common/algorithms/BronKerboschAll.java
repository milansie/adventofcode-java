package common.algorithms;

import java.util.*;

/**
 * This class implements the Bron–Kerbosch algorithm to find all maximal cliques in an undirected graph.
 * The graph is represented by an adjacency list, where adjacencyList.get(i) returns a list of neighbors of vertex i.
 */
public class BronKerboschAll {

    /**
     * Adjacency list of the graph. adjacencyList.get(i) contains a list of neighbors of vertex i.
     */
    private final List<List<Integer>> adjacencyList;

    /**
     * Stores all discovered maximal cliques.
     */
    private final List<Set<Integer>> allMaximalCliques = new ArrayList<>();

    /**
     * Keeps track of the largest clique found so far.
     */
    private Set<Integer> bestClique = new HashSet<>();

    /**
     * Constructs the BronKerboschAll object with the given adjacency list.
     *
     * @param adjacencyList a list of lists representing the neighbors of each vertex
     */
    public BronKerboschAll(List<List<Integer>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    /**
     * Finds and returns the largest clique in the graph by running the Bron–Kerbosch algorithm.
     *
     * @return a set of vertices representing the largest clique found
     */
    public Set<Integer> getBestClique() {
        // R: vertices in the current clique
        // P: potential vertices that could be added to the current clique
        // X: vertices that must not be added to the current clique
        Set<Integer> R = new HashSet<>();
        Set<Integer> P = new HashSet<>();
        Set<Integer> X = new HashSet<>();

        // Fill P with all vertices in the graph (0..N-1)
        for (int i = 0; i < adjacencyList.size(); i++) {
            P.add(i);
        }

        // Run the recursive Bron–Kerbosch search
        bronKerbosch(R, P, X);

        // Return the largest clique found
        return bestClique;
    }

    /**
     * Finds and returns all maximal cliques in the graph by running the Bron–Kerbosch algorithm.
     *
     * @return a list of sets, where each set is a maximal clique
     */
    public List<Set<Integer>> findAllMaximalCliques() {
        // R: vertices in the current clique
        // P: potential vertices that could be added to the current clique
        // X: vertices that must not be added to the current clique
        Set<Integer> R = new HashSet<>();
        Set<Integer> P = new HashSet<>();
        Set<Integer> X = new HashSet<>();

        // Fill P with all vertices in the graph (0..N-1)
        for (int i = 0; i < adjacencyList.size(); i++) {
            P.add(i);
        }

        // Run the recursive Bron–Kerbosch search
        bronKerbosch(R, P, X);

        // Return the list of all maximal cliques
        return allMaximalCliques;
    }

    /**
     * The core recursive Bron–Kerbosch procedure.
     *
     * @param R the set of vertices included in the current clique
     * @param P the set of candidate vertices that could be added to form a larger clique
     * @param X the set of vertices that should not be added to the current clique
     */
    private void bronKerbosch(Set<Integer> R, Set<Integer> P, Set<Integer> X) {
        // If there are no more vertices to add (P and X are empty), we've found a maximal clique
        if (P.isEmpty() && X.isEmpty()) {
            // Store the clique
            allMaximalCliques.add(new HashSet<>(R));

            // Update the largest clique if necessary
            if (R.size() > bestClique.size()) {
                bestClique = new HashSet<>(R);
            }
            return;
        }

        // Pruning: if even adding all vertices in P won't exceed the size of the current best clique, skip
        if (R.size() + P.size() <= bestClique.size()) {
            return;
        }

        // Choose a pivot for optimization (any vertex from P ∪ X)
        Integer pivot = choosePivot(P, X);
        if (pivot == null) {
            // If both P and X are empty, we would have terminated earlier, so just return
            return;
        }

        // All neighbors of the chosen pivot
        Set<Integer> pivotNeighbors = new HashSet<>(adjacencyList.get(pivot));

        // Candidates to explore are vertices in P \ pivotNeighbors
        Set<Integer> candidates = new HashSet<>(P);
        candidates.removeAll(pivotNeighbors);

        // Explore each candidate
        for (Integer v : candidates) {
            // newR = R ∪ {v}
            Set<Integer> newR = new HashSet<>(R);
            newR.add(v);

            // newP = P ∩ N(v)
            Set<Integer> newP = intersect(P, adjacencyList.get(v));

            // newX = X ∩ N(v)
            Set<Integer> newX = intersect(X, adjacencyList.get(v));

            // Recurse
            bronKerbosch(newR, newP, newX);

            // Move v from P to X, removing it from candidates
            P.remove(v);
            X.add(v);
        }
    }

    /**
     * Chooses a pivot vertex. This basic implementation returns the first element of P if it's not empty;
     * otherwise, it returns the first element of X.
     *
     * @param P the set of candidate vertices
     * @param X the set of excluded vertices
     * @return an Integer representing the chosen pivot vertex, or null if both P and X are empty
     */
    private Integer choosePivot(Set<Integer> P, Set<Integer> X) {
        if (!P.isEmpty()) {
            return P.iterator().next();
        } else if (!X.isEmpty()) {
            return X.iterator().next();
        } else {
            return null;
        }
    }

    /**
     * Computes the intersection of a set with a list of neighbors.
     *
     * @param set the set of vertices we want to intersect
     * @param neighbors a list of neighbors for a particular vertex
     * @return a new set representing the intersection of 'set' and 'neighbors'
     */
    private Set<Integer> intersect(Set<Integer> set, List<Integer> neighbors) {
        Set<Integer> result = new HashSet<>();
        for (Integer n : neighbors) {
            if (set.contains(n)) {
                result.add(n);
            }
        }
        return result;
    }
}
