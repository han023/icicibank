package com.codewithmuddasir.onlinebanking;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.codewithmuddasir.onlinebanking.databinding.ActivityCongratulationBinding;

public class CongratulationActivity extends AppCompatActivity {

    ActivityCongratulationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_congratulation);


        binding.home.setOnClickListener(view -> {

            Intent intent = new Intent(CongratulationActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        });


    }
}