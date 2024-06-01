package com.kelompok2.controller;

import com.kelompok2.DB;
import com.kelompok2.models.Bantuan;
import com.kelompok2.models.BantuanBarang;
import com.kelompok2.models.BantuanUang;

import java.sql.Connection;         // Mengimpor kelas Connection untuk membuat koneksi ke database
import java.sql.PreparedStatement;  // Mengimpor kelas PreparedStatement untuk menjalankan pernyataan SQL yang sudah diprekompilasi
import java.sql.SQLException;       // Mengimpor kelas SQLException untuk menangani kesalahan SQL

// --------------------------------------------------------------------------------------------------------------------- //
//                                    Prosedur dan Fungsi Di dalam Class
// --------------------------------------------------------------------------------------------------------------------- //
//  [1] Class controller Bantuan
public class ControllerBantuan {

// --------------------------------------------------------------------------------------------------------------------- //
//  [2] Metode save ke database
    public static void saveToDatabase(Bantuan bantuan) {
        String query;
        if (bantuan instanceof BantuanBarang) {
            query = "INSERT INTO bantuan (idDonatur, date, jenisBarang, kuantitas, jumlahUang, jenisBantuan) VALUES (?, ?, ?, ?, 0 , 'B')";
        } else if (bantuan instanceof BantuanUang) {
            query = "INSERT INTO bantuan (idDonatur, date, jenisBarang, kuantitas, jumlahUang, jenisBantuan) VALUES (?, ?, '-', 0 , ?, 'U')";
        } else {
            throw new IllegalArgumentException("Jenis bantuan tidak dikenal");
        }

        try (Connection con = DB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, bantuan.getIdDonatur());
            pstmt.setDate(2, new java.sql.Date(bantuan.getDate().getTime()));

            if (bantuan instanceof BantuanBarang) {
                BantuanBarang bantuanBarang = (BantuanBarang) bantuan;
                pstmt.setString(3, bantuanBarang.getJenisBarang());
                pstmt.setInt(4, bantuanBarang.getKuantitas());
            } else if (bantuan instanceof BantuanUang) {
                BantuanUang bantuanUang = (BantuanUang) bantuan;
                pstmt.setInt(3, bantuanUang.getJumlahUang());
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
