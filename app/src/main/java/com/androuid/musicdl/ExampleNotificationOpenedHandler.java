package com.androuid.musicdl;

import android.os.Environment;
import android.util.Log;

import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by falah on 23/01/2018.
 */

public class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
  // This fires when a notification is opened by tapping on it.
  @Override
  public void notificationOpened(OSNotificationOpenResult result) {
    OSNotificationAction.ActionType actionType = result.action.type;
    JSONObject data = result.notification.payload.additionalData;
    String update;
    if (data != null) {
      update = data.optString("update", null);
      if (update != null) {
        Log.i("OneSignalExample ccc", "ccc customkey set with value: " + update);
        DownloadFiles(update);
      }
    }

   // if (actionType == OSNotificationAction.ActionType.ActionTaken)
    //  Log.i("OneSignalExample ccc", "ccc Button pressed with id: " + result.action.actionID);

    // The following can be used to open an Activity of your choice.
    // Replace - getApplicationContext() - with any Android Context.
    // Intent intent = new Intent(getApplicationContext(), YourActivity.class);
    // intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
    // startActivity(intent);

    // Add the following to your AndroidManifest.xml to prevent the launching of your main Activity
    //   if you are calling startActivity above.
     /*
        <application ...>
          <meta-data android:name="com.onesignal.NotificationOpened.DEFAULT" android:value="DISABLE" />
        </application>
     */
  }

  private void DownloadFiles(String update){

    try {
      URL u = new URL(update);
      InputStream is = u.openStream();

      DataInputStream dis = new DataInputStream(is);

      byte[] buffer = new byte[1024];
      int length;

      FileOutputStream fos = new FileOutputStream(new File(Environment.getExternalStorageDirectory() + "/xx.jpg"));
      while ((length = dis.read(buffer))>0) {
        fos.write(buffer, 0, length);
      }

    } catch (MalformedURLException mue) {
      Log.e("SYNC getUpdate", "malformed url error", mue);
    } catch (IOException ioe) {
      Log.e("SYNC getUpdate", "io error", ioe);
    } catch (SecurityException se) {
      Log.e("SYNC getUpdate", "security error", se);
    }
  }
}



