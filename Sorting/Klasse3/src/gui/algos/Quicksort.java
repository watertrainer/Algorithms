package src.gui.algos;

import src.gui.GUI;
public class Quicksort extends SortingAlgorithm{
     int count = 0;

     public Quicksort(){
         super();
         info = "Quicksort: 4ms delay per swap     ";
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
        comparisons++;
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

                    break;
                }
                comparisons++;
                accesses++;



            }
            for(j = rechts-1; j>links;j--){
                if(ar[j]<po){


                    break;

                }
                comparisons++;
                accesses++;
            }
            if(i<j){
                swap(i,j,ar);
            }
            comparisons++;

        }
        swap(i,rechts,ar);
        return i;
    }


}
