
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class quicksortduz {

	private double array[];
	private int length;
	public static Random random;
	static double[] Array2;

	public void sort(double[] inputArr) {

		if (inputArr == null || inputArr.length == 0) {
			return;
		}
		this.array = inputArr;
		length = inputArr.length;
		quickSort(0, length - 1);
	}

	private void quickSort(int lowerIndex, int higherIndex) {

		int i = lowerIndex;
		int j = higherIndex;
		// pivot eleman orta eleman olarak seçiliyor.
		double pivot = array[lowerIndex + (higherIndex - lowerIndex) / 2];
		// Divide into two arrays
		while (i <= j) {
			/**
			 * In each iteration, we will identify a number from left side which
			 * is greater then the pivot value, and also we will identify a
			 * number from right side which is less then the pivot value. Once
			 * the search is done, then we exchange both numbers.
			 */
			while (array[i] < pivot) {
				i++;
			}
			while (array[j] > pivot) {
				j--;
			}
			if (i <= j) {
				exchangeNumbers(i, j);
				// move index to next position on both sides
				i++;
				j--;
			}
		}
		// call quickSort() method recursively
		if (lowerIndex < j)
			quickSort(lowerIndex, j);
		if (i < higherIndex)
			quickSort(i, higherIndex);
	}

	private void exchangeNumbers(int i, int j) {
		double temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public int Random(int i) {
		return random.nextInt(i);
	}

	public static void main(String[] args) {

		final int Array_sayisi = 10000000;

		quicksortduz sorter = new quicksortduz();
		Scanner scan = new Scanner(System.in);

		System.out.println("Quick Sort Başlıyor...");

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

		// System.out.println(" eleman " + 2 + " : " + Array2[2]);

		long startTime = System.currentTimeMillis();

		sorter.sort(Array2);

		System.out.println("Zaman : " + (System.currentTimeMillis() - startTime));

		/* Eleman Sayısı */
		// System.out.println("Kaç Eleman girilecek?");

		// n = scan.nextInt();

		/* n elemanlı dizi */

		// int input[] = new int[ n ];

		/* Elemanları al */
		// System.out.println("\n"+ n +" Tane Eleman giriniz...");
		// for (int j = 0; j < n; j++)
		// input[j] = scan.nextInt();
		// int[] input = {24,2,45,20,56,75,2,56,99,53,12};
		// sorter.sort(input);
		// for(int i:input){
		// System.out.print(i);
		// System.out.print(" ");
		// }

	}

}
