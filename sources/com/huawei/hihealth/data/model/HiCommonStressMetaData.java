package com.huawei.hihealth.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public abstract class HiCommonStressMetaData implements Parcelable {
    protected Integer algorithmVer;
    protected Integer deltaPressure;
    protected Integer devNo;
    protected Long endTime;
    protected Integer frontPressure;
    protected Integer maxHeartRate;
    protected Integer meanHeartRate;
    protected Integer minHeartRate;
    protected Integer rearPressure;
    protected Long startTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiCommonStressMetaData() {
    }

    protected HiCommonStressMetaData(Parcel parcel) {
        this.maxHeartRate = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.minHeartRate = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.meanHeartRate = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.frontPressure = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.rearPressure = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.deltaPressure = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.startTime = (Long) parcel.readValue(Long.class.getClassLoader());
        this.endTime = (Long) parcel.readValue(Long.class.getClassLoader());
        this.algorithmVer = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.devNo = (Integer) parcel.readValue(Integer.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.maxHeartRate);
        parcel.writeValue(this.minHeartRate);
        parcel.writeValue(this.meanHeartRate);
        parcel.writeValue(this.frontPressure);
        parcel.writeValue(this.rearPressure);
        parcel.writeValue(this.deltaPressure);
        parcel.writeValue(this.startTime);
        parcel.writeValue(this.endTime);
        parcel.writeValue(this.algorithmVer);
        parcel.writeValue(this.devNo);
    }
}
