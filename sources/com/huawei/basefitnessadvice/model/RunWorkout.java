package com.huawei.basefitnessadvice.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.pluginfitnessadvice.Workout;

/* loaded from: classes3.dex */
public class RunWorkout extends Workout {
    public static final Parcelable.Creator<RunWorkout> CREATOR = new Parcelable.Creator<RunWorkout>() { // from class: com.huawei.basefitnessadvice.model.RunWorkout.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RunWorkout createFromParcel(Parcel parcel) {
            return new RunWorkout(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RunWorkout[] newArray(int i) {
            return new RunWorkout[i];
        }
    };
    private ExerciseProfile cooldown;
    private int dayNumber;
    private int distance;
    private int phraseNumber;
    private int repeats;
    private ExerciseProfile rest;
    private int trainingEffect;
    private ExerciseProfile warmup;
    private int weekPhraseNumber;
    private ExerciseProfile work;
    private String workoutDate;

    @Override // com.huawei.pluginfitnessadvice.Workout, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RunWorkout() {
    }

    protected RunWorkout(Parcel parcel) {
        super(parcel);
        this.workoutDate = parcel.readString();
        this.dayNumber = parcel.readInt();
        this.phraseNumber = parcel.readInt();
        this.repeats = parcel.readInt();
        this.warmup = (ExerciseProfile) parcel.readParcelable(ExerciseProfile.class.getClassLoader());
        this.work = (ExerciseProfile) parcel.readParcelable(ExerciseProfile.class.getClassLoader());
        this.rest = (ExerciseProfile) parcel.readParcelable(ExerciseProfile.class.getClassLoader());
        this.cooldown = (ExerciseProfile) parcel.readParcelable(ExerciseProfile.class.getClassLoader());
        this.weekPhraseNumber = parcel.readInt();
        this.trainingEffect = parcel.readInt();
        this.distance = parcel.readInt();
    }

    public int getDayNumber() {
        return this.dayNumber;
    }

    public void setDayNumber(int i) {
        this.dayNumber = i;
    }

    public int getPhraseNumber() {
        return this.phraseNumber;
    }

    public void setPhraseNumber(int i) {
        this.phraseNumber = i;
    }

    public int getRepeats() {
        return this.repeats;
    }

    public void setRepeats(int i) {
        this.repeats = i;
    }

    public ExerciseProfile getWarmup() {
        return this.warmup;
    }

    public void setWarmup(ExerciseProfile exerciseProfile) {
        this.warmup = exerciseProfile;
    }

    public ExerciseProfile getWork() {
        return this.work;
    }

    public void setWork(ExerciseProfile exerciseProfile) {
        this.work = exerciseProfile;
    }

    public ExerciseProfile getRest() {
        return this.rest;
    }

    public void setRest(ExerciseProfile exerciseProfile) {
        this.rest = exerciseProfile;
    }

    public ExerciseProfile getCooldown() {
        return this.cooldown;
    }

    public void setCooldown(ExerciseProfile exerciseProfile) {
        this.cooldown = exerciseProfile;
    }

    public int getWeekPhraseNumber() {
        return this.weekPhraseNumber;
    }

    public void setWeekPhraseNumber(int i) {
        this.weekPhraseNumber = i;
    }

    public int getTrainingEffect() {
        return this.trainingEffect;
    }

    public void setTrainingEffect(int i) {
        this.trainingEffect = i;
    }

    public int acquireDistance() {
        return this.distance;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public String acquireWorkoutDate() {
        return this.workoutDate;
    }

    public void putWorkoutDate(String str) {
        this.workoutDate = str;
    }

    @Override // com.huawei.pluginfitnessadvice.Workout, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.workoutDate);
        parcel.writeInt(this.dayNumber);
        parcel.writeInt(this.phraseNumber);
        parcel.writeInt(this.repeats);
        parcel.writeParcelable(this.warmup, i);
        parcel.writeParcelable(this.work, i);
        parcel.writeParcelable(this.rest, i);
        parcel.writeParcelable(this.cooldown, i);
        parcel.writeInt(this.weekPhraseNumber);
        parcel.writeInt(this.trainingEffect);
        parcel.writeInt(this.distance);
    }
}
