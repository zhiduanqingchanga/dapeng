package com.example.cxq123456.mysecondapplication.ViewDate;

import java.util.List;

public class GetSensorRealtimeDataResult {


    private List<GetSensorRealtimeDataResultBean> GetSensorRealtimeDataResult;

    public List<GetSensorRealtimeDataResultBean> getGetSensorRealtimeDataResult() {
        return GetSensorRealtimeDataResult;
    }

    public void setGetSensorRealtimeDataResult(List<GetSensorRealtimeDataResultBean> GetSensorRealtimeDataResult) {
        this.GetSensorRealtimeDataResult = GetSensorRealtimeDataResult;
    }

    public static class GetSensorRealtimeDataResultBean {
        /**
         * DeviceBoxID : A10000
         * DeviceID : 10004
         * DeviceType : 光照强度
         * Remark : 光照强度
         * Status : 正常
         * Value : 993Lux
         */

        private String DeviceBoxID;
        private String DeviceID;
        private String DeviceType;
        private String Remark;
        private String Status;
        private String Value;

        public String getDeviceBoxID() {
            return DeviceBoxID;
        }

        public void setDeviceBoxID(String DeviceBoxID) {
            this.DeviceBoxID = DeviceBoxID;
        }

        public String getDeviceID() {
            return DeviceID;
        }

        public void setDeviceID(String DeviceID) {
            this.DeviceID = DeviceID;
        }

        public String getDeviceType() {
            return DeviceType;
        }

        public void setDeviceType(String DeviceType) {
            this.DeviceType = DeviceType;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String Value) {
            this.Value = Value;
        }
    }
}