package lesson4;

public class SimpleLinkedListImpl<E> implements LinkedList<E> {

  protected int size;

  protected Node<E> firstElement;

  @Override
  public void insertFirst(E value) {
    Node<E> newNode = new Node<>(value, firstElement);
    firstElement = newNode;
    size++;
  }

  @Override
  public E removeFirst() {
    if (isEmpty()) {
      return null;
    }
    Node<E> removedElement = firstElement;
    E removedItem = removedElement.item;
    firstElement = firstElement.next;
    removedElement.next = null;
    removedElement.item = null;
    size--;
    return removedItem;
  }

  @Override
  public boolean remove(E value) {
    if (isEmpty()) {
      return false;
    }

    Node<E> current = firstElement;
    Node<E> previous = null;
    while (current != null) {
      if (value.equals(current.item)) {
        break;
      }
      previous = current;
      current = current.next;
    }
    if (current == null) {
      return false;
    }
    if (current == firstElement) {
      firstElement = firstElement.next;
    } else {
      previous.next = current.next;

    }
    current.item = null;
    current.next = null;
    size--;
    return true;
  }

  @Override
  public boolean contains(E value) {
    Node<E> current = firstElement;
    while (current != null) {
      if (current.item.equals(value)) {
        return true;
      }
      current = current.next;
    }
    return false;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public void display() {
    System.out.println("------------------------");
    Node<E> current = firstElement;
    while (current != null) {
      System.out.println(current.item);
      current = current.next;
    }
    System.out.println("------------------------");

  }

  @Override
  public E getFirst() {
    return firstElement.item;
  }
}
