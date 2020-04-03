package id.sch.sman1garut.app.sman1garut.SPreferenced;

import android.content.Context;
import android.content.SharedPreferences;

import id.sch.sman1garut.app.sman1garut.models.models_users.DataUser;

public class PrefManager {

    Context context;

    public PrefManager(Context context) {
        this.context = context;
    }

    public void saveUserData(DataUser du) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("UserId",du.getUser_id());
        editor.putString("Username",du.getUsername());
        editor.putString("Nama",du.getNama());
        editor.putString("Email",du.getEmail());
        editor.putString("Level",du.getLevel());
        editor.putFloat("Lat",du.getLat());
        editor.putFloat("Lng",du.getLng());
        editor.putString("Photo",du.getPhoto());
        editor.putString("Kelas",du.getKelas());
        editor.putInt("JoinDate",du.getJoindate());
        editor.putString("Token",du.getToken());
        editor.commit();
    }
    public  String getUserId(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("UserId", "");
    }
    public  String getUsername(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Username", "");
    }

    public  String getToken(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Token", "");
    }

    public  String getNama(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Nama", "");
    }

    public  String getPhoto(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Photo", "");
    }

    public  String getLevel(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Level", "");
    }

    public  String getKelas(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Kelas", "");
    }

    public String getEmail() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Email", "");
    }

    public boolean isUserLogedOut() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE);
        boolean isUsernameEmpty = sharedPreferences.getString("Username", "").isEmpty();
        boolean isTokenEmpty = sharedPreferences.getString("Token", "").isEmpty();
        return isUsernameEmpty || isTokenEmpty;
    }
}
