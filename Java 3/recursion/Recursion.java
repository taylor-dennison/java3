public class Recursion {

   public static void main(String[] args) {
   
      //raise an integer x to the power of y (assume y is +)
      
      
   }
   
   //base cases are x^0 = 1 and x^1 = x
   //reduced problem x^(y-1)
   //general problem is x * x^(y-1)
   public static int recursiveExponential(int x, int y)  {
      //only here to help if y starts at 0. 
      if (y==0) {
         return 1;
      } else if (y==1){
         return x;
      } else {      
         return x * recursiveExponential(x, y-1);
      }
      
   }
}