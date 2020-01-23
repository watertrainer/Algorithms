package src.gui.Algorithms;

import src.GUI;

import java.util.Arrays;

public class Quicksort {
    static int wait = 4;
    static int count = 0;
    static boolean running = false;

    public static void sort(int[] ar){
        running = true;
        sortRec(ar,0,ar.length-1);
        running = false;
    }
    public static int[] sortRec(int[] ar,int links, int rechts){
        if(links<rechts){
            int t = partition(ar,links,rechts);

            sortRec(ar,links,t-1);
            sortRec(ar,t+1,rechts);
        }
        return ar;
    }

    public static int partition(int[] ar,int links, int rechts){
        int po = ar[rechts];
        int i = 0;
        int j = 1;
        o:
        while (i<j){

            for(i = links; i<rechts;i++){

                if(ar[i]>=po) {
                    break;
                }




            }
            for(j = rechts-1; j>links;j--){
                if(ar[j]<po){
                    break;

                }
            }
            if(i<j){
                swap(i,j,ar);
            }

        }
        swap(i,rechts,ar);
        return i;
    }
    public static void swap(int i1, int i2, int[] arr){
        int save = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = save;
        GUI.repaint();
        GUI.sleep(2);
    }

}
