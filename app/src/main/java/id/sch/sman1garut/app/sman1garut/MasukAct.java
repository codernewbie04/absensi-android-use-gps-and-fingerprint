package id.sch.sman1garut.app.sman1garut;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.pixplicity.easyprefs.library.Prefs;

import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;
import id.sch.sman1garut.app.sman1garut.SPreferenced.PrefManager;

import id.sch.sman1garut.app.sman1garut.api.client;
import id.sch.sman1garut.app.sman1garut.models.models_users.DataUser;
import id.sch.sman1garut.app.sman1garut.models.models_users.ResponseLogin;
import id.sch.sman1garut.app.sman1garut.utils.move;
import id.sch.sman1garut.app.sman1garut.utils.validate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MasukAct extends AppCompatActivity implements View.OnClickListener {
  TextView buat_akun, btn_masuk;
  EditText et_username, et_password;
  private Context mContext;

  //declate variable
  private DataUser userData;

  //declare sweet alert
  //   private SweetAlertDialog pDialog;
  private ProgressDialog pDialog;

  //    SharedPreferences prefs = getSharedPreferences("userdata", MODE_PRIVATE);
//    String restoredText = prefs.getString("text", null);
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_masuk);
    mContext=this;
    startInit();
  }

  public void startInit() {
    initUI();
    initEvent();
  }

  public void initUI() {
    et_username = findViewById(R.id.et_username);
    et_password = findViewById(R.id.et_password);
    btn_masuk   = findViewById(R.id.btn_masuk);
    buat_akun   = findViewById(R.id.buat_akun);
  }


  public void initEvent() {
    btn_masuk.setOnClickListener(this);
    buat_akun.setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()){
      case R.id.btn_masuk:
        if (validate_login())
          login();
        break;

      case R.id.buat_akun:
        move.moveActivity(mContext,DaftarAct.class);
        break;
    }
  }

  public boolean validate_login(){
    return (!validate.cek(et_username)&&!validate.cek(et_password));

  }

  public void login(){
    pDialog = new ProgressDialog(this);
    //  pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
    pDialog.setMessage("Loading");
    pDialog.setCancelable(false);
    // pDialog.setIndeterminate(false);
    pDialog.show();

    Call<ResponseLogin> user=client.getApi().auth(et_username.getText().toString(),et_password.getText().toString(), FirebaseInstanceId.getInstance().getToken());
    user.enqueue(new Callback<ResponseLogin>() {
      @Override
      public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
        pDialog.hide();
        if (response.body().getStatus()){
          userData=response.body().getData();
          new PrefManager(mContext).saveUserData(userData);
          Intent goto_dashboard = new Intent(MasukAct.this, DashboardAct.class);
          startActivity(goto_dashboard);
          finish();
        }else{
          Toasty.error(mContext,response.body().getMessage(),Toast.LENGTH_LONG).show();
        }

      }

      @Override
      public void onFailure(Call<ResponseLogin> call, Throwable t) {
        pDialog.hide();
        Toasty.error(mContext,"Gagal terhubung ke server!!",Toast.LENGTH_LONG).show();
        if (pDialog.isShowing())
          pDialog.dismiss();
      }
    });

  }

}
