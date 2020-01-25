package src.gui.algos;
import src.gui.GUI;

public class SelectionSort extends SortingAlgorithm{
	@Override
	public void sort(int[] ar) {
        running = true;
		int s = 0;
		while(s<ar.length) {
			comparisons++;
			int smallest = Integer.MAX_VALUE;
			int smallestIndex = 0;
			for(int i = s;i<ar.length;i++) {
				if (ar[i] < smallest) {
					smallest = ar[i];
					accesses++;
					smallestIndex = i;
					writes+=2;
				}
				accesses++;
				comparisons++;
			}
			swap(s,smallestIndex,ar);

			s++;

		}
		running = false;
		super.sort(ar);
	}
	public SelectionSort(){
		info = "SelectionSort: 4ms after each swap";
		wait = 4;
	}

}
