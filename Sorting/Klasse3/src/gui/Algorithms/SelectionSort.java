package src.gui.Algorithms;

import src.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class SelectionSort extends SortingAlgorithm{

	public void sort(int[] ar) {
        running = true;
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
			GUI.repaint();
			GUI.sleep(2);

			s++;

		}
		running = false;
	}
	public SelectionSort(){
		info = "SelectionSort: 2ms after each swap";
	}

}
