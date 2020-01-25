package src.gui.algos;

import src.gui.AlgosKeyListener;
import src.gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.Arrays;

public class Radixsort extends SortingAlgorithm {
    int[] darr;
    int[] count;
    int in = 0;
    boolean explanationMode = false;
    JFrame f;
    int digit;
    int spInDarr = -1;
    int spInArr = -1;
    int spInCo = -1;
    int spInCoKe = -1;

    public Radixsort() {
        info = "RadixSort:" + " 2ms for every counted and inserted element";
    }

    public void sort(int[] arr) {

        int digit = 1;
        int maxNum = getMax(arr);
        int n = arr.length;
        darr = new int[n];

        while (maxNum / digit > 1) {

            int count[] = new int[10];

            for (int i = 0; i < 10; i++) {
                count[i] = 0;
                writes++;
            }

            for (in = 0; in < n; in++) {
                count[(arr[in] / digit) % 10]++;
                writes++;
                accesses++;
                GUI.repaint();
                GUI.sleep(2);
            }
            in = 0;

            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
                accesses++;
                writes++;
            }

            for (int i = n - 1; i >= 0; i--) {
                darr[count[(arr[i] / digit) % 10] - 1] = arr[i];
                count[(arr[i] / digit) % 10]--;
                writes += 2;
                accesses++;
                GUI.repaint();
                GUI.sleep(2);
            }

            for (int i = 0; i < n; i++) {
                arr[i] = darr[i];
                accesses++;
                writes++;
            }

            digit *= 10;
        }
        GUI.repaint();

    }

    public void draw(Graphics g) {
        if (in > 0) {

            g.setColor(Color.red);
            g.drawLine(in, GUI.maxValue + 50, in, GUI.maxValue + 50 - GUI.arr[in]);

            g.setColor(new Color(45, 148, 43));
            for (int j = 0; j < in; j++) {
                g.drawLine(j, GUI.maxValue + 50, j, GUI.maxValue + 50 - GUI.arr[j]);
            }
            g.setColor(Color.black);
            for (int k = in; k < GUI.arr.length; k++) {
                g.drawLine(k, GUI.maxValue + 50, k, GUI.maxValue + 50 - GUI.arr[k]);
            }
            g.setColor(Color.black);
        } else {
            g.setColor(Color.darkGray);
            for (int i = 0; i < darr.length; i++) {

                g.drawLine(i, GUI.maxValue + 50, i, GUI.maxValue + 50 - darr[i]);
            }
        }
    }

    public int getMax(int[] arr) {

        int maxNum = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxNum) {
                maxNum = arr[i];
            }
        }
        return maxNum;
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

        count = new int[10];
        JPanel content = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int ymax = f.getHeight() - 100;
                int xmin = 40;
                int xmax = f.getWidth() - 90;
                g.drawLine(xmax, ymax, xmin, ymax);
                g.drawLine(xmax, ymax - 50, xmin, ymax - 50);
                g.drawLine(xmax, ymax, xmax, ymax - 50);
                int placePerNumber = (xmax - xmin) / darr.length;
                g.setFont(new Font("Calibri", Font.PLAIN, 20));
                for (int i = 0; i < darr.length; i++) {
                    g.drawLine(xmin + (i) * placePerNumber, ymax - 50, xmin + (i) * placePerNumber, ymax);
                    int x = xmin + (i + 1) * placePerNumber - placePerNumber / 3 - placePerNumber / 4;
                    if (i == spInDarr) {
                        g.setColor(Color.red);
                        if (i >= 10) {

                            g.drawString(String.valueOf(i), x - 5, ymax - 70);
                        } else {

                            g.drawString(String.valueOf(i), x, ymax - 70);
                        }
                        g.setColor(Color.BLACK);
                        AttributedString re = new AttributedString(String.valueOf(darr[i]));

                        re.addAttribute(TextAttribute.FONT, new Font("Calibri", Font.PLAIN, 20));
                        if (darr[i] >= 10) {

                            re.addAttribute(TextAttribute.FOREGROUND, Color.red, 2 - (digit + 1), 2 - (digit));
                            g.drawString(re.getIterator(), x - 5, ymax - 20);
                        } else {

                            re.addAttribute(TextAttribute.FOREGROUND, Color.red, 0, 1);
                            g.drawString(re.getIterator(), x, ymax - 20);

                        }
                    } else {
                        if (darr[i] >= 10) {
                            g.drawString(String.valueOf(darr[i]), x - 5, ymax - 20);
                        } else {
                            g.drawString(String.valueOf(darr[i]), x, ymax - 20);
                        }
                        if (i >= 10) {

                            g.drawString(String.valueOf(i), x - 5, ymax - 70);
                        } else {

                            g.drawString(String.valueOf(i), x, ymax - 70);
                        }

                    }
                }
                ymax = f.getHeight() - 250;
                xmin = placePerNumber * 5 + 40;
                xmax = f.getWidth() - (70 + placePerNumber * 5 + 20);
                g.drawLine(xmax, ymax, xmin, ymax);
                g.drawLine(xmax, ymax - 50, xmin, ymax - 50);
                g.drawLine(xmax, ymax, xmax, ymax - 50);
                placePerNumber = (xmax - xmin) / count.length;
                for (int i = 0; i < count.length; i++) {
                    g.drawLine(xmin + (i) * placePerNumber, ymax - 50, xmin + (i) * placePerNumber, ymax);
                    int x = xmin + (i + 1) * placePerNumber - placePerNumber / 3 - placePerNumber / 4;
                    if (i == spInCo) {
                        String toDraw = String.valueOf(i);
                        g.setColor(Color.red);
                        if (count[i] >= 10) {
                            g.drawString(String.valueOf(count[i]), x - 5, ymax - 20);
                        } else {
                            g.drawString(String.valueOf(count[i]), x, ymax - 20);
                        }
                        g.setColor(Color.black);
                    } else {
                        g.setColor(Color.BLACK);
                        if (count[i] >= 10) {
                            g.drawString(String.valueOf(count[i]), x - 5, ymax - 20);
                        } else {
                            g.drawString(String.valueOf(count[i]), x, ymax - 20);
                        }
                    }
                    if (i == spInCoKe) {
                        g.setColor(Color.red);

                        g.drawString(String.valueOf(i), x, ymax - 70);
                        g.setColor(Color.BLACK);
                    } else {

                        g.drawString(String.valueOf(i), x, ymax - 70);
                    }
                }
                ymax = f.getHeight() - 400;
                xmin = 40;
                xmax = f.getWidth() - (70 + 20);
                g.drawLine(xmax, ymax, xmin, ymax);
                g.drawLine(xmax, ymax - 50, xmin, ymax - 50);
                g.drawLine(xmax, ymax, xmax, ymax - 50);
                placePerNumber = (xmax - xmin) / ar.length;
                for (int i = 0; i < ar.length; i++) {
                    g.drawLine(xmin + (i) * placePerNumber, ymax - 50, xmin + (i) * placePerNumber, ymax);
                    int x = xmin + (i + 1) * placePerNumber - placePerNumber / 3 - placePerNumber / 4;
                    if (i == spInArr) {

                        StringBuilder toDraw = new StringBuilder(String.valueOf(ar[i]));
                        if (ar[i] < 10) {

                            for(int j = 0;j<digit;j++)
                                toDraw.insert(0, "0");
                        }
                        AttributedString re = new AttributedString(toDraw.toString());

                        re.addAttribute(TextAttribute.FONT, new Font("Calibri", Font.PLAIN, 20));
                        if (ar[i] >= 10) {

                            re.addAttribute(TextAttribute.FOREGROUND, Color.red, 2 - (digit + 1), 2 - (digit));
                            g.drawString(re.getIterator(), x - 5*digit, ymax - 20);
                        } else {

                            re.addAttribute(TextAttribute.FOREGROUND, Color.red, 0, 1);
                            g.drawString(re.getIterator(), x-5*digit, ymax - 20);

                        }
                    } else {
                        if (ar[i] >= 10)
                            g.drawString(String.valueOf(ar[i]), x - 5, ymax - 20);
                        else {
                            StringBuilder toDraw = new StringBuilder(String.valueOf(ar[i]));
                            for(int j = 0;j<digit;j++)
                                toDraw.insert(0, "0");
                            g.drawString(toDraw.toString(), x-5*digit, ymax - 20);
                        }
                    }
                }

            }
        };

        JLabel explain = new JLabel("Numbers over Elements of middle Array represent the key whose number is stored there");
        f.repaint();
        content.add(explain);
        explain.setFont(new Font("Calibri", Font.PLAIN, 20));
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


        digit = 0;
        int maxNum = getMax(ar);
        int n = ar.length;
        darr = new int[n];
        int helDigit = 1;
        GUI.sleep(3000);
        System.out.println(maxNum/helDigit);
        while (Math.floor(maxNum/helDigit) >= 1) {


            for (int i = 0; i < 10; i++) {
                count[i] = 0;
            }

            Arrays.fill(darr,0);
            explain.setText("Counting how often each key is in digit " + digit);
            f.repaint();
            GUI.sleep(2000);
            for (in = 0; in < n; in++) {
                spInCoKe = (ar[in] / helDigit) % 10;
                spInArr = in;
                f.repaint();
                GUI.sleep(1000);
                spInCo = (ar[in] / helDigit) % 10;
                count[(ar[in] / helDigit) % 10]++;
                f.repaint();
                GUI.sleep(500);
                spInCo = -1;

            }
            in = 0;
            explain.setText("Converting Count array to Indexes. Every Number in this Array now represents where in the originial array a number with this key should go");
            f.repaint();
            spInDarr = -1;
            spInArr = -1;
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
                spInCo = i;
                spInCoKe = i;
                f.repaint();
                GUI.sleep(700);
            }

            explain.setText("Adding numbers sorted by digit " + digit + " to new Array");
            f.repaint();
            for (int i = n - 1; i >= 0; i--) {
                spInArr = i;
                spInCo = (ar[i] / helDigit) % 10;
                spInCoKe = spInCo;
                spInDarr = -1;

                count[(ar[i] / helDigit) % 10]--;
                f.repaint();
                GUI.sleep(1000);

                spInDarr = count[(ar[i] / helDigit) % 10];

                f.repaint();
                GUI.sleep(700);
                darr[count[(ar[i] / helDigit) % 10]] = ar[i];
                f.repaint();
                GUI.sleep(1000);
            }

            for (int i = 0; i < n; i++) {
                ar[i] = darr[i];
            }
            helDigit *= 10;
            digit += 1;
            System.out.println(Math.floor(maxNum/helDigit));
        }
        System.out.println("The End");
        explain.setText("Finished!");

    }
}
