package graph;

import com.google.common.base.Preconditions;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A directed graph with a fixed number of vertices and edges.
 *
 * <p>Each vertex is represented by an integer from 0 to n-1, where n is the number of
 * vertices in the graph.
 * <p>
 * Edges are stored with adjacency lists for each vertex.
 */
public class SimpleGraph implements Graph<Integer> {
  private final int vertexCount;
  private final Map<Integer, Set<Integer>> adjacent;

  /**
   * Creates a graph with the edges listed in the second parameter.
   *
   * @param vertexCount number of vertices in the graph
   * @param edges array of directed edges in the format {@code {{source, dest}}}
   */
  public SimpleGraph(int vertexCount, int[]... edges) {
    Preconditions.checkArgument(vertexCount >= 0, "vertex count must be non-negative");
    this.vertexCount = vertexCount;
    adjacent = new HashMap<>(vertexCount);
    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];
      Preconditions.checkArgument(u != v, "cannot create loop at vertex %d", u);
      Preconditions.checkArgument(u >= 0 && u <= vertexCount && v >= 0 && v <= vertexCount,
              "cannot create edge from %d to %d in a graph with %d vertices", u, v, vertexCount);
      Set<Integer> adj = adjacent.get(u);
      if (adj == null) {
        adj = new LinkedHashSet<>(); // LinkedHashSet to use edge insertion order for iteration.
        adjacent.put(u, adj);
      }
      Preconditions.checkArgument(!adj.contains(v), "cannot create duplicate edge %d -> %d", u, v);
      adj.add(v);
    }
  }

  @Override public int vertexCount() {
    return vertexCount;
  }

  @Override public Collection<Integer> vertexSet() {
    return IntStream.range(0, vertexCount).boxed().collect(Collectors.toList());
  }

  @Override public Collection<Integer> neighbours(Integer v) {
    return Collections.unmodifiableSet(adjacent.getOrDefault(v, Collections.emptySet()));
  }

  /**
   * Returns a String representation of the vertices and edges of the graph.
   *
   * @return A String representation of the graph.
   */
  public String toString() {
    StringBuilder str = new StringBuilder();
    for (int u = 0; u < vertexCount; ++u) {
      str.append(String.format("Vertex %d", u));
      if (!neighbours(u).isEmpty()) {
        str.append(", outgoing edges:");
        for (int v : neighbours(u)) {
          str.append(String.format("\n\t%d -> %d", u, v));
        }
      }
      str.append("\n");
    }
    return str.toString();
  }
}
