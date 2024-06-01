package com.kelompok2;

// Mengimpor kelas yang diperlukan
import java.io.BufferedReader;                  // Untuk membaca input dari pengguna
import java.io.IOException;                     // Untuk menangani kesalahan I/O
import java.io.InputStreamReader;               // Untuk membaca input dari sistem
import java.util.regex.Matcher;                 // Untuk mencocokkan pola dalam string
import java.util.regex.Pattern;                 // Untuk mendefinisikan pola regex
import java.security.MessageDigest;             // Untuk menghitung hash dari string
import java.security.NoSuchAlgorithmException;  // Untuk menangani pengecualian jika algoritma hash tidak ditemukan
import java.sql.Connection;                     // Untuk membuat koneksi ke database
import java.sql.PreparedStatement;              // Untuk menjalankan pernyataan SQL yang sudah dipersiapkan
import java.sql.ResultSet;                      // Untuk menyimpan hasil dari pernyataan SQL
import java.sql.SQLException;                   // Untuk menangani pengecualian SQL


// Deklarasi kelas final, berarti kelas ini tidak bisa diwariskan
public final class ErrorHandling {

    // Deklarasi objek BufferedReader untuk membaca input dari sistem
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    // Method untuk memastikan input hanya angka
    public static final int harusAngka(String kalimat){
        while (true) {
            try {
                System.out.print(kalimat);
                int angka = Integer.parseInt(input.readLine());
                return angka;
            } 
            
            // Terdapat huruf pada inputan
            catch (NumberFormatException | IOException e) {
                Tampilan.notif("Hanya menerima inputan angka, silahkan coba lagi...");
            }
        }
    }

    // Method untuk mengembalikan input angka sebagai string
    public static final String harusAngkaString(String kalimat){
        int angka = harusAngka(kalimat);
        String angkaString = String.valueOf(angka);
        return angkaString;    
    }

    // Method untuk memastikan input angka dalam batasan tertentu
    public static final int batasan(final int min, final int max, String kalimat){
        while(true){
            int angka = harusAngka(kalimat);

            // Cek apakah angka memenuhi batasan
            if (angka < min){
                Tampilan.notif("Angka yang di masukan terlalu kecil, harus > " + min);
            } else if (angka > max){
                Tampilan.notif("Angka yang di masukan terlalu besar, harus < " + max);
            } else{
                return angka;
            }
        }
    }

    // Method untuk memastikan input hanya berisi huruf dan dalam batasan panjang tertentu
    public static final String harusHuruf(String kalimat, int min, int max) {
        while (true) {
            try {
                System.out.print(kalimat);
                String huruf = input.readLine().trim();
    
                // Memastikan hanya huruf dan spasi yang dimasukkan, dan input tidak kosong
                if (!huruf.isEmpty() && huruf.matches("[a-zA-Z ]+")) {
                    if (huruf.length() >= min && huruf.length() <= max) {
                        return huruf;
                    } else {
                        Tampilan.notif("Kalimat harus terdiri dari " + min + " sampai " + max + " karakter.");
                    }
                } else {
                    Tampilan.notif("Hanya menerima inputan huruf dan spasi, silahkan coba lagi...");
                }
            } catch (IOException e) {
                Tampilan.notif("Terjadi kesalahan saat membaca input, silahkan coba lagi...");
            }
        }
    }
    
    // Method untuk validasi password dengan panjang dan karakter tertentu
    public static final String validasiPassword(String kalimat) throws IOException {
        while (true) {
            System.out.print(kalimat);
            String huruf = input.readLine();
            if (huruf.length() >= 6 && huruf.length() <= 10 && huruf.matches(".*\\d.*") && huruf.matches(".*[a-zA-Z].*")) {
                return hashPassword(huruf);
            } else {
                Tampilan.notif("Password antara 6 dan 10 karakter dan mengandung satu angka dan satu huruf.");
            }
        }
    }

