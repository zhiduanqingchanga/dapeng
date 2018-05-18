package com.example.cxq123456.mysecondapplication.DataShow;

import java.util.List;

/**
 * Created by cxq123456 on 2018/5/10.
 */
public class GetSensorDayDataResult {
    private List<GetSensorDayDataResultBean> GetSensorDayDataResult;

    public List<GetSensorDayDataResultBean> getGetSensorDayDataResult() {
        return GetSensorDayDataResult;
    }

    public void setGetSensorDayDataResult(List<GetSensorDayDataResultBean> GetSensorDayDataResult) {
        this.GetSensorDayDataResult = GetSensorDayDataResult;
    }


    @Override
    public String toString() {
        return "GetSensorDayDataResult{" +
                "GetSensorDayDataResult=" + GetSensorDayDataResult +
                '}';
    }

    public static class GetSensorDayDataResultBean {
        /*1“AvgValue中”： “20”，
           2“DDate”： “十二分之二千零十七”，
           3“DTime”：1，
           4“DeviceBoxID”： “A10000”，
           5“的DeviceID”： “10002”，
           6 “设备类型” ： “空气温度”，
        7“MaxValue的”： “22”，
        8“MinValue”： “19”，
           9“备注”： “空气温度”}，
        * */

        private String DeviceBoxID;
        private String DeviceID;
        private String DeviceType;
        private String Remark;
        private String DDate;
        private String DTime;
        private String MinValue;
        private String MaxValue;
        private String AvgValue = "0";

        public String getDeviceBoxID() {
            return DeviceBoxID;
        }

        public void setDeviceBoxID(String deviceBoxID) {
            DeviceBoxID = deviceBoxID;
        }

        public String getDeviceID() {
            return DeviceID;
        }

        public void setDeviceID(String deviceID) {
            DeviceID = deviceID;
        }

        public String getDeviceType() {
            return DeviceType;
        }

        public void setDeviceType(String deviceType) {
            DeviceType = deviceType;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String remark) {
            Remark = remark;
        }

        public String getDDate() {
            return DDate;
        }

        public void setDDate(String DDate) {
            this.DDate = DDate;
        }

        public String getDTime() {
            return DTime;
        }

        public void setDTime(String DTime) {
            this.DTime = DTime;
        }

        public String getMinValue() {
            return MinValue;
        }

        public void setMinValue(String MinValue) {
            this.MinValue = MinValue;
        }

        public String getMaxValue() {
            return MaxValue;
        }

        public void setMaxValue(String maxValue) {
            MaxValue = maxValue;
        }

        public String getAvgValue() {
            return AvgValue;

        }

        public void setAvgValue(String avgValue) {
            AvgValue = avgValue;
        }

        @Override
        public String toString() {
            return "GetSensorDayDataResultBean{" +
                    "DeviceBoxID='" + DeviceBoxID + '\'' +
                    ", DeviceID='" + DeviceID + '\'' +
                    ", DeviceType='" + DeviceType + '\'' +
                    ", Remark='" + Remark + '\'' +
                    ", DDate='" + DDate + '\'' +
                    ", DTime='" + DTime + '\'' +
                    ", MinValue='" + MinValue + '\'' +
                    ", MaxValue='" + MaxValue + '\'' +
                    ", AvgValue='" + AvgValue + '\'' +
                    '}';
        }
    }




}
