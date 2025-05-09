package com.huawei.hwcloudmodel.model.unite;

import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes7.dex */
public class GetHealthStatRsp extends CloudCommonReponse {
    private List<BloodOxygenSaturationTotal> bloodOxygenSaturationTotal;
    private List<BradycardiaAlarmTotal> bradycardiaAlarmTotal;
    private List<ExerciseIntensityTotal> exerciseIntensityTotal;
    private List<HeartRateRiseAlarmTotal> heartRateRiseAlarmTotal;
    private List<HeartRateTotal> heartRateTotal;
    private List<MenstrualTotal> menstrualTotal;
    private List<ProfessionalSleepTotal> professionalSleepTotal;
    private List<SleepTotal> sleepTotal;
    private List<StressTotal> stressTotal;

    public List<BloodOxygenSaturationTotal> getBloodOxygenSaturationTotal() {
        return this.bloodOxygenSaturationTotal;
    }

    public void setBloodOxygenSaturationTotal(List<BloodOxygenSaturationTotal> list) {
        this.bloodOxygenSaturationTotal = list;
    }

    public List<BradycardiaAlarmTotal> getBradycardiaAlarmTotal() {
        return this.bradycardiaAlarmTotal;
    }

    public void setBradycardiaAlarmTotal(List<BradycardiaAlarmTotal> list) {
        this.bradycardiaAlarmTotal = list;
    }

    public List<SleepTotal> getSleepTotal() {
        return this.sleepTotal;
    }

    public List<ProfessionalSleepTotal> getProfessionalSleepTotal() {
        return this.professionalSleepTotal;
    }

    public void setSleepTotal(List<SleepTotal> list) {
        this.sleepTotal = list;
    }

    public void setProfessionalSleepTotal(List<ProfessionalSleepTotal> list) {
        this.professionalSleepTotal = list;
    }

    public List<HeartRateTotal> getHeartRateTotal() {
        return this.heartRateTotal;
    }

    public void setHeartRateTotal(List<HeartRateTotal> list) {
        this.heartRateTotal = list;
    }

    public List<StressTotal> getStressTotal() {
        return this.stressTotal;
    }

    public void setStressTotal(List<StressTotal> list) {
        this.stressTotal = list;
    }

    public List<ExerciseIntensityTotal> getExerciseIntensityTotal() {
        return this.exerciseIntensityTotal;
    }

    public void setExerciseIntensityTotal(List<ExerciseIntensityTotal> list) {
        this.exerciseIntensityTotal = list;
    }

    public List<HeartRateRiseAlarmTotal> getHeartRateRiseTotal() {
        return this.heartRateRiseAlarmTotal;
    }

    public void setHeartRateRiseTotal(List<HeartRateRiseAlarmTotal> list) {
        this.heartRateRiseAlarmTotal = list;
    }

    public List<MenstrualTotal> getMenstrualTotal() {
        return this.menstrualTotal;
    }

    public void setMenstrualTotal(List<MenstrualTotal> list) {
        this.menstrualTotal = list;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "GetHealthStatRsp";
    }
}
