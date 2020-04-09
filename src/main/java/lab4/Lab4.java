package lab4;

import java.util.*;

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
                    int newDist = x.distance + e.distance;

                    int wDist = map.getOrDefault(w, Integer.MAX_VALUE);

                    if (!map.containsKey(w) || newDist < wDist) {
                        map.put(w, newDist);
                        queue.removeIf((edge) -> edge.node == w);
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
        if (start == end) {
            return new LinkedList<Integer>();
        }
        List<Integer> answer = new LinkedList<>();
        answer.add(start);
        Comparator<PQElement> cmp = Comparator.comparingInt(e -> e.distance);
        PriorityQueue<PQElement> queue = new PriorityQueue<>(cmp);
        queue.add(new PQElement(start, 0));

        Map<Integer, Integer> distances = new HashMap<>();
        g.vertexSet().forEach(v -> distances.put(v, Integer.MAX_VALUE));
        distances.put(start, 0);

        Map<Integer, Integer> parent = new HashMap<>();
        while (!queue.isEmpty()) {
            PQElement element = queue.poll();
            if (element.node == end) {
                List<Integer> path = new LinkedList<>();
                int current = end;
                while (true) { //Problematic...
                    path.add(current);
                    current = parent.get(current);
                    if (current == start) {
                        path.add(current);
                        break;
                    }
                }
                Collections.reverse(path);
                System.out.println(path);
                return path;
            } else {
                for (Edge e : g.edges(element.node)) {
                    int w = e.destination;
                    int newDist = element.distance + e.distance;
                    int wDist = distances.get(w);
                    if (!distances.containsKey(w) || newDist < wDist) {
                        distances.put(w, newDist);
                        queue.add(new PQElement(w, distances.get(w)));
                        parent.put(w, element.node);

                    }
                }
            }
        }
        return new LinkedList<Integer>();
    }
}
