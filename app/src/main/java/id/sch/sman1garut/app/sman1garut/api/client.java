package id.sch.sman1garut.app.sman1garut.api;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class client {
    //Local = 192.168.100.31/api/
    //private  static  final  String BASE_URL="https://api.codernewbie.com/";
    private  static  final  String BASE_URL="https://api.codernewbie.com/";
    private  static  final  String BASE_URL_IMAGE="http://192.168.100.31/rental-api/upload/avatars/";
    private  static  final  String BASE_URL_IMG="http://192.168.100.31/rental-api/upload/";

    public static request getApi() {
        //Builder Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        request apiService = retrofit.create(request.class);

        return apiService;
    }

    public  static String getBaseUrl(){
        return BASE_URL;
    }
}
