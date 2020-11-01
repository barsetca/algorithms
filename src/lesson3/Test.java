package lesson3;

import java.util.ArrayDeque;
import lesson3.queue.Deque;
import lesson3.queue.DequeImpl;
import lesson3.queue.PriorityQueue;
import lesson3.queue.Queue;
import lesson3.stack.Stack;
import lesson3.stack.StackImpl;

public class Test {


  public static void main(String[] args) {
//        testStack();
    //testQueue();
    testDeque();
    ArrayDeque<Integer> integers = new ArrayDeque<>();
  }

  private static void testStack() {
    Stack<Integer> stack = new StackImpl<>(5);

    System.out.println("Add value 1: " + addToStack(stack, 1));
    System.out.println("Add value 2: " + addToStack(stack, 2));
    System.out.println("Add value 3: " + addToStack(stack, 3));
    System.out.println("Add value 4: " + addToStack(stack, 4));
    System.out.println("Add value 5: " + addToStack(stack, 5));
    System.out.println("Add value 6: " + addToStack(stack, 6));

    System.out.println("Stack size: " + stack.size());
    System.out.println("Stack top: " + stack.peek());

    while (!stack.isEmpty()) {
      System.out.println(stack.pop());
      System.out.println("Stack size: " + stack.size());
    }
  }


  private static void testQueue() {
//        Queue<Integer> queue = new QueueImpl<>(5);
    Queue<Integer> queue = new PriorityQueue<>(5);
    System.out.println(queue.insertTail(3));
    System.out.println(queue.insertTail(5));
    System.out.println(queue.insertTail(1));
    System.out.println("Queue is full: " + queue.isFull());
    System.out.println(queue.insertTail(2));
    System.out.println(queue.insertTail(6));
    System.out.println(queue.insertTail(4));

    System.out.println("Queue peek: " + queue.peekHead());
    System.out.println("Queue size: " + queue.size());
    System.out.println("Queue is full: " + queue.isFull());

    while (!queue.isEmpty()) {
      System.out.println(queue.removeHead());
    }
    System.out.println("Queue size: " + queue.size());
  }

  private static void testDeque() {
    System.out.println("testDeque");
    Deque<Integer> queue = new DequeImpl<>(5);

    fillDeque(queue);

    System.out.println("removeHead");
    while (!queue.isEmpty()) {
      System.out.println(queue.removeHead());
    }

    fillDeque(queue);

    System.out.println("removeTail");
    while (!queue.isEmpty()) {
      System.out.println(queue.removeTail());
    }

  }

  private static void fillDeque(Deque<Integer> queue) {
    System.out.println(queue.insertTail(5));
    System.out.println(queue.insertTail(7));
    System.out.println(queue.insertTail(1));
    System.out.println("Queue is full: " + queue.isFull());
    System.out.println(queue.insertHead(4));
    System.out.println(queue.insertHead(3));
    System.out.println(queue.insertTail(6));
    System.out.println("Queue peekHead: " + queue.peekHead());
    System.out.println("Queue peekTail: " + queue.peekTail());
    System.out.println("Queue size: " + queue.size());
    System.out.println("Queue is full: " + queue.isFull());
  }


  private static boolean addToStack(Stack<Integer> stack, int value) {
    if (!stack.isFull()) {
      stack.push(value);
      return true;
    }
    return false;
  }
}
