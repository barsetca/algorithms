package lesson3.queue;

public interface Queue<E> {

  boolean insertTail(E value);

  E removeHead();

  E peekHead();

  int size();

  default boolean isEmpty() {
    return size() == 0;
  }

  boolean isFull();

}
