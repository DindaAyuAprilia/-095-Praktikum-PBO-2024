package com.kelompok2.controller; // Deklarasi paket untuk kelas ini, menunjukkan bahwa kelas ini adalah bagian dari paket 'com.kelompok2.controller'


import java.sql.Connection;         // Mengimpor kelas Connection untuk membuat koneksi ke database
import java.sql.PreparedStatement;  // Mengimpor kelas PreparedStatement untuk menjalankan pernyataan SQL yang sudah diprekompilasi
import java.sql.SQLException;       // Mengimpor kelas SQLException untuk menangani kesalahan SQL

import com.kelompok2.DB;            // Mengimpor kelas DB dari paket com.kelompok2
import com.kelompok2.models.Anak;   // Mengimpor kelas Anak dari paket com.kelompok2.models


// --------------------------------------------------------------------------------------------------------------------- //
//                                    Prosedur dan Fungsi Di dalam Class
// --------------------------------------------------------------------------------------------------------------------- //
//  [1] Class controller anak
public class ControllerAnak {

// --------------------------------------------------------------------------------------------------------------------- //
//  [2] Metode save ke database
    public static void saveToDatabase(Anak anak) {
        try (Connection con = DB.getConnection()) {
            String query = "INSERT INTO anak (namaAnak, umurAnak, jenisKelamin, statusAnak, kodePantiAsuhan) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, anak.getNamaAnak());
                pstmt.setInt(2, anak.getUmurAnak());
                pstmt.setString(3, anak.getJenisKelamin());
                pstmt.setString(4, anak.getStatusAnak());
                pstmt.setInt(5, anak.getKodePantiAsuhan());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [3] Metode update ke database
    public static void updateDatabase(Anak anak) {
        String query = "UPDATE anak SET namaAnak = ?, umurAnak = ?, statusAnak = ?, kodePantiAsuhan = ? WHERE idAnak = ?";
        try (Connection con = DB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, anak.getNamaAnak());
            pstmt.setInt(2, anak.getUmurAnak());
            pstmt.setString(3, anak.getStatusAnak());
            pstmt.setInt(4, anak.getKodePantiAsuhan());
            pstmt.setInt(5, anak.getIdAnak());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [4] Metode delete dari database
    public static void deleteFromDatabase(int idAnak) {
        try (Connection con = DB.getConnection()) {
            String query = "DELETE FROM anak WHERE idAnak = ?";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setInt(1, idAnak);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     
}