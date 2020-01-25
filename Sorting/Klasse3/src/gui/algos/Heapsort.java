package src.gui.algos;

import src.gui.AlgosKeyListener;
import src.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Heapsort extends SortingAlgorithm {
    JFrame f;
    int inS = 0;
    int j;
    int inRed1;
    int inRed2;
    boolean explanationMode;

    public Heapsort() {
        info = "Heapsort: 2ms for every swap     ";
        wait = 2;
    }

    public void sort(int[] arr) {

        int n = arr.length;

        for (int i = (n / 2) - 1; i >= 0; i--) {

            heapen(arr, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            swap(0, i, arr);
            heapen(arr, i, 0);

        }

        super.sort(arr);

    }

    public void heapen(int[] arr, int sizeH, int i) {

        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < sizeH && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < sizeH && arr[right] > arr[largest]) {
            largest = right;
        }
        accesses += 4;
        comparisons += 4;
        if (largest != i) {
            if (explanationMode) {
                f.repaint();
                GUI.sleep(1000);
            }
            swap(i, largest, arr);
            heapen(arr, sizeH, largest);

        }


    }

    @Override
    public void explanationSort() {
        explanationMode = true;
        int[] ar = new int[20];
        int maxValue = 20;
        for (int i = 0; i < 20; i++) {
            ar[i] = (int) (Math.random() * maxValue);
        }
        f = new JFrame();
        f.setSize(800, 800);
        int waitol = wait;
        wait = 0;
        int n = ar.length;
        j = n;
        JPanel content = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                int width = (f.getWidth() - 50) / ar.length;
                int heightoffset = 40;
                int height = (f.getHeight() - heightoffset) / maxValue / 3;
                for (int i = 0; i < ar.length; i++) {
                    //if(i == inS){
                    //                        g.setColor(Color.green);
                    //                    }
                    if (i == inRed1 || i == inRed2) {
                        g.setColor(Color.red);
                    }
                    if (i >= j) g.setColor(Color.green);
                    g.fillRect(i * width, (f.getHeight() - heightoffset) - (ar[i] * height), width, (height * ar[i]));
                    g.setColor(Color.black);
                    String toDraw = String.valueOf(ar[i]);

                    System.out.println(g.getFont());;
                    g.setFont(new Font("Calibri",Font.PLAIN,20));
                    g.drawChars(toDraw.toCharArray(), 0, toDraw.length(), i * width + width / 2, (int) ((f.getHeight() - heightoffset) - (ar[i] * height)) - 10);
                    g.setColor(Color.black);
                    //if(i == inS){
                    //                        g.setColor(Color.black);
                    //                    }
                    g.setFont(new Font("Calibri", Font.PLAIN,12));
                    drawTree(g, 1, 0, ar, (int) (0.3 * (f.getHeight() - heightoffset))+50, j);
                }
                g.setColor(Color.red);
                g.drawLine(j * width, f.getHeight() - heightoffset, j * width, (f.getHeight() - heightoffset) - (maxValue * height));
            }
        };

        JLabel explanation = new JLabel("First time heapifying (If a child is greater than its parent, swap them)");
        explanation.setFont(new Font("Calibri",Font.PLAIN,20));
        content.add(explanation);
        f.setContentPane(content);
        f.setResizable(false);
        f.setVisible(true);
        f.addKeyListener(new AlgosKeyListener());
        f.setFocusable(true);
        f.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        Thread t = Thread.currentThread();
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                f.dispose();
                t.interrupt();
            }
        });
        for (int i = (n / 2) - 1; i >= 0; i--) {

            heapen(ar, n, i);

        }
        inRed2 = -1;
        inRed1 = -1;
        explanation.setText("Starting to Sort");
        f.repaint();
        GUI.sleep(2000);
        for (j = n - 1; j > 0; j--) {
            explanation.setText("Swapping highest Element to the end");

            swap(0, j, ar);

            f.repaint();
            GUI.sleep(1500);
            explanation.setText("Heapifying (If a child is greater than its parent, swap them)");

            f.repaint();
            heapen(ar, j, 0);

        }
        explanation.setText("Finished");
        inS = -1;
        f.repaint();
        wait = waitol;
    }

    public void drawTree(Graphics g, int rec, int i, int[] ar, int y, int until) {
        if (i > until) {
            return;

        }
        // if(i == inS){
        //                g.setColor(Color.green);
        //            }
        if (i == inRed1 || i == inRed2) {
            g.setColor(Color.red);
        }
        g.fillOval(rec * 80 - 5, y - 5, 10, 10);
        String toDraw = String.valueOf(ar[i]);
        // if(i == inS){
        //            g.setColor(Color.black);
        //        }
        if (i == inRed1 || i == inRed2) {
            g.setColor(Color.black);
        }

        g.drawChars(toDraw.toCharArray(), 0, toDraw.length(), rec * 80, y - 10);
        if (2 * i + 1 < until) {
            if (2 * i + 1 == inRed2)
                g.setColor(Color.red);


            g.drawLine(rec * 80, y, rec * 80 + 80, y - (200 / (int) (Math.pow(2, rec))));
            drawTree(g, rec + 1, 2 * i + 1, ar, y - ((200 / (int) (Math.pow(2, rec)))), until);

            g.setColor(Color.black);
        }
        if (2 * i + 2 < until) {
            if (2 * i + 2 == inRed2)
                g.setColor(Color.red);
            g.drawLine(rec * 80, y, rec * 80 + 80, y + (200 / (int) (Math.pow(2, rec))));
            g.setColor(Color.black);

            drawTree(g, rec + 1, 2 * i + 2, ar, y + ((200 / (int) (Math.pow(2, rec)))), until);

        }
    }

    @Override
    public void swap(int i1, int i2, int[] ar) {
        inRed1 = i1;
        inRed2 = i2;
        if(explanationMode) {
            f.repaint();
            GUI.sleep(1000);
        }
        super.swap(i1, i2, ar);
    }
}

