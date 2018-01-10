package com.example.myidoldemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myidoldemo.db.UserInfo;
import com.example.myidoldemo.util.HttpUtil;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SignInActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private CheckBox rememberPassword;
    private EditText userNameEdit;
    private EditText passwordEdit;
    private Button signIn;
    private Button signUp;
    private String mbaseUrl="http://10.0.3.2:8080/MyIdolDemo/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initView();
    }
    public void initView(){
        userNameEdit=(EditText)findViewById(R.id.username_edit);
        passwordEdit=(EditText)findViewById(R.id.password_edit);
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        rememberPassword=(CheckBox)findViewById(R.id.remember_password);
        signIn=(Button)findViewById(R.id.signin);
        signUp=(Button)findViewById(R.id.signup);
        final SharedPreferences.Editor editor=pref.edit();
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=userNameEdit.getText().toString();
                String password=passwordEdit.getText().toString();
                if(rememberPassword.isChecked()){
                    editor.putString("userName",userName);
                    editor.putString("password",password);
                    editor.putBoolean("isRemember",true);
                }else {
                    editor.clear();
                }
                editor.apply();
                queryUserInfo(userName,password);

                    Intent intent=new Intent(SignInActivity.this,MainActivity.class);
                    startActivity(intent);
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

public void queryUserInfo(String userName,String password){
    String address=mbaseUrl+"login?userName="+userName+"&password="+password;
    HttpUtil.sendOkHttpRequest(address, new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            L.e("onFailure:"+e.getMessage());
            e.printStackTrace();

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String res=response.body().string();
            L.e(res);

        }
    });


}
    public void addUserInfo(String userName,String password){
        UserInfo userInfo=new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setPassword(password);
        userInfo.save();

    }
}
