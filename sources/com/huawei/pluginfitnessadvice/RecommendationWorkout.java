package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;

/* loaded from: classes6.dex */
public class RecommendationWorkout implements Parcelable {
    public static final Parcelable.Creator<RecommendationWorkout> CREATOR = new Parcelable.Creator<RecommendationWorkout>() { // from class: com.huawei.pluginfitnessadvice.RecommendationWorkout.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: clZ_, reason: merged with bridge method [inline-methods] */
        public RecommendationWorkout createFromParcel(Parcel parcel) {
            return new RecommendationWorkout(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public RecommendationWorkout[] newArray(int i) {
            return new RecommendationWorkout[i];
        }
    };

    @SerializedName("topicListName")
    private String mTopicListName;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String mWorkoutId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected RecommendationWorkout(Parcel parcel) {
        this.mWorkoutId = parcel.readString();
        this.mTopicListName = parcel.readString();
    }

    private RecommendationWorkout(a aVar) {
        this.mWorkoutId = aVar.b;
        this.mTopicListName = aVar.d;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mWorkoutId);
        parcel.writeString(this.mTopicListName);
    }

    /* loaded from: classes9.dex */
    public static final class a {
        private String b;
        private String d;
    }

    public String getWorkoutId() {
        return this.mWorkoutId;
    }

    public String getTopicListName() {
        return this.mTopicListName;
    }

    public String toString() {
        return "RecommendationWorkout{mWorkoutId='" + this.mWorkoutId + "', mTopicListName='" + this.mTopicListName + "'}";
    }
}
