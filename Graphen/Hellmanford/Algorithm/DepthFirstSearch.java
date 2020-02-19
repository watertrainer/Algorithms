package Algorithm;

import GUI.Graph;

import java.util.LinkedList;

public class DepthFirstSearch extends GraphAlgorithm {
    public DepthFirstSearch(Graph g) {
        super(g);
    }

    @Override
    public void calculateAlgorithm(boolean visual) {
        if (visual) {
        g.info.setText("Starting");
        if(sleep(1000))
            return;
    }
        int minx = Integer.MAX_VALUE;
        int maxx = 0;
        int inx = -1;
        int inmx = -1;
        for (int i = 0; i < g.nodes.size(); i++) {
            if (g.nodes.get(i).x < minx) {
                minx = g.nodes.get(i).x;
                inx = i;
            } else if (g.nodes.get(i).x > maxx) {
                maxx = g.nodes.get(i).x;
                inmx = i;
            }
        }
        if (inx == -1 || inmx == -1) {
            g.info.setText("");
            return;
        }
        g.setStart(g.nodes.get(inx));
        g.setEnd(g.nodes.get(inmx));
        if(visual) {
            g.info.setText("Start and End selected. Starting to find the End node");
        }
        if(sleep(2000))return;
        DFS(0,g.start,new LinkedList<Edge>(),visual);
    }
    @org.jetbrains.annotations.Nullable
    public LinkedList<Edge> DFS(int d, Node cur, LinkedList<Edge> ins, boolean visual) {
        if(visual){
            g.info.setText("Going to next Node");
            if(sleep(2500)) return null;
            if(select(cur))
                return null;

        }

        if (cur.visited) {
            if (visual) {
                g.info.setText("Node already visited!");
                if(sleep(2500))
                    return null;
            }
            ins.removeLast();
            cur.visited = false;
            return null;
        }
        if (cur == g.end) {

            if (visual) {
                g.info.setText("Found end Node");
                if(sleep(1000))
                    return null;
            }


            cur.visited = false;
            return ins;
        }


        cur.visited = true;
        g.info.setText("Selecting next Node");
        if(sleep(2500))
            return null;
        for (int i = 0; i < cur.edges.size(); i++) {
            if (visual) {
                g.info.setText("Found next Node");
                if(select(cur.edges.get(i)))
                    return null;
                sleep(2500);
            }
            ins.add(cur.edges.get(i));
            if (DFS(d + 1, cur.edges.get(i).end, ins, visual) != null) {
                if (visual){
                    //deselect(cur);
                    //deselect(ins.getLast());
                }
                cur.visited = false;
                return ins;
            }
            //ins.removeLast();

        }

        if (visual){
            deselect(cur);
            if(!ins.isEmpty())
                deselect(ins.getLast());
        }

        cur.visited = false;
        if (!ins.isEmpty())
            ins.removeLast();

        return null;
    }
}
