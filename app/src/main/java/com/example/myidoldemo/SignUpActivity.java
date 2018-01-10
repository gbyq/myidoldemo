package com.example.myidoldemo;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myidoldemo.db.UserInfo;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {
    private EditText userNameEdit;
    private EditText passwordEdit;
    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
    }
    public void initView(){
        userNameEdit=(EditText)findViewById(R.id.username_edit);
        passwordEdit=(EditText)findViewById(R.id.password_edit);
        signUp=(Button)findViewById(R.id.signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=userNameEdit.getText().toString();
                String password=passwordEdit.getText().toString();
                addUserInfo(userName,password);
//                queryUserInfo(userName,password);
//                Boolean isHaveUser=queryUserInfo(userName,password);
//                if (!isHaveUser){
//                    addUserInfo(userName,password);
//                }

            }
        });
    }
    public void queryUserInfo(String userName,String password){
//        List<UserInfo> userInfos=DataSupport.where("userName=?","userName").find(UserInfo.class);
        Cursor cursor= DataSupport.findBySQL("select * from UserInfo where userName=? and password=?",
                userName,password);
        List<String> nameList=new ArrayList<>();
        List<String> pswList=new ArrayList<>();
        if(cursor!=null){
        if(cursor.moveToFirst()){
            do{
                String name=cursor.getString(cursor.getColumnIndex("userName"));
                String psw=cursor.getString(cursor.getColumnIndex("password"));
                nameList.add(name);
                pswList.add(psw);
                Log.v("name",name);
                Log.v("password",psw);
            }while(cursor.moveToNext());
        }
        cursor.close();

    }}
    public void addUserInfo(String userName,String password){
        UserInfo userInfo=new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setPassword(password);
        userInfo.save();

    }

}
