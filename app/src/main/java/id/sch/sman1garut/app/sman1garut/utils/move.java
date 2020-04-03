package id.sch.sman1garut.app.sman1garut.utils;


import android.content.Context;
import android.content.Intent;

public class move extends Intent {

  public static void moveActivity(Context mContext,Class<?> activity){
    Intent i=new Intent(mContext,activity);
    mContext.startActivity(i);
  }


}
