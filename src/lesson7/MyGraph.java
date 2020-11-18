package lesson7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class MyGraph {

  private final List<Vertex> vertexList;
  private final boolean[][] adjMat;

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
    visitVertex(startVertex, stack);

    while (!stack.isEmpty()) {
      Vertex nearVertex = findNearUnvisitedVertex(stack.peek());
      if (nearVertex != null) {
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
    Queue<Vertex> stack = new LinkedList<>();
    visitVertex(startVertex, stack);
    while (!stack.isEmpty()) {
      Vertex nearVertex = findNearUnvisitedVertex(stack.peek());
      if (nearVertex != null) {
        visitVertex(nearVertex, stack);
      } else {
        stack.remove();
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
    }
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
    System.out.println(vertex);
    queue.add(vertex);
    vertex.setVisited(true);
  }

}
