import graph.Graph;
import graph.GraphException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GraphTest {
  @Test
  public void emptyGraph() {
    Graph<Integer, Integer> empty = new Graph<>(true);
    assertTrue(empty.isEmpty());
  }

  @Test
  public void oneElement() {
    Graph<Integer, Integer> graph = new Graph<>(true);
    Integer expected = 2;
    graph.addNode(expected);
    Integer actual = graph.getNodes().stream().toList().get(0);
    assertEquals(expected, actual);
  }

  @Test
  public void sameElements() {
    Graph<Integer, Integer> graph = new Graph<>(true);
    Integer firstVertex = 2;
    graph.addNode(firstVertex);
    graph.addNode(firstVertex);
    graph.addNode(firstVertex);
    List<Integer> expected = new ArrayList<>();
    expected.add(firstVertex);
    List<Integer> actual = graph.getNodes().stream().toList();
    assertEquals(expected, actual);
  }

  @Test
  public void containVertex() {
    Graph<Integer, Integer> graph = new Graph<>(true);
    Integer firstVertex = 2;
    Integer secondVertex = 4;
    Integer thirdVertex = 3;
    graph.addNode(firstVertex);
    graph.addNode(secondVertex);
    graph.addNode(thirdVertex);
    assertTrue(graph.containsNode(secondVertex));
  }

  @Test
  public void containEdgeDirect() {
    Graph<Integer, Integer> graph = new Graph<>(true);
    Integer firstVertex = 2;
    Integer secondVertex = 4;
    Integer thirdVertex = 3;
    graph.addNode(firstVertex);
    graph.addNode(secondVertex);
    graph.addNode(thirdVertex);
    graph.addEdge(thirdVertex, firstVertex, Integer.valueOf(20));

    System.out.println(graph.getNodes());
    assertTrue(graph.containsEdge(thirdVertex, firstVertex));
  }

  // test rimozione vertice
  @Test
  public void removeVertexDirect() {
    Graph<Integer, Integer> graph = new Graph<>(true);
    Integer firstVertex = 2;
    Integer secondVertex = 4;
    graph.addNode(secondVertex);
    graph.addNode(firstVertex);
    graph.addEdge(secondVertex, firstVertex, Integer.valueOf(20));
    graph.removeNode(firstVertex);
    assertTrue(!graph.containsNode(firstVertex));
  }

  @Test
  public void adjacents() {
    Graph<Integer, Integer> graph = new Graph<>(true);
    Integer firstVertex = 2;
    Integer secondVertex = 4;
    Integer thirdVertex = 3;
    graph.addNode(firstVertex);
    graph.addEdge(firstVertex, secondVertex, Integer.valueOf(20));
    graph.addEdge(firstVertex, thirdVertex, Integer.valueOf(30));
    List<Integer> expected = new ArrayList<>();
    expected.add(thirdVertex);
    expected.add(secondVertex);
    List<Integer> actual = graph.getNeighbours(firstVertex).stream().toList();
    assertEquals(expected, actual);
  }

  // test per grafi indiretto
  @Test
  public void containEdgeIndirectGraph() {
    Graph<Integer, Integer> graph = new Graph<>(false, true);
    Integer firstVertex = 2;
    Integer secondVertex = 4;
    graph.addNode(firstVertex);
    graph.addNode(secondVertex);
    graph.addEdge(secondVertex, firstVertex, Integer.valueOf(20));
    assertTrue(graph.containsEdge(secondVertex, firstVertex));
  }

  @Test
  public void containReverseEdgeIndirectGraph() {
    Graph<Integer, Integer> graph = new Graph<>(false, true);
    Integer firstVertex = 2;
    Integer secondVertex = 4;
    graph.addNode(firstVertex);
    graph.addNode(secondVertex);
    graph.addEdge(secondVertex, firstVertex, Integer.valueOf(20));
    assertTrue(graph.containsEdge(firstVertex, secondVertex));
  }

  @Test
  public void indirectRemoveEdge() {
    Graph<Integer, Integer> graph = new Graph<>(false, true);
    Integer firstVertex = 2;
    Integer secondVertex = 4;
    graph.addNode(firstVertex);
    graph.addNode(secondVertex);
    graph.addEdge(secondVertex, firstVertex, Integer.valueOf(20));
    graph.removeEdge(firstVertex, secondVertex);
    assertFalse(graph.containsEdge(secondVertex, firstVertex));
    assertFalse(graph.containsEdge(firstVertex, secondVertex));
  }

  @Test
  public void indirectWeight() {
    Graph<Integer, Integer> graph = new Graph<>(false, true);
    Integer firstVertex = 2;
    Integer secondVertex = 4;
    graph.addNode(firstVertex);
    graph.addNode(secondVertex);
    graph.addEdge(secondVertex, firstVertex, Integer.valueOf(20));
    Integer expected = graph.getLabel(secondVertex, firstVertex);
    Integer actual = graph.getLabel(firstVertex, secondVertex);
    assertEquals(expected, actual);
  }

  @Test
  public void labelledGraph(){
    Graph<Integer, Integer> graph = new Graph<>(false, false);
    graph.addNode(2);
    graph.addNode(4);
    graph.addEdge(2, 4, null);
    Integer expected = null;
    Integer actual = graph.getLabel(2, 4);
    assertEquals(expected, actual);
  }

}