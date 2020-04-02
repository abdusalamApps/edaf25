package lab3;

import graph.Graph;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class WordGraph implements Graph<String> {
    private final Map<String, Set<String>> graph = new HashMap<>();

    public WordGraph(Path wordfile, WordCriteria criteria) throws IOException {
        try (Reader reader = Files.newBufferedReader(wordfile)) {
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNext()) {
                String word = scanner.nextLine();
                graph.put(word, new HashSet<>());
            }
        }
        for (String word : graph.keySet()) {
            for (String neighbour : graph.keySet()) {
                if (criteria.adjacent(word, neighbour)) {
                    Set<String> wordSet = graph.get(word);
                    wordSet.add(neighbour);
                    graph.put(word, wordSet);
                }
            }
        }
    }

    @Override
    public int vertexCount() {
        return graph.size();
    }

    @Override
    public Collection<String> vertexSet() {
        return graph.keySet();
    }

    @Override
    public Collection<String> neighbours(String v) {
        return graph.getOrDefault(v, Collections.emptySet());
    }
}
