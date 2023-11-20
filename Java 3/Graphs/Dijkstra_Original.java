public class Dijkstra_Original {

   static int[] dijkstra(int graph[][], int source) {
      int N = graph.length;
      // denotes shortest distance from source node to all other nodes
      // this is going to have to change to distances[][N], with N reflecting previous
      // node info
      int distances[] = new int[N];
      // indicates if the node has been visited or not (defaults to false)
      boolean visited[] = new boolean[N];

      // initialize shortest distance to all nodes as "infinity"
      for (int i = 0; i < N; i++)
         distances[i] = Integer.MAX_VALUE;

      distances[source] = 0; // distance from source node to itself is 0

      // find shortest path to all nodes
      for (int count = 0; count < N - 1; count++) {
         // choose the minimum distance node from the set of nodes
         // not yet visited
         int min = Integer.MAX_VALUE;
         int minIndex = -1;

         for (int i = 0; i < N; i++) {
            if (!visited[i] && distances[i] <= min) {
               min = distances[i];
               minIndex = i;
            }
         }

         // mark the minimum distance node as visited
         visited[minIndex] = true;

         for (int i = 0; i < N; i++) {
            // Update distances only if node has not been visited,
            // there is an edge from minimum distance node to node i,
            // and the total length of path from source to node i
            // via minimum distance node is smaller than current value
            // stored in distances
            if (!visited[i] && graph[minIndex][i] != 0 &&
                  distances[minIndex] + graph[minIndex][i] < distances[i])

               distances[i] = distances[minIndex] + graph[minIndex][i];
         }
      }

      return distances;
   }

   // going to need to alter to included distances[][]. Needs to print a 3rd column
   // with previous node.
   static void printDistances(int distances[], char[] nodes) {
      int N = distances.length;
      System.out.println("Node  Distance");

      for (int i = 0; i < N; i++)
         System.out.println(nodes[i] + "\t\t" + distances[i]);
   }

   public static void main(String[] args) {
      char[] nodeNames = { 'A', 'B', 'C', 'D', 'E' }; // Nodes 0, 1, 2, 3, 4
      int graph[][] = new int[][] { { 0, 6, 0, 1, 0 },
            { 6, 0, 5, 2, 2 },
            { 0, 5, 0, 0, 5 },
            { 1, 2, 0, 0, 1 },
            { 0, 2, 5, 1, 0 } };

      int distances[] = dijkstra(graph, 0);

      printDistances(distances, nodeNames);
   }
}