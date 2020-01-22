import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Edge {
    int capacity;
    Node start;
    Node end;
    boolean started;
    boolean isUsed;
    int used;
    Hellmanford h;



    public Edge(int capacity, Node start, Node end,Hellmanford hellmanford) {
        this.capacity = capacity;
        this.start = start;
        this.end = end;
        h = hellmanford;


    }

    public boolean canHoldCapacity(int flow){
        return capacity>=flow;
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

    }
    public double distance(double x1,double y1,double x2,double y2){
        return (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
    }
    public void draw(Graphics g){

        String toDraw = String.valueOf(capacity);
        if(started) toDraw = used+"/"+capacity;
        g.drawChars(toDraw.toCharArray(),0,toDraw.toCharArray().length,(start.x+end.x)/2,(start.y+end.y)/2);

        Arrow.drawArrow((Graphics2D)g,this);
        g.drawLine(start.x,start.y,end.x,end.y);

    }
    void remove(){
        start.removeEdge(this);
        end.removeEdge(this);
        if(h.edges.remove(this));
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
}
