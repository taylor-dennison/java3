import java.util.*;
import java.io.*;
class TermEntry implements Comparable<TermEntry> {

   private String term;
   private int count;
   
   public TermEntry(String term, int count) {
      this.term  = term;
      this.count = count;
   }
   public String getTerm() {
      return term;
   }
   public int getCount() {
      return count;
   }
   public int compareTo(TermEntry te) {
         
      return this.term.compareTo(te.getTerm());
   }
   
   public String toString() {
   
      return "The term is: " + this.term + " and the count is : " + this.count;
   }

}

class Node {
   Node left;
   Node right;
   TermEntry data;
   
   public Node(TermEntry value) {
      data = value;
   }
}

public class IRSystem {

   private static HashMap<String, TermEntry> termsAndCounts = new HashMap<>();

   Node root; // root node of the entire tree
   int depth = 0;
   
   public IRSystem(TermEntry[] keys) {
     // sort keys in ascending order
      Arrays.sort(keys);
     
      int start = 0;
      int end = keys.length - 1;
      int mid = (start + end) / 2;
      root = new Node(keys[mid]);
      
      // left side of array passed to left subtree
      insert(root, keys, start, mid - 1);
      // right side of array passed to right subtree
      insert(root, keys, mid + 1, end);
   }
   
   public void insert(Node node, TermEntry[] keys, int start, int end) {
      if(start <= end) {
         //new midpoint
         int mid = (start + end) / 2;
         if(keys[mid].getTerm().compareTo( node.data.getTerm()) < 0) { //affects left subtree only
            //create left node
            node.left = new Node(keys[mid]);
            insert(node.left, keys, start, mid - 1);
            //create the right node
            insert(node.left, keys, mid + 1, end);
         }
         else { // affects right subtree 
            //create new node
            node.right = new Node(keys[mid]);
            //create left node
            insert(node.right, keys, start, mid - 1);
            //create right node
            insert(node.right, keys, mid + 1, end);
         }
      }
   }
   //root node is passed in to this method to start. 
   public void inorderTraversal(Node node, boolean reverse) {
      // print the contents of the tree in increasing order
      // null checking is our "base case".  
      if(node != null) {
         //** This is a LDR traversal.  To change to another type, simply reorder these methods.
         
         if (reverse) {
               //is there a right child of this node?
               inorderTraversal(node.right, reverse);
               //visit data
               System.out.println(node.data.getTerm() + ": " + node.data.getCount()); // print node's key value
               //is there a left child? 
               inorderTraversal(node.left, reverse);
         
         } else {
               //is there a left child of this node?
               inorderTraversal(node.left, reverse);
               //visit data
               System.out.println(node.data.getTerm() + ": " + node.data.getCount()); // print node's key value
               //is there a right child? 
               inorderTraversal(node.right, reverse);
           }
      }
   }
   //root node is passed in to this method to start. 
    public void preorderTraversal(Node node) {
      // print the contents of the tree in increasing order
      // null checking is our "base case".  
      if(node != null) {
         //** This is a DLR traversal.  
         
         //visit data
         System.out.println("Visited " + node.data.getTerm()); // print node's key value
         //is there a left child of this node?
         preorderTraversal(node.left);
         
         //is there a right child? 
         preorderTraversal(node.right);
      }
   }
   //root node is passed in to this method to start. 
    public void postorderTraversal(Node node) {
      // print the contents of the tree in increasing order
      // null checking is our "base case".  
      if(node != null) {
         //** This is a LRD traversal.  
         postorderTraversal(node.left);
         
         //is there a right child? 
         postorderTraversal(node.right);
         
          //visit data
         System.out.println("Visited " + node.data.getTerm()); // print node's key value
         //is there a left child of this node?
      }
   }
   
   public TermEntry search(Node node, String key) {
      if(node == null)
         // hitting an empty node means search has failed.  Value not in the tree.
         return null;
      if(node.data.getTerm().equals(key) ) {
         return node.data;
         }
      else if(key.compareTo(node.data.getTerm()) < 0)  {
         // need to search the left subtree since key is less than node value
         depth++;
         return search(node.left, key);
         }
      else {
         // key value is larger than current node, search right subtree
         depth++;
         return search(node.right, key); 
         }
   }
   

