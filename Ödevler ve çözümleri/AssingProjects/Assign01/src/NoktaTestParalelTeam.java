
import edu.rit.pj.ParallelRegion;
import edu.rit.pj.ParallelTeam;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kadir
 */
public class NoktaTestParalelTeam {

	int id;
	public static long en_uzak_nokta = 0;
	public static int sayac = 20000000;
	public static long z_uzaklık_basla = 0, z_uzaklık_bitir, z_uzaklık;
	public static long z_max_basla = 0, z_max_bitir = 0, z_max;
	public static long z_basla, z_toplam_bitir, z_toplam;
	public static Noktalar[] noktalar;
	public static boolean henüzBitmedi;
	public static int thread_sayisi = 4;

	public static void main(String[] args) {

		noktalar = new Noktalar[sayac];
		for (int i = 0; i < sayac; i++) {
			noktalar[i] = new Noktalar();
		}
		z_basla = System.currentTimeMillis();

		ParallelTeam team = new ParallelTeam(thread_sayisi);

		try {
			team.execute(new ParallelRegion() {

				public void run() {

					int id = getThreadIndex();
					int basla = id * sayac / thread_sayisi;
					int bitir = (id + 1) * sayac / thread_sayisi;

					if (z_uzaklık_basla == 0) {
						z_uzaklık_basla = System.currentTimeMillis();
					}
					// calculating uzaklık
					for (int i = basla; i < bitir; i++) {
						noktalar[i].uzaklık = noktalar[i].uzaklık_hesapla();
					}
					z_uzaklık_bitir = System.currentTimeMillis();

					if (z_max_basla == 0) {
						z_max_basla = System.currentTimeMillis();
					}
					findMax(basla, bitir);
					if (z_max_bitir == 0) {
						z_max_bitir = System.currentTimeMillis();
					}
				}
			});
		} catch (Exception ex) {
			Logger.getLogger(NoktaTestParalelTeam.class.getName()).log(Level.SEVERE, null, ex);
		}

		z_toplam_bitir = System.currentTimeMillis();

		z_uzaklık = z_uzaklık_bitir - z_uzaklık_basla;
		z_max = z_max_bitir - z_max_basla;
		z_toplam = z_toplam_bitir - z_basla;

		System.out.println("PARALEL TEAM:");
		System.out.println("UZAKLIK HESAPLAMA ZAMANI:" + z_uzaklık + " ms");
		System.out.println("EN UZAK NOKTA BULMA ZAMANI: ~" + z_max + " ms");
		System.out.println("TOPLAM ZAMAN: " + z_toplam + " ms");
		System.out.println("EN UZAK NOKTA: : " + en_uzak_nokta);
	}

	public NoktaTestParalelTeam(int id) {
		this.id = id;
	}

	public static void findMax(int s, int f) {
		long max = noktalar[s].uzaklık;
		for (int i = s; i < f - 1; i++) {
			if (max < noktalar[i + 1].uzaklık) {
				max = noktalar[i + 1].uzaklık;
			}
		}
		if (en_uzak_nokta < max) {
			en_uzak_nokta = max;
		}

	}
}
