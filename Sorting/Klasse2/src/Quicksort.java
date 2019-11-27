package src;

import java.util.Arrays;

public class Quicksort {
    public static int[] sortieren(int[] ar,int links, int rechts){
        if(links<rechts){
            int t = partition(ar,links,rechts);
            sortieren(ar,links,t-1);
            sortieren(ar,t+1,rechts);
        }
        return ar;
    }

    public static int partition(int[] ar,int links, int rechts){
        int p = (int)(Math.random()*(rechts-links)+links+1);
        int po = ar[p];
        ar[p] = ar[rechts];
        ar[rechts] = po;
        System.out.println(Arrays.toString(ar));
        System.out.println(p+","+po);
        o:
        while (true){

            for(int i = links; i<rechts;i++){

                if(ar[i]>po){
                    for(int j = rechts; j>links;j--){
                        if(ar[j]<po){
                            if(i>j){
                                ar[rechts] = ar[i];
                                ar[i] = po;
                                return i;
                            }
                            else{
                                int k1 = ar[j];
                                ar[j] = ar[i];
                                ar[i] = k1;
                                System.out.println(Arrays.toString(ar));
                                continue o;
                            }
                        }


                    }
                    ar[rechts] = ar[links];
                    ar[links] = po;
                    return links;
                }

            }
            return rechts;

        }
    }

    public static void main(String[] args){
        System.out.println(Arrays.toString(sortieren(new int[]{1, 3, 5, -6, 2, 54, 100000, 4}, 0, 7)));
    }
}
