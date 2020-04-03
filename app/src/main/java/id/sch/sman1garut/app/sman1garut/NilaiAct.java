package id.sch.sman1garut.app.sman1garut;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import id.sch.sman1garut.app.sman1garut.adapter.KegiatanAdapter;

public class NilaiAct extends AppCompatActivity {
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private RecyclerView RVPengumpan;
    private KegiatanAdapter mAdapter;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        getSupportActionBar().setTitle("Daftar Nilai");
    }
}
