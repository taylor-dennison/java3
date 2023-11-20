import java.util.*;

public class Balancer {
   static String expression1 = "-{ [ b * b – (4 * a * c) ] / (2 * a) }";       //should be balanced
   static String expression2 = "-[ { [ b * b – ( 4 * a * c ) ] / ( 2 * a ) }"; //should NOT be balanced
   static String expression3 = "-{ [ b * b – ( 4 * a * c ) ] / ( 2 * a ) } ]"; //should NOT be balanced
   static String expression4 = "-{ [ b * b – [ ( 4 * a * c ) ] / ( 2 * a ) }"; //should NOT be balanced
   
   static char[] openingOperators  = {'(','[','{'};
   static char[] closingOperators  = {')',']','}'};
   
   public static void main(String[] args) {
      
      //to see the output of each expression, change the number after the word expression
      System.out.println("The expression is given is " + expression4 + "\n" );
      //be sure to change the experession here too.
      if (determineIfExpressionIsBalanced(expression4)) {
          System.out.println("This expression is balanced");
      } else {
         System.out.println("The expression is NOT balanced");
      } 
   } 
   
   public static boolean determineIfExpressionIsBalanced(String exp) {
      
      int length = exp.length();
      char[] expressionChars   = exp.toCharArray();
      
      Stack<Character> stack = new Stack<Character>();
      
      for (int i=0; i < length; i++) {
        for (int j=0; j <= 2; j++) {
         //opening character identified
         if (expressionChars[i] == openingOperators[j]) {
            stack.push(expressionChars[i]);
            //closing operator identified
         } else if (expressionChars[i] == closingOperators[j]) {
             //check to see if the closing operator matches the top character of stack.
             try {
               if (openingOperators[j] != stack.pop()) {
                   return false;
               } 
               // catch will indicate that there was a closing character before any opening characters.
             } catch (EmptyStackException e) {  
                  return false;
             }
           }
        }
      } 
      if (stack.empty()) {
      
         return true;
      }
      return false;
   }
}