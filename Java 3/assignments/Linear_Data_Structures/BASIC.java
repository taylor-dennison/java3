import java.util.*;

public class BASIC {
   
   static LinkedList<Integer> lineList = new LinkedList<Integer>();
   
   //main method
   public static void main(String[] args) {
      //instantiate default data
      for (int i = 10; i <=100; i+=10) {
         lineList.add(i);
      }
      //run through test cases.
      System.out.println("Test Case 1: List all lines of the program");
      listAll();
      System.out.println("Test Case 2: List lines 40-80 of the program");
      listRange(40,80);
      System.out.println("Test Case 3: Insert Line 17 into the program");
      insert(17);
      System.out.println("Test Case 4: Insert Line 34 into the program");
      insert(34);
      System.out.println("Test Case 5: Insert Line 88 into the program");
      insert(88);
      System.out.println("Test Case 6: List all lines of the program");
      listAll();
      System.out.println("Test Case 7: Renumber all lines of the program");
      renumber();
      System.out.println("Test Case 8: List all lines of the program");
      listAll();
      System.out.println("Test Case 9: Insert Line 80 into the program (should not be allowed)");
      insert(80);
      System.out.println("Test Case 10: Modify Line 9 of the program (should not be allowed)");
      modify(9);
      System.out.println("Test Case 11: Modify Line 10 of the program to line 110 (should not be allowed)");
      modify(10,110);
      System.out.println("Test Case 12: Modify Line 10 of the program to line 111");
      modify(10, 111);
      System.out.println("Test Case 13: List all lines of the program");
      listAll();
      System.out.println("Test Case 14: Renumber the program"); 
      renumber();
      System.out.println("Test Case 15: List all lines of the program");
      listAll();

   }//end of main method
   
   
   
   //listAll() method
   private static void listAll() {
      ListIterator<Integer> listIterator = lineList.listIterator();
      
      while (listIterator.hasNext()) {
         System.out.print("\t");
         System.out.println(listIterator.next());
      }
      
   }//end of listAll() method
   
   
   //listRange() method
   private static void listRange(int first, int last) {
      
      ListIterator<Integer> listIterator = lineList.listIterator();
      
      boolean firstFound  = false;
      while (listIterator.hasNext()) {
         //determine the value following the pointer
         int nextLine = listIterator.next();
         //if the value is equal to the first parameter, indicate that 
         //we've found the first number
         if (nextLine == first && !firstFound) {
            firstFound = true;
           //check to see if the value is equal to the last parameter, 
           //indicate we've found the last number.  Print and return. 
         } else if (nextLine == last) {
            System.out.print("\t");
            System.out.println(nextLine);
            return;
         }
         // once we have found the first matching value, print every value after.
         if (firstFound) {
            System.out.print("\t");
            System.out.println(nextLine);
         }
            
      }//end of while
      
   }//end of listRange() method
   
   
   //insert() method
   private static void insert(int lineNumber) {
      
      ListIterator<Integer> listIterator = lineList.listIterator();
      
      while(listIterator.hasNext()) {
      
         int nextLine = listIterator.next();
         
         if(nextLine >= lineNumber) {
            if(nextLine == lineNumber) {
               System.out.println("\tERROR:  Cannot overwrite existing line.");
            } else {
               listIterator.previous();
               listIterator.add(lineNumber);
               return;
            }
         }
      }//end of while
      
   }//end of insert() method
   
   //renumber() method
   private static void renumber() {
        ListIterator<Integer> listIterator = lineList.listIterator();
        int counter = 1;
        while (listIterator.hasNext()) {
          listIterator.next();
          counter++;
        }
        while (listIterator.hasPrevious()){ 
         listIterator.previous();
         counter--;
         listIterator.set(counter * 10);
        }
   }
   
   //modify() method with only one parameter
   private static void modify(int lineNumber) {
       System.out.println("\tERROR: New line number not given.");
   }
   
   private static void modify(int lineNumber, int newLineNumber) {
       ListIterator<Integer> listIterator = lineList.listIterator();
       
       //determine direction iterator must go. True is forward, false is backwards
       boolean newLineLarger = newLineNumber > lineNumber ? true : false;
        
       boolean lineNumberFound = false;
       while (listIterator.hasNext()) {
         int nextLine = listIterator.next();
         //check to see if newLineNumber already exists in program
         if (nextLine == newLineNumber) {
           System.out.println("\tERROR: New line number is already in use.");
           return;
           //ensure that the line number already exists
         } else if (nextLine == lineNumber) {
              lineNumberFound = true;
         }
       }
       //no line number found
       if (!lineNumberFound) {
         System.out.println("\tERROR: Line number does not exist.");
         return;
       }
       // line number found, and no newLineNumber exists.  Okay to perform modify operation. 
       boolean lineNumberRemoved = false;
       while (listIterator.hasPrevious()) {
         int prevLine = listIterator.previous();
         //find old line number and remove
         if (prevLine == lineNumber) { 
           listIterator.remove();
           lineNumberRemoved = true;
         }
         //insert newLineNumber
         //if newLineLarger is larger, traverse forward.  If newLineLarger is false, traverse backwards. 
         if (lineNumberRemoved && newLineLarger) {
           while (listIterator.hasNext()) {
              int nextLine = listIterator.next();
              if(nextLine > newLineNumber) {
                 listIterator.previous();
                 listIterator.add(newLineNumber);
                 return;
              }
           }
         } else if (lineNumberRemoved) {
               int prev = listIterator.previous();
               if(prev > newLineNumber) {
                  listIterator.add(lineNumber);
                  return;
               }
           }
        }//end while
       
   }//end modify()


}//end of class BASIC