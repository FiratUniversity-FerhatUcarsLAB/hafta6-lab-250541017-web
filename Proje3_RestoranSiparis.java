/*
 * Ad Soyad: MERYEM MELEK ŞİMŞEK
 * Ogrenci No: 250541017
 * Tarih: 27/11/2025
 * Aciklama: Gorev 3 -Restoran Siparis Sistemi
 * 
 * Menu, miktar ve ozel durumları yoneten siparis sistemi
 */
import java.util.Scanner;

public class RestoranSiparis {

    // 1 - Menuyu goster
    public static void showMenu() {
        System.out.println("===== MENU =====");
        System.out.println("1 - Pizza (120 TL)");
        System.out.println("2 - Burger (90 TL)");
        System.out.println("3 - Makarna (75 TL)");
        System.out.println("4 - Salata (60 TL)");
        System.out.println("5 - Tatli (50 TL)");
        System.out.println("================");
    }

    // 2 - Temel yemek fiyatini al
    public static double getBasePrice(int kod) {
        switch (kod) {
            case 1: return 120;
            case 2: return 90;
            case 3: return 75;
            case 4: return 60;
            case 5: return 50;
            default: return 0;
        }
    }

    // 3 - Icecek ucreti
    public static double getDrinkPrice(int d) {
        switch (d) {
            case 1: return 20; // kola
            case 2: return 15; // ayran
            case 3: return 10; // su
            case 4: return 25; // ice tea
            default: return 0;
        }
    }

    // 4 - Tatli ucreti
    public static double getDessertPrice(int t) {
        switch (t) {
            case 1: return 50;
            case 2: return 40;
            case 3: return 35;
            default: return 0;
        }
    }

    // 5 - Happy hour indirimi (14-17)
    public static double happyHourDiscount(int saat, double drinkPrice) {
        if (saat >= 14 && saat <= 17) {
            return drinkPrice * 0.20;
        }
        return 0;
    }

    // 6 - Combo menü (%15 indirim)
    public static double comboDiscount(boolean yemekVar, boolean icecekVar, boolean tatliVar, double toplam) {
        if (yemekVar && icecekVar && tatliVar) {
            return toplam * 0.15;
        }
        return 0;
    }

    // 7 - 200 TL uzeri %10 indirim
    public static double bigOrderDiscount(double toplam) {
        if (toplam > 200) {
            return toplam * 0.10;
        }
        return 0;
    }

    // 8 - Ogrenci hafta ici indirimi
    public static double studentDiscount(boolean ogrenci, int gun, double toplam) {
        // gun 1-5 arasi hafta ici
        if (ogrenci && gun >= 1 && gun <= 5) {
            return toplam * 0.10;
        }
        return 0;
    }

    // 9 - Garson bahsis önerisi (%10)
    public static double tipSuggest(double toplam) {
        return toplam * 0.10; // fişe yazılır, toplam fiyata eklenmez
    }

    // 10 - Final hesaplama
    public static double calculateFinal(double yemek, double icecek, double tatli,
                                        double hh, double combo, double big, double stud) {
        return (yemek + icecek + tatli) - (hh + combo + big + stud);
    }

    // === MAIN ===================================
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        showMenu();

        System.out.print("Yemek kodu (1-5): ");
        int y = in.nextInt();

        System.out.print("Icecek (1=Kola, 2=Ayran, 3=Su, 4=IceTea, 0=Yok): ");
        int i = in.nextInt();

        System.out.print("Tatli (1=Kek, 2=Magnolia, 3=Profiterol, 0=Yok): ");
        int t = in.nextInt();

        System.out.print("Ogrenci misiniz? (1=Evet, 0=Hayir): ");
        boolean ogrenci = in.nextInt() == 1;

        System.out.print("Gun (1=Pzt ... 7=Pzr): ");
        int gun = in.nextInt();

        System.out.print("Saat (0-23): ");
        int saat = in.nextInt();

        // fiyatlar
        double yemek = getBasePrice(y);
        double icecek = getDrinkPrice(i);
        double tatli = getDessertPrice(t);

        // indirimler
        double indHappy = happyHourDiscount(saat, icecek);
        double indCombo = comboDiscount(y > 0, i > 0, t > 0, yemek + icecek + tatli);
        double ind200 = bigOrderDiscount(yemek + icecek + tatli);
        double indStudent = studentDiscount(ogrenci, gun, yemek + icecek + tatli);

        // toplam
        double total = calculateFinal(yemek, icecek, tatli, indHappy, indCombo, ind200, indStudent);
        double tip = tipSuggest(total);

        // CIKTILAR
        System.out.println("\n===== SIPARIS OZETI =====");
        System.out.printf("Yemek:  %.2f TL\n", yemek);
        System.out.printf("Icecek: %.2f TL\n", icecek);
        System.out.printf("Tatli:  %.2f TL\n", tatli);
        System.out.println("--------------------------");
        System.out.printf("Happy Hour Indirimi : -%.2f TL\n", indHappy);
        System.out.printf("Combo Indirimi      : -%.2f TL\n", indCombo);
        System.out.printf("200 TL Uzeri Ind    : -%.2f TL\n", ind200);
        System.out.printf("Ogrenci Indirimi    : -%.2f TL\n", indStudent);
        System.out.println("--------------------------");
        System.out.printf("TOPLAM TUTAR        : %.2f TL\n", total);
        System.out.printf("Bahsis Onerisi (%%10): %.2f TL\n", tip);
    }
}
