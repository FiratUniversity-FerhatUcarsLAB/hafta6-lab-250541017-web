/*
 * Ad Soyad: MERYEM MELEK ŞİMŞEK
 * Ogrenci No: 250541017
 * Tarih: 13/11/2025
 * Aciklama: Gorev 2 - Sinema Bileti Fiyatlandirma
 *
 * Sinema bileti fiyatını hesaplayan akıllı sistem
 * 
 */
import java.util.Scanner;

public class SinemaBileti {

    // 1 — Hafta sonu mu?
    public static boolean isWeekend(int gun) {
        return gun == 6 || gun == 7;
    }

    // 2 — Matine mi? (12:00 öncesi)
    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    // 3 — Temel fiyatı hesapla
    public static double calculateBasePrice(int gun, int saat) {

        boolean weekend = isWeekend(gun);
        boolean matinee = isMatinee(saat);

        if (!weekend && matinee) return 45;   // Haftaıcı matine
        if (!weekend && !matinee) return 65; // Haftaıcı normal
        if (weekend && matinee) return 55;   // Haftasonu matine

        return 85;                            // Hafta sonu normal
    }

    // 4 — İndirim hesaplama
    public static double calculateDiscount(int yas, int meslek, int gun, double basePrice) {
        double discount = 0;
        
        boolean weekend = isWeekend(gun);
        
        // == Yas Indirimleri ==
        if (yas >= 65) {
            discount = basePrice * 0.30;
        } else if (yas < 12) {
            discount = basePrice * 0.25;
        }
        // == Meslek Indirimlri ==
        switch (meslek) {
            case 1: // Ogrenci
                if (!weekend){ 
                    discount = basePrice * 0.20 ; // %20 (pzt-prs)
                } else {
                    discount = basePrice *0.15 ; // %15 (cuma-pzr)
                }
                break;
        }
        
        return discount ;
    }
    // 5-  Film Formatina Gore Ek Ucret
    public static double getFormatExtra(int tur) {
        switch (tur) {
            case 1 : return 0; // 2D
            case 2 : return 25; // 3D
            case 3 : return 35; // IMAX
            case 4 : return 50; // 4DX
            default: return 0;
        }
    }
    // 6- Final Fiyati Hesapla
    public static double calculateFinalPrice(double basePrice , double discount , double extra) {
        return (basePrice - discount) + extra;
    }
    // 7- Bilet Bilgi Metni
    public static void generateTicketInfo(double basePrice ,  double discount , double extra , double total) {
        System.out.println("\n===== SINEMA BILET BILGISI =====");
        System.out.printf("Temel Fiyat  : %.2f TL\n", basePrice);
        System.out.printf("Indirim      : %.2f TL\n", discount);
        System.out.printf("Format Ekstra: %.2f TL\n", extra);
        System.out.println("-------------------------------");
        System.out.printf("TOPLAM TUTAR : %.2f TL\n", total);
        
    }
    // MAIN ===============================
    public static void main(String[] args) {
        Scanner in  = new Scanner(System.in) ;
        
        System.out.print("Gun (1-7): ");
        int gun = in.nextInt();
        
        System.out.print("Saat (8-23): ");
        int saat = in.nextInt();
        
        System.out.print("Yas: ");
        int yas = in.nextInt();
        
        System.out.print("Meslek (1=Ogrenci, 2=Ogretmen, 3=Diger): ");
        int meslek = in.nextInt();

        System.out.print("Film Turu (1=2D, 2=3D, 3=IMAX, 4=4DX): ");
        int format = in.nextInt();
// Hesaplamalar
        double base = calculateBasePrice(gun, saat);
        double indirim = calculateDiscount(yas, meslek, gun, base);
        double extra = getFormatExtra(format);
        double total = calculateFinalPrice(base, indirim, extra);

        // Çıktı üret
        generateTicketInfo(base, indirim, extra, total);
    }
}
