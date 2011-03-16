import java.util.ArrayList;

/**
 * A class used for benchmarking IntSorters.
 * @author Björn Tegelund
 * @version 2011.02.24
 */
public class Benchmark {
	Stopwatch stopwatch;
	Data data;
	
	/**
	 * Checks how long time the given sorter takes to sort the given vector.
	 * @param v The vector to be sorted.
	 * @param sorter The IntSorter to be used.
	 * @return The time the sorting took in nanoseconds.
	 */
	public long timeSort(int[] v, IntSorter sorter) {
		stopwatch = new Stopwatch();
		stopwatch.start();
		sorter.sort(v);
		stopwatch.stop();
		return stopwatch.nanoseconds();
	}
	
	/**
	 * Finds the optimal swap point for the Quicksorts that use insertion sort.
	 * @param max The maximum swap point to be used.
	 * @return The optimal break point.
	 */
	public int findOptimalSwapPoint(int max) {
		IntSorter qs;
		int optimalSwap = 0;
		long minimumTime = Long.MAX_VALUE;
		
		long time;
		data = new Data(100000,10000,Data.Order.RANDOM);
		for(int j = 1; j <= 3; j++) {
			System.out.println("Running optimization set: " + j);
			for(int i = max; i > 2; i--) {
				qs = new Quicksort(i,false);
				time = averageSortTime(data,qs,10);
				if(time < minimumTime) {
					optimalSwap = i;
					minimumTime = time;
					System.out.println("Optimal swap is now: " + i);
				}
			}
		}
		return optimalSwap;
	}
	
	/**
	 * Benchmarks a vector of IntSorters. They sort vectors of length 10^i <= 1000000.
	 * Note: To benchmark vectors with only identical elements set maxElementValue to 1.
	 * 
	 * @param sorters The vector of IntSorters to be benchmarked.
	 * @param order The order of the data to be used.
	 * @param maxElementValue The maximum element used to be allowed in the benchmarking data.
	 */
	public void benchmarkIntSorters(IntSorter[] sorters, Data.Order order, int maxElementValue) {
		for(int i = 10; i <= 1000000; i*=10) {
			data = new Data(i,maxElementValue,order);
			System.out.println("\n\nSorting " + i + " elements that are " + order + " order:");
			for(IntSorter sorter : sorters) {
				System.out.println(sorter.toString());
				System.out.println("Took: " + averageSortTime(data,sorter,100) + " ns.");
			}
		}
	}
	
	/**
	 * The average time it takes for given IntSorter to sort given data. The average is taken out of a given amount of repetitions.
	 * @param data The data which corresponding vector should be sorted.
	 * @param sorter The IntSorter that should be used.
	 * @param repetitions The amount of repetitions to be used and calculated the average from.
	 * @return The average sorting time.
	 */
	public long averageSortTime(Data data, IntSorter sorter, int repetitions) {
		long[] results = new long[repetitions];
		for(int i=0; i < repetitions; i++) {
			results[i] = timeSort(data.get(), sorter);
		}
		long total = 0;
		for(int i=0; i < repetitions;i++) {
			total += results[i];
		}
		return total/repetitions;
	}
	
	public static void main(String[] args) {
		Benchmark b = new Benchmark();
		int optimalSwapPoint = b.findOptimalSwapPoint(500);
		IntSorter[] sorters = new IntSorter[] {
				new Arrayssort(),
				new Quicksort(0,false),
				new Quicksort(0,true),
				new Quicksort(optimalSwapPoint, false),
				new Quicksort(optimalSwapPoint, true),
				new Insertionsort()
		};
		b.benchmarkIntSorters(sorters, Data.Order.RANDOM, 1); //Benchmarking with only identical elements
		b.benchmarkIntSorters(sorters, Data.Order.RANDOM, 1000000);
		b.benchmarkIntSorters(sorters, Data.Order.DESCENDING, 1000000);
		b.benchmarkIntSorters(sorters, Data.Order.ASCENDING, 1000000);
	}
}
