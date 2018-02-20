package main.java;

// SortDemo.java     
import java.util.*;   // for class StringTokenizer


public class SortDemoData  {
               
        public Item[] myArray;
        public String algo;

        public SortDemoData(){
        	myArray = null;
        }
        
        
        // read numbers from input to array myArray
        public void initializeArray(String string) throws NumberFormatException, 
        NoSuchElementException, NegativeArraySizeException{

	        	StringTokenizer inputTokens = new StringTokenizer(string);
	        	String[] result = string.split("\\s");

                String str = "";
                    
                int n; // will be the length of myArray        
                if (inputTokens.hasMoreTokens()) {
                        n = inputTokens.countTokens(); 
                } else {
                        throw new NoSuchElementException();
                }

                // define the array of the right length n    
                if (n == 1) { 

                        // interpret the next number as number of entries and 
                        // generate a random vector with entries 1 ... n
                        str = inputTokens.nextToken(); 
                        n =  Integer.valueOf(str).intValue();
                        // may throw NegativeArraySizeException
                        myArray = new Item[n];
                        boolean[] tempArray = new boolean[n + 1];
                        RandomNumber randGen = new RandomNumber();
                        int randNr;
                        for (int i = 0; i < n; i++) {
                                do 
                                        randNr = randGen.nextIntRand(1, n);
                                while (tempArray[randNr]);                  
                                tempArray[randNr] = true;
                                myArray[i] = new Item(randNr);
                        }
                } else {           
                        Item [] array = new Item[n];
                        int count =0;
                        // read the n numbers into the array
                        for (int i = 0; i < n; i++) {
                        	

                                str = result[i];
                                if(Arrays.asList(Arrays.copyOfRange(result, 0, i)).contains(str)){
                                	count = count + 1;
                                }
                                else{
                                	array[i-count] = new Item( 
                                	        Integer.valueOf(str).intValue());
                                }

                                
                        }
                        myArray = new Item[n-count];
                        myArray = Arrays.copyOfRange(array, 0, n-count);
                        
                        
               } 
        }
             
        /**
    	 * All methods should have a Javadoc according to STYLE.
    	 * @param choice: chosen sort algorithm, needs to be a number between 0 and 5. If the number is out of range 0 should be chosen
    	 * The attribute algo should always contain the choosen algorithm as string
    	 * @throws Exception as per typical main specifications
    	 */
        public StringBuffer runAlgo(int choice) {     
                        Item[] copyOfMyArray = new Item[myArray.length];
                        System.arraycopy(
                                myArray, 0, copyOfMyArray, 0, myArray.length);
                        
                        //SER 316
                        //if (choice < 0 || choice > 5) choice = 0;
                        if(choice == 0) {
                        	SortAlgos.bubbleSort(myArray); 
                        	algo = "Bubble Sort";
                        }
                        else if(choice  == 1) {
                        	SortAlgos.selectionSort(myArray); 
                			algo = "Selection Sort";
                        }
                        else if(choice  == 2) {
                        	SortAlgos.insertionSort(myArray); 
                			algo = "Insertion Sort";
                        }
                        else if(choice  == 3) {
                        	SortAlgos.mergeSort(myArray);
                			algo = "Merge Sort";
                        }
                        else if(choice  == 4) {
                        	SortAlgos.quickSort(myArray); 
                			algo = "Quick Sort";
                        }
                        else if(choice  == 5) {
                        	SortAlgos.heapSort(myArray);     
                			algo = "Heap Sort";
                        }
                        else {
                        	SortAlgos.bubbleSort(myArray);  
	            			algo = "Bubble Sort";
                        }
                        
                        // output initial sequence of unsorted and sorted array 
                        int limit = Math.min(myArray.length, 1024);
                        StringBuffer outputBuf = new StringBuffer();
                        outputBuf.append(algo + "\n");
                        for (int i = 0; i < limit; i++) {
                                outputBuf.append(
                                        i + ": " 
                                          + Integer.toString( 
                                                       copyOfMyArray[i].key)  
                                          + " --> "
                                          + Integer.toString(myArray[i].key) 
                                          + "\n");
                        }
                        outputBuf.append("\r");
                        		
               return outputBuf;            
        }        		
}


