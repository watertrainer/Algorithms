package src.gui.Algorithms;

import src.GUI;

public class InsertionSort extends SortingAlgorithm{

	public InsertionSort(){
		info = "InsertionSort:4ms after each inserted Element";
	}


	public synchronized void sort(int[] sortieren) {
	running = true;
	int temp;
	for (int i = 1; i < sortieren.length; i++) {
		temp = sortieren[i];
		accesses++;
		int j = i;
		while (j > 0 && sortieren[j - 1] > temp) {

			sortieren[j] = sortieren[j - 1];
			accesses+=2;
			comparisons++;
			j--;
		}

		GUI.repaint();
		GUI.sleep(4);
		sortieren[j] = temp;
	}
	running = false;

		super.sort(sortieren);
}


}