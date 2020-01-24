package src.Algorithms;

public class Radixsort {

    public static int[] sort(int[] arr){

        int digit = 1;
        int maxNum = getMax(arr);
        int n = arr.length;
        int[] darr = new int[n];

        while(maxNum/digit > 1){

            int count[]=new int[10];

            for (int i = 0; i < 10; i++) {
                count[i] = 0;
            }

            for (int i = 0; i < n; i++){
                count[ (arr[i] / digit) % 10]++;
            }

            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }

            for (int i = n - 1; i >= 0; i--){
                darr[count[ (arr[i] / digit) % 10] - 1] = arr[i];
                count[ (arr[i] / digit) % 10]--;
            }

            for (int i = 0; i < n; i++){
                arr[i] = darr[i];
            }

            digit *= 10;
        }

        return arr;
    }



    public static int getMax (int[] arr){

        int maxNum = 0;

        for(int i = 0; i < arr.length; i++){
            if(arr[i] > maxNum){
                maxNum = arr[i];
            }
        }
        return maxNum;
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
