package com.huawei.datatype;

import com.google.gson.annotations.SerializedName;
import defpackage.jdy;

/* loaded from: classes3.dex */
public class GpsParameter {

    @SerializedName("gps_info_bitmap")
    private int gpsInfoBitmap;

    @SerializedName("gps_para_element_num")
    private int gpsParaElementNum;

    @SerializedName("gps_para_format")
    private int gpsParaFormat;

    @SerializedName("gps_threshold")
    private int gpsThreshold;

    public int getGpsInfoBitmap() {
        return ((Integer) jdy.d(Integer.valueOf(this.gpsInfoBitmap))).intValue();
    }

    public void setGpsInfoBitmap(int i) {
        this.gpsInfoBitmap = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getGpsParaFormat() {
        return ((Integer) jdy.d(Integer.valueOf(this.gpsParaFormat))).intValue();
    }

    public void setGpsParaFormat(int i) {
        this.gpsParaFormat = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getGpsParaElementNum() {
        return ((Integer) jdy.d(Integer.valueOf(this.gpsParaElementNum))).intValue();
    }

    public void setGpsParaElementNum(int i) {
        this.gpsParaElementNum = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getGpsThreshold() {
        return ((Integer) jdy.d(Integer.valueOf(this.gpsThreshold))).intValue();
    }

    public void setGpsThreshold(int i) {
        this.gpsThreshold = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }
}
