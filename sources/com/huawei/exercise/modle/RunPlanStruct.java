package com.huawei.exercise.modle;

import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class RunPlanStruct {
    private long run_plan_date;
    private int run_plan_distance;
    private String run_plan_name;
    private int run_plan_repeats;
    private int run_plan_train_effect;
    private int run_plan_workout_id;
    private List<TrainingStruct> trainingStructList;

    public void setRun_plan_name(String str) {
        this.run_plan_name = str;
    }

    public void setRun_plan_date(long j) {
        this.run_plan_date = j;
    }

    public void setRun_plan_workout_id(int i) {
        this.run_plan_workout_id = i;
    }

    public void setRun_plan_train_effect(int i) {
        this.run_plan_train_effect = i;
    }

    public void setRun_plan_repeats(int i) {
        this.run_plan_repeats = i;
    }

    public void setRun_plan_distance(int i) {
        this.run_plan_distance = i;
    }

    public void setTrainingStructList(List<TrainingStruct> list) {
        this.trainingStructList = list;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.run_plan_name);
        stringBuffer.append(this.run_plan_date);
        stringBuffer.append(this.run_plan_train_effect);
        stringBuffer.append(this.run_plan_repeats);
        stringBuffer.append(this.run_plan_distance);
        List<TrainingStruct> list = this.trainingStructList;
        if (list != null) {
            Iterator<TrainingStruct> it = list.iterator();
            while (it.hasNext()) {
                stringBuffer.append(it.next().toString());
            }
        }
        return stringBuffer.toString();
    }
}
