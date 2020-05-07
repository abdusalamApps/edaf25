package lab5;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Lab5 {
  /**
   * Computes the maximum flow for a flow network.
   * @param g a graph with edge capacities and a source and sink
   * @return shortest distance between start and end
   */
  public static int maxFlow(FlowGraph g, int source, int sink) {
    // TODO(D2): Impelementera Edmonds-Karp varianten av Ford-Fulkerson algoritmen.
    // Det vill säga, använd bredden först-sökning för att hitta en väg med positivt flöde,
    // subtrahera det flödet och upprepa tills det inte går att skicka igenom mer flöden.
    return 0;
  }

  /**
   * Read a flowgraph from a file.
   */
  public static FlowGraph loadFlowGraph(Path path) throws IOException {
    // TODO(D3): läs in ett flödesnätverk från fil.
    // Filen börjar med ett heltal som anger antalet noder,
    // sedan följer ett tal m som är antalet bågar.
    // Resten av filen består av m rader där varje rad anger en båge i
    // formatet u v c som beskriver en båge från en nod u till v med kapacitet c.
    return new FlowGraph(2);
  }
}
