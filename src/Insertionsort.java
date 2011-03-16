/**
 * A class that uses Insertionsort to sort arrays of integers.
 * Implements the IntSorter interface.
 * 
 * @author Björn Tegelund
 * @version 2011.02.21
 */
public class Insertionsort implements IntSorter {
	/**
	 * Creates a new Insertionsorter.
	 */
	public Insertionsort() {
	}
	
	/**
	 * Sorts a vector of integers.
	 * @param v The vector to be sorted.
	 */
	@Override
	public void sort(int[] v) {
		for(int i = 1; i < v.length; i++) {
			int j = i;
			int tmp = v[i];
			while((j>0) && (v[j-1] > tmp)) {
				v[j] = v[j-1];
				j--;
			}
			v[j] = tmp;
		}
	}
	
	/**
	 * Sorts a part of a vector of integers.
	 * @param v The vector which should be sorted.
	 * @param first The first index to be sorted (inclusive).
	 * @param last The last index to be sorted (inclusive).
	 */
	public void sort(int[] v, int first, int last) {
		for(int i = first; i <= last; i++) {
			int j = i;
			int tmp = v[i];
			while((j>first) && (v[j-1] > tmp)) {
				v[j] = v[j-1];
				j--;
			}
			v[j] = tmp;
		}
	}
	
	/**
	 * Used when benchmarking to see which particular IntSorter is being tested.
	 */
	public String toString() {
		return "Insertionsort";
	}
}
