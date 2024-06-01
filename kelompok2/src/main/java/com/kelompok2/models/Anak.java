package com.kelompok2.models;

import java.io.BufferedReader;      // Import untuk membaca input dari InputStreamReader
import java.io.IOException;         // Import untuk menangani IOException
import java.io.InputStreamReader;   // Import untuk membaca input dari user
import java.sql.Connection;         // Import untuk koneksi ke database
import java.sql.PreparedStatement;  // Import untuk menyiapkan pernyataan SQL
import java.sql.ResultSet;          // Import untuk menampung hasil dari pernyataan SQL
import java.sql.SQLException;       // Import untuk menangani SQLException

import com.kelompok2.CRUD;          // Import untuk operasi CRUD (Create, Read, Update, Delete)
import com.kelompok2.DB;            // Import untuk manajemen koneksi database
import com.kelompok2.ErrorHandling; // Import untuk menangani kesalahan
import com.kelompok2.Tampilan;      // Import untuk tampilan antarmuka pengguna
import com.kelompok2.controller.ControllerAnak; // Import untuk mengontrol logika aplikasi terkait anak


// --------------------------------------------------------------------------------------------------------------------- //
//                        Deklarasi kelas Anak yang mengimplementasikan interface CRUD
// --------------------------------------------------------------------------------------------------------------------- //
public class Anak implements CRUD {

    // Atribut anak
    private int idAnak;
    private String namaAnak;
    private int umurAnak;
    private String jenisKelamin;
    private String statusAnak;
    private int kodePantiAsuhan;
    int hitung = 0;

    
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    // Konstruktur kosong untuk deklarasi objek tanpa memberikan nilai awal ke atribut-atributnya.
    public Anak() {}

