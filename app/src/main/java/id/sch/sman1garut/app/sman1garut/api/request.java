package id.sch.sman1garut.app.sman1garut.api;

/**
 * Created by Coder Newbie 19/06/19
 */

import id.sch.sman1garut.app.sman1garut.models.model_kegiatan.ResponseKegiatan;
import id.sch.sman1garut.app.sman1garut.models.models_absensi.ResponseAbsensi;
import id.sch.sman1garut.app.sman1garut.models.models_users.ResponseLogin;
import id.sch.sman1garut.app.sman1garut.models.models_users.ResponseRegister;
import id.sch.sman1garut.app.sman1garut.models.models_users.ResponseUnique;
import id.sch.sman1garut.app.sman1garut.models.models_users.UploadImage;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;


public interface request {
    @FormUrlEncoded
    @POST("Authentication/login")
    Call<ResponseLogin> auth(@Field("username") String username,
                             @Field("password") String password,
                             @Field("firebase_token") String firebase_token
    );
    @Multipart
    @POST("Authentication/register")
    Call<ResponseRegister> userDaftar(@Part("username") RequestBody username,
                                        @Part("email") RequestBody email,
                                        @Part("password") RequestBody password,
                                        @Part("nama") RequestBody nama,
                                        @Part("kelas") RequestBody kelas,
                                        @Part("firebase_token") RequestBody firebase_token,
                                        @Part MultipartBody.Part file
                                        );
    @Multipart
    @POST("Authentication/upload_photo")
    Call<UploadImage> uploadImage(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file
    );

    @FormUrlEncoded
    @POST("Authentication/validate_data")
    Call<ResponseUnique> unique(@Field("data") String data,
                                      @Field("type") String type
    );
    @FormUrlEncoded
    @PUT("Api/user")
    Call<ResponseRegister> userUpdate(@Field("ID_USER") String ID_USER,
                                      @Field("NAME") String name,
                                      @Field("NIK") String nik,
                                      @Field("USERNAME") String username,
                                      @Field("EMAIL") String email,
                                      @Field("NO_TELP") String no_telp,
                                      @Field("JENIS_KELAMIN") String jenis_kelamin,
                                      @Field("ALAMAT") String alamat,
                                      @Field("PASSWORD") String password,
                                      @Field("ACTIVATED") Integer activated,
                                      @Field("GROUP_USER") Integer group_user,
                                      @Field("PHOTO") String photo);
    @FormUrlEncoded
    @POST("fitur/kegiatan_detail")
    Call<ResponseKegiatan> detailKegiatan (@Field("id") int id);

    @FormUrlEncoded
    @POST("fitur/absensi")
    Call<ResponseAbsensi> absensi(@Field("accessToken") String token,
                             @Field("absensi") String absensi
    );
}
