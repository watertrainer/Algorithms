import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SelectionSort {
	static int width;
	static int height = 600;
	public static int[] sort(int[] ar) {
		JFrame f = new JFrame();
		JPanel p = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				for(int i = 0;i<ar.length;i++) {
					g.setColor(Color.green);
					g.fillRect(width*i, 0, width, ar[i]);
					g.setColor(Color.black);
					g.drawRect(width*i, 0, width, ar[i]);
				}
			}
		};
		f.setContentPane(p);
		f.setSize(width*ar.length, height+50);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int s = 0;
		while(s<ar.length) {
			int smallest = Integer.MAX_VALUE;
			int smallestIndex = 0;
			for(int i = s;i<ar.length;i++) {
				if (ar[i] < smallest) {
					smallest = ar[i];
					smallestIndex = i;
				}
			}
			ar[smallestIndex] = ar[s];
			ar[s] = smallest;
			f.repaint();
			try {
				Thread.sleep(10000/ar.length);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			s++;

		}
		return ar;
	}
	
	public static void main(String[] args) {
		int[] ar = new int[1000];
		width = 1000/ar.length;
		for(int i = 0;i<ar.length;i++){
			ar[i] = (int)(Math.random()*height);
		}

		System.out.println(Arrays.toString(sort(ar)));
	}
}
