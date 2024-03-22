///////////////////////////////////////////////////////////////////////////////////////
/*                         Library  Yang Di perlukan                                 */
///////////////////////////////////////////////////////////////////////////////////////
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
///////////////////////////////////////////////////////////////////////////////////////

public class Donatur {

///////////////////////////////////////////////////////////////////////////////////////
/*                       Deklarasi Objek Yang di perlukan                            */
///////////////////////////////////////////////////////////////////////////////////////
    static int hitung = 0;
    private int idDonatur;
    private String namaDonatur;
    private int umurDonatur;
    private String pekerjaanDonatur;
    private String alamatDonatur;
    private String nomorDonatur;
    private String password;

    // Deklarasi objek yang akan digunakan
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static Tampilan look = new Tampilan();
    static ErrorHandling error = new ErrorHandling();

    // List menu
    static String[] menuUbah = {"Nama", "Umur", "Pekerjaan", "Alamat", "Nomor Ponsel", "Password", "Exit"};

    // Constructor default    
    Donatur() {}

    // Constructor donatur dengan parameter
    public Donatur(String nama, int umur, String pekerjaan, String alamat, String nomor, String password) {
        this.idDonatur = ++hitung;
        this.namaDonatur = nama;
        this.umurDonatur = umur;
        this.pekerjaanDonatur = pekerjaan;
        this.alamatDonatur = alamat;
        this.nomorDonatur = nomor;
        this.password = password;
    }

