package id.sch.sman1garut.app.sman1garut;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import id.sch.sman1garut.app.sman1garut.utils.move;

public class SuccessDaftarAct extends AppCompatActivity {
    ImageView bg_success;
    TextView tx_success, subtext;
    Button btn_explore;
    Animation ttb, btt;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        setContentView(R.layout.activity_success_daftar);
        bg_success = findViewById(R.id.img_success);
        tx_success = findViewById(R.id.txt_success);
        subtext = findViewById(R.id.subtext);
        btn_explore = findViewById(R.id.btn_explore);

        ttb = AnimationUtils.loadAnimation(this, R.anim.ttb_animation);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt_animation);

        bg_success.startAnimation(ttb);
        tx_success.startAnimation(ttb);
        subtext.startAnimation(btt);
        btn_explore.startAnimation(btt);


        btn_explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,DashboardAct.class);
                startActivity(i);
                finish();
            }
        });

    }
}
