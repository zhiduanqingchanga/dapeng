package com.example.cxq123456.mysecondapplication.ViewDate;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.cxq123456.mysecondapplication.R;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Request;

/**
 * Created by cxq123456 on 2018/5/2.
 */
public class ViewDateActivity extends Activity implements View.OnClickListener {

    private RecyclerView recyclrview;
    private GetSensorRealtimeDataResult bean;
    private MyAdapter myAdapter;
    private String path = "http://211.149.245.105:8866/MonitorService.svc/webHttp/GetSensorRealtimeData";
    // path 是请求地址
    // 请求参数 { "GreenHouseID":"House1"}

    TextView responseText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载一个布局
        setContentView(R.layout.activity_viewdata);
        Button send_request =(Button) this.findViewById(R.id.send_request);
        send_request.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        setOkHttp();
    }

    public void setRecyclerView() {
        recyclrview = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclrview.setLayoutManager(linearLayoutManager);
        myAdapter = new MyAdapter(ViewDateActivity.this, bean);
        recyclrview.setAdapter(myAdapter);
    }


   public void setOkHttp() {
       OkHttp.postAsync1(path, "{\"GreenHouseID\":\"House1\"}", new OkHttp.DataCallBack() {
           @Override
           public void requestFailure(Request request, IOException e) {
               System.out.println(">>>>>>>>>>>>>>>>>>qingqiu shibai ");
           }

           @Override
           public void requestSuccess(String result) throws Exception {
               Intent intent = new Intent();

               System.out.println(">>>>>>>>>>>>>>>>>qingqiu chengong : " + result);
               Gson gson = new Gson();
               bean = gson.fromJson(result, GetSensorRealtimeDataResult.class);
               setRecyclerView();
           }
       });

   }









}




