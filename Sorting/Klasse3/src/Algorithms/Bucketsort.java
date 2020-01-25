package src.Algorithms;

import src.gui.GUI;

import java.awt.*;
import java.util.ArrayList;

public class Bucketsort {

    static ArrayList<Integer>[] buk;
    static int drarIn,buIn = 0;


    /**
     * Sortiert ein Array nach src.gui.Algorithms.Bucketsort
     * @param ar Das zu sortierende Array
     * @param k Die Anzahl an Buckets die verwendet werden soll
     */
    public static void sort(int[] ar, int k){
        buk = new ArrayList[k];
        for(int i = 0;i<buk.length;i++){
            buk[i] = new ArrayList<>();
        }
        int M = maxIn(ar); //Der Maximale Wert
        for(int i = 0;i<ar.length;i++){
            //Jedes Element wird seinem Bucket hinzugefügt.
            //Wir verwenden gleichmäßige Buckets, das heißt, dass man erst das Verhätlnis berechnet, wie viel "Prozent" von
            //der Max value das Element hat und dann wird das mit der Anzahl an Buckets multipliziert, wodurch der Index
            //im Buk Array gefunden wird
            buk[(int) Math.floor((k-1)*ar[i]/M)].add(ar[i]);
        }

        int arIn = 0;
        for(int i = 0;i<k;i++){
            //Die Buckets werden in das Array Kopiert
            for(int j:toArray(buk[i])){
                ar[arIn] = j;
                arIn++;

            };
        }
        insertionSort(ar);
    }

    public static int[] toArray(ArrayList<Integer> ar){
        int[] re = new int[ar.size()];
        for(int i = 0;i<ar.size();i++){
            re[i]=ar.get(i);
        }
        return re;
    }
    public static int[] insertionSort(int[] sortieren) {
        int temp;
        for (int i = 1; i < sortieren.length; i++) {
            temp = sortieren[i];
            int j = i;
            while (j > 0 && sortieren[j - 1] > temp) {
                sortieren[j] = sortieren[j - 1];
                j--;
            }
            sortieren[j] = temp;
        }
        return sortieren;
    }

    public static int maxIn(int ar[]){
        int M = ar[0];
        for(int i = 1;i<ar.length;i++){
            if(ar[i]>M){
                M=ar[i];
            }
        }
        return M;
    }

}