    // Konstruktur dengan parameter untuk deklarasi objek dengan memberikan nilai awal ke atribut-atributnya.
    public Anak(String nama, int umur, String jk, String status, int kodePantiAsuhan) {
        this.namaAnak = nama;
        this.umurAnak = umur;
        this.jenisKelamin = jk;
        this.statusAnak = status;
        this.kodePantiAsuhan = kodePantiAsuhan;
    }


// --------------------------------------------------------------------------------------------------------------------- //
//                                    Prosedur dan Fungsi Di dalam Class
// --------------------------------------------------------------------------------------------------------------------- //
//  [1] Membuat data anak baru
    public static Anak dataAnak() throws IOException {
        Tampilan.cls();

        // Memeriksa apakah terdapat panti asuhan yang tersedia 
        if (!cekPantiAsuhan()) {
            Tampilan.notif("Belum ada panti asuhan. Tidak bisa menambahkan anak.");
            return null;
        }

        // Meminta input pengguna untuk nama dan usia anak
        Tampilan.notif("Mengisi Data Anak");
        String namaAnak = ErrorHandling.harusHuruf("\n\nMasukkan Nama Anak: ", 2, 20);
        int usiaAnak = ErrorHandling.batasan(0, 17, "Masukkan Usia Anak: ");
        
        // Memilih panti asuhan tempat anak akan ditempatkan
        Tampilan.cls();
        System.out.println("Pilih Panti Asuhan Tempat Anak Akan Ditempatkan:");
        int kodePantiAsuhan = pilihPantiAsuhan();
        if (kodePantiAsuhan == -1) {
            Tampilan.cls();
            Tampilan.notif("Pilihan tidak valid atau panti penuh. Tidak bisa menambahkan anak.");
            return null;
        }

        // Memilih jenis kelamin anak
        Tampilan.cls();
        String jenisKelamin = "";
        while (true) {
            Tampilan.menu("Jenis Kelamin", new String[]{"Laki-laki", "Perempuan"});
            String pilihanJK = input.readLine();
            Tampilan.garis();

            if (pilihanJK.equals("1")) {
                jenisKelamin = "Laki-laki";
                break;
            } else if (pilihanJK.equals("2")) {
                jenisKelamin = "Perempuan";
                break;
            } else {
                Tampilan.cls();
                Tampilan.notif("Pilihan tidak sesuai, coba lagi");
            }
        }

        // Memilih status anak
        Tampilan.cls();
        String statusAnak = "";
        while (true) {
            Tampilan.menu("Status Anak", new String[]{"Normal", "Berkebutuhan Khusus"});
            String pilihanStatus = input.readLine();
            Tampilan.garis();

            if (pilihanStatus.equals("1")) {
                statusAnak = "Normal";
                break;
            } else if (pilihanStatus.equals("2")) {
                statusAnak = "Berkebutuhan Khusus";
                break;
            } else {
                Tampilan.cls();
                Tampilan.notif("Pilihan tidak sesuai, coba lagi");
            }
        }

        // Mengembalikan objek anak baru dengan informasi yang telah diinputkan
        Tampilan.cls();
        return new Anak(namaAnak, usiaAnak, jenisKelamin, statusAnak, kodePantiAsuhan);
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [2] Cek apakah data panti ada
    public static boolean cekPantiAsuhan() {

        // Membuat koneksi
        try (Connection con = DB.getConnection()) {

            // Membuat query
            String sql = "SELECT COUNT(*) FROM pantiasuhan";

            // Mempersiapkan pernyataan SQL
            PreparedStatement stmt = con.prepareStatement(sql);

            // Mengeksekusi query dan menyimpan hasilnya dalam objek ResultSet
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Mengembalikan 'true' jika jumlah entri lebih dari 0, menandakan adanya data panti asuhan
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Mengembalikan 'false' jika terjadi pengecualian atau tidak ada hasil yang ditemukan dalam query
        return false;
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [3] Metode untuk memilih panti asuhan tempat anak akan ditempatkan
    public static int pilihPantiAsuhan() throws IOException {

        // Membuat koneksi
        try (Connection con = DB.getConnection()) {

            // Membuat query untuk mengambil data panti asuhan dari tabe
            String sql = "SELECT * FROM pantiasuhan";

            // Membuat PreparedStatement dengan ResultSet yang dapat digulir
            PreparedStatement stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // Mengeksekusi query untuk mendapatkan hasil
            ResultSet rs = stmt.executeQuery();
    
            // Memeriksa apakah hasil query kosong
            if (!rs.next()) {
                Tampilan.notif("Belum ada panti asuhan.");
                Tampilan.cls();
                return -1;
            }
    

            // Menampilkan daftar panti asuhan akan ditampilkan
            while (true) {
                Tampilan.notif("Daftar Panti Asuhan");

                // Mengatur posisi cursor ke sebelum baris pertama
                rs.beforeFirst();  
                
                int index = 1;    // Inisialisasi indeks untuk nomor panti asuhan

                // Menampilkan semua panti asuhan dan statusnya
                while (rs.next()) {
                    System.out.println(index + ". " + rs.getString("namaPantiAsuhan") + " (Status: " + rs.getString("statusPantiAsuhan") + ")");
                    index++;
                }
                Tampilan.garis();
    
                // Memilih panti asugan
                int nomorPanti = ErrorHandling.harusAngka("Pilih nomor panti asuhan: ");

                // Memeriksa apakah nomor yang dimasukkan oleh pengguna valid
                if (nomorPanti < 1 || nomorPanti >= index) {
                    Tampilan.notif("Pilihan tidak valid.");
                    Tampilan.cls();
                    continue;
                }
    
                // Mengatur posisi cursor ke nomor panti asuhan yang dipilih
                rs.absolute(nomorPanti);

                // Memeriksa apakah panti asuhan yang dipilih penuh
                if (rs.getString("statusPantiAsuhan").equals("Penuh")) {
                    Tampilan.notif("Panti asuhan penuh. Pilih panti asuhan lain.");
                } else {
                    Tampilan.cls();
                    return rs.getInt("kodePantiAsuhan");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Mengembalikan -1 jika terjadi kesalahan dalam eksekusi query
        return -1;
    }
    
// --------------------------------------------------------------------------------------------------------------------- //
//  [4] Metode untuk menambah data anak
    @Override
    public void create() throws IOException {

        // Mengisi data anak
        Anak anak = dataAnak();

        // Memeriksa apakah data anak berhasil diisi lalu save ke database
        if (anak != null) {
            ControllerAnak.saveToDatabase(anak);

            // Setelah anak berhasil ditambahkan, perbarui status panti asuhan
            PantiAsuhan panti = PantiAsuhan.getPantiAsuhanByKode(anak.kodePantiAsuhan);
            if (panti != null) {
                panti.perbaruiStatusPanti();
            }
            
            Tampilan.cls();
            Tampilan.notif("Data Anak Berhasil ditambahkan!");
        }
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [5] Metode untuk membaca data anak
    @Override
    public void read() throws IOException {
        hitung = 0;

        // Membuat koneksi
        try (Connection con = DB.getConnection()) {

            // Membuat querry
            String sql = "SELECT a.*, p.namaPantiAsuhan FROM anak a JOIN pantiasuhan p ON a.kodePantiAsuhan = p.kodePantiAsuhan";
            
            // Mempersiapkan pernyataan SQL
            PreparedStatement stmt = con.prepareStatement(sql);

            // Mengeksekusi query dan menyimpan hasilnya dalam objek ResultSet
            ResultSet rs = stmt.executeQuery();

            // Jika tidak ada data dalam hasil query
            if (!rs.next()) {
                Tampilan.notif("Data Anak kosong");
                return;
            }

            // Menampilkan semua data anak
            Tampilan.notif("Data Anak");
            do {
                Tampilan.garis();
                System.out.println("ID anak         : " + rs.getInt("idAnak"));
                System.out.println("Nama            : " + rs.getString("namaAnak"));
                System.out.println("Umur            : " + rs.getInt("umurAnak"));
                System.out.println("Jenis Kelamin   : " + rs.getString("jenisKelamin"));
                System.out.println("Status          : " + rs.getString("statusAnak"));
                System.out.println("Nama Panti Asuhan : " + rs.getString("namaPantiAsuhan"));
                System.out.println();
                hitung++;
            } while (rs.next());

            Tampilan.garis();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [6] Metode untuk mengubah data anak
    @Override
    public void update() throws IOException {
        Tampilan.cls();
        int idAnak;
        while (true) {
            
            // Memanggil metode read() untuk menampilkan data anak yang ada
            read();
            
            // Jika tidak ada data anak
            if (hitung == 0){
                return;
            }

            // Meminta pengguna untuk memilih ID anak yang akan diubah
            idAnak = ErrorHandling.harusAngka("Pilih ID anak yang ingin diubah: ");
            
            // Membuat koneksi
            try (Connection con = DB.getConnection()) {

                // Membuat query
                String sql = "SELECT COUNT(*) FROM anak WHERE idAnak = ?";
                
                // Menyiapkan pernyataan dengan id yang dipilih
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, idAnak);

                // Mengeksekusi query
                ResultSet rs = stmt.executeQuery();

                // Jika ada entri dengan ID anak yang dipilih, keluar dari loop
                if (rs.next() && rs.getInt(1) > 0) {
                    break;
                } else {
                    Tampilan.notif("ID tidak valid, coba lagi.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Membuat koneksi
        try (Connection con = DB.getConnection()) {

            // Membuat query
            String sql = "SELECT * FROM anak WHERE idAnak = ?";

            // Menyiapkan pernyataan sql dengan id anak
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idAnak);
            ResultSet rs = stmt.executeQuery();

            // Membuat objek Anak baru untuk menyimpan data anak yang akan diubah
            Anak anakDiubah = new Anak();

            // Memeriksa apakah result set memiliki data
            if (rs.next()) {
                anakDiubah.idAnak = rs.getInt("idAnak");
                anakDiubah.namaAnak = rs.getString("namaAnak");
                anakDiubah.umurAnak = rs.getInt("umurAnak");
                anakDiubah.jenisKelamin = rs.getString("jenisKelamin");
                anakDiubah.statusAnak = rs.getString("statusAnak");
                anakDiubah.kodePantiAsuhan = rs.getInt("kodePantiAsuhan");

                boolean updated = false;

                // Menampilkan informasi anak yang akan diubah dan meminta pengguna untuk memilih atribut yang ingin diubah
                while (true) {
                    Tampilan.cls();
                    Tampilan.garis();
                    System.out.println("ID anak         : " + anakDiubah.idAnak);
                    System.out.println("Nama            : " + anakDiubah.namaAnak);
                    System.out.println("Umur            : " + anakDiubah.umurAnak);
                    System.out.println("Jenis Kelamin   : " + anakDiubah.jenisKelamin);
                    System.out.println("Status          : " + anakDiubah.statusAnak);
                    Tampilan.garis();
                    System.out.println();
                    Tampilan.menu("Pilih Atribut Data yang Ingin Diubah", new String[]{"Nama", "Umur", "Status", "Selesai"});
                    String pilihan = input.readLine();

                    switch (pilihan) {
                        case "1":
                            anakDiubah.namaAnak = ErrorHandling.harusHuruf("Masukkan Nama Anak baru: ", 2, 20);
                            updated = true;
                            break;
                        case "2":
                            anakDiubah.umurAnak = ErrorHandling.batasan(0, 17, "Masukkan Usia Anak baru: ");
                            updated = true;
                            break;
                        case "3":
                            while (true) {
                                Tampilan.menu("Status Anak", new String[]{"Normal", "Berkebutuhan Khusus"});
                                String pilihanStatus = input.readLine();
                                Tampilan.garis();

                                if (pilihanStatus.equals("1")) {
                                    anakDiubah.statusAnak = "Normal";
                                    break;
                                } else if (pilihanStatus.equals("2")) {
                                    anakDiubah.statusAnak = "Berkebutuhan Khusus";
                                    break;
                                } else {
                                    Tampilan.cls();
                                    Tampilan.notif("Pilihan tidak sesuai, coba lagi");
                                }
                            }
                            updated = true;
                            break;
                        case "4":
                            if (updated) {
                                ControllerAnak.updateDatabase(anakDiubah);
                            }
                            return;
                        default:
                            Tampilan.cls();
                            Tampilan.notif("Pilihan tidak valid, coba lagi.");
                            break;
                    }
                }
            } else {
                Tampilan.notif("Data anak tidak ditemukan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [7] Metode untuk menghapus data anak
    @Override
    public void delete() throws IOException {
        Tampilan.cls();
        int idAnak;

        while (true) {
            // Menampilkan data anak, jika kosong maka keluar
            read();
            if (hitung == 0) {
                return;
            }

            // Memilih data anak berdasarkan id anak
            idAnak = ErrorHandling.harusAngka("Pilih ID anak yang ingin diluluskan: ");
            
            // Membuat koneksi
            try (Connection con = DB.getConnection()) {
                // Membuat query
                String sql = "SELECT COUNT(*) FROM anak WHERE idAnak = ?";
                
                // Menyiapkan pernyataan sql
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, idAnak);

                // Mengeksekusi pernyataan
                ResultSet rs = stmt.executeQuery();

                // Jika ditemukan datanya, maka keluar dari loop
                if (rs.next() && rs.getInt(1) > 0) {
                    break;
                } else {
                    Tampilan.notif("ID tidak valid, coba lagi.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Membuat koneksi
        try (Connection con = DB.getConnection()) {
            // Membuat query
            String sql = "SELECT a.*, p.namaPantiAsuhan FROM anak a JOIN pantiasuhan p ON a.kodePantiAsuhan = p.kodePantiAsuhan WHERE a.idAnak = ?";
            
            // Menyiapkan pernyataan
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idAnak);

            // Mengeksekusi pernyataan
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) { // Memastikan result set memiliki data sebelum mengaksesnya
                // Menampilkan informasi anak
                Tampilan.cls();
                Tampilan.garis();
                System.out.println("ID anak         : " + rs.getInt("idAnak"));
                System.out.println("Nama            : " + rs.getString("namaAnak"));
                System.out.println("Umur            : " + rs.getInt("umurAnak"));
                System.out.println("Jenis Kelamin   : " + rs.getString("jenisKelamin"));
                System.out.println("Status          : " + rs.getString("statusAnak"));
                System.out.println("Nama Panti Asuhan : " + rs.getString("namaPantiAsuhan"));
                Tampilan.garis();

                // Menyimpan data panti asuhan untuk pembaruan status setelah penghapusan anak
                int kodePantiAsuhan = rs.getInt("kodePantiAsuhan");

                // Memilih status keluar anak karena apa
                String statusKeluar = "";
                while (true) {
                    Tampilan.cls();
                    Tampilan.menu("Status Keluar Anak", new String[]{"Lulus", "Diadopsi"});
                    String pilihanStatus = input.readLine();
                    if (pilihanStatus.equals("1")) {
                        statusKeluar = "Lulus";
                        break;
                    } else if (pilihanStatus.equals("2")) {
                        statusKeluar = "Diadopsi";
                        break;
                    } else {
                        Tampilan.notif("Pilihan tidak sesuai, coba lagi");
                    }
                }

                // Menyimpan data anak ke histori anak untuk tujuan arsip
                String insertHistoriSql = "INSERT INTO historianak (idAnak, namaAnak, umurAnak, jenisKelamin, statusAnak, statusKeluar, namaPantiAsuhan) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = con.prepareStatement(insertHistoriSql)) {
                    pstmt.setInt(1, rs.getInt("idAnak"));
                    pstmt.setString(2, rs.getString("namaAnak"));
                    pstmt.setInt(3, rs.getInt("umurAnak"));
                    pstmt.setString(4, rs.getString("jenisKelamin"));
                    pstmt.setString(5, rs.getString("statusAnak"));
                    pstmt.setString(6, statusKeluar);
                    pstmt.setString(7, rs.getString("namaPantiAsuhan"));
                    pstmt.executeUpdate();
                }

                // Menghapus data anak di database
                ControllerAnak.deleteFromDatabase(idAnak);

                // Memperbarui status panti asuhan setelah anak dihapus
                PantiAsuhan panti = PantiAsuhan.getPantiAsuhanByKode(kodePantiAsuhan);
                if (panti != null) {
                    panti.perbaruiStatusPanti();
                }

                Tampilan.cls();
                Tampilan.notif("Anak Berhasil diluluskan!");
            } else {
                Tampilan.notif("Data anak tidak ditemukan.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [8] Metode untuk melihat histori data anak
    public void lihatHistoriAnak() throws IOException {
        Tampilan.cls();
    
        // Membuat koneksi
        try (Connection con = DB.getConnection()) {

            // Membuat querry
            String sql = "SELECT * FROM historianak";

            // Menyiapkan pernyataan
            PreparedStatement stmt = con.prepareStatement(sql);

            // Mengeksekusi pernyataan
            ResultSet rs = stmt.executeQuery();
    
            // Jika data histori kosong
            if (!rs.next()) {
                Tampilan.notif("Histori Anak kosong");
                return;
            }
    
            // Menampilkan data anak
            Tampilan.notif("Histori Anak");
            do {
                Tampilan.garis();
                System.out.println("ID anak            : " + rs.getInt("idAnak"));
                System.out.println("Nama               : " + rs.getString("namaAnak"));
                System.out.println("Umur               : " + rs.getInt("umurAnak"));
                System.out.println("Jenis Kelamin      : " + rs.getString("jenisKelamin"));
                System.out.println("Status             : " + rs.getString("statusAnak"));
                System.out.println("Status Keluar      : " + rs.getString("statusKeluar"));
                System.out.println("Nama Panti Asuhan  : " + rs.getString("namaPantiAsuhan"));
                System.out.println();
            } while (rs.next());
    
            Tampilan.garis();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// --------------------------------------------------------------------------------------------------------------------- //
//  [9] Metode untuk melihat histori data anak
    public static void lihatAnakDalamPanti(int kodePantiAsuhan) {

        // Membuat koneksi
        try (Connection con = DB.getConnection()) {

            // Membuat querry dan menyiapkan pernyataanya sesuai kode pantiasuhan yang di pilih lalu dieksekusi
            String sql = "SELECT * FROM anak WHERE kodePantiAsuhan = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, kodePantiAsuhan);
            ResultSet rs = stmt.executeQuery();

            // Jika tidak ada data anak di dalamnya
            if (!rs.next()) {
                Tampilan.notif("Data Anak dalam Panti Asuhan ini kosong");
                return;
            }

            // Menampilkan data anak di panti asuhan ini
            Tampilan.notif("Data Anak dalam Panti Asuhan");
            do {
                Tampilan.garis();
                System.out.println("ID Anak            : " + rs.getInt("idAnak"));
                System.out.println("Nama Anak          : " + rs.getString("namaAnak"));
                System.out.println("Umur Anak          : " + rs.getInt("umurAnak"));
                System.out.println("Jenis Kelamin      : " + rs.getString("jenisKelamin"));
                System.out.println("Status Anak        : " + rs.getString("statusAnak"));
                System.out.println("Kode Panti Asuhan  : " + rs.getInt("kodePantiAsuhan"));
                System.out.println();
            } while (rs.next());

            Tampilan.garis();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
// --------------------------------------------------------------------------------------------------------------------- //
//  [10] Metode getter dan setter
    public int getIdAnak() {
        return idAnak;
    }

    public void setIdAnak(int idAnak) {
        this.idAnak = idAnak;
    }

    public String getNamaAnak() {
        return namaAnak;
    }

    public void setNamaAnak(String namaAnak) {
        this.namaAnak = namaAnak;
    }

    public int getUmurAnak() {
        return umurAnak;
    }

    public void setUmurAnak(int umurAnak) {
        this.umurAnak = umurAnak;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getStatusAnak() {
        return statusAnak;
    }

    public void setStatusAnak(String statusAnak) {
        this.statusAnak = statusAnak;
    }

    public int getKodePantiAsuhan() {
        return kodePantiAsuhan;
    }

    public void setKodePantiAsuhan(int kodePantiAsuhan) {
        this.kodePantiAsuhan = kodePantiAsuhan;
    }
}
