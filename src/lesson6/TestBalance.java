package lesson6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestBalance {

  public static final int MAX_HEIGHT = 4;
  public static final int MAX_NUMBER_OF_ELEMENT = (int) Math.pow(2, MAX_HEIGHT) - 1;
  public static final int MAX_NUMBER_OF_TREE = 20;
  public static List<MyTree<Integer>> trees = new ArrayList<>();
  public static final Integer MIN_VALUE = -25;
  public static final Integer MAX_VALUE = 25;


  public static void main(String[] args) {
    fillTreeList();
    printBalancePercent();
     }

  private static void printBalancePercent() {
    int countBalance = 0;
    for (MyTree<Integer> tree : trees) {
      if (tree.isBalanced()) {
        countBalance++;
        tree.display();
      }
    }
    System.out.printf(
        "Из %d деревьев сбалансировано %f %%%n", MAX_NUMBER_OF_TREE,
        (countBalance * 1.0 / trees.size()) * 100);
  }

  private static void fillTreeList() {
    for (int i = 0; i < MAX_NUMBER_OF_TREE; i++) {
      trees.add(fillTree(new MyTreeImpl<>(MAX_HEIGHT)));
    }
  }

  private static MyTreeImpl<Integer> fillTree(MyTreeImpl<Integer> integerMyTree) {
    Random random = new Random();
    for (int i = 0; i < MAX_NUMBER_OF_ELEMENT; i++) {
      integerMyTree.add(MIN_VALUE + random.nextInt(MAX_VALUE - MIN_VALUE + 1));
    }
    return integerMyTree;
  }
}


