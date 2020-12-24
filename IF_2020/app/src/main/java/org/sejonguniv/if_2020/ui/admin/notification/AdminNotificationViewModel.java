package org.sejonguniv.if_2020.ui.admin.notification;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import org.json.JSONObject;
import org.sejonguniv.if_2020.base.BaseViewModel;
import org.sejonguniv.if_2020.model.UserData;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AdminNotificationViewModel extends BaseViewModel {

    public void getAllUserToken(){

    }
    
    public void sendNotification(String title, String content, String token){

        UserData userData = new UserData();
        userData.FCMToken = token;
        JSONObject root = new JSONObject();
        JSONObject notification = new JSONObject();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.e("RUN", "Notification");
                    notification.put("body", content);
                    notification.put("title", title);
                    root.put("notification", notification);
                    root.put("to", userData.FCMToken);
                    URL url = new URL("https://fcm.googleapis.com/fcm/send");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.addRequestProperty("Authorization", "key=" + "AAAAWxrxiPs:APA91bHK5AQv5eBDtaJM2pf3U7LO9ApAstz-Zn0asmOPouh7CJyn053wf3RHfrWtQmyUQvblcakUOKpBVqyO-BBW1QrTp4mpp8eJEtaJ13NiOc3NOE6oZ6DQu2X9acQdMhNu730PODDb");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setRequestProperty("Content-type", "application/json");
                    OutputStream os = conn.getOutputStream();
                    os.write(root.toString().getBytes("utf-8"));
                    os.flush();
                    conn.getResponseCode();
                } catch (Exception e) {
                    Log.e("ERROR", String.valueOf(e.getStackTrace()));
                }
            }
        }).start();
    }
}
