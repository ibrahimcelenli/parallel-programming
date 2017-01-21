
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * mergesort with Fork-Join Threads Ahmet uyar
 */
public class MergeSortWithForkJoin extends RecursiveAction {

	private double[] mSource;
	private int mStart;
	private int mLength;
	private int id;

	public MergeSortWithForkJoin(double[] src, int start, int length, int id) {
		mSource = src;
		mStart = start;
		mLength = length;
		this.id = id;
		// System.out.println("my id: "+ id + " my start index: "+mStart+" my
		// length: "+mLength);
	}

	// Average pixels from source, write results into destination.
	protected void computeDirectly() {
		Arrays.sort(mSource, mStart, mStart + mLength);
	}

	@Override
	protected void compute() {
		if (mLength <= blockSize) {
			computeDirectly();
			return;
		}

		int split = mLength / 2;
		MergeSortWithForkJoin th1 = new MergeSortWithForkJoin(mSource, mStart, split, 2 * id);
		MergeSortWithForkJoin th2 = new MergeSortWithForkJoin(mSource, mStart + split, mLength - split, 2 * id + 1);
		invokeAll(th1, th2);
		merge(source, th1.mStart, th2.mStart, th2.mStart + th2.mLength);
	}

	public static double source[] = new double[10000000];
	static double source2[] = new double[10000000];
	// static int cores = Runtime.getRuntime().availableProcessors();
	static int cores = 2;
	public static double aux[] = new double[source.length];
	protected static int blockSize = (int) Math.ceil(source.length / (double) cores);

	public static void main(String[] args) throws Exception {

		arrayInit();
		long startTime = System.currentTimeMillis();
		Arrays.sort(source2);
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		System.out.println("seq time: " + duration);

		MergeSortWithForkJoin fb = new MergeSortWithForkJoin(source, 0, source.length, 1);
		ForkJoinPool pool = new ForkJoinPool(2);

		startTime = System.currentTimeMillis();
		pool.invoke(fb);
		endTime = System.currentTimeMillis();
		duration = endTime - startTime;
		System.out.println("sorting took " + duration + " milliseconds.");

		isSorted();

		/*
		 * for (int j = 0; j < 1000; j++) { System.out.println(source[j]); }
		 */

	}

	public static void arrayInit() {

		BufferedReader zem = null;

		try {
			zem = new BufferedReader(new FileReader("quicksort.txt"));
			for (int i = 0; i < source.length; i++) {
				String sCurrentLine = zem.readLine();
				source[i] = Double.parseDouble(sCurrentLine);
				source2[i] = source[i];

			}

			// System.out.println(source[2]);

		}

		catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (zem != null)
					zem.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	public static void merge(double d1[], int i1, int i2, int last) {
		int indis1 = i1;
		int indis2 = i2;
		int indis3 = i1;
		while (indis1 < i2 && indis2 < last) {
			if (d1[indis1] < d1[indis2]) {
				aux[indis3] = d1[indis1];
				indis1++;
				indis3++;
			} else {
				aux[indis3] = d1[indis2];
				indis2++;
				indis3++;
			}
		}

		while (indis1 < i2) {
			aux[indis3++] = d1[indis1++];
		}

		while (indis2 < last) {
			aux[indis3++] = d1[indis2++];
		}

		for (int i = i1; i < last; i++) {
			d1[i] = aux[i];
		}
	}

	public static void isSorted() {
		for (int j = 0; j < source.length - 1; j++) {
			if (source[j] > source[j + 1]) {
				System.out.println("array is not sorted");
				System.out.println(j + ": " + source[j]);
				System.out.println((j + 1) + ": " + source[j + 1]);
				return;
			}
		}

		System.out.println("array is sorted. ");
	}
}
