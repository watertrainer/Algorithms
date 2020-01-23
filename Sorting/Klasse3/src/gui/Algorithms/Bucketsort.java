package src.gui.Algorithms;

import src.GUI;

import java.awt.*;
import java.util.ArrayList;

public class Bucketsort extends SortingAlgorithm{
    ArrayList<Integer>[] buk;
    int drarIn,buIn = 0;
    public boolean insertion;

    public Bucketsort(){
        info = "Bucketsort: 2ms for each inserted Element in a Bucket and in InsertionSort";
    }
    /**
     * Sortiert ein Array nach src.gui.Algorithms.Bucketsort
     * @param ar Das zu sortierende Array
     * @param k Die Anzahl an Buckets die verwendet werden soll
     */
    public void sort(int[] ar, int k){
        running = true;
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
            GUI.sleep(2);
            GUI.repaint();
        }

        int arIn = 0;
        for(int i = 0;i<k;i++){
            //Die Buckets werden in das Array Kopiert
            for(int j:toArray(buk[i])){
                ar[arIn] = j;
                arIn++;

            };
        }
        insertion = true;
        insertionSort(ar);
        insertion = false;
        GUI.repaint();
        running = false;
    }

    public int[] toArray(ArrayList<Integer> ar){
        int[] re = new int[ar.size()];
        for(int i = 0;i<ar.size();i++){
            re[i]=ar.get(i);
        }
        return re;
    }

    public int[] insertionSort(int[] sortieren) {
        int temp;
        for (int i = 1; i < sortieren.length; i++) {
            temp = sortieren[i];
            int j = i;
            while (j > 0 && sortieren[j - 1] > temp) {
                sortieren[j] = sortieren[j - 1];
                GUI.repaint();
                j--;
            }
            GUI.sleep(2);
            sortieren[j] = temp;
        }
        return sortieren;
    }

    public int maxIn(int ar[]){
        int M = ar[0];
        for(int i = 1;i<ar.length;i++){
            if(ar[i]>M){
                M=ar[i];
            }
        }
        return M;
    }
    public void draw(Graphics g){
        if(insertion){

        }else {
            int x = 0;
            for (int i = 0; i < buk.length; i++) {
                for (int j = 0; j < buk[i].size(); j++) {
                    g.drawLine(x, GUI.maxValue + 50, x, GUI.maxValue + 50 - buk[i].get(j));
                    x++;
                }
            }
        }
    }

    @Override
    public void sort(int[] ar) {
        sort(ar,10);
    }


}