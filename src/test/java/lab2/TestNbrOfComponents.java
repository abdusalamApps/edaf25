package lab2;

import graph.Graph;
import graph.SimpleGraph;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class TestNbrOfComponents {
  @Test
  public final void testOneVertex() {
    Graph<Integer> g = new SimpleGraph(1);
    assertThat(Lab2.nbrOfComponents(g)).isEqualTo(1);
  }

  @Test
  public final void testTwoVerticesTwoComponents() {
    Graph<Integer> g = new SimpleGraph(2);
    assertThat(Lab2.nbrOfComponents(g)).isEqualTo(2);
  }

  @Test
  public final void testTwoVerticesOneComponent() {
    Graph<Integer> g = new SimpleGraph(2, new int[][]{
        {0, 1}, {1, 0},
    });
    assertThat(Lab2.nbrOfComponents(g)).isEqualTo(1);
  }

  @Test
  public final void testThreeVerticesOneComponent() {
    Graph<Integer> g = new SimpleGraph(2, new int[][]{
        {0, 1}, {1, 0},
        {0, 2}, {2, 0},
    });
    assertThat(Lab2.nbrOfComponents(g)).isEqualTo(1);
  }

  @Test
  public final void testThreeVerticesTwoComponents() {
    Graph<Integer> g = new SimpleGraph(2, new int[][]{
        {1, 2}, {2, 1},
    });
    assertThat(Lab2.nbrOfComponents(g)).isEqualTo(2);
  }

  @Test
  public final void testFiveVerticesOneComponent() {
    Graph<Integer> g = new SimpleGraph(5,
        new int[][]{
            {0, 1}, {1, 0},
            {0, 2}, {2, 0},
            {1, 2}, {2, 1},
            {0, 3}, {3, 0},
            {3, 4}, {4, 3},
        });
    assertThat(Lab2.nbrOfComponents(g)).isEqualTo(1);
  }

  @Test
  public final void testFiveVerticesTwoComponents() {
    Graph<Integer> g = new SimpleGraph(5,
        new int[][]{
            {0, 1}, {1, 0},
            {0, 2}, {2, 0},
            {1, 2}, {2, 1},
            {3, 4}, {4, 3},
        });
    assertThat(Lab2.nbrOfComponents(g)).isEqualTo(2);
  }

  @Test
  public final void testFiveVerticesFiveComponents() {
    Graph<Integer> g = new SimpleGraph(5);
    assertThat(Lab2.nbrOfComponents(g)).isEqualTo(5);
  }

}