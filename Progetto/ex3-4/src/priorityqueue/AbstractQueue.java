package priorityqueue;

public interface AbstractQueue<E> {
  public boolean empty(); // controlla se la coda è vuota

  public boolean push(E e) throws PriorityQueueException; // aggiunge un elemento alla coda

  public E top(); // accede all'elemento in cima alla coda

  public void pop(); // rimuove l'elemento in cima alla coda

  public boolean contains(E e);

  public boolean remove(E e);
}
