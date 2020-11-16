package lesson6;

public class Test {

  public static void main(String[] args) {
//    testTree();
    testRemoveElement();
  }

  private static void testRemoveElement() {
    MyTree<Integer> tree = new MyTreeImpl<>();
    tree.add(60);
    tree.add(25);
    tree.add(66);
    tree.add(15);
    tree.add(5);
    tree.add(20);
    tree.add(45);
    tree.add(30);
    tree.add(55);
    tree.add(32);
    tree.add(63);
    tree.add(69);
//    tree.add(62);
//    tree.add(65);
//    tree.add(67);
//    tree.add(71);
    tree.display();
    System.out.println("Сбалансировано? - " + tree.isBalanced());
    // tree.remove(25);
    //tree.display();
  }

  private static void testTree() {
    MyTree<Integer> tree = new MyTreeImpl<>();
    tree.add(60);
    tree.add(50);
    tree.add(66);
    tree.add(40);
    tree.add(55);
    tree.add(70);
    tree.add(31);
    tree.add(45);
    tree.add(67);
    tree.add(81);

    System.out.println("Find 70: " + tree.contains(70));
    System.out.println("Find 700: " + tree.contains(700));

    //tree.display();
//    System.out.println("IN_ORDER -----------------------------------------");
//    tree.traverse(MyTree.TraverseMode.IN_ORDER, System.out::println);
//    System.out.println("PRE_ORDER -----------------------------------------");
//    tree.traverse(MyTree.TraverseMode.PRE_ORDER, System.out::println);
//    System.out.println("POST_ORDER -----------------------------------------");
//    tree.traverse(MyTree.TraverseMode.POST_ORDER, System.out::println);
//    System.out.println("REVERS_ORDER -----------------------------------------");
//    tree.traverse(TraverseMode.REVERS_ORDER, System.out::println);
  }
}
