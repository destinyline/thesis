package com.a13560301.dream.fragmenttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.a13560301.dream.fragmenttest.Model.EntityModel;
import com.a13560301.dream.fragmenttest.Model.UserModel;

/**
 * Created by dream on 12/13/2016.
 */

public class LoginActivity extends Activity {

    protected  void  onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        final Button btnregis = (Button)findViewById(R.id.btnregis);
        btnregis.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this,SignupActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });


    }

    public void onClickSignIn(View v)
    {
        EditText username_edt = (EditText)findViewById(R.id.EDTUsername);
        EditText password_edt = (EditText)findViewById(R.id.EDTPassword);

        EntityModel entity = new EntityModel();

        ///Assign Data Entitymodel object
        entity.getValues().put("login_username",username_edt.getText().toString());
        entity.getValues().put("login_password",password_edt.getText().toString());

        ///Creat ServerConnecter object
        ServerConnector connector = new ServerConnector();

        ///Connect www page with EntityModel object
        String strresult = connector.connect("getusersignin.php",true,entity);

//        Toast.makeText(this,strresult,Toast.LENGTH_SHORT).show();

        UserModel user = new UserModel();
        user.setUserModel(strresult);

        if (user.getUser_id() != 0 ){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this,"Username or Password is incorrect.",Toast.LENGTH_SHORT).show();
        }
    }



//    public void onClicksignup(View v){
//        Button btnregis = (Button)findViewById(R.id.btnregis);
//        Intent intent1 = new Intent(this,SignupActivity.class);
//        startActivity(intent1);
//
//    }



}