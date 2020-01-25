package src.gui.algos;

import src.gui.AlgosKeyListener;
import src.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Bucketsort extends SortingAlgorithm {
    public boolean insertion;
    ArrayList<Integer>[] buk;
    int drarIn, buIn = 0;
    JFrame f;
    boolean explanationMode;
    int emptyTill = -1;
    int empyafter = Integer.MAX_VALUE;
    int spBucket = -1;
    int spIn = -1;

    public Bucketsort() {
        info = "Bucketsort: 2ms for each inserted Element in a Bucket and in InsertionSort";
    }

    /**
     * Sortiert ein Array nach src.gui.Algorithms.Bucketsort
     *
     * @param ar Das zu sortierende Array
     * @param k  Die Anzahl an Buckets die verwendet werden soll
     */
    public void sort(int[] ar, int k) {
        running = true;
        buk = new ArrayList[k];
        for (int i = 0; i < buk.length; i++) {
            buk[i] = new ArrayList<>();
        }
        int M = maxIn(ar); //Der Maximale Wert
        for (int i = 0; i < ar.length; i++) {
            //Jedes Element wird seinem Bucket hinzugefügt.
            //Wir verwenden gleichmäßige Buckets, das heißt, dass man erst das Verhätlnis berechnet, wie viel "Prozent" von
            //der Max value das Element hat und dann wird das mit der Anzahl an Buckets multipliziert, wodurch der Index
            //im Buk Array gefunden wird
            buk[(int) Math.floor((k - 1) * ar[i] / M)].add(ar[i]);
            writes++;
            accesses++;
            GUI.sleep(2);
            GUI.repaint();
        }

        int arIn = 0;
        for (int i = 0; i < k; i++) {
            //Die Buckets werden in das Array Kopiert
            for (int j : toArray(buk[i])) {
                ar[arIn] = j;
                writes++;
                arIn++;

            }
            ;
        }
        insertion = true;
        insertionSort(ar);
        insertion = false;
        GUI.repaint();
        running = false;

        super.sort(ar);
    }

    public int[] toArray(ArrayList<Integer> ar) {
        int[] re = new int[ar.size()];
        for (int i = 0; i < ar.size(); i++) {
            re[i] = ar.get(i);
        }
        return re;
    }

    public int[] insertionSort(int[] sortieren) {
        int temp;
        for (int i = 1; i < sortieren.length; i++) {
            temp = sortieren[i];
            accesses++;
            int j = i;
            while (j > 0 && sortieren[j - 1] > temp) {
                sortieren[j] = sortieren[j - 1];
                accesses += 2;
                comparisons += 2;
                writes++;
                GUI.repaint();
                j--;
            }
            GUI.sleep(2);
            sortieren[j] = temp;
            writes++;
        }
        return sortieren;
    }

    public int maxIn(int ar[]) {
        int M = ar[0];
        accesses++;
        for (int i = 1; i < ar.length; i++) {
            if (ar[i] > M) {
                M = ar[i];
                accesses++;
            }
            accesses++;
            comparisons++;

        }
        return M;
    }

    public void draw(Graphics g) {
        if (insertion) {

        } else {
            int x = 0;
            for (int i = 0; i < buk.length; i++) {
                for (int j = 0; j < buk[i].size(); j++) {
                    g.drawLine(x, GUI.maxValue + 50, x, GUI.maxValue + 50 - buk[i].get(j));
                    x++;
                }
            }
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

        int k = 5;
        buk = new ArrayList[k];
        int M = maxIn(ar); //Der Maximale Wert
        JPanel content = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                int width = (f.getWidth() - 50) / ar.length;
                int heightoffset = 40;
                int height = (f.getHeight() - heightoffset) / maxValue / 3;
                for (int i = 0; i < ar.length; i++) {
                    if (i <= emptyTill || i > empyafter)
                        g.setColor(Color.blue);
                    if(i==spIn){
                        g.setColor(Color.red);
                    }
                    g.fillRect((i) * width, (f.getHeight() - heightoffset) - (ar[i] * height), width, (height * ar[i]));
                    g.setColor(Color.black);
                    String toDraw = String.valueOf(ar[i]);
                    g.drawChars(toDraw.toCharArray(), 0, toDraw.length(), i * width + width / 2, (int) ((f.getHeight() - heightoffset) - (ar[i] * height)) - 10);
                }
                int x = 40;
                for (int i = 0; i < buk.length; i++) {

                    int ymax = (f.getHeight() - heightoffset) - (maxValue * height) - 100;
                    int xol = x;

                    Font f = new Font("Calibri", Font.PLAIN, 20);
                    if (i == spBucket) {
                        f = new Font("Calibri", Font.BOLD, 20);
                    }
                    g.setFont(f);
                    int in = 0;
                    g.drawLine(x, ymax, x, ymax - 140);
                    while (in < buk[i].size()) {
                        boolean gre10 = false;
                        if (i == spBucket) {
                            g.setColor(Color.red);
                        }
                        for (int j = 0; j < 3 && in < buk[i].size(); j++) {

                            g.drawString(String.valueOf(buk[i].get(in)), x + 10, ymax - j * 40 - 10);

                            if (buk[i].get(in) >= 10) gre10 = true;
                            in++;
                        }
                        x += 30;
                        if (gre10) x += 10;

                    }
                    g.setColor(Color.black);
                    if (x == xol)
                        x += 30;
                    g.drawLine(x, ymax, xol, ymax);
                    g.drawString(String.valueOf((int) Math.floor(i * M / (k - 1))), xol + (x - xol) / 2 - 10, ymax - 160);
                    g.drawString(String.valueOf(i), xol + (x - xol) / 2 - 10, ymax + 20);

                    g.drawLine(x, ymax, x, ymax - 140);
                    x += 40;

                }
            }
        };

        JLabel explanation = new JLabel("We have 5 Buckets. Each Bucket has the numbers after the one" +
                " which is rendered above it.");
        JLabel explanation2 = new JLabel("The numbers are chosen, so that each bucket has the least amount of numbers possible");
        explanation.setFont(new Font("Calibri",Font.PLAIN,20));
        explanation2.setFont(new Font("Calibri",Font.PLAIN,20));
        content.add(explanation);
        content.add(explanation2);
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

        for (int i = 0; i < buk.length; i++) {
            buk[i] = new ArrayList<>(5);
        }
        GUI.sleep(8000);
        explanation2.setText("");
        explanation.setText("Copying each Element in its corresponding bucket");
        f.repaint();
        for (emptyTill = 0; emptyTill < ar.length; emptyTill++) {
            buk[(int) Math.floor((k - 1) * ar[emptyTill] / M)].add(ar[emptyTill]);
            f.repaint();
            GUI.sleep(1000);
        }
        Arrays.fill(ar, 0);
        emptyTill = -1;
        empyafter = 0;
        explanation.setText("Refilling the Elements in the Array");
        f.repaint();
        for (int i = 0; i < buk.length; i++) {
            spBucket = i;
            f.repaint();
            GUI.sleep(1000);
            while (!buk[i].isEmpty()) {
                ar[empyafter] = buk[i].get(0);
                buk[i].remove(0);
                f.repaint();
                GUI.sleep(1000);
                empyafter++;
            }

        }
        empyafter = Integer.MAX_VALUE;
        explanation.setText("Run InsertionSort over the now presorted array");
        f.repaint();
        int temp;
        for (int i = 1; i < ar.length; i++) {
            temp = ar[i];
            int j = i;
            while (j > 0 && ar[j - 1] > temp) {
                ar[j] = ar[j - 1];
                j--;

                spIn = j;
                f.repaint();
                GUI.sleep(500);
            }

            ar[j] = temp;

        }
        spIn = -1;
        explanation.setText("Finished!");
        f.repaint();
    }

    @Override
    public void sort(int[] ar) {
        sort(ar, 10);
    }


}
