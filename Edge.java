public class Edge {
    char startVertex;
    char endVertex;

    Edge(char a, char b) {
        startVertex = a;
        endVertex = b;
    }

    public void print() {
        System.out.print("(" + startVertex + ", " + endVertex + ")");
    }
}
