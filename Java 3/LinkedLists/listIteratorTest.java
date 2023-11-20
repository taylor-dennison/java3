import java.util.*;
public class listIteratorTest {

   public static void main(String[] args) {
      LinkedList<String> list = new LinkedList<String>();
      list.add("Head");
      list.add("Element 10");
      list.add("Element 20");
      list.add("Element 30");
      list.add("Element 40");
    
      ListIterator list_Iter = list.listIterator();
      
      System.out.println("ListIteratorTest list elements");
      while (list_Iter.hasNext()) {
         System.out.println("Node value = " + list_Iter.next());
      }
      
      
      //create an iterator with the cursor "between" Elements 1 and 2.
      list_Iter = list.listIterator(2);
      list_Iter.add("Element 11");
      
      //get a new iterator that starts at the beginning of the list
      list_Iter = list.listIterator();
      
       System.out.println("List after insert");
      while (list_Iter.hasNext()) {
         System.out.println("Node value = " + list_Iter.next());
      }
      
      
      list_Iter = list.listIterator();
      list_Iter.next();
      list_Iter.remove();
      list_Iter.next();
      list_Iter.set("Head");
      list_Iter = list.listIterator();
      
      System.out.println("List after delete and update");
      while (list_Iter.hasNext()) {
         System.out.println("Node value = " + list_Iter.next());
      }

   }
}