package lesson8;

import java.util.LinkedList;

public class LinkedHashTableImpl<K, V> implements HashTable<K, V> {


  private final int maxSize;
  private LinkedList<Node<K, V>>[] data;
  private int size;
  private int arraySize;

  static class Node<K, V> implements Entry<K, V> {

    private final K key;
    private V value;

    public Node(K key, V value) {
      this.key = key;
      this.value = value;
    }

    @Override
    public K getKey() {
      return key;
    }

    @Override
    public V getValue() {
      return value;
    }

    @Override
    public void setValue(V value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return "Node{" +
          "key=" + key +
          ", value=" + value +
          '}';
    }
  }

  public LinkedHashTableImpl(int maxSize) {
    this.maxSize = maxSize;
    this.arraySize = maxSize / 2 + 1;
    this.data = new LinkedList[arraySize];
    for (int i = 0; i < arraySize; i++) {
      this.data[i] = new LinkedList<>();
    }
  }

  @Override
  public boolean put(K key, V value) {
    if (size == maxSize) {
      return false;
    }
    LinkedList<Node<K, V>> listForChange = data[hash(key)];
    Node<K, V> forPut = new Node<>(key, value);
    if (listForChange.isEmpty()) {
      listForChange.add(forPut);
      size++;
      return true;
    }
    for (Node<K, V> node : listForChange) {
      if (node.key.equals(key)) {
        node.setValue(value);
        return true;
      }
    }
    listForChange.add(forPut);
    size++;
    return true;
  }

  private int hash(K key) {
    return key.hashCode() % data.length;
  }

  @Override
  public V get(K key) {
    LinkedList<Node<K, V>> listForFind = data[hash(key)];
    for (Node<K, V> node : listForFind) {
      if (node.key.equals(key)) {
        return node.value;
      }
    }
    return null;
  }

  @Override
  public V remove(K key) {
    LinkedList<Node<K, V>> listForFind = data[hash(key)];
    for (Node<K, V> node : listForFind) {
      if (node.key.equals(key)) {
        V value = node.value;
        listForFind.remove(node);
        size--;
        return value;
      }
    }
    return null;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public void display() {
    System.out.println("----------");
    for (int i = 0; i < data.length; i++) {
      System.out.print(i + " = ");
      data[i].forEach(n -> System.out.print(n + " "));
      System.out.println();
    }
    System.out.println("----------");
  }
}
