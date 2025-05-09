package com.huawei.profile.coordinator.bean;

import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class PutCharacteristicBodyBean {
    private List<ServicesBean> services;
    private String status;
    private String timestamp;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }

    public List<ServicesBean> getServices() {
        return this.services;
    }

    public void setServices(List<ServicesBean> list) {
        this.services = list;
    }

    public static class ServicesBean {
        private Map<String, Object> data;
        private String sid;
        private String ts;

        public String getTs() {
            return this.ts;
        }

        public void setTs(String str) {
            this.ts = str;
        }

        public String getSid() {
            return this.sid;
        }

        public void setSid(String str) {
            this.sid = str;
        }

        public Map<String, Object> getData() {
            return this.data;
        }

        public void setData(Map<String, Object> map) {
            this.data = map;
        }
    }
}
