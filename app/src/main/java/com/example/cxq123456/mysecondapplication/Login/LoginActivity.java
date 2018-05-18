package com.example.cxq123456.mysecondapplication.Login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cxq123456.mysecondapplication.Main.MainActivity;
import com.example.cxq123456.mysecondapplication.R;

public class LoginActivity extends Activity implements View.OnClickListener {


    private static final String TAG = "LoginActivity";
    //定义两个变量
    private EditText mEtxtName;
    private EditText mEtxtPwd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ///加载一个布局
        setContentView(R.layout.activity_login);
        ///找到按钮
        Button btnLogin = (Button) this.findViewById(R.id.butLogin);
        ///给button设置一个点击事件
        btnLogin.setOnClickListener(this);
        //获取输入的信息
        mEtxtName = (EditText) this.findViewById(R.id.username);

        mEtxtPwd = (EditText) this.findViewById(R.id.pwd);
    }



    @Override
    public void onClick(View view) {

        login();
    }


    public void login() {

        String name = this.mEtxtName.getText().toString();
        String password = this.mEtxtPwd.getText().toString();


        //验证输入信息是否正确
        Log.i("Login", "name=" + name + ",password=" + password);
        if ((name.equals("崔新奇")) && (password.equals("123456"))) {
            Log.i("LOGIN", "login success!");
            //把用户信息保存下来
            saveUser(name, password);
            //实现页面跳转
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        else
            Log.i("LOGIN", "login fail!");








    }

    public void saveUser(String name, String password){

          /*sharedpreferences的使用*/
        //打开sp
        SharedPreferences sharedPreferences= this.getSharedPreferences("user", MODE_PRIVATE);

        //让sp处于编辑状态
        SharedPreferences.Editor editor= sharedPreferences.edit();

        //存放数据
        editor.putString("username",name);
        editor.putString("userpwd",password);

        //数据提交
        editor.commit();

    }


}


