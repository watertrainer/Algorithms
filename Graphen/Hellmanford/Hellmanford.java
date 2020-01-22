

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class Hellmanford{
    ArrayList<Node> nodes = new ArrayList<Node>(20);
    List<Edge> edges = new ArrayList<Edge>(50);
    boolean strg,cre,e,started,s;
    Node moveing;
    boolean moving;

    Node start,end;
    JFrame f;

    JLabel info;


    public static void main(String[] args){
        Hellmanford h = new Hellmanford();

    }
    public Hellmanford(){
        f = new JFrame();
        JPanel p = new JPanel(){


            public void paintComponent(Graphics g){
                for(int i = 0;i<edges.size();i++){
                    edges.get(i).draw(g);
                }
                for(int i = 0;i<nodes.size();i++){
                    nodes.get(i).draw(g);
                }

            }
        };
        p.setLayout(new BoxLayout(p,BoxLayout.LINE_AXIS));
        p.add(Box.createHorizontalGlue());
        p.setFocusable(true);
        p.addMouseListener(new MyMouseListener(this));
        p.addKeyListener(new MyKeyListener(this));
        p.addMouseMotionListener(new MyMouseMotionListener(this));


        Arrow.init();
        info = new JLabel("");
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        info.setAlignmentY(Component.TOP_ALIGNMENT);
        p.add(info);
        p.add(Box.createHorizontalGlue());
        JButton fordFulkerson = new JButton("Ford Fulkerson");
        fordFulkerson.addActionListener(e -> {
            if(started){
                setStarted(false);
                if(end != null) {
                    end.toRender = null;
                    end = null;
                }
                if(start!= null){
                    start.toRender=null;
                    start = null;
                }
                info.setText("");
            }else {
                info.setText("Select start and end Node (Press S or E and select a Node)");
                setStarted(true);
            }
        });
        fordFulkerson.setAlignmentX(Component.RIGHT_ALIGNMENT);

        fordFulkerson.setAlignmentY(Component.TOP_ALIGNMENT);
        fordFulkerson.setFocusable(false);
        p.add(fordFulkerson);



        f.setContentPane(p);
        f.setSize(400,500);
        f.setVisible(true);
        Thread t = new Thread(() -> {
            while(true){
                f.repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    public void fordFulkerson(){
        info.setText("Starting");

    }

    public void setStarted(boolean t){
        for(int i = 0;i<edges.size();i++){
            edges.get(i).started = t;
        }
        started = t;
    }

    public void setStart(Node start) {
        if(this.start != null) {
            this.start.toRender = null;
        }
        this.start = start;
        start.toRender = Color.red;
    }

    public void setEnd(Node end) {
        if(this.end!=null) {
            this.end.toRender = null;
        }
        this.end = end;
        end.toRender =Color.green;
    }
}
