package com.example.cxq123456.mysecondapplication.DataShow;

/**
 * Created by cxq123456 on 2018/5/11.
 */
public class DataShowModel {
    /**
     * GreenHouseID : House1
     * DeviceID : 10002
     * DDate : 2017/12
     */
    //将参数和路径转成一个json数据
    private String GreenHouseID;
    private String DeviceID;
    private String DDate;

    public String getGreenHouseID() {
        return GreenHouseID;
    }

    public void setGreenHouseID(String GreenHouseID) {
        this.GreenHouseID = GreenHouseID;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String DeviceID) {
        this.DeviceID = DeviceID;
    }

    public String getDDate() {
        return DDate;
    }

    public void setDDate(String DDate) {
        this.DDate = DDate;
    }
}
