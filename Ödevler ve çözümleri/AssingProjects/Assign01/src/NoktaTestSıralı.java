
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kadir
 */
public class NoktaTestSıralı {

    public static int sayac = 20000000;
    public static long z_uretim_basla, z_uretim_bitir, uretim_z;
    public static long z_uzaklık_basla, z_uzaklık_bitir, uzaklık_z;
    public static long z_max_basla, z_max_bitir, max_z;
    public static long ilk_zaman, son_zaman, toplam_z;
    public static int max_index;
    public static Noktalar[] noktalar;

    public static void main(String[] args) {

        noktalar = new Noktalar[sayac];

        //nokta oluşturma
        z_uretim_basla = System.currentTimeMillis();
        for (int i = 0; i < sayac; i++) {
            noktalar[i] = new Noktalar();
        }
        z_uretim_bitir = System.currentTimeMillis();

        // uzaklık hesapla
        z_uzaklık_basla = System.currentTimeMillis();
        for (int i = 0; i < sayac; i++) {
            noktalar[i].uzaklık = noktalar[i].uzaklık_hesapla();
        }
        z_uzaklık_bitir = System.currentTimeMillis();

        // Orjinden en uzak noktayı bul.
        z_max_basla = System.currentTimeMillis();
        max_index = findMax();
        z_max_bitir = System.currentTimeMillis();

        uretim_z = z_uretim_bitir - z_uretim_basla;
        uzaklık_z = z_uzaklık_bitir - z_uzaklık_basla;
        max_z = z_max_bitir - z_max_basla;
        toplam_z = z_max_bitir - z_uretim_basla;

        // nokta görüntülemek test amaçlı
        /*
         for (int i = 0; i < sayac; i++) {
         System.out.println("noktalar: " + noktalar[i].x + "," + noktalar[i].y + " uzaklık " + noktalar[i].uzaklık);
         }
         */
        System.out.println("SIRALI:");
        System.out.println("NOKTALARIN ÜRETİM ZAMANI: " + uretim_z + " ms");
        System.out.println("UZAKLIK HESAPLAMA ZAMANI: " + uzaklık_z + " ms");
        System.out.println("EN UZAK NOKTA BULMA ZAMANI: " + max_z + " ms");
        System.out.println("TOPLAM ZAMAN: " + toplam_z + " ms");
        System.out.println("EN UZAK NOKTA: (" + noktalar[max_index].x + "," + noktalar[max_index].y + ") distance is : " + noktalar[max_index].uzaklık);

    }

    public static int findMax() {
        int index = 0;
        double max = noktalar[0].uzaklık;
        for (int i = 0; i < sayac - 1; i++) {
            if (max < noktalar[i + 1].uzaklık) {
                max = noktalar[i + 1].uzaklık;
                index = i + 1;
            }
        }
        return index;
    }

}
