import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.text.DecimalFormat;
import java.io.*;

public class TwitchFriendships {

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

               // this gives us the weight to the shortest path in if its a weighted graph.
               distances[0][neighbor] = distances[0][minIndex] + adj[minIndex].get(j).getWeight();

               // this is setting the previous node
               distances[1][neighbor] = minIndex;
            }
         }

      }
      return distances;
   }

   static double calculateUserAverageSeparation(int[][] distances, int length, int source) {
      double totalSeparations = 0;

      for (int i = 0; i < length; i++) {
         if (i == source) {
            continue;
         }
         boolean sourceNodeNotFound = true;
         int prevNode = distances[1][i];

         int counter = 0;
         while (sourceNodeNotFound) {
            if (prevNode == source) {
               counter++;
               sourceNodeNotFound = false;
            } else {
               counter++;
               prevNode = distances[1][prevNode];
            }
         }
         totalSeparations += counter;
      }

      return totalSeparations / (length - 1);
   }

   public static WeightedGraph createFriendsGraph() {

      try (BufferedReader br = new BufferedReader(new FileReader("twitch_friendships.txt"))) {
         String line;
         int totalCount = Integer.parseInt(br.readLine());
         WeightedGraph friendsGraph = new WeightedGraph(totalCount);
         while ((line = br.readLine()) != null) {
            String[] friends = line.split(" ");
            int person = Integer.parseInt(friends[0]);
            int friend = Integer.parseInt(friends[1]);
            friendsGraph.addEdge(person, new Edge(friend));
            friendsGraph.addEdge(friend, new Edge(person));
         }
         return friendsGraph;

      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("An error has occured while trying to read file");
         return null;
      }

   }

   public static double computeAverageFriendCount(LinkedList<Edge>[] adjList) {

      double total = 0;
      double count = adjList.length;
      for (int i = 0; i < count; i++) {
         total += adjList[i].size();
      }

      return total / count;
   }

   public static void main(String[] args) {

      System.out.println("\n\nProcessing. Please Wait...\n");
      WeightedGraph graph = createFriendsGraph();
      int totalConnections = graph.getNumNodes();
      double individualAveragesTotal = 0;
      for (int i = 0; i < totalConnections; i++) {
         int[][] distances = dijkstra(graph, i);
         double individualAverage = calculateUserAverageSeparation(distances, totalConnections, i);
         individualAveragesTotal += individualAverage;
      }
      System.out.println("Average Twitch user separation = "
            + new DecimalFormat("#.##").format(individualAveragesTotal / totalConnections) + " degrees");
      System.out.println("Average Twitch user friend count = "
            + new DecimalFormat("#.##").format(computeAverageFriendCount(graph.getAdjList())) + " friends\n\n");

   }
}

class Edge {
   private int endNode;
   private int weight;

   public Edge(int endNode) {
      this.endNode = endNode;
      this.weight = 1;
   }

   public int getEndNode() {
      return this.endNode;
   }

   public int getWeight() {
      return this.weight;
   }
}

class WeightedGraph {
   private int N; // number of nodes in the graph
   private LinkedList<Edge> adj[]; // adjacency list for each node

   WeightedGraph(int numNodes) {
      N = numNodes;
      adj = new LinkedList[N]; // create an adjacency list for each node

      for (int i = 0; i < N; i++) {
         adj[i] = new LinkedList(); // create empty list of edges
      }
   }

   public void addEdge(int startNode, Edge edge) {
      adj[startNode].add(edge);
   }

   public int getNumNodes() {
      return N;
   }

   public LinkedList<Edge>[] getAdjList() {
      return adj;
   }

   public String toString() {
      String output = new String();

      for (int i = 0; i < N; i++) {
         output += i + ": ";

         ListIterator iter = adj[i].listIterator();

         while (iter.hasNext()) {
            Edge e = (Edge) iter.next();
            output += e.getEndNode() + " ";

         }
         output += "\n";
      }
      return output;
   }

}