package src.gui.algos;

import src.gui.GUI;
import src.gui.AlgosKeyListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

public abstract class AlgoActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(AlgosKeyListener.e){
            Thread t = new Thread(()->{
                initAlgo().explanationSort();
            });
            t.start();

        }
        else {
            if (!GUI.allowMultiple && (!GUI.algos.isEmpty())) {
                return;
            }
            if ((GUI.bucket != null || GUI.radix != null)) return;
            Thread t = new Thread(() -> {
                SortingAlgorithm m = initAlgo();
                GUI.algos.add(m);
                m.sort(GUI.arr);
                GUI.algos.remove(m);
                m = null;

            });
            t.start();

        }
    }

    public abstract SortingAlgorithm initAlgo();
}
