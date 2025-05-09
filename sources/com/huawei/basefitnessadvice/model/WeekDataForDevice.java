package com.huawei.basefitnessadvice.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes3.dex */
public class WeekDataForDevice {

    @SerializedName("dayDataForDeviceList")
    List<DayDataForDevice> mDayDataForDeviceList;

    @SerializedName("deviceUdId")
    private String mDeviceUdId;

    @SerializedName("weekIndex")
    private int mWeekIndex;

    public List<DayDataForDevice> getDayDataForDeviceList() {
        return this.mDayDataForDeviceList;
    }

    public void setDayDataForDeviceList(List<DayDataForDevice> list) {
        this.mDayDataForDeviceList = list;
    }

    public int getWeekIndex() {
        return this.mWeekIndex;
    }

    public void setWeekIndex(int i) {
        this.mWeekIndex = i;
    }

    public String getDeviceUdId() {
        return this.mDeviceUdId;
    }

    public void setDeviceUdId(String str) {
        this.mDeviceUdId = str;
    }
}
