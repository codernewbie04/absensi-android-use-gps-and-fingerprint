package id.sch.sman1garut.app.sman1garut.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.media.Image;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;
import id.sch.sman1garut.app.sman1garut.DashboardAct;
import id.sch.sman1garut.app.sman1garut.MasukAct;
import id.sch.sman1garut.app.sman1garut.SPreferenced.PrefManager;
import id.sch.sman1garut.app.sman1garut.api.client;
import id.sch.sman1garut.app.sman1garut.models.models_absensi.ResponseAbsensi;
import id.sch.sman1garut.app.sman1garut.models.models_users.ResponseLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.String.valueOf;

@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private final SweetAlertDialog pdialog;
    private Context context;

    public FingerprintHandler(Context context, SweetAlertDialog pDialog){

        this.context = context;
        this.pdialog = pDialog;

    }

    public void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject){

        CancellationSignal cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this, null);

    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText(errString+". Fingerprint dibekukan selama 60 detik!")
                .show();

    }

    @Override
    public void onAuthenticationFailed() {

        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText("Gagal Authentikasi! Cobalagi")
                .show();

    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        Toast.makeText((Activity)context, "There was an Auth Error. " + helpString, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        Date currentTime    = Calendar.getInstance().getTime();
        String token        = new PrefManager(context).getToken();
        Call<ResponseAbsensi> user=client.getApi().absensi(token, "absensi-" + valueOf(currentTime));
        user.enqueue(new Callback<ResponseAbsensi>() {
            @Override
            public void onResponse(Call<ResponseAbsensi> call, Response<ResponseAbsensi> response) {
                    if(response.body().getStatus()){
                        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Good job!")
                        .setContentText("Absensi telah tersimpan kedalam database!")
                        .show();
                    } else {
                        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Ooops...")
                        .setContentText(response.body().getMessage())
                        .show();
                    }
            }

            @Override
            public void onFailure(Call<ResponseAbsensi> call, Throwable t) {
                new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Ooops...")
                        .setContentText("Gagal terhubung ke server!")
                        .show();
            }
        });
        pdialog.hide();
//        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
//                .setTitleText("Good job!")
//                .setContentText("Absensi telah tersimpan kedalam database!")
//                .show();
    }

//    private void update(String s, boolean b) {
//
//        TextView paraLabel = (TextView) ((Activity)context).findViewById(R.id.paraLabel);
//        ImageView imageView = (ImageView) ((Activity)context).findViewById(R.id.fingerprintImage);
//
//        paraLabel.setText(s);
//
//        if(b == false){
//
//            paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
//
//        } else {
//
//            paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
//            imageView.setImageResource(R.mipmap.ic_launcher_round);
//
//        }
//
//    }
}