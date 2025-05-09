package com.huawei.pluginachievement.manager.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class TrackData implements Parcelable {
    public static final Parcelable.Creator<TrackData> CREATOR = new Parcelable.Creator<TrackData>() { // from class: com.huawei.pluginachievement.manager.model.TrackData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TrackData createFromParcel(Parcel parcel) {
            return new TrackData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TrackData[] newArray(int i) {
            return new TrackData[i];
        }
    };
    private float bestPace;
    private float distance;
    private long endTime;
    private int maxSkippingTimes;
    private Map<Integer, Float> paceMap;
    private Map<Double, Double> partTimeMap;
    private int skipSpeed;
    private int sportDataSource;
    private long totalTime;
    private int trackNum;
    private long trackTime;
    private int type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TrackData() {
        this.totalTime = 0L;
        this.partTimeMap = new HashMap(0);
        this.sportDataSource = 0;
        this.paceMap = new HashMap(0);
    }

    public TrackData(Parcel parcel) {
        this.totalTime = 0L;
        this.partTimeMap = new HashMap(0);
        this.sportDataSource = 0;
        this.paceMap = new HashMap(0);
        this.distance = parcel.readFloat();
        this.type = parcel.readInt();
        this.trackTime = parcel.readLong();
        this.endTime = parcel.readLong();
        this.totalTime = parcel.readLong();
        parcel.readMap(this.partTimeMap, Double.class.getClassLoader());
        parcel.readMap(this.paceMap, Double.class.getClassLoader());
        this.sportDataSource = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.distance);
        parcel.writeInt(this.type);
        parcel.writeLong(this.trackTime);
        parcel.writeLong(this.endTime);
        parcel.writeLong(this.totalTime);
        parcel.writeMap(this.partTimeMap);
        parcel.writeMap(this.paceMap);
        parcel.writeInt(this.sportDataSource);
    }

    public float acquireDistance() {
        return this.distance;
    }

    public void saveDistance(float f) {
        this.distance = f;
    }

    public int acquireTrackNum() {
        return this.trackNum;
    }

    public void saveTrackNum(int i) {
        this.trackNum = i;
    }

    public int getMaxSkippingTimes() {
        return this.maxSkippingTimes;
    }

    public void setMaxSkippingTimes(int i) {
        this.maxSkippingTimes = i;
    }

    public int getSkipSpeed() {
        return this.skipSpeed;
    }

    public void setSkipSpeed(int i) {
        this.skipSpeed = i;
    }

    public int acquireType() {
        return this.type;
    }

    public void saveType(int i) {
        this.type = i;
    }

    public long acquireTrackTime() {
        return this.trackTime;
    }

    public void saveTrackTime(long j) {
        this.trackTime = j;
    }

    public long acquireEndTime() {
        return this.endTime;
    }

    public void saveEndTime(long j) {
        this.endTime = j;
    }

    public long getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(long j) {
        this.totalTime = j;
    }

    public Map<Double, Double> acquirePartTimeMap() {
        return this.partTimeMap;
    }

    public void savePartTimeMap(Map<Double, Double> map) {
        this.partTimeMap = map;
    }

    public void saveSportDataSource(int i) {
        this.sportDataSource = i;
    }

    public void savePaceMap(Map<Integer, Float> map) {
        this.paceMap = map;
    }

    public float acquireBestPace() {
        return this.bestPace;
    }

    public void saveBestPace(float f) {
        this.bestPace = f;
    }
}
