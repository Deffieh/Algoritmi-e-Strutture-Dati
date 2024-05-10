package priorityqueue;

import priorityqueue.AbstractQueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class PriorityQueue<E> implements AbstractQueue<E> {
  private List<E> priorityQueue;
  private HashSet<E> elementSet;

  private Comparator<E> comparator;

  public PriorityQueue(){
    this.priorityQueue= new ArrayList<>();
    this.elementSet = new HashSet<>();
    this.comparator= null;
  }

  public PriorityQueue(Comparator<E> comparator) {
    this();
    this.setComparator(comparator);
  }

  public List<E> getPriorityQueue() {
    return priorityQueue;
  }
  public void setComparator(Comparator<E> comparator) {
    this.comparator = comparator;
  }

  @Override
  public boolean empty() {
    return priorityQueue.isEmpty();
  }
  @Override
  public boolean contains(E e) {
    //- Usiamo la HashSet per memoriazzare una copia dei dati
    //-- Per avere una Complessità di O(1)
    return elementSet.contains(e);
  }

  private int compare(E e1, E e2) {
    if (comparator != null) {
      return comparator.compare(e1, e2);
    }
    else {
//      Comparable<? super E> comparable = (Comparable<? super E>) e1;
      return -1;//comparable.compareTo(e2);
    }
  }
  // swap values at two indexes
  private void swap(int x, int y) {
    // swap with a child having greater value
    E temp = priorityQueue.get(x);
    priorityQueue.set(x, priorityQueue.get(y));
    priorityQueue.set(y, temp);
  }
  // Recursive heapify-up procedure for Comparator
  private void heapifyUp(int i) {
    if (i > 0 && compare(priorityQueue.get(i),priorityQueue.get(parent(i))) <= 0) {
      // swap the two if queue property is violated
      swap(i, parent(i));
      // call heapify-up on the parent
      heapifyUp(parent(i));
    }
  }

  @Override
  public boolean push(E e) {
    if (e == null || contains(e)) return false;
    if (//!(e instanceof Comparable) &&
            comparator == null)
      return false;

    priorityQueue.add(e);
    elementSet.add(e);

    heapifyUp(size() - 1);
    return true;
  }

  @Override
  public E top() {
    if(empty()) return null;
    return priorityQueue.get(0);
  }

  @Override
  public boolean remove(E e){
    if(!priorityQueue.contains(e)) return false;

    int index = priorityQueue.indexOf(e);
    int lastIdx = size() - 1;
//    if (index != lastIdx) {
    E lastElement = priorityQueue.get(lastIdx); // Ultimo elemento
    //priorityQueue.set(index, lastElement);
    swap(index,lastIdx);
    priorityQueue.remove(e);
    // call heapify-down on the root node

    heapifyDown(index);
//    }
//    else {
//      priorityQueue.remove(e);
//    }
    elementSet.remove(e);
    return true;
  }
  @Override
  public void pop() {
    if (empty()) return;
    // replace the root of the queue with the last element of the vector
    //remove(priorityQueue.lastElement());
    remove(top());
  }


  private int parent(int pos) {
    return (pos - 1) >> 1;
  }
  // return left child of `A[i]` O(1);
  private int left(int pos) {
    return (pos << 1) + 1;
  }
  // return right child of `A[i]`  O(1);
  private int right(int pos) {
    return left(pos) + 1;
  }

  private int size() {
    return priorityQueue.size();
  }

  private void heapifyDown(int i) {
    // get left and right child of node at index `i`
    int left = left(i);
    int right = right(i);
    int smallest = i;

    // compare `A[i]` with its left and right child
    // and find the smallest value
    if (left < size() && compare(priorityQueue.get(smallest), priorityQueue.get(left)) >= 0) {
      smallest = left;
    }
    if (right < size() && compare(priorityQueue.get(smallest), priorityQueue.get(right))  >= 0) {
      smallest = right;
    }

    if (smallest != i) {
      // swap with a child having lesser value
      swap(i, smallest);
      // call heapify-down on the child
      heapifyDown(smallest);
    }
  }


  public void decreaseKey(E old, E value){
    if (old == null || value == null) return;
    if(compare(old, value)<=0) return;

    int index=priorityQueue.indexOf(old);
    priorityQueue.set(index, value);
    heapifyUp(index);

    /*if (this.comparator == null) {
      Comparable<E> key = (Comparable<E>) old;

      if (key.compareTo(value) > 0) {
        int index = priorityQueue.indexOf(old);
        priorityQueue.set(index, value);
        heapifyUp(index);
      }
    } else {
      if (comparator.compare(old, value) > 0) {
        int index = priorityQueue.indexOf(old);
        priorityQueue.set(index, value);
        heapifyUp(index);
      }
    }*/


  }

  public void printQueue(){
    for(E el: priorityQueue){
      System.out.print(el + " ");
    }
  }

}
