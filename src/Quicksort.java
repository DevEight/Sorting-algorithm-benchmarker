import java.util.Random;

/**
 * A Quicksort algorithm which allows you to customise the way it sorts slightly.
 * @author Björn Tegelund, Stefan Nilsson
 * @version 2011.02.23
 */
public class Quicksort implements IntSorter {
	private Random random;
	private final int swapAtLen; //Tells the algorithm at what length it should switch to Insertionsort
	private final boolean randomPivot; //Tells the algorithm whether it should use a random pivot or not.
	private Insertionsort insertionSort; //The insertionsorter that is used, should one be needed.
	
	/**
	 * Creates a new Quicksorter.
	 * @param swapAtLen At what length it should switch to using insertionsort. Use 0 to indicate no use of insertionsort.
	 * @param randomPivot Whether it should use a random pivot or not.
	 */
	public Quicksort(int swapAtLen, boolean randomPivot) {
		if(swapAtLen >= 0) {
			this.swapAtLen = swapAtLen;
			if(swapAtLen > 1) {
				insertionSort = new Insertionsort();
			}
		}
		else {
			throw new IllegalArgumentException("swapAtLen needs to be >= 0!");
		}
		this.randomPivot = randomPivot;
		random = new Random();
	}
	
	/**
	 * Sorts a vector of integers.
	 * @param v The vector to be sorted.
	 */
	public void sort(int[] v) {
		qsort(v, 0, v.length-1);
	}
	
	// Sorts the elements of the subvector v[first..last].
	private void qsort(int[] v, int first, int last) {
		if (first >= last) // Less than two elements
	        return;
		else if(swapAtLen > 1 && last-first+1 <= swapAtLen) {
			insertionSort.sort(v,first,last);
			return;
		}

	    // Choose a pivot element.
	    int p;
	    if(randomPivot) {
	    	p = v[first + random.nextInt(last - first + 1)];
	    }
	    else {
	    	p = v[(first+last)/2];
	    }

	    // Partition the elements so that every number of
	    // v[first..mid] <= p and every number of v[mid+1..last] > p.
	    int mid = partition(v, first, last, p);

	    qsort(v, first, mid);
	    qsort(v, mid+1, last);
	}
	
	/**
	 * Reorders the elements of the subarray v[first..last] so that
	 * all elements in v[first..low-1] are less than pivot,
	 * all elements in v[low..high] are equal to pivot,
	 * all elements in v[high+1..last] are greater than pivot.
	 * Returns the middle index.
	 * Precondition: first < last.
	 */
	private int partition(int[] v, int first, int last, int pivot) {
	    int low = first;
	    int mid = first;
	    int high = last;

	    while (mid <= high) {
	        // Invariant:
	        //  - v[first..low-1] < pivot
	        //  - v[low..mid-1] = pivot
	        //  - v[mid..high] are unknown
	        //  - v[high+1..last] > pivot
	        //
	        //       < pivot   = pivot      unknown     > pivot
	        //     -----------------------------------------------
	        // v: |          |          |a            |           |
	        //     -----------------------------------------------
	        //     ^          ^          ^           ^           ^
	        //    first      low        mid         high        last
	        //
	        int a = v[mid];
	        if (a < pivot) {
	            v[mid] = v[low];
	            v[low] = a;
	            low++;
	            mid++;
	        } else if (a == pivot) {
	            mid++;
	        } else { // a > pivot
	            v[mid] = v[high];
	            v[high] = a;
	            high--;
	        }
	    }
	    return (low + high)/2;
	}
	
	/**
	 * Used when benchmarking to see which particular IntSorter is being tested.
	 */
	public String toString() {
		return "Quicksort - " + (randomPivot ? "Random Pivot - " : "Normal Pivot - ") + (swapAtLen > 1 ? "Using insertion at: " + swapAtLen : "Not using insertion");
	}
}
