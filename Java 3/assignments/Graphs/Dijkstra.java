import java.util.LinkedList;
import java.util.Queue;
import java.util.ListIterator;

public class Dijkstra {

   static int[][] dijkstra(WeightedGraph graph, int source) {
      int N = graph.getNumNodes();
      // denotes shortest distance from source node to all other nodes, as well as
      // previous node shortest path for each node. distances[0][N] represents the row
      // of shortests distances.
      // distances [1][N] shows the previous node that gave us the shortest path for
      // each node.
      int[][] distances = new int[2][N];
      // indicates if the node has been visited or not (defaults to false)
      boolean[] visited = new boolean[N];

      // adj List
      LinkedList<Edge>[] adj = graph.getAdjList();

      // initialize shortest distance to all nodes as "infinity"
      for (int i = 0; i < N; i++) {
         distances[0][i] = Integer.MAX_VALUE;
      }
      distances[0][source] = 0; // distance from source node to itself is 0

      // find shortest path to all nodes
      for (int count = 0; count < N; count++) {
         // choose the minimum distance node from the set of nodes
         // not yet visited
         int min = Integer.MAX_VALUE;
         // thisis finding which node of available nodes is the closest to the source. So
         // if the source isnt "A", find what is the source
         int minIndex = -1;

         for (int i = 0; i < N; i++) {
            if (!visited[i] && distances[0][i] <= min) {
               min = distances[0][i];
               minIndex = i;
            }
         }
         // System.out.println("Visiting Node " + minIndex);
         // mark the minimum distance node as visited
         visited[minIndex] = true;

         for (int j = 0; j < adj[minIndex].size(); j++) {
            // Update distances only if node has not been visited,
            // there is an edge from minimum distance node to node i,
            // and the total length of path from source to node i
            // via minimum distance node is smaller than current value
            // stored in distances
            int neighbor = adj[minIndex].get(j).getEndNode();

            if (!visited[neighbor]
                  && (distances[0][minIndex] + adj[minIndex].get(j).getWeight()) < distances[0][neighbor]) {

               // in here, we need to set the shortest distance from source node, as well as
               // the previous node that gave us that information

               // this gives us the weight to the shortest path.
               distances[0][neighbor] = distances[0][minIndex] + adj[minIndex].get(j).getWeight();

               // this is setting the previous node
               distances[1][neighbor] = minIndex;
            }
         }

      }
      return distances;
   }

   static void printDistances(int distances[][], char[] nodes) {
      int N = nodes.length;
      for (int i = 0; i < N; i++)
         if (distances[0][i] == 0) {
            System.out.println(nodes[i] + "\t" + distances[0][i]);
         } else {
            System.out.println(nodes[i] + "\t" + distances[0][i] + "\t" + nodes[distances[1][i]]);
         }

   }

   static void printPaths(int[][] distances, char[] nodes, int source) {
      int N = nodes.length;

      for (int i = 0; i < N; i++) {
         System.out.print("Shortest path from Node " + nodes[source] + " to Node " + nodes[i] + " is: ");
         boolean sourceNodeNotFound = true;
         StringBuilder path = new StringBuilder();
         int prevNode = distances[1][i];
         path.append(" ," + nodes[i]);
         int counter = 0;
         while (sourceNodeNotFound) {
            if (prevNode == source) {
               counter++;
               path.append(" ," + nodes[prevNode]);
               sourceNodeNotFound = false;
            } else {
               counter++;
               path.append(" ," + nodes[prevNode]);
               prevNode = distances[1][prevNode];
            }
         }
         System.out.println(path.reverse().substring(0, path.length() - 2) + "  Degrees of sep = " + counter);
      }
   }

   public static void main(String[] args) {
      char[] nodeNames = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' }; // Nodes 0, 1, 2, 3, 4, 5, 6
      int numNodes = nodeNames.length;

      WeightedGraph graph = new WeightedGraph(numNodes, nodeNames);
      // A
      graph.addEdge(0, new Edge(1, 1));
      graph.addEdge(0, new Edge(3, 1));
      // B
      graph.addEdge(1, new Edge(0, 1));
      graph.addEdge(1, new Edge(2, 1));
      // C
      graph.addEdge(2, new Edge(1, 1));
      graph.addEdge(2, new Edge(3, 4));
      graph.addEdge(2, new Edge(6, 5));
      // D
      graph.addEdge(3, new Edge(0, 1));
      graph.addEdge(3, new Edge(2, 4));
      graph.addEdge(3, new Edge(4, 4));
      graph.addEdge(3, new Edge(5, 6));
      // E
      graph.addEdge(4, new Edge(5, 3));
      graph.addEdge(4, new Edge(3, 4));
      // F
      graph.addEdge(5, new Edge(3, 6));
      graph.addEdge(5, new Edge(4, 3));
      // G
      graph.addEdge(6, new Edge(2, 5));
      System.out.println();
      System.out.println(graph);
      System.out.println("----------------");

      int[][] distances = dijkstra(graph, 6);

      printDistances(distances, nodeNames);
      System.out.println("----------------");
      System.out.println();

      printPaths(distances, nodeNames, 6);
   }
}

class Edge {
   private int endNode;
   private int weight;

   public Edge(int endNode, int weight) {
      this.endNode = endNode;
      this.weight = weight;
   }

   public int getWeight() {
      return this.weight;
   }

   public int getEndNode() {
      return this.endNode;
   }
}

class WeightedGraph {
   private int N; // number of nodes in the graph
   private char[] names; // names of each node
   private LinkedList<Edge> adj[]; // adjacency list for each node

   WeightedGraph(int numNodes, char[] nodeNames) {
      N = numNodes;
      adj = new LinkedList[N]; // create an adjacency list for each node
      names = new char[N];

      for (int i = 0; i < N; i++) {
         adj[i] = new LinkedList(); // create empty list of edges
         names[i] = nodeNames[i]; // name the current node
      }
   }

   public void addEdge(int startNode, Edge edge) {
      adj[startNode].add(edge);
   }

   public int getNumNodes() {
      return N;
   }

   public char[] getNodeNames() {
      return names;
   }

   public LinkedList<Edge>[] getAdjList() {
      return adj;
   }

   public String toString() {
      String output = new String();

      for (int i = 0; i < N; i++) {
         output += names[i] + ": ";

         ListIterator iter = adj[i].listIterator();

         while (iter.hasNext()) {
            Edge e = (Edge) iter.next();
            output += names[(int) e.getEndNode()] + ":" + e.getWeight() + "  ";

         }
         output += "\n";
      }
      return output;
   }
}