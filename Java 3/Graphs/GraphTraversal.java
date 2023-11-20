import java.util.LinkedList;
import java.util.Queue;
import java.util.ListIterator;

class UnweightedGraph {
   private int N; // number of nodes in the graph
   private char[] names; // names of each node
   private LinkedList<Integer> adj[]; // adjacency list for each node
   
   UnweightedGraph(int numNodes, char[] nodeNames) {
      N = numNodes;
      adj = new LinkedList[N]; // create an adjacency list for each node
      names = new char[N];
      
      for(int i = 0; i < N; i++) {
         adj[i] = new LinkedList(); // create empty list of edges
         names[i] = nodeNames[i]; // name the current node
      }
   }
   
   public void addEdge(int startNode, int endNode) {
      adj[startNode].add(endNode);
   }
   
   public int getNumNodes() {
      return N;
   }
   
   public char[] getNodeNames() {
      return names;
   }
   
   public LinkedList<Integer>[] getAdjList() {
      return adj;
   }
   
   public String toString() {
      String output = new String();
      
      for(int i = 0; i < N; i++) {
         output += names[i] + ": ";
         
         ListIterator iter = adj[i].listIterator();
         
         while(iter.hasNext()) 
            output += names[(int) iter.next()] + " "; 
         
         output += "\n";  
      }
      
      return output;
   }
}

public class GraphTraversal {
   static void BFS(UnweightedGraph graph, int source) {
      int N = graph.getNumNodes();
      char[] names = graph.getNodeNames();
      LinkedList<Integer> adj[] = graph.getAdjList();
      
      boolean visited[] = new boolean[N]; // list of visited edges (defaults to false)
      Queue<Integer> queue = new LinkedList<Integer>(); // BFS queue
   
      System.out.println("BFS Traversal:");
   
      // source node is visited, add it to the queue for processing
      visited[source] = true;
      queue.add(source);
      
      while(queue.size() != 0) {
         // remove node from queue and process (print) it
         int head = queue.remove();
         System.out.println("Processed Node " + names[head]);
         
         ListIterator iter = adj[head].listIterator();
         
         // get adjacency list for the node that was just processed
         // if an adjacent node has not been visited, add to the queue
         // and mark it as visited
         while(iter.hasNext()) 
         { 
            int i = (int) iter.next(); 
            
            if(!visited[i]) {
               System.out.println("Adding Node " + names[i] + " to queue");
               visited[i] = true; 
               queue.add(i); 
            } 
         } 
      }
   }
   
   public static void main(String[] args) {
      char[] nodeNames = {'A', 'B', 'C', 'D', 'E'}; // Nodes 0, 1, 2, 3, 4
      int numNodes = nodeNames.length;
      
      UnweightedGraph graph = new UnweightedGraph(numNodes, nodeNames);
      graph.addEdge(0, 1);
      graph.addEdge(0, 3);
      graph.addEdge(1, 0);
      graph.addEdge(1, 2);
      graph.addEdge(1, 3);
      graph.addEdge(1, 4);
      graph.addEdge(2, 1);
      graph.addEdge(2, 4);
      graph.addEdge(3, 0);
      graph.addEdge(3, 1);
      graph.addEdge(3, 4);
      graph.addEdge(4, 1);
      graph.addEdge(4, 2);
      graph.addEdge(4, 3);
      System.out.println(graph);
      
      BFS(graph, 0);
   }
}