package prim;

import graph.Edge;
import graph.Graph;
import graph.GraphException;
import priorityqueue.PriorityQueue;
import priorityqueue.PriorityQueueException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrimAlgorithm<V, E extends Number> {
  public Map<V, Edge<V, Double>> prim(Graph<V, E> graph, V source, Comparator<Edge<V,Double>> comparator) throws PriorityQueueException, GraphException {
    List<V> vertexes=graph.getNodes().stream().toList();
    Map<V,V> predecessor= new HashMap<>();
    Map<V,Double> distance = new HashMap<>();
    Map<V, Edge<V,Double>> mst = new HashMap<>();
    PriorityQueue<Edge<V,Double>> priorityQueue = new PriorityQueue<>(comparator);

    for(V vertex: vertexes){
      distance.put(vertex, Double.POSITIVE_INFINITY);
      predecessor.put(vertex, null);
    }
    distance.put(source, 0.0);
    predecessor.put(source, source);

    for(V vertex: vertexes){
      priorityQueue.push(new Edge<>(null, vertex, distance.get(vertex)));
    }

    //----------------------------------------------------
    while(!priorityQueue.empty()){
      Edge<V, Double> u=priorityQueue.top();
      priorityQueue.pop();
      u.setStart(predecessor.get(u.getEnd()));
      mst.put(u.getEnd(), u);
      List<V> adjacents=graph.getNeighbours(u.getEnd()).stream().toList();
      for(V adjacent: adjacents){
        Double weight = Double.valueOf(graph.getLabel(u.getEnd(), adjacent).toString());
        Double old = distance.get(adjacent);
        if(old.compareTo(weight)>0 && containsVertex(priorityQueue, adjacent)){
            distance.put(adjacent, weight);
            predecessor.put(adjacent, u.getEnd());
            priorityQueue.decreaseKey(new Edge<>(null, adjacent, old),
                    new Edge<>(null, adjacent, weight));
        }
      }
      //caso foresta
      if(u.getStart()==null){
        u.setStart(u.getEnd());
        u.setLabel(0.0);
      }
    }


    return mst;
  }

  public static <V> boolean containsVertex(PriorityQueue<Edge<V, Double>> priorityQueue, V vertex) {
    for (Edge<V, Double> edge : priorityQueue.getPriorityQueue()) {
      if (edge.getStart() == null && edge.getEnd().equals(vertex)) {
        return true;
      }
    }
    return false;
  }

}
