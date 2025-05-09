package com.huawei.ui.main.stories.health.interactors.healthdata;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes6.dex */
public class StateIndexDayData implements Parcelable {
    public static final Parcelable.Creator<StateIndexDayData> CREATOR = new Parcelable.Creator<StateIndexDayData>() { // from class: com.huawei.ui.main.stories.health.interactors.healthdata.StateIndexDayData.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: dEZ_, reason: merged with bridge method [inline-methods] */
        public StateIndexDayData createFromParcel(Parcel parcel) {
            return new StateIndexDayData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public StateIndexDayData[] newArray(int i) {
            return new StateIndexDayData[i];
        }
    };
    private float mCondition;
    private float mFatigue;
    private float mFitness;
    private float mTotalPoint;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public StateIndexDayData() {
    }

    protected StateIndexDayData(Parcel parcel) {
        this.mTotalPoint = parcel.readFloat();
        this.mFitness = parcel.readFloat();
        this.mFatigue = parcel.readFloat();
        this.mCondition = parcel.readFloat();
    }

    public float getTotalPoint() {
        return this.mTotalPoint;
    }

    public void setTotalPoint(float f) {
        this.mTotalPoint = f;
    }

    public float getFitness() {
        return this.mFitness;
    }

    public void setFitness(float f) {
        this.mFitness = f;
    }

    public float getFatigue() {
        return this.mFatigue;
    }

    public void setFatigue(float f) {
        this.mFatigue = f;
    }

    public float getCondition() {
        return this.mCondition;
    }

    public void setCondition(float f) {
        this.mCondition = f;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.mTotalPoint);
        parcel.writeFloat(this.mFitness);
        parcel.writeFloat(this.mFatigue);
        parcel.writeFloat(this.mCondition);
    }
}
