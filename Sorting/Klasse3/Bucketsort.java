import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bucketsort {
    public static void main(String[] args){
        int ar[] = {1,2,3,4,5,6,2,50,24,47,9};
        sort(ar,5);
    }

    /**
     * Sortiert ein Array nach Bucketsort
     * @param ar Das zu sortierende Array
     * @param k Die Anzahl an Buckets die verwendet werden soll
     */
    public static void sort(int[] ar, int k){
        ArrayList<Integer>[] buk = new ArrayList[k];
        for(int i = 0;i<buk.length;i++){
            buk[i] = new ArrayList<>();
        }
        int M = maxIn(ar); //Der Maximale Wert
        for(int i = 0;i<ar.length;i++){
            //Jedes Element wird seinem Bucket hinzugefügt.
            //Wir verwenden gleichmäßige Buckets, das heißt, dass
            buk[(int) Math.floor((k-1)*ar[i]/M)].add(ar[i]);
        }

        System.out.println(Arrays.toString(buk));
        //Index im Ar, um die Buckets wieder zusammenzuführen
        int arIn = 0;
        //Für jedem Bucket
        for(int i = 0;i<k;i++){
            //Hier wird der Bucket mit Insertion Sort sortiert und dann auch direkt darüber iteriert (for-each-Schleife)
            for(int j:insertionSort(toArray(buk[i]))){
                ar[arIn] = j;
                arIn++;
            };
        }
        System.out.println(Arrays.toString(ar));
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
