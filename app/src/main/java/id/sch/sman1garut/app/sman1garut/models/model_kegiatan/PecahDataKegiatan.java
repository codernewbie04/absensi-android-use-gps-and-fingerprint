package id.sch.sman1garut.app.sman1garut.models.model_kegiatan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PecahDataKegiatan {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("judul")
    @Expose
    private String judul;

    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;

    @SerializedName("tempat")
    @Expose
    private String tempat;

    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    @SerializedName("mulai")
    @Expose
    private String mulai;

    @SerializedName("selesai")
    @Expose
    private String selesai;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("day")
    @Expose
    private int day;

    @SerializedName("month")
    @Expose
    private String month;

    @SerializedName("waktu")
    @Expose
    private String waktu;

    @SerializedName("tanggal")
    @Expose
    private String tanggal;

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getAuthor() {
        return author;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getMulai() {
        return mulai;
    }

    public String getSelesai() {
        return selesai;
    }

    public String getTanggal() {
        return tanggal;
    }

    public int getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getTempat() {
        return tempat;
    }
}
