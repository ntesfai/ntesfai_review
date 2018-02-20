package main.java;

/** 
 * A collection of various sorting algorithms for sorting an array
 * of items with int key
 */

public final class SortAlgos {

        /**
         * Sorts with bubblesort algorithm
         * 
         * Returns a NullPointerException when the array is empty
         * Returns a sorted array with the same length as the initial array
         * 
         * @param vec the array to be sorted
	     * @exception NullPointerException if <code>vec</code> 
	     * is not initialized
         */
      public static void bubbleSort(Item[] vec) 
                            throws NullPointerException {
                if (vec == null) throw new NullPointerException();
        
		        int n = vec.length; 
		        Item temp;
                int bottom;       // bottom for each pass        
                for (bottom = 0; bottom < n; bottom++)  {
                        for (int i = 1; i < (n-bottom); i++) {
                                if (vec[i-1].key > vec[i].key) {
                                        temp = vec[i-1];
                                        vec[i-1] = vec[i];
                                        vec[i] = temp;
                                }
                        }
                }
        }
    
        /**
         * Sorts with selectionsort algorithm
         * @param vec the array to be sorted
         * @exception NullPointerException if <code>vec</code> 
         * is not initialized
         */
        public static void selectionSort(Item vec[]) 
                            throws NullPointerException {
                if (vec == null) throw new NullPointerException();
                
                int minIndx;            // Index of smallest key in each pass
                int bottom;             // bottom for each pass
                int i;
                Item temp;
                int n = vec.length;
            
                for (bottom = 0; bottom < n-1; bottom++) {
                             //  INVARIANT (prior to test):
                             //  All vec[bottom+1..n-1] are >= vec[bottom]
                             //  && vec[0..bottom] are in ascending order
                             //  && bottom >= 0
                        minIndx = bottom;
                        for (i = bottom+1; i < n; i++) {
                                    // INVARIANT (prior to test):
                                    // vec[minIndx] <= all
                                    // vec[0..i-1]
                                    // && i >= bottom+1
                                if (vec[i].key < vec[minIndx].key) { 
                                        minIndx = i; 
                                }
                        }
                        temp = vec[bottom];
                        vec[bottom] = vec[minIndx];
                        vec[minIndx] = temp; 
                }
        }
    
        /**
         * Sorts with insertionsort algorithm
         * @param vec the array to be sorted
         * @exception NullPointerException if <code>vec</code> 
         * is not initialized
         */
        public static void insertionSort(Item vec[]) 
                        throws NullPointerException {
                if (vec == null) throw new NullPointerException();
                int n = vec.length;
                int currentPos, insPos; 
                for (currentPos = 1; currentPos < n; currentPos++) {
                        insPos = findInsPosition(vec, currentPos - 1, 
                                        vec[currentPos].key);
                        insertAtPosition(vec, insPos, currentPos);
                }   
        }
        
        /**
         * Finds insertion position with binary search
         * @param vec the array to be sorted
         * @param range upper bound for insertion position
         * @param x the value determining the position 
         * @return the insertion position 
         */
        private static int findInsPosition(Item[] vec, int range, int x) {
                int index;   // variable to hold the position
                int i,j,k;
                i = 0; j = range; // initialize lower index i and upper index j
                do { 
                        k =  (i + j) / 2;  // choose k halfway between i and j
                        if (x >= vec[k].key) { 
                                i = k + 1;   // update lower index i
                        } else {                 
                                j = k - 1;   // update upper index j
                        }
                } while (i <= j);
                if (x >= vec[k].key) {
                        index = k + 1;
                } else  {                   
                        index = k;
                }
                return index;
        }
        
        /**
         * Inserts array component into a sorted range below the component
         * such that the result is again sorted
         * @param vec the array in which this happens
         * @param insPos the insertion position
         * @param fromPos the position whose value 
         * has to be inserted at <code>insPos</code>
         */
        private static void insertAtPosition(Item[] vec, int insPos, 
                                                         int fromPos ) {
                if (insPos == fromPos) return;
                System.out.println("here");
                Item temp = vec[fromPos];
                for (int i = fromPos; i > insPos; i--) vec[i] = vec[i-1];
                vec[insPos] = temp;
        }
    
    
        /**
         * Sorts with mergesort algorithm
         * @param vec the array to be sorted
         * @exception NullPointerException if <code>vec</code> 
         * is not initialized
         */
        public static void mergeSort(Item vec[]) 
                                throws NullPointerException {
                if (vec == null) throw new NullPointerException();
                mergeSort(vec, 0, vec.length - 1);
        }
        
        
        /**
         * merges two sorted adjacent ranges of an array
         * @param vec the array in which this happens
         * @param left start of the first range
         * @param middle end of the first range
         * @param right end of the second range
         */
        private static void merge(Item[] vec, int left, int middle, int right) {
                int i, j;
                int m = middle - left + 1; // length of first array region
                int n = right - middle;    // length of second array region
            
                // make copies of array regions to be merged 
                // (only the references to the items)
                Item[] copy1 = new Item[m];
                Item[] copy2 = new Item[n];
                for (i = 0; i < m; i++) copy1[i] = vec[left + i]; 
                for (j = 0; j < n; j++) copy2[j] = vec[middle + 1 + j]; 
                    
                i = 0; j = 0;   
                // merge copy1 and copy2 into  vec[left...right]
                while (i < m && j < n) {
                        if (copy1[i].key < copy2[j].key) {
                                vec[left+i+j] = copy1[i];
                                i++;
                        } else {
                                vec[left+i+j] = copy2[j];
                                j++;
                        }//endif
                }//endwhile
                if (j == n) { // second array region is completely handled, 
                              // so copy rest of first region
                        while (i < m) {
                                vec[left+i+j] = copy1[i];
                                i++;
                        }
                }
                // if (i == m) do nothing, 
                // rest of second region is already in place
        }
        
