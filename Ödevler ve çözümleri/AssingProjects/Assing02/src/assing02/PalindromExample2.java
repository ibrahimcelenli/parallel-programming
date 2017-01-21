
package assing02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PalindromExample2 extends Thread {
	/*
	 * static BufferedReader br; static String readingValue; static Words words;
	 * static List<String> normal_array = new ArrayList<String>();
	 */
	static Words words;
	static InvertedWords invWords;
	static OpeRations opr;
	public static long z_uzaklık_basla = 0, z_uzaklık_bitir, z_uzaklık;
	public static long sonuc;

	static int id;
	// static int thread_sayisi = 2;

	public PalindromExample2(int id) {
		this.id = id;

	}

	public static void main(String[] args) throws IOException {

		PalindromExample2 t1 = new PalindromExample2(1);

		t1.start();

		boolean henüzBitmedi = true;

		while (henüzBitmedi) {
			henüzBitmedi = false;

			if (t1.isAlive()) {
				henüzBitmedi = true;
			} else {

				henüzBitmedi = false;

				z_uzaklık_basla = System.currentTimeMillis();

				invWords = new InvertedWords(words.normalWords);
				opr = new OpeRations(words.normalWords, invWords.invertedWords);

				z_uzaklık_bitir = System.currentTimeMillis();

				sonuc = z_uzaklık_bitir - z_uzaklık_basla;

				System.out.println("palindrom : " + sonuc);

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		/*
		 * 
		 * words = new Words(); invWords = new InvertedWords(words.normalWords);
		 * opr = new OpeRations(words.normalWords, invWords.invertedWords);
		 * 
		 */
	}

	public void run() {

		z_uzaklık_basla = System.currentTimeMillis();

		switch (id) {
		case 1:
			try {
				words = new Words();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // System.out.println("thread 1");

			break;

		}

		z_uzaklık_bitir = System.currentTimeMillis();

		sonuc = z_uzaklık_bitir - z_uzaklık_basla;

		System.out.println("okunan süre :" + sonuc);

	}

	// System.out.println(OpeRations.anormalWords.toString());

}
