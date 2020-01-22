package src;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import Algorithms.*;

public class GUI {
    static JFrame f;
    static int[] arr;
    static int arrLength;
    public static boolean bucket,allowMultiple,changeOnResize = false;
    public static boolean running = false;
    static void init(){
        f = new JFrame();
        f.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                if(changeOnResize) {
                    arrLength = GUI.f.getWidth() - 150;
                    arr = new int[arrLength];
                    GUI.randomize(true);
                }
            }
        });
        f.setSize(1150,1050);
        arrLength = 1000;
        JPanel p = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                if(!bucket){
                    for(int i = 0;i<arr.length;i++) {
                        g.setColor(Color.black);
                        g.drawLine(i, 1000, i, 1000-arr[i]);
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
            if(!allowMultiple && GUI.running){
                return;
            }
            Thread t = new Thread(){
                public void run(){
                    GUI.running = true;
                    GUI.randomize(false);
                    GUI.running = false;
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
                GUI.running = true;
                BubbleSortSave.sort(arr);
                GUI.running = false;
            });
            t.start();
        });
        buttonPane.add(bubble);

        JButton heap = new JButton("HeapSort");
        heap.setAlignmentX(Component.RIGHT_ALIGNMENT);
        heap.addActionListener(e -> {
            Thread t = new Thread(() -> {
                if(!allowMultiple && GUI.running){
                    return;
                }
                GUI.running = true;
                Heapsort.sort(arr);
                GUI.running = false;
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
                    GUI.running = true;
                    Bucketsort.sort(arr, 10);
                    GUI.bucket = false;

                    GUI.running = false;
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

        p.add(buttonPane,BorderLayout.LINE_END);
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
            arr[i] = (int) (Math.random()*1000);
            repaint();
            if(!fast)
            sleep(1);
        }
    }
    public static void main(String[] args){
        arr = new int[1000];
        init();
        randomize(false);
    }
}
