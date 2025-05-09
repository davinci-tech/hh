package com.huawei.ui.main.stories.health.interactors.healthdata;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class RunningStateIndexData implements Parcelable {
    public static final Parcelable.Creator<RunningStateIndexData> CREATOR = new Parcelable.Creator<RunningStateIndexData>() { // from class: com.huawei.ui.main.stories.health.interactors.healthdata.RunningStateIndexData.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: dEO_, reason: merged with bridge method [inline-methods] */
        public RunningStateIndexData createFromParcel(Parcel parcel) {
            return new RunningStateIndexData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public RunningStateIndexData[] newArray(int i) {
            return new RunningStateIndexData[i];
        }
    };
    private Map<Integer, RunningLevelSomeDayData> mData = new HashMap();
    private RunningLevelCurrentData mRunningLevelCurrentData;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RunningStateIndexData() {
    }

    protected RunningStateIndexData(Parcel parcel) {
        this.mRunningLevelCurrentData = (RunningLevelCurrentData) parcel.readParcelable(RunningLevelCurrentData.class.getClassLoader());
        parcel.readMap(this.mData, RunningLevelSomeDayData.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mRunningLevelCurrentData, i);
        parcel.writeMap(this.mData);
    }

    public Map<Integer, RunningLevelSomeDayData> getData() {
        return this.mData;
    }

    public void setData(Map<Integer, RunningLevelSomeDayData> map) {
        this.mData = map;
    }

    public RunningLevelCurrentData getRunningLevelCurrentData() {
        return this.mRunningLevelCurrentData;
    }

    public void setRunningLevelCurrentData(RunningLevelCurrentData runningLevelCurrentData) {
        this.mRunningLevelCurrentData = runningLevelCurrentData;
    }

    public String toString() {
        return "RunningStateIndexData{data=" + this.mData + ", runningLevelCurrentData=" + this.mRunningLevelCurrentData + '}';
    }
}
