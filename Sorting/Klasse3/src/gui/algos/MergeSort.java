package src.gui.algos;

import src.gui.GUI;

public class MergeSort extends SortingAlgorithm {
    public void sort(int[] ar) {
        sortRec(ar, 0, ar.length - 1);
        super.sort(ar);
    }

    public void merge(int a, int m, int e, int[] ar) {
        int leac = m - a+1;
        int leec = e - m;
        if(leac == 0 || leec == 0)
            return;
        int[] aC = new int[leac];
        int[] eC = new int[leec];

        for (int i = 0; i < leac; i++) {
            aC[i] = ar[i + a];
            accesses++;
            writes++;
        }
        for (int i = 0; i < leec; i++) {
            eC[i] = ar[i + m+1];
            accesses++;
            writes++;
        }

        int i = 0, j = 0;
        int in = a;
        while (i < leac && j < leec) {
            comparisons+=2;
            if (aC[i] <= eC[j]) {
                ar[in] = aC[i];
                accesses++;
                comparisons++;
                writes++;
                i++;
            } else {
                ar[in] = eC[j];
                writes++;
                accesses++;
                j++;
            }
            accesses+=2;
            comparisons++;
            in++;
            GUI.repaint();
            GUI.sleep(1);
        }
        while(i<leac){
            ar[in] = aC[i];
            writes++;
            accesses++;
            comparisons++;
            i++;
            in++;
            GUI.repaint();
            GUI.sleep(1);
        }
        while(j<leec){
            ar[in] = eC[j];
            writes++;
            accesses++;
            comparisons++;
            j++;
            in++;
            GUI.repaint();
            GUI.sleep(1);
        }
    }

    public void sortRec(int[] ar, int a, int e) {
        if (a < e) {
            comparisons++;
            int m = a+ (e-a)/2;
            sortRec(ar, a, m);
            sortRec(ar, m+1 , e);
            merge(a, m, e, ar);
        }
    }

    public MergeSort(){
        info = "MergeSort: 1ms for each merged Element";
        wait = 0;
    }
}
