package com.example.cxq123456.mysecondapplication.DataShow;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cxq123456.mysecondapplication.R;
import com.example.cxq123456.mysecondapplication.ViewDate.OkHttp;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
import okhttp3.Request;

/**
 * Created by cxq123456 on 2018/5/10.
 */
public class DataShowActivity extends Activity {
    private GetSensorDayDataResult bean;
    private String path = "http://211.149.245.105:8866/MonitorService.svc/webHttp/GetSensorDayData";
    private Spinner spinner1;
    private Spinner spinner2;
    private String result1;//下拉列表1中选定项的值
    //private String result2;
    private int y_possion;//获取value下拉选项的位置
    //private int x_possion;//获取到时间选择的位置
    // path 是请求地址
    /*请求参数 {
    "GreenHouseID: House1,
    DeviceID: 10002,
    DDate :2017/12-2018/3(一共4个月的数据)


    "}*/

    // linechartview
    private LineChartView lineChart;

    ArrayList<String> date = new ArrayList<>();//X轴的标注
    ArrayList<String> val = new ArrayList<>();//图表的数据点
    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载一个布局
        setContentView(R.layout.activity_datashow);

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);

        //给时间下拉列表添加监听器
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //通过位置来获取下拉列表的值
                result1 = spinner1.getItemAtPosition(position).toString();
                //x_possion = position;
                Log.i("result", result1);
                //setOkHttp(result1,0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //给value下拉列表添加监听器
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //通过位置来获取下拉列表的值
                //result2 = spinner2.getItemAtPosition(position).toString();
                // y_possion = position;
                Log.d(">>>>>> ", position + "");//
                setOkHttp(result1, position);// run
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // 图表
        lineChart = (LineChartView)findViewById(R.id.line_chart);


    }


    public void setOkHttp(String result1, final int position) {
        DataShowModel dataShowModel = new DataShowModel();
        dataShowModel.setGreenHouseID("House1");
        dataShowModel.setDeviceID("10002");
        dataShowModel.setDDate(result1);

        String postStr = new Gson().toJson(dataShowModel, DataShowModel.class);
        OkHttp.postAsync1(path, postStr, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Log.e("onFail", e.getMessage());
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Intent intent = new Intent();

                Log.e("onSuccess", result + "");
                Gson gson = new Gson();
                bean = gson.fromJson(result, GetSensorDayDataResult.class);
                System.out.println(">>>>>> size " + bean.getGetSensorDayDataResult().size());
                getX(bean.getGetSensorDayDataResult());
                getY(bean.getGetSensorDayDataResult(), position);
                analy(bean.getGetSensorDayDataResult(),position);//这个方法用来对数据进行分析和统计
                initLineChart();//初始化
                //
                date = new ArrayList<>();//X轴的标注
                val = new ArrayList<>();//图表的数据点
                mPointValues = new ArrayList<PointValue>();
                mAxisXValues = new ArrayList<AxisValue>();
            }
        });

    }
    //对数据进行分析
    private void analy(List<GetSensorDayDataResult.GetSensorDayDataResultBean> getSensorDayDataResult, int position){
         int maxDay=0;//统计超过预定最大空气温度的天数
         int minDay=0;//统计低于预定最大空气温度的天数
        for (int i = 0; i < getSensorDayDataResult.size(); i++) {
            if (i>31){
                break;
            }
            //获取最大值
            String maxValue = getSensorDayDataResult.get(i).getMaxValue();
            maxValue = maxValue==null||"".equals(maxValue)?"0":maxValue;

            /*String avgValue = getSensorDayDataResult.get(i).getAvgValue();
            avgValue = maxValue==null||"".equals(avgValue)?"0":avgValue;*/
            //获取最小值
            String minValue = getSensorDayDataResult.get(i).getMinValue();
            minValue = minValue==null||"".equals(minValue)?"0":minValue;
            // run
            switch (position){
                case 0:
                    if (Integer.parseInt(maxValue)>23){
                        maxDay++;
                    }
                    break;
                case 2:
                    if (Integer.parseInt(minValue)<18){
                        minDay++;
                    }
                    break;
                default:
                    break;

            }


        }

        if (maxDay >= 4) {
            Toast.makeText(this,"当前月份温度偏高天数过多，请适当调整大棚内的空气温度",Toast.LENGTH_LONG).show();
        }
        else if(minDay>=4){
            Toast.makeText(this,"当前月份温度偏底天数过多，请适当调整大棚内的空气温度",Toast.LENGTH_LONG).show();
        }
        else if (maxDay <4 && minDay < 4 && position !=1){
            Toast.makeText(this,"当前月份温度偏底天数过多，请适当调整大棚内的空气温度",Toast.LENGTH_LONG).show();

        }

    }
    //获取纵坐标
    private void getY(List<GetSensorDayDataResult.GetSensorDayDataResultBean> getSensorDayDataResult, int position) {
        for (int i = 0; i < getSensorDayDataResult.size(); i++) {
            if (i>31){
                break;
            }

            String maxValue = getSensorDayDataResult.get(i).getMaxValue();
            maxValue = maxValue==null||"".equals(maxValue)?"0":maxValue;

            String avgValue = getSensorDayDataResult.get(i).getAvgValue();
            avgValue = maxValue==null||"".equals(avgValue)?"0":avgValue;

            String minValue = getSensorDayDataResult.get(i).getMinValue();
            minValue = minValue==null||"".equals(minValue)?"0":minValue;
            // run
            switch (position){
                case 0:
                    mPointValues.add(new PointValue(i, Integer.parseInt(maxValue)));
                    break;
                case 1:
                    mPointValues.add(new PointValue(i, Integer.parseInt(avgValue)));
                    break;
                case 2:
                    mPointValues.add(new PointValue(i, Integer.parseInt(minValue)));
                    break;
                default:
                    mPointValues.add(new PointValue(i, Integer.parseInt(avgValue)));
                    break;
            }

        }
    }


    // 获取横坐标
    private void getX(List<GetSensorDayDataResult.GetSensorDayDataResultBean> bean) {
        System.out.println("bean >>>>>  " + bean.toString());

        for (int i = 0; i < bean.size(); i++) {
            if (i>31){
                break;
            }

            System.out.println(">>> date  size " + bean.size() + ">>>>>> val  " + bean.get(i).getDTime());
            mAxisXValues.add(new AxisValue(i).setLabel(bean.get(i).getDTime()));
        }
    }


    private void initLineChart(){
        Line line = new Line(mPointValues).setColor(Color.parseColor("#FFCD41"));  //折线的颜色
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.SQUARE）
        line.setCubic(false);//曲线是否平滑
//	    line.setStrokeWidth(3);//线条的粗细，默认是3
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
//		line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用直线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(false);  //X轴下面坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setName("号");
        //axisX.setTextColor(Color.WHITE);  //设置字体颜色
        axisX.setTextColor(Color.RED);//灰色

//	    axisX.setName("未来几天的天气");  //表格名称
        axisX.setTextSize(11);//设置字体大小
        axisX.setMaxLabelChars(7); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
//	    data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线


        Axis axisY = new Axis();  //Y轴
        axisY.setName("℃");//y轴标注
        axisY.setTextSize(11);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        axisY.setTextColor(Color.BLUE);//灰色
        //data.setAxisYRight(axisY);  //y轴设置在右边

        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);  //缩放类型，水平
        lineChart.setMaxZoom((float) 3);//缩放比例
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
        /**注：下面的7，10只是代表一个数字去类比而已
         * （
         * 下面几句可以设置X轴数据的显示个数（x轴0-7个数据），当数据点个数小于（29）的时候，缩小到极致hellochart默认的是所有显示。当数据点个数大于（29）的时候，
         * 若不设置axisX.setMaxLabelChars(int count)这句话,则会自动适配X轴所能显示的尽量合适的数据个数。
         * 若设置axisX.setMaxLabelChars(int count)这句话,
         * 33个数据点测试，若 axisX.setMaxLabelChars(10);里面的10大于v.right= 7; 里面的7，则
         刚开始X轴显示7条数据，然后缩放的时候X轴的个数会保证大于7小于10
         若小于v.right= 7;中的7,反正我感觉是这两句都好像失效了的样子 - -!
         * 并且Y轴是根据数据的大小自动设置Y轴上限
         * 若这儿不设置 v.right= 7; 这句话，则图表刚开始就会尽可能的显示所有数据，交互性太差
         */
        Viewport v = new Viewport(lineChart.getMaximumViewport());
        v.left = 0;
        v.right= 7;
        lineChart.setCurrentViewport(v);
    }

}




