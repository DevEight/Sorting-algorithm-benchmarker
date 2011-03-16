import java.util.ArrayList;
import java.util.Arrays;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Random;

/**
 * Used to test IntSorters against the Arrays.sort method to see if they're sorting correctly.
 * @author Björn Tegelund
 * @version 2011.02.23
 */
public class SortTest {
	Random random;
	ArrayList<Data> dataList;
	int[][] caseVector;
	int[] v1;
	int[] v2;
	
	@Before
	public void setUp() throws Exception {
		dataList = new ArrayList<Data>();
		random = new Random();
		for(int i = 0; i<5; i++) {
			dataList.add(new Data(random.nextInt(100000)+1, 100000,Data.Order.RANDOM));
			dataList.add(new Data(random.nextInt(100000)+1, 100000, Data.Order.ASCENDING));
			dataList.add(new Data(random.nextInt(100000)+1, 100000, Data.Order.DESCENDING));
		}
		caseVector = new int[][] {
				{},
				{1},
				{-3,1,1,1,0,0,1,1,1},
				{1,1,1,1,1,1,1}
		};
	}
	
	@Test
	public void testQS () { //Normal Quicksort
		runTests(new Quicksort(0,false));
	}
	
	@Test 
	public void testQSwRP() { //Quicksort with Random pivot
		runTests(new Quicksort(0,true));
	}
	
	@Test
	public void testQSwIS() { //Quicksort with insertionsort
		runTests(new Quicksort(3,false));
		runTests(new Quicksort(5,false));
		runTests(new Quicksort(6,false));
	}
	
	@Test
	public void testQSwISwRP() { //Quicksort with insertionsort and random pivot
		runTests(new Quicksort(3,true));
		runTests(new Quicksort(4,true));
		runTests(new Quicksort(8,true));
	}
	
	@Test
	public void testInsertionSort() { //Normal insertionsort
		runTests(new Insertionsort());
	}
	
	private void runTests(IntSorter sorter) {
		sortData(sorter);
		specialCases(sorter);
	}
	
	private void sortData(IntSorter sorter) {
		for(Data data : dataList) {
			v1 = data.get();
			v2 = data.get();
			Arrays.sort(v1);
			sorter.sort(v2);
			assertArrayEquals(v1,v2);
		}
	}
	
	private void specialCases(IntSorter sorter) {
		for(int[] specialCase : caseVector) {
			v1 = specialCase;
			v2 = specialCase;
			Arrays.sort(v1);
			sorter.sort(v2);
			assertArrayEquals(v1,v2);
		}
	}
}
