package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendationBar implements Parcelable {
    public static final Parcelable.Creator<RecommendationBar> CREATOR = new Parcelable.Creator<RecommendationBar>() { // from class: com.huawei.pluginfitnessadvice.RecommendationBar.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: clY_, reason: merged with bridge method [inline-methods] */
        public RecommendationBar createFromParcel(Parcel parcel) {
            return new RecommendationBar(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public RecommendationBar[] newArray(int i) {
            return new RecommendationBar[i];
        }
    };

    @SerializedName("name")
    private String mName;

    @SerializedName("recommendationWorkoutList")
    private List<RecommendationWorkout> mRecommendationWorkouts;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected RecommendationBar(Parcel parcel) {
        this.mName = parcel.readString();
        this.mRecommendationWorkouts = parcel.createTypedArrayList(RecommendationWorkout.CREATOR);
    }

    private RecommendationBar(c cVar) {
        this.mName = cVar.d;
        this.mRecommendationWorkouts = cVar.c;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeTypedList(this.mRecommendationWorkouts);
    }

    /* loaded from: classes9.dex */
    public static final class c {
        private List<RecommendationWorkout> c;
        private String d;
    }

    public String getName() {
        return this.mName;
    }

    public List<RecommendationWorkout> getRecommendationWorkouts() {
        return this.mRecommendationWorkouts;
    }

    public String toString() {
        return "RecommendationBar{mName='" + this.mName + "', mRecommendationWorkouts=" + this.mRecommendationWorkouts + '}';
    }
}
