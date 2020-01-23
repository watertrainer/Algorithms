package src.gui.Algorithms;

import src.GUI;

public abstract class  SortingAlgorithm {
    boolean running = false;
    int wait = 0;
    int comparisons;
    int accesses;
    int swaps;
    protected String info = "";
    public boolean isRunning(){
        return running;
    };
    public void sort(int[] ar){

        System.out.println(info +". Length: "+ar.length+" Compared: "+comparisons+" Accessed: "+accesses+" Swapped: "+swaps);
    };

    public String getInfo(){
        return (info +" Compared: "+comparisons+" Accessed: "+accesses+" Swapped: "+swaps);
    }
    public void swap(int in1,int in2,int[] ar){
        int save = ar[in1];
        ar[in1] = ar[in2];
        ar[in2] = save;
        swaps++;
        accesses+=2;
        GUI.repaint();
        GUI.sleep(wait);
    }

}
