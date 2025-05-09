package com.huawei.health.sportservice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.operation.ble.BleConstants;
import defpackage.moj;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class SportLaunchParams implements Parcelable {
    public static final Parcelable.Creator<SportLaunchParams> CREATOR = new Parcelable.Creator<SportLaunchParams>() { // from class: com.huawei.health.sportservice.SportLaunchParams.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: axy_, reason: merged with bridge method [inline-methods] */
        public SportLaunchParams createFromParcel(Parcel parcel) {
            return new SportLaunchParams(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public SportLaunchParams[] newArray(int i) {
            return new SportLaunchParams[i];
        }
    };

    @SerializedName("dataSource")
    private int mDataSource;

    @SerializedName(JsbMapKeyNames.H5_EXTRAS)
    private Map<String, String> mExtras;

    @SerializedName("isNeedRestart")
    private boolean mIsNeedRestart;

    @SerializedName("isRestart")
    private boolean mIsRestart;

    @SerializedName("launchActivity")
    private String mLaunchActivity;

    @SerializedName("sportDataCallback")
    private ISportDataCallback mSportDataCallback;

    @SerializedName("sportTarget")
    private int mSportTarget;

    @SerializedName(BleConstants.SPORT_TYPE)
    private int mSportType;

    @SerializedName(WorkoutRecord.Extend.COURSE_TARGET_VALUE)
    private float mTargetValue;

    @SerializedName("trackType")
    private int mTrackType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SportLaunchParams() {
        this.mSportType = 0;
        this.mDataSource = 0;
        this.mTrackType = 0;
        this.mSportTarget = -1;
        this.mTargetValue = -1.0f;
        this.mExtras = new HashMap();
    }

    protected SportLaunchParams(Parcel parcel) {
        this.mSportType = 0;
        this.mDataSource = 0;
        this.mTrackType = 0;
        this.mSportTarget = -1;
        this.mTargetValue = -1.0f;
        this.mExtras = new HashMap();
        if (parcel != null) {
            this.mSportType = parcel.readInt();
            this.mDataSource = parcel.readInt();
            this.mTrackType = parcel.readInt();
            this.mSportTarget = parcel.readInt();
            this.mTargetValue = parcel.readFloat();
            this.mLaunchActivity = parcel.readString();
            this.mIsNeedRestart = parcel.readByte() != 0;
            this.mIsRestart = parcel.readByte() != 0;
            this.mExtras = parcel.readHashMap(HashMap.class.getClassLoader());
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeInt(this.mSportType);
            parcel.writeInt(this.mDataSource);
            parcel.writeInt(this.mTrackType);
            parcel.writeInt(this.mSportTarget);
            parcel.writeFloat(this.mTargetValue);
            parcel.writeString(this.mLaunchActivity);
            parcel.writeByte(this.mIsNeedRestart ? (byte) 1 : (byte) 0);
            parcel.writeByte(this.mIsRestart ? (byte) 1 : (byte) 0);
            parcel.writeMap(this.mExtras);
        }
    }

    public SportLaunchParams setSportType(int i) {
        this.mSportType = i;
        return this;
    }

    public SportLaunchParams setDataSource(int i) {
        this.mDataSource = i;
        return this;
    }

    public SportLaunchParams setTrackType(int i) {
        this.mTrackType = i;
        return this;
    }

    public SportLaunchParams setTargetValue(float f) {
        this.mTargetValue = f;
        return this;
    }

    public SportLaunchParams setSportDataCallback(ISportDataCallback iSportDataCallback) {
        this.mSportDataCallback = iSportDataCallback;
        return this;
    }

    public SportLaunchParams setSportTarget(int i) {
        this.mSportTarget = i;
        return this;
    }

    public SportLaunchParams setLaunchActivity(String str) {
        this.mLaunchActivity = str;
        return this;
    }

    public SportLaunchParams setNeedRestart(boolean z) {
        this.mIsNeedRestart = z;
        return this;
    }

    public SportLaunchParams setRestart(boolean z) {
        this.mIsRestart = z;
        return this;
    }

    public SportLaunchParams addExtra(String str, Object obj) {
        this.mExtras.put(str, moj.e(obj));
        return this;
    }

    public int getSportType() {
        return this.mSportType;
    }

    public int getDataSource() {
        return this.mDataSource;
    }

    public int getTrackType() {
        return this.mTrackType;
    }

    public int getSportTarget() {
        return this.mSportTarget;
    }

    public float getTargetValue() {
        return this.mTargetValue;
    }

    public ISportDataCallback getSportDataCallback() {
        return this.mSportDataCallback;
    }

    public String getLaunchActivity() {
        return this.mLaunchActivity;
    }

    public boolean isNeedRestart() {
        return this.mIsNeedRestart;
    }

    public boolean isRestart() {
        return this.mIsRestart;
    }

    public <T> T getExtra(String str, Class<T> cls) {
        String str2 = this.mExtras.get(str);
        if (str2 == null || str2.isEmpty()) {
            return null;
        }
        return (T) moj.e(this.mExtras.get(str), cls);
    }

    public <T> T getExtra(String str, Class<T> cls, T t) {
        String str2 = this.mExtras.get(str);
        return (str2 == null || str2.isEmpty()) ? t : (T) moj.e(this.mExtras.get(str), cls);
    }

    public String toString() {
        return "SportLaunchParams{mSportType=" + this.mSportType + ", mDataSource=" + this.mDataSource + ", mTrackType=" + this.mTrackType + ", mSportTarget=" + this.mSportTarget + ", mTargetValue=" + this.mTargetValue + ", mSportDataCallback=" + this.mSportDataCallback + ", mLaunchActivity='" + this.mLaunchActivity + "', mIsNeedRestart=" + this.mIsNeedRestart + ", mIsRestart=" + this.mIsRestart + '}';
    }
}
