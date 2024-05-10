import org.junit.Test;
import priorityqueue.PriorityQueue;
import priorityqueue.PriorityQueueException;

import static org.junit.Assert.*;

public class PriorityQueueTest {

  @Test
  public void emptyQueue(){
    PriorityQueue<Integer> queue=new PriorityQueue<>();
    assertTrue(queue.empty());
  }
  @Test
  public void oneElement() {
    PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o1.compareTo(o2));
    queue.push(12);
    assertEquals(Integer.valueOf(12), queue.top());
  }

  @Test
  public void oneElementRemove() {
    PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o1.compareTo(o2));
    queue.push(12);
    queue.pop();
    assertTrue(queue.empty());
  }

  @Test
  public void sameElements() {
    PriorityQueue<Integer> queue = new PriorityQueue<>((o1,o2)->o1.compareTo(o2));
    int expected = 1;
    queue.push(1);
    queue.push(1);
    queue.push(1);
    queue.push(1);
    queue.push(1);
    queue.getPriorityQueue();
    System.out.println();
    int actual = queue.top();
    assertEquals(expected, actual);
  }

  @Test
  public void rootTest() {
    PriorityQueue<Integer> queue = new PriorityQueue<>((o1,o2)->o1.compareTo(o2));
    int expected = 1;
    queue.push(12);
    queue.push(4);
    queue.push(8);
    queue.push(1);
    queue.push(7);
    int actual = queue.top();
    assertEquals(expected, actual);
  }

  @Test
  public void rootPopTest() {
    PriorityQueue<Integer> queue = new PriorityQueue<>((o1,o2)->o1.compareTo(o2));
    int expected = 2;
    queue.push(12);
    queue.push(4);
    queue.push(8);
    queue.push(1);
    queue.push(2);
    queue.push(7);
    queue.pop();
    int actual = queue.top();
    assertEquals(expected, actual);
  }

  @Test
  public void rootTestString(){
    PriorityQueue<String> queue= new PriorityQueue<>((o1,o2)->o1.compareTo(o2));
    String excepted="algoritmi";
    queue.push("università");
    queue.push("esame");
    queue.push("algoritmi");
    queue.push("strutture");
    String actual=queue.top();
    assertEquals(excepted, actual);
  }

  @Test
  public void removeTest(){
    PriorityQueue<Integer> queue= new PriorityQueue<>((o1,o2)->o1.compareTo(o2));
    queue.push(2);
    queue.push(6);
    queue.push(4);
    queue.push(12);
    queue.push(8);
    queue.push(1);
    queue.push(7);

    queue.remove(8);

    assertFalse(queue.contains(8));
  }

  @Test
  public void popTest(){
    PriorityQueue<Integer> queue= new PriorityQueue<>((o1,o2)->o1.compareTo(o2));
    queue.push(2);
    queue.push(6);
    queue.push(4);
    queue.push(12);
    queue.push(8);
    queue.push(1);
    queue.push(7);

    queue.pop();

    assertFalse(queue.contains(1));
  }
  

}
