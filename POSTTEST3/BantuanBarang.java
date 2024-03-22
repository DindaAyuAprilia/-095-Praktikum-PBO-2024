///////////////////////////////////////////////////////////////////////////////////////
/*                         Library Yang Di perlukan                                  */
///////////////////////////////////////////////////////////////////////////////////////
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
///////////////////////////////////////////////////////////////////////////////////////

public class BantuanBarang extends Bantuan {

///////////////////////////////////////////////////////////////////////////////////////
/*                       Deklarasi Objek Yang di perlukan                            */
///////////////////////////////////////////////////////////////////////////////////////
    private String jenisBarang;
    private int kuantitas;

    // Deklarasi objek yang akan digunakan
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static Tampilan look = new Tampilan();
    static ErrorHandling error = new ErrorHandling();

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

///////////////////////////////////////////////////////////////////////////////////////
/*                            Method Yang Diperlukan                                 */
///////////////////////////////////////////////////////////////////////////////////////
//  [1] Method untuk menambah data bantuan barang
    public static void tambahBantuanBarang(int idDonatur) throws IOException {
        look.cls();
        look.notif("Detail Bantuan Barang");
        System.out.print("Jenis Barang : ");
        String jenisBarang = input.readLine();
        int kuantitas = error.harusAngka("Kuantitas    : ");
        look.garis();
        Bantuan.tambahBantuanBarang(idDonatur, jenisBarang, kuantitas);

        look.cls();
        look.notif("Data Bantuan Barang Berhasil ditambahkan!");
    }

    
    
}
