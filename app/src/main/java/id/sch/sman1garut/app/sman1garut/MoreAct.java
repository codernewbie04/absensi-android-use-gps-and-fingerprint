package id.sch.sman1garut.app.sman1garut;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RelativeLayout;

import id.sch.sman1garut.app.sman1garut.utils.move;

public class MoreAct extends AppCompatActivity {
    RelativeLayout logout,absensi;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        absensi = (RelativeLayout) findViewById(R.id.absensi);
        logout = (RelativeLayout) findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = mContext.getSharedPreferences("UserData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear().commit();
                    move.moveActivity(mContext,GetStartedAct.class);
            }
        });
        absensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SharedPreferences sharedPreferences = mContext.getSharedPreferences("UserData", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.clear().commit();
                move.moveActivity(mContext,MapsAct.class);
            }
        });
    }
}
