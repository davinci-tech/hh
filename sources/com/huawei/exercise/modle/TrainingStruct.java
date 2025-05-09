package com.huawei.exercise.modle;

/* loaded from: classes3.dex */
public class TrainingStruct {
    private int training_duration;
    private int training_hr_limit_high;
    private int training_hr_limit_low;
    private int training_intensity_limit_high;
    private int training_intensity_limit_low;
    private int training_speed_limit_high;
    private int training_speed_limit_low;
    private int training_type;

    public void setTraining_type(int i) {
        this.training_type = i;
    }

    public void setTraining_speed_limit_high(int i) {
        this.training_speed_limit_high = i;
    }

    public void setTraining_speed_limit_low(int i) {
        this.training_speed_limit_low = i;
    }

    public void setTraining_hr_limit_high(int i) {
        this.training_hr_limit_high = i;
    }

    public void setTraining_hr_limit_low(int i) {
        this.training_hr_limit_low = i;
    }

    public void setTraining_intensity_limit_high(int i) {
        this.training_intensity_limit_high = i;
    }

    public void setTraining_intensity_limit_low(int i) {
        this.training_intensity_limit_low = i;
    }

    public void setTraining_duration(int i) {
        this.training_duration = i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.training_type);
        stringBuffer.append(this.training_speed_limit_high);
        stringBuffer.append(this.training_speed_limit_low);
        stringBuffer.append(this.training_hr_limit_high);
        stringBuffer.append(this.training_hr_limit_low);
        stringBuffer.append(this.training_intensity_limit_high);
        stringBuffer.append(this.training_intensity_limit_low);
        stringBuffer.append(this.training_duration);
        return stringBuffer.toString();
    }
}
