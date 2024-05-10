package graph;

public class Edge<V, L> implements AbstractEdge<V,L>{
  private V start;
  private V end;
  private L label;

  public Edge(V start, V end, L label) {
    this.start = start;
    this.end = end;
    this.label = label;
  }

  public Edge(V start, V end) {
    this(start, end, null);
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    Edge<V, L> other = (Edge<V, L>) obj;
    if (start != null)
      return this.start.equals(other.start) && this.end.equals(other.end);
    return this.end.equals(other.end);
  }

  @Override
  public int hashCode() {
    return end.hashCode();
  }

  @Override
  public String toString() {
    return "\tsrc: " + start + " ,dest: " + end + " ,weight: " + label + "\n";
  }

  // Setters & Getters
  public V getStart() {
    return start;
  }

  public void setStart(V start) {
    this.start = start;
  }

  public V getEnd() {
    return end;
  }

  public void setEnd(V end) {
    this.end = end;
  }

  public L getLabel() {
    return label;
  }

  public void setLabel(L label) {
    this.label = label;
  }

}