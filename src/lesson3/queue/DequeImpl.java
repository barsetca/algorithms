package lesson3.queue;

public class DequeImpl<E> extends QueueImpl<E> implements Deque<E> {

  public DequeImpl(int maxSize) {
    super(maxSize);
  }

  @Override
  public boolean insertHead(E value) {
    if (isFull()) {
      return false;
    }
    if (isEmpty()) {
      return insertTail(value);
    }
    head = (head == DEFAULT_HEAD) ? data.length : head;
    data[--head] = value;
    size++;

    return true;
  }

  @Override
  public E removeTail() {
    if (size <= 1) {
      return removeHead();
    }
    E removed = data[tail--];
    tail = tail == DEFAULT_TAIL ? (data.length - 1) : tail;
    size--;
    return removed;
  }

  @Override
  public E peekTail() {
    return data[tail];
  }
}
