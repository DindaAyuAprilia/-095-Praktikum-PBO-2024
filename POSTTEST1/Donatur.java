///////////////////////////////////////////////////////////////////////////////////////
/*                         Library  Yang Di perlukan                                 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
///////////////////////////////////////////////////////////////////////////////////////


public class Donatur {

    // Deklarasi variabel utama Donatur
    static int hitung = 0;
    int idDonatur;
    String namaDonatur;
    int umurDonatur;
    String pekerjaanDonatur;
    String alamatDonatur;
    String nomorDonatur;

    // Deklarasi objek yang akan digunakan
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static Tampilan look = new Tampilan();
    static Anak anak = new Anak();

    // Constructor default    
    Donatur() {

    }

    // Constructor donatur dengan parameter
    public Donatur(String nama, int umur, String pekerjaan, String alamat, String nomor) {
        this.idDonatur = ++hitung;
        this.namaDonatur = nama;
        this.umurDonatur = umur;
        this.pekerjaanDonatur = pekerjaan;
        this.alamatDonatur = alamat;
        this.nomorDonatur = nomor;
    }

    // Array list untuk menyimpan data donatur
    static ArrayList<Donatur> daftarDonatur = new ArrayList<>();


///////////////////////////////////////////////////////////////////////////////////////
/*                          Method Yang Diperlukan                                 */
///////////////////////////////////////////////////////////////////////////////////////
//  [1] Membuat data donatur
    public static Donatur dataDonatur() throws IOException {
        look.cls();
        System.out.print("\n\nMasukkan Nama Donatur: ");
        String namaDonatur = input.readLine();
        System.out.print("Masukkan Usia Donatur: ");
        int usiaDonatur = Integer.parseInt(input.readLine());
        System.out.print("Masukkan Pekerjaan Donatur: ");
        String pekerjaanDonatur = input.readLine();
        System.out.print("Masukkan Alamat Donatur: ");
        String alamatDonatur = input.readLine();
        System.out.print("Masukkan Nomor Donatur: ");
        String nomorDonatur = input.readLine();
        return new Donatur(namaDonatur, usiaDonatur, pekerjaanDonatur, alamatDonatur, nomorDonatur);
    }

///////////////////////////////////////////////////////////////////////////////////////
//  [2] Proses tambah data donatur
    public static void tambahDonatur() throws IOException {
        daftarDonatur.add(dataDonatur());
        look.cls();
        look.notif("Data Donatur Berhasil ditambahkan!");
    }

///////////////////////////////////////////////////////////////////////////////////////
//  [3] Proses lihat data donatur
    public static void lihatDonatur() {
        look.cls();

        // Cek data donatur
        if (daftarDonatur.isEmpty()) {
            look.notif("Data Donatur kosong");
            return;
        }

        look.garis();
        System.out.println(" ".repeat(21) + "Data Donatur");
        for (Donatur donatur : daftarDonatur) {
            look.garis();
            System.out.println("ID Donatur      : " + donatur.idDonatur);
            System.out.println("Nama            : " + donatur.namaDonatur);
            System.out.println("Umur            : " + donatur.umurDonatur);
            System.out.println("Pekerjaan       : " + donatur.pekerjaanDonatur);
            System.out.println("Alamat          : " + donatur.alamatDonatur);
            System.out.println("Nomor           : " + donatur.nomorDonatur);
            System.out.println();
        }
    }


///////////////////////////////////////////////////////////////////////////////////////
//  [4] Proses ubah data donatur
    public static void ubahDonatur() throws IOException {
        look.cls();

        // Cek data donatur
        if (daftarDonatur.isEmpty()) {
            look.notif("Data Donatur kosong");
            return;
        }

        lihatDonatur();
        look.garis();
        System.out.print("Pilih ID Donatur yang ingin diubah: ");
        int indeks = Integer.parseInt(input.readLine());
        Donatur donaturDiubah = daftarDonatur.get(indeks - 1);
        Donatur dataBaru = dataDonatur();
        dataBaru.idDonatur = donaturDiubah.idDonatur;
        daftarDonatur.set(indeks - 1, dataBaru);
        look.cls();
        look.notif("Data Donatur Berhasil diubah!");
    }

///////////////////////////////////////////////////////////////////////////////////////
//  [5] Proses hapus data donatur
    public static void hapusDonatur() throws IOException {
        look.cls();

        // Cek data donatur
        if (daftarDonatur.isEmpty()) {
            look.notif("Data Donatur kosong");
            return;
        }

        lihatDonatur();
        look.garis();
        System.out.print("Pilih ID Donatur yang ingin dihapus: ");
        int indeks = Integer.parseInt(input.readLine());

        for (Donatur donatur : daftarDonatur) {
            if (donatur.idDonatur == indeks) {
                daftarDonatur.remove(donatur);
                look.cls();
                look.notif("Data Donatur Berhasil dihapus!");
                return;
            }
        }

        look.cls();
        look.notif("ID tidak ditemukan");
    }

///////////////////////////////////////////////////////////////////////////////////////
//  [6] Menu data donatur
    public static void menuDonatur(String[] menuDonatur) throws IOException {
        while (true) {
            look.menu("Menu Donatur", menuDonatur);
            String pilihanDonatur = input.readLine();
            look.garis();

            switch (pilihanDonatur) {
                case "1":
                    tambahDonatur();
                    break;
                case "2":
                    lihatDonatur();
                    look.garis();
                    break;
                case "3":
                    ubahDonatur();
                    break;
                case "4":
                    hapusDonatur();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
                    break;
            }
        }
    }
}
