import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener implements MouseListener {
    final Hellmanford m;
    MyMouseListener(Hellmanford a){
        m = a;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        JComponent j = (JComponent)e.getSource();
        j.requestFocus();
        if(e.getButton() == MouseEvent.BUTTON1) {
            if(m.strg) {
                for (int i = 0; i < m.nodes.size(); i++) {
                    if (m.nodes.get(i).clicked(e.getX(), e.getY())) {
                        m.moveing = m.nodes.get(i);
                        m.moving = true;
                        return;
                    }
                }
            }
            else if (m.nodeMode) {
                Node n = new Node(e.getX(),e.getY(),m);
                for(int i = 0;i<m.nodes.size();i++){
                    n.createEdge(m.nodes.get(i));
                }
                m.nodes.add(n);
                return;
            } else {
                    for (int i = 0; i < m.edges.size(); i++) {
                        if (m.edges.get(i).isBetween(e.getX(), e.getY()) < 90) {
                            m.edges.get(i).capacity++;
                            return;
                        }
                    }
                }

        }
        if(e.getButton() == MouseEvent.BUTTON3){

                for (int i = 0; i < m.nodes.size(); i++) {
                    if (m.nodes.get(i).clicked(e.getX(), e.getY())) {
                        m.nodes.get(i).clearEdges();
                        m.nodes.remove(i);
                        return;
                    }
                }
                for(int i = 0;i<m.edges.size();i++){
                    if(m.edges.get(i).isBetween(e.getX(),e.getY())<90){
                        if(m.strg ){

                            m.edges.get(i).remove();
                        }
                        else if(m.edges.get(i).capacity==0) {
                            int an = JOptionPane.showConfirmDialog(null,
                                    "Sicher, dass du diese Kante löschen möchtest??");
                            if(an == JOptionPane.YES_OPTION)
                                m.edges.get(i).remove();
                        }else{
                            m.edges.get(i).capacity--;
                        }
                        return;
                    }
                }

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(m.moving){
            m.moving = false;
            m.moveing = null;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}


