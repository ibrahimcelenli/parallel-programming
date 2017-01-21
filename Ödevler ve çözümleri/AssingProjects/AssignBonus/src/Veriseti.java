
import java.io.*;
import java.util.Random;

public class Veriseti {

	final static int Array_sayisi = 10000000;

	public static void main(String[] args) {

		Random random = new Random();
		double[] Array2 = new double[Array_sayisi];
		for (int i = 0; i < Array_sayisi; i++) {
			Array2[i] = random.nextInt(Array_sayisi);
		}

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("quicksort.txt"));
			for (int i = 0; i < Array_sayisi; i++) {
				out.write(Array2[i] + "\n");
			}
			out.close();
		} catch (IOException e) {
		}

		System.out.println("İşlem Tamamlandı.");

	}

}
