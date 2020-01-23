package src.gui.Algorithms;

import src.GUI;

public class Randomize extends SortingAlgorithm {
    public Randomize(){
        info="Randomize:2ms per Element";
    }
    public boolean fast = false;
    @Override
    public void sort(int[] ar) {
        for(int i = 0; i < GUI.arrLength; i++){
            ar[i] = (int) (Math.random()*GUI.maxValue);
            GUI.repaint();
            if(!fast)
                GUI.sleep(2);
        }
    }

}
