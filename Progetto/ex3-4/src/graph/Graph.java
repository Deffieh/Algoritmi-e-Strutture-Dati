package graph;

import java.util.*;

public class Graph<V, L> implements AbstractGraph<V,L>{

  private Map<V, Set<Edge<V, L>>> graph;
  private boolean direct;
  private boolean label;

  // Empty Graph O(1)
  public Graph(boolean label) {
    this(true, label);
  }

  // Empty Graph O(1)
  public Graph(boolean directed, boolean labelled) {
    this.direct = directed;
    this.label = labelled;
    this.graph = new HashMap<>();
  }

  public boolean isEmpty() {
    return graph.isEmpty();
  }


  // direct? – O(1)
  @Override
  public boolean isDirected() {
    return direct;
  }

  @Override
  public boolean isLabelled() {
    return label;
  }

  //0(1)
  @Override
  public boolean addNode(V a)  {
    if (a == null)
      return false;
    if (!containsNode(a))
      graph.put(a, new HashSet<>());
    return true;
  }

  // O(1)
  public boolean addEdge(V start, V end, L label) {
    if (start == null || end == null || label == null)
      return false;

    addNode(start); // O(1)
    addNode(end); // O(1)

    if(!isLabelled()) {
      graph.get(start).add(new Edge<>(start, end));
      if (!isDirected()) {
        graph.get(end).add(new Edge<>(end, start));
      }
    }
    else {
      graph.get(start).add(new Edge<>(start, end, label)); // O(1)
      if (!isDirected()) {
        graph.get(end).add(new Edge<>(end, start, label)); // O(1)
      }
    }
    return true;
  }

  // O(1)
  @Override
  public boolean containsNode(V a) {
    if (a == null)
      return false;
    return graph.containsKey(a);
  }

  // O(1) (*)
  @Override
  public boolean containsEdge(V a, V b) {
    if (a == null || b == null)
      return false;
    if (!containsNode(a) || !containsNode(b))
      return false;

    boolean hasIt = false;
    Edge<V, L> edge = new Edge<>(a, b);

    Iterator<Edge<V, L>> iterator = graph.get(a).iterator();
    while (iterator.hasNext() && !hasIt) {
      if (iterator.next().equals(edge))
        hasIt = true;
    }
    return hasIt;
  }

  // O(n)
  @Override
  public boolean removeNode(V a) {
    if (a == null)
      return false;
    if (!containsNode(a))
      return false;

    for (V v : graph.keySet()) {
      if (containsEdge(v, a))
        removeEdge(v, a);
    }
    graph.remove(a);
    return true;
  }

  // O(1) (*)
  public boolean removeEdge(V a, V b)  {
    if (a == null || b == null)
      return false;
    if (!containsNode(a) || !containsNode(b))
      return false;

    if (containsEdge(a, b))
      graph.get(a).remove(new Edge<V, L>(a, b));
    if (!isDirected())
      graph.get(b).remove(new Edge<V, L>(b, a));
    return true;
  }

  // O(1)
  // Getting the keyset is O(1) and cheap. This is because HashMap.keyset()
  // returns the actual KeySet object associated with the HashMap.
  @Override
  public int numNodes() {
    return graph.keySet().size();
  }

  //O(n)
  @Override
  public int numEdges() {
    int sum = 0;
    for (V v : graph.keySet()) {
      sum += graph.get(v).size();
    }
    return isDirected() ? sum : sum / 2;
  }

  // O(n) copy all set elements
  @Override
  public AbstractCollection<V> getNodes() {
    return new ArrayList<>(graph.keySet());
  }


  // O(n)
  public AbstractCollection<AbstractEdge<V,L>> getEdges() {
    AbstractCollection<AbstractEdge<V,L>> setVertexes = new HashSet<>();
    graph.keySet().stream().forEach(vertex -> setVertexes.addAll(graph.get(vertex)));
    return setVertexes;
  }

  // O(1) (*)
  @Override
  public AbstractCollection<V> getNeighbours(V a) {
    if (a == null)
      return null;
    if (!containsNode(a))
      return null;
    return (AbstractCollection<V>) graph.get(a).stream().map(Edge::getEnd).toList();

  }


  // O(1) (*)
  @Override
  public L getLabel(V a, V b) {
    if (a == null || b == null)
      return null;
    if (!containsNode(a) || !containsNode(b))
      return null;

    Edge<V, L> edge = new Edge<>(a, b);
    L weight = null;
    Iterator<Edge<V, L>> iterator = graph.get(a).iterator();
    while (weight == null && iterator.hasNext()) {
      Edge<V, L> iterEdge = iterator.next();
      if (iterEdge.equals(edge))
        weight = iterEdge.getLabel();
    }
    return weight;
  }

  // O(n)
  public Set<Edge<V, L>> getEdges(V a)  {
    if (a == null)
      return null;
    if (!containsNode(a))
      return null;

    return graph.get(a);
  }

  @Override
  public String toString() {
    return "Graph{" +
            "graph=" + graph +
            ", direct=" + direct +
            '}';
  }
}
