package lesson7;

public class Main7 {

  public static void main(String[] args) {
//        testGraph();
//        testDfs();
    testBfs();
  }

  private static void testGraph() {
    MyGraph graph = new MyGraph(4);
    graph.addVertex("A");
    graph.addVertex("B");
    graph.addVertex("C");
    graph.addVertex("D");

    graph.addEdges("A", "B", "C");
    graph.addEdges("B", "A", "C", "D");
    graph.addEdges("C", "A", "B", "D");
    graph.addEdges("D", "B", "C");

    System.out.println("Size of graph is " + graph.getVertexSize());
    graph.display();
  }

  private static void testDfs() {
    MyGraph graph = new MyGraph(7);
    graph.addVertex("A");
    graph.addVertex("B");
    graph.addVertex("C");
    graph.addVertex("D");
    graph.addVertex("E");
    graph.addVertex("F");
    graph.addVertex("G");

    graph.addEdges("A", "B", "C", "D");
    graph.addEdges("B", "E");
    graph.addEdges("D", "F");
    graph.addEdges("F", "G");

    graph.dfs("A");
    //A B E C D F G
  }


  private static void testBfs() {
    MyGraph graph = new MyGraph(8);
    graph.addVertex("A");
    graph.addVertex("B");
    graph.addVertex("C");
    graph.addVertex("D");
    graph.addVertex("E");
    graph.addVertex("F");
    graph.addVertex("G");
    graph.addVertex("H");

    graph.addEdges("A", "B", "C", "D");
    graph.addEdges("B", "E");
    graph.addEdges("E", "H");
    graph.addEdges("C", "F");
    graph.addEdges("D", "G");

    graph.bfs("A");
  }
}
