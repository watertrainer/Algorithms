package src;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SelectionSort {
	public static int[] sort(int[] ar) {
		JFrame f = new JFrame();
		JPanel p = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				for(int i = 0;i<ar.length;i++) {
					g.setColor(Color.green);
					g.fillRect(5*i, 0, 5, ar[i]);
					g.setColor(Color.black);
					g.drawRect(5*i, 0, 5, ar[i]);
				}
			}
		};
		f.setContentPane(p);
		f.setSize(500, 500);
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
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			s++;

		}
		return ar;
	}
	
	public static void main(String[] args) {
	
		System.out.println(Arrays.toString(sort(new int[] {3,5,9,12,352,10,13,10,1,23,1,34,456,123,45,23,123,234,23,23,34,234})));
	}
}
