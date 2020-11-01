package lesson3.queue;

import lesson3.stack.Stack;
import lesson3.stack.StackImpl;

public class Revers {

  private static final String TEXT = " public void push(E value) {\n"
      + "    data[size++ = value :sreveR\n";

  public static void main(String[] args) {
    new Revers(TEXT).revers();
  }

  private final String textToRevers;


  public Revers(String textToRevers) {
    this.textToRevers = textToRevers;
  }

  public void revers() {
    Stack<Character> stack = new StackImpl<>(textToRevers.length());
    displayText(stack);
  }

  private void displayText(Stack<Character> stack) {
    for (int i = 0; i < textToRevers.length(); i++) {
      char currentChar = textToRevers.charAt(i);
      stack.push(currentChar);
    }
    for (int i = 0; i < textToRevers.length(); i++) {
      System.out.print(stack.pop());
    }
  }
}
