package com.kelompok2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import com.kelompok2.models.Bantuan;

// Admin class
public class Admin {

    // List menu
    static final String[] menuDonatur = {"Daftar Data Donatur", "Exit"};
    static final String[] menuAdmin   = {"Data Anak", "Data Donatur", "Data Panti Asuhan", "Data Bantuan", "Exit"};
    static final String[] menuPanti   = {"Tambah Panti Asuhan", "Daftar Panti Asuhan", "Ubah Panti Asuhan", "Exit"};
    static final String[] menuAnak    = {"Tambah Data Anak", "Daftar Data Anak", "Ubah Data Anak", "Luluskan Anak", "Lihat Histori Anak", "Exit"};

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    // Admin menu
    public static void MenuAdmin() throws IOException {
        while (true) {
            Tampilan.menu("Menu Admin", menuAdmin);
            String pilihan = input.readLine();
            Tampilan.cls();

            switch (pilihan) {
                case "1":
                    Menu.menuAnak(menuAnak);
                    break;
                case "2":
                    Menu.menuDonatur(menuDonatur);
                    break;
                case "3":
                    Menu.menuPanti(menuPanti);
                    break;
                case "4":
                    Bantuan.lihatHistoriBantuan();
                    break;
                case "5":
                    Tampilan.cls();
                    return;
                default:
                    Tampilan.cls();
                    Tampilan.notif("Pilihan tidak valid, silakan coba lagi.");
            }
        }
    }
}
