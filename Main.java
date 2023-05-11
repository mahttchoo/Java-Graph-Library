public class Main {
    public static void main(String args[]) throws Exception {
        Graph graph = new Graph("graph1.csv");
        graph.printEdges();
        graph.BFS(0);
        graph.DFS(0);

        graph.insertVertex(); // Remove this line on the bigger graphs or Dijkstra will not work. Dijkstra assumes graph is connected.
        graph.printEdges();

        graph.insertEdge(false, 5, 1);
        graph.insertEdge(true, 5, 4);
        graph.printEdges();
        graph.BFS(5);
        graph.DFS(5);
        graph.Dijkstra(0, 5);
    }
}

// SOURCES USED:
// https://graphonline.ru/en/
// https://rollbar.com/guides/java/how-to-throw-exceptions-in-java/
// https://www.geeksforgeeks.org/multidimensional-arrays-in-java/
// https://www.geeksforgeeks.org/queue-interface-java/
// https://www.youtube.com/watch?v=-Aud0cDh-J8&ab_channel=AlexLee