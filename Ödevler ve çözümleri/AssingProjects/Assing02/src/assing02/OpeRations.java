
package assing02;

import java.util.ArrayList;
import java.util.List;

public class OpeRations {
	static List<String> anormalWords = new ArrayList<String>();
	static int sayac = 0;

	public OpeRations(List<String> normalWords, List<String> invertedWords) {
		for (int i = 0; i < normalWords.size(); i++) {

			String str1 = (String) normalWords.get(i);
			for (int j = 0; j < invertedWords.size(); j++) {
				String str2 = (String) invertedWords.get(j);
				if (str2.equalsIgnoreCase(str1) && i != j) {
					sayac++;
					// System.out.println(""+str1+"--->indis--->"+i+"--->"+str2+"--->indis--->"+j);

					// anormalWords.add(""+str1+"--->indis--->"+i+"--->"+str2+"--->indis--->"+j);

				}
			}

		}
		System.out.println(sayac);
	}

}
