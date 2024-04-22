import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// Default akses
class Menu {

    // List menu
    static final String[] menuAdmin            = {"Data Anak"          , "Data Donatur"         , "Data Fasilitas"      , "Exit"};
    static final String[] menuDonatur          = {"Daftar Data Donatur", "Ubah Data Donatur"    , "Hapus Data Donatur"  , "Exit"};
    static final String[] menuAnak             = {"Tambah Data Anak"   , "Daftar Data Anak"     , "Ubah Data Anak"      , "Hapus Data Anak"     , "Exit"};
    static final String[] menuUser             = {"Profil"             , "Donasi"               , "Lihat Riwayat Donasi", "Exit"}; 
    static final String[] menuFasilitas        = {"Tambah Fasilitas"   , "Daftar Data Fasilitas", "Ubah Data Fasilitas" , "Hapus Data Fasilitas", "Exit"};
    static final String[] menuBantuan          = {"Donasi Barang"      , "Donasi Uang"          , "Exit"};
    static final String[] menuJenisFasilitas   = {"Tempat Tinggal"     , "Pendidikan"           , "Kesehatan"          , "Rekreasi"            , "Transportasi"};

    // Deklarasi objek yang akan digunakan
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static Tampilan look = new Tampilan();
    static Fasilitas fasilitas = new Fasilitas();
    static Anak anak = new Anak();

    
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
                    anak.tambah();
                    break;
                case "2":
                    anak.lihat();
                    break;
                case "3":
                    anak.ubah();
                    break;
                case "4":
                    anak.hapus();
                    break;
                case "5":
                    look.cls(); 
                    return;
                default:
                    look.cls();
                    look.notif("Pilihan tidak valid, silakan coba lagi.");
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
                    fasilitas.tambah();
                    break;
                case "2":
                    fasilitas.lihat();
                    break;
                case "3":
                    fasilitas.ubah();
                    break;
                case "4":
                    fasilitas.hapus();
                    break;
                case "5":
                    look.cls();
                    return;
                default:
                    look.cls();
                    look.notif("Pilihan tidak valid, silakan coba lagi.");
            }
        }
    }

    public static String jenisFasilitas() throws IOException {
        while (true) {
            look.menu("Jenis Fasilitas", menuJenisFasilitas);
            String pilihanFasilitas = input.readLine();
            look.garis();

            switch (pilihanFasilitas) {
                case "1":
                    return "Tempat Tinggal";
                case "2":
                    return "Pendidikan";
                case "3":
                    return "Kesehatan";
                case "4":
                    return "Rekreasi";
                case "5":
                    return "Transportasi";
                default:
                    look.cls();
                    look.notif("Pilihan tidak valid, silakan coba lagi.");
            }
        }
        
    }
}

