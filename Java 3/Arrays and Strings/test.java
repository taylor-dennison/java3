public class test {

   public static void main(String[] args) {
   
      int[] numbers = new int[10];   
      int elementMemory = numbers.length * Integer.SIZE;
      System.out.println("Array elements use " + elementMemory + " bits");
      int elementMemoryBytes = elementMemory / Byte.SIZE;
      System.out.println("Array elements use " + elementMemoryBytes + " bytes");
      
      int rows = 5;
      int cols = 2;
      
      int[][] numbers2D = new int[rows][cols]; 
      
      int memory2D = 0;
      System.out.println("There are " + numbers2D.length + " rows in this 2D array");
      
      for (int i=0; i< rows; i++) 
         memory2D += (numbers2D[i].length * Integer.SIZE ) / Byte.SIZE;
      
      System.out.println("2D array elements use " + memory2D + " bytes");
      
      
      //String Memory
      
      String str = new String("This is a string");
      
      int charMemory = ( str.length() * Character.SIZE ) / Byte.SIZE;
      System.out.println("Character array elements use " + charMemory + " bytes");
   }
}


