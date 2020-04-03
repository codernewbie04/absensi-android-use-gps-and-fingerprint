package id.sch.sman1garut.app.sman1garut;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.squareup.picasso.Picasso;

import java.io.File;

import es.dmoral.toasty.Toasty;
import id.sch.sman1garut.app.sman1garut.SPreferenced.PrefManager;
import id.sch.sman1garut.app.sman1garut.api.client;
import id.sch.sman1garut.app.sman1garut.api.request;
import id.sch.sman1garut.app.sman1garut.models.models_users.DataUser;
import id.sch.sman1garut.app.sman1garut.models.models_users.ResponseRegister;
import id.sch.sman1garut.app.sman1garut.models.models_users.UploadImage;
import id.sch.sman1garut.app.sman1garut.utils.FilePath;
import id.sch.sman1garut.app.sman1garut.utils.FileUtils;
import id.sch.sman1garut.app.sman1garut.utils.move;
import id.sch.sman1garut.app.sman1garut.utils.validate;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Daftar2Act extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    LinearLayout btn_back;
    Button btn_daftar, add_foto;
    EditText et_nama;
    Spinner sp_kelas;
    String username, email, password, kelas;
    Integer photo_max = 1;
    Uri photo_location;
    MultipartBody.Part body;
    ImageView pic_photo_register_user;
    private DataUser userData;
    private ProgressDialog pDialog;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar2);
        mContext=this;
        startInit();

        sp_kelas = findViewById(R.id.kelas);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.kelas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_kelas.setAdapter(adapter);
        sp_kelas.setOnItemSelectedListener(this);
    }

    public void startInit() {
        initUI();
        initEvent();
    }

    public void initUI() {
        pic_photo_register_user = findViewById(R.id.pic_photo_register_user);
        et_nama = findViewById(R.id.et_nama);
        Intent i = getIntent();
        username = i.getStringExtra("username");
        email = i.getStringExtra("email");
        password = i.getStringExtra("password");
        btn_daftar = findViewById(R.id.btn_daftar);
        btn_back = findViewById(R.id.btn_back);
        add_foto = findViewById(R.id.add_foto);
    }

    public void initEvent() {
        btn_daftar.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        add_foto.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        kelas = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_daftar:
                if(validate_daftar())
                    daftar();
                break;
            case R.id.add_foto:
                findFoto();
                break;
            case R.id.btn_back:
                onBackPressed();
                break;
        }
    }

    private void findFoto() {
        Intent pic = new Intent();
        pic.setType("image/*");
        pic.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(pic, photo_max);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == photo_max && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            //Toast.makeText(mContext,photo_location.toString() , Toast.LENGTH_SHORT).show();
            photo_location = data.getData();
            Picasso.with(this).load(photo_location).centerCrop().fit().into(pic_photo_register_user);
        }

    }

    private boolean validate_daftar() {
        Boolean status = true;
        if(!validate.cek(et_nama)){
            if(kelas.equals("Pilih Kelas")){
                status = false;
                Toasty.error(mContext, "Kelas harus diisi", Toast.LENGTH_LONG).show();
            }
        } else {
            status = false;
        }
        return  status;
    }

    private void daftar() {
        //uploadFile(photo_location);
        pDialog = new ProgressDialog(this);
        // pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setMessage("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
        RequestBody USERNAME =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, username);

        RequestBody EMAIL =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, email);

        RequestBody PASSWORD =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, password);

        RequestBody NAMA =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, et_nama.getText().toString());

        RequestBody KELAS =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, kelas);

        RequestBody TOKEN =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, FirebaseInstanceId.getInstance().getToken());

        if(photo_location != null){
            Uri uri = photo_location;
            String selectedFilePath = FilePath.getPath(this, uri);
            File file = FileUtils.getFile(this, photo_location);
            RequestBody requestFile =
                    RequestBody.create(
                            MediaType.parse(getContentResolver().getType(photo_location)),
                            file
                    );
            body =
                    MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        } else {
            body = null;
        }


        Call<ResponseRegister> register;
        register = client.getApi().userDaftar(USERNAME,
                EMAIL,
                PASSWORD,
                NAMA,
                KELAS,
                TOKEN,
                body
                );

        register.enqueue(new Callback<ResponseRegister>() {

            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                pDialog.hide();
                if (response.isSuccessful()){
                    if (response.body().getStatus()) {
                        userData=response.body().getData();
                        new PrefManager(mContext).saveUserData(userData);
                        Intent goto_success = new Intent(Daftar2Act.this, SuccessDaftarAct.class);
                        startActivity(goto_success);
                        finish();
                    }else {
                        Toasty.error(mContext,response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toasty.error(mContext,"Gagal dibuat", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                pDialog.hide();
                Toasty.error(mContext,t.toString(), Toast.LENGTH_LONG).show();
            }
        });


    }

//    private void uploadFile(Uri fileUri) {
//
//        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
//        // use the FileUtils to get the actual file by uri
//        File file = FileUtils.getFile(this, fileUri);
//
//        // create RequestBody instance from file
//        RequestBody requestFile =
//                RequestBody.create(
//                        MediaType.parse(getContentResolver().getType(fileUri)),
//                        file
//                );
//
//        // MultipartBody.Part is used to send also the actual file name
//        MultipartBody.Part body =
//                MultipartBody.Part.createFormData("image", file.getName(), requestFile);
//
//        // add another part within the multipart request
//        String descriptionString = "hello, this is description speaking";
//        RequestBody description =
//                RequestBody.create(
//                        okhttp3.MultipartBody.FORM, descriptionString);
//
//        // finally, execute the request
//        Call<UploadImage> call = client.getApi().uploadImage(description, body);
//        call.enqueue(new Callback<UploadImage>() {
//            @Override
//            public void onResponse(Call<UploadImage> call,
//                                   Response<UploadImage> response) {
//                Toast.makeText(Daftar2Act.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
//                //Log.e("Uploadsss:", response.body().getMsg());
//            }
//
//            @Override
//            public void onFailure(Call<UploadImage> call, Throwable t) {
//                Log.e("Uploadsss:", t.getMessage());
//            }
//        });
//    }
}
