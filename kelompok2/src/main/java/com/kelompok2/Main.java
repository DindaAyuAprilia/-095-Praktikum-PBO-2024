package com.kelompok2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.kelompok2.models.Donatur;

import java.io.IOException;

public class Main {
    static String[] menuMasuk = {"Registrasi", "Login", "Exit"};
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int idDonatur;

    public static void main(String[] args) throws IOException {
        Tampilan.cls();
        while (true) {
            Tampilan.menu("Selamat Datang", menuMasuk);
            String pilihan = input.readLine();
            Tampilan.cls();

            switch (pilihan) {
                case "1":
                    Donatur.tambahDonatur();
                    break;
                case "2":
                    Tampilan.notif("L O G I N");
                    String email = ErrorHandling.validasiEmailLogin("Masukkan Email      : ");
                    String password = ErrorHandling.validasiPassword("Masukkan Password   : ");

                    // Check for donor credentials
                    int id = Donatur.getIdDonaturByEmailPassword(email, password);
                    if (id != -1) {
                        // Check Admin
                        if (email.equals("kelompok2@gmail.com") && password.equals(ErrorHandling.hashPassword("admin123"))){
                            Tampilan.notif("Login berhasil, Selamat datang Admin...");
                            Admin.MenuAdmin();
                            break;
                        }
                        idDonatur = id;    
                        Tampilan.cls();
                        Tampilan.notif("Login berhasil!!");
                        Menu.MenuUser(idDonatur);
                    } else {
                        Tampilan.cls();
                        Tampilan.notif("Login gagal. Email/Password salah.");
                    }
                    break;
                case "3":
                    Tampilan.notif("Terima kasih telah menggunakan program.");
                    System.exit(0);
                default:
                    Tampilan.notif("Pilihan tidak valid, silahkan coba lagi");
            }
        }
    }
}
