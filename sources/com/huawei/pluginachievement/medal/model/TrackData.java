package com.huawei.pluginachievement.medal.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class TrackData implements Parcelable {
    public static final Parcelable.Creator<TrackData> CREATOR = new Parcelable.Creator<TrackData>() { // from class: com.huawei.pluginachievement.medal.model.TrackData.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: cgN_, reason: merged with bridge method [inline-methods] */
        public TrackData createFromParcel(Parcel parcel) {
            return new TrackData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public TrackData[] newArray(int i) {
            return new TrackData[i];
        }
    };
    private float mBestPace;
    private float mDistance;
    private long mEndTime;
    private Map<Integer, Float> mPaceMap;
    private Map<Double, Double> mPartTimeMap;
    private int mSportDataSource;
    private long mTrackTime;
    private int mType;
    private double mValue;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TrackData() {
        this.mPartTimeMap = new HashMap(0);
        this.mSportDataSource = 0;
        this.mPaceMap = new HashMap(0);
    }

    public TrackData(Parcel parcel) {
        this.mPartTimeMap = new HashMap(0);
        this.mSportDataSource = 0;
        this.mPaceMap = new HashMap(0);
        this.mDistance = parcel.readFloat();
        this.mType = parcel.readInt();
        this.mTrackTime = parcel.readLong();
        this.mEndTime = parcel.readLong();
        parcel.readMap(this.mPartTimeMap, Double.class.getClassLoader());
        parcel.readMap(this.mPaceMap, Double.class.getClassLoader());
        this.mSportDataSource = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.mDistance);
        parcel.writeInt(this.mType);
        parcel.writeLong(this.mTrackTime);
        parcel.writeLong(this.mEndTime);
        parcel.writeMap(this.mPartTimeMap);
        parcel.writeMap(this.mPaceMap);
        parcel.writeInt(this.mSportDataSource);
    }

    public float acquireDistance() {
        return this.mDistance;
    }

    public void saveDistance(float f) {
        this.mDistance = f;
    }

    public int acquireType() {
        return this.mType;
    }

    public void saveType(int i) {
        this.mType = i;
    }

    public long acquireTrackTime() {
        return this.mTrackTime;
    }

    public void saveTrackTime(long j) {
        this.mTrackTime = j;
    }

    public long acquireEndTime() {
        return this.mEndTime;
    }

    public void saveEndTime(long j) {
        this.mEndTime = j;
    }

    public Map<Double, Double> acquirePartTimeMap() {
        return this.mPartTimeMap;
    }

    public void savePartTimeMap(Map<Double, Double> map) {
        this.mPartTimeMap = map;
    }

    public int acquireSportDataSource() {
        return this.mSportDataSource;
    }

    public void saveSportDataSource(int i) {
        this.mSportDataSource = i;
    }

    public Map<Integer, Float> acquirePaceMap() {
        return this.mPaceMap;
    }

    public void savePaceMap(Map<Integer, Float> map) {
        this.mPaceMap = map;
    }

    public float acquireBestPace() {
        return this.mBestPace;
    }

    public void saveBestPace(float f) {
        this.mBestPace = f;
    }

    public double acquireValue() {
        return this.mValue;
    }

    public void saveValue(double d) {
        this.mValue = d;
    }
}
