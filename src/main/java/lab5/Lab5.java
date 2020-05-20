package lab5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Lab5 {
    /**
     * Computes the maximum flow for a flow network.
     *
     * @param g a graph with edge capacities and a source and sink
     * @return shortest distance between start and end
     */
    public static int maxFlow(FlowGraph g, int source, int sink) {
        // todo(D2): Impelementera Edmonds-Karp varianten av Ford-Fulkerson algoritmen.
        // Det vill säga, använd bredden först-sökning för att hitta en väg med positivt
        // flöde,
        // subtrahera det flödet och upprepa tills det inte går att skicka igenom mer
        // flöden.
        int[][] residual = g.toMatrix();  // The capacities
        int[] parent = new int[residual.length];
        while (bfs(source, sink, residual, parent)) {

            // Find the bottleneck
            List<Integer> capacities = new ArrayList<>(); //remaining capacity
            int prev = sink;
            while (true) {
                int next = parent[prev];
                capacities.add(residual[next][prev]);
                prev = next;
                if (next == source) break;
            }
            int bottleneck = Collections.min(capacities);
            prev = sink;
            while (true) {
                int next = parent[prev];
                residual[next][prev] -= bottleneck; // Normal direction
                residual[prev][next] += bottleneck; // Return edge

                prev = next;
                if (next == source) break;
            }
        }
        int initialCapacity = Arrays.stream(g.toMatrix()[source]).sum();
        int residualCapacity = Arrays.stream(residual[source]).sum();
        int maxFlow = initialCapacity - residualCapacity;
        return maxFlow;
    }

    /**
     * Find a path from start to end
     *
     * @param numVertex the current vertex
     * @param start     start node
     * @param end       end node
     * @param residual  the residual graph used to find paths
     * @param pred      the previous nodes
     */
    private static boolean bfs(int start, int end, int[][] residual, int[] parent) {
        int vertexCount = residual.length;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);

        boolean[] visited = new boolean[vertexCount];
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll(); //Gets the current node

            if (current == end) {
                return true;
            }

            for (int i = 0; i < vertexCount; i++) {
                if (!visited[i] && residual[current][i] > 0) {
                    visited[i] = true;
                    queue.add(i);
                    parent[i] = current;
                }
            }
        }
        return false;
    }

    /**
     * Read a flowgraph from a file.
     */
    public static FlowGraph loadFlowGraph(Path path) throws IOException {
        // todo(D3): läs in ett flödesnätverk från fil.
        // Filen börjar med ett heltal som anger antalet noder,
        // sedan följer ett tal m som är antalet bågar.
        // Resten av filen består av m rader där varje rad anger en båge i
        // formatet u v c som beskriver en båge från en nod u till v med kapacitet c.
        System.out.println(path.toString());
        int vertexCount = 0;
        FlowEdge[] edges = null;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path.toFile()));
            vertexCount = Integer.valueOf(reader.readLine()); //Getting vertexcount
            edges = new FlowEdge[Integer.valueOf(reader.readLine())]; //Initializing edge-array with correct number of edges

            String line = reader.readLine();
            int index = 0;
            while (line != null && !line.equals("")) {
                String[] edgeString = line.split(" ");
                int source = Integer.valueOf(edgeString[0]);
                int destination = Integer.valueOf(edgeString[1]);
                int capacity = Integer.valueOf(edgeString[2]);
                if (capacity < 0) capacity = Integer.MAX_VALUE;
                edges[index] = new FlowEdge(source, destination, capacity);
                line = reader.readLine();
                index++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FlowGraph(vertexCount, edges);

    }
}
