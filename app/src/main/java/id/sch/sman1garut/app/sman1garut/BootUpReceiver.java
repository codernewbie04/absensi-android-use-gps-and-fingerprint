package id.sch.sman1garut.app.sman1garut;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import java.util.List;

import id.sch.sman1garut.app.sman1garut.utils.MyFirebaseMessagingService;

public class BootUpReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
      Intent i = new Intent(context, MyFirebaseMessagingService.class);
      i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      context.startActivity(i);
    }

    try {
      Intent intent2= new Intent();
      if ("xiaomi".equalsIgnoreCase(android.os.Build.MANUFACTURER)) {
        intent2.setComponent(new ComponentName("com.miui.securitycenter",
                "com.miui.permcenter.autostart.AutoStartManagementActivity"));
      }

      // context is your Context
      List<ResolveInfo> list = context.getPackageManager()
              .queryIntentActivities(intent2, PackageManager.MATCH_DEFAULT_ONLY);

      if  (list.size() > 0) {
        context.startActivity(intent2);
      }

    } catch (Exception e) {
      Log.d("PERMISSION", e.toString());
    }


  }
}
