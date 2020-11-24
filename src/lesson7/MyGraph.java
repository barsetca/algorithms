package lesson7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class MyGraph {

  private final List<Vertex> vertexList;
  private final boolean[][] adjMat;

  private int countSteps;

  public MyGraph(int maxVertexCount) {
    this.vertexList = new ArrayList<>(maxVertexCount);
    this.adjMat = new boolean[maxVertexCount][maxVertexCount];
  }

  public void addVertex(String label) {
    vertexList.add(new Vertex(label));
  }

  public void addEdge(String startLabel, String endLabel) {
    int startIndex = indexOf(startLabel);
    int endIndex = indexOf(endLabel);

    if (startIndex == -1 || endIndex == -1) {
      throw new IllegalArgumentException("One of the label not exists in vertexList");
    }
    adjMat[startIndex][endIndex] = true;
    adjMat[endIndex][startIndex] = true;
  }

  public void addEdges(String startLabel, String secondLabel, String... others) {
    addEdge(startLabel, secondLabel);
    for (String label : others) {
      addEdge(startLabel, label);
    }
  }

  private int indexOf(String vertexLabel) {
    for (int i = 0; i < vertexList.size(); i++) {
      if (vertexLabel.equals(vertexList.get(i).getLabel())) {
        return i;
      }
    }
    return -1;
  }

  public int getVertexSize() {
    return vertexList.size();
  }

  public void display() {
    for (int i = 0; i < getVertexSize(); i++) {
      System.out.print(vertexList.get(i));
      for (int j = 0; j < getVertexSize(); j++) {
        if (adjMat[i][j]) {
          System.out.print(" -> " + vertexList.get(j));
        }
      }
      System.out.println();
    }
  }

  /**
   * англ. Depth-first search, DFS
   *
   * @param startLabel
   */
  public void dfs(String startLabel) {

    int startIndex = getStartIndex(startLabel);

    Vertex startVertex = vertexList.get(startIndex);
    Stack<Vertex> stack = new Stack<>();
    System.out.println(startVertex);
    visitVertex(startVertex, stack);

    while (!stack.isEmpty()) {
      Vertex nearVertex = findNearUnvisitedVertex(stack.peek());
      if (nearVertex != null) {
        System.out.println(nearVertex);
        visitVertex(nearVertex, stack);
      } else {
        stack.pop();
      }
    }
    resetVertexState();
  }

  /**
   * англ. breadth-first search, BFS
   *
   * @param startLabel
   */
  public void bfs(String startLabel) {

    int startIndex = getStartIndex(startLabel);

    Vertex startVertex = vertexList.get(startIndex);
    Queue<Vertex> queue = new LinkedList<>();
    System.out.println(startVertex);
    visitVertex(startVertex, queue);
    while (!queue.isEmpty()) {
      Vertex nearVertex = findNearUnvisitedVertex(queue.peek());
      if (nearVertex != null) {
        System.out.println(nearVertex);
        visitVertex(nearVertex, queue);
      } else {
        queue.remove();
      }
    }
    resetVertexState();
  }

  private int getStartIndex(String startLabel) {
    int startIndex = indexOf(startLabel);
    if (startIndex == -1) {
      throw new IllegalArgumentException("One of the label not exists in vertexList");
    }
    return startIndex;
  }

  private void resetVertexState() {
    for (Vertex vertex : vertexList) {
      vertex.setVisited(false);
      vertex.setPrevious(null);
    }
    countSteps = 0;
  }

  private Vertex findNearUnvisitedVertex(Vertex peek) {
    int indexPeek = indexOf(peek.getLabel());
    for (int i = 0; i < vertexList.size(); i++) {
      Vertex checked = vertexList.get(i);
      if (adjMat[indexPeek][i] && !checked.getVisited()) {
        return checked;
      }
    }
    return null;
  }

  private void visitVertex(Vertex vertex, Stack<Vertex> stack) {
    System.out.println(vertex);
    stack.push(vertex);
    vertex.setVisited(true);
  }

  private void visitVertex(Vertex vertex, Queue<Vertex> queue) {
    queue.add(vertex);
    vertex.setVisited(true);
  }

  public void printShortestPath(String startLabel, String endLabel) {

    if (startLabel.equals(endLabel)) {
      System.out
          .println("Мы находимся в конечной точке. Количество необходимых шагов = " + countSteps);
      return;
    }

    int startIndex = indexOf(startLabel);
    int endIndex = indexOf(endLabel);

    if (startIndex == -1 || endIndex == -1) {
      throw new IllegalArgumentException("One of the label not exists in vertexList");
    }
    Vertex startVertex = vertexList.get(startIndex);
    Queue<Vertex> queue = new LinkedList<>();
    visitVertex(startVertex, queue);
    while (!queue.isEmpty()) {
      Vertex peek = queue.peek();
      Vertex nearVertex = findNearUnvisitedVertex(peek);
      if (nearVertex != null) {
        nearVertex.setPrevious(peek);
        if (endLabel.equals(nearVertex.getLabel())) {
          System.out.println("\n---------------------------------------------------------------");
          printPath(nearVertex);
          System.out.println("\n---------------------------------------------------------------\n");
          break;
        }
        visitVertex(nearVertex, queue);
      } else {
        queue.remove();
      }
    }
    resetVertexState();

  }

  private void printPath(Vertex vertex) {

    if (vertex.getPrevious() == null) {

      System.out.printf("Крачайший путь (%d шага(ов)) : %n", countSteps);
      System.out.print(vertex.getLabel());

      return;
    }
    countSteps++;
    printPath(vertex.getPrevious());
    System.out.print(" -> ");
    System.out.print(vertex.getLabel());
  }
}
