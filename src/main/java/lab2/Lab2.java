package lab2;

import graph.Graph;

import java.util.*;

public class Lab2 {

  /**
   * Generic depth first search in a graph, starting from the vertex u.
   *
   * @param g       the graph to search in
   * @param u       the start vertex
   * @param visited set of visited vertices (should be empty for a full search)
   * @param <T>     the vertex type
   */
  private static <T> void dfs(Graph<T> g, T u, Set<T> visited) {
    visited.add(u);
    for (T v : g.neighbours(u)) {
      if (!visited.contains(v)) {
        dfs(g, v, visited);
      }
    }
  }

  public static boolean isConnected(Graph<Integer> g) {
    Set<Integer> visited = new HashSet<>();
    dfs(g, g.vertexSet().iterator().next(), visited);
    return visited.size() >= g.vertexCount();
  }

  public static int nbrOfComponents(Graph<Integer> g) {
    ArrayList<Integer> remaining = new ArrayList<>(g.vertexSet());
    int count = 0;
    while (remaining.size() != 0 && count < 100) {
      Set<Integer> visited = new HashSet<>();
      dfs(g, remaining.get(0), visited);
      remaining.removeAll(visited);
      count++;
    }
    return count;
  }

  public static boolean pathExists(Graph<Integer> g, int u, int v) {
    return quickDfs(g, u, v, new HashSet<Integer>());
  }

  public static boolean quickDfs(Graph<Integer> g, Integer start, Integer find, Set<Integer> visited) {
    visited.add(start);
    if (visited.contains(find)) return true;
    for (Integer vertex : g.neighbours(start)) {
      if (!visited.contains(vertex))
        if (quickDfs(g, vertex, find, visited))
          return true;
    }
    return false;
  }

  public static List<Integer> findPath(Graph<Integer> g, int u, int v) {
    // TODO(D5): implement this!
    return Collections.emptyList();
  }
}

