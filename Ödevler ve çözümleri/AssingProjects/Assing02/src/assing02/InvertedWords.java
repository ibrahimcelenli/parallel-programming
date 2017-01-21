
package assing02;

import java.util.ArrayList;
import java.util.List;

public class InvertedWords {
	static int wordLength;
	static String invertedWord;
	static List<String> invertedWords = new ArrayList<String>();

	public InvertedWords(List<String> normalWords) {
		for (int i = 0; i < 20000; i++) {
			wordLength = normalWords.get(i).length();
			for (int j = wordLength - 1; j >= 0; j--) {
				invertedWord = invertedWord + normalWords.get(i).charAt(j);
			}
			invertedWords.add(invertedWord);
			invertedWord = "";
		}
		// yazdir();
	}

	public static void yazdir() {
		for (int i = 0; i < 20000; i++) {
			System.err.println(invertedWords.get(i));
		}
	}
}
