import java.util.*;

public class Exercise {

   public static void main(String[] args) {
   
      String statement = new String("abcdefghijk");
      
      System.out.println(stackReverse(statement));
      System.out.println(queueReverse(statement));
   };
   
   
   public static String stackReverse(String in) {
      
      int length = in.length();
      char[] letters = in.toCharArray();
      
      char[] reverse = new char[length];
   
      Stack<Character> stack = new Stack<Character>();
      
      for (int i=0; i < length; i++) {
        stack.push(letters[i]);
      }
      
      int counter = 0;
      while (!stack.isEmpty()) {
         reverse[counter] = (char)stack.pop();
         counter++;
      }
      
      return new String(reverse);
      
   }
   
   public static String queueReverse(String in) {
      
      int length = in.length();
      char[] letters = in.toCharArray();
      
      char[] reverse = new char[length];
      
      Queue<Character> q = new LinkedList<Character>();
      
      for (int i=0; i < length; i++) {
        q.add(letters[length - i -1]);
      }
      
      int counter = 0;
      while (q.peek() != null) {
      
         reverse[counter] = q.remove();
         counter++;
      }
      
        return new String(reverse);
   }

}