package lesson3.queue;

public class QueueImpl<E> implements Queue<E> {

  public static final int DEFAULT_HEAD = 0;
  public static final int DEFAULT_TAIL = -1;

  protected E[] data;
  protected int size;

  protected int head;
  protected int tail;

  @SuppressWarnings("unchecked")
  public QueueImpl(int maxSize) {
    this.data = (E[]) new Object[maxSize];
    head = DEFAULT_HEAD;
    tail = DEFAULT_TAIL;
  }

  @Override //O(1)
  public boolean insertTail(E value) {
    if (isFull()) {
      return false;
    }
    if (tail == data.length - 1) {
      tail = DEFAULT_TAIL;
    }
    data[++tail] = value;
    size++;
    return true;
  }

  @Override //O(1)
  public E removeHead() {
    if (isEmpty()) {
      return null;
    }
    if (head == data.length) {
      head = DEFAULT_HEAD;
    }
    size--;
    return data[head++];
  }

  @Override //O(1)
  public E peekHead() {
    return data[head];
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isFull() {
    return size == data.length;
  }
}
