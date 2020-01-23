package src.gui.Algorithms;

import src.GUI;

public class BubbleSortSave {


    static boolean running = false;
    public static void sort(int[] arr){
        boolean changed = true;
        int save;
        while (changed) {
            changed = false;
            for (int i = 0; i < arr.length-1; i++) {

                if (arr[i] > arr[i + 1]) {
                    save = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = save;
                    changed = true;

                }

            }
            GUI.repaint();
            GUI.sleep(5);
        }
    }
}
