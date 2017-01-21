
package assing02;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//0 ile 20000 arasi tarama islemi yapar.

public class Words {
	// static List<String> words = new ArrayList<String>();
	static BufferedReader br;
	static String readingValue;
	static Words words;
	static int wordLength;
	static List<String> normalWords = new ArrayList<String>();

	public Words() throws IOException {
		try {
			br = new BufferedReader(new FileReader("zemberek.txt"));
			for (int i = 0; i < 20000; i++) {
				readingValue = br.readLine();
				normalWords.add(readingValue);
			}
		} catch (FileNotFoundException ex) {
			Logger.getLogger(PalindromExample2.class.getName()).log(Level.SEVERE, null, ex);
		}
		// yazdir();

	}

	public static void yazdir() {
		for (int i = 0; i < 20000; i++) {
			System.out.println(normalWords.get(i));
		}
	}
}
