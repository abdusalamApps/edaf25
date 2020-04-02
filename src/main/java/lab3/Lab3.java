package lab3;

import graph.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Lab3 {
    public static <T> int distance(Graph<T> g, T source, T dest) {
        int distance = 0;
        Set<T> visited = new HashSet<>();
        visited.add(source);
        Set<T> currentLevel = new HashSet<>();
        currentLevel.add(source);
        while (!currentLevel.isEmpty()) {
            Set<T> nextLevel = new HashSet<>();
            for (T vertex : currentLevel) {
                if (vertex.equals(dest))
                    return distance;
                for (T neighbour : g.neighbours(vertex)) {
                    if (!visited.contains(neighbour)) {
                        visited.add(neighbour);
                        nextLevel.add(neighbour);
                    }
                }
            }
            distance++;
            currentLevel = nextLevel;

        }
        return -1;
    }
}
