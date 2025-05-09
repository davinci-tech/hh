package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import defpackage.jdy;

/* loaded from: classes5.dex */
public class TriathlonStruct implements Parcelable {
    public static final Parcelable.Creator<TriathlonStruct> CREATOR = new Parcelable.Creator<TriathlonStruct>() { // from class: com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.TriathlonStruct.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TriathlonStruct createFromParcel(Parcel parcel) {
            return new TriathlonStruct(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TriathlonStruct[] newArray(int i) {
            return new TriathlonStruct[i];
        }
    };

    @SerializedName("workoutLinkHasDetai")
    private boolean mIsWorkoutLinkHasDetail;

    @SerializedName("workoutLinkDetailsCalorie")
    private int mWorkoutLinkDetailsCalorie;

    @SerializedName("workoutLinkDetailsDistance")
    private int mWorkoutLinkDetailsDistance;

    @SerializedName("workoutLinkDetailsEndtime")
    private int mWorkoutLinkDetailsEndTime;

    @SerializedName("workoutLinkDetailsStarttime")
    private int mWorkoutLinkDetailsStartTime;

    @SerializedName("workoutLinkDetailsTotaltime")
    private int mWorkoutLinkDetailsTotalTime;

    @SerializedName("workoutLinkDetailsType")
    private int mWorkoutLinkDetailsType;

    @SerializedName("workoutLinkTransitionTime")
    private int mWorkoutLinkTransitionTime;

    @SerializedName("workoutRelationType")
    private int mWorkoutRelationType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TriathlonStruct() {
    }

    protected TriathlonStruct(Parcel parcel) {
        this.mWorkoutRelationType = parcel.readInt();
        this.mWorkoutLinkDetailsType = parcel.readInt();
        this.mWorkoutLinkDetailsStartTime = parcel.readInt();
        this.mWorkoutLinkDetailsEndTime = parcel.readInt();
        this.mWorkoutLinkDetailsTotalTime = parcel.readInt();
        this.mWorkoutLinkDetailsDistance = parcel.readInt();
        this.mWorkoutLinkDetailsCalorie = parcel.readInt();
        this.mWorkoutLinkTransitionTime = parcel.readInt();
        this.mIsWorkoutLinkHasDetail = parcel.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mWorkoutRelationType);
        parcel.writeInt(this.mWorkoutLinkDetailsType);
        parcel.writeInt(this.mWorkoutLinkDetailsStartTime);
        parcel.writeInt(this.mWorkoutLinkDetailsEndTime);
        parcel.writeInt(this.mWorkoutLinkDetailsTotalTime);
        parcel.writeInt(this.mWorkoutLinkDetailsDistance);
        parcel.writeInt(this.mWorkoutLinkDetailsCalorie);
        parcel.writeInt(this.mWorkoutLinkTransitionTime);
        parcel.writeByte(this.mIsWorkoutLinkHasDetail ? (byte) 1 : (byte) 0);
    }

    public int getWorkoutRelationType() {
        return ((Integer) jdy.d(Integer.valueOf(this.mWorkoutRelationType))).intValue();
    }

    public void setWorkoutRelationType(int i) {
        this.mWorkoutRelationType = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getWorkoutLinkDetailsType() {
        return ((Integer) jdy.d(Integer.valueOf(this.mWorkoutLinkDetailsType))).intValue();
    }

    public void setWorkoutLinkDetailsType(int i) {
        this.mWorkoutLinkDetailsType = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getWorkoutLinkDetailsStartTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.mWorkoutLinkDetailsStartTime))).intValue();
    }

    public void setWorkoutLinkDetailsStartTime(int i) {
        this.mWorkoutLinkDetailsStartTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getWorkoutLinkDetailsEndTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.mWorkoutLinkDetailsEndTime))).intValue();
    }

    public void setWorkoutLinkDetailsEndTime(int i) {
        this.mWorkoutLinkDetailsEndTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getWorkoutLinkDetailsTotalTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.mWorkoutLinkDetailsTotalTime))).intValue();
    }

    public void setWorkoutLinkDetailsTotalTime(int i) {
        this.mWorkoutLinkDetailsTotalTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getWorkoutLinkDetailsDistance() {
        return ((Integer) jdy.d(Integer.valueOf(this.mWorkoutLinkDetailsDistance))).intValue();
    }

    public void setWorkoutLinkDetailsDistance(int i) {
        this.mWorkoutLinkDetailsDistance = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getWorkoutLinkDetailsCalorie() {
        return ((Integer) jdy.d(Integer.valueOf(this.mWorkoutLinkDetailsCalorie))).intValue();
    }

    public void setWorkoutLinkDetailsCalorie(int i) {
        this.mWorkoutLinkDetailsCalorie = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getWorkoutLinkTransitionTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.mWorkoutLinkTransitionTime))).intValue();
    }

    public void setWorkoutLinkTransitionTime(int i) {
        this.mWorkoutLinkTransitionTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public boolean isWorkoutLinkHasDetail() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.mIsWorkoutLinkHasDetail))).booleanValue();
    }

    public void setWorkoutLinkHasDetail(boolean z) {
        this.mIsWorkoutLinkHasDetail = ((Boolean) jdy.d(Boolean.valueOf(z))).booleanValue();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(0);
        stringBuffer.append("TriathlonStruct{workoutRelationType=").append(this.mWorkoutRelationType).append(", workoutLinkDetailsType=").append(this.mWorkoutLinkDetailsType).append(", workoutLinkDetailsStartTime=").append(this.mWorkoutLinkDetailsStartTime).append(", workoutLinkDetailsEndTime=").append(this.mWorkoutLinkDetailsEndTime).append(", workoutLinkDetailsTotalTime=").append(this.mWorkoutLinkDetailsTotalTime).append(", workoutLinkDetailsDistance=").append(this.mWorkoutLinkDetailsDistance).append(", workoutLinkDetailsCalorie=").append(this.mWorkoutLinkDetailsCalorie).append(", workoutLinkTransitionTime=").append(this.mWorkoutLinkTransitionTime).append(", workoutLinkHasDetail=").append(this.mIsWorkoutLinkHasDetail).append("}");
        return stringBuffer.toString();
    }
}
