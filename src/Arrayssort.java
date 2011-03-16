import java.util.Arrays;

/**
 * A class that implements IntSorter and uses Arrays.sort method.
 * This class is used to make testing and benchmarking easier, as there is no longer a need to write separate methods for Arrays.sort (it by default is not an IntSorter).
 * @author Björn Tegelund
 * @version 2011.02.23
 */
public class Arrayssort implements IntSorter{
	/**
	 * Sorts a vector of integers.
	 * @param v The vector the be sorted.
	 */
	public void sort(int[] v) {
		Arrays.sort(v);
	}
	
	/**
	 * Used when benchmarking to see which particular IntSorter is being tested.
	 */
	public String toString() {
		return "Arrays.sort";
	}
}
