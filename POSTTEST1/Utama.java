// Import library
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Utama {

    // List menu
    static String[] menuAdmin     = {"Data Anak"          , "Data Donatur"         , "Data Fasilitas"     , "Exit"};
    static String[] menuAnak      = {"Tambah Data Anak"   , "Daftar Data Anak"     , "Ubah Data Anak"     , "Hapus Data Anak"     , "Exit"};
    static String[] menuDonatur   = {"Tambah Data Donatur", "Daftar Data Donatur"  , "Ubah Data Donatur"  , "Hapus Data Donatur"  , "Exit"};
    static String[] menuFasilitas = {"Tambah Fasilitas"   , "Daftar Data Fasilitas", "Ubah Data Fasilitas", "Hapus Data Fasilitas", "Exit"};

    // Deklarasi objek yang akan di gunakan
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static Tampilan look = new Tampilan();
    
    public static void main(String[] args) throws IOException {
        

        // Menu admin
        while (true) {
            look.cls();
            look.menu("Menu Admin", menuAdmin);
            String pilihan = input.readLine();
            look.cls();

            if (pilihan.equals("1")) {
                Anak.menuAnak(menuAnak);
            } 
            
            else if (pilihan.equals("2")) {
                Donatur.menuDonatur(menuDonatur);
            } 
            
            else if (pilihan.equals("3")) {
                Fasilitas.menuFasilitas(menuFasilitas);
            } 
            
            else if (pilihan.equals("4")) {
                // Keluar dari program
                System.exit(0);
            }
        }
    }


}
