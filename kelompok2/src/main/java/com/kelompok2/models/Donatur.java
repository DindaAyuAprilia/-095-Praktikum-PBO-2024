package com.kelompok2.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

import com.kelompok2.controller.ControllerDonatur;
import com.kelompok2.DB;
import com.kelompok2.ErrorHandling;
import com.kelompok2.Tampilan;

// --------------------------------------------------------------------------------------------------------------------- //
//                                    Prosedur dan Fungsi Di dalam Class
// --------------------------------------------------------------------------------------------------------------------- //
//  [1] Deklarasi kelas Donatur
public class Donatur {

    // Variabel class donatur
    static int hitung = 0;
    private int idDonatur;
    private String namaDonatur;
    private int umurDonatur;
    private String pekerjaanDonatur;
    private String alamatDonatur;
    private String nomorDonatur;
    private String emailDonatur;
    private String password;

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static String[] menuUbah = {"Nama", "Umur", "Pekerjaan", "Alamat", "Nomor Ponsel", "Email", "Password", "Exit"};

    // Constructor untuk inisialisasi objek Donatur dengan parameter
    public Donatur(String nama, int umur, String pekerjaan, String alamat, String nomor, String email, String password) {
        this.namaDonatur = nama;
        this.umurDonatur = umur;
        this.pekerjaanDonatur = pekerjaan;
        this.alamatDonatur = alamat;
        this.nomorDonatur = nomor;
        this.emailDonatur = email;
        this.password = password;
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [2] Method untuk membuat data donatur
    public static Donatur dataDonatur() throws IOException {
        Tampilan.cls();
        Tampilan.notif("Mengisi Data Diri");
        String namaDonatur = ErrorHandling.validasiPanjangKalimat("\n\nMasukkan Nama Anda         : ", 3, 100);
        int usiaDonatur = ErrorHandling.batasan(17, 100, "Masukkan Usia Anda         : ");
        Tampilan.cls();
        String pekerjaanDonatur = pilihPekerjaan();
        Tampilan.cls();
        String alamatDonatur = ErrorHandling.validasiPanjangKalimat("Masukkan alamat Anda       : ", 10, 150);
        String nomorDonatur = ErrorHandling.validasiNomorPonsel();
        String emailDonatur = ErrorHandling.validasiEmail("Masukkan Email Anda        : ");
        String passwordDonatur = ErrorHandling.validasiPassword("Masukkan Password Anda     : ");
        Tampilan.cls();
        return new Donatur(namaDonatur, usiaDonatur, pekerjaanDonatur, alamatDonatur, nomorDonatur, emailDonatur, passwordDonatur);
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [3] Method untuk menambah data donatur
    public static void tambahDonatur() throws IOException {
        Donatur donatur = dataDonatur();
        ControllerDonatur.saveToDatabase(donatur);
        
        Tampilan.notif("Data Anda Berhasil ditambahkan!");
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [4] Method untuk melihat data donatur
    public static void lihatDonatur() {
        Tampilan.cls();

        // Mendapatkan koneksi
        try (Connection con = DB.getConnection()) {

            // Membuat querry
            String sql = "SELECT * FROM donatur where role = 'U'";

            // Membuat pernyataan
            Statement stmt = con.createStatement();

            // Mengeksekusi pernyataan
            ResultSet rs = stmt.executeQuery(sql);
            if (rs == null || !rs.next()) {
                Tampilan.notif("Data Donatur kosong");
                return;
            }

            // Menampilkan data
            Tampilan.garis();
            System.out.println(" ".repeat(21) + "Data Donatur");
            do {
                Tampilan.garis();
                System.out.println("ID Donatur      : " + rs.getInt("idDonatur"));
                System.out.println("Nama            : " + rs.getString("namaDonatur"));
                System.out.println("Umur            : " + rs.getInt("umurDonatur"));
                System.out.println("Pekerjaan       : " + rs.getString("pekerjaanDonatur"));
                System.out.println("Alamat          : " + rs.getString("alamatDonatur"));
                System.out.println("Nomor           : " + rs.getString("nomorDonatur"));
                System.out.println("Email           : " + rs.getString("emailDonatur"));
                System.out.println();
            } while (rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [5] Method untuk mengubah data donatur
    public static void ubahDonatur(int idDonatur) throws IOException {
        Tampilan.cls();

        // Mendapatkan koneksi
        try (Connection con = DB.getConnection()) {
            
            // Membuat querry
            String sql = "SELECT * FROM donatur WHERE idDonatur = ?";
            
            // Menyiapkan pernyataan dan mengeksekusi program
            PreparedStatement stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setInt(1, idDonatur); // Set the idDonatur parameter
            ResultSet rs = stmt.executeQuery();
            
            // Jika data donatur ditemuka
            if (rs != null && rs.next()) {
                Tampilan.cls();
                Tampilan.notif("Data Anda");
                System.out.println("Nama            : " + rs.getString("namaDonatur"));
                System.out.println("Umur            : " + rs.getInt("umurDonatur"));
                System.out.println("Pekerjaan       : " + rs.getString("pekerjaanDonatur"));
                System.out.println("Alamat          : " + rs.getString("alamatDonatur"));
                System.out.println("Nomor           : " + rs.getString("nomorDonatur"));
                System.out.println("Email           : " + rs.getString("emailDonatur"));
                System.out.println();
                Tampilan.garis();
                Tampilan.menu("Pilih Atribut Data yang Diubah", menuUbah);
                
                // Menampilkan menu pilihan atribut yang akan diubah
                String pilihanUbah = input.readLine();
                Tampilan.garis();
    
                switch (pilihanUbah) {
                    case "1":
                        rs.updateString("namaDonatur", ErrorHandling.harusHuruf("\n\nMasukkan Nama Baru: ", 3, 100));
                        break;
                    case "2":
                        rs.updateInt("umurDonatur", ErrorHandling.batasan(17, 100, "Masukkan Usia Baru: "));
                        break;
                    case "3":
                        rs.updateString("pekerjaanDonatur", pilihPekerjaan());
                        break;
                    case "4":
                        rs.updateString("alamatDonatur", ErrorHandling.validasiPanjangKalimat("Masukkan alamat baru: ", 10, 150));
                        break;
                    case "5":
                        rs.updateString("nomorDonatur", ErrorHandling.validasiNomorPonsel());
                        break;
                    case "6":
                        rs.updateString("emailDonatur", ErrorHandling.validasiEmail("Masukkan Email Baru: "));

                        break;
                    case "7":
                        String passOld = ErrorHandling.validasiPassword("Masukkan Password Sebelumnya: ");
                        if (passOld.equals(rs.getString("password"))){
                            rs.updateString("password", ErrorHandling.validasiPassword("Masukkan Password Baru: "));
                            break;
                        }
                        else{
                            Tampilan.cls();
                            Tampilan.notif("Password tidak sama dengan yang sebelumnya");
                            return;
                        }
                        
                    case "8":
                        Tampilan.cls();
                        Tampilan.notif("Tidak ada data yang di ubah");
                        return;
                    default:
                        Tampilan.cls();
                        Tampilan.notif("Pilihan tidak valid");
                        return;
                }
    
                // Menyimpan perubahan ke database
                rs.updateRow();
                Tampilan.cls();
                Tampilan.notif("Data Donatur Berhasil diubah!");
            } else {
                Tampilan.notif("ID Donatur tidak ditemukan");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [6] Method untuk memilih pekerjaan dari daftar pilihan
    public static String pilihPekerjaan() throws IOException {
        String[] pekerjaanUmum = {
            "Mahasiswa", "Karyawan", "Wiraswasta", "PNS", "Dokter",
            "Perawat", "Guru", "Dosen", "Insinyur", "Pengacara", "Akuntan",
            "Seniman", "Jurnalis", "Atlet", "Sopir", "Petani", "Nelayan",
            "Pengusaha", "Teknisi", "Arsitek", "Lainnya"
        };

        Tampilan.menu("Pilih Pekerjaan Anda", pekerjaanUmum);
        String pilihan = input.readLine();
        int pilihanInt;

        try {
            pilihanInt = Integer.parseInt(pilihan);
        } catch (NumberFormatException e) {
            Tampilan.notif("Pilihan tidak valid. Silakan coba lagi.");
            return pilihPekerjaan();
        }

        if (pilihanInt > 0 && pilihanInt <= pekerjaanUmum.length) {
            if (pilihanInt == pekerjaanUmum.length) { // If 'Lainnya' is chosen
                System.out.print("Masukkan Pekerjaan Anda    : ");
                return ErrorHandling.validasiPanjangKalimat("Masukkan Pekerjaan Anda    : ", 2, 50);
            } else {
                return pekerjaanUmum[pilihanInt - 1];
            }
        } else {
            Tampilan.notif("Pilihan tidak valid. Silakan coba lagi.");
            return pilihPekerjaan();
        }
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [7] Getter donatur

    public String getNamaDonatur() {
        return namaDonatur;
    }

    public int getUmurDonatur() {
        return umurDonatur;
    }

    public String getPekerjaanDonatur() {
        return pekerjaanDonatur;
    }

    public String getAlamatDonatur() {
        return alamatDonatur;
    }

    public String getNomorDonatur() {
        return nomorDonatur;
    }

    public String getEmailDonatur() {
        return emailDonatur;
    }

    public String getPassword() {
        return password;
    }

    public int getIdDonatur() {
        return idDonatur;
    }

    public static int getIdDonaturByEmailPassword(String email, String password) {
        return ControllerDonatur.getIdDonaturByEmailPassword(email, password);
    }
}