    // Method untuk menghitung hash dari password
    public static String hashPassword(String password) {
    try {
        // Menggunakan algoritma SHA-256
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Menghitung hash
        byte[] hash = md.digest(password.getBytes());

        // StringBuilder untuk menyimpan hasil hash dalam format heksadesimal
        StringBuilder hexString = new StringBuilder(2 * hash.length);

        // Mengubah byte ke heksadesimal
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();

    } 
    
    // Menangani pengecualian jika algoritma tidak ditemukan
    catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
        return null;
    }
}


    // Method untuk memvalidasi email
    public static final boolean isValidEmail(String email) {
        // Pola regex untuk email
        final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        
        // Mengompilasi pola regex
        final Pattern pattern = Pattern.compile(emailRegex);

        // Mencocokkan email dengan pola
        final Matcher matcher = pattern.matcher(email);

        // Mengembalikan true jika sesuai
        return matcher.matches();
    }

    // Method untuk mengecek apakah email sudah ada di database
    public static boolean isEmailExist(String email) {
        try (Connection con = DB.getConnection()) {
            String sql = "SELECT COUNT(*) FROM donatur WHERE emailDonatur = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method untuk validasi email dengan error handling
    public static final String validasiEmail(String kalimat) throws IOException {
        while (true) {
            System.out.print(kalimat);
            String email = input.readLine();
            if (isValidEmail(email)) {
                if (!isEmailExist(email)) {
                    return email;
                } else {
                    Tampilan.notif("Email sudah ada. Silakan gunakan email lain.");
                }
            } else {
                Tampilan.notif("Email tidak valid. Silakan coba lagi.");
            }
        }
    }


    // Method untuk validasi email pada login
    public static final String validasiEmailLogin(String kalimat) throws IOException {
        while (true) {
            System.out.print(kalimat);
            String email = input.readLine();
            if (isValidEmail(email)) {
                return email;
            } else {
                Tampilan.notif("Email tidak valid. Silakan coba lagi.");
            }
        }
    }

    // Method untuk validasi nomor ponsel
    public static String validasiNomorPonsel() throws IOException {
        String nomor;
        while (true) {
            System.out.print("Masukkan Nomor Ponsel Anda : ");
            nomor = input.readLine();
            if (nomor.matches("^(08|\\+62)\\d{8,11}$")) {
                break;
            } else if (nomor.matches("\\d{10,13}")) {
                Tampilan.notif("Nomor ponsel tampaknya berasal dari negara lain. Apakah Anda yakin? (ya/tidak): ");
                String konfirmasi = input.readLine();
                if (konfirmasi.equalsIgnoreCase("ya")) {
                    break;
                }
            } else {
                Tampilan.notif("Nomor ponsel harus terdiri dari 10-13 digit dan dimulai dengan 08 atau +62.");
            }
        }
        return nomor;
    }
    

    // Validasi untuk panjang kalimat
    public static String validasiPanjangKalimat(String kata, int min, int max) throws IOException {
        String kalimat;
        while (true) {
            System.out.print(kata);
            kalimat = input.readLine();
            int letterCount = 0;
            for (char c : kalimat.toCharArray()) {
                if (Character.isLetter(c)) {
                    letterCount++;
                }
            }
            if (kalimat.matches("[a-zA-Z 0-9.-]+") && kalimat.length() >= min && kalimat.length() <= max && letterCount >= 3) {
                break;
            } else {
                if (kalimat.length() < min || kalimat.length() > max) {
                    Tampilan.notif("Kalimat harus terdiri dari " + min + "-" + max + " karakter.");
                }
                if (!kalimat.matches("[a-zA-Z ]+")) {
                    Tampilan.notif("Kalimat hanya boleh terdiri dari huruf dan spasi.");
                }
                if (letterCount < 3) {
                    Tampilan.notif("Kalimat harus memiliki setidaknya 2 huruf.");
                }
            }
        }
        return kalimat;
    }

    // Method untuk validasi panjang kalimat yang hanya berisi angka
    public static String validasiPanjangKalimatInt(String kata, int min, int max) throws IOException {
        String kalimat;
        while (true) {
            System.out.print(kata);
            kalimat = input.readLine();

             // Validasi jika kalimat hanya berisi angka dan panjangnya sesuai batas min dan max
            if (kalimat.matches("[0-9]+") && kalimat.length() >= min && kalimat.length() <= max) {
                break;
            } else {
                Tampilan.notif("Kalimat tidak boleh berisi huruf");
                if (kalimat.length() < min || kalimat.length() > max) {
                    Tampilan.notif("Kalimat harus terdiri dari " + min + "-" + max + " karakter.");
                }
            }
        }
        return kalimat;
    }
    
}
