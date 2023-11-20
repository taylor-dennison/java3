import java.util.*;

public class StacksAndQueues {

   public static void main(String[] args) {
   
      //Stacks ->  LIFO (Last In First Out)
      
      Stack<Integer> stack = new Stack<Integer>();
      
      //push int onto stack in numerical order
      for (int i=1; i <=5; i++) {
         stack.push(i);
      }
      
      while( !stack.empty()) {
         System.out.println("The current top element is " + stack.peek());
         System.out.println("Popped " + stack.pop() + " off the stack");
      }
      System.out.println();
      
      //Queues -> FIFO (First in First Out)
      
      //NOTICE ITS INSTANTIATED WITH A LINKED LIST.  ONLY WAY TO INITIALIZE QUEUE
      Queue<Integer> q = new LinkedList<Integer>();
      
       for (int i=1; i <=5; i++) {
         q.add(i);
      }
       
       // only way to check if queue is empty is to check if peek is null
       while(q.peek() != null) {
         System.out.println("The current head element is " + q.peek());
         System.out.println("Removed " + q.remove() + " from the queue");
      }   
   }

}