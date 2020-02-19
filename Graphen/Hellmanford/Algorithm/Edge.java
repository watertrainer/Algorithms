package Algorithm;

import java.awt.*;
import java.awt.geom.Point2D;
import GUI.*;
import org.jetbrains.annotations.Nullable;

public class Edge {
    public int capacity;
    public Node start;
    public Node end;
    public boolean started;
    boolean isUsed;
    public int used;
    public final Graph h;
    @Nullable
    public Color toRender;



    public Edge(int capacity, Node start, Node end, Graph graph) {
        this.capacity = capacity;
        this.start = start;
        this.end = end;
        h = graph;


    }
    public void addUsed(int i){
        used += i;
        end.getEdgeTo(start).used -= i;
    }
    public boolean canHoldCapacity(int flow){
        return capacity- used>=flow;
    }

    public double isBetween(int x,int y){




        double distanceSq =distance(start.x,start.y,end.x,end.y);

        if(distanceSq==0) return Point2D.distance(start.x,start.y,end.x,end.y);

        // dotproduct = (c.x - a.x) * (b.x - a.x) + (c.y - a.y)*(b.y - a.y)
        double dotproduct = ((x-start.x)*(end.x-start.x) + (y-start.y) * (end.y-start.y))/distanceSq;
        double t = Math.max(0,Math.min(1,dotproduct));

        return distance(x,y,start.x+t*(end.x-start.x),start.y+t*(end.y-start.y));
    }
    public void changeDirection(){
        Node s = start;
        start = end;
        end = s;
        start.edges.add(this);
        start.edgeToMe.remove(this);
        end.edgeToMe.add(this);
        end.edges.remove(this);

    }


    public double distance(double x1,double y1,double x2,double y2){
        return (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
    }
    public void draw(Graphics g){

        String toDraw = String.valueOf(capacity);
        if(h.ford.isRunning()) {
            toDraw = used+"/"+capacity;
        }
        if(toRender != null){
            g.setColor(toRender);
        }

        g.drawChars(toDraw.toCharArray(),0,toDraw.toCharArray().length,(start.x+end.x)/2,(start.y+end.y)/2);
        Graphics2D g2d = (Graphics2D)g;
        Arrow.drawArrow(g2d,this);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(start.x,start.y,end.x,end.y);
        g.setColor(Color.black);

    }
    public void remove(){
        start.removeEdge(this);
        //end.removeEdge(this);
        //start.child.remove(end);
        h.edges.remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (!start.equals(edge.start)) return false;
        return end.equals(edge.end);
    }

    @Override
    public int hashCode() {
        int result = start.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }

    @Override
    public Edge clone(){
        System.out.println("cloned");
        Edge e = new Edge(capacity,start,end,h);
        e.used = used;
        return e;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "capacity=" + capacity +
                ", start=" + start +
                ", end=" + end +
                ", used=" + used +
                '}';
    }
}
