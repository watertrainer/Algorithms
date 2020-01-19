

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Hellmanford{
    ArrayList<Node> nodes = new ArrayList<Node>(20);

    List<Edge> edges = new ArrayList<Edge>(50);
    boolean nodeMode = true;
    boolean strg;
    Node moveing;
    boolean moving;
    JFrame f;
    JButton nodeModeButton;
    public static void main(String[] args){
        Hellmanford h = new Hellmanford();

    }
    public Hellmanford(){
        f = new JFrame();
        JPanel p = new JPanel(){
            public void paintComponent(Graphics g){
                for(int i = 0;i<nodes.size();i++){
                    nodes.get(i).draw(g);
                }
                for(int i = 0;i<edges.size();i++){
                    edges.get(i).draw(g);
                }
            }
        };

        p.setLayout(new BoxLayout(p,BoxLayout.PAGE_AXIS));
        nodeModeButton = new JButton("Erstellen Modus");
        nodeModeButton.addActionListener(e -> {
            toggleNodeMode();

        });
        nodeModeButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        nodeModeButton.setFocusable(false);
        p.add(nodeModeButton);
        p.setFocusable(true);
        p.addMouseListener(new MyMouseListener(this));
        p.addKeyListener(new MyKeyListener(this));
        p.addMouseMotionListener(new MyMouseMotionListener(this));
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


    }

    public void toggleNodeMode(){
        nodeMode = !nodeMode;
        if(nodeMode) {
            nodeModeButton.setText("Erstellen Modus");
        }
        else{
            nodeModeButton.setText("Bearbeiten Modus");
        }
    }
    }