   public static String[] parse(String filename) {
   
     LinkedList<String> words = new LinkedList<>();
   
     try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        String line;
         while ((line = br.readLine()) != null) {
             String[] lineWords = line.split(" ");
             
             for (String word : lineWords) {
               words.add(word.toLowerCase());
             }
         }
         
     } catch (IOException ioe) {
         ioe.printStackTrace();
         System.out.println("An error has occured while trying to read file");
     }
     
     return words.toArray(new String[words.size()]);
     
   }

   public static void countTerms(String[] terms) {
      for (String term : terms) {
         if (termsAndCounts.containsKey(term)) {
            int newCount = termsAndCounts.get(term).getCount() + 1;
            termsAndCounts.replace(term, new TermEntry(term, newCount));
         } else {
            termsAndCounts.put(term, new TermEntry(term, 1));
         }
      }
   }

   public TermEntry singleTermQuery(String term) {
      return search(this.root, term);
   }

   public boolean andQuery(String term1, String term2) {
      TermEntry result1 = singleTermQuery(term1);
      TermEntry result2 = singleTermQuery(term2);

      if (result1 != null && result2 != null) {
         return true;
      } else {
         return false;
      }
   }

   public boolean orQuery(String term1, String term2) {
      TermEntry result1 = singleTermQuery(term1);
      TermEntry result2 = singleTermQuery(term2);

      if (result1 != null || result2 != null) {
         return true;
      } else {
         return false;
      }
   }

   public static void main(String args[]) {

      String [] fileWords = parse("quotes.txt");
      countTerms(fileWords);
      
      TermEntry[] termEntries = termsAndCounts.values().toArray(new TermEntry[termsAndCounts.size()]);
    
      IRSystem bst = new IRSystem(termEntries);
      //inorder 
      System.out.println("Test Case 1");
      bst.inorderTraversal(bst.root, false);
      System.out.println();



      System.out.println("Test Case 2");
      TermEntry stqResult1 = bst.singleTermQuery("all");
      if (stqResult1 != null) {
         System.out.println("Result found. Term occurred " + stqResult1.getCount() + " times.");
      } else {
         System.out.println("No results found.");
      }
      System.out.println();



      System.out.println("Test Case 3");
      TermEntry stqResult2 = bst.singleTermQuery("carrying");
      if (stqResult2 != null) {
         System.out.println("Result found.  Term occurred " + stqResult2.getCount() + " times.");
      } else {
         System.out.println("No results found.");
      }
      System.out.println();


      
      System.out.println("Test Case 4");
      TermEntry stqResult3 = bst.singleTermQuery("robot");
      if (stqResult3 != null) {
         System.out.println("Result found.  Term occurred " + stqResult3.getCount() + " times.");
      } else {
         System.out.println("No results found.");
      }
      System.out.println();



      System.out.println("Test Case 5");
      if (bst.andQuery("seattle", "mariners")) {
         System.out.println("Both words found!");
      } else {
         System.out.println("One or both words were missing");
      }  
      System.out.println();



      System.out.println("Test Case 6");
      if (bst.andQuery("seattle", "pilots")) {
         System.out.println("Both words found!");
      } else {
         System.out.println("One or both words were missing");
      }  
      System.out.println();



      System.out.println("Test Case 7");
      if (bst.orQuery("four", "score")) {
         System.out.println("Both words found!");
      } else {
         System.out.println("One or both words were missing");
      }  
      System.out.println();



      System.out.println("Test Case 8");
      if (bst.orQuery("five", "score")) {
         System.out.println("Both words found!");
      } else {
         System.out.println("One or both words were missing");
      }  
      System.out.println();



      System.out.println("Test Case 9");
      if (bst.orQuery("five", "robots")) {
         System.out.println("Both words found!");
      } else {
         System.out.println("Both words were missing");
      }  
      System.out.println();
   
   }
}
