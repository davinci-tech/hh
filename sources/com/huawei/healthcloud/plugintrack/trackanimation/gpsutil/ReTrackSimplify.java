package com.huawei.healthcloud.plugintrack.trackanimation.gpsutil;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.hjg;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class ReTrackSimplify implements Parcelable, Serializable {
    public static final int ALTITUDE_CURVE = 33;
    private static final int COMPLETE_TRACK_FLAG = 1;
    public static final Parcelable.Creator<ReTrackSimplify> CREATOR = new Parcelable.Creator<ReTrackSimplify>() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.gpsutil.ReTrackSimplify.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ReTrackSimplify[] newArray(int i) {
            return new ReTrackSimplify[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: aYk_, reason: merged with bridge method [inline-methods] */
        public ReTrackSimplify createFromParcel(Parcel parcel) {
            return new ReTrackSimplify(parcel);
        }
    };
    public static final int ERROR_KEY = -1;
    public static final int HEART_RATE_CURVE = 36;
    private static final int INCOMPLETE_TRACK_FLAG = -1;
    public static final int PACE_CURVE = 35;
    public static final int SPEED_CURVE = 32;
    public static final int STEP_RATE_CURVE = 34;
    private static final String TAG = "ReTrackSimplify";
    public static final int TYPE_PACE = 16;
    public static final int TYPE_SPEED = 17;
    public static final int TYPE_STEP_RATE = 18;
    private static final long serialVersionUID = 8476191207284824705L;
    private int mAreaNumber;
    private double mAvgSpeedField;
    private int mAvgSpeedType;
    private LatLong mCenterPoint;
    private int mCurveType;
    private double mCycles;
    private int mDeviceId;
    private ArrayList<hjg> mHiHealthMarkerList;
    private Map<Integer, hjg> mHiHealthMarkerMap;
    private boolean mIsCompleteTrack;
    private double mLensTotalDistance;
    private Pair<Integer, Double> mMaxAltitude;
    private LatLong mMaxBoundary;
    private Pair<Integer, Double> mMaxHeartRate;
    private Pair<Integer, Double> mMaxMultiplexField;
    private Pair<Integer, Double> mMaxSpeedField;
    private int mMaxSpeedType;
    private LatLong mMinBoundary;
    private Pair<Integer, Double> mMinMultiplexField;
    private String mProductId;
    private int mSportType;
    private long mStartTimeStamp;
    private double mTotalCalories;
    private long mTotalTime;
    private double mTrackTotalDistance;
    private double mValidTotalDistance;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ReTrackSimplify() {
        Double valueOf = Double.valueOf(0.0d);
        this.mMaxBoundary = new LatLong(0.0d, 0.0d);
        this.mMinBoundary = new LatLong(0.0d, 0.0d);
        this.mCenterPoint = new LatLong(0.0d, 0.0d);
        this.mStartTimeStamp = 0L;
        this.mTotalTime = 0L;
        this.mValidTotalDistance = 0.0d;
        this.mTrackTotalDistance = 0.0d;
        this.mLensTotalDistance = 0.0d;
        this.mCycles = 0.0d;
        this.mTotalCalories = 0.0d;
        this.mAvgSpeedField = 0.0d;
        this.mSportType = 0;
        this.mCurveType = 0;
        this.mMaxSpeedType = 0;
        this.mAvgSpeedType = 0;
        this.mMaxHeartRate = new Pair<>(-1, valueOf);
        this.mMaxAltitude = new Pair<>(-1, valueOf);
        this.mMaxSpeedField = new Pair<>(-1, valueOf);
        this.mMaxMultiplexField = new Pair<>(-1, valueOf);
        this.mMinMultiplexField = new Pair<>(-1, valueOf);
        this.mAreaNumber = 0;
        this.mHiHealthMarkerList = new ArrayList<>();
        this.mHiHealthMarkerMap = new HashMap();
    }

    public ReTrackSimplify(Parcel parcel) {
        Double valueOf = Double.valueOf(0.0d);
        this.mMaxBoundary = new LatLong(0.0d, 0.0d);
        this.mMinBoundary = new LatLong(0.0d, 0.0d);
        this.mCenterPoint = new LatLong(0.0d, 0.0d);
        this.mStartTimeStamp = 0L;
        this.mTotalTime = 0L;
        this.mValidTotalDistance = 0.0d;
        this.mTrackTotalDistance = 0.0d;
        this.mLensTotalDistance = 0.0d;
        this.mCycles = 0.0d;
        this.mTotalCalories = 0.0d;
        this.mAvgSpeedField = 0.0d;
        this.mSportType = 0;
        this.mCurveType = 0;
        this.mMaxSpeedType = 0;
        this.mAvgSpeedType = 0;
        this.mMaxHeartRate = new Pair<>(-1, valueOf);
        this.mMaxAltitude = new Pair<>(-1, valueOf);
        this.mMaxSpeedField = new Pair<>(-1, valueOf);
        this.mMaxMultiplexField = new Pair<>(-1, valueOf);
        this.mMinMultiplexField = new Pair<>(-1, valueOf);
        this.mAreaNumber = 0;
        this.mHiHealthMarkerList = new ArrayList<>();
        this.mHiHealthMarkerMap = new HashMap();
        this.mMaxBoundary = new LatLong(parcel.readDouble(), parcel.readDouble());
        this.mMinBoundary = new LatLong(parcel.readDouble(), parcel.readDouble());
        this.mCenterPoint = new LatLong(parcel.readDouble(), parcel.readDouble());
        this.mStartTimeStamp = parcel.readLong();
        this.mTotalTime = parcel.readLong();
        this.mValidTotalDistance = parcel.readDouble();
        this.mTrackTotalDistance = parcel.readDouble();
        this.mLensTotalDistance = parcel.readDouble();
        this.mTotalCalories = parcel.readDouble();
        this.mAvgSpeedField = parcel.readDouble();
        this.mCycles = parcel.readDouble();
        this.mSportType = parcel.readInt();
        this.mCurveType = parcel.readInt();
        this.mMaxSpeedType = parcel.readInt();
        this.mAvgSpeedType = parcel.readInt();
        this.mDeviceId = parcel.readInt();
        this.mIsCompleteTrack = parcel.readInt() == 1;
        this.mMaxHeartRate = new Pair<>(Integer.valueOf(parcel.readInt()), Double.valueOf(parcel.readDouble()));
        this.mMaxAltitude = new Pair<>(Integer.valueOf(parcel.readInt()), Double.valueOf(parcel.readDouble()));
        this.mMaxSpeedField = new Pair<>(Integer.valueOf(parcel.readInt()), Double.valueOf(parcel.readDouble()));
        this.mMaxMultiplexField = new Pair<>(Integer.valueOf(parcel.readInt()), Double.valueOf(parcel.readDouble()));
        this.mMinMultiplexField = new Pair<>(Integer.valueOf(parcel.readInt()), Double.valueOf(parcel.readDouble()));
        this.mAreaNumber = parcel.readInt();
        this.mProductId = parcel.readString();
        parcel.readList(this.mHiHealthMarkerList, hjg.class.getClassLoader());
        parcel.readMap(this.mHiHealthMarkerMap, hjg.class.getClassLoader());
    }

    public void syncMaxValue() {
        int i = this.mCurveType;
        if ((i == 32 || i == 34) && ((Integer) this.mMaxSpeedField.first).intValue() != -1) {
            this.mMaxSpeedField = new Pair<>(Integer.valueOf(((Integer) this.mMaxMultiplexField.first).intValue()), (Double) this.mMaxSpeedField.second);
        }
        if (this.mCurveType == 33 && ((Integer) this.mMaxAltitude.first).intValue() != -1) {
            this.mMaxAltitude = new Pair<>(Integer.valueOf(((Integer) this.mMaxMultiplexField.first).intValue()), (Double) this.mMaxAltitude.second);
        }
        if (this.mCurveType != 36 || ((Integer) this.mMaxHeartRate.first).intValue() == -1) {
            return;
        }
        this.mMaxHeartRate = new Pair<>(Integer.valueOf(((Integer) this.mMaxMultiplexField.first).intValue()), (Double) this.mMaxHeartRate.second);
    }

    public ReTrackSimplify setMaxBoundary(LatLong latLong) {
        if (latLong != null) {
            this.mMaxBoundary = latLong;
        } else {
            LogUtil.h(TAG, "maxBoundary is null");
        }
        return this;
    }

    public ReTrackSimplify setMinBoundary(LatLong latLong) {
        if (latLong != null) {
            this.mMinBoundary = latLong;
        } else {
            LogUtil.h(TAG, "minBoundary is null");
        }
        return this;
    }

    public ReTrackSimplify setCenterPoint(LatLong latLong) {
        if (latLong != null) {
            this.mCenterPoint = latLong;
        } else {
            LogUtil.h(TAG, "centerPoint is null");
        }
        return this;
    }

    public ReTrackSimplify setCycles(double d) {
        this.mCycles = d;
        return this;
    }

    public ReTrackSimplify setStartTimeStamp(long j) {
        this.mStartTimeStamp = j;
        return this;
    }

    public ReTrackSimplify setValidTotalDistance(double d) {
        this.mValidTotalDistance = Math.round(Math.abs(d) / 10.0d) * 10.0d;
        return this;
    }

    public ReTrackSimplify setTrackTotalDistance(double d) {
        this.mTrackTotalDistance = d;
        return this;
    }

    public ReTrackSimplify setLensTotalDistance(double d) {
        this.mLensTotalDistance = d;
        return this;
    }

    public ReTrackSimplify setTotalTime(long j) {
        this.mTotalTime = j;
        return this;
    }

    public ReTrackSimplify setAvgSpeedField(double d) {
        this.mAvgSpeedField = d;
        return this;
    }

    public ReTrackSimplify setTotalCalories(double d) {
        this.mTotalCalories = d;
        return this;
    }

    public ReTrackSimplify setSportType(int i) {
        this.mSportType = i;
        return this;
    }

    public ReTrackSimplify setCurveType(int i) {
        this.mCurveType = i;
        return this;
    }

    public ReTrackSimplify setMaxSpeedType(int i) {
        this.mMaxSpeedType = i;
        return this;
    }

    public ReTrackSimplify setAvgSpeedType(int i) {
        this.mAvgSpeedType = i;
        return this;
    }

    public ReTrackSimplify setMaxHeartRate(Pair<Integer, Double> pair) {
        if (pair != null) {
            this.mMaxHeartRate = pair;
        } else {
            LogUtil.h(TAG, "maxHeartRate is null");
        }
        return this;
    }

    public ReTrackSimplify setMaxSpeedField(Pair<Integer, Double> pair) {
        if (pair != null) {
            this.mMaxSpeedField = pair;
        } else {
            LogUtil.h(TAG, "maxSpeed is null");
        }
        return this;
    }

    public ReTrackSimplify setMaxAltitude(Pair<Integer, Double> pair) {
        if (pair != null) {
            this.mMaxAltitude = pair;
        } else {
            LogUtil.h(TAG, "maxHeight is null");
        }
        return this;
    }

    public ReTrackSimplify setMaxMultiplexField(Pair<Integer, Double> pair) {
        if (pair != null) {
            this.mMaxMultiplexField = pair;
        } else {
            LogUtil.h(TAG, "maxMultiplexField is null");
        }
        return this;
    }

    public ReTrackSimplify setMinMultiplexField(Pair<Integer, Double> pair) {
        if (pair != null) {
            this.mMinMultiplexField = pair;
        } else {
            LogUtil.h(TAG, "minMultiplexField is null");
        }
        return this;
    }

    public ReTrackSimplify setDeviceId(int i) {
        this.mDeviceId = i;
        return this;
    }

    public ReTrackSimplify setProductId(String str) {
        this.mProductId = str;
        return this;
    }

    public ReTrackSimplify setIsTrackComplete(boolean z) {
        this.mIsCompleteTrack = z;
        return this;
    }

    public ReTrackSimplify setAreaNumber(int i) {
        this.mAreaNumber = i;
        return this;
    }

    public Map<Integer, hjg> getHiHealthMarkerMap() {
        return this.mHiHealthMarkerMap;
    }

    public void setHiHealthMarkerMap(Map<Integer, hjg> map) {
        if (this.mHiHealthMarkerMap == null) {
            this.mHiHealthMarkerMap = new HashMap();
        }
        this.mHiHealthMarkerMap.putAll(map);
    }

    public ArrayList<hjg> getHiHealthMarkerList() {
        return this.mHiHealthMarkerList;
    }

    public void setHiHealthMarkerList(ArrayList<hjg> arrayList) {
        this.mHiHealthMarkerList = arrayList;
    }

    public Pair<Integer, Double> getMinMultiplexField() {
        return this.mMinMultiplexField;
    }

    public Pair<Integer, Double> getMaxMultiplexField() {
        return this.mMaxMultiplexField;
    }

    public LatLong getMaxBoundary() {
        return this.mMaxBoundary;
    }

    public LatLong getMinBoundary() {
        return this.mMinBoundary;
    }

    public LatLong getCenterPoint() {
        return this.mCenterPoint;
    }

    public long getStartTimeStamp() {
        return this.mStartTimeStamp;
    }

    public double getValidTotalDistance() {
        return this.mValidTotalDistance;
    }

    public double getTrackTotalDistance() {
        return this.mTrackTotalDistance;
    }

    public double getLensTotalDistance() {
        return this.mLensTotalDistance;
    }

    public double getCycles() {
        return this.mCycles;
    }

    public long getTotalTime() {
        return this.mTotalTime;
    }

    public double getAvgSpeedField() {
        return this.mAvgSpeedField;
    }

    public double getTotalCalories() {
        return this.mTotalCalories;
    }

    public int getSportType() {
        return this.mSportType;
    }

    public int getCurveType() {
        return this.mCurveType;
    }

    public int getMaxSpeedType() {
        return this.mMaxSpeedType;
    }

    public int getAvgSpeedType() {
        return this.mAvgSpeedType;
    }

    public Pair<Integer, Double> getMaxHeartRate() {
        return this.mMaxHeartRate;
    }

    public Pair<Integer, Double> getMaxSpeed() {
        return this.mMaxSpeedField;
    }

    public Pair<Integer, Double> getMaxAltitude() {
        return this.mMaxAltitude;
    }

    public int getDeviceId() {
        return this.mDeviceId;
    }

    public String getProductId() {
        return this.mProductId;
    }

    public boolean getIsCompleteTrack() {
        return this.mIsCompleteTrack;
    }

    public int getAreaNumber() {
        return this.mAreaNumber;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.mMaxBoundary.getLatLng().b);
        parcel.writeDouble(this.mMaxBoundary.getLatLng().d);
        parcel.writeDouble(this.mMinBoundary.getLatLng().b);
        parcel.writeDouble(this.mMinBoundary.getLatLng().d);
        parcel.writeDouble(this.mCenterPoint.getLatLng().b);
        parcel.writeDouble(this.mCenterPoint.getLatLng().d);
        parcel.writeLong(this.mStartTimeStamp);
        parcel.writeLong(this.mTotalTime);
        parcel.writeDouble(this.mValidTotalDistance);
        parcel.writeDouble(this.mTrackTotalDistance);
        parcel.writeDouble(this.mLensTotalDistance);
        parcel.writeDouble(this.mTotalCalories);
        parcel.writeDouble(this.mAvgSpeedField);
        parcel.writeDouble(this.mCycles);
        parcel.writeInt(this.mSportType);
        parcel.writeInt(this.mCurveType);
        parcel.writeInt(this.mMaxSpeedType);
        parcel.writeInt(this.mAvgSpeedType);
        parcel.writeInt(this.mDeviceId);
        parcel.writeInt(this.mIsCompleteTrack ? 1 : -1);
        parcel.writeInt(((Integer) this.mMaxHeartRate.first).intValue());
        parcel.writeDouble(((Double) this.mMaxHeartRate.second).doubleValue());
        parcel.writeInt(((Integer) this.mMaxAltitude.first).intValue());
        parcel.writeDouble(((Double) this.mMaxAltitude.second).doubleValue());
        parcel.writeInt(((Integer) this.mMaxSpeedField.first).intValue());
        parcel.writeDouble(((Double) this.mMaxSpeedField.second).doubleValue());
        parcel.writeInt(((Integer) this.mMaxMultiplexField.first).intValue());
        parcel.writeDouble(((Double) this.mMaxMultiplexField.second).doubleValue());
        parcel.writeInt(((Integer) this.mMinMultiplexField.first).intValue());
        parcel.writeDouble(((Double) this.mMinMultiplexField.second).doubleValue());
        parcel.writeInt(this.mAreaNumber);
        parcel.writeString(this.mProductId);
        parcel.writeList(this.mHiHealthMarkerList);
        parcel.writeMap(this.mHiHealthMarkerMap);
    }
}
