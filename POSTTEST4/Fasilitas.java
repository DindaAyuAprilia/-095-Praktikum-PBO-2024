///////////////////////////////////////////////////////////////////////////////////////
/*                         Library  Yang Di perlukan                                 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
///////////////////////////////////////////////////////////////////////////////////////


public class Fasilitas {

    // Deklarasi variabel utama fasilitas
    static int hitung = 0;
    int kodeFasilitas;
    String namaFasilitas;
    int kapasitasFasilitas;
    String lokasiFasilitas;
    String statusFasilitas;

    // Deklarasi objek yang akan digunakan
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static Tampilan look = new Tampilan();
    static ErrorHandling error = new ErrorHandling();
    static ArrayList<Fasilitas> daftarFasilitas = new ArrayList<>();

    // Constructor default
    public Fasilitas() {
    }

    // Constructor fasilitas dengan parameter
    public Fasilitas(String nama, int kapasitas, String lokasi, String status) {
        this.kodeFasilitas = ++hitung;
        this.namaFasilitas = nama;
        this.kapasitasFasilitas = kapasitas;
        this.lokasiFasilitas = lokasi;
        this.statusFasilitas = status;
    }


///////////////////////////////////////////////////////////////////////////////////////
/*                          Method Yang Diperlukan                                 */
///////////////////////////////////////////////////////////////////////////////////////
//  [1] Membuat data fasilitas
    public static Fasilitas dataFasilitas() throws IOException {
        look.cls();
        System.out.print("\n\nMasukkan Nama Fasilitas: ");
        String namaFasilitas = input.readLine();
        int kapasitasFasilitas = error.batasan(17, 100, "Masukkan Kapasitas Fasilitas: ");
        System.out.print("Masukkan Lokasi Fasilitas: ");
        String lokasiFasilitas = input.readLine();
        System.out.print("Masukkan Status Fasilitas: ");
        String statusFasilitas = input.readLine();
        return new Fasilitas(namaFasilitas, kapasitasFasilitas, lokasiFasilitas, statusFasilitas);
    }

///////////////////////////////////////////////////////////////////////////////////////
//  [2] Proses tambah data fasilitas
    public static void tambahFasilitas() throws IOException {
        daftarFasilitas.add(dataFasilitas());
        look.cls();
        look.notif("Data Fasilitas Berhasil ditambahkan!");
    }

///////////////////////////////////////////////////////////////////////////////////////
//  [3] Proses lihat data fasilitas
    public static void lihatFasilitas() {
        look.cls();

        if (daftarFasilitas.isEmpty()) {
            look.notif("Data Fasilitas kosong");
            return;
        }

        look.garis();
        System.out.println(" ".repeat(21) + "Data Fasilitas");
        for (Fasilitas fasilitas : daftarFasilitas) {
            look.garis();
            System.out.println("Kode Fasilitas   : " + fasilitas.kodeFasilitas);
            System.out.println("Nama             : " + fasilitas.namaFasilitas);
            System.out.println("Kapasitas        : " + fasilitas.kapasitasFasilitas);
            System.out.println("Lokasi           : " + fasilitas.lokasiFasilitas);
            System.out.println("Status           : " + fasilitas.statusFasilitas);
            System.out.println();
        }
    }

///////////////////////////////////////////////////////////////////////////////////////
//  [4] Proses ubah data fasilitas
    public static void ubahFasilitas() throws IOException {
        look.cls();

        if (daftarFasilitas.isEmpty()) {
            look.notif("Data Fasilitas kosong");
            return;
        }

        lihatFasilitas();
        look.garis();
        System.out.print("Pilih Kode Fasilitas yang ingin diubah: ");
        int indeks = Integer.parseInt(input.readLine());
        Fasilitas fasilitasDiubah = daftarFasilitas.get(indeks - 1);
        Fasilitas dataBaru = dataFasilitas();
        dataBaru.kodeFasilitas = fasilitasDiubah.kodeFasilitas;
        daftarFasilitas.set(indeks - 1, dataBaru);
        look.cls();
        look.notif("Data Fasilitas Berhasil diubah!");
    }


///////////////////////////////////////////////////////////////////////////////////////
//  [5] Proses hapus data fasilitas
    public static void hapusFasilitas() throws IOException {
        look.cls();

        if (daftarFasilitas.isEmpty()) {
            look.notif("Data Fasilitas kosong");
            return;
        }

        lihatFasilitas();
        look.garis();
        System.out.print("Pilih Kode Fasilitas yang ingin dihapus: ");
        int indeks = Integer.parseInt(input.readLine());

        for (int i = 0; i < daftarFasilitas.size(); i++) {
            Fasilitas fasilitas = daftarFasilitas.get(i);
            if (fasilitas.kodeFasilitas == indeks) {
                daftarFasilitas.remove(i);
                look.cls();
                look.notif("Data Fasilitas Berhasil dihapus!");
                return;
            }
        }

        look.cls();
        look.notif("Kode Fasilitas tidak ditemukan");
    }
}
