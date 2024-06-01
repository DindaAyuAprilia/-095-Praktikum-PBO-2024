package com.kelompok2.models;       // Deklarasi paket untuk kelas ini, menunjukkan bahwa kelas ini adalah bagian dari paket 'com.kelompok2.models'


import java.io.BufferedReader;      // Mengimpor BufferedReader untuk membaca input dari pengguna
import java.io.IOException;         // Mengimpor IOException untuk menangani kesalahan input/output
import java.io.InputStreamReader;   // Mengimpor InputStreamReader untuk membaca input dari sistem
import java.sql.Connection;         // Mengimpor Connection untuk membuat koneksi ke database
import java.sql.PreparedStatement;  // Mengimpor PreparedStatement untuk menjalankan pernyataan SQL yang sudah diprekompilasi
import java.sql.ResultSet;          // Mengimpor ResultSet untuk menampung hasil dari query SQL
import java.sql.SQLException;       // Mengimpor SQLException untuk menangani kesalahan SQL

import com.kelompok2.DB;            // Mengimpor kelas DB dari paket com.kelompok2, yang mungkin berisi metode untuk mengelola koneksi database
import com.kelompok2.ErrorHandling; // Mengimpor kelas ErrorHandling dari paket com.kelompok2, yang mungkin berisi metode untuk menangani kesalahan
import com.kelompok2.Tampilan;      // Mengimpor kelas Tampilan dari paket com.kelompok2, yang mungkin berisi metode untuk menampilkan informasi kepada pengguna
import com.kelompok2.controller.ControllerPantiAsuhan; // Mengimpor kelas ControllerPantiAsuhan dari paket com.kelompok2.controller, yang mungkin berisi metode untuk mengontrol operasi terkait panti asuhan


// --------------------------------------------------------------------------------------------------------------------- //
//                                    Prosedur dan Fungsi Di dalam Class
// --------------------------------------------------------------------------------------------------------------------- //
//  [1] Deklarasi kelas PantiAsuha
public class PantiAsuhan {

    // Deklarasi variabel pantiasuhan
    static int hitung = 0;
    private int kodePantiAsuhan;
    private String namaPantiAsuhan;
    private int kapasitasPantiAsuhan;
    private String lokasiPantiAsuhan;
    private String statusPantiAsuhan; 
    private String statusBangunan;    

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
 
    // Konstruktur kosong untuk deklarasi objek tanpa memberikan nilai awal ke atribut-atributnya.
    public PantiAsuhan() {}

