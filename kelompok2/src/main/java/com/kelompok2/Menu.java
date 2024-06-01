package com.kelompok2;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.kelompok2.models.Anak;
import com.kelompok2.models.Bantuan;
import com.kelompok2.models.BantuanBarang;
import com.kelompok2.models.BantuanUang;
import com.kelompok2.models.Donatur;
import com.kelompok2.models.PantiAsuhan;

import java.io.IOException;

// Default akses
public class Menu {

    // List menu
    static final String[] menuBantuan          = {"Donasi Barang", "Donasi Uang", "Exit"};
    static final String[] menuUser             = {"Profil", "Donasi", "Lihat Riwayat Donasi", "Exit"}; 

    // Deklarasi objek yang akan digunakan
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    // Menu Donatur saat masuk
    public static void MenuUser(int idDonatur) throws IOException {
        while (true) {
            Tampilan.menu("Menu Donatur", menuUser); 
            String pilihan = input.readLine();
            Tampilan.cls();

            switch (pilihan) {
                case "1":
                    Donatur.ubahDonatur(idDonatur);
                    break;
                case "2":
                    Menu.MenuBantuan(idDonatur);
                    break;
                case "3":
                    Bantuan.lihatHistoriBantuanDonatur(idDonatur);
                    break;
                case "4":
                    Tampilan.cls();
                    return;
                default:
                    Tampilan.cls();
                    Tampilan.notif("Pilihan tidak valid, silakan coba lagi.");
            }
        }
    }

    // Menu Donasi
    public static void MenuBantuan(int idDonatur) throws IOException {
        Tampilan.cls();
        while (true) {
            Tampilan.menu("Menu Bantuan", menuBantuan); 
            String pilihan = input.readLine();
            Tampilan.cls();

            switch (pilihan) {
                case "1":
                    BantuanBarang.tambahBantuanBarang(idDonatur);
                    break;
                case "2":
                    BantuanUang.tambahBantuanUang(idDonatur);
                    break;
                case "3":
                    Tampilan.cls();
                    return;
                default:
                    Tampilan.cls();
                    Tampilan.notif("Pilihan tidak valid, silakan coba lagi.");
            }
        }
    }

    // Menu Donatur untuk admin
    public static void menuDonatur(String[] menuDonatur) throws IOException {
        while (true) {
            Tampilan.menu("Menu Donatur", menuDonatur);
            String pilihanDonatur = input.readLine();
            Tampilan.garis();

            switch (pilihanDonatur) {
                case "1":
                    Donatur.lihatDonatur();
                    break;
                case "2":
                    Tampilan.cls();
                    return;
                default:
                    Tampilan.cls();
                    Tampilan.notif("Pilihan tidak valid, silakan coba lagi.");
            }
        }
    }

    public static void menuAnak(String[] menuAnak) throws IOException {
        while (true) {
            Tampilan.menu("Menu Anak", menuAnak);
            String pilihan = input.readLine();
            Tampilan.cls();
    
            Anak anak = new Anak();
    
            switch (pilihan) {
                case "1":
                    anak.create();
                    break;
                case "2":
                    Tampilan.cls();
                    anak.read();
                    break;
                case "3":
                    anak.update();
                    break;
                case "4":
                    anak.delete();
                    break;
                case "5":
                    anak.lihatHistoriAnak();
                    break;
                case "6":
                    return;
                default:
                    Tampilan.cls();
                    Tampilan.notif("Pilihan tidak valid, silakan coba lagi.");
            }
        }
    }
    

    // Menu Panti
    public static void menuPanti(String[] menuPanti) throws IOException {
        while (true) {
            Tampilan.menu("Menu Panti Asuhan", menuPanti);
            String pilihanPanti = input.readLine();
            Tampilan.garis();
            PantiAsuhan panti = new PantiAsuhan();

            switch (pilihanPanti) {
                case "1":
                    panti.tambah();
                    break;
                case "2":
                    panti.lihat();
                    break;
                case "3":
                    panti.ubah();
                    break;
                case "4":
                    Tampilan.cls();
                    return;
                default:
                    Tampilan.cls();
                    Tampilan.notif("Pilihan tidak valid, silakan coba lagi.");
            }
        }
    }
}
