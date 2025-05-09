package com.huawei.exercise.modle;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;

/* loaded from: classes3.dex */
public class WorkoutRecordStatistic {

    @SerializedName(HwExerciseConstants.JSON_NAME_ALG_TYPE)
    private int mAlgoType = 0;
    private int workout_Epoc;
    private int workout_HrABS_peak_max;
    private int workout_HrABS_peak_min;
    private int workout_anaerobic_exercise_etraining_effect;
    private long workout_climb;
    private long workout_date_Info;
    private int workout_etraining_effect;
    private long workout_exercise_duration;
    private int workout_load_peak;
    private int workout_maxMET;
    private int workout_record_calorie;
    private int workout_record_distance;
    private long workout_record_end_time;
    private int workout_record_id;
    private float workout_record_speed;
    private long workout_record_start_time;
    private int workout_record_status;
    private long workout_record_step;
    private long workout_record_total_time;
    private int workout_recovery_time;
    private int workout_type;

    public int getWorkout_record_id() {
        return this.workout_record_id;
    }

    public void setWorkout_record_id(int i) {
        this.workout_record_id = i;
    }

    public int getWorkout_record_status() {
        return this.workout_record_status;
    }

    public void setWorkout_record_status(int i) {
        this.workout_record_status = i;
    }

    public long getWorkout_record_start_time() {
        return this.workout_record_start_time;
    }

    public void setWorkout_record_start_time(long j) {
        this.workout_record_start_time = j;
    }

    public long getWorkout_record_end_time() {
        return this.workout_record_end_time;
    }

    public void setWorkout_record_end_time(long j) {
        this.workout_record_end_time = j;
    }

    public int getWorkout_record_calorie() {
        return this.workout_record_calorie;
    }

    public void setWorkout_record_calorie(int i) {
        this.workout_record_calorie = i;
    }

    public int getWorkout_record_distance() {
        return this.workout_record_distance;
    }

    public void setWorkout_record_distance(int i) {
        this.workout_record_distance = i;
    }

    public long getWorkout_record_step() {
        return this.workout_record_step;
    }

    public void setWorkout_record_step(long j) {
        this.workout_record_step = j;
    }

    public long getWorkout_record_total_time() {
        return this.workout_record_total_time;
    }

    public void setWorkout_record_total_time(long j) {
        this.workout_record_total_time = j;
    }

    public float getWorkout_record_speed() {
        return this.workout_record_speed;
    }

    public void setWorkout_record_speed(float f) {
        this.workout_record_speed = f;
    }

    public long getWorkout_climb() {
        return this.workout_climb;
    }

    public void setWorkout_climb(long j) {
        this.workout_climb = j;
    }

    public int getWorkout_HrABS_peak_max() {
        return this.workout_HrABS_peak_max;
    }

    public void setWorkout_HrABS_peak_max(int i) {
        this.workout_HrABS_peak_max = i;
    }

    public int getWorkout_HrABS_peak_min() {
        return this.workout_HrABS_peak_min;
    }

    public void setWorkout_HrABS_peak_min(int i) {
        this.workout_HrABS_peak_min = i;
    }

    public int getWorkout_load_peak() {
        return this.workout_load_peak;
    }

    public void setWorkout_load_peak(int i) {
        this.workout_load_peak = i;
    }

    public int getWorkout_etraining_effect() {
        return this.workout_etraining_effect;
    }

    public void setWorkout_etraining_effect(int i) {
        this.workout_etraining_effect = i;
    }

    public int getWorkout_anaerobic_exercise_etraining_effect() {
        return this.workout_anaerobic_exercise_etraining_effect;
    }

    public void setWorkout_anaerobic_exercise_etraining_effect(int i) {
        this.workout_anaerobic_exercise_etraining_effect = i;
    }

    public int getWorkout_Epoc() {
        return this.workout_Epoc;
    }

    public void setWorkout_Epoc(int i) {
        this.workout_Epoc = i;
    }

    public int getWorkout_maxMET() {
        return this.workout_maxMET;
    }

    public void setWorkout_maxMET(int i) {
        this.workout_maxMET = i;
    }

    public int getWorkout_recovery_time() {
        return this.workout_recovery_time;
    }

    public void setWorkout_recovery_time(int i) {
        this.workout_recovery_time = i;
    }

    public long getWorkout_exercise_duration() {
        return this.workout_exercise_duration;
    }

    public void setWorkout_exercise_duration(long j) {
        this.workout_exercise_duration = j;
    }

    public long getWorkout_date_Info() {
        return this.workout_date_Info;
    }

    public void setWorkout_date_Info(long j) {
        this.workout_date_Info = j;
    }

    public void setWorkout_type(int i) {
        this.workout_type = i;
    }

    public int getWorkout_type() {
        return this.workout_type;
    }

    public int getmAlgoType() {
        return this.mAlgoType;
    }

    public void setmAlgoType(int i) {
        this.mAlgoType = i;
    }
}
