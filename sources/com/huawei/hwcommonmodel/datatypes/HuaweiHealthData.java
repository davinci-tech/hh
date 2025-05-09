package com.huawei.hwcommonmodel.datatypes;

import defpackage.jdy;

/* loaded from: classes5.dex */
public class HuaweiHealthData {
    private int commandType = -1;
    private String data = "";
    private String data1 = "";
    private String data2 = "";

    public String getData1() {
        return (String) jdy.d(this.data1);
    }

    public void setData1(String str) {
        this.data1 = str;
    }

    public String getData2() {
        return (String) jdy.d(this.data2);
    }

    public void setData2(String str) {
        this.data2 = str;
    }

    public int getCommandType() {
        return ((Integer) jdy.d(Integer.valueOf(this.commandType))).intValue();
    }

    public void setCommandType(int i) {
        this.commandType = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String getData() {
        return (String) jdy.d(this.data);
    }

    public void setData(String str) {
        this.data = (String) jdy.d(str);
    }

    public String toString() {
        return "HuaweiHealthData{commandType=" + this.commandType + ", data='" + this.data + "', data1='" + this.data1 + "', data2='" + this.data2 + "'}";
    }
}
