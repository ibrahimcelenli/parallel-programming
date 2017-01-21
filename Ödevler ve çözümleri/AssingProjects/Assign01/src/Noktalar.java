
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
public class Noktalar {

	public long x;
	public long y;
	public long uzaklık;
	public static Random rn = new Random();

	public Noktalar() {

		this.x = rastgeleNoktaUret();
		this.y = rastgeleNoktaUret();
	}

	public long uzaklık_hesapla() {
		return (long) Math.sqrt(x * x + y * y);
	}

	public static long rastgeleNoktaUret() {
		return rn.nextLong();
	}
}
