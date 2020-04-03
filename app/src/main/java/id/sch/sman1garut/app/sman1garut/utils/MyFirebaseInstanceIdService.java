package id.sch.sman1garut.app.sman1garut.utils;


import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        SendTokenToServer(FirebaseInstanceId.getInstance().getToken());
    }

    private void SendTokenToServer(String token) {
        Log.d("Token", String.valueOf(token));
    }
}
