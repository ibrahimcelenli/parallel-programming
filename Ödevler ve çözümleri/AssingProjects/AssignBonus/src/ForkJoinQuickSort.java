
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

//RecurisiveAction metodundan türettik nedeni ise görevlerimizden bir sonuç beklediğimizden.
//Eğer herhangi bir sonuç beklemeseydik o zaman RecursiveAction metodundan türetebilirdik.
public class ForkJoinQuickSort extends RecursiveAction {

	// threshold değerimiz : problemimizin eşik değeri olarak görebiliriz.
	private static int THRESHOLD = 1000;
	static double[] Array2;

	private static double[] array;
	private final int left;
	private final int right;
	public static Random random;

	public ForkJoinQuickSort() {
		this(array, 0, array.length - 1);
	}

	private ForkJoinQuickSort(double[] array, int left, int right) {
		this.array = array;
		this.left = left;
		this.right = right;
		// System.out.println(" my start index: "+this.left+" my length:
		// "+this.right );
	}

	// compute metodu kuyruğa eklenmeden önce mevcut thread ile çalıştırır.
	@Override
	protected void compute() {
		// Bu değerden küçük olanlar otomatik olarak Array.sort ile
		// sıralanacaktır.

		if (right - left < THRESHOLD) {
			Arrays.sort(array, left, right + 1);
		} else {
			int pivotDegeri = partition(array, left, right);
			ForkJoinTask nesne = null;

			if (left < pivotDegeri)
				// fork metodumuz ile kuyruğa ekleme işlemi gerçekleştirilir.Her
				// metodun kendi kuyruğu vardır
				// ama gerekirse diğer threadler iş çalma yapabilirler.
				nesne = new ForkJoinQuickSort(array, left, pivotDegeri).fork();

			if (pivotDegeri + 1 < right)
				new ForkJoinQuickSort(array, pivotDegeri + 1, right).invoke();

			// nesnemiz null değil ise kuruktaki işin bitmesi için bekletilir.
			if (nesne != null)
				nesne.join();
		}

	}

	// partition metodumuz pivot elemanımıza göre karşılaştırma yapmaktadır.

	public static int partition(double[] array, int left, int right) {
		// ortalama değerimiz.
		double pivotDegeri = array[ortalamaDeger(left, right)];

		--left;
		++right;

		while (true) {
			do
				++left;
			while (array[left] < pivotDegeri);

			do
				--right;
			while (array[right] > pivotDegeri);

			if (left < right) {
				double tmp = array[left];
				array[left] = array[right];
				array[right] = tmp;
			} else {
				return right;
			}
		}
	}

	private static int ortalamaDeger(int left, int right) {
		return (right + left) / 2;
	}

	// Random değerlerimiz bu metot üzerinden gönderilmektedir.

	/*
	 * public int Random(int i) { return random.nextInt(i); }
	 * 
	 */

	public static void main(String[] args) throws IOException {

		final int Array_sayisi = 10000000;
		// random = new Random();

		// ilk çalıştırdığımızdaki random değerlerin aynısını her seferinde
		// üretmede kullanıyoruz
		// random.setSeed(123456789L);

		Array2 = new double[Array_sayisi];

		BufferedReader zem = null;

		try {
			zem = new BufferedReader(new FileReader("quicksort.txt"));
			for (int i = 0; i < Array2.length; i++) {
				String sCurrentLine = zem.readLine();
				Array2[i] = Double.parseDouble(sCurrentLine);

			}

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

		// int cores = 8;
		// int cores = Runtime.getRuntime().availableProcessors();
		// THRESHOLD = (int)Math.ceil(Array2.length/(double)cores);

		// System.out.println(cores);

		/*
		 * for (int i1 = 0; i1 <Array2.length; i1++) {
		 * System.out.println(" eleman " + i1 + " : " + Array2[i1] ); }
		 */

		// System.out.println(" eleman " + 2 + " : " + Array2[2]);

		ForkJoinPool pool = new ForkJoinPool();
		long startTime = System.currentTimeMillis();
		pool.invoke(new ForkJoinQuickSort(Array2, 0, Array2.length - 1));
		System.out.println("Zaman : " + (System.currentTimeMillis() - startTime));

		/*
		 * for (int i1 = 1; i1 < 10; i1++) { System.out.println(" eleman " + i1
		 * + " : " + Array2[i1] ); }
		 */

		// System.out.println(" eleman " + 12114 + " : " + Array2[12114]);

	}

}
