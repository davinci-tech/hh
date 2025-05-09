package com.huawei.hwcloudmodel.model.intelligent;

import java.util.Map;

/* loaded from: classes5.dex */
public class DeviceServiceInfo {
    private Map<String, String> data;
    private String sid;

    public String getSid() {
        return this.sid;
    }

    public void setSid(String str) {
        this.sid = str;
    }

    public Map<String, String> getData() {
        return this.data;
    }

    public void setData(Map<String, String> map) {
        this.data = map;
    }

    public String toString() {
        return "DeviceServiceInfo{sid=" + this.sid + ", data=" + this.data + "}";
    }
}
