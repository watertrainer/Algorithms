package src;

public class IntroSort {

    public static int[] insertionSort(int[] arr){

        int save;

        for(int i = 1; i < arr.length; i++){

            save = arr[i];
            int j = i;

            while(j > 0 && arr[j-1] > save){

                arr[j] = arr[j-1];
                j--;
            }

            arr[j] = save;
        }
        return arr;
    }


    public static int[] heapSort(int[] arr){

        int n = arr.length;

        for(int i = n / 2 - 1; i >= 0; i--){
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
        }

        heapen(arr, sizeH, largest);
    }









    public static int[] sort(int[] arr){

        int limit_Tiefe = 2*(int)(Math.floor(Math.log(arr.length)));
        int n = arr.length;

        if(n <= 16){                                                            //InsertionSort
            insertionSort(arr);

        }else if(limit_Tiefe == 0){                                             //HeapSort
            heapSort(arr);

        }else{
            //int p = partition(arr);

        }

        return arr;
    }




    public static void main(String[] args){

        int[] unsortiert = new int[1000];
        int obergrenze = 1000;

        for(int i = 0; i < unsortiert.length; i++){
            unsortiert[i] = (int) (Math.random()*obergrenze);
        }

        int[] sortiert = sort(unsortiert);
        for(int i=0; i < sortiert.length; i++){
            System.out.print(sortiert[i] + ", ");
        }

    }
}
