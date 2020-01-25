package src.gui.algos;

import src.gui.GUI;

import javax.swing.*;
import java.awt.*;

public abstract class SortingAlgorithm{
    boolean running = false;
    int wait = 0;
    int comparisons;
    int accesses;
    int writes;
    int swaps;
    protected String info = "";
    public boolean isRunning(){
        return running;
    };
    public void sort(int[] ar){

        System.out.println(getInfo());
    }

    public void explanationSort(){

    }

    public void drawExplanation(Graphics g){

    }

    public String getInfo(){
        return (info +"\t Compared: "+comparisons+" Accessed: "+accesses+" Writes: "+writes+" Swapped: "+swaps);
    }
    public void swap(int in1,int in2,int[] ar){
        int save = ar[in1];
        ar[in1] = ar[in2];
        ar[in2] = save;
        swaps++;
        writes+=2;
        accesses+=2;
        GUI.repaint();
        GUI.sleep(wait);
    }


}
