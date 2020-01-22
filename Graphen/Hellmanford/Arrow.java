import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class Arrow {
    private static Polygon arrowHead = new Polygon();
    /**Drawing an Arrow Head**/
    static AffineTransform tx = new AffineTransform();

    public static void init(){
        //Draw an Arrow
        arrowHead.addPoint( 0,-5);
        arrowHead.addPoint( -5, -15);
        arrowHead.addPoint( 5,-15);
    }
    public static void drawArrow(Graphics2D g,Edge e){
        tx.setToIdentity();
        double angle = Math.atan2(e.end.y-e.start.y, e.end.x-e.start.x);
        double angleU = angle;
        //double atan = Math.tan(angle);
        //        double a = Math.sqrt(100/(atan*atan+1));
        //        double b = a*atan;
        //        if(angle<0){
        //            if(angle<-Math.PI/2)
        //                tx.translate(e.end.x+b, e.end.y-a);
        //            else
        //                tx.translate(e.end.x-b, e.end.y+a);
        //        }
        //        else{
        //            if(angle<Math.PI/2)
        //            tx.translate(e.end.x+b, e.end.y+a);
        //            else
        //            tx.translate(e.end.x-b, e.end.y-a);
        //        }
        tx.translate(e.end.x, e.end.y);
        tx.rotate((angleU-Math.PI/2d));

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setTransform(tx);
        g2d.fill(arrowHead);
        g2d.dispose();
    }
}
