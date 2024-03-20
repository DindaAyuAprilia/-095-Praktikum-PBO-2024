import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static String[] menuMasuk = {"Registrasi", "Login", "Exit"};
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static Tampilan look = new Tampilan();
    static int idDonatur;

    public static void main(String[] args) throws IOException {
        look.cls();
        while (true) {
            look.menu("Selamat Datang", menuMasuk);
            String pilihan = input.readLine();
            look.cls();

            switch (pilihan) {
                case "1":
                    Donatur.tambahDonatur();
                    break;
                case "2":
                    look.notif("L O G I N");
                    System.out.print("Masukkan username(nama): ");
                    String username = input.readLine();
                    System.out.print("Masukkan Password      : ");
                    String password = input.readLine();

                    if (username.equals("dinda") && password.equals("095")){
                        look.notif("Login berhasil, Selamat datang Admin...");
                        Menu.MenuAdmin();
                        break;
                    }

                    int id = Donatur.getIdDonaturByUsernamePassword(username,password);
                    if (id != -1) {
                        idDonatur = id;    
                        look.cls();
                        look.notif("Login berhasil!!");
                        Menu.MenuUser(idDonatur);
                    } 
                    
                    else {
                        look.cls();
                        look.notif("Login gagal. Username/Password salah.");
                    }
                    break;
                case "3":
                    System.out.println("Terima kasih telah menggunakan program.");
                    System.exit(0);
                default:
                    look.notif("Pilihan tidak valid, silahkan coba lagi");
            }
        }
    }

}
