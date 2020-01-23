package src.Algorithms;

import src.GUI;

public class Heapsort {

    public static int[] sort(int[] arr){

        int n = arr.length;

        for(int i = (n / 2) - 1; i >= 0; i--){

            heapen(arr, n, i);

        }

        for(int i = n - 1; i >= 0; i--){
            int save = arr[0];
            arr[0] = arr[i];
            arr[i] = save;

            heapen(arr, i, 0);

        }

        return arr;
    }

    public static void heapen(int[] arr, int sizeH, int i){

        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if(left < sizeH && arr[left] > arr[largest]){
            largest = left;
        }

        if(right < sizeH && arr[right] > arr[largest]){
            largest = right;
        }

        if(largest != i){
            int save = arr[i];
            arr[i] = arr[largest];
            arr[largest] = save;


            heapen(arr, sizeH, largest);

        }


    }


}

