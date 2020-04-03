package id.sch.sman1garut.app.sman1garut;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import id.sch.sman1garut.app.sman1garut.SPreferenced.PrefManager;

public class SplashAct extends AppCompatActivity {
    Animation app_splash, btt;
    ImageView app_logo;
    TextView app_subtitle;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext=this;
        app_splash = AnimationUtils.loadAnimation(this, R.anim.sta_animation);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt_animation);

        // load element
        app_logo = findViewById(R.id.applogo);
        app_subtitle = findViewById(R.id.copyrigh);

        // run animation
        app_logo.startAnimation(app_splash);
        app_subtitle.startAnimation(btt);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!new PrefManager(mContext).isUserLogedOut()){
                    Intent goto_dashboard = new Intent(SplashAct.this, DashboardAct.class);
                    startActivity(goto_dashboard);
                    finish();
                } else {
                    Intent goto_getstarted = new Intent(SplashAct.this, GetStartedAct.class);
                    ActivityOptionsCompat option = ActivityOptionsCompat
                            .makeSceneTransitionAnimation(SplashAct.this, app_logo, ViewCompat.getTransitionName(app_logo));
                    startActivity(goto_getstarted, option.toBundle());
                    finish();
                }
            }
        }, 2000);
    }
}
