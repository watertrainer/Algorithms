package Algorithm;

import GUI.Graph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An abstract of how an Algorithm should be implemented.
 */
public abstract class GraphAlgorithm implements ActionListener {
    private Thread run;
    private boolean running;
    protected final Graph g;


    public GraphAlgorithm(Graph g){
        this.g = g;
    }
    /**
     * Should calculate the Algorithm for the given Graph
     * Please call setStart and setEnd to select a end and start node (only when necessary).
     * At the end set Start and End to null (using the methods!).
     * @param visual should the Algorithm be visualized? Please make use of the select() and deselect() methods for this
     *               You can select a Node (BUT SAVE THE RETURNED COLOR) of select(Node) and it will be rendered as
     *               "used". Deselect a Node using the deselect(Node, previousColor). You can select a Edge the same way.
     *               You can sleep using the sleep(int) method.
     *
     */
    public abstract void calculateAlgorithm(boolean visual);

    @Override
    public void actionPerformed(ActionEvent e) {
        running = true;
        run = new Thread(()->{

            g.setStarted(true);
            calculateAlgorithm(true);
            g.setStarted(false);
        });
        run.start();
        running = false;
    }

    public void cancel(){
        running = false;
        run.interrupt();
    }

    public void pause() throws InterruptedException {
            while (!running) {
                run.wait();
            }
    }

    public void cont(){
        running = true;
        run.notifyAll();
    }
}
