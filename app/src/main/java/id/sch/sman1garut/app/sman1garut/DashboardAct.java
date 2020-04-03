package id.sch.sman1garut.app.sman1garut;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import id.sch.sman1garut.app.sman1garut.SPreferenced.PrefManager;
import id.sch.sman1garut.app.sman1garut.utils.move;

public class DashboardAct extends AppCompatActivity  implements View.OnClickListener{
    LinearLayout btn_pengumuman, btn_nilai, btn_kegiatan, btn_tugas, btn_berita, btn_more;
    TextView display_name, display_kelas;
    ImageView display_pict;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mContext=this;
        startInit();
    }

    private void startInit() {
        initUI();
        initValue();
        initEvent();
    }

    private void initUI() {
        display_name        = findViewById(R.id.display_name);
        display_pict        = findViewById(R.id.myphoto);
        display_kelas       = findViewById(R.id.kelas);
        btn_pengumuman      = findViewById(R.id.btn_pengumuman);
        btn_nilai           = findViewById(R.id.btn_nilai);
        btn_kegiatan        = findViewById(R.id.btn_kegiatan);
        btn_tugas           = findViewById(R.id.btn_tugas);
        btn_berita          = findViewById(R.id.btn_berita);
        btn_more            = findViewById(R.id.btn_more);
    }

    private void initValue() {
        //set Display Name
        String nama = new PrefManager(mContext).getNama();
        display_name.setText(nama);
        //set Foto Profile
        String url = new PrefManager(mContext).getPhoto();
        Picasso.with(this).load(url).into(display_pict);
        //set Kelas
        String kelas = new PrefManager(mContext).getKelas();
        display_kelas.setText(kelas);

    }

    private void initEvent() {
        btn_pengumuman.setOnClickListener(this);
        display_name.setOnClickListener(this);
        btn_pengumuman.setOnClickListener(this);
        btn_nilai.setOnClickListener(this);
        btn_kegiatan.setOnClickListener(this);
        btn_tugas.setOnClickListener(this);
        btn_berita.setOnClickListener(this);
        btn_more.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_pengumuman:
                    move.moveActivity(mContext, PengumumanAct.class);
                break;
            case R.id.btn_nilai:
                    move.moveActivity(mContext, NilaiAct.class);
                break;
            case R.id.btn_kegiatan:
                    move.moveActivity(mContext, KegiatanAct.class);
                break;
            case R.id.btn_tugas:
                move.moveActivity(mContext, TugasAct.class);
                break;
            case R.id.btn_berita:
                move.moveActivity(mContext, BeritaAct.class);
                break;
            case R.id.btn_more:
                move.moveActivity(mContext, MoreAct.class);
//                    SharedPreferences sharedPreferences = mContext.getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.clear().commit();
//                    move.moveActivity(mContext,GetStartedAct.class);
                break;
        }
    }
}
