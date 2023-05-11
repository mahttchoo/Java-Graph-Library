
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;
import java.util.Set;

public class Graph {
    int[][] adjMat;
    char[] vertexes;

    // Default Constructor
    Graph(int[][] a, char[] v) throws Exception {
        // Testing if matrix is squared
        for (int i = 0; i < a.length; i++) {
            if (a[i].length != a.length) {
                throw new Exception("\nError: Adjacency Matrix not square\nRow " + i + " doesn't match.");
            }
        }
        if (v.length != a.length) {
            throw new Exception("\nError: Vertex count is off.");
        }
        adjMat = a;
        vertexes = v;
    }

    // Constructor from .csv file
    Graph(String path) throws Exception {
        int v = 0;
        String line = "";
        File file = new File(path);
        Queue<String[]> data = new LinkedList<>();

        try {
            // BufferedReader reads the .csv a line at a time and gives a string, then the string is turned into an array, then this array is put into the matrix.
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);

                // Testing if matrix is squared
                // Tests if each array being inputted in the matrix matches in length
                if (values.length != data.peek().length) {
                    br.close();
                    throw new Exception("\nError: Adjacency Matrix not squared.\nDetected on Row " + v + ".");
                }
                v++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Testing if matrix is squared
        // v is the number of rows. Since all arrays are the same length (have the same number of columns), v must be equal to the number of columns.
        if (v != data.peek().length) {
            throw new Exception("\nError: Adjacency Matrix not squared. Number of rows and columns do not match. Check the .csv for spaces, it should only contain 1s, 0s, and commas.");
        }

        // Inputting the data from the .csv into the Graph variables.
        int[][] am = new int[v][v];
        char[] vs = new char[v];
        for (int row = 0; row < v; row++) {
            String[] s = data.remove();
            for (int col = 0; col < v; col++) {
                int n = (int)(s[col].charAt(0)) - 48; // Finding the char value of the 1 character string, then casting it as int, then subtracting 48 so it's its real value
                am[row][col] = n;
                vs[row] = (char)(row + 65); // A, B, C... etc
            }
        }
        adjMat = am;
        vertexes = vs;
    }

    char get(int i) {
        return vertexes[i];
    }

    int getSize() {
        return adjMat.length;
    }

    void printEdges() {
        int l = adjMat.length;

        // Printing list of vertexes on the top
        System.out.print("  ");
        for (int i = 0; i < l; i++) {
            System.out.print(vertexes[i] + " ");
        }
        System.out.println();

        // Printing actual data of adjacency matrix
        for (int row = 0; row < l; row++) {
            System.out.print(vertexes[row] + " ");
            for (int col = 0; col < l; col++) {
                System.out.print(adjMat[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    void BFS(int s) {
        int n = this.getSize();
        Boolean[] visited = new Boolean[n];
        Arrays.fill(visited, false);
        LinkedList<Edge> list = new LinkedList<Edge>();
        Queue<Integer> q = new LinkedList<>();
        String vertexTraversalInt = "";

        q.add(s);
        visited[s] = true;

        System.out.println("Breadth First Search Vertices Traversal Order:");
        while (!q.isEmpty()) {
            int v = q.remove();
            System.out.print((char)(v + 65) + " ");
            vertexTraversalInt = vertexTraversalInt + (v + 1) + " ";

            // For all unvisited neighbors of v, add to queue, mark as visited
            for (int i = 0; i < n; i++) {
                if (this.adjMat[v][i] == 1) {
                    if (visited[i] == false) {
                        Edge edge = new Edge(this.get(v), this.get(i));
                        list.add(edge);
                        q.add(i);
                        visited[i] = true;
                    }
                }
            }
        }
        System.out.println("\n" + vertexTraversalInt + "\n");

        System.out.println("Breadth First Search Edges Traversal Order:");
        for (int i = 0; i < list.size(); i++) {
            list.get(i).print();
        }
        System.out.println("\n");
    }

    void DFS(int s) {
        int n = this.getSize();
        Boolean[] visited = new Boolean[n];
        Arrays.fill(visited, false);
        Stack<Integer> stack = new Stack<Integer>();
        String vertexTraversalInt = "";

        stack.push(s);

        System.out.println("Depth First Search:");
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (visited[v]) { // Ignore the rest of the code if v was already used in part of another path
                continue;
            }
            visited[v] = true;
            System.out.print((char)(v + 65) + " ");
            vertexTraversalInt = vertexTraversalInt + (v + 1) + " ";

            // For all unvisited neighbors of v, add to stack from largest to smallest
            for (int i = (n - 1); i >= 0; i--) {
                if (this.adjMat[v][i] == 1) {
                    if (visited[i] == false) {
                        stack.push(i);
                    }
                }
            }
        }
        System.out.println("\n" + vertexTraversalInt + "\n");
    }

    // Assumes that the graph is connected
    void Dijkstra(int s, int e){
        int n = this.getSize();
        int[] distance = new int[n];
        int[] backPointer = new int[n];
        Arrays.fill(distance, 1000);

        if (s > n) {
            System.out.println("id entered is outside the range.");
            return;
        }

        distance[s] = 0;

        Set<Integer> set = new HashSet<Integer>();
        set.add(s);

        for (int i = 0; i < n; i++) {
            if (adjMat[s][i] == 1) {
                distance[i] = 1;
            }
        }

        System.out.println("Dijkstra Search Vertices Traversal Order: ");
        while (set.size() < vertexes.length) {
            for (int i = 0; i < n; i++) {
                if (distance[i] < 1000 && !set.contains(i)) {
                    set.add(i);
                    System.out.print((char)(i + 65) + " ");
                    for (int j = 0; j < n; j++) {
                        if (adjMat[i][j] == 1 && (distance[j] > distance[i] + 1)) {
                            distance[j] = distance[i] + 1;
                            backPointer[j] = i;
                        }
                    }
                    break;
                }
            }
        }
        System.out.println("\n");

        System.out.println("Distances from vertex " + vertexes[s] + ":");
        for (int i = 0; i < n; i++) {
            System.out.print(vertexes[i] + " = " + distance[i] + ", ");
        }
        System.out.println("\n");

        if (distance[e] < 1000) {
            System.out.println("Path from " + vertexes[s] + " to " + vertexes[e] + ":");
            Stack<Integer> stack = new Stack<Integer>();
            int c = e;
            stack.push(c);
            while (c != s) {
                c = backPointer[c];
                stack.push(c);
            }
            while (!stack.isEmpty()) {
                System.out.print(vertexes[stack.pop()] + " ");
            }
        }
        System.out.println("\n");
    }

    void insertEdge(boolean directed, int start, int end){
        adjMat[start][end] = 1;
        if (!directed) {
            adjMat[end][start] = 1;
        }
    }

    void insertVertex(){
        int newSize = adjMat.length + 1;
        int[][] newMat = new int[newSize][newSize];
        for (int i = 0; i < adjMat.length; i++) {
            for (int j = 0; j < adjMat.length; j++) {
                newMat[i][j] = adjMat[i][j];
            }
        }
        adjMat = newMat;

        char newVertex = (char)((int)vertexes[vertexes.length - 1] + 1);
        char[] newVertexes = new char[newSize];
        for (int i = 0; i < adjMat.length - 1; i++) {
            newVertexes[i] = vertexes[i];
        }
        newVertexes[newSize - 1] = newVertex;
        vertexes = newVertexes;

        System.out.println("New vertex " + newVertex + " added at id " + (newSize - 1) + ".\n");
    }
}
