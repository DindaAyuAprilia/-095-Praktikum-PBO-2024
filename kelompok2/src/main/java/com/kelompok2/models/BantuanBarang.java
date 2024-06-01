package com.kelompok2.models;

// Mengimpor kelas dari package yang berbeda
import com.kelompok2.Tampilan;                     // Untuk menangani tampilan atau output ke pengguna
import com.kelompok2.ErrorHandling;                // Untuk menangani kesalahan dan validasi input
import com.kelompok2.controller.ControllerBantuan; // Untuk mengontrol operasi terkait bantuan

import java.io.BufferedReader;      // Untuk membaca input dari pengguna
import java.io.IOException;         // Untuk menangani kemungkinan kesalahan I/O
import java.io.InputStreamReader;   // Untuk membaca input dari sistem
import java.util.Date;              // Untuk menangani tanggal dan waktu


// --------------------------------------------------------------------------------------------------------------------- //
//                                    Prosedur dan Fungsi Di dalam Class
// --------------------------------------------------------------------------------------------------------------------- //
// [1] Deklarasi kelas Bantuan Barang
public class BantuanBarang extends Bantuan {

    // Variabel
    private String jenisBarang;
    private int kuantitas;

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    // Konstruktor untuk menginisialisasi objek BantuanBarang
    public BantuanBarang(int idDonatur, String jenisBarang, int kuantitas) {
        super(idDonatur, new Date());
        this.jenisBarang = jenisBarang;
        this.kuantitas = kuantitas;
    }

    public String getJenisBarang() {
        return jenisBarang;
    }

    public int getKuantitas() {
        return kuantitas;
    }

// --------------------------------------------------------------------------------------------------------------------- //
// [2] Method untuk menambah bantuan barang
    public static void tambahBantuanBarang(int idDonatur) throws IOException {
        Tampilan.cls();
        String jenisBarang = pilihJenisBarang();
        int kuantitas = ErrorHandling.batasan(1, 1000, "Kuantitas    : ");
        Tampilan.garis();
        Bantuan.tambahBantuan(idDonatur, jenisBarang, kuantitas);

        Tampilan.notif("Data Bantuan Barang Berhasil ditambahkan!");
    }

// --------------------------------------------------------------------------------------------------------------------- //
// [3] Method untuk memilih jenis barang
    public static String pilihJenisBarang() throws IOException {

        // Daftar jenis barang umum
        String[] jenisBarangUmum = {
            "Pakaian", "Makanan", "Mainan", "Buku", "Obat-obatan", 
            "Perlengkapan Sekolah", "Elektronik", "Alat Tulis", "Lainnya"
        };
    
        Tampilan.menu("Pilih Jenis Barang", jenisBarangUmum);
        String pilihan = input.readLine();
        int pilihanInt;
    
        try {
            pilihanInt = Integer.parseInt(pilihan);
        } catch (NumberFormatException e) {
            Tampilan.notif("Pilihan tidak valid. Silakan coba lagi.");
            return pilihJenisBarang();
        }
    
        // Validasi jika memilih "Lainnya"
        if (pilihanInt > 0 && pilihanInt <= jenisBarangUmum.length) {
            if (pilihanInt == jenisBarangUmum.length) {
                return ErrorHandling.validasiPanjangKalimat("Masukkan Jenis Barang: ", 5,50);
            } else {
                return jenisBarangUmum[pilihanInt - 1];
            }
        } else {
            Tampilan.notif("Pilihan tidak valid. Silakan coba lagi.");
            return pilihJenisBarang();
        }
    }

// --------------------------------------------------------------------------------------------------------------------- //
// [4] Menyimpan objek ke database menggunakan ControllerBantuan
    @Override
    protected void saveToDatabase() {
        ControllerBantuan.saveToDatabase(this);
    }
}
