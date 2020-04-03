package id.sch.sman1garut.app.sman1garut;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import id.sch.sman1garut.app.sman1garut.SPreferenced.PrefManager;

public class GetStartedAct extends AppCompatActivity {
  Button btn_masuk, btn_daftar;
  Animation ttb, btt, stb;
  ImageView applogo;
  TextView slogan;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_get_started);
    getWindow().setEnterTransition(null);

    ttb = AnimationUtils.loadAnimation(this, R.anim.ttb_animation);
    btt = AnimationUtils.loadAnimation(this, R.anim.btt_animation);
    stb = AnimationUtils.loadAnimation(this, R.anim.stb_animation);
    applogo = findViewById(R.id.applogo);
    slogan = findViewById(R.id.slogan);
    btn_masuk = findViewById(R.id.btn_masuk);
    btn_daftar = findViewById(R.id.btn_daftar);


    slogan.startAnimation(ttb);
    btn_masuk.startAnimation(btt);
    btn_daftar.startAnimation(btt);




    btn_masuk.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent goto_masuk = new Intent(GetStartedAct.this, MasukAct.class);
        startActivity(goto_masuk);
      }
    });
    btn_daftar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent goto_daftar = new Intent(GetStartedAct.this, DaftarAct.class);
        startActivity(goto_daftar);
      }
    });
  }
}
