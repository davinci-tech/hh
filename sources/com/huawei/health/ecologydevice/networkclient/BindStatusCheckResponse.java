package com.huawei.health.ecologydevice.networkclient;

/* loaded from: classes8.dex */
public class BindStatusCheckResponse {
    private int bindStatusCode;
    private int code;
    private DeviceBindResp deviceBindResp;
    private String msg;

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getBindStatusCode() {
        return this.bindStatusCode;
    }

    public DeviceBindResp getDeviceBindResp() {
        return this.deviceBindResp;
    }

    public static class DeviceBindResp {
        private long deviceCode;

        public long getDeviceCode() {
            return this.deviceCode;
        }
    }
}
