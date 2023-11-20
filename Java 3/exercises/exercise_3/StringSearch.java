import java.util.*;

public class StringSearch {
    
   static int bruteForce(String str, String substr) {
      int n = str.length();
      int m = substr.length();
      int index;
      
      for(int i = 0; i <= n - m; i++) {
         int j = 0;
         
         while(j < m && substr.charAt(j) == str.charAt(i + j)) {
            j++;
         }
         
         if(j == m)
            return i;
      }
      
      return -1;
   }
   
   static int rabinKarp(String str, String substr) {
      int n = str.length();
      int m = substr.length();
      int t = 0; // hash value for str window
      int p = 0; // hash value for substr
      
      // Calculate the hash value of the substring
      // and the first str window 
      for(int i = 0; i < m; i++) {  
         p = p + substr.charAt(i);
         t = t + str.charAt(i);
      } 
      
      int i = 0;
      while(i <= n - m) { 
         // if the hashes match, confirm that the window 
         // and substring match 
         if(p == t) { 
            int j;
            System.out.println("Hashes matched: window = " 
                               + str.substring(i, i + m));
            
            for(j = 0; j < m; j++) {
               if(substr.charAt(j) != str.charAt(i + j))
                  break; 
            } 
         
            if(j == m) 
               return i; 
         }
         
         if(i < n - m) {
            // compute the hash for the next window
            t = t - str.charAt(i);
            t = t + str.charAt(i + m);
         }
         
         i++;
      }
      
      return -1;
   }
   
   static LinkedList rabinKarpMultiple(String str, String substr) {
      LinkedList matches = new LinkedList();
      int n = str.length();
      int m = substr.length();
      int t = 0; // hash value for str window
      int p = 0; // hash value for substr
      
      // Calculate the hash value of the substring
      // and the first str window 
      for(int i = 0; i < m; i++) {  
         p = p + substr.charAt(i);
         t = t + str.charAt(i);
      } 
      
      int i = 0;
      while(i <= n - m) { 
         // if the hashes match, confirm that the window 
         // and substring match 
         if(p == t) { 
            int j;
      
            for(j = 0; j < m; j++) {
               if(substr.charAt(j) != str.charAt(i + j))
                  break; 
            } 
         
            if(j == m) 
            //when doing multple, cant return here.  Add to collection instead
               matches.add(i); 
         }
         
         if(i < n - m) {
            // compute the hash for the next window
            t = t - str.charAt(i);
            t = t + str.charAt(i + m);
         }
         
         i++;
      }
      
      return matches;
      
      
   }
   
   public static int countBases(String str, String base) {
   
      LinkedList matches = rabinKarpMultiple(str, base);
      
      return matches.size();
   
   }

               
   public static void main(String[] args) {
      String str = "GTTGCAGTTACTTATTATCTGAAAACCAGTTGATGTTAAGGAATACTCTGTCTAAGACAACATATGTAATAAAAATTATATATTCGTTGGGTTCTCTCGA";
      String substr = "GTT";
      
      LinkedList matches = rabinKarpMultiple(str, substr);
      
      if (matches.size() > 0) {
         ListIterator iter = matches.listIterator();
         System.out.print("We have matches!  They are in the following locations: ");
         while (iter.hasNext()) {
            System.out.print(iter.next() + ", ");
         }
          System.out.println();
      } else {
         System.out.println("No matches were found!");
      
      }
      
      System.out.println("The total number of base \"A\" matches is " + countBases(str, "A"));
      System.out.println("The total number of base \"C\" matches is " + countBases(str, "C"));
      System.out.println("The total number of base \"G\" matches is " + countBases(str, "G"));
      System.out.println("The total number of base \"T\" matches is " + countBases(str, "T"));
      
   }
}