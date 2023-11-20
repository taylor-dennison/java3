import java.util.Arrays;

class Node {
   Node left;
   Node right;
   int data;
   
   public Node(int value) {
      data = value;
   }
}

public class BinarySearchTree {
   Node root; // root node of the entire tree
   int depth = 0;
   
   public BinarySearchTree(int[] keys) {
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
   
   public void insert(Node node, int[] keys, int start, int end) {
      if(start <= end) {
         //new midpoint
         int mid = (start + end) / 2;
         if(keys[mid] < node.data) { //affects left subtree only
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
               System.out.println("Visited " + node.data); // print node's key value
               //is there a left child? 
               inorderTraversal(node.left, reverse);
         
         } else {
               //is there a left child of this node?
               inorderTraversal(node.left, reverse);
               //visit data
               System.out.println("Visited " + node.data); // print node's key value
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
         System.out.println("Visited " + node.data); // print node's key value
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
         System.out.println("Visited " + node.data); // print node's key value
         //is there a left child of this node?
      }
   }
   
   public Node search(Node node, int key) {
      if(node == null)
         // hitting an empty node means search has failed.  Value not in the tree.
         return null;
      if(node.data == key) {
         return node;
         }
      else if(node.data > key) {
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
   
   public static void main(String args[]) {
      int[] key_values = {16, 70, 11, 23, 15, 25, 106};
      BinarySearchTree bst = new BinarySearchTree(key_values);
      
      //preorder
      System.out.println("Preorder tree traversal");
      bst.preorderTraversal(bst.root);
      System.out.println();
      //postorder
      System.out.println("Postorder tree traversal");
      bst.postorderTraversal(bst.root);
      System.out.println();
      //inorder reverse
      System.out.println("Inorder reverse tree traversal");
      bst.inorderTraversal(bst.root, true);
      System.out.println();
      //inorder 
      System.out.println("Inorder tree traversal");
      bst.inorderTraversal(bst.root, false);
      System.out.println();
      
      //search test cases, only run one at a time
      // int search_key = 70;
//       //we return a node because if there is no matching value with search_key, null is returned. 
//       Node node = bst.search(bst.root, search_key);
      
      // int search_key = 16;
//       //we return a node because if there is no matching value with search_key, null is returned. 
//       Node node = bst.search(bst.root, search_key);

      int search_key = 110;
      //we return a node because if there is no matching value with search_key, null is returned. 
      Node node = bst.search(bst.root, search_key);
      
      if(node != null) {
         System.out.println("Found " + node.data);
         System.out.println("I'm " + bst.depth + " levels deep!");
      }
      else
         System.out.println(search_key + " not found");
   }
}
