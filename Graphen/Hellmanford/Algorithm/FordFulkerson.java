package Algorithm;

import GUI.Graph;

import java.awt.*;
import java.util.LinkedList;

public class FordFulkerson extends GraphAlgorithm {
    public FordFulkerson(Graph g) {
        super(g);
    }

    @Override
    public void calculateAlgorithm(boolean visual) {
        if (visual)
            g.info.setText("Starting");
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


        if (visual) {
            for (Node node : g.nodes) {
                node.sortNodes();
            }
            g.info.setText("Start and finish chosen. Starting to find the max tree");
            g.f.repaint();
            g.sleep(3000);
            g.info.setText("Finding Path");
        }
        for (Node n : g.nodes)
            n.origInd = n.edges.size();
        for (Node n : g.nodes) {
            n.createAugmentedEdges();
        }
        Node cur;
        LinkedList<Edge> ins = new LinkedList<>();
        int maxFlow = 0;
        while (DFS(0, g.start, ins, visual) != null) {
            cur = g.start;

            if (visual)
                g.info.setText("Found Path");
            int minc = Integer.MAX_VALUE;
            for (Edge in : ins) {
                //int c = cur.getCapTo(cur.child.get(in));
                int c = in.capacity - in.used;
                minc = Math.min(minc, c);
                System.out.println("New min:" + minc + "for " + in.capacity + " " + in.used);
                //cur = cur.child.get(in);
            }

            if (visual) {
                g.info.setText("Minimal capacity is " + minc);
                g.f.repaint();
                g.sleep(3000);
            }
            cur = g.start;

            for (int i = 0; i < ins.size(); i++) {
                //cur.getEdgeTo(cur.child.get(ins.get(i))).used += minc;
                ins.get(i).addUsed(minc);
                //cur = cur.child.get(ins.get(i));
                cur = ins.get(i).end;

                if (visual) {
                    cur.toRender = null;
                    g.f.repaint();
                    g.sleep(100);
                }
            }
            maxFlow += minc;
            ins = new LinkedList<>();

            if (visual) {
                g.info.setText("Finding Path");
            }
        }

        if (visual) {
            g.info.setText("No Path Found!");
            g.sleep(5000);
            g.info.setText("Max Flow found " + maxFlow);

        }
        g.setStarted(false);
        for (Node n : g.nodes) {
            n.removeAugmented();
        }
        g.start.toRender = null;
        g.end.toRender = null;
        g.f.repaint();

    }

    @org.jetbrains.annotations.Nullable
    public LinkedList<Edge> DFS(int d, Node cur, LinkedList<Edge> ins, boolean visual) {
        Color p = null;

        if (visual) {
            p = g.select(cur);
        }
        if (cur.visited) {
            if (visual) {
                g.deselect(cur, p);
                g.deselect(ins.getLast(), null);
            }
            ins.removeLast();
            cur.visited = false;
            return null;
        }
        if (cur == g.end) {

            if (visual) {
                g.deselect(cur, p);
                g.deselect(ins.getLast(), null);
            }


            cur.visited = false;
            return ins;
        }


        cur.visited = true;
        for (int i = 0; i < cur.edges.size(); i++) {
            if (!cur.edges.get(i).canHoldCapacity(1))
                continue;
            if (visual) {
                    g.select(cur.edges.get(i));
            }
            ins.add(cur.edges.get(i));

            System.out.println(i + " for edge " + cur.edges.size() + " List: " + ins);
            if (DFS(d + 1, cur.edges.get(i).end, ins, visual) != null) {
                if (visual){
                    g.deselect(cur,p);
                    g.deselect(ins.getLast(),null);
                }
                cur.visited = false;
                return ins;
            }
            //ins.removeLast();

        }

        if (visual){
            g.deselect(cur,p);
            if(!ins.isEmpty())
                g.deselect(ins.getLast(),null);
        }

        cur.visited = false;
        if (!ins.isEmpty())
            ins.removeLast();

        return null;
    }
}
