package org.sejonguniv.if_2020.ui.admin.notification;

import org.json.JSONObject;
import org.sejonguniv.if_2020.base.BaseViewModel;
import org.sejonguniv.if_2020.model.FCMToken;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AdminNotificationViewModel extends BaseViewModel {
    
    public void sendNotification(String title, String content, String token){

        FCMToken fcmToken = new FCMToken();
        fcmToken.FCMToken = token;
        JSONObject root = new JSONObject();
        JSONObject notification = new JSONObject();

        String fcmServer = "AAAAWxrxiPs:APA91bHK5AQv5eBDtaJM2pf3U7LO9ApAstz-Zn0asmOPouh7CJyn053wf3RHfrWtQmyUQvblcakUOKpBVqyO-BBW1QrTp4mpp8eJEtaJ13NiOc3NOE6oZ6DQu2X9acQdMhNu730PODDb";

        new Thread(() -> {
            try {
                notification.put("body", content);
                notification.put("title", title);
                root.put("notification", notification);
                root.put("to", fcmToken.FCMToken);
                URL url = new URL("https://fcm.googleapis.com/fcm/send");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.addRequestProperty("Authorization", "key=" + fcmServer);
                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestProperty("Content-type", "application/json");
                OutputStream os = conn.getOutputStream();
                os.write(root.toString().getBytes(StandardCharsets.UTF_8));
                os.flush();
                conn.getResponseCode();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
