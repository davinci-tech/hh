package com.huawei.hihealth.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hihealth.ResultUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class HiHearRateUpMetaData implements Parcelable {
    public static final Parcelable.Creator<HiHearRateUpMetaData> CREATOR = new Parcelable.Creator<HiHearRateUpMetaData>() { // from class: com.huawei.hihealth.data.model.HiHearRateUpMetaData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HiHearRateUpMetaData createFromParcel(Parcel parcel) {
            return new HiHearRateUpMetaData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HiHearRateUpMetaData[] newArray(int i) {
            return new HiHearRateUpMetaData[i];
        }
    };
    private List<HiHeartRateData> details;
    private int maxHeartRate;
    private int minHeartRate;
    private int threshold;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiHearRateUpMetaData() {
    }

    protected HiHearRateUpMetaData(Parcel parcel) {
        this.maxHeartRate = parcel.readInt();
        this.minHeartRate = parcel.readInt();
        this.threshold = parcel.readInt();
        ArrayList arrayList = new ArrayList();
        this.details = arrayList;
        parcel.readList(arrayList, HiHeartRateData.class.getClassLoader());
    }

    public int getThreshold() {
        return this.threshold;
    }

    public void setThreshold(int i) {
        this.threshold = i;
    }

    public List<HiHeartRateData> getDetails() {
        return this.details;
    }

    public void setDetails(List<HiHeartRateData> list) {
        this.details = list;
    }

    public int getMaxHeartRate() {
        return this.maxHeartRate;
    }

    public void setMaxHeartRate(int i) {
        this.maxHeartRate = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    public int getMinHeartRate() {
        return this.minHeartRate;
    }

    public void setMinHeartRate(int i) {
        this.minHeartRate = ((Integer) ResultUtils.a(Integer.valueOf(i))).intValue();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.maxHeartRate);
        parcel.writeInt(this.minHeartRate);
        parcel.writeInt(this.threshold);
        parcel.writeList(this.details);
    }
}
