package Algorithm;

import GUI.Graph;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An abstract of how an Algorithm should be implemented.
 */
public abstract class GraphAlgorithm implements ActionListener {
    private volatile Thread run;
    private boolean running;
    protected final Graph g;
    private boolean paused;


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
        if(g.started)
            return;
        running = true;
        run = new Thread(()->{

            g.setStarted(true);
            calculateAlgorithm(true);
            g.setStarted(false);
            running = false;
            System.out.println("Finished");
        });
        run.start();
        System.out.println("Started Me on "+run);
    }

    public void cancel(){
        running = false;
        run.interrupt();
        System.out.println("Interrupted "+run );
        g.setStarted(false);
    }

    public void pause() throws InterruptedException {
        System.out.println("Paused");
        if(paused)
            return;
        paused = true;
        Thread t = new Thread(() -> {
            synchronized (run){
            while (paused) {
                try {
                    run.wait(1);
                } catch (InterruptedException ignored) {
                    System.out.println("Exception");
                }
            }
        }});
        t.start();
    }

    public void cont(){
        paused = false;
        synchronized (run){
            run.notifyAll();
        }
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isPaused(){
        return paused;
    }
    public boolean select(Node cur) {
        Color p = cur.toRender;
        cur.toRender = new Color(55
                , 113, 255);
        g.f.repaint();
        return sleep(500);
    }

    public boolean select(Node cur, Color c) {
        Color p = cur.toRender;
        cur.toRender = c;
        g.f.repaint();
        return sleep(500);
    }
    public boolean select(Edge e,Color c){
        Color p = e.toRender;
        e.toRender = c;
        g.f.repaint();
        return sleep(500);
    }

    public boolean select(Edge e){
        Color p = e.toRender;
        e.toRender = new Color(55
                , 113, 255);
        g.f.repaint();
        return sleep(500);
    }
    public void deselect(Node cur){
        cur.toRender = null;
    }
    public void deselect(Edge i){
        i.toRender = null;
    }
    public boolean sleep(int i) {
        try {
            if(Thread.interrupted()){
                System.out.println("interrupted");
                g.info.setText("Canceled");
                return true;
            }
            if(paused)
            {
                execPause();
            }
            Thread.sleep(i);
        } catch (InterruptedException ex) {
            System.out.println("Exception");
            g.info.setText("Canceled");
            return true;
        }
        return false;
    }
    public void execPause(){
        System.out.println("Waiting on paused");
        synchronized (run){
            try {
                while (paused)
                    run.wait();
            }catch(Exception e){

            }
            }
    }
}
