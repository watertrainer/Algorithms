package Algorithm;

import GUI.Graph;

import java.awt.*;
import java.util.*;
import java.util.List;

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
        if(visual) {
            g.info.setText("Start and End selected. Starting to calculate the shortest Path");
        }
        if(sleep(2000))
            return;
        g.end.toRenderNormal = null;
        List<Node> bekannt = new ArrayList<>();
        Node curr = g.start;
        for(int i = 0; i < g.nodes.size(); i++) {
            g.nodes.get(i).cost = Integer.MAX_VALUE;
            g.nodes.get(i).costWithHeuristic = Integer.MAX_VALUE;
        }
        curr.cost = 0;
        for(int i = 0; i < curr.edges.size(); i++) {
            curr.edges.get(i).end.costWithHeuristic = curr.cost + curr.edges.get(i).capacity + (int)Math.sqrt(Math.pow(curr.x-g.end.x, 2)+Math.pow(curr.y-g.end.y, 2));
            curr.edges.get(i).end.cost = curr.cost + curr.edges.get(i).capacity;
            bekannt.add(curr.edges.get(i).end);
            if(visual) {
                if(select(curr.edges.get(i).end, Color.yellow))
                    return;
                if(select(curr.edges.get(i),Color.green))
                    return;
            }
        }
        bekannt.sort(Comparator.comparingInt(a -> a.costWithHeuristic));


        while(!bekannt.isEmpty()){
            g.info.setText("Selecting next Node");
            if(visual){
                if(select(curr,Color.GREEN))
                    return;
                if(select(bekannt.get(0)))
                    return;
            }
            curr = bekannt.get(0);
            if(curr.cost <= g.end.cost)
                break;
            for(int i = 0; i < curr.edges.size(); i++) {
                curr.edges.get(i).end.costWithHeuristic = curr.cost + curr.edges.get(i).capacity + (int)Math.sqrt(Math.pow(curr.x-g.end.x, 2)+Math.pow(curr.y-g.end.y, 2));
                curr.edges.get(i).end.cost = curr.cost + curr.edges.get(i).capacity;
                bekannt.add(curr.edges.get(i).end);
                if(visual) {
                    if(select(curr.edges.get(i).end, Color.yellow))
                        return;

                    if(select(curr.edges.get(i),Color.green))return;
                }
            }
            bekannt.sort(Comparator.comparingInt(a -> a.costWithHeuristic));
            bekannt.remove(curr);
            if(visual){
                if(select(curr,Color.GREEN))
                    return;
            }

        }
        if(visual) {
            g.info.setText("Shortest Path found.");
            if(sleep(5000))return;
            g.info.setText("Here it is");
            while (curr!= g.start){
                for(Edge e:curr.edgeToMe){
                    if(curr.cost - e.capacity == e.start.cost) {
                        curr = e.start;
                        if(select(curr, Color.red))
                            return;
                        if(select(e,Color.red))
                            return;
                        break;
                    }
                }
            }
            if(sleep(4000))return;
            g.info.setText("Finished!");
            if(sleep(2000))return;
            g.info.setText("");

        }


    }


}
