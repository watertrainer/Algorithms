package src.gui.algos;

import src.gui.GUI;

public class Randomize extends SortingAlgorithm {
    public Randomize(){
        info="Randomize: 2ms per Element      ";
    }
    public boolean fast = false;
    @Override
    public void sort(int[] ar) {
        for(int i = 0; i < ar.length; i++){
            ar[i] = (int) (Math.random()*GUI.maxValue);
            writes++;
            GUI.repaint();
            if(!fast)
                GUI.sleep(2);
        }

        super.sort(ar);
    }

}
