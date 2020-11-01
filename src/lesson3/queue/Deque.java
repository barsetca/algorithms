package lesson3.queue;

public interface Deque<E> extends Queue<E> {

  boolean insertHead(E value);

  E removeTail();

  E peekTail();
}
