package com.huawei.exercise.modle;

/* loaded from: classes8.dex */
public class OperatorStatus {
    private long operation_time;
    private int operator_type;
    private long run_plan_date;
    private int sport_exist = 1;
    private long sport_startTime;
    private int sport_type;
    private int train_monitor_state;
    private int workout_type;

    public int getSport_exist() {
        return this.sport_exist;
    }

    public void setSport_exist(int i) {
        this.sport_exist = i;
    }

    public int getSport_type() {
        return this.sport_type;
    }

    public void setSport_type(int i) {
        this.sport_type = i;
    }

    public long getSport_startTime() {
        return this.sport_startTime;
    }

    public void setSport_startTime(long j) {
        this.sport_startTime = j;
    }

    public int getWorkout_type() {
        return this.workout_type;
    }

    public void setWorkout_type(int i) {
        this.workout_type = i;
    }

    public long getRun_plan_date() {
        return this.run_plan_date;
    }

    public void setRun_plan_date(long j) {
        this.run_plan_date = j;
    }

    public int getOperator_type() {
        return this.operator_type;
    }

    public void setOperator_type(int i) {
        this.operator_type = i;
    }

    public void setTrain_monitor_state(int i) {
        this.train_monitor_state = i;
    }

    public int getTrain_monitor_state() {
        return this.train_monitor_state;
    }

    public void setOperation_time(long j) {
        this.operation_time = j;
    }

    public long getOperation_time() {
        return this.operation_time;
    }
}
