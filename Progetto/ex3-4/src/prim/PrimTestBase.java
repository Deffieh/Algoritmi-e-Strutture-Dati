package prim;

import graph.Edge;
import graph.Graph;
import graph.GraphException;
import priorityqueue.PriorityQueueException;

import java.util.Map;

public class PrimTestBase {
  public static void main(String[] args) throws GraphException, PriorityQueueException {
    System.out.println("MST Start");
    Graph<Integer, Integer> graph= new Graph<>(false, true);
    PrimAlgorithm<Integer, Integer> pa=new PrimAlgorithm<>();


    graph.addNode(0);
    graph.addNode(1);
    graph.addNode(2);
    graph.addNode(3);
    graph.addNode(4);
    graph.addNode(5);
    graph.addNode(6);
    graph.addNode(7);

    graph.addNode(10);
    graph.addNode(11);
    graph.addNode(20);
    graph.addNode(21);

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

    graph.addEdge(10, 11, 9);
    graph.addEdge(11, 10, 9);
    graph.addEdge(20, 21, 9);
    graph.addEdge(21, 20, 9);


    /*Graph<String, Integer> graph= new Graph<>(false, true);
    PrimAlgorithm<String, Integer> pa=new PrimAlgorithm<>();

    graph.addNode("A");
    graph.addNode("B");
    graph.addNode("C");
    graph.addNode("D");

    graph.addEdge("A", "B", 5);
    graph.addEdge("B", "C", 3);
    graph.addEdge("C", "D", 2);
    graph.addEdge("D", "A", 4);
    graph.addEdge("E", "F", 9);
    graph.addEdge("F", "E", 9);


    Map<String, Edge<String, Double>> mst= pa.prim(graph, "A", (o1, o2)->o1.getLabel().compareTo(o2.getLabel()));*/

    Map<Integer, Edge<Integer, Double>> mst= pa.prim(graph, 0, (o1, o2)->o1.getLabel().compareTo(o2.getLabel()));
    mst.forEach((k, v) -> {
      System.out.println("Chiave: " + k + ", Valore: " + v);
    });
    System.out.println("MST End");
  }
}
