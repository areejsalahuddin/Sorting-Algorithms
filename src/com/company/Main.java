package com.company;

public class Main {


    static void  selectionSort(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            //find minimum in unsorted array
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            //swap min with first element
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;

        }
    }

    static void bubbleSort(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

    }


    static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && (arr[j] > key)) {
                arr[j + 1] = arr[j];
                j=j-1;
            }
            arr[j + 1] = key;
        }

    }

    //divide and conquer approach
    static void merge(int arr[],int start,int mid,int end) {


        //sizes of sub arrays
        int n1 = mid - start + 1;
        int n2 = end - mid;
        //temp arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        if(start<end){
            //copy data to temp arrays
            //left array
            for (int i = 0; i < n1; i++) {
                L[i] = arr[start + i];
            }
            //right array
            for (int j = 0; j < n2; ++j) {
                R[j] = arr[mid + 1 + j];
            }
            // Initial indexes of first and second sub arrays
            int i = 0, j = 0;

            // Initial index of merge
            int k = start;
            while (i < n1 && j < n2) {
                if (L[i] <= R[j]) {
                    arr[k] = L[i];
                    i++;
                }
                else {
                    arr[k] = R[j];
                    j++;
                }
                k++;
            }

            /* Copy remaining elements of L[] if any */
            while (i < n1) {
                arr[k] = L[i];
                i++;
                k++;
            }

            /* Copy remaining elements of R[] if any */
            while (j < n2) {
                arr[k] = R[j];
                j++;
                k++;
            }
        }

    }

    static void mergeSort(int arr[], int l, int r) {

        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }



    static void heapSort(int arr[]) {
        int n = arr.length;

        //Rearrange array (building heap)
        for (int i = (n / 2) - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        //swap root and last node and delete last node from heap
        for (int i = n - 1; i >= 0; i--) {
            //Current root moved to the end
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;

            heapify(arr, i, 0);//calling max heapify on the heap reduced
        }
    }

    static void heapify(int arr[], int n, int i) {
        int max = i; //Initialize max as root
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        //If left child is greater than root
        if (leftChild < n && arr[leftChild] > arr[max])
            max = leftChild;

        //If right child is greater than max
        if (rightChild < n && arr[rightChild] > arr[max])
            max = rightChild;

        //If max is not root swap them
        if (max != i) {
            int temp = arr[i];
            arr[i] = arr[max];
            arr[max] = temp;

            //heapify the affected sub-tree recursively
            heapify(arr, n, max);
        }
    }



    static void quickSort(int[] arr, int start, int end) {

        int pivot = arr[(start+ end)/2];
        int i = start;
        int j = end;

        do {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;
            if (i <= j)
            {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        } while (i <= j);

        if (start < j) quickSort(arr, start, j);
        if (i < end) quickSort(arr, i, end);

    }

    static int [] copyArray(int arr[] ,int index){
        int temp[]= new int[index];
        for (int p = 0; p < index; p++) {
            temp[p] = arr[p];
        }
        return temp;
    }
    public static void main(String[] args) {

        for(int j=1000; j<1000000; j=j*10)
        {
            int arr[] = new int[j];
            int min = -j;
            int max = j;
            for (int i = 0; i < arr.length; i++) {
                arr[i] =(int) ((Math.random() * (max - min)) + min);
            }
            int temp[] = copyArray(arr,j);
            long startHeap = System.currentTimeMillis();
            heapSort(temp);
            long endHeap = System.currentTimeMillis();
            long executionTimeOfHeap = endHeap - startHeap;
            System.out.println("Execution time of array ["+j+"] using Heap Sorting: " + executionTimeOfHeap + " milliseconds");

            temp = copyArray(arr,j);
            long startQuick = System.currentTimeMillis();
            quickSort(temp, 0, temp.length - 1);
            long endQuick = System.currentTimeMillis();
            long executionTimeOfQuick = endQuick - startQuick;
            System.out.println("Execution time of array ["+j+"] using Quick Sorting: " + executionTimeOfQuick + " milliseconds");

            temp = copyArray(arr,j);
            long startInsertion = System.currentTimeMillis();
            insertionSort(temp);
            long endInsertion = System.currentTimeMillis();
            long executionTimeOfInsertion = endInsertion - startInsertion;
            System.out.println("Execution time of array ["+j+"] using Insertion Sorting: " + executionTimeOfInsertion + " milliseconds");

            temp = copyArray(arr,j);
            long startSelection = System.currentTimeMillis();
            selectionSort(temp);
            long endSelection = System.currentTimeMillis();
            long executionTimeOfSelection = endSelection - startSelection;
            System.out.println("Execution time of array ["+j+"] using Selection Sorting: " + executionTimeOfSelection + " milliseconds");

            temp = copyArray(arr,j);
            long startBubble = System.currentTimeMillis();
            bubbleSort(temp);
            long endBubble = System.currentTimeMillis();
            long executionTimeOfBubble = endBubble - startBubble;
            System.out.println("Execution time of array ["+j+"] using Bubble Sorting: " + executionTimeOfBubble + " milliseconds");


            temp = copyArray(arr,j);
            long startMerge = System.currentTimeMillis();
            mergeSort(temp, 0, temp.length - 1);
            long endMerge = System.currentTimeMillis();
            long executionTimeOfMerge = endMerge - startMerge;
            System.out.println("Execution time of array ["+j+"] using Merge Sorting: " + executionTimeOfMerge + " milliseconds");
            System.out.println("----------------------------------------------------------------------------------------------" );


        }

    }
}
