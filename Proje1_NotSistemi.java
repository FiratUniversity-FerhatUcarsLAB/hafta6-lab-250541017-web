/*
 * Ad Soyad: MERYEM MELEK ŞİMŞEK
 * Ogrenci No: 250541017
 * Tarih: 14/11/2025
 * Aciklama: Gorev 1 -Not Sistemi
 * 
 * Bir ogrencinin notlarını degerlendiren ve detaylı rapor veren program.
 */
import java.util.Scanner;

public class Odev1 {

    public static void main(String[] args) {
        // Ödev 1
        Scanner in = new Scanner(System.in);
        int vizeNot, finalNot, odevNot;

        System.out.print("Vize notunu Girin:");
        vizeNot = in.nextInt();

        System.out.print("Final notunu Girin:");
        finalNot = in.nextInt();

        System.out.print("Ödev notunu Girin:");
        odevNot = in.nextInt();

        System.out.println("=== Ogrenci Not Raporu ===");
        System.out.println("Vize Notu : " + vizeNot);
        System.out.println("Final Notu : " + finalNot);
        System.out.println("Ödev Notu : " + odevNot);

        double ort = calculateAverage(vizeNot, finalNot, odevNot);

        System.out.println("---------------------------");
        System.out.printf("Ortalama : %.1f\n", ort);
        System.out.println("Harf Notu : " + getLetterGrade(ort));

        // Durum (if-else ile)
        if (isPassingGrade(ort)) {
            System.out.println("Durum : GEÇTİ");
        } else {
            System.out.println("Durum : KALDI");
        }

        // Onur listesi (if-else ile)
        if (isHonorList(ort, vizeNot, finalNot, odevNot)) {
            System.out.println("Onur Listesi : EVET");
        } else {
            System.out.println("Onur Listesi : HAYIR");
        }

        // Bütünleme (if-else ile)
        if (hasRetakeRight(ort)) {
            System.out.println("Bütünleme : VAR");
        } else {
            System.out.println("Bütünleme : YOK");
        }

        in.close();
    }

    // Ortalama Hesaplama
    public static double calculateAverage(int vizeNot, int finalNot, int odevNot) {
        return vizeNot * 0.3 + finalNot * 0.4 + odevNot * 0.3;
    }

    // Geçme Durumu
    public static boolean isPassingGrade(double ort) {
        if (ort >= 50) {
            return true;
        } else {
            return false;
        }
    }

    // Harf Notu (çakışmasız aralıklar)
    public static String getLetterGrade(double ort) {
        String harfNot;
        if (ort >= 90 && ort <= 100) {
            harfNot = "A";
        } else if (ort >= 80) {
            harfNot = "B";
        } else if (ort >= 70) {
            harfNot = "C";
        } else if (ort >= 60) {
            harfNot = "D";
        } else {
            harfNot = "F";
        }
        return harfNot;
    }

    // Onur Listesi
    public static boolean isHonorList(double ort, int vizeNot, int finalNot, int odevNot) {
        if (ort >= 85 && vizeNot >= 70 && finalNot >= 70 && odevNot >= 70) {
            return true;
        } else {
            return false;
        }
    }

    // Bütünleme Hakkı (if-else ile, ternary kaldırıldı)
    public static boolean hasRetakeRight(double ort) {
        if (ort >= 40 && ort < 50) {
            return true;
        } else {
            return false;
        }
    }
}
