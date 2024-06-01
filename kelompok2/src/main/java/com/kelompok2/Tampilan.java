package com.kelompok2;

public class Tampilan {

    public static void menu(String kalimat, String[] pilihan) {
        int panjangKalimat = kalimat.length();
        int total = 80 - panjangKalimat;
        int spasi = (total) / 2;
        System.out.println("\n\n" + "=".repeat(80));
        System.out.println(" ".repeat(spasi) + kalimat + " ".repeat(spasi));
        System.out.println("=".repeat(80));
        int nomor = 1;
        for (String x : pilihan) {
            System.out.println(nomor + ". " + x);
            nomor++;
        }
        System.out.print("\nMasukan pilihan anda: ");
    }

    public static void notif(String kalimat){
        int panjangKalimat = kalimat.length();
        int spasi = (80 - panjangKalimat) / 2;
        System.out.println("=".repeat(80));
        System.out.println(" ".repeat(spasi) + kalimat + " ".repeat(spasi));
        System.out.println("=".repeat(80));
    }

    public static void garis(){
        System.out.println("=".repeat(80));
    }

    // Metode untuk membersihkan layar konsol
    public static void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Error saat membersihkan layar: " + e.getMessage());
        }
    }
}
