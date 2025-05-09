package com.huawei.hwfoundationmodel.trackmodel;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes.dex */
public class StepRateData implements Serializable, TimeSequence, Parcelable {
    public static final Parcelable.Creator<StepRateData> CREATOR = new Parcelable.Creator<StepRateData>() { // from class: com.huawei.hwfoundationmodel.trackmodel.StepRateData.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: bPx_, reason: merged with bridge method [inline-methods] */
        public StepRateData createFromParcel(Parcel parcel) {
            return new StepRateData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public StepRateData[] newArray(int i) {
            return new StepRateData[i];
        }
    };
    private static final long serialVersionUID = -1186336294220451546L;
    private int mStepRate;
    private long mTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected StepRateData(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mTime = parcel.readLong();
        this.mStepRate = parcel.readInt();
    }

    public StepRateData() {
    }

    public StepRateData(long j, int i) {
        this.mTime = j;
        this.mStepRate = i;
    }

    @Override // com.huawei.hwfoundationmodel.trackmodel.TimeSequence
    public long acquireTime() {
        return this.mTime;
    }

    public void saveTime(long j) {
        this.mTime = j;
    }

    public int acquireStepRate() {
        return this.mStepRate;
    }

    public void saveStepRate(int i) {
        this.mStepRate = i;
    }

    public String toString() {
        return this.mTime + "," + this.mStepRate;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeLong(this.mTime);
        parcel.writeInt(this.mStepRate);
    }
}
