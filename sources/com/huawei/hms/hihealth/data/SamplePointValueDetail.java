package com.huawei.hms.hihealth.data;

import android.os.Parcelable;
import com.huawei.hms.health.aabq;
import com.huawei.hms.health.aabv;
import com.huawei.hms.health.aabw;
import com.huawei.hms.health.aaby;
import java.util.List;

/* loaded from: classes9.dex */
public class SamplePointValueDetail extends aabq {
    public static final Parcelable.Creator<SamplePointValueDetail> CREATOR = new aabq.aab(SamplePointValueDetail.class);
    private static final String TAG = "SamplePointValueDetail";

    @aaby(id = 5)
    private String mFieldName;

    @aaby(id = 2)
    private double mFloatValue;

    @aaby(id = 1)
    private int mIntegerValue;

    @aaby(id = 4)
    private List<MapValue> mMapValueInfoList;

    @aaby(id = 3)
    private String mStringValue;

    public void setStringValue(String str) {
        this.mStringValue = str;
    }

    public void setMapValueInfo(MapValue mapValue) {
        this.mMapValueInfoList.add(mapValue);
    }

    public void setIntegerValue(int i) {
        this.mIntegerValue = i;
    }

    public void setFloatValue(double d) {
        this.mFloatValue = d;
    }

    public void setFieldName(String str) {
        this.mFieldName = str;
    }

    public String getStringValue() {
        return this.mStringValue;
    }

    public List<MapValue> getMapValue() {
        return this.mMapValueInfoList;
    }

    public int getIntegerValue() {
        return this.mIntegerValue;
    }

    public String getFieldName() {
        return this.mFieldName;
    }

    public double getDoubleValue() {
        return this.mFloatValue;
    }

    public SamplePointValueDetail(int i, List<MapValue> list, double d, String str, String str2) {
        this.mIntegerValue = i;
        this.mFloatValue = d;
        this.mStringValue = str;
        this.mMapValueInfoList = list;
        this.mFieldName = str2;
    }

    @aabw
    public SamplePointValueDetail(@aabv(id = 1) int i, @aabv(id = 2) double d, @aabv(id = 3) String str, @aabv(id = 4) List<MapValue> list, @aabv(id = 5) String str2) {
        this.mIntegerValue = i;
        this.mFloatValue = d;
        this.mStringValue = str;
        this.mMapValueInfoList = list;
        this.mFieldName = str2;
    }
}
