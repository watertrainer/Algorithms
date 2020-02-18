package GUI;

import Algorithm.AStar;
import Algorithm.Edge;
import Algorithm.FordFulkerson;
import Algorithm.Node;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;


public class Graph {
    public ArrayList<Node> nodes = new ArrayList<Node>(20);
    public ArrayList<Edge> edges = new ArrayList<Edge>(50);
    public boolean strg, cre, e, started, s;
    public Node moveing;
    public boolean moving;

    public FordFulkerson ford;
    public AStar aStar;

    public Node start, end;
    public JFrame f;

    public JLabel info;


    public static void main(String[] args) {
        Graph h = new Graph();

    }

    public Graph() {
        f = new JFrame();
        JPanel p = new JPanel() {


            public void paintComponent(Graphics g) {
                for (int i = 0; i < edges.size(); i++) {
                    edges.get(i).draw(g);
                }
                for (int i = 0; i < nodes.size(); i++) {
                    nodes.get(i).draw(g);
                }

            }
        };
        p.setLayout(new BoxLayout(p,BoxLayout.LINE_AXIS));
        p.setFocusable(true);
        p.addMouseListener(new MyMouseListener(this));
        p.addKeyListener(new MyKeyListener(this));
        p.addMouseMotionListener(new MyMouseMotionListener(this));


        Arrow.init();
        JPanel infopanel = new JPanel();
        infopanel.setLayout(new BoxLayout(infopanel,BoxLayout.PAGE_AXIS));

        info = new JLabel("");
        //info.setBorder(BorderFactory.createLineBorder(Color.green));

        infopanel.add(info);
        infopanel.add(Box.createVerticalGlue());
        infopanel.setOpaque(false);

        p.add(infopanel);
        p.add(Box.createHorizontalGlue());

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.PAGE_AXIS));

        JButton fordFulkerson = new JButton("Ford Fulkerson");
        ford = new FordFulkerson(this);
        fordFulkerson.addActionListener(ford);

        fordFulkerson.setAlignmentX(Component.RIGHT_ALIGNMENT);
        fordFulkerson.setAlignmentY(Component.TOP_ALIGNMENT);
        fordFulkerson.setFocusable(false);

        JButton aStarButton = new JButton("A*");
        aStar = new AStar(this);
        aStarButton.addActionListener(aStar);

        aStarButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        aStarButton.setAlignmentY(Component.TOP_ALIGNMENT);
        aStarButton.setFocusable(false);

        JButton kruskal = new JButton("Kruskal");


        buttonPane.add(fordFulkerson);
        buttonPane.add(aStarButton);
        buttonPane.add(Box.createVerticalGlue());
        buttonPane.setOpaque(false);

        p.add(buttonPane,BorderLayout.EAST);


        f.setContentPane(p);
        f.setSize(400, 500);
        f.setVisible(true);
        Thread t = new Thread(() -> {
            while (true) {
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






    public void randomize() {
        int numNodes = 7;
        double propChild = 0.3d;
        int tryChild = 2;
        int maxX = 30;
        int maxY = 30;
        int minX = 2;
        int minY = 2;
        int maxCap = 12;
        nodes.clear();
        edges.clear();
        for (int i = 0; i < numNodes; i++) {
            nodes.add(new Node((int) (Math.random() * (maxX - minX) + minX) * 20, (int) (Math.random() * (maxY - minY) + minY) * 20,
                    this));
        }
        for (Node n : nodes) {

            for (int i = 0; i < tryChild; i++) {
                if (Math.random() > propChild) {
                    Node child = nodes.get((int) (Math.random() * numNodes));
                    //!n.child.contains(child) && !child.child.contains(n) &&
                    if (n.createEdge(child)) {
                        n.getEdgeTo(child).capacity = (int) (Math.random() * maxCap);
                    } else
                        i--;
                }
            }
        }
    }

    public Color select(Node cur) {
        Color p = cur.toRender;
        cur.toRender = new Color(55
                , 113, 255);
        f.repaint();
        sleep(2000);
        return p;
    }

    public Color select(Node cur, Color c) {
        Color p = cur.toRender;
        cur.toRender = c;
        f.repaint();
        sleep(2000);
        return p;
    }

    public Color select(Edge e){
        Color p = e.toRender;
        e.toRender = new Color(55
                , 113, 255);
        f.repaint();
        return p;
    }
    public void deselect(Node cur, Color p){
        cur.toRender = p;
    }
    public void deselect(Edge i, Color p){
        i.toRender = p;
    }
    public void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void setStarted(boolean t) {
        for (int i = 0; i < edges.size(); i++) {
            edges.get(i).started = t;
        }
        started = t;
        f.repaint();
    }

    public void setStart(Node start) {
        if (this.start != null) {
            this.start.toRender = null;
        }
        this.start = start;
        if(start != null)
        start.toRender = Color.red;
    }

    @Nullable
    public void setEnd(Node end) {
        if (this.end != null) {
            this.end.toRender = null;
        }
        this.end = end;
        if(end != null)
        end.toRender = Color.green;
    }
}
