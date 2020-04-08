package lab4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Helper class for the priority queue in Dijkstras algorithm.
 */
class PQElement {
    int node;
    int distance;

    public PQElement(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}

public class Lab4 {
    /**
     * Computes the shortest distance between start and end in the graph g using Dijkstra's
     * algorithm.
     * This version handles only graphs with integer edge distances.
     *
     * @param g     a graph with distance information attached to the edges
     * @param start start vertex
     * @param end   end vertex
     * @return shortest distance between start and end
     */
    public static int distance(DistanceGraph g, int start, int end) {
        // En Comparator skapas för att hålla listan med bågar sorterad:
        Comparator<PQElement> cmp = Comparator.comparingInt(e -> e.distance);
        PriorityQueue<PQElement> queue = new PriorityQueue<>(cmp);
        queue.add(new PQElement(start, 0));
        // TODO(D2): slutför Dijkstras algoritm för att hitta kortaste avstånd start->end.
        Map<Integer, Integer> map = new HashMap<>();
        map.put(start, 0);
        while (!queue.isEmpty()) {   // så länge pq inte är tom
            PQElement x = queue.poll(); // {x, dist} = ta ut minsta elementet ur pq
            if (x.node == end) { // om x == v
                return x.distance; // return dist
            } else {
                for (Edge e : g.edges(x.node)) { // för varje båge e från x
                    int w = e.destination; // w = e.destination
                    int newDist = map.get(x.node) + e.distance;

                    int wDist  = map.getOrDefault(w, Integer.MAX_VALUE);

                    if (!map.containsKey(w) || newDist < wDist) {
                        map.put(w, newDist);
                        queue.add(new PQElement(w, newDist));
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Finds a shortest path between start and end in a graph g Dijkstra's
     * algorithm.
     * The graph can have any distance unit.
     *
     * @param g     a graph with distance information attached to the edges
     * @param start start vertex
     * @param end   end vertex
     * @return a list containing the nodes on the shortest path from start to end
     */
    public static List<Integer> shortestPath(DistanceGraph g, int start, int end) {
        Comparator<PQElement> cmp = Comparator.comparingInt(e -> e.distance);
        PriorityQueue<PQElement> queue = new PriorityQueue<>(cmp);
        queue.add(new PQElement(start, 0));
        // TODO(D3): slutför algoritmen och returnera vägen från start till end.
        // T.ex. om kortaste vägen mellan 0 och 10 är 0->1->5->10 ska listan [0,1,5,10] returneras.
        List<Integer> answer = new LinkedList<>();
        return answer;
    }
}
