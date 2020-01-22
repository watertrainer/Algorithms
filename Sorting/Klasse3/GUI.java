import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    static JFrame f;
    static int[] arr;
    static boolean bucket;
    static void init(){
        f = new JFrame();
        f.setSize(1550,1050);
        f = new JFrame();
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
        buttonPane.setLayout(new BoxLayout(buttonPane,BoxLayout.Y_AXIS));

        JButton random = new JButton("Randomize");
        random.setAlignmentX(Component.RIGHT_ALIGNMENT);
        random.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread t = new Thread(){
                    public void run(){
                        GUI.randomize();
                    }
                };
                t.start();
            }
        });
        buttonPane.add(random);

        JButton heap = new JButton("HeapSort");
        heap.setAlignmentX(Component.RIGHT_ALIGNMENT);
        heap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread t = new Thread(){
                    public void run(){
                        Heapsort.sort(arr);
                    }
                };
                t.start();
            }
        });
        buttonPane.add(heap);


        JButton bucket= new JButton("BucketSort");
        bucket.setAlignmentX(Component.RIGHT_ALIGNMENT);
        bucket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!GUI.bucket) {
                    GUI.bucket = true;
                    Thread t = new Thread() {
                        public void run() {
                            Bucketsort.sort(arr, 10);
                            GUI.bucket = false;
                        }
                    };
                    t.start();
                }
            }
        });
        buttonPane.add(bucket);


        p.add(buttonPane,BorderLayout.LINE_END);
        f.setContentPane(p);
        f.setSize(arr.length, 1000+50);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    static void repaint(){
        f.repaint();
    }

    static void sleep(int i){
        try {
            Thread.sleep((1*i));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void randomize(){
        for(int i = 0; i < arr.length; i++){
            arr[i] = (int) (Math.random()*1000);
            repaint();
            sleep(1);
        }
    }
    public static void main(String[] args){
        arr = new int[1000];
        GUI.init();
        GUI.randomize();
    }
}
