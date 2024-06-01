package com.kelompok2.models;       // Deklarasi paket untuk kelas ini, menunjukkan bahwa kelas ini adalah bagian dari paket 'com.kelompok2.models'


import com.kelompok2.Tampilan;      // Mengimpor kelas Tampilan dari paket com.kelompok2
import com.kelompok2.DB;            // Mengimpor kelas DB dari paket com.kelompok2
import com.kelompok2.ErrorHandling; // Mengimpor kelas DB dari paket com.kelompok2
import com.kelompok2.controller.ControllerBantuan;  // Mengimpor kelas ControllerBantuan dari paket com.kelompok2.controller

import java.sql.Connection;         // Mengimpor kelas Connection untuk membuat koneksi ke database
import java.sql.PreparedStatement;  // Mengimpor kelas PreparedStatement untuk menjalankan pernyataan SQL yang sudah diprekompilasi
import java.sql.ResultSet;          // Mengimpor kelas ResultSet untuk menampung hasil dari query SQL
import java.sql.SQLException;       // Mengimpor kelas SQLException untuk menangani kesalahan SQL
import java.util.Date;              // Mengimpor kelas Date untuk bekerja dengan tanggal
import java.util.HashMap;           // Mengimpor kelas HashMap untuk menyimpan data total barang
import java.util.Map;               // Mengimpor kelas Map untuk menggunakan antarmuka map


// --------------------------------------------------------------------------------------------------------------------- //
//                                    Prosedur dan Fungsi Di dalam Class
// --------------------------------------------------------------------------------------------------------------------- //
// [1] Deklarasi kelas abstract Bantuan
public abstract class Bantuan {

    // Deklarasi variabel instance dengan tingkat akses protected
    protected int idBantuan;
    protected int idDonatur;
    protected Date date;

    // Konstruktor untuk menginisialisasi variabel instance
    public Bantuan(int idDonatur, Date date) {
        this.idDonatur = idDonatur;
        this.date = date;
    }

    // Getter id Donatur
    public int getIdDonatur() {
        return idDonatur;
    }

    // Getter date
    public Date getDate() {
        return date;
    }

    // Overloading(proses menambah bantuan barang)
    public static void tambahBantuan(int idDonatur, String jenisBarang, int kuantitas) {
        BantuanBarang bantuanBarang = new BantuanBarang(idDonatur, jenisBarang, kuantitas);
        ControllerBantuan.saveToDatabase(bantuanBarang);
    }

    // Overloading(proses menambah bantuan uang)
    public static void tambahBantuan(int idDonatur, int jumlahUang) {
        BantuanUang bantuanUang = new BantuanUang(idDonatur, jumlahUang);
        ControllerBantuan.saveToDatabase(bantuanUang);
    }

    // Method static untuk melihat histori bantuan dari seorang donatur
    public static void lihatHistoriBantuanDonatur(int idDonatur) {
        Tampilan.cls();
        boolean foundData = false;

        // Membuat koneksi
        try (Connection con = DB.getConnection()) {

            // Membuat querry
            String query = "SELECT * FROM bantuan WHERE idDonatur = ?";
            // Membuat pernyataan dan mengeksekusinya
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setInt(1, idDonatur);
                try (ResultSet rs = pstmt.executeQuery()) {
                    Tampilan.notif("Daftar Histori Donasi");
                    while (rs.next()) {
                        foundData = true;
                        System.out.println("Tanggal     : " + rs.getDate("date"));
                        System.out.println("Jenis Barang: " + rs.getString("jenisBarang"));
                        System.out.println("Kuantitas   : " + rs.getInt("kuantitas"));
                        System.out.println("Jumlah Uang : " + rs.getInt("jumlahUang"));
                        Tampilan.garis();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Jika data tidak di temukan
        if (!foundData) {
            Tampilan.garis();
            System.out.println("Tidak ada data bantuan dari donatur dengan ID: " + idDonatur);
            Tampilan.garis();
        }
    }

    // Method static untuk melihat histori seluruh bantuan
    public static void lihatHistoriBantuan() {
        Tampilan.cls();
        boolean foundData = false;
        Map<String, Integer> totalBarang = new HashMap<>();
        int totalUang = 0;

        try (Connection con = DB.getConnection()) {
            // Menampilkan pilihan jenis histori yang ingin dilihat
            Tampilan.menu("Pilih Histori Bantuan", new String[]{"Barang", "Uang"});
            String pilihan = ErrorHandling.harusAngkaString("");

            String query = "SELECT * FROM bantuan";
            if (pilihan.equals("1")) {
                query += " WHERE jenisBarang NOT LIKE '%-%'";
            } else if (pilihan.equals("2")) {
                query += " WHERE jumlahUang <> 0";
            }

            try (PreparedStatement pstmt = con.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {
                Tampilan.notif("Daftar Histori Donasi");
                while (rs.next()) {
                    foundData = true;
                    System.out.println("ID Bantuan  : " + rs.getInt("idBantuan"));
                    System.out.println("ID Donatur  : " + rs.getInt("idDonatur"));
                    System.out.println("Tanggal     : " + rs.getDate("date"));

                    String jenisBarang = rs.getString("jenisBarang");
                    int kuantitas = rs.getInt("kuantitas");
                    int jumlahUang = rs.getInt("jumlahUang");

                    if (jenisBarang != null) {
                        System.out.println("Jenis Barang: " + jenisBarang);
                        System.out.println("Kuantitas   : " + kuantitas);
                        totalBarang.put(jenisBarang, totalBarang.getOrDefault(jenisBarang, 0) + kuantitas);
                    }
                    if (jumlahUang > 0) {
                        System.out.println("Jumlah Uang : " + jumlahUang);
                        totalUang += jumlahUang;
                    }
                    Tampilan.garis();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!foundData) {
            Tampilan.garis();
            System.out.println("Tidak ada data bantuan.");
            Tampilan.garis();
        } else {
            System.out.println("\n\n");
            Tampilan.notif("Deskripsi Total Bantuan");
            if (!totalBarang.isEmpty()) {
                System.out.println("Total Bantuan Barang:");
                for (Map.Entry<String, Integer> entry : totalBarang.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue() + " unit");
                }
            }
            if (totalUang > 0) {
                System.out.println("Total Bantuan Uang: Rp" + totalUang);
            }
            Tampilan.garis();
        }
    }
    // menyimpan ke database
    protected abstract void saveToDatabase();
}
