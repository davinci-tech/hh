package com.huawei.basefitnessadvice.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class ExerciseProfile implements Parcelable {
    public static final Parcelable.Creator<ExerciseProfile> CREATOR = new Parcelable.Creator<ExerciseProfile>() { // from class: com.huawei.basefitnessadvice.model.ExerciseProfile.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ExerciseProfile createFromParcel(Parcel parcel) {
            return new ExerciseProfile(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ExerciseProfile[] newArray(int i) {
            return new ExerciseProfile[i];
        }
    };
    private static final String TAG = "Suggestion_ExerciseProfile";

    @SerializedName("duration")
    private int mDuration;

    @SerializedName(IndoorEquipManagerApi.KEY_HEART_RATE)
    private ExerciseLimits mHeartRate;

    @SerializedName("intensity")
    private ExerciseLimits mIntensity;

    @SerializedName("message")
    private String mMessage;

    @SerializedName("runningSpeed")
    private ExerciseLimits mRunningSpeed;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ExerciseProfile() {
    }

    protected ExerciseProfile(Parcel parcel) {
        this.mIntensity = (ExerciseLimits) parcel.readParcelable(ExerciseLimits.class.getClassLoader());
        this.mHeartRate = (ExerciseLimits) parcel.readParcelable(ExerciseLimits.class.getClassLoader());
        this.mRunningSpeed = (ExerciseLimits) parcel.readParcelable(ExerciseLimits.class.getClassLoader());
        this.mDuration = parcel.readInt();
        this.mMessage = parcel.readString();
    }

    public ExerciseLimits getIntensity() {
        return this.mIntensity;
    }

    public void setIntensity(ExerciseLimits exerciseLimits) {
        this.mIntensity = exerciseLimits;
    }

    public ExerciseLimits getHeartRate() {
        return this.mHeartRate;
    }

    public void setHeartRate(ExerciseLimits exerciseLimits) {
        this.mHeartRate = exerciseLimits;
    }

    public ExerciseLimits getRunningSpeed() {
        return this.mRunningSpeed;
    }

    public void setRunningSpeed(ExerciseLimits exerciseLimits) {
        this.mRunningSpeed = exerciseLimits;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public void setDuration(int i) {
        this.mDuration = i;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public void setMessage(String str) {
        this.mMessage = str;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
            return;
        }
        parcel.writeParcelable(this.mIntensity, i);
        parcel.writeParcelable(this.mHeartRate, i);
        parcel.writeParcelable(this.mRunningSpeed, i);
        parcel.writeInt(this.mDuration);
        parcel.writeString(this.mMessage);
    }
}
