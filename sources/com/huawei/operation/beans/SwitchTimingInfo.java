package com.huawei.operation.beans;

import java.util.List;

/* loaded from: classes9.dex */
public class SwitchTimingInfo {
    private String name;
    private List<TimingInfo> timingInfo;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public List<TimingInfo> getTimingInfo() {
        return this.timingInfo;
    }

    public void setTimingInfo(List<TimingInfo> list) {
        this.timingInfo = list;
    }

    public static class TimingInfo {
        private int code;
        private String date;

        public int getCode() {
            return this.code;
        }

        public void setCode(int i) {
            this.code = i;
        }

        public String getDate() {
            return this.date;
        }

        public void setDate(String str) {
            this.date = str;
        }
    }
}
