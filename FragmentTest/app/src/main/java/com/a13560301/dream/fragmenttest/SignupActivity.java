package com.a13560301.dream.fragmenttest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dream on 12/13/2016.
 */

public class SignupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_signup);

        final EditText EDTUsername = (EditText)findViewById(R.id.EDTUsername);
        final EditText EDTPassword = (EditText)findViewById(R.id.EDTPassword);
        final EditText EDTEmail = (EditText)findViewById(R.id.EDTEmail);
        final Button bRegister = (Button)findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String username = EDTUsername.getText().toString();
                final String password = EDTPassword.getText().toString();
                final String email = EDTEmail.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonReponse = new JSONObject(response);
                            boolean success = jsonReponse.getBoolean("success");

                            if(success){
                                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                                SignupActivity.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry",null)
                                        .create()
                                        .show();

                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };
                SignupRequest SignupRequest = new SignupRequest(username,password,email,responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);
                queue.add(SignupRequest);
            }
        });
    }
}
