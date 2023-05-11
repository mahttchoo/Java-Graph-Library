This code has a Breadth First Search, Depth First Search, and Dijkstra's shortest path algorithm that are used on a Graph class. Both are written iteratively.
The Graph class is written using an adjacency matrix. Both undirected and directed graphs work.
The code in Main constructs the graphs by reading a .csv file (although Graph also accepts hard coded adjacency matrices).
- graph1: v=5, undirected
- graph2: v=11, undirected
- graph3: v=63, undirected
- graph4: v=6, directed
- graph5: v=12, directed

You are welcome to upload your own graphs in a .csv file, format it like the 5 examples. No trailing commas at the end of each row and no spaces.
I would recommend using https://graphonline.ru/en/ to generate a matrix, just format it before using here.
Graphonline has a BFS, DFS, and a Dijkstra search that you can use to test against mine.

BFS outputs the vertices and edges it traversed.
DFS outputs the the vertices it traversed.
Dijkstra outputs the vertices it traversed, the distances it assigned to each vertex, and the the shortest path between two vertices. #Dijkstra is written assuming that graphs are complete.
insertEdge inserts an edge between a start and an end vertex, either directed or undirected.
insertVertex adds a new vertex on the edge of the adjacency matrix.
Both BFS and DFS have a time complexity of O(N^2).
