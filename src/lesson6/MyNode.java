package lesson6;

public class MyNode<T extends Comparable<? super T>> {

  private final T value;

  private MyNode<T> leftChild;
  private MyNode<T> rightChild;

  private int level;

  public MyNode(T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }

  public MyNode<T> getLeftChild() {
    return leftChild;
  }

  public void setLeftChild(MyNode<T> leftChild) {
    this.leftChild = leftChild;
  }

  public MyNode<T> getRightChild() {
    return rightChild;
  }

  public void setRightChild(MyNode<T> rightChild) {
    this.rightChild = rightChild;
  }

  public boolean isLeaf() {
    return leftChild == null && rightChild == null;
  }

  public boolean isRightChild(T value) {
    return value.compareTo(this.value) > 0;
  }

  public void addChild(MyNode<T> newChild) {
    if (isRightChild(newChild.getValue())) {
      setRightChild(newChild);
    } else {
      setLeftChild(newChild);
    }
  }

  public boolean hasOnlyOneChild() {
    return leftChild == null ^ rightChild == null;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }
}
