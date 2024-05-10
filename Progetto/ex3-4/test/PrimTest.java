import graph.Edge;
import graph.Graph;
import graph.GraphException;
import org.junit.Test;
import prim.PrimAlgorithm;
import priorityqueue.PriorityQueueException;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PrimTest {

  @Test
  public void containIndirectEdge() throws GraphException, PriorityQueueException {
    Graph<Integer, Integer> graph= new Graph<>(false, true);
    PrimAlgorithm<Integer, Integer> pa=new PrimAlgorithm<>();
    System.out.println("MST Start");

    graph.addNode(0);
    graph.addNode(1);
    graph.addNode(2);
    graph.addNode(3);
    graph.addNode(4);
    graph.addNode(5);
    graph.addNode(6);
    graph.addNode(7);

    graph.addEdge(0,3, 2);
    graph.addEdge(0,1, 9);
    graph.addEdge(0, 2, 3);
    graph.addEdge(0, 4, 5);
    graph.addEdge(1, 2, 9);
    graph.addEdge(1, 5, 5);
    graph.addEdge(2, 4, 4);
    graph.addEdge(3, 5, 9);
    graph.addEdge(3, 7, 2);
    graph.addEdge(4, 7, 2);
    graph.addEdge(5, 6, 9);
    graph.addEdge(5, 7, 9);

    Map<Integer, Edge<Integer, Double>> mst= pa.prim(graph, 0, (o1, o2)->o1.getLabel().compareTo(o2.getLabel()));



    assertEquals(Double.valueOf(0), mst.get(0).getLabel());
    assertEquals(Double.valueOf(9), mst.get(1).getLabel());
    assertEquals(Double.valueOf(3), mst.get(2).getLabel());
    assertEquals(Double.valueOf(2), mst.get(3).getLabel());
    assertEquals(Double.valueOf(2), mst.get(4).getLabel());
    assertEquals(Double.valueOf(5), mst.get(5).getLabel());
    assertEquals(Double.valueOf(9), mst.get(6).getLabel());
    assertEquals(Double.valueOf(2), mst.get(7).getLabel());
  }
}
