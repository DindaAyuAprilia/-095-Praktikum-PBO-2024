import java.io.IOException;

public abstract class CRUD {
    protected abstract void tambah() throws IOException;
    protected abstract void lihat();
    protected abstract void ubah() throws IOException;
    protected abstract void hapus() throws IOException;
    
}
