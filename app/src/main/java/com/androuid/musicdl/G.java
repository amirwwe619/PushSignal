package com.androuid.musicdl;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

import com.onesignal.OneSignal;

import java.lang.reflect.Method;

/**
 * Created by falah on 18/01/2018.
 */
//Git
  //Git2
  //Bye
public class G extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    //Hey2
    //Hey3
    OneSignal.startInit(this)
      .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
      .unsubscribeWhenNotificationsAreDisabled(true)
      .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
      .init();
    Log.i("","LOGGGg");

    if(Build.VERSION.SDK_INT>=24){
      try{
        Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
        m.invoke(null);
      }catch(Exception e){
        e.printStackTrace();
      }
    }


    // Call syncHashedEmail anywhere in your app if you have the user's email.
    // This improves the effectiveness of OneSignal's "best-time" notification scheduling feature.
    // OneSignal.syncHashedEmail(userEmail);
  }
}