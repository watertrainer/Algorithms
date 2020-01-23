package src.gui.Algorithms;

import src.GUI;

import java.util.Arrays;

public class Quicksort extends SortingAlgorithm{
     int count = 0;

     public Quicksort(){
         super();
         info = "Quicksort: 4ms delay per swap";
         wait = 4;
     }



    public void sort(int[] ar){
        running = true;
        sortRec(ar,0,ar.length-1);
        running = false;

        super.sort(ar);
    }


    public int[] sortRec(int[] ar,int links, int rechts){
        if(links<rechts){
            int t = partition(ar,links,rechts);

            sortRec(ar,links,t-1);
            sortRec(ar,t+1,rechts);
        }
        return ar;
    }

    public int partition(int[] ar,int links, int rechts){
        int po = ar[rechts];
        accesses++;
        int i = 0;
        int j = 1;
        o:
        while (i<j){
            comparisons++;
            for(i = links; i<rechts;i++){

                if(ar[i]>=po) {
                    comparisons++;
                    accesses++;
                    break;
                }




            }
            for(j = rechts-1; j>links;j--){
                if(ar[j]<po){

                    comparisons++;
                    accesses++;
                    break;

                }
            }
            if(i<j){
                comparisons++;
                swap(i,j,ar);
            }

        }
        swap(i,rechts,ar);
        return i;
    }


}
