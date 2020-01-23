package src.gui.Algorithms;

import src.GUI;

public class Heapsort extends SortingAlgorithm{

    public Heapsort(){
        info = "Heapsort: 2ms for every swap";
        wait = 2;
    }
    public synchronized void sort(int[] arr){

        int n = arr.length;

        for(int i = (n / 2) - 1; i >= 0; i--){

            heapen(arr, n, i);
        }

        for(int i = n - 1; i >= 0; i--){
            swap(0,i,arr);
            heapen(arr, i, 0);

        }

        super.sort(arr);

    }

    public synchronized void heapen(int[] arr, int sizeH, int i){

        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if(left < sizeH && arr[left] > arr[largest]){
            accesses+=2;
            comparisons+=2;
            largest = left;
        }

        if(right < sizeH && arr[right] > arr[largest]){
            accesses+=2;
            comparisons+=2;
            largest = right;
        }

        if(largest != i){
            swap(i,largest,arr);
            heapen(arr, sizeH, largest);

        }


    }


}

