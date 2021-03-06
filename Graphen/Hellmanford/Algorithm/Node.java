package Algorithm;

import GUI.Graph;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Node {
    public int x;
    public int y;
    public ArrayList<Edge> edges;
    public ArrayList<Edge> edgeToMe;
    //ArrayList<Node> child;
    Graph h;
    @org.jetbrains.annotations.Nullable
    public Color toRender;

    @Nullable
    public Color toRenderNormal;
    boolean visited;
    public int cost;
    public int costWithHeuristic;

    int origInd;
    public Node(int x, int y, Graph graph){
        this.x = x;
        this.y = y;
        edges = new ArrayList<>();
        edgeToMe = new ArrayList<>();
        //child = new ArrayList<>();
        h = graph;
        toRenderNormal =Color.black;
    }


    public boolean clicked(int x, int y){
        return ((x-this.x)*(x-this.x) + (this.y-y)*(this.y-y) <=100);


    }

    public void draw(Graphics g)
    {
        if(h.aStar.isRunning() || h.dijkstra.isRunning()){
            String toDraw = cost == Integer.MAX_VALUE ? "\u221e" :String.valueOf(cost);
            g.setColor(Color.black);
            g.drawString(toDraw,x,y+20);
        }
        else if(h.kruskal.isRunning()){
            String toDraw = String.valueOf(cost);
            g.setColor(Color.black);
            g.drawString(toDraw,x,y+20);
        }
        if(toRender!=null){
            g.setColor(toRender);
        }
        else{
            g.setColor(toRenderNormal);
        }

        g.fillOval(x-5,y-5,10,10);
        g.setColor(Color.black);
        g.drawOval(x-5,y-5,10,10);

    }

    public boolean createEdge(Node n){
        Edge e = new Edge(1,this,n,h);
        if(!edges.contains(e) && !e.start.equals(e.end)) {
            edges.add(e);
            n.edgeToMe.add(e);
            System.out.println(n.edgeToMe);
            //child.add(n);
            h.edges.add(e);
            return true;
        }
        else{
            return false;
        }
    }
    public void createAugmentedEdges(){
        int si = edges.size();
        for(int i = 0;i<si;i++){
            edges.get(i).used = 0;
            Edge e = new Edge(edges.get(i).capacity,edges.get(i).end,this,h);
            e.used = e.capacity;
            edges.get(i).end.edges.add(e);

        }
        origInd = si;

    }

    public void removeAugmented(){
        while(edges.size()!=origInd)
            edges.remove(edges.size()-1);
        for (Edge e:
             edges) {
            e.used = 0;

        }
    }
    public void removeEdge(Edge edge){
        if(edges.remove(edge)){
            edge.end.edgeToMe.remove(edge);
        }

    }

    public void clearEdges(){
       while(!edges.isEmpty()){
            edges.get(0).remove();
        }
    }

    public int getCapTo(Node n){
        for(int i = 0;i<edges.size();i++){
            if(edges.get(i).end == n)
                return edges.get(i).capacity - edges.get(i).used;
        }
        return 0;
    }

    public void sortNodes(){
        edges.sort(Comparator.comparingInt(a -> -a.end.y));
    }
    @Nullable
    public Edge getEdgeTo(Node n){
        for(int i = 0;i<edges.size();i++){
            if(edges.get(i).end.equals(n))
                return edges.get(i);
        }
        return null;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (x != node.x) return false;
        return y == node.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public Node clone(){
        Node n = new Node(x,y,h);
        n.edges = (ArrayList<Edge>) edges.clone();
        return n;
    }


}
