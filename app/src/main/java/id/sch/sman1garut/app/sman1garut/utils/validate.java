package id.sch.sman1garut.app.sman1garut.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import es.dmoral.toasty.Toasty;
import id.sch.sman1garut.app.sman1garut.api.client;
import id.sch.sman1garut.app.sman1garut.models.models_users.ResponseLogin;
import id.sch.sman1garut.app.sman1garut.models.models_users.ResponseUnique;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class validate {

  public static boolean cek(EditText et) {
    View focusView = null;
    Boolean cancel=false;
    if (TextUtils.isEmpty(et.getText().toString().trim())) {
      et.setError("Inputan Harus Di Isi");
      focusView = et;
      cancel = true;
    }
    if (cancel) {
      focusView.requestFocus();
    }
    return cancel;
  }

  public static boolean cekEmail(EditText email){
    View focusView = null;
    Boolean cancel=false;
    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
      email.setError("Email tidak valid");
      focusView = email;
      cancel = true;
    }
    if (cancel) {
      focusView.requestFocus();
    }
    return cancel;
  }

  public static boolean strlen6Chara(EditText et){
    View focusView = null;
    Boolean cancel=false;
    if (et.getText().toString().length() < 6) {
      et.setError("Data terlalu pendek!");
      focusView = et;
      cancel = true;
    }
    if (cancel) {
      focusView.requestFocus();
    }
    return cancel;
  }


  public static boolean cekPassword(EditText et,String Password,String ConfirmPassword){
    View focusView = null;
    Boolean cancel=false;
    if (!Password.equals(ConfirmPassword)) {
      et.setError("Password tidak sama");
      focusView = et;
      cancel = true;
    }
    if (cancel) {
      focusView.requestFocus();
    }
    return cancel;
  }
}

