///////////////////////////////////////////////////////////////////////////////////////
/*                         Library Yang Di perlukan                                  */
///////////////////////////////////////////////////////////////////////////////////////
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
///////////////////////////////////////////////////////////////////////////////////////

public class BantuanUang extends Bantuan {

///////////////////////////////////////////////////////////////////////////////////////
/*                       Deklarasi Objek Yang di perlukan                            */
///////////////////////////////////////////////////////////////////////////////////////
    private int jumlahUang;

    // Deklarasi objek yang akan digunakan
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static Tampilan look = new Tampilan();
    static ErrorHandling error = new ErrorHandling();

    // ArrayList untuk menyimpan data bantuan
    static ArrayList<Bantuan> daftarBantuan = new ArrayList<>();

    public BantuanUang(int idDonatur, int jumlahUang) {
        super(idDonatur, new Date());
        this.jumlahUang = jumlahUang;
    }

    public int getJumlahUang() {
        return jumlahUang;
    }

///////////////////////////////////////////////////////////////////////////////////////
/*                            Method Yang Diperlukan                                 */
///////////////////////////////////////////////////////////////////////////////////////
//  [1] Mehode tambah bantuan uang
    public static void tambahBantuanUang(int idDonatur) throws IOException {
        look.cls();
        look.notif("Detail Bantuan Uang");
        int jumlah = error.harusAngka("Jumlah Uang: ");
        look.garis();
        Bantuan.tambahBantuan(idDonatur,jumlah);

        look.cls();
        look.notif("Data Bantuan Barang Berhasil ditambahkan!");
    }

///////////////////////////////////////////////////////////////////////////////////////
//  [2] Method untuk melihat histori bantuan dari donatur tertentu
    @Override
    public void detailInformasiDonasi() {
        super.detailInformasiDonasi();
        System.out.println("Jumlah Uang : " + jumlahUang);
    }

}
