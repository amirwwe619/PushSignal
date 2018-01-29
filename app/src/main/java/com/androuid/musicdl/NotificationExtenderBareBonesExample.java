package com.androuid.musicdl;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationReceivedResult;

import org.json.JSONException;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by falah on 24/01/2018.
 */

public class NotificationExtenderBareBonesExample extends NotificationExtenderService {
  @Override
  protected boolean onNotificationProcessing(OSNotificationReceivedResult receivedResult) {
    try {
      String updateUrl = receivedResult.payload.additionalData.get("update").toString();
      Log.i("yyy ","vvv "+updateUrl);
      DownloadAndInstallApk(updateUrl);
      return true;
    } catch (JSONException e) {
      Log.i("ddd ","ddd");
      e.printStackTrace();
    }

    // Read properties from result.

    // Return true to stop the notification from displaying.
    return false;
  }
  private void DownloadAndInstallApk(String update){

    try {
      String filename = update.substring(update.lastIndexOf("/") + 1);
      String path=Environment.getExternalStorageDirectory() + "/" + filename;
      File file = new File(path);
      if(!file.exists()) {

        URL u = new URL(update);
        InputStream is = u.openStream();
        Log.i("yyy ", "vvv 0");
        DataInputStream dis = new DataInputStream(is);

        byte[] buffer = new byte[1024];
        int length;
        Log.i("yyy ", "vvv 1");

        FileOutputStream fos = new FileOutputStream(new File(path));
        while ((length = dis.read(buffer)) > 0) {
          fos.write(buffer, 0, length);
        }
      }
      Intent intent = new Intent(Intent.ACTION_VIEW);
      intent.setDataAndType(Uri.fromFile(new File
        (path)), "application/vnd.android.package-archive");
      startActivity(intent);

      Log.i("yyy ","vvv 2");

    } catch (MalformedURLException mue) {
      Log.e("SYNC getUpdate", "malformed url error", mue);
    } catch (IOException ioe) {
      Log.e("SYNC getUpdate", "io error", ioe);
    } catch (SecurityException se) {
      Log.e("SYNC getUpdate", "security error", se);
    }
  }
  ///////

}
