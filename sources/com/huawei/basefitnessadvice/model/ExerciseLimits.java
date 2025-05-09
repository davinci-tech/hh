package com.huawei.basefitnessadvice.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class ExerciseLimits implements Parcelable {
    public static final Parcelable.Creator<ExerciseLimits> CREATOR = new Parcelable.Creator<ExerciseLimits>() { // from class: com.huawei.basefitnessadvice.model.ExerciseLimits.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ExerciseLimits createFromParcel(Parcel parcel) {
            return new ExerciseLimits(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ExerciseLimits[] newArray(int i) {
            return new ExerciseLimits[i];
        }
    };
    private static final String TAG = "Suggestion_ExerciseLimits";

    @SerializedName("max")
    private int mMax;

    @SerializedName("min")
    private int mMin;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ExerciseLimits() {
    }

    public ExerciseLimits(int i, int i2) {
        this.mMin = i;
        this.mMax = i2;
    }

    protected ExerciseLimits(Parcel parcel) {
        this.mMin = parcel.readInt();
        this.mMax = parcel.readInt();
    }

    public int getMin() {
        return this.mMin;
    }

    public void setMin(int i) {
        this.mMin = i;
    }

    public int getMax() {
        return this.mMax;
    }

    public void setMax(int i) {
        this.mMax = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
        } else {
            parcel.writeInt(this.mMin);
            parcel.writeInt(this.mMax);
        }
    }
}
