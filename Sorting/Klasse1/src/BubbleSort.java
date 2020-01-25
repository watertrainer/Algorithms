package src;

import src.gui.GUI;

public class BubbleSort {

    private static int save;

    int[] arr = {10, 5, 7, 16, 3, 1, 4, 8, 9, 2};


    public static void sortieren(int[] arr){
        for(int j = 0; j < arr.length - 1; j++) {
            for (int i = 0; i < arr.length - 1; i++) {

                if (arr[i] > arr[i + 1]) {
                    save = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = save;
                    GUI.repaint();
                    GUI.sleep(1);
                }

            }
        }
        //ausgabe();
    }
    public void ausgabe(){
        for(int i = 0; i <= arr.length-1; i++) {
            System.out.print(arr[i] + "  ");
            //lol
        }
    }


    public static void main(String[] args){
        BubbleSort main = new BubbleSort();
        //main.sortieren();
    }

}
