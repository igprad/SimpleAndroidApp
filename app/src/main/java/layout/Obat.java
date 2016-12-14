package layout;

/**
 * Created by Aldi on 29/11/2016.
 */

public class Obat {
    private String nama;
    private String jenis;
    private String deskripsi;
    private double harga;

    public Obat(){}
    public Obat(String nama,String jenis,double harga,String deskripsi){
        setNama(nama);
        setJenis(jenis);
        setDeskripsi(deskripsi);
        setHarga(harga);
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }
}
