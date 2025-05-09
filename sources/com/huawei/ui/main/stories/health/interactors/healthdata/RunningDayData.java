package com.huawei.ui.main.stories.health.interactors.healthdata;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;

/* loaded from: classes6.dex */
public class RunningDayData implements Parcelable, IStorageModel {
    public static final Parcelable.Creator<RunningDayData> CREATOR = new Parcelable.Creator<RunningDayData>() { // from class: com.huawei.ui.main.stories.health.interactors.healthdata.RunningDayData.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: dEL_, reason: merged with bridge method [inline-methods] */
        public RunningDayData createFromParcel(Parcel parcel) {
            return new RunningDayData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public RunningDayData[] newArray(int i) {
            return new RunningDayData[i];
        }
    };
    private float mCurrentRunLevel;
    private float mRealRunLevel;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RunningDayData() {
    }

    protected RunningDayData(Parcel parcel) {
        this.mCurrentRunLevel = parcel.readFloat();
        this.mRealRunLevel = parcel.readFloat();
    }

    public float getCurrentRunLevel() {
        return this.mCurrentRunLevel;
    }

    public void setCurrentRunLevel(float f) {
        this.mCurrentRunLevel = f;
    }

    public float getRealRunLevel() {
        return this.mRealRunLevel;
    }

    public void setRealRunLevel(float f) {
        this.mRealRunLevel = f;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.mCurrentRunLevel);
        parcel.writeFloat(this.mRealRunLevel);
    }
}
