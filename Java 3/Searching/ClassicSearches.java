import java.util.Arrays;

public class ClassicSearches {

   static int linearSearchUnordered(int[] arr, int key) {
      int n = arr.length;
      
      for(int i = 0; i < n; i++) {
         if(arr[i] == key)
            return i;
      }
      
      return -1;
   }
   
   static int linearSearchOrdered(int[] arr, int key) {
      int n = arr.length;
      
      for(int i = 0; i < n; i++) {
         if(arr[i] == key)
            return i;
         else if(arr[i] > key)
            return -1;
      }
            
      return -1;
   }

   static int binarySearch(int arr[], int key) {
      int start = 0;
      int end = arr.length - 1;
      
      while(start <= end) {
         int mid = (start + end) / 2;
         if(arr[mid] == key)
            return mid;
         else if(arr[mid] < key)
            start = mid + 1;
         else
            end = mid - 1;
      }
      
      return -1;
   }

   static int interpolationSearch(int[] arr, int key) {
      int low = 0;
      int high = arr.length - 1;
      
      while(low <= high) {
         int mid = low + (((high - low) * (key - arr[low])) / 
                     (arr[high] - arr[low]));
                     
         System.out.println("mid = " + mid);
         
         if(key == arr[mid])
            return mid;
         
         if(key < arr[mid])
            high = mid - 1;
         else
            low = mid + 1;
      }
      
      return -1;
   }

   static void searchResult(String type, int key, int index) {
      if(index != -1)
         System.out.println(type + ": Found " + key + " at index " + index);
      else
         System.out.println(type + ": Did not find " + key);
   }
   
   static void printArray(int arr[]) { 
      int n = arr.length; 
      
      for(int i = 0; i < n; i++) 
         System.out.print(arr[i] + " "); 
      
      System.out.println(); 
   }

   public static void main(String[] args) {
      int index;
      int key;
      int[] nums = {15, 98, 7, 22, 9, 61, 57};
      
      printArray(nums);
      key = 22;
      searchResult("Linear", key, linearSearchUnordered(nums, key));
      
      Arrays.sort(nums);
      printArray(nums);
      key = 61;
      searchResult("Linear", key, linearSearchOrdered(nums, key));
      searchResult("Binary", key, binarySearch(nums, key));
      searchResult("Interpolation", key, interpolationSearch(nums, key));
   }
}