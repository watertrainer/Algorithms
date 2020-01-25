package src.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import src.gui.AlgosKeyListener;
import src.gui.algos.*;
import src.gui.algos.InsertionSort;
import src.gui.algos.Quicksort;

public class GUI {
    public static int maxValue = 800;
    public static int arrLength;


    public static boolean allowMultiple, changeOnResize = false;
    public static volatile int[] arr;
    public static Bucketsort bucket;
    public static Radixsort radix;

    public static ArrayList<SortingAlgorithm> algos;

    static JTextArea info;
    static JFrame f;
    public static JCheckBox allowMult;

    static void init() {
        f = new JFrame();
        arrLength = 1000;
        algos = new ArrayList<>(10);


        JPanel buttonPane = new JPanel();
        buttonPane.setOpaque(false);
        JPanel p = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                if (bucket != null&& !bucket.insertion) {
                    bucket.draw(g);
                } else if (radix != null) {
                    radix.draw(g);
                } else {
                    if(AlgosKeyListener.e){
                        g.setColor(Color.green);
                        g.drawRect(buttonPane.getX(),buttonPane.getY(),buttonPane.getWidth(),buttonPane.getHeight());
                        g.setColor(Color.black);
                    }
                    for (int i = 0; i < arr.length; i++) {
                        g.setColor(Color.black);
                        g.drawLine(i, maxValue + 50, i, maxValue + 50 - arr[i]);


                    }

                }
                updateText();
                super.paintComponent(g);
            }
        };
        p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
        p.setOpaque(false);


        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.PAGE_AXIS));

        JButton random = new JButton("Randomize");
        random.setAlignmentX(Component.RIGHT_ALIGNMENT);
        random.setFocusable(false);
        random.addActionListener(new AlgoActionListener(){

            @Override
            public SortingAlgorithm initAlgo() {
                return new Randomize();
            }
        });
        buttonPane.add(random);

        JButton bubble = new JButton("BubbleSort");
        bubble.setAlignmentX(Component.RIGHT_ALIGNMENT);
        bubble.setFocusable(false);
        bubble.addActionListener(new AlgoActionListener(){

            @Override
            public SortingAlgorithm initAlgo() {
                return new BubbleSortSave();
            }
        });
        buttonPane.add(bubble);

        JButton select = new JButton("SelectionSort");
        select.setAlignmentX(Component.RIGHT_ALIGNMENT);
        select.setFocusable(false);
        select.addActionListener(new AlgoActionListener(){

            @Override
            public SortingAlgorithm initAlgo() {
                return new SelectionSort();
            }
        });
        buttonPane.add(select);

        JButton insert = new JButton("InsertionSort");
        insert.setAlignmentX(Component.RIGHT_ALIGNMENT);
        insert.setFocusable(false);
        insert.addActionListener(new AlgoActionListener(){

            @Override
            public SortingAlgorithm initAlgo() {
                return new InsertionSort();
            }
        });
        buttonPane.add(insert);

        JButton quick = new JButton("Quicksort");
        quick.setAlignmentX(Component.RIGHT_ALIGNMENT);
        quick.setFocusable(false);
        quick.addActionListener(new AlgoActionListener(){

            @Override
            public SortingAlgorithm initAlgo() {
                return new Quicksort();
            }
        });
        buttonPane.add(quick);

        JButton merge = new JButton("MergeSort");
        merge.setAlignmentX(Component.RIGHT_ALIGNMENT);
        merge.setFocusable(false);
        merge.addActionListener(new AlgoActionListener(){

            @Override
            public SortingAlgorithm initAlgo() {
                return new MergeSort();
            }
        });
        buttonPane.add(merge);

        JButton heap = new JButton("HeapSort");
        heap.setAlignmentX(Component.RIGHT_ALIGNMENT);
        heap.setFocusable(false);
        heap.addActionListener(new AlgoActionListener(){

            @Override
            public SortingAlgorithm initAlgo() {
                return new Heapsort();
            }
        });
        buttonPane.add(heap);


        JButton bucket = new JButton("BucketSort");
        bucket.setAlignmentX(Component.RIGHT_ALIGNMENT);
        bucket.setFocusable(false);
        bucket.addActionListener(e -> {
            if(AlgosKeyListener.e){
                Thread t = new Thread(()->{
                    new Bucketsort().explanationSort();
                });
                t.start();
            }else
            if (GUI.bucket == null) {
                if (!algos.isEmpty()) {
                    return;
                }
                if (radix != null) return;
                Thread t = new Thread(() -> {

                    GUI.bucket = new Bucketsort();
                    algos.add(GUI.bucket);
                    GUI.bucket.sort(arr);
                    algos.remove(GUI.bucket);
                    GUI.bucket = null;

                });
                t.start();
            }
        });
        buttonPane.add(bucket);


        JButton radix = new JButton("Radix Sort");
        radix.setAlignmentX(Component.RIGHT_ALIGNMENT);
        radix.setFocusable(false);
        radix.addActionListener(e -> {
            if(AlgosKeyListener.e){
                Thread t = new Thread(()->{
                    new Radixsort().explanationSort();
                });
                t.start();
            }else
            if (GUI.radix == null) {
                if (!algos.isEmpty()) {
                    return;
                }
                if (GUI.bucket != null) return;
                Thread t = new Thread(() -> {

                    GUI.radix = new Radixsort();
                    algos.add(GUI.radix);
                    GUI.radix.sort(arr);
                    algos.remove(GUI.radix);
                    GUI.radix = null;

                });
                t.start();
            }
        });
        buttonPane.add(radix);


        allowMult = new JCheckBox("Allow Multiple?");
        allowMult.setAlignmentX(Component.RIGHT_ALIGNMENT);
        allowMult.setFocusable(false);
        allowMult.addActionListener(actionEvent -> {
            allowMultiple = !allowMultiple;
        });
        buttonPane.add(allowMult);

        JCheckBox changeOnResize = new JCheckBox("Change on Resize?");
        changeOnResize.setAlignmentX(Component.RIGHT_ALIGNMENT);
        changeOnResize.setFocusable(false);
        changeOnResize.addActionListener(actionEvent -> {
                GUI.changeOnResize = !GUI.changeOnResize;
                if (GUI.changeOnResize && GUI.radix == null && GUI.bucket == null) {
                    arrLength = GUI.f.getWidth() - 150;
                    GUI.maxValue = f.getHeight() - 90;
                    arr = new int[arrLength];
                    fastRandomize(arr);
                }

        });
        buttonPane.add(changeOnResize);

        buttonPane.add(Box.createVerticalGlue());

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
        infoPanel.setOpaque(false);

        info = new JTextArea("Select a Sorting Algorithm");
        info.setBorder(null);
        info.setBackground(f.getBackground());
        info.setOpaque(false);
        info.setEditable(false);
        info.setFocusable(false);


        infoPanel.add(info);

        infoPanel.add(Box.createVerticalGlue());

        buttonPane.setAlignmentX(Component.LEFT_ALIGNMENT);

        p.add(infoPanel);
        p.add(Box.createHorizontalGlue());
        p.add(buttonPane);

        f.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                if (GUI.changeOnResize && GUI.radix == null && GUI.bucket == null) {
                    arrLength = GUI.f.getWidth() - 150;
                    maxValue = GUI.f.getHeight() - 90;
                    arr = new int[arrLength];
                    GUI.fastRandomize(arr);
                }
            }
        });

        p.addKeyListener(new AlgosKeyListener());
        p.setFocusable(true);

        f.setSize(1150, maxValue + 90);
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
        }
    }


    public static void main(String[] args) {
        arr = new int[1000];
        init();
        Randomize r = new Randomize();
        algos.add(r);
        r.sort(arr);
        algos.remove(r);
    }

    public static void fastRandomize(int[] ar) {
        for (int i = 0; i < GUI.arrLength; i++) {
            ar[i] = (int) (Math.random() * maxValue);
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
                StringBuilder info = new StringBuilder("The following Algorithms are calculating:" + System.lineSeparator());
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
    }
}
