///////////////////////////////////////////////////////////////////////////////////////
/*                         Library Yang Di perlukan                                  */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
///////////////////////////////////////////////////////////////////////////////////////

public class Anak {

///////////////////////////////////////////////////////////////////////////////////////
/*                       Deklarasi Objek Yang di perlukan                            */
///////////////////////////////////////////////////////////////////////////////////////
    static int hitung = 1;
    int idAnak;
    String namaAnak;
    int umurAnak;
    String jenisKelamin;
    String statusAnak;

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static Tampilan look = new Tampilan();
    static ErrorHandling error = new ErrorHandling();

    // Constructor anak tanpa parameter
    Anak(){}

    // Constructor anak dengan parameter
    public Anak(String nama, int umur, String jk, String status){
        this.idAnak = hitung++;
        this.namaAnak = nama;
        this.umurAnak = umur;
        this.jenisKelamin = jk;
        this.statusAnak = status;
    }

    // ArrayList untuk menyimpan data anak
    static ArrayList<Anak> daftarAnak = new ArrayList<>();


///////////////////////////////////////////////////////////////////////////////////////
/*                            Method Yang Diperlukan                                 */
///////////////////////////////////////////////////////////////////////////////////////
//  [1] Membuat data anak
    public static Anak dataAnak() throws IOException {
        look.cls();

        // Mengisi data anak
        look.notif("Mengisi Data Anak");
        String namaAnak = error.harusHuruf("\n\nMasukkan Nama Anak: ");
        int usiaAnak = error.batasan(0, 17, "Masukkan Usia Anak: ");
        look.cls();

        // Cek jenis kelamin
        String jenisKelamin = "";
        while (true) {
            look.menu("Jenis Kelamin", new String[]{"Laki-laki", "Perempuan"});
            String pilihanJK = input.readLine();
            look.garis();

            if (pilihanJK.equals("1")) {
                jenisKelamin = "Laki-laki";
                break;
            } 
            
            else if (pilihanJK.equals("2")) {
                jenisKelamin = "Perempuan";
                break;
            } 
            
            else {
                look.cls();
                look.notif("Pilihan tidak sesuai, coba lagi");
            }
        }

        // Cek status anak
        look.cls();
        String statusAnak = "";
        while (true) {
            look.menu("Status Anak", new String[]{"Normal", "Berkebutuhan Khusus"});
            String pilihanStatus = input.readLine();
            look.garis();

            if (pilihanStatus.equals("1")) {
                statusAnak = "Normal";
                break;
            } 
            
            else if (pilihanStatus.equals("2")) {
                statusAnak = "Berkebutuhan Khusus";
                break;
            } 
            
            else {
                look.cls();
                look.notif("Pilihan tidak sesuai, coba lagi");
            }
        }
        
        // Membuat data anak
        return new Anak(namaAnak, usiaAnak, jenisKelamin, statusAnak);
    }

///////////////////////////////////////////////////////////////////////////////////////
//  [2] Proses untuk menambah data anak
    public static void tambahAnak() throws IOException {
        daftarAnak.add(dataAnak());
        look.cls();
        look.notif("Data Anak Berhasil di tambahkan!");
    }

///////////////////////////////////////////////////////////////////////////////////////
//  [3] Proses untuk melihat data anak
    public static void lihatAnak() {
        look.cls();

        // Cek jika kosong
        if (daftarAnak.isEmpty()) {
            look.notif("Data Anak kosong");
            return;
        }

        // Menampilkan data anak
        look.garis();
        look.notif("Data Anak");
        for (Anak anak : daftarAnak) {
            look.garis();
            System.out.println("ID anak         : " + anak.idAnak);
            System.out.println("Nama            : " + anak.namaAnak);
            System.out.println("Umur            : " + anak.umurAnak);
            System.out.println("Jenis Kelamin   : " + anak.jenisKelamin);
            System.out.println("Status          : " + anak.statusAnak);
            System.out.println();
        }
        look.garis();
    }

///////////////////////////////////////////////////////////////////////////////////////
//  [4] Proses untuk mengubah data anak
    public static void ubahAnak() throws IOException {
        look.cls();

        // Cek jika kosong
        if (daftarAnak.isEmpty()) {
            look.notif("Data Anak kosong");
            return;
        }

        // Menampilkan data anak yang ingin diubah
        lihatAnak();
        look.garis();
        System.out.print("Pilih ID anak yang ingin diubah: ");
        int indeks = Integer.parseInt(input.readLine());

        // Cek apakah ID anak yang dipilih valid
        if (indeks < 1 || indeks > daftarAnak.size()) {
            look.notif("ID Anak tidak valid.");
            return;
        }

        Anak anakDiubah = daftarAnak.get(indeks - 1);

        // Memilih atribut yang ingin diubah
        look.menu("Pilih Atribut Data yang Diubah", new String[]{"Nama", "Umur", "Jenis Kelamin", "Status"});
        String pilihanUbah = input.readLine();
        look.garis();

        switch (pilihanUbah) {
            case "1":
                anakDiubah.namaAnak = error.harusHuruf("Masukkan Nama Baru: ");
                look.cls();
                look.notif("Data Anak Berhasil diubah!");
                break;
            case "2":
                anakDiubah.umurAnak = error.batasan(0, 17, "Masukkan Umur Baru: ");
                look.cls();
                look.notif("Data Anak Berhasil diubah!");
                break;
            case "3":
                // Cek jenis kelamin
                while (true) {
                    look.menu("Jenis Kelamin", new String[]{"Laki-laki", "Perempuan"});
                    String pilihanJK = input.readLine();
                    
                    if (pilihanJK.equals("1")) {
                        anakDiubah.jenisKelamin = "Laki-laki";
                        break;
                    } 
                    
                    else if (pilihanJK.equals("2")) {
                        anakDiubah.jenisKelamin = "Perempuan";
                        break;
                    } 
                    
                    else {
                        look.notif("Pilihan tidak sesuai, coba lagi");
                    }
                }
                look.cls();
                look.notif("Data Anak Berhasil diubah!");
                break;
            case "4":
                // Cek status anak
                while (true) {
                    look.menu("Status Anak", new String[]{"Normal", "Berkebutuhan Khusus"});
                    String pilihanStatus = input.readLine();
                    if (pilihanStatus.equals("1")) {
                        anakDiubah.statusAnak = "Normal";
                        break;
                    } else if (pilihanStatus.equals("2")) {
                        anakDiubah.statusAnak = "Berkebutuhan Khusus";
                        break;
                    } else {
                        look.notif("Pilihan tidak sesuai, coba lagi");
                    }
                }
                look.cls();
                look.notif("Data Anak Berhasil diubah!");
                break;
            default:
                look.notif("Pilihan tidak valid.");
                break;
        }

        look.cls();
        look.notif("Data Anak Berhasil diubah!");
    }

///////////////////////////////////////////////////////////////////////////////////////
//  [5] Proses untuk menghapus data anak
    public static void hapusAnak() throws IOException {
        look.cls();

        // Cek jika kosong
        if (daftarAnak.isEmpty()) {
            look.notif("Data Anak kosong");
            return;
        }

        lihatAnak();
        look.garis();
        System.out.print("Pilih id anak yang ingin dihapus: ");
        int indeks = Integer.parseInt(input.readLine());

        // Cari anak berdasarkan id
        for (Anak anak : daftarAnak) {
            if (anak.idAnak == indeks) {
                daftarAnak.remove(anak);
                look.cls();
                look.notif("Data Anak Berhasil di hapus!");
                return;
            }
        }

        // Jika id tidak ditemukan
        look.cls();
        look.notif("ID tidak ditemukan");
    }
}
