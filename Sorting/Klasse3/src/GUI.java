package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import src.gui.Algorithms.*;
import src.gui.Algorithms.Quicksort;

public class GUI {
    static JFrame f;
    static int[] arr;
    static int arrLength;
    public static int maxValue = 800;
    public static boolean bucket,allowMultiple,changeOnResize = false;
    public static boolean running = false;
    static JLabel info;
    static void init(){
        f = new JFrame();
        f.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                if(changeOnResize) {
                    arrLength = GUI.f.getWidth() - 150;
                    maxValue = GUI.f.getHeight() -90;
                    arr = new int[arrLength];
                    GUI.randomize(true);
                }
            }
        });
        f.setSize(1150,maxValue+90);
        arrLength = 1000;
        JPanel p = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                if(!bucket){
                    for(int i = 0;i<arr.length;i++) {
                        g.setColor(Color.black);
                        g.drawLine(i, maxValue+50, i, maxValue+50-arr[i]);
                    }
                }
                else{
                    Bucketsort.draw(g);
                }
            }
        };
        p.setLayout(new BorderLayout());
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.PAGE_AXIS));

        JButton random = new JButton("Randomize");
        random.setAlignmentX(Component.RIGHT_ALIGNMENT);
        random.addActionListener(e -> {
            if(!allowMultiple && running){
                return;
            }
            Thread t = new Thread(){
                public void run(){
                    setRunning(true);
                    randomize(false);
                    setRunning(false);
                }
            };
            t.start();
        });
        buttonPane.add(random);

        JButton bubble = new JButton("BubbleSort");
        bubble.setAlignmentX(Component.RIGHT_ALIGNMENT);
        bubble.addActionListener(e -> {
            Thread t = new Thread(() ->{
                if(!allowMultiple && GUI.running){
                    return;
                }
                setRunning(true);
                BubbleSortSave.sort(arr);
                setRunning(false);
            });
            t.start();
        });
        buttonPane.add(bubble);

        JButton select = new JButton("SelectionSort");
        select.setAlignmentX(Component.RIGHT_ALIGNMENT);
        select.addActionListener(e -> {
            Thread t = new Thread(() ->{
                if(!allowMultiple && GUI.running){
                    return;
                }
                setRunning(true);
                SelectionSort.sort(arr);
                setRunning(false);
            });
            t.start();
        });
        buttonPane.add(select);

        JButton insert = new JButton("InsertionSort");
        insert.setAlignmentX(Component.RIGHT_ALIGNMENT);
        insert.addActionListener(e -> {
            Thread t = new Thread(() ->{
                if(!allowMultiple && GUI.running){
                    return;
                }
                setRunning(true);
                InsertionSort.sort(arr);
                setRunning(false);
            });
            t.start();
        });
        buttonPane.add(insert);

        JButton quick = new JButton("Quicksort");
        quick.setAlignmentX(Component.RIGHT_ALIGNMENT);
        quick.addActionListener(e -> {
            Thread t = new Thread(() ->{
                if(!allowMultiple && GUI.running){
                    return;
                }
                setRunning(true);
                Quicksort.sort(arr);
                setRunning(false);
            });
            t.start();
        });
        buttonPane.add(quick);

        JButton heap = new JButton("HeapSort");
        heap.setAlignmentX(Component.RIGHT_ALIGNMENT);
        heap.addActionListener(e -> {
            Thread t = new Thread(() -> {
                if(!allowMultiple && GUI.running){
                    return;
                }
                setRunning(true);
                Heapsort.sort(arr);
                setRunning(false);
            });
            t.start();
        });
        buttonPane.add(heap);


        JButton bucket= new JButton("BucketSort");
        bucket.setAlignmentX(Component.RIGHT_ALIGNMENT);
        bucket.addActionListener(e -> {
            if(!GUI.bucket) {
                GUI.bucket = true;
                Thread t = new Thread(() -> {
                    if(!allowMultiple && GUI.running){
                        return;
                    }
                    setRunning(true);
                    Bucketsort.sort(arr, 10);
                    GUI.bucket = false;

                    setRunning(false);
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
            if(GUI.changeOnResize) {
                arrLength = GUI.f.getWidth() - 150;
                arr = new int[arrLength];
                GUI.randomize(true);
            }
        });

        buttonPane.add(changeOnResize);


        info = new JLabel("Select a Sorting Algorithm");
        info.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(buttonPane,BorderLayout.LINE_END);
        p.add(info,BorderLayout.NORTH);
        f.setContentPane(p);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void repaint(){
        f.repaint();
    }

    public static void sleep(int i){
        try {
            Thread.sleep((i));


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void randomize(boolean fast){
        for(int i = 0; i < arrLength; i++){
            arr[i] = (int) (Math.random()*maxValue);
            repaint();
            if(!fast)
            sleep(2);
        }
    }
    public static void main(String[] args){
        arr = new int[1000];
        init();
        randomize(false);
    }

    public static void setRunning(boolean running) {
        GUI.running = running;
        if(!allowMultiple){
            if(running){
                info.setText("Calculating...");
            }
            else{
                int counter = 0;
                for(int i = 1;i<arr.length;i++){
                    if(arr[i-1]>arr[i])
                        counter++;
                }
                info.setText("Finished. There are "+counter+" wrong placed Numbers");
            }
        }
        else{
            if(running){
                info.setText("Multiple Algorithms are calculating");
            }
            else{int counter = 0;
                for(int i = 1;i<arr.length;i++){
                    if(arr[i-1]>arr[i])
                        counter++;
                }
                info.setText("Something Finished. "+counter+" wrong placed Numbers");
            }
        }
        GUI.repaint();
    }
}
