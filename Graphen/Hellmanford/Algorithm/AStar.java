package Algorithm;

import GUI.Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AStar extends GraphAlgorithm {


    public AStar(Graph g) {
        super(g);
    }

    @Override
    public void calculateAlgorithm(boolean visual) {

        List<Node> bekannt = new ArrayList<Node>();
        Node curr = g.start;
        g.select(curr);
        for(int i = 0; i < g.nodes.size(); i++)
            g.nodes.get(i).cost = Integer.MAX_VALUE;
        curr.cost = 0;
        for(int i = 0; i < curr.edges.size(); i++) {
            curr.edges.get(i).end.cost = curr.cost + curr.edges.get(i).capacity;
            bekannt.add(curr.edges.get(i).end);
        }
        bekannt.sort(Comparator.comparingInt(a -> a.cost));
        curr = bekannt.get(0);
        g.select(curr);
        bekannt.remove(0);

        while(!bekannt.isEmpty()){
            for(int i = 0; i < curr.edges.size(); i++) {
                curr.edges.get(i).end.cost = curr.cost + curr.edges.get(i).capacity;
                bekannt.add(curr.edges.get(i).end);
            }
            bekannt.sort(Comparator.comparingInt(a -> a.cost));
            curr = bekannt.get(0);
            g.select(curr);
            bekannt.remove(0);
        }
    }


}
