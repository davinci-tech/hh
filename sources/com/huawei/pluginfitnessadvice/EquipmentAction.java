package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class EquipmentAction implements Parcelable {
    public static final Parcelable.Creator<EquipmentAction> CREATOR = new Parcelable.Creator<EquipmentAction>() { // from class: com.huawei.pluginfitnessadvice.EquipmentAction.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: clO_, reason: merged with bridge method [inline-methods] */
        public EquipmentAction createFromParcel(Parcel parcel) {
            return new EquipmentAction(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public EquipmentAction[] newArray(int i) {
            return new EquipmentAction[i];
        }
    };
    private static final String TAG = "Fitness_EquipmentAction";

    @SerializedName("actionName")
    private String mActionName;

    @SerializedName("calorie")
    private int mCalorie;

    @SerializedName("endTimeLong")
    private long mEndTime;

    @SerializedName("maxRpm")
    private int mMaxRpm;

    @SerializedName("minRpm")
    private int mMinRpm;

    @SerializedName("resistance")
    private int mResistance;

    @SerializedName("slope")
    private int mSlope;

    @SerializedName("speed")
    private double mSpeed;

    @SerializedName("startTimeLong")
    private long mStartTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected EquipmentAction(Parcel parcel) {
        if (parcel == null) {
            LogUtil.h(TAG, "EquipmentAction in == null");
            return;
        }
        this.mActionName = parcel.readString();
        this.mStartTime = parcel.readLong();
        this.mEndTime = parcel.readLong();
        this.mSpeed = parcel.readDouble();
        this.mSlope = parcel.readInt();
        this.mCalorie = parcel.readInt();
        this.mResistance = parcel.readInt();
        this.mMaxRpm = parcel.readInt();
        this.mMinRpm = parcel.readInt();
    }

    private EquipmentAction(a aVar) {
        this.mActionName = aVar.f8473a;
        this.mStartTime = aVar.j;
        this.mEndTime = aVar.b;
        this.mSpeed = aVar.h;
        this.mSlope = aVar.i;
        this.mCalorie = aVar.d;
        this.mResistance = aVar.g;
        this.mMaxRpm = aVar.c;
        this.mMinRpm = aVar.e;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
            return;
        }
        parcel.writeString(this.mActionName);
        parcel.writeLong(this.mStartTime);
        parcel.writeLong(this.mEndTime);
        parcel.writeDouble(this.mSpeed);
        parcel.writeInt(this.mSlope);
        parcel.writeInt(this.mCalorie);
        parcel.writeInt(this.mResistance);
        parcel.writeInt(this.mMaxRpm);
        parcel.writeInt(this.mMinRpm);
    }

    /* loaded from: classes9.dex */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private String f8473a;
        private long b;
        private int c;
        private int d;
        private int e;
        private int g;
        private double h;
        private int i;
        private long j;
    }

    public String getActionName() {
        return this.mActionName;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public long getEndTime() {
        return this.mEndTime;
    }

    public double getSpeed() {
        return this.mSpeed;
    }

    public int getSlope() {
        return this.mSlope;
    }

    public int getCalorie() {
        return this.mCalorie;
    }

    public int getResistance() {
        return this.mResistance;
    }

    public int getMaxRpm() {
        return this.mMaxRpm;
    }

    public int getMinRpm() {
        return this.mMinRpm;
    }
}
