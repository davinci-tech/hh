package com.huawei.basefitnessadvice.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class TrainStatistics implements Parcelable {
    public static final Parcelable.Creator<TrainStatistics> CREATOR = new Parcelable.Creator<TrainStatistics>() { // from class: com.huawei.basefitnessadvice.model.TrainStatistics.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TrainStatistics createFromParcel(Parcel parcel) {
            return new TrainStatistics(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TrainStatistics[] newArray(int i) {
            return new TrainStatistics[i];
        }
    };
    private long calorie;
    private long duration;
    private long totalTimes;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TrainStatistics() {
        this.calorie = 0L;
        this.duration = 0L;
        this.totalTimes = 0L;
    }

    protected TrainStatistics(Parcel parcel) {
        this.calorie = parcel.readLong();
        this.duration = parcel.readLong();
        this.totalTimes = parcel.readLong();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.calorie);
        parcel.writeLong(this.duration);
        parcel.writeLong(this.totalTimes);
    }

    public long acquireCalorie() {
        return this.calorie;
    }

    public void saveCalorie(long j) {
        this.calorie = j;
    }

    public long acquireDuration() {
        return this.duration;
    }

    public void saveDuration(long j) {
        this.duration = j;
    }

    public long acquireTotalTimes() {
        return this.totalTimes;
    }

    public void saveTotalTimes(long j) {
        this.totalTimes = j;
    }
}
