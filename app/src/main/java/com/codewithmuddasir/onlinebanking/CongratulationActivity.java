package com.codewithmuddasir.onlinebanking;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.codewithmuddasir.onlinebanking.databinding.ActivityCongratulationBinding;
import com.codewithmuddasir.onlinebanking.helper.Util;

public class CongratulationActivity extends AppCompatActivity {

    ActivityCongratulationBinding binding;
    CountDownTimer countDownTimer;
    private Util util = new Util();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_congratulation);

        util.saveLocalData(this,"check","true");

//
//        binding.home.setOnClickListener(view -> {
//
//            Intent intent = new Intent(CongratulationActivity.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//
//        });

        startCountdownTimer();



    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        Util util = new Util();
//        if (util.getLocalData(this, "check").equals("true")) {
//            Log.e("asdf123", "pause: verify activity");
//            PackageManager packageManager = getPackageManager();
//            packageManager.setApplicationEnabledSetting(
//                    getPackageName(),
//                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
//                    PackageManager.DONT_KILL_APP
//            );
//        }
//    }


    private void startCountdownTimer() {
        countDownTimer = new CountDownTimer(3600000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = millisUntilFinished / 1000;
                long minutes = (secondsRemaining % 3600) / 60;
                long seconds = secondsRemaining % 60;
                binding.timer.setText(minutes + " : " + seconds);
            }

            @Override
            public void onFinish() {
                binding.timer.setText("00 : 00");
            }
        };
        countDownTimer.start();
    }


}