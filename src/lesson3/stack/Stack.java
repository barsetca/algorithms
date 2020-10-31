package lesson3.stack;

// base of array
public interface Stack<E> {

  void push(E value); // add

  E pop(); //delete

  E peek(); //get

  int size();

  default boolean isEmpty() {
    return size() == 0;
  }

  boolean isFull();

}
