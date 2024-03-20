import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// Default akses
class Menu {

    // List menu
    static String[] menuAdmin     = {"Data Anak"          , "Data Donatur"         , "Data Fasilitas"      , "Exit"};
    static String[] menuDonatur   = {"Daftar Data Donatur", "Ubah Data Donatur"    , "Hapus Data Donatur"  , "Exit"};
    static String[] menuAnak      = {"Tambah Data Anak"   , "Daftar Data Anak"     , "Ubah Data Anak"      , "Hapus Data Anak"     , "Exit"};
    static String[] menuUser      = {"Profil"             , "Donasi"               , "Lihat Riwayat Donasi", "Exit"}; 
    static String[] menuFasilitas = {"Tambah Fasilitas"   , "Daftar Data Fasilitas", "Ubah Data Fasilitas" , "Hapus Data Fasilitas", "Exit"};
    static String[] menuBantuan   = {"Donasi Barang", "Donasi Uang", "Exit"};

    // Deklarasi objek yang akan digunakan
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static Tampilan look = new Tampilan();
    
    // Menu admin
    public static void MenuAdmin() throws IOException {
        while (true) {
            look.menu("Menu Admin", menuAdmin);
            String pilihan = input.readLine();
            look.cls();

            switch (pilihan) {
                case "1":
                    menuAnak(menuAnak);
                    break;
                case "2":
                    menuDonatur(menuDonatur);
                    break;
                case "3":
                    menuFasilitas(menuFasilitas);
                    break;
                case "4":
                    look.cls();
                    return;
                default:
                look.cls();
                look.notif("Pilihan tidak valid, silakan coba lagi.");
                break;
            }
        }
    }

    // Menu Donatur saat masuk
    public static void MenuUser(int idDonatur) throws IOException {
        while (true) {
            look.menu("Menu Donatur", menuUser); 
            String pilihan = input.readLine();
            look.cls();

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
                    look.cls();
                    return;
                default:
                look.cls();
                look.notif("Pilihan tidak valid, silakan coba lagi.");
                break;
            }
        }
    }

    // Menu Donasi
    public static void MenuBantuan(int idDonatur) throws IOException {
        look.cls();
        while (true) {
            look.menu("Menu Bantuan", menuBantuan); 
            String pilihan = input.readLine();
            look.cls();

            switch (pilihan) {
                case "1":
                    BantuanBarang.tambahBantuanBarang(idDonatur);
                    break;
                case "2":
                    BantuanUang.tambahBantuanUang(idDonatur);
                    break;
                case "3":
                    look.cls();
                    return;
                default:
                look.cls();
                look.notif("Pilihan tidak valid, silakan coba lagi.");
                break;
            }
        }
    }

    // Menu Donatur untuk admin
    public static void menuDonatur(String[] menuDonatur) throws IOException {
        while (true) {
            look.menu("Menu Donatur", menuDonatur);
            String pilihanDonatur = input.readLine();
            look.garis();

            switch (pilihanDonatur) {
                case "1":
                    Donatur.lihatDonatur();
                    break;
                case "2":
                    Donatur.ubahDonatur();
                    break;
                case "3":
                    Donatur.hapusDonatur();
                    break;
                case "4":
                    look.cls();
                    return;
                default:
                    look.cls();
                    look.notif("Pilihan tidak valid, silakan coba lagi.");
                    break;
            }
        }
    }

    // Menu Anak
    public static void menuAnak(String[] menuAnak) throws IOException {
        while (true) {
            look.menu("Menu Anak", menuAnak);
            String pilihanAnak = input.readLine();
            look.garis();

            switch (pilihanAnak) {
                case "1":
                    Anak.tambahAnak();
                    break;
                case "2":
                    Anak.lihatAnak();
                    break;
                case "3":
                    Anak.ubahAnak();
                    break;
                case "4":
                    Anak.hapusAnak();
                    break;
                case "5":
                    look.cls(); 
                    return;
                default:
                    look.cls();
                    look.notif("Pilihan tidak valid, silakan coba lagi.");
                    break;
            }
        }
    }

    // Menu Fasilitas
    public static void menuFasilitas(String[] menuFasilitas) throws IOException {
        while (true) {
            look.menu("Menu Fasilitas", menuFasilitas);
            String pilihanFasilitas = input.readLine();
            look.garis();

            switch (pilihanFasilitas) {
                case "1":
                    Fasilitas.tambahFasilitas();
                    break;
                case "2":
                Fasilitas.lihatFasilitas();
                    look.garis();
                    break;
                case "3":
                Fasilitas.ubahFasilitas();
                    break;
                case "4":
                Fasilitas.hapusFasilitas();
                    break;
                case "5":
                    look.cls();
                    return;
                default:
                    look.cls();
                    look.notif("Pilihan tidak valid, silakan coba lagi.");
                    break;
            }
        }
    }
}

