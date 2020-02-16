package Algorithm;

import GUI.Graph;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra extends GraphAlgorithm {

    ArrayList<Node> nodes = new ArrayList<Node>(20);
    List<Edge> edges = new ArrayList<Edge>(50);

    public Dijkstra(Graph g) {
        super(g);
    }

    public void dijkstra(){}

    @Override
    public void calculateAlgorithm(boolean visual) {

    }
}
