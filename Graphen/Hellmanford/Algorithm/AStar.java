package Algorithm;

import GUI.Graph;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class AStar extends GraphAlgorithm {


    public AStar(Graph g) {
        super(g);
    }

    @Override
    public void calculateAlgorithm(boolean visual) {
        //Select starting
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

        g.info.setText("Start and End selected. Starting to calculate the shortest Path");
        List<Node> bekannt = new ArrayList<Node>();
        Node curr = g.start;
        Color p = g.select(curr);
        for(int i = 0; i < g.nodes.size(); i++)
            g.nodes.get(i).cost = Integer.MAX_VALUE;
        curr.cost = 0;
        for(int i = 0; i < curr.edges.size(); i++) {
            curr.edges.get(i).end.cost = curr.cost + curr.edges.get(i).capacity + (int)Math.sqrt(Math.pow(curr.x-g.end.x, 2)+Math.pow(curr.y-g.end.y, 2));
            bekannt.add(curr.edges.get(i).end);
        }
        bekannt.sort(Comparator.comparingInt(a -> a.cost));
        g.deselect(curr,p);
        curr = bekannt.get(0);
        p = g.select(curr);
        bekannt.remove(0);

        while(!bekannt.isEmpty()){
            for(int i = 0; i < curr.edges.size(); i++) {
                curr.edges.get(i).end.cost = curr.cost + curr.edges.get(i).capacity + (int)Math.sqrt(Math.pow(curr.x-
                        g.end.x, 2)+Math.pow(curr.y-g.end.y, 2));
                bekannt.add(curr.edges.get(i).end);
            }
            bekannt.sort(Comparator.comparingInt(a -> a.cost));

            if(curr.getEdgeTo(bekannt.get(0))!=null)
                g.select(curr.getEdgeTo(bekannt.get(0)));
            curr = bekannt.get(0);
            p = g.select(curr);

            bekannt.remove(0);
        }
        g.info.setText("Shortest Path found. It costs  "+g.end.cost+" to get there");
        g.sleep(5000);
    }


}
