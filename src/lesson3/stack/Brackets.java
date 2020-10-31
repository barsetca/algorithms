package lesson3.stack;

import java.util.HashMap;
import java.util.Map;

public class Brackets {

  private static final String TEXT = " public void push(E value) {\n"
      + "    data[size++ = value;\n"
      + "  }";

  private static final Map<Character, Character> BRACKETS = new HashMap<>() {{
    put('{', '}');
    put('[', ']');
    put('(', ')');
  }};

  public static void main(String[] args) {
    new Brackets(TEXT).check();
  }

  private final String textToCheck;

  public Brackets(String textToCheck) {
    this.textToCheck = textToCheck;
  }

  public void check() {
    Stack<Character> stack = new StackImpl<>(textToCheck.length());
    checkDelimiter(stack);
  }

  private void checkDelimiter(Stack<Character> stack) {
    for (int i = 0; i < textToCheck.length(); i++) {
      char currentChar = textToCheck.charAt(i);
      switch (currentChar) {
        case '{':
        case '[':
        case '(':
          stack.push(currentChar);
          break;
        case '}':
        case ']':
        case ')':
          if (stack.isEmpty()) {
            System.err.println("Error: " + currentChar + " at " + i + " index");
            break;
          }
          Character lastOpenedBracket = stack.pop();
          if (currentChar != BRACKETS.get(lastOpenedBracket)) {
            System.err.println("Error: current bracket - " + currentChar + " at " + i + " index "
                + " somewhere missing " + BRACKETS.get(lastOpenedBracket));
          }
          break;
        default:
          break;
      }
    }
    if (!stack.isEmpty()) {
      System.err.println("Error: missing right delimiter");
    }
  }
}
