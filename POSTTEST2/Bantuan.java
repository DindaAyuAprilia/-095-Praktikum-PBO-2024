import java.util.Date;
import java.util.ArrayList;

public class Bantuan {
    // Deklarasi variabel utama bantuan
    protected int idBantuan;
    protected int idDonatur;
    protected Date date;
    static int hitung = 1;

    // ArrayList untuk menyimpan data bantuan
    protected static ArrayList<Bantuan> daftarBantuan = new ArrayList<>();

    // Deklarasi objek yang akan digunakan
    static Tampilan look = new Tampilan();
    

    // Constructor default
    Bantuan(){

    }

    // Constructor dengan parameter
    public Bantuan(int idDonatur, Date date){
        this.idBantuan = hitung++;
        this.idDonatur = idDonatur;
        this.date = date;
    }

    // Method untuk menambah data bantuan barang
    public static void tambahBantuanBarang(int idDonatur, String jenisBarang, int kuantitas) {
        BantuanBarang bantuanBarang = new BantuanBarang(idDonatur, jenisBarang, kuantitas);
        daftarBantuan.add(bantuanBarang);
    }

    // Method untuk menambah data bantuan uang
    public static void tambahBantuanUang(int idDonatur, int jumlahUang) {
        BantuanUang bantuanUang = new BantuanUang(idDonatur, jumlahUang);
        daftarBantuan.add(bantuanUang);
    }

    // Method untuk melihat histori bantuan dari donatur tertentu
    public static void lihatHistoriBantuanDonatur(int idDonatur) {
        look.cls();
        boolean foundData = false;

        look.notif("Daftar Histori Donasi");
        for (Bantuan bantuan : daftarBantuan) {
            if (bantuan.idDonatur == idDonatur) {
                foundData = true; // Set foundData ke true jika ada data ditemukan
                if (bantuan instanceof BantuanBarang) {
                    BantuanBarang bantuanBarang = (BantuanBarang) bantuan;
                    System.out.println("ID Bantuan  : " + bantuanBarang.idBantuan);
                    System.out.println("ID Donatur  : " + bantuanBarang.idDonatur);
                    System.out.println("Jenis Barang: " + bantuanBarang.getJenisBarang());
                    System.out.println("Kuantitas   : " + bantuanBarang.getKuantitas());
                    System.out.println("Tanggal     : " + bantuanBarang.date);
                    look.garis();
                } else if (bantuan instanceof BantuanUang) {
                    BantuanUang bantuanUang = (BantuanUang) bantuan;
                    System.out.println("ID Bantuan  : " + bantuanUang.idBantuan);
                    System.out.println("ID Donatur  : " + bantuanUang.idDonatur);
                    System.out.println("Jumlah Uang : " + bantuanUang.getJumlahUang());
                    System.out.println("Tanggal     : " + bantuanUang.date);
                    look.garis();
                }
            }
        }

        // Cek apakah ada data atau tidak
        if (!foundData) {
            look.garis();
            System.out.println("Tidak ada data bantuan dari donatur dengan ID: " + idDonatur);
            look.garis();
        }
    }
}
