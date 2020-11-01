package lesson3.queue;

import static java.lang.System.arraycopy;

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
    if (head == 0) {
      //т.к. !isFull сдвигаем все элементы вправо на 1
      arraycopy(data, 0, data, 1, size);
      data[head] = value;
      tail++;
      size++;
    }
    if (head > 0) {
      System.out.println("head before = " + head);
      data[--head] = value;
      size++;
      System.out.println("head after = " + head);
      return true;
    }

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
