package id.sch.sman1garut.app.sman1garut;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import es.dmoral.toasty.Toasty;
import id.sch.sman1garut.app.sman1garut.api.client;
import id.sch.sman1garut.app.sman1garut.models.models_users.ResponseLogin;
import id.sch.sman1garut.app.sman1garut.models.models_users.ResponseUnique;
import id.sch.sman1garut.app.sman1garut.utils.validate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarAct extends AppCompatActivity implements View.OnClickListener{
    LinearLayout btn_back;
    Button btn_lanjut;
    EditText username, email, password;
    AppCompatCheckBox checkbox;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        mContext=this;
        startInit();
    }

    private void startInit() {
        initUI();
        initEvent();
    }

    private void initUI() {
        username = findViewById(R.id.et_username);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        checkbox = (AppCompatCheckBox) findViewById(R.id.checkbox);
        btn_lanjut = findViewById(R.id.btn_lanjut);
        btn_back = findViewById(R.id.btn_back);
    }

    private void initEvent() {
        checkbox.setOnClickListener(this);
        btn_lanjut.setOnClickListener(this);
        btn_back.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.checkbox:
                CheckBox checkBox = (CheckBox)v;
                if(checkBox.isChecked()){
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.btn_lanjut:
                if(validate_daftar()){
                    lanjut();
                }
                break;
            case R.id.btn_back:
                onBackPressed();
                break;
        }
    }

    private boolean validate_daftar() {
        return  (!validate.cek(email)&&!validate.cek(username)&&!validate.cek(password)&&!validate.cekEmail(email)
                &&!validate.strlen6Chara(username)&&!validate.strlen6Chara(password));
    }


    private void lanjut() {
        Call<ResponseUnique> user=client.getApi().unique(email.getText().toString(), "email");
        user.enqueue(new Callback<ResponseUnique>() {
            @Override
            public void onResponse(Call<ResponseUnique> call, Response<ResponseUnique> response) {
                if (response.body().getStatus()){
                    lanjut2();
                } else {
                    View focusView = null;
                    email.setError(response.body().getMessage());
                    focusView = email;
                    focusView.requestFocus();
                }
            }

            @Override
            public void onFailure(Call<ResponseUnique> call, Throwable t) {
                View focusView = null;
                Toasty.error(mContext, t.toString(), Toast.LENGTH_SHORT).show();
                focusView = email;
                focusView.requestFocus();
            }
        });
    }

    private void lanjut2() {
        Call<ResponseUnique> user=client.getApi().unique(username.getText().toString(), "username");
        user.enqueue(new Callback<ResponseUnique>() {
            @Override
            public void onResponse(Call<ResponseUnique> call, Response<ResponseUnique> response) {
                if (response.body().getStatus()){
                    String data_username =  username.getText().toString();
                    String data_email= email.getText().toString();
                    String data_password = password.getText().toString();
                    Intent goto_daftar2 = new Intent(DaftarAct.this, Daftar2Act.class);
                    goto_daftar2.putExtra("username", data_username);
                    goto_daftar2.putExtra("email", data_email);
                    goto_daftar2.putExtra("password", data_password);
                    startActivity(goto_daftar2);
                } else {
                    View focusView = null;
                    username.setError(response.body().getMessage());
                    focusView = username;
                    focusView.requestFocus();
                }

            }

            @Override
            public void onFailure(Call<ResponseUnique> call, Throwable t) {
                View focusView = null;
                Toasty.error(mContext, "Tidak ada koneksi!", Toast.LENGTH_SHORT).show();
                focusView = username;
                focusView.requestFocus();
            }
        });
    }
}
