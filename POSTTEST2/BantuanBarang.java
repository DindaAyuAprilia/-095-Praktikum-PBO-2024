import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class BantuanBarang extends Bantuan {

    private String jenisBarang;
    private int kuantitas;

    // Deklarasi objek yang akan digunakan
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static Tampilan look = new Tampilan();

    public BantuanBarang(int idDonatur, String jenisBarang, int kuantitas) {
        super(idDonatur, new Date());
        this.jenisBarang = jenisBarang;
        this.kuantitas = kuantitas;
    }

    public String getJenisBarang() {
        return jenisBarang;
    }

    public int getKuantitas() {
        return kuantitas;
    }

    public static void tambahBantuanBarang(int idDonatur) throws IOException {
        look.cls();
        look.notif("Detail Bantuan Barang");
        System.out.print("Jenis Barang : ");
        String jenisBarang = input.readLine();
        System.out.print("Kuantitas    : ");
        int kuantitas = Integer.parseInt(input.readLine());
        look.garis();
        Bantuan.tambahBantuanBarang(idDonatur, jenisBarang, kuantitas);

        look.cls();
        look.notif("Data Bantuan Barang Berhasil ditambahkan!");
    }

    
    
}
