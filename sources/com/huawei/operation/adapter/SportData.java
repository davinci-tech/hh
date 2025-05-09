package com.huawei.operation.adapter;

import java.util.List;

/* loaded from: classes9.dex */
public class SportData {
    private List<Data> data;

    public List<Data> getData() {
        return this.data;
    }

    public void configData(List<Data> list) {
        this.data = list;
    }

    public static class Data {
        private int type;
        private String value;

        public int getType() {
            return this.type;
        }

        public void configType(int i) {
            this.type = i;
        }

        public String getValue() {
            return this.value;
        }

        public void configValue(String str) {
            this.value = str;
        }
    }
}
