package src;

import java.util.Scanner;

public class BubbleSort {
    Scanner s = new Scanner(System.in);
    private int save;
    private int n = 10;
    int[] arr = new int[n];


    private BubbleSort main;


    public void eingabe(){
        for(int i = 0; i <= n-1; i++) {
            System.out.print("Bitte den nächsten Wert eingeben: ");
            arr[i] = s.nextInt();
        }
    }

    public void sortieren(){
        for(int o = 1; o <= n-1; o++) {
            for (int i = 0; i <= n - 2; i++) {

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
        for(int i = 0; i <= n-1; i++) {
            System.out.print(arr[i] + "  ");
        }
    }

    public int getN() {
        return n;
    }

    public int[] getArr() {
        return arr;
    }

    public BubbleSort(){
        this.main = this;
        eingabe();
    }

    public void onTick(){
        while (true){
            sortieren();
        }
    }

    public static void main(String[] args){

        BubbleSort main = new BubbleSort();
        main.onTick();
        main.ausgabe();

    }

}
