package Algorithm;

import GUI.Graph;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;

public class Kruskal extends GraphAlgorithm{
    public Kruskal(Graph g) {
        super(g);
    }

    @Override
    public void calculateAlgorithm(boolean visual) {
        ArrayList<ArrayList<Node>> forest = new ArrayList<>(g.nodes.size());
        for(Node n:g.nodes){
            ArrayList<Node> tree = new ArrayList<>(1);
            tree.add(n);
            forest.add(tree);
            n.cost = forest.size()-1;
        }
        ArrayList<Edge> edges = (ArrayList<Edge>) g.edges.clone();
        for(Edge e:edges){
            e.used = -1;
        }
        edges.sort(Comparator.comparingInt((a)-> a.capacity));
        Edge cur = edges.remove(0);

        int minSpanning = 0;
        if(visual){
            g.info.setText("Every node has its own tree. Starting to construct min-spanning-tree");
            g.f.repaint();
            if(sleep(2000))return;
            g.info.setText("Selecting Edge with lowest cost");
            if(sleep(2000))return;
            if(select(cur))return;
        }
        int sub = 0;
        while (forest.get(0).size() != g.nodes.size()){
            if(cur.start.cost != cur.end.cost){
                if(visual){
                    g.info.setText("Edge connects two trees. Fusing them!");
                    if(sleep(2500))
                        return;
                }
                if(cur.end.cost<cur.start.cost){
                    fuse(forest.get(cur.end.cost),forest.get(cur.start.cost),cur.end.cost,visual);
                }
                else{
                    fuse(forest.get(cur.start.cost),forest.get(cur.end.cost),cur.start.cost,visual);
                }
                //cur.used = cur.start.cost;
            }
            else{
                if(visual){
                    if(sleep(1))return;
                    g.info.setText("Edge does not connect two trees. Next Edge!");
                    deselect(cur);
                    if(sleep(2500))return;
                }
            }
            minSpanning += cur.capacity;

            cur = edges.remove(0);
            if(visual){
                g.info.setText("Selecting Edge with lowest cost");
                if(select(cur))
                    return;
                if(sleep(2500))
                    return;
            }

        }
        if(visual){
            g.info.setText("Finished! There is your tree with a sum of "+minSpanning);
            if(sleep(4000))
                return;

        }




    }

    public void fuse(Collection<? super Node> tree1, Collection<? extends Node> tree2, int in, boolean visual){
        Random r = new Random(in);
        for(Node n:tree2){
            n.cost = in;
            tree1.add(n);
            if(visual) {
                sleep(500);
                g.f.repaint();
            }
        }
        tree2.clear();
    }

}
