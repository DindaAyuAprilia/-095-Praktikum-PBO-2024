///////////////////////////////////////////////////////////////////////////////////////
/*                         Library  Yang Di perlukan                                 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
///////////////////////////////////////////////////////////////////////////////////////


public class Fasilitas implements CRUD {

    // Deklarasi variabel utama fasilitas
    static int hitung = 0;
    private int kodeFasilitas;
    private String namaFasilitas;
    private String jenisFasilitas;
    private int kapasitasFasilitas;
    private String lokasiFasilitas;
    private String statusFasilitas;


    // Deklarasi objek yang akan digunakan
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static Tampilan look = new Tampilan();
    static ErrorHandling error = new ErrorHandling();
    static ArrayList<Fasilitas> daftarFasilitas = new ArrayList<>();

    // Constructor default
    public Fasilitas() {
    }

    // Constructor fasilitas dengan parameter
    public Fasilitas(String nama, String jenis, int kapasitas, String lokasi, String status) {
        this.kodeFasilitas = ++hitung;
        this.namaFasilitas = nama;
        this.jenisFasilitas = jenis;
        this.kapasitasFasilitas = kapasitas;
        this.lokasiFasilitas = lokasi;
        this.statusFasilitas = status;
    }


///////////////////////////////////////////////////////////////////////////////////////
/*                          Method Yang Diperlukan                                   */
///////////////////////////////////////////////////////////////////////////////////////
//  [1] Membuat data fasilitas
    public static Fasilitas dataFasilitas() throws IOException {
        look.cls();
        System.out.print("\n\nMasukkan Nama Fasilitas: ");
        String namaFasilitas = input.readLine();
        String jenisFasilitas = Menu.jenisFasilitas();
        int kapasitasFasilitas = error.batasan(0, 150, "Masukkan Kapasitas Fasilitas: ");
        System.out.print("Masukkan Alamat Fasilitas: ");
        String lokasiFasilitas = input.readLine();
        System.out.print("Masukkan Status Fasilitas: ");
        String statusFasilitas = input.readLine();
        return new Fasilitas(namaFasilitas, jenisFasilitas, kapasitasFasilitas, lokasiFasilitas, statusFasilitas);
    }

///////////////////////////////////////////////////////////////////////////////////////
//  [2] Proses tambah data fasilitas
    public void tambah() throws IOException {
        daftarFasilitas.add(dataFasilitas());
        look.cls();
        look.notif("Data Fasilitas Berhasil ditambahkan!");
    }

///////////////////////////////////////////////////////////////////////////////////////
//  [3] Proses lihat data fasilitas
    public void lihat() {
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
            System.out.println("Nama             : " + fasilitas.jenisFasilitas);
            System.out.println("Nama             : " + fasilitas.namaFasilitas);
            System.out.println("Kapasitas        : " + fasilitas.kapasitasFasilitas);
            System.out.println("Lokasi           : " + fasilitas.lokasiFasilitas);
            System.out.println("Status           : " + fasilitas.statusFasilitas);
            System.out.println();
        }
    }

///////////////////////////////////////////////////////////////////////////////////////
//  [4] Proses ubah data fasilitas
    public void ubah() throws IOException {
        look.cls();

        if (daftarFasilitas.isEmpty()) {
            look.notif("Data Fasilitas kosong");
            return;
        }

        lihat();
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
    public void hapus() throws IOException {
        look.cls();

        if (daftarFasilitas.isEmpty()) {
            look.notif("Data Fasilitas kosong");
            return;
        }

        lihat();
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

    public static boolean cekFasilitasTempatTinggal() {
        for (Fasilitas fasilitas : daftarFasilitas) {
            if (fasilitas.jenisFasilitas.equalsIgnoreCase("Tempat Tinggal")) {
                return true;
            }
        }
        return false;
    }

    
}

// Perbaiki

