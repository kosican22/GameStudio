import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Spustac {

	public static void main(String[] args) {
		
        System.out.println("Zadajte svoju volbu : ");
		System.out.println("1) miny");
		System.out.println("2) kamene");
		System.out.println("3) guessNumber");
		Scanner sc = new Scanner(System.in);
		sc.useLocale(Locale.US);
		int volba = sc.nextInt();
		switch (volba) {
		case 1:
			System.out.println("Zadali ste 1");
			break;
		case 2:
			System.out.println("Zadali ste 2");
			break;
		case 3:
			Spustac s = new Spustac();
			s.guessNumber();
			break;
		}
	}
	
	public void guessNumber() {
		Random random = new Random();
		int cislo = random.nextInt(10);
		Scanner sc = new Scanner(System.in);
		sc.useLocale(Locale.US);
		while(true) {
			System.out.println("Hadajte cislo od 0..9");
			int hadaj = sc.nextInt();
			if (cislo == hadaj) {
				System.out.println("Uhadli ste");
				break;
			} else if (hadaj < cislo) {
				System.out.println("Pridaj trochu");
			} else {
				System.out.println("Uber Trochu");
			}
		}
	}
}
