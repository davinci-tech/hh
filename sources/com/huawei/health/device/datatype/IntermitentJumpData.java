package com.huawei.health.device.datatype;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class IntermitentJumpData implements Parcelable {
    public static final Parcelable.Creator<IntermitentJumpData> CREATOR = new Parcelable.Creator<IntermitentJumpData>() { // from class: com.huawei.health.device.datatype.IntermitentJumpData.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: Er_, reason: merged with bridge method [inline-methods] */
        public IntermitentJumpData createFromParcel(Parcel parcel) {
            return new IntermitentJumpData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public IntermitentJumpData[] newArray(int i) {
            return new IntermitentJumpData[i];
        }
    };
    private int mIntermittentJumpBreakTime;
    private int mIntermittentJumpExerciseNum;
    private int mIntermittentJumpExerciseTime;
    private int mIntermittentJumpGroups;
    private int mIntermittentJumpMode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IntermitentJumpData() {
    }

    protected IntermitentJumpData(Parcel parcel) {
        this.mIntermittentJumpMode = parcel.readInt();
        this.mIntermittentJumpExerciseTime = parcel.readInt();
        this.mIntermittentJumpExerciseNum = parcel.readInt();
        this.mIntermittentJumpBreakTime = parcel.readInt();
        this.mIntermittentJumpGroups = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeInt(this.mIntermittentJumpMode);
            parcel.writeInt(this.mIntermittentJumpExerciseNum);
            parcel.writeInt(this.mIntermittentJumpExerciseTime);
            parcel.writeInt(this.mIntermittentJumpBreakTime);
            parcel.writeInt(this.mIntermittentJumpGroups);
        }
    }

    public int getIntermittentJumpMode() {
        return this.mIntermittentJumpMode;
    }

    public void setIntermittentJumpMode(int i) {
        this.mIntermittentJumpMode = i;
    }

    public int getIntermittentJumpExerciseTime() {
        return this.mIntermittentJumpExerciseTime;
    }

    public void setIntermittentJumpExerciseTime(int i) {
        this.mIntermittentJumpExerciseTime = i;
    }

    public int getIntermittentJumpExerciseNum() {
        return this.mIntermittentJumpExerciseNum;
    }

    public void setIntermittentJumpExerciseNum(int i) {
        this.mIntermittentJumpExerciseNum = i;
    }

    public int getIntermittentJumpBreakTime() {
        return this.mIntermittentJumpBreakTime;
    }

    public void setIntermittentJumpBreakTime(int i) {
        this.mIntermittentJumpBreakTime = i;
    }

    public int getIntermittentJumpGroups() {
        return this.mIntermittentJumpGroups;
    }

    public void setIntermittentJumpGroups(int i) {
        this.mIntermittentJumpGroups = i;
    }
}