    // Array list untuk menyimpan data donatur
    static ArrayList<Donatur> daftarDonatur = new ArrayList<>();


///////////////////////////////////////////////////////////////////////////////////////
/*                          Method Yang Diperlukan                                 */
///////////////////////////////////////////////////////////////////////////////////////
//  [1] Membuat data donatur
    public static Donatur dataDonatur() throws IOException {
        look.cls();
        look.notif("Mengisi Data Diri");
        String namaDonatur = error.harusHuruf("\n\nMasukkan Nama Anda         : ");
        int usiaDonatur = error.batasan(17, 100, "Masukkan Usia Anda         : ");
        System.out.print("Masukkan Pekerjaan Anda    : ");
        String pekerjaanDonatur = input.readLine();
        System.out.print("Masukkan Alamat Anda       : ");
        String alamatDonatur = input.readLine();
        System.out.print("Masukkan Nomor Ponsel Anda : ");
        String nomorDonatur = input.readLine();
        System.out.print("Masukkan Password Anda     : ");
        String passwordDonatur = input.readLine();
        return new Donatur(namaDonatur, usiaDonatur, pekerjaanDonatur, alamatDonatur, nomorDonatur, passwordDonatur);
    }

///////////////////////////////////////////////////////////////////////////////////////
//  [2] Proses tambah data donatur
    public static void tambahDonatur() throws IOException {
        daftarDonatur.add(dataDonatur());
        look.cls();
        look.notif("Data Anda Berhasil ditambahkan!");
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

        // Cek apakah indeks yang dipilih valid
        if (indeks < 1 || indeks > daftarDonatur.size()) {
            look.notif("ID Donatur tidak valid.");
            return;
        }
        while (true) {
            look.menu("Pilih Atribut Data yang Diubah", menuUbah);
            String pilihanUbah = input.readLine();
            Donatur donaturDiubah = daftarDonatur.get(indeks-1);
            look.garis();

            switch (pilihanUbah) {
                case "1":
                    donaturDiubah.namaDonatur =  error.harusHuruf("\n\nMasukkan Nama Baru         : ");
                    break;
                case "2":
                    donaturDiubah.umurDonatur = error.batasan(17, 100, "Masukkan Usia Baru         : ");
                    break;
                case "3":
                    System.out.print("Masukkan Pekerjaan Baru: ");
                    donaturDiubah.pekerjaanDonatur = input.readLine();
                    break;
                case "4":
                    System.out.print("Masukkan Alamat Baru: ");
                    donaturDiubah.alamatDonatur = input.readLine();
                    break;
                case "5":
                    System.out.print("Masukkan Nomor Ponsel Baru: ");
                    donaturDiubah.nomorDonatur = input.readLine();
                    break;
                case "6":
                    System.out.print("Masukkan Password Baru: ");
                    donaturDiubah.password = input.readLine();
                    break;
                case "7":
                    look.cls();
                    look.notif("Tidak ada data yang di ubah");
                    return;
                default:
                    look.notif("Pilihan tidak valid.");
                    return;
                }

            look.cls();
            look.notif("Data Donatur Berhasil diubah!");
        }
    }

// Proses ubah data donatur dengan ID yang sudah diberikan di parameter
public static void ubahDonatur(int idDonatur) throws IOException {
    look.cls();

    // Cek data donatur
    if (daftarDonatur.isEmpty()) {
        look.notif("Data Donatur kosong");
        return;
    }

    // Menampilkan data donatur yang sesuai dengan ID
    for (int i = 0; i < daftarDonatur.size(); i++) {
        if (daftarDonatur.get(i).idDonatur == idDonatur) {
            look.cls();
            look.notif("Data Anda");
            System.out.println("ID Donatur      : " + daftarDonatur.get(i).idDonatur);
            System.out.println("Nama            : " + daftarDonatur.get(i).namaDonatur);
            System.out.println("Umur            : " + daftarDonatur.get(i).umurDonatur);
            System.out.println("Pekerjaan       : " + daftarDonatur.get(i).pekerjaanDonatur);
            System.out.println("Alamat          : " + daftarDonatur.get(i).alamatDonatur);
            System.out.println("Nomor           : " + daftarDonatur.get(i).nomorDonatur);
            System.out.println("Password        : " + daftarDonatur.get(i).password);
            System.out.println();
            look.garis();
            look.menu("Pilih Atribut Data yang Diubah", menuUbah);
            String pilihanUbah = input.readLine();
            look.garis();

            switch (pilihanUbah) {
                case "1":
                    daftarDonatur.get(i).namaDonatur =  error.harusHuruf("\n\nMasukkan Nama Baru: ");
                    break;
                case "2":
                    System.out.print("Masukkan Umur Baru: ");
                    daftarDonatur.get(i).umurDonatur = error.batasan(17, 100, "Masukkan Usia Baru: ");
                    break;
                case "3":
                    System.out.print("Masukkan Pekerjaan Baru: ");
                    daftarDonatur.get(i).pekerjaanDonatur = input.readLine();
                    break;
                case "4":
                    System.out.print("Masukkan Alamat Baru: ");
                    daftarDonatur.get(i).alamatDonatur = input.readLine();
                    break;
                case "5":
                    System.out.print("Masukkan Nomor Ponsel Baru: ");
                    daftarDonatur.get(i).nomorDonatur = input.readLine();
                    break;
                case "6":
                    System.out.print("Masukkan Password Baru: ");
                    daftarDonatur.get(i).password = input.readLine();
                    break;
                case "7":
                    look.cls();
                    look.notif("Tidak ada data yang di ubah");
                    return;
                default:
                    look.cls();
                    look.notif("Pilihan tidak valid");
                    return;
            }

            look.cls();
            look.notif("Data Donatur Berhasil diubah!");
            return;
        }
    }

    // Jika ID donatur tidak ditemukan
    look.notif("ID Donatur tidak ditemukan");
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
//  [6] Setter dan Getter untuk password dan nama
    public String getNamaDonatur() {
        return namaDonatur;
    }

    public String getPassword() {
        return password;
    }

    public int getIdDonatur() {
        return idDonatur;
    }

///////////////////////////////////////////////////////////////////////////////////////
//  [7] Mendapatkan id donatur
    public static int getIdDonaturByUsernamePassword(String username, String password) {
        for (Donatur donatur : daftarDonatur) {
            if (donatur.getNamaDonatur().equals(username) && donatur.getPassword().equals(password)) {
                return donatur.getIdDonatur();
            }
        }
        return -1; 
    }
    

}





