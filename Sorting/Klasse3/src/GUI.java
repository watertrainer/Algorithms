package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Random;

import org.jetbrains.annotations.Nullable;
import src.gui.Algorithms.*;
import src.gui.Algorithms.Quicksort;

public class GUI {
    public static int maxValue = 800;
    public static boolean allowMultiple, changeOnResize = false;
    public static boolean running = false;
    static JFrame f;
    static int[] arr;
    public static int arrLength;
    static @Nullable Bucketsort bucket;
    static ArrayList<SortingAlgorithm> algos;
    static JTextArea info;

    static void init() {
        f = new JFrame();
        f.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                if (changeOnResize) {
                    arrLength = GUI.f.getWidth() - 150;
                    maxValue = GUI.f.getHeight() - 90;
                    arr = new int[arrLength];
                    fastRandomize(arr);
                }
            }
        });
        f.setSize(1150, maxValue + 90);
        arrLength = 1000;
        algos = new ArrayList<>(10);
        JPanel p = new JPanel() {
            @Override
            public void paint(Graphics g) {

                super.paint(g);
                if (bucket == null || bucket.insertion) {
                    for (int i = 0; i < arr.length; i++) {
                        g.setColor(Color.black);
                        g.drawLine(i, maxValue + 50, i, maxValue + 50 - arr[i]);
                    }
                } else {
                    bucket.draw(g);
                }

            }
        };
        p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.PAGE_AXIS));

        JButton random = new JButton("Randomize");
        random.setAlignmentX(Component.RIGHT_ALIGNMENT);
        random.addActionListener(e -> {
            if (!allowMultiple && (!algos.isEmpty() || GUI.running)) {
                return;
            }
            Thread t = new Thread() {
                public void run() {

                    Randomize r = new Randomize();
                    algos.add(r);
                    updateText();
                    r.sort(arr);
                    algos.remove(r);
                    updateText();
                }
            };
            t.start();
        });
        buttonPane.add(random);

        JButton bubble = new JButton("BubbleSort");
        bubble.setAlignmentX(Component.RIGHT_ALIGNMENT);
        bubble.addActionListener(e -> {
            Thread t = new Thread(() -> {
                if (!allowMultiple && (!algos.isEmpty() || GUI.running)) {
                    return;
                }

                BubbleSortSave b = new BubbleSortSave();
                algos.add(b);
                updateText();
                b.sort(arr);
                algos.remove(b);
                updateText();

            });
            t.start();
        });
        buttonPane.add(bubble);

        JButton select = new JButton("SelectionSort");
        select.setAlignmentX(Component.RIGHT_ALIGNMENT);
        select.addActionListener(e -> {
            Thread t = new Thread(() -> {
                if (!allowMultiple && (!algos.isEmpty() || GUI.running)) {
                    return;
                }

                SelectionSort s = new SelectionSort();
                algos.add(s);
                updateText();
                s.sort(arr);
                algos.remove(s);
                updateText();
            });
            t.start();
        });
        buttonPane.add(select);

        JButton insert = new JButton("InsertionSort");
        insert.setAlignmentX(Component.RIGHT_ALIGNMENT);
        insert.addActionListener(e -> {
            Thread t = new Thread(() -> {
                if (!allowMultiple && (!algos.isEmpty() || GUI.running)) {
                    return;
                }
                InsertionSort i = new InsertionSort();
                algos.add(i);
                updateText();
                i.sort(arr);
                algos.remove(i);

                updateText();
            });
            t.start();
        });
        buttonPane.add(insert);

        JButton quick = new JButton("Quicksort");
        quick.setAlignmentX(Component.RIGHT_ALIGNMENT);
        quick.addActionListener(e -> {
            Thread t = new Thread(() -> {
                if (!allowMultiple && (!algos.isEmpty() || GUI.running)) {
                    return;
                }
                Quicksort q = new Quicksort();
                algos.add(q);
                updateText();
                q.sort(arr);
                algos.remove(q);

                updateText();
            });
            t.start();
        });
        buttonPane.add(quick);

        JButton heap = new JButton("HeapSort");
        heap.setAlignmentX(Component.RIGHT_ALIGNMENT);
        heap.addActionListener(e -> {
            Thread t = new Thread(() -> {
                if (!allowMultiple && (!algos.isEmpty() || GUI.running)) {
                    return;
                }
                Heapsort h = new Heapsort();
                algos.add(h);
                updateText();
                h.sort(arr);
                algos.remove(h);

                updateText();
            });
            t.start();
        });
        buttonPane.add(heap);


        JButton bucket = new JButton("BucketSort");
        bucket.setAlignmentX(Component.RIGHT_ALIGNMENT);
        bucket.addActionListener(e -> {
            if (GUI.bucket == null) {
                GUI.bucket = new Bucketsort();
                Thread t = new Thread(() -> {
                    if (!allowMultiple && (!algos.isEmpty() || GUI.running)) {
                        return;
                    }
                    algos.add(GUI.bucket);
                    updateText();
                    GUI.bucket.sort(arr);
                    GUI.bucket = null;
                    algos.remove(GUI.bucket);

                    updateText();
                });
                t.start();
            }
        });
        buttonPane.add(bucket);


        JCheckBox allowMult = new JCheckBox("Allow Multiple?");
        allowMult.setAlignmentX(Component.RIGHT_ALIGNMENT);
        allowMult.addActionListener(actionEvent -> allowMultiple = !allowMultiple);
        buttonPane.add(allowMult);

        JCheckBox changeOnResize = new JCheckBox("Change on Resize?");
        changeOnResize.setAlignmentX(Component.RIGHT_ALIGNMENT);
        changeOnResize.addActionListener(actionEvent -> {
            GUI.changeOnResize = !GUI.changeOnResize;
            if (GUI.changeOnResize) {
                arrLength = GUI.f.getWidth() - 150;
                GUI.maxValue = f.getHeight() - 90;
                arr = new int[arrLength];
                fastRandomize(arr);
            }
        });

        buttonPane.add(changeOnResize);

        buttonPane.add(Box.createVerticalGlue());
        info = new JTextArea("Select a Sorting Algorithm");
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
        infoPanel.add(info);
        infoPanel.add(Box.createVerticalGlue());
        info.setBorder(null);
        info.setBackground(f.getBackground());
        info.setEditable(false);


        buttonPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(infoPanel);


        p.add(Box.createHorizontalGlue());
        p.add(buttonPane);


        f.setContentPane(p);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void repaint() {
        f.repaint();
    }

    public static void sleep(int i) {
        try {
            Thread.sleep((i));


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        arr = new int[1000];
        init();
        Randomize r = new Randomize();
        GUI.algos.add(r);
        GUI.updateText();
        r.sort(arr);
        algos.remove(r);
        GUI.updateText();
    }
    public static void fastRandomize(int[] ar){
        for(int i = 0; i < GUI.arrLength; i++){
            ar[i] = (int) (Math.random()*GUI.maxValue);
        }
        repaint();
    }
    public static void updateText() {
        if (!allowMultiple) {
            if (!algos.isEmpty()) {
                info.setText(algos.get(0).getInfo());
            } else {
                int counter = 0;
                for (int i = 1; i < arr.length; i++) {
                    if (arr[i - 1] > arr[i])
                        counter++;
                }
                info.setText("Finished. There are " + counter + " wrong placed Numbers");
            }
        } else {
            if (!algos.isEmpty()) {
                StringBuilder info = new StringBuilder("The following Algorithms are calculating:"+System.lineSeparator());
                for (int i = 0; i < algos.size(); i++) {
                    info.append(algos.get(i).getInfo()).append(System.lineSeparator());
                }
                GUI.info.setText(info.toString());

            } else {
                int counter = 0;
                for (int i = 1; i < arr.length; i++) {
                    if (arr[i - 1] > arr[i])
                        counter++;
                }
                info.setText("Finished. " + counter + " wrong placed Numbers");
            }
        }
        GUI.repaint();
    }
}
