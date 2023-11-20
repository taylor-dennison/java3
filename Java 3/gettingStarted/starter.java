import java.util.ArrayList;

public class starter {

   public static String stringReverse(String in) {

      char[] chars = in.toCharArray();
      int charsLength = chars.length;
      ArrayList<Character> newChars = new ArrayList<Character>();  

      for (int i = charsLength -1 ; i >= 0; i--) {
         newChars.add(chars[i]);
      };

      String reverse = "";
      for (Character c: newChars) {
         reverse += c.charValue();
      }
      return reverse;
   }

   public static int patternFinder(String sequence, String pattern) {

      
      return -1;
   }

   public static void main(String[] args) {
      
      String str = new String("This is a string");
      System.out.println(str);
      System.out.println(stringReverse(str));

      String sequence = new String("GTGGCAGTTACTTA");
      String pattern = new String("GTT");
   }
}
