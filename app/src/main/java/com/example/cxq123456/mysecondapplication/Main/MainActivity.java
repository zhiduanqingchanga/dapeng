package com.example.cxq123456.mysecondapplication.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.cxq123456.mysecondapplication.DataShow.DataShowActivity;
import com.example.cxq123456.mysecondapplication.R;
import com.example.cxq123456.mysecondapplication.ViewDate.ViewDateActivity;

/**
 * Created by cxq123456 on 2018/5/2.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private Button button1,  button3,  button5;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载一个布局
        setContentView(R.layout.activity_main);
        //找到按钮

        button1 = (Button) this.findViewById(R.id.button1);

        button5 = (Button) this.findViewById(R.id.button5);

        //依次给按钮绑定响应事件
        button1.setOnClickListener(this);
        button5.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.button1:
                Toast.makeText(this,"ViewDateActivity",Toast.LENGTH_LONG).show();
                Log.d(">>>>>>>>click  " ,"button1    ViewDateActivity");
                intent.setClass(MainActivity.this,ViewDateActivity.class);
                break;
            case R.id.button5:
                Toast.makeText(this,"DataShowActivity",Toast.LENGTH_LONG).show();
                Log.d(">>>>>>>>click  " ,"button5   DataShowActivity");
                intent.setClass(MainActivity.this,DataShowActivity.class);
                break;

        }
        startActivity(intent);

    }
}










