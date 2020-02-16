package GUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MyMouseMotionListener implements MouseMotionListener {
    final Graph m;
    public MyMouseMotionListener (Graph a){
        m = a;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if(m.moving) {
            m.moveing.x = e.getX();
            m.moveing.y = e.getY();
            m.f.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
