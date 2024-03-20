public class Tampilan {

    void menu(String kalimat, String[] pilihan) {
        int panjangKalimat = kalimat.length();
        int total = 80 - panjangKalimat;
        int spasi = (total - panjangKalimat) / 2;
        System.out.println("\n\n" + "=".repeat(total));
        System.out.println(" ".repeat(spasi) + kalimat + " ".repeat(spasi));
        System.out.println("=".repeat(total));
        int nomor = 1;
        for (String x : pilihan) {
            System.out.println(nomor + ". " + x);
            nomor++;
        }
        System.out.print("\nMasukan pilihan anda: ");
    }

    void notif(String kalimat){
        int panjangKalimat = kalimat.length();
        int total = 80 - panjangKalimat;
        int spasi = (total - panjangKalimat) / 2;
        System.out.println("=".repeat(total));
        System.out.println(" ".repeat(spasi) + kalimat + " ".repeat(spasi));
        System.out.println("=".repeat(total));
    }

    void garis(){
        System.out.println("=".repeat(50));
    }

    // Metode untuk membersihkan layar konsol
    void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Error saat membersihkan layar: " + e.getMessage());
        }
    }
}
