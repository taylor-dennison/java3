import java.lang.reflect.Array;
import java.util.*;

public class ClassicSearchesA3 {
   static int ops = 0;
   
   static int linearSearchOrdered(int[] arr, int key) {
      int n = arr.length;
      ops = 0;
      
      for(int i = 0; i < n; i++) {
         ops++;
         if(arr[i] == key)
            return i;
         else if(arr[i] > key) {
            return -1;
         }
      }
            
      return -1;
   }

   static int binarySearch(int arr[], int key) {
      int start = 0;
      int end = arr.length - 1;
      ops = 0;
      
      while(start <= end) {
         ops++;
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
      ops = 0;
      
      while(low <= high && key >= arr[low] && key <= arr[high]) {
         ops++;
         int index = low + (((key - arr[low]) * (high - low)) / 
                     (arr[high] - arr[low]));
         
         if(key == arr[index])
            return index;
         
         if(key < arr[index])
            high = index - 1;
         else
            low = index + 1;
      }
      
      return -1;
   }

   static void searchResult(String type, int key, int index) {
      if(index != -1)
         System.out.println(type + ": Found " + key + " at index " + index + 
                            " in " + ops + " operations");
      else
         System.out.println(type + ": Did not find " + key + " in " + ops + 
                            " operations");
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

      //generate data set between 1 and 1000
      int low = 1;
      int high = 10000;
      
      int[] dataSet = new int[1000];
      int[] secondaryDataSet = new int[1000];
      for (int i = 0; i < 1000; i++) {          
         int randomNumber = (int)Math.floor(Math.random()*(high-low+1)+low);
         dataSet[i] = randomNumber;
      }

      //generate set of numbers not in dataSet.
      HashMap<String, Integer> hmDataSet = new HashMap<String, Integer>();
      for (int i : dataSet) {

         hmDataSet.put(Integer.toString(i), i);
      }

      for (int i = 0; i < 1000; i++) {
         boolean goodNum = false;
         while (!goodNum) {
            int randomInt = (int)Math.floor(Math.random()*(high-low+1)+low);
             if (!hmDataSet.containsValue(randomInt)) {
                secondaryDataSet[i] = randomInt;
                goodNum=true;
             }
          }
      }

      //sort both datasets
      Arrays.sort(dataSet);
      Arrays.sort(secondaryDataSet);

      //op counters
      int linearOps        = 0;
      int binaryOps        = 0;
      int interpolationOps = 0;

      //averages
      double totalLinearCounter = 0;
      double totalBinaryCounter = 0;
      double totalInterpolationCounter = 0;

      double totalLinearCounterSecondary = 0;
      double totalBinaryCounterSecondary = 0;
      double totalInterpolationCounterSecondary = 0;

      System.out.println("Original Data Set");
      for (int num = 0; num < 100; num++) {
         for (int i = 0; i < 1000; i++) {
            linearSearchOrdered(dataSet, dataSet[i]);
            linearOps += ops;
            binarySearch(dataSet, dataSet[i]);
            binaryOps += ops;
            interpolationSearch(dataSet, dataSet[i]);
            interpolationOps += ops;
         }
      double linearAverage = linearOps / 1000.0;
      double binaryAverage = binaryOps / 1000.0;
      double interpolationAverage = interpolationOps / 1000.0;

      totalLinearCounter += linearAverage;
      totalBinaryCounter += binaryAverage;
      totalInterpolationCounter += interpolationAverage;
      
      linearOps = 0;
      binaryOps = 0;
      interpolationOps = 0;
      }

      System.out.println("Average Linear ops : " + totalLinearCounter / 100.0);
      System.out.println("Average Binary ops : " + totalBinaryCounter / 100.0);
      System.out.println("Average Interpolation ops :" + totalInterpolationCounter / 100.0);
      

      System.out.println("Secondary Data Set");
      for (int num = 0; num < 100; num++) {
         for (int i = 0; i < 1000; i++) {
            linearSearchOrdered(dataSet, secondaryDataSet[i]);
            linearOps += ops;
            binarySearch(dataSet, secondaryDataSet[i]);
            binaryOps += ops;
            interpolationSearch(dataSet, secondaryDataSet[i]);
            interpolationOps += ops;
         }
      double linearAverage = linearOps / 1000.0;
      double binaryAverage = binaryOps / 1000.0;
      double interpolationAverage = interpolationOps / 1000.0;

      totalLinearCounterSecondary+= linearAverage;
      totalBinaryCounterSecondary += binaryAverage;
      totalInterpolationCounterSecondary += interpolationAverage;

      linearOps = 0;
      binaryOps = 0;
      interpolationOps = 0;
      }

      System.out.println("Average Linear ops : " + totalLinearCounterSecondary / 100.0);
      System.out.println("Average Binary ops : " +totalBinaryCounterSecondary / 100.0);
      System.out.println("Average Interpolation ops :" + totalInterpolationCounterSecondary / 100.0);



   }
}