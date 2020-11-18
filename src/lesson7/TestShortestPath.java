package lesson7;

public class TestShortestPath {

  public static void main(String[] args) {
    findShortestPath();
  }

  private static void findShortestPath() {
    MyGraph graph = new MyGraph(10);
    graph.addVertex("Москва");
    graph.addVertex("Тула");
    graph.addVertex("Липецк");
    graph.addVertex("Воронеж");
    graph.addVertex("Рязань");
    graph.addVertex("Тамбов");
    graph.addVertex("Саратов");
    graph.addVertex("Калуга");
    graph.addVertex("Орел");
    graph.addVertex("Курск");

    graph.addEdges("Москва", "Тула", "Рязань", "Калуга");
    graph.addEdges("Тула", "Липецк");
    graph.addEdges("Рязань", "Тамбов");
    graph.addEdges("Калуга", "Орел");
    graph.addEdges("Липецк", "Воронеж");
    graph.addEdges("Тамбов", "Саратов");
    graph.addEdges("Орел", "Курск");
    graph.addEdges("Саратов", "Воронеж");
    graph.addEdges("Курск", "Воронеж");

    //graph.display();

    graph.printShortestPath("Москва", "Воронеж");


  }
}
