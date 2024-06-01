package com.kelompok2.models;

// Mengimpor kelas dari package yang berbeda
import com.kelompok2.Tampilan;      // Untuk menangani tampilan atau output ke pengguna
import com.kelompok2.ErrorHandling; // Untuk menangani kesalahan dan validasi input
import com.kelompok2.controller.ControllerBantuan; // Untuk mengontrol operasi terkait bantuan

import java.io.BufferedReader;      // Untuk membaca input dari pengguna
import java.io.IOException;         // Untuk menangani kemungkinan kesalahan I/O
import java.io.InputStreamReader;   // Untuk membaca input dari sistem
import java.util.Date;              // Untuk menangani tanggal dan waktu


// --------------------------------------------------------------------------------------------------------------------- //
//                                    Prosedur dan Fungsi Di dalam Class
// --------------------------------------------------------------------------------------------------------------------- //
// [1] Deklarasi kelas Bantuan Uang
public class BantuanUang extends Bantuan {

    // Variabel jumlah uang
    private int jumlahUang;

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    // Konstruktor untuk menginisialisasi objek BantuanUang
    public BantuanUang(int idDonatur, int jumlahUang) {
        super(idDonatur, new Date());
        this.jumlahUang = jumlahUang;
    }

    public int getJumlahUang() {
        return jumlahUang;
    }

// --------------------------------------------------------------------------------------------------------------------- //
// [2] Method untuk menambah bantuan uang
    public static void tambahBantuanUang(int idDonatur) throws IOException {
        Tampilan.cls();
        Tampilan.notif("Detail Bantuan Uang");
        int jumlah = ErrorHandling.batasan(2000, 100000000, "Jumlah Uang: ");
        Tampilan.garis();
        
        // Konfirmasi pembayaran
        boolean pembayaranBerhasil = konfirmasiPembayaran(idDonatur, jumlah);
        
        if (pembayaranBerhasil) {
            Bantuan.tambahBantuan(idDonatur, jumlah);
            Tampilan.notif("Data Bantuan Uang Berhasil ditambahkan!");
        } else {
            Tampilan.cls();
            Tampilan.notif("Proses pembayaran gagal. Silakan coba lagi.");
        }
    }

// --------------------------------------------------------------------------------------------------------------------- //
// [3] Method untuk konfirmasi pembayaran
    public static boolean konfirmasiPembayaran(int idDonatur, int jumlah) throws IOException {
        Tampilan.cls();
        Tampilan.notif("Konfirmasi Pembayaran");
        System.out.println("Anda akan mentransfer sejumlah: " + jumlah);

        // Memilih bank dari daftar
        String[] daftarBank = {"Bank A", "Bank B", "Bank C", "Bank D"};
        String namaBank;
        while (true) {
            Tampilan.menu("Pilih Bank", daftarBank);
            namaBank = input.readLine();
            if (namaBank.equals("1")){
                namaBank = "Bank A";
                break;
            }

            else if (namaBank.equals("2")){
                namaBank = "Bank B";
                break;
            }

            else if (namaBank.equals("3")){
                namaBank = "Bank C";
                break;
            }
            else if (namaBank.equals("4")){
                namaBank = "Bank D";
                break;
            }
            else{
                Tampilan.notif("Pilihan tidak valid.");
            }
        }

        System.out.println("Bank yang dipilih: " + namaBank);

        String noRekeningAnda = ErrorHandling.validasiPanjangKalimatInt("Masukkan nomor rekening anda: ", 10, 15);

        String otp = ErrorHandling.harusAngkaString("Masukkan kode OTP yang dikirim ke ponsel Anda(123456): ");
        Tampilan.garis();
    
        if (otp.equals("123456")) { 
            System.out.println("Pembayaran berhasil!");
            Tampilan.garis();
            tampilkanStruk(idDonatur, jumlah, namaBank, noRekeningAnda);
            return true;
        } else {
            System.out.println("OTP tidak valid. Pembayaran gagal.");
            Tampilan.garis();
            return false;
        }
    }

// --------------------------------------------------------------------------------------------------------------------- //
// [4] Menampilkan struk
    public static void tampilkanStruk(int idDonatur, int jumlah, String namaBank, String noRekening) {
        Tampilan.cls();
        Tampilan.notif("Struk Pembayaran");
        System.out.println("ID Donatur    : " + idDonatur);
        System.out.println("Jumlah Uang   : " + jumlah);
        System.out.println("Bank          : " + namaBank);
        System.out.println("No Rekening   : " + noRekening);
        System.out.println("Tanggal       : " + new Date());
        Tampilan.garis();
    }

// --------------------------------------------------------------------------------------------------------------------- //
// [5] Menyimpan ke database
    @Override
    protected void saveToDatabase() {
        ControllerBantuan.saveToDatabase(this);
    }
}
