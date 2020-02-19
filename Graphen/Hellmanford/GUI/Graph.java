package GUI;

import Algorithm.*;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;


public class Graph {
    public ArrayList<Node> nodes = new ArrayList<Node>(20);
    public ArrayList<Edge> edges = new ArrayList<Edge>(50);
    public boolean strg, cre, e, started, s;
    @Nullable
    public Node moveing;
    public boolean moving;

    public FordFulkerson ford;
    public AStar aStar;
    public Dijkstra dijkstra;
    public Kruskal kruskal;
    public DepthFirstSearch dfs;

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
                g.setFont(new Font("Calibri",Font.PLAIN,20));
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
        this.kruskal = new Kruskal(this);
        kruskal.addActionListener(this.kruskal);
        kruskal.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JButton dijkstraButton = new JButton("Dijkstra");
        dijkstra = new Dijkstra(this);
        dijkstraButton.addActionListener(dijkstra);

        dijkstraButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        dijkstraButton.setAlignmentY(Component.TOP_ALIGNMENT);
        dijkstraButton.setFocusable(false);

        JButton dfsButton = new JButton("Depth First Search");
        dfs = new DepthFirstSearch(this);
        dfsButton.addActionListener(dfs);
        dfsButton.setAlignmentX(Component.RIGHT_ALIGNMENT);



        JButton pause = new JButton("Pause");
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause();
            }
        });
        pause.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cont();
            }
        });
        continueButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JButton helpButton = new JButton("Help");
        helpButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(f.getContentPane(),"" +
                    "Beim anklicken einer Edge wird dessen Wert erhöht (Linksklick) oder verniedrigt (Rechtsklick)\n" +
                    "Falls beim Rechtsklick STRG gedrückt wird, wird die Node/Edge automatisch gelöscht\n" +
                    "Beim gedrückthalten von STRG kann eine Node mithilfe von ziehen über den Bildschirm bewegt werden\n" +
                    "(C): Beim Klicken wird eine Node erstellt. Sie verbindet sich automatisch mit allen vorhandenen Nodes\n" +
                    "(E): Beim Klicken einer Edge, verändert diese ihre Richtung\n" +
                    "(E): Beim Klicken zweier Nodes wird zwischen diesen eine Edge erstellt\n" +
                    "","Hilfe",JOptionPane.INFORMATION_MESSAGE);
        });
        helpButton.setAlignmentX(Component.RIGHT_ALIGNMENT);


        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> cancel());
        cancelButton.setAlignmentX(Component.RIGHT_ALIGNMENT);



        buttonPane.add(fordFulkerson);
        buttonPane.add(aStarButton);
        buttonPane.add(dijkstraButton);
        buttonPane.add(kruskal);
        buttonPane.add(dfsButton);

        buttonPane.add(helpButton);


        buttonPane.add(pause);
        buttonPane.add(continueButton);
        buttonPane.add(cancelButton);
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




    public void pause(){
        f.repaint();
        try {
            if (dijkstra.isRunning()) {
                dijkstra.pause();
            }else if(aStar.isRunning())
                aStar.pause();
            else if(ford.isRunning())
                ford.pause();
            else if(kruskal.isRunning())
                kruskal.pause();
            else if(dfs.isRunning())
                dfs.pause();
        }
        catch (Exception e){

        }
    }

    public void cont(){
        f.repaint();
        if (dijkstra.isPaused()) {
            dijkstra.cont();
        }else if(aStar.isPaused())
            aStar.cont();
        else if(ford.isPaused())
            ford.cont();
        else if(kruskal.isPaused())
            kruskal.cont();
        else if(dfs.isPaused())
            dfs.cont();
    }

    public void cancel(){
        if (dijkstra.isRunning()) {
            dijkstra.cancel();
        }else if(aStar.isRunning())
            aStar.cancel();
        else if(ford.isRunning())
            ford.cancel();
        else if(kruskal.isRunning())
            kruskal.cancel();
        else if(dfs.isRunning())
            dfs.cancel();
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



    public void setStarted(boolean t) {
        for (int i = 0; i < edges.size(); i++) {
            edges.get(i).started = t;
            if(!t)
                edges.get(i).toRender = null;
        }
        if(!t){
            for(Node n:nodes){
                n.toRender = null;
            }
        }
        started = t;
        f.repaint();
    }

    public void setStart(Node start) {
        if (this.start != null) {
            this.start.toRenderNormal = null;
        }
        this.start = start;
        if(start != null)
            start.toRenderNormal = Color.red;
    }

    @Nullable
    public void setEnd(Node end) {
        if (this.end != null) {
            this.end.toRenderNormal = null;
        }
        this.end = end;
        if(end != null)
        end.toRenderNormal = Color.green;
    }
}