        /**
         * sorts array by mergesort in a certain range
         * @param vec the array in which this happens
         * @param first start of the range
         * @param last end of the range
         */
        private static void mergeSort(Item[] vec, int first, int last) {
                if (first == last || vec.length == 0) return;	
                // devide vec into 2 equal parts
                int middle = (first + last) / 2; 
                mergeSort(vec, first, middle);   // sort the first part
                mergeSort(vec, middle+1, last);  // sort the second part
                merge(vec, first, middle, last); // merge the 2 sorted parts
        }
    
    
        /**
         * Sorts with quicksort algorithm
         * @param vec the array to be sorted
         * @exception NullPointerException if <code>vec</code> 
         * is not initialized
         */
        public static void quickSort(Item[] vec) 
                                throws NullPointerException {
                if (vec == null) throw new NullPointerException();
                quickSort(vec, 0, vec.length - 1);
        }
        
        /**
         * sorts array by quicksort in a certain range
         * @param vec the array in which this happens
         * @param loBound start of the range
         * @param hiBound end of the range
         */
        private static void quickSort(Item[] vec, int loBound, int hiBound) {
                int loSwap, hiSwap;
                int pivotKey, pivotIndex;
                Item temp, pivotItem;
        
                if (hiBound - loBound == 1) {         // Two items to sort
                        if (vec[loBound].key > vec[hiBound].key) {
                                temp = vec[loBound];
                                vec[loBound] = vec[hiBound];
                                vec[hiBound] = temp;
                        }
                        return;
                }
                pivotIndex = (loBound + hiBound) / 2; // 3 or more items to sort
                pivotItem = vec[pivotIndex];       
                vec[pivotIndex] = vec[loBound];
                vec[loBound] = pivotItem;    
                pivotKey = pivotItem.key; 
                loSwap = loBound + 1;
                hiSwap = hiBound;
                do {
                        while (loSwap <= hiSwap && vec[loSwap].key <= pivotKey)
                            // INVARIANT (prior to test):
                            // All vec[loBound+1..loSwap-1]
                            // are <= pivot  &&  loSwap <= hiSwap+1
                                loSwap++;
                        while (vec[hiSwap].key > pivotKey)
                            // INVARIANT (prior to test):
                            //    All vec[hiSwap+1..hiBound]
                            //    are > pivot  &&  hiSwap >= loSwap-1
                                hiSwap--;
                        if (loSwap < hiSwap) {
                                temp = vec[loSwap];
                                vec[loSwap] = vec[hiSwap];
                                vec[hiSwap] = temp;
                                loSwap++;
                                hiSwap--;
                        }
                        // INVARIANT: All vec[loBound..loSwap-1] are <= pivot
                        // && All vec[hiSwap+1..hiBound] are > pivot
                        // && (loSwap < hiSwap) --> 
                        //                 vec[loSwap] <= pivot < vec[hiSwap]
                        // && (loSwap >= hiSwap) --> vec[hiSwap] <= pivot
                        // && loBound <= loSwap <= hiSwap+1 <= hiBound+1
                } while (loSwap <= hiSwap);
                vec[loBound] = vec[hiSwap];
                vec[hiSwap] = pivotItem;
        
                if (loBound < hiSwap-1)     // 2 or more items in 1st subvec
                        quickSort(vec, loBound, hiSwap-1);
        
                if (hiSwap+1 < hiBound)     // 2 or more items in 2nd subvec
                        quickSort(vec, hiSwap+1, hiBound);
        }
    
    
        /**
         * establishes heap property in a certain range
         * @param vec the array in which this happens
         * @param top start of the range
         * @param bottom end of the range
         */
        private static void heapify(Item[] vec, int top, int bottom) {
                Item temp;
                int child;
            
                if (2*top+1 > bottom) return; // nothing to do 
            
                if (2*top+2 > bottom) { 
                        // vec[2*top+1] is only child of vec[top]
                        child = 2*top+1;
                } else {                  // 2 sons, determine bigger one
                        if (vec[2*top+1].key > vec[2*top+2].key) {
                                child = 2*top+1;
                        } else {
                                child = 2*top+2;
                        }
                }//endif
            
                // check if exchange is necessary
                if (vec[top].key < vec[child].key) {
                        temp = vec[top]; 
                        vec[top] = vec[child]; 
                        vec[child] = temp;
                        // recursive call for possible further exchanges
                        heapify(vec, child, bottom); 
                }//endif
        }
    
        /**
         * turns array into a heap
         * @param vec the array to which this happens
         */
        private static void createHeap(Item[] vec) {
                for (int i = vec.length/2 - 1; i >= 0; i--) {
                        heapify(vec, i, vec.length - 1);
                }
        }
    
        /**
         * sorts array by heapsort in a certain range
         * @param vec the array in which this happens
         */
        public static void heapSort(Item[] vec) 
                                throws NullPointerException {
                if (vec == null) throw new NullPointerException();
    
                Item temp;
                int last;
                int n = vec.length;
            
                createHeap(vec);
                for (last = n-1; last > 0; last--) {
                        // exchange top component with 
                        // current last component of vec
                        temp = vec[0]; 
                        vec[0] = vec[last]; 
                        vec[last] = temp;
                        // call Heapify to to reestablish heap property
                        heapify(vec, 0, last-1);
                }//endfor
        }


}
