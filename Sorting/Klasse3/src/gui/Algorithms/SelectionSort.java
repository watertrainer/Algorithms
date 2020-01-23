package src.gui.Algorithms;

import src.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class SelectionSort extends SortingAlgorithm{
	@Override
	public void sort(int[] ar) {
        running = true;
		int s = 0;
		while(s<ar.length) {
			int smallest = Integer.MAX_VALUE;
			int smallestIndex = 0;
			for(int i = s;i<ar.length;i++) {
				if (ar[i] < smallest) {
					comparisons++;
					smallest = ar[i];
					accesses+=2;
					smallestIndex = i;
				}
			}
			swap(s,smallestIndex,ar);

			s++;

		}
		running = false;
		super.sort(ar);
	}
	public SelectionSort(){
		info = "SelectionSort: 2ms after each swap";
		wait = 2;
	}

}
