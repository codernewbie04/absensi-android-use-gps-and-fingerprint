package id.sch.sman1garut.app.sman1garut.models.models_users;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataUser {
    @SerializedName("user_id")
    @Expose
    private Integer user_id;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("level")
    @Expose
    private String level;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("kelas")
    @Expose
    private String kelas;

    @SerializedName("lat")
    @Expose
    private Float lat;

    @SerializedName("lng")
    @Expose
    private Float lng;

    @SerializedName("photo")
    @Expose
    private String photo;

    @SerializedName("joindate")
    @Expose
    private Integer joindate;

    @SerializedName("access_token")
    @Expose
    private String token;


    public Integer getUser_id() {
        return user_id;
    }

    public void setId_user(Integer id_user) {
        this.user_id = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLevel() {
        return level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kls) {
        this.kelas = kls;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }


    public String getPhoto() {return photo; }

    public Integer getJoindate() {return joindate; }

    public void setAlamat(Integer joindate) {
        this.joindate = joindate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

