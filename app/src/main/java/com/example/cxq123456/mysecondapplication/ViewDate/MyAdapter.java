package com.example.cxq123456.mysecondapplication.ViewDate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cxq123456.mysecondapplication.R;

/**
 * Created by cxq123456 on 2018/5/6    添加adapter适配器
 * 将各种数据以合适的形式显示在View中给用户看。Adapter有很多的接口、抽象类、子类可以使用，这里就我们常用的几个进行讲解
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{
    private Context context;
    private GetSensorRealtimeDataResult bean;

    public MyAdapter(Context context, GetSensorRealtimeDataResult bean) {
        this.context = context;
        this.bean = bean;
    }
    // fuck
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder = new MyHolder(LayoutInflater.from(context).inflate(R.layout.view_data, parent, false));
        return holder;
    }

    @Override//getGetSensorRealtimeDataResult
    public void onBindViewHolder(MyHolder holder, int position) {
        // xiang yaosha xinxi jiu  tianjia
        holder.deviceBoxID.setText(bean.getGetSensorRealtimeDataResult().get(position).getDeviceBoxID());
        holder.texts.setText(bean.getGetSensorRealtimeDataResult().get(position).getDeviceID());
        /*3 geikongjian chuanzhi*/
        holder.deviceType.setText(bean.getGetSensorRealtimeDataResult().get(position).getDeviceType());
        holder.status.setText(bean.getGetSensorRealtimeDataResult().get(position).getStatus());
        holder.value.setText(bean.getGetSensorRealtimeDataResult().get(position).getValue());

    }

    @Override
    public int getItemCount() {
       //数据长度
        System.out.println(">>>>>>>>>>>>>>>>shuju  changdu : " +bean.getGetSensorRealtimeDataResult().size());
        return bean.getGetSensorRealtimeDataResult().size();
    }


    class MyHolder extends RecyclerView.ViewHolder {

        TextView texts;
        /*2   dierbu  chushihua  kongjian*/
        TextView deviceID ;
        TextView deviceType;
        TextView deviceBoxID;
        TextView status;
        TextView value;
        public MyHolder(View itemView) {
            super(itemView);
            texts = (TextView) itemView.findViewById(R.id.response_text);
            deviceBoxID = (TextView) itemView.findViewById(R.id.deviceBoxID);
            deviceID = (TextView) itemView.findViewById(R.id.deviceID);
            deviceType = (TextView) itemView.findViewById(R.id.deviceType);
            status = (TextView) itemView.findViewById(R.id.status);
            value = (TextView) itemView.findViewById(R.id.value);


        }
    }



}
