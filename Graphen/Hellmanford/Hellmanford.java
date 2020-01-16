import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Hellmanford {
    ArrayList<Node> nodes = new ArrayList<Node>();
    public static void main(String[] args){
        Hellmanford h = new Hellmanford();

    }
    public Hellmanford(){
        JFrame f = new JFrame();
        JPanel p = new JPanel();
        p.addMouseListener(new MyMouseListener(nodes));
        f.setContentPane(p);
    }

    private static class MyMouseListener implements MouseListener {
        ArrayList<Node> nodes;
        public MyMouseListener(ArrayList<Node>a){
            nodes = a;
        }
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            for(int i = 0;i<nodes.size();i++){
                nodes.get(i).pressed(e.getX(),e.getY());
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
