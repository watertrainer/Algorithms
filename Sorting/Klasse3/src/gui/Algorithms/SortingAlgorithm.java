package src.gui.Algorithms;

public abstract class  SortingAlgorithm {
    boolean running = false;
    protected String info = "";
    public boolean isRunning(){
        return running;
    };
    public abstract void sort(int[] ar);
    public String getInfo(){
        return info;
    }

}
