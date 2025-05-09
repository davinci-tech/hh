package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class LayoutTemplateInfo implements Parcelable {
    public static final Parcelable.Creator<LayoutTemplateInfo> CREATOR = new Parcelable.Creator<LayoutTemplateInfo>() { // from class: com.huawei.pluginfitnessadvice.LayoutTemplateInfo.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: clV_, reason: merged with bridge method [inline-methods] */
        public LayoutTemplateInfo createFromParcel(Parcel parcel) {
            return new LayoutTemplateInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public LayoutTemplateInfo[] newArray(int i) {
            return new LayoutTemplateInfo[i];
        }
    };

    @SerializedName("coachBar")
    private CoachBar mCoachBar;

    @SerializedName("recommendationBar")
    private RecommendationBar mRecommendationBar;

    @SerializedName("introductionBar")
    private WorkoutIntroductionBar mWorkoutIntroductionBar;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected LayoutTemplateInfo(Parcel parcel) {
        this.mCoachBar = (CoachBar) parcel.readParcelable(CoachBar.class.getClassLoader());
        this.mRecommendationBar = (RecommendationBar) parcel.readParcelable(RecommendationBar.class.getClassLoader());
        this.mWorkoutIntroductionBar = (WorkoutIntroductionBar) parcel.readParcelable(WorkoutIntroductionBar.class.getClassLoader());
    }

    private LayoutTemplateInfo(a aVar) {
        this.mCoachBar = aVar.b;
        this.mRecommendationBar = aVar.d;
        this.mWorkoutIntroductionBar = aVar.f8475a;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mCoachBar, i);
        parcel.writeParcelable(this.mRecommendationBar, i);
        parcel.writeParcelable(this.mWorkoutIntroductionBar, i);
    }

    /* loaded from: classes9.dex */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private WorkoutIntroductionBar f8475a;
        private CoachBar b;
        private RecommendationBar d;
    }

    public CoachBar getCoachBar() {
        return this.mCoachBar;
    }

    public RecommendationBar getRecommendationBar() {
        return this.mRecommendationBar;
    }

    public WorkoutIntroductionBar getWorkoutIntroductionBar() {
        return this.mWorkoutIntroductionBar;
    }

    public String toString() {
        return "LayoutTemplateInfo{coachBar=" + this.mCoachBar + ", recommendationBar=" + this.mRecommendationBar + ", workoutIntroductionBar=" + this.mWorkoutIntroductionBar + '}';
    }
}
