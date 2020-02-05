

import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    ArrayList<Node> nodes = new ArrayList<Node>(20);
    List<Edge> edges = new ArrayList<Edge>(50);
    boolean strg, cre, e, started, s;
    Node moveing;
    boolean moving;

    Node start, end;
    JFrame f;

    JLabel info;


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
        p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
        p.add(Box.createHorizontalGlue());
        p.setFocusable(true);
        p.addMouseListener(new MyMouseListener(this));
        p.addKeyListener(new MyKeyListener(this));
        p.addMouseMotionListener(new MyMouseMotionListener(this));


        Arrow.init();
        info = new JLabel("");
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        info.setAlignmentY(Component.TOP_ALIGNMENT);
        info.setBorder(BorderFactory.createLineBorder(Color.green));
        p.add(info);
        p.add(Box.createVerticalGlue());

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.PAGE_AXIS));

        JButton fordFulkerson = new JButton("Ford Fulkerson");
        fordFulkerson.addActionListener(actionEvent -> {
            setStarted(true);
            Thread t = new Thread(() -> fordFulkerson(true));
            t.start();
        });

        fordFulkerson.setAlignmentX(Component.RIGHT_ALIGNMENT);
        fordFulkerson.setAlignmentY(Component.TOP_ALIGNMENT);
        fordFulkerson.setFocusable(false);

        JButton randomize = new JButton("Randomize");
        randomize.addActionListener(actionEvent -> {
            randomize();
        });

        randomize.setAlignmentX(Component.RIGHT_ALIGNMENT);
        randomize.setAlignmentY(Component.TOP_ALIGNMENT);
        randomize.setFocusable(false);


        buttonPane.add(fordFulkerson);
        buttonPane.add(randomize);
        buttonPane.add(Box.createVerticalGlue());
        buttonPane.setBorder(BorderFactory.createLineBorder(Color.red));

        p.add(buttonPane);


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

    public void fordFulkerson(boolean visual) {
        if (visual)
            info.setText("Starting");
        int minx = Integer.MAX_VALUE;
        int maxx = 0;
        int inx = -1;
        int inmx = -1;
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).x < minx) {
                minx = nodes.get(i).x;
                inx = i;
            } else if (nodes.get(i).x > maxx) {
                maxx = nodes.get(i).x;
                inmx = i;
            }
        }
        setStart(nodes.get(inx));
        setEnd(nodes.get(inmx));

        if (visual) {
            for (Node node : nodes) {
                node.sortNodes();
            }
            info.setText("Start and finish chosen. Starting to find the max tree");
            f.repaint();
            sleep(3000);
            info.setText("Finding Path");
        }
        for (Node n : nodes) {
            n.createAugmentedEdges();
        }
        Node cur;
        ArrayList<Integer> ins = new ArrayList<>(15);
        int maxFlow = 0;
        while (DFS(0, start, ins, visual) != null) {
            cur = start;

            if (visual)
                info.setText("Found Path");
            int minc = Integer.MAX_VALUE;
            for (Integer in : ins) {
                //int c = cur.getCapTo(cur.child.get(in));
                int c = cur.edges.get(in).capacity - cur.edges.get(in).used;
                minc = Math.min(minc, c);
                //cur = cur.child.get(in);
                cur = cur.edges.get(in).end;
            }

            if (visual) {
                info.setText("Minimal capacity is " + minc);
                f.repaint();
                sleep(3000);
            }
            cur = start;

            for (int i = 0; i < ins.size(); i++) {
                //cur.getEdgeTo(cur.child.get(ins.get(i))).used += minc;
                cur.edges.get(ins.get(i)).addUsed(minc);
                //cur = cur.child.get(ins.get(i));
                cur = cur.edges.get(ins.get(i)).end;

                if (visual) {
                    cur.toRender = null;
                    f.repaint();
                    sleep(100);
                }
            }
            maxFlow += minc;
            ins = new ArrayList<>(15);

            if (visual) {
                info.setText("Finding Path");
            }
        }

        if (visual) {
            info.setText("No Path Found!");
            sleep(5000);
            info.setText("Max Flow found " + maxFlow);
        }
        setStarted(false);


    }

    @Nullable
    public ArrayList<Integer> DFS(int d, Node cur, ArrayList<Integer> ins, boolean visual) {
        Color p = null;
        if (cur.visited) {
            return null;
        }
        if (visual) {
            p = select(cur);
        }
        if (cur == end) {

            if (visual)
                cur.toRender = p;

            cur.visited = false;
            return ins;
        }

        if (d == ins.size())
            ins.add(0);

        for (int i = 0; i < cur.edges.size(); i++) {
            ins.set(d, i);
            System.out.println(i +" for edge"+cur.edges.size()+" List: "+ins);
            cur.visited = true;
            if (cur.edges.get(i).canHoldCapacity(1) && DFS(d + 1, cur.edges.get(i).end, ins, visual) != null) {

                if (visual)
                    cur.toRender = p;

                cur.visited = false;
                return ins;
            }

        }

        if (visual)
            cur.toRender = p;

        cur.visited = false;
        return null;
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
    }

    public void setStart(Node start) {
        if (this.start != null) {
            this.start.toRender = null;
        }
        this.start = start;
        start.toRender = Color.red;
    }

    public void setEnd(Node end) {
        if (this.end != null) {
            this.end.toRender = null;
        }
        this.end = end;
        end.toRender = Color.green;
    }
}
