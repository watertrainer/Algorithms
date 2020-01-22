package src;

public class BubbleSortSave {


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
