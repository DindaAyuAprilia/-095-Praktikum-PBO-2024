package com.kelompok2.controller;

import java.sql.ResultSet;
import java.sql.Connection;         // Mengimpor kelas Connection untuk membuat koneksi ke database
import java.sql.PreparedStatement;  // Mengimpor kelas PreparedStatement untuk menjalankan pernyataan SQL yang sudah diprekompilasi
import java.sql.SQLException;       // Mengimpor kelas SQLException untuk menangani kesalahan SQL


import com.kelompok2.DB;
import com.kelompok2.models.Donatur;

// --------------------------------------------------------------------------------------------------------------------- //
//                                    Prosedur dan Fungsi Di dalam Class
// --------------------------------------------------------------------------------------------------------------------- //
//  [1] Class controller Donatur
public class ControllerDonatur {

// --------------------------------------------------------------------------------------------------------------------- //
//  [2] Metode save ke database
    public static void saveToDatabase(Donatur donatur) {
        try (Connection con = DB.getConnection()) {
            String sql = "INSERT INTO donatur (namaDonatur, umurDonatur, pekerjaanDonatur, alamatDonatur, nomorDonatur, emailDonatur, password, role) VALUES (?, ?, ?, ?, ?, ?, ?, 'U')";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, donatur.getNamaDonatur());
                stmt.setInt(2, donatur.getUmurDonatur());
                stmt.setString(3, donatur.getPekerjaanDonatur());
                stmt.setString(4, donatur.getAlamatDonatur());
                stmt.setString(5, donatur.getNomorDonatur());
                stmt.setString(6, donatur.getEmailDonatur());
                stmt.setString(7, donatur.getPassword());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [2] Metode mendapatkan id donatur berdasarkan email dan password
    public static int getIdDonaturByEmailPassword(String email, String password) {
        try (Connection con = DB.getConnection()) {
            String sql = "SELECT idDonatur FROM donatur WHERE emailDonatur = ? AND password = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("idDonatur");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
