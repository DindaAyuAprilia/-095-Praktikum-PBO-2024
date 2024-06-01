package com.kelompok2.controller;

import java.sql.Connection;              // Mengimpor kelas Connection untuk membuat koneksi ke database
import java.sql.PreparedStatement;       // Mengimpor kelas PreparedStatement untuk menjalankan pernyataan SQL yang sudah diprekompilasi
import java.sql.SQLException;            // Mengimpor kelas SQLException untuk menangani kesalahan SQL

import com.kelompok2.DB;                 // Mengimpor kelas DB dari paket com.kelompok2
import com.kelompok2.models.PantiAsuhan; // Mengimpor kelas Panti Asuhan dari paket com.kelompok2.models


// --------------------------------------------------------------------------------------------------------------------- //
//                                    Prosedur dan Fungsi Di dalam Class
// --------------------------------------------------------------------------------------------------------------------- //
//  [1] Class controller Panti Asuhan
public class ControllerPantiAsuhan {

// --------------------------------------------------------------------------------------------------------------------- //
//  [2] Metode save ke database
    public static void saveToDatabase(PantiAsuhan panti) {
        try (Connection con = DB.getConnection()) {
            String query = "INSERT INTO pantiasuhan (namaPantiAsuhan, kapasitasPantiAsuhan, lokasiPantiAsuhan, statusPantiAsuhan, statusBangunan) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, panti.getNamaPantiAsuhan());
                pstmt.setInt(2, panti.getKapasitasPantiAsuhan());
                pstmt.setString(3, panti.getLokasiPantiAsuhan());
                pstmt.setString(4, panti.getStatusPantiAsuhan());
                pstmt.setString(5, panti.getStatusBangunan());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [3] Metode update ke database
    public static void updateDatabase(PantiAsuhan panti) {
        try (Connection con = DB.getConnection()) {
            String query = "UPDATE pantiasuhan SET namaPantiAsuhan = ?, statusBangunan = ? WHERE kodePantiAsuhan = ?";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, panti.getNamaPantiAsuhan());
                pstmt.setString(2, panti.getStatusBangunan());
                pstmt.setInt(3, panti.getKodePantiAsuhan());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
