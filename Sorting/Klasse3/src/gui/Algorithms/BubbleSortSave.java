package src.gui.Algorithms;

import src.GUI;

public class BubbleSortSave extends SortingAlgorithm{


    public BubbleSortSave(){

        info = "BubbleSort:5ms after each complete Iteration";
        wait = 0;
    }
    public void sort(int[] arr){
        boolean changed = true;
        int save;
        while (changed) {
            changed = false;
            for (int i = 0; i < arr.length-1; i++) {

                if (arr[i] > arr[i + 1]) {
                    comparisons++;
                    accesses+=2;
                    swap(i,i+1,arr);
                    changed = true;

                }

            }
            GUI.repaint();
            GUI.sleep(5);
        }

        super.sort(arr);
    }


}
