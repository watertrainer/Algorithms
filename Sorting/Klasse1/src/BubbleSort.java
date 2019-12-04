import java.util.Scanner;


public class BubbleSort {

    private int save;

    int[] arr = {10, 5, 7, 16, 3, 1, 4, 8, 9, 2};


    public void sortieren(){
        for(int j = 0; j < arr.length - 1; j++) {
            for (int i = 0; i < arr.length - 1; i++) {

                if (arr[i] > arr[i + 1]) {
                    save = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = save;
                }

            }
        }
        ausgabe();
    }
    public void ausgabe(){
        for(int i = 0; i <= arr.length-1; i++) {
            System.out.print(arr[i] + "  ");
            //lol2
        }
    }


    public static void main(String[] args){

        BubbleSort main = new BubbleSort();
        main.sortieren();

    }

}
