package lesson6;

import java.util.Stack;
import java.util.function.Consumer;

public class MyTreeImpl<E extends Comparable<? super E>> implements MyTree<E> {

  private int size;
  private int maxHeight;

  private MyNode<E> root;

  public MyTreeImpl() {
    this(0);
  }

  public MyTreeImpl(int maxHeight) {
    this.maxHeight = maxHeight;
  }

  @Override
  public boolean add(E value) {
    MyNode<E> newNode = new MyNode<>(value);

    if (isEmpty()) {
      root = newNode;
      size++;
      return true;
    }

    NodeAndParent nodeAndParent = doFind(value);
    if (maxHeight != 0 && nodeAndParent.height > maxHeight) {
      return false;
    }
    MyNode<E> parent = nodeAndParent.parent;
    if (parent == null || nodeAndParent.current != null) {
      return false;
    }
    parent.addChild(newNode);
    size++;
    return true;
  }

  @Override
  public boolean contains(E value) {
    NodeAndParent nodeAndParent = doFind(value);
    return nodeAndParent.current != null;
  }

  @Override
  public boolean remove(E value) {
    NodeAndParent nodeAndParent = doFind(value);
    MyNode<E> removedNode = nodeAndParent.current;
    MyNode<E> parent = nodeAndParent.parent;

    if (removedNode == null) {
      return false;
    }
    if (removedNode.isLeaf()) {
      if (removedNode == root) {
        root = null;
      } else if (parent.isRightChild(value)) {
        parent.setRightChild(null);
      } else {
        parent.setLeftChild(null);
      }
    } else if (removedNode.hasOnlyOneChild()) {
      MyNode<E> child = removedNode.getLeftChild() != null ?
          removedNode.getLeftChild() : removedNode.getRightChild();

      if (removedNode == root) {
        root = child;
      } else if (parent.isRightChild(value)) {
        parent.setRightChild(child);
      } else {
        parent.setLeftChild(child);
      }
    } else {
      MyNode<E> successor = getSuccessor(removedNode);
      if (removedNode == root) {
        root = successor;
      } else if (parent.isRightChild(value)) {
        parent.setRightChild(successor);
      } else {
        parent.setLeftChild(successor);
      }
      successor.setLeftChild(removedNode.getLeftChild());
    }
    size--;
    return true;
  }

  private MyNode<E> getSuccessor(MyNode<E> removedNode) {
    MyNode<E> successor = removedNode;
    MyNode<E> successorParent = null;
    MyNode<E> current = removedNode.getRightChild();
    while (current != null) {
      successorParent = successor;
      successor = current;
      current = current.getLeftChild();
    }
    if (successor != removedNode.getRightChild() && successorParent != null) {
      successorParent.setLeftChild(successor.getRightChild());
      successor.setRightChild(removedNode.getRightChild());
    }
    return successor;

  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void traverse(TraverseMode mode, Consumer<E> action) {
    switch (mode) {
      case IN_ORDER:
        inOrder(root, action);
        break;
      case PRE_ORDER:
        preOrder(root, action);
        break;
      case POST_ORDER:
        postOrder(root, action);
        break;
      case REVERS_ORDER:
        reverseOrder(root, action);
        break;
      default:
        throw new IllegalArgumentException("Unknown mode: " + mode);
    }

  }

  private void reverseOrder(MyNode<E> current, Consumer<E> action) {
    if (current == null) {
      return;
    }
    reverseOrder(current.getRightChild(), action);
    action.accept(current.getValue());
    reverseOrder(current.getLeftChild(), action);
  }

  private void postOrder(MyNode<E> current, Consumer<E> action) {
    if (current == null) {
      return;
    }
    postOrder(current.getLeftChild(), action);
    postOrder(current.getRightChild(), action);
    action.accept(current.getValue());
  }

  private void preOrder(MyNode<E> current, Consumer<E> action) {
    if (current == null) {
      return;
    }
    action.accept(current.getValue());
    preOrder(current.getLeftChild(), action);
    preOrder(current.getRightChild(), action);

  }

  private void inOrder(MyNode<E> current, Consumer<E> action) {
    if (current == null) {
      return;
    }
    inOrder(current.getLeftChild(), action);
    action.accept(current.getValue());
    inOrder(current.getRightChild(), action);
  }

  @Override
  public void display() {
    Stack<MyNode<E>> globalStack = new Stack<>();
    globalStack.push(root);
    int nBlanks = 64;

    boolean isRowEmpty = false;
    System.out.println("................................................................");

    while (!isRowEmpty) {
      Stack<MyNode<E>> localStack = new Stack<>();

      isRowEmpty = true;
      for (int i = 0; i < nBlanks; i++) {
        System.out.print(" ");
      }

      while (!globalStack.isEmpty()) {
        MyNode<E> tempNode = globalStack.pop();
        if (tempNode != null) {
          System.out.print(tempNode.getValue());
          localStack.push(tempNode.getLeftChild());
          localStack.push(tempNode.getRightChild());
          if (tempNode.getLeftChild() != null || tempNode.getRightChild() != null) {
            isRowEmpty = false;
          }
        } else {
          System.out.print("--");
          localStack.push(null);
          localStack.push(null);
        }
        for (int j = 0; j < nBlanks * 2 - 2; j++) {
          System.out.print(" ");
        }
      }

      System.out.println();

      while (!localStack.isEmpty()) {
        globalStack.push(localStack.pop());
      }

      nBlanks /= 2;
    }
    System.out.println("................................................................");
  }

  private NodeAndParent doFind(E value) {
    MyNode<E> previous = null;
    MyNode<E> current = root;
    int height = 1;

    while (current != null) {
      height++;
      if (current.getValue().equals(value)) {
        return new NodeAndParent(current, previous, height);
      }
      previous = current;
      if (current.isRightChild(value)) {
        current = current.getRightChild();
      } else {
        current = current.getLeftChild();
      }
    }

    return new NodeAndParent(null, previous, height);
  }

  public boolean isBalanced() {
    return isBalanced(root);
  }


  private boolean isBalanced(MyNode node) {
    return (node == null) ||
        isBalanced(node.getLeftChild()) &&
            isBalanced(node.getRightChild()) &&
            Math.abs(height(node.getLeftChild()) - height(node.getRightChild())) <= 1;
  }

  private static int height(MyNode node) {
    return node == null ? 0
        : 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
  }

  private class NodeAndParent {

    private MyNode<E> current;
    private MyNode<E> parent;
    private int height;

    public NodeAndParent(MyNode<E> current, MyNode<E> parent, int height) {
      this.current = current;
      this.parent = parent;
      this.height = height;
    }
  }
}
