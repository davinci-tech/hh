package com.huawei.health.device.datatype;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class SkippingTargetMode implements Parcelable {
    public static final Parcelable.Creator<SkippingTargetMode> CREATOR = new Parcelable.Creator<SkippingTargetMode>() { // from class: com.huawei.health.device.datatype.SkippingTargetMode.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: Es_, reason: merged with bridge method [inline-methods] */
        public SkippingTargetMode createFromParcel(Parcel parcel) {
            return new SkippingTargetMode(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public SkippingTargetMode[] newArray(int i) {
            return new SkippingTargetMode[i];
        }
    };
    private int mGoal;
    private IntermitentJumpData mIntermitentJumpData;
    private int mStartMode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SkippingTargetMode() {
        this.mStartMode = 6;
        this.mGoal = 0;
    }

    public SkippingTargetMode(Parcel parcel) {
        this.mStartMode = 6;
        this.mGoal = 0;
        this.mStartMode = parcel.readInt();
        this.mGoal = parcel.readInt();
        this.mIntermitentJumpData = (IntermitentJumpData) parcel.readParcelable(IntermitentJumpData.class.getClassLoader());
    }

    public SkippingTargetMode(int i, int i2, IntermitentJumpData intermitentJumpData) {
        this.mStartMode = i;
        this.mGoal = i2;
        this.mIntermitentJumpData = intermitentJumpData;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mStartMode);
        parcel.writeInt(this.mGoal);
        parcel.writeParcelable(this.mIntermitentJumpData, i);
    }

    public int getStartMode() {
        return this.mStartMode;
    }

    public void setStartMode(int i) {
        this.mStartMode = i;
    }

    public int getGoal() {
        return this.mGoal;
    }

    public void setGoal(int i) {
        this.mGoal = i;
    }

    public IntermitentJumpData getIntermitentJumpData() {
        return this.mIntermitentJumpData;
    }

    public void setIntermitentJumpData(IntermitentJumpData intermitentJumpData) {
        this.mIntermitentJumpData = intermitentJumpData;
    }
}