    // Konstruktur dengan parameter untuk deklarasi objek dengan memberikan nilai awal ke atribut-atributnya.
    public PantiAsuhan(String nama, int kapasitas, String lokasi, String status, String statusBangunan) {
        this.kodePantiAsuhan = ++hitung;
        this.namaPantiAsuhan = nama;
        this.kapasitasPantiAsuhan = kapasitas;
        this.lokasiPantiAsuhan = lokasi;
        this.statusPantiAsuhan = status;
        this.statusBangunan = statusBangunan;
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [2] Method untuk mendapatkan data panti asuhan dari pengguna
    public static PantiAsuhan dataPantiAsuhan() throws IOException {
        Tampilan.cls();
        String namaPantiAsuhan = ErrorHandling.validasiPanjangKalimat("\n\nMasukkan Nama Panti Asuhan: ", 5, 50);
        int kapasitasPantiAsuhan = ErrorHandling.batasan(10, 150, "Masukkan Kapasitas Panti Asuhan: ");
        String lokasiPantiAsuhan = ErrorHandling.validasiPanjangKalimat("Masukkan Alamat Panti Asuhan: ", 5, 50);
        Tampilan.cls();
        String statusPantiAsuhan = "Tidak Penuh";
        String statusBangunan    = "Baik";

        return new PantiAsuhan(namaPantiAsuhan, kapasitasPantiAsuhan, lokasiPantiAsuhan, statusPantiAsuhan, statusBangunan);
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [3] Method untuk menambah panti asuhan
    public void tambah() throws IOException {
        PantiAsuhan panti = dataPantiAsuhan();
        if (panti != null) {
            ControllerPantiAsuhan.saveToDatabase(panti);
            Tampilan.notif("Data Panti Asuhan Berhasil ditambahkan!");
        }
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [4] Method untuk melihat data panti asuhan
    public void lihat() throws IOException {
        Tampilan.cls();

        // Untuk mendapatkan koneksi
        try (Connection con = DB.getConnection()) {
            
            // Membuat querry, menyiapkan pernyataan dan di eksekusi
            String sql = "SELECT * FROM pantiasuhan";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Jika data kosong
            if (!rs.next()) {
                Tampilan.notif("Data Panti Asuhan kosong");
                return;
            }

            // Menampilkan data
            Tampilan.notif("Data Panti Asuhan");
            do {
                Tampilan.garis();
                System.out.println("Kode Panti Asuhan : " + rs.getInt("kodePantiAsuhan"));
                System.out.println("Nama              : " + rs.getString("namaPantiAsuhan"));
                System.out.println("Kapasitas         : " + rs.getInt("kapasitasPantiAsuhan"));
                System.out.println("Lokasi            : " + rs.getString("lokasiPantiAsuhan"));
                System.out.println("Status            : " + rs.getString("statusPantiAsuhan"));
                System.out.println("Status Bangunan   : " + rs.getString("statusBangunan"));
                System.out.println();
            } while (rs.next());

            Tampilan.garis();

            // Pilihan untuk melihat data anak dalam panti yang dipilih
            System.out.print("Pilih Kode Panti Asuhan untuk melihat data anak di dalamnya: ");
            int kodePilihan = Integer.parseInt(input.readLine());

            // Menampilkan data anak dalam panti yang dipilih
            Tampilan.cls();
            Tampilan.notif("Data Anak dalam Panti Asuhan " + getNamaPanti(kodePilihan));
            Anak.lihatAnakDalamPanti(kodePilihan);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [5] Method untuk mengubah data panti asuhan
    public void ubah() throws IOException {
        Tampilan.cls();

        // Untuk mendapatkan koneksi
        try (Connection con = DB.getConnection()) {
            
            // Membuat querry, menyiapkan pernyataan dan di eksekusi
            String sql = "SELECT * FROM pantiasuhan";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Jika data kosong
            if (!rs.next()) {
                Tampilan.notif("Data Panti Asuhan kosong");
                return;
            }

            // Menampilkan data
            Tampilan.notif("Data Panti Asuhan");
            do {
                Tampilan.garis();
                System.out.println("Kode Panti Asuhan : " + rs.getInt("kodePantiAsuhan"));
                System.out.println("Nama              : " + rs.getString("namaPantiAsuhan"));
                System.out.println("Kapasitas         : " + rs.getInt("kapasitasPantiAsuhan"));
                System.out.println("Lokasi            : " + rs.getString("lokasiPantiAsuhan"));
                System.out.println("Status            : " + rs.getString("statusPantiAsuhan"));
                System.out.println("Status Bangunan   : " + rs.getString("statusBangunan"));
                System.out.println();
            } while (rs.next());

            // Memilih panti asuhan yang ingin di ubah
            Tampilan.garis();
            System.out.print("Pilih Kode Panti Asuhan yang ingin diubah: ");
            int kodePilihan = Integer.parseInt(input.readLine());


            // Membuat querry, menyiapkan pernyataan dan mengeksekusi pernyataan
            sql = "SELECT * FROM pantiasuhan WHERE kodePantiAsuhan = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, kodePilihan);
            rs = stmt.executeQuery();

            // Menampilkan data dan memilih atribut yang ingin diubah
            if (rs.next()) {
                Tampilan.cls();
                Tampilan.notif("Data Panti Asuhan");
                System.out.println("Kode Panti Asuhan : " + rs.getInt("kodePantiAsuhan"));
                System.out.println("Nama              : " + rs.getString("namaPantiAsuhan"));
                System.out.println("Status Bangunan   : " + rs.getString("statusBangunan"));
                System.out.println();
                Tampilan.garis();
                Tampilan.menu("Pilih atribut yang ingin diubah:", new String[]{"Nama", "Status Bangunan", "Batal"});
                String pilihanUbah = input.readLine();
                Tampilan.garis();

                // Membuat objek pati asuhan yang di ubah untuk di update nanti
                PantiAsuhan updatedPanti = new PantiAsuhan();
                updatedPanti.kodePantiAsuhan = rs.getInt("kodePantiAsuhan");
                updatedPanti.namaPantiAsuhan = rs.getString("namaPantiAsuhan");
                updatedPanti.kapasitasPantiAsuhan = rs.getInt("kapasitasPantiAsuhan");
                updatedPanti.lokasiPantiAsuhan = rs.getString("lokasiPantiAsuhan");
                updatedPanti.statusBangunan = rs.getString("statusBangunan");

                switch (pilihanUbah) {
                    case "1":
                        updatedPanti.namaPantiAsuhan = ErrorHandling.validasiPanjangKalimat("Masukkan Nama Panti Asuhan baru: ", 5, 50);
                        Tampilan.cls();
                        break;
                    case "2":
                        System.out.println("Pilih Status Bangunan baru:");
                        System.out.println("1. Perlu diperbaiki");
                        System.out.println("2. Baik");
                        updatedPanti.statusBangunan = input.readLine().equals("1") ? "Perlu diperbaiki" : "Baik";
                        Tampilan.cls();
                        break;
                    case "3":
                        Tampilan.cls();
                        Tampilan.notif("Pengubahan data dibatalkan.");
                        return;
                    default:
                        Tampilan.notif("Pilihan tidak valid.");
                        Tampilan.cls();
                }

                ControllerPantiAsuhan.updateDatabase(updatedPanti);
                Tampilan.cls();
                Tampilan.notif("Data Panti Asuhan Berhasil diubah!");
            } else {
                Tampilan.cls();
                Tampilan.notif("Kode Panti Asuhan tidak ditemukan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [6] Method untuk mendapatkan nama panti asuhan berdasarkan kode panti asuhan
    private String getNamaPanti(int kodePanti) {
        try (Connection con = DB.getConnection()) {
            String sql = "SELECT namaPantiAsuhan FROM pantiasuhan WHERE kodePantiAsuhan = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, kodePanti);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("namaPantiAsuhan");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [7] Method untuk memperbarui status panti asuhan
    public void perbaruiStatusPanti() {

        // Mendapatkan koneksi ke database
        try (Connection con = DB.getConnection()) {
            String sql = "SELECT COUNT(*) AS jumlahAnak FROM anak WHERE kodePantiAsuhan = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, this.kodePantiAsuhan);
            ResultSet rs = stmt.executeQuery();

            // Jika data ditemukan
            if (rs.next()) {
                // Mendapatkan jumlah anak dari ResultSet
                int jumlahAnak = rs.getInt("jumlahAnak");
                
                // Jika jumlah anak lebih besar atau sama dengan kapasitas
                if (jumlahAnak >= this.kapasitasPantiAsuhan) {

                    // Mengatur status panti asuhan menjadi 'Penuh'
                    this.statusPantiAsuhan = "Penuh";
                } else {

                    // Mengatur status panti asuhan menjadi 'Tidak Penuh'
                    this.statusPantiAsuhan = "Tidak Penuh";
                }

                // Update status panti asuhan di database
                sql = "UPDATE pantiasuhan SET statusPantiAsuhan = ? WHERE kodePantiAsuhan = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, this.statusPantiAsuhan);
                stmt.setInt(2, this.kodePantiAsuhan);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [8] Method untuk mendapatkan objek PantiAsuhan berdasarkan kodePantiAsuhan
    public static PantiAsuhan getPantiAsuhanByKode(int kodePantiAsuhan) {
        PantiAsuhan panti = null;
        
        try (Connection con = DB.getConnection()) {
            String sql = "SELECT * FROM pantiasuhan WHERE kodePantiAsuhan = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, kodePantiAsuhan);
            ResultSet rs = stmt.executeQuery();

            // Jika data ditemukan
            if (rs.next()) {
                // Membuat objek PantiAsuhan dengan data dari ResultSet
                panti = new PantiAsuhan(
                    rs.getString("namaPantiAsuhan"),
                    rs.getInt("kapasitasPantiAsuhan"),
                    rs.getString("lokasiPantiAsuhan"),
                    rs.getString("statusPantiAsuhan"),
                    rs.getString("statusBangunan")
                );

                // Mengatur kode panti asuhan
                panti.kodePantiAsuhan = rs.getInt("kodePantiAsuhan");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return panti;
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [9] Getter dan setter pantiasuhan
    public int getKodePantiAsuhan() {
    return kodePantiAsuhan;
    }

    public String getNamaPantiAsuhan() {
        return namaPantiAsuhan;
    }

    public int getKapasitasPantiAsuhan() {
        return kapasitasPantiAsuhan;
    }

    public String getLokasiPantiAsuhan() {
        return lokasiPantiAsuhan;
    }

    public String getStatusPantiAsuhan() {
        return statusPantiAsuhan;
    }

    public String getStatusBangunan() {
        return statusBangunan;
    }

}
