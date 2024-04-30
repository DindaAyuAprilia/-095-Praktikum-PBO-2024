import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Final class
public final class ErrorHandling {

    // Deklarasi objek yang akan digunakan
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static Tampilan look = new Tampilan();


    public final int harusAngka(String kalimat){
        while (true) {
            try {
                System.out.print(kalimat);
                int angka = Integer.parseInt(input.readLine());
                return angka;
            } 
            
            // Terdapat huruf pada inputan
            catch (NumberFormatException | IOException e) {
                look.notif("Hanya menerima inputan angka, silahkan coba lagi...");
            }
        }
    }

    public String harusAngkaString(String kalimat){
        int angka = harusAngka(kalimat);
        String angkaString = String.valueOf(angka);
        return angkaString;    
    }

    public int batasan(int min, int max, String kalimat){
        while(true){
            int angka = harusAngka(kalimat);

            // Cek apakah angka memenuhi batasan
            if (angka < min){
                look.notif("Angka yang di masukan terlalu kecil...");
            }

            else if (angka > max){
                look.notif("Angka yang di masukan terlalu besar...");
            }

            else{
                return angka;
            }
        }
    }

    public String harusHuruf(String kalimat) {
        while (true) {
            try {
                System.out.print(kalimat);
                String huruf = input.readLine();

                // Memastikan hanya huruf yang dimasukkan
                if (huruf.matches("[a-zA-Z]+")) { 
                    return huruf;
                } 
                
                else {
                    look.notif("Hanya menerima inputan huruf, silahkan coba lagi...");
                }
            } 
            
            catch (IOException e) {
                look.notif("Terjadi kesalahan saat membaca input, silahkan coba lagi...");
            }
        }
    }
}