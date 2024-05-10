import priorityqueue.PriorityQueue;
import priorityqueue.PriorityQueueException;



public class PriorityQueueMain {
  public static void main(String []args) throws PriorityQueueException {
    PriorityQueue<Integer> priorityQueue=new PriorityQueue<>();
    priorityQueue.push(0);
    priorityQueue.push(3);
    priorityQueue.push(4);
    priorityQueue.push(6);
    priorityQueue.push(8);
    priorityQueue.push(7);
    priorityQueue.push(2);
    priorityQueue.printQueue();
    System.out.println();

    priorityQueue.pop();
    priorityQueue.printQueue();
    System.out.println();

    System.out.println(priorityQueue.top());

    PriorityQueue<Integer> test=new PriorityQueue<>();
    test.push(12);
    test.pop();
    System.out.print("test:");
    test.printQueue();
    System.out.println(test.empty());
  }
}
