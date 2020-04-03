package id.sch.sman1garut.app.sman1garut;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import es.dmoral.toasty.Toasty;
import id.sch.sman1garut.app.sman1garut.SPreferenced.PrefManager;
import id.sch.sman1garut.app.sman1garut.api.client;
import id.sch.sman1garut.app.sman1garut.models.model_kegiatan.PecahDataKegiatan;
import id.sch.sman1garut.app.sman1garut.models.model_kegiatan.ResponseKegiatan;
import id.sch.sman1garut.app.sman1garut.models.models_users.ResponseLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailKegiatanAct extends AppCompatActivity {
    PecahDataKegiatan dataKegiatan;
    Context mContext;
    ProgressDialog pdLoading;
    int id;
    TextView judul, deskripsi, author_tanggal, day, month, waktu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kegiatan);
        mContext=this;
        Intent i = getIntent();
        id = i.getIntExtra("id", 0);
        Toast.makeText(this, "Val is " + id, Toast.LENGTH_SHORT).show();
        pdLoading = new ProgressDialog(DetailKegiatanAct.this);
        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        pdLoading.show();
        initStart();
    }

    private void initStart() {
        initUI();
        initValue();
    }

    private void initUI() {
        judul = findViewById(R.id.judul);
        deskripsi = findViewById(R.id.deskripsi);
        author_tanggal = findViewById(R.id.author_tanggal);
        day = findViewById(R.id.day);
        month = findViewById(R.id.month);
        waktu = findViewById(R.id.waktu);
    }

    private void initValue() {
        Call<ResponseKegiatan> user=client.getApi().detailKegiatan(id);
        user.enqueue(new Callback<ResponseKegiatan>() {
            @Override
            public void onResponse(Call<ResponseKegiatan> call, Response<ResponseKegiatan> response) {
                if (response.body().getStatus()){
                    dataKegiatan =response.body().getData();
                    String dataJudul = dataKegiatan.getJudul();
                    judul.setText(dataJudul);

                    String dataDeskripsi = dataKegiatan.getDeskripsi();
                    deskripsi.setText(dataDeskripsi);

                    String dataAthorTanggal = "- "+dataKegiatan.getAuthor() + ", " + dataKegiatan.getTanggal();
                    author_tanggal.setText(dataAthorTanggal);

                    int dataDay = dataKegiatan.getDay();
                    day.setText(String.valueOf(dataDay));

                    String dataMonth = dataKegiatan.getMonth();
                    month.setText(dataMonth);

                    String dataWaktu =dataKegiatan.getWaktu();
                    waktu.setText(dataWaktu);


                    pdLoading.dismiss();
                }else{
                    pdLoading.dismiss();
                    Toasty.error(mContext,"Gagal",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseKegiatan> call, Throwable t) {
                pdLoading.dismiss();
                Toasty.error(mContext,"Gagal terhubung ke server!!",Toast.LENGTH_LONG).show();
            }
        });
    }
}
