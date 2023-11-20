import java.util.*;
public class test {

   public static void main(String[] args) {
      LinkedList<String> list = new LinkedList<String>();
      list.add("Head");
      list.add("Element 10");
      list.add("Element 20");
      list.add("Element 30");
      list.add("Element 40");
      
      System.out.println("LinkedList list elements");
      //list.size() is bad practice! Store the value before the loop!
      for (int i = 0; i < list.size(); i++) {
         System.out.println("Node value = " + list.get(i));
      }
      //notice that it will insert BEFORE indicated element. In other words,
      //what was the value at index 2 will become the value of index 3 
      list.add(2, "Element 11");
      
      System.out.println("list after insert");
      for (int i = 0; i < list.size(); i++) {
         System.out.println("Node value = " + list.get(i));
      }
      
      //this removes the 0 index.
      list.remove();
      //this changes what the value at the 0 index was. Here, it goes from
      // "Element 10" to "Head" 
      list.set(0, "Head"); 
      
      System.out.println("list after delete and update");
      for (int i = 0; i < list.size(); i++) {
         System.out.println("Node value = " + list.get(i));
      }
   }
}


