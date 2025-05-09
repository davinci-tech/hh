package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.huawei.hwcloudmodel.model.unite.BloodOxygenSaturationTotal;
import com.huawei.hwcloudmodel.model.unite.BradycardiaAlarmTotal;
import com.huawei.hwcloudmodel.model.unite.ExerciseIntensityTotal;
import com.huawei.hwcloudmodel.model.unite.HeartRateRiseAlarmTotal;
import com.huawei.hwcloudmodel.model.unite.HeartRateTotal;
import com.huawei.hwcloudmodel.model.unite.MenstrualTotal;
import com.huawei.hwcloudmodel.model.unite.ProfessionalSleepTotal;
import com.huawei.hwcloudmodel.model.unite.SleepTotal;
import com.huawei.hwcloudmodel.model.unite.StressTotal;
import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;
import java.util.List;

/* loaded from: classes7.dex */
public class AddHealthStatReq implements IRequest {
    private List<BloodOxygenSaturationTotal> bloodOxygenSaturationTotal;
    private List<BradycardiaAlarmTotal> bradycardiaAlarmTotal;
    private List<ExerciseIntensityTotal> exerciseIntensityTotal;
    private List<HeartRateRiseAlarmTotal> heartRateRiseAlarmTotal;
    private List<HeartRateTotal> heartRateTotal;
    private List<MenstrualTotal> menstrualTotal;
    private List<ProfessionalSleepTotal> professionalSleepTotal;
    private List<SleepTotal> sleepTotal;
    private List<StressTotal> stressTotal;
    private String timeZone;

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

    public List<HeartRateTotal> getHeartRateTotal() {
        return this.heartRateTotal;
    }

    public void setHeartRateTotal(List<HeartRateTotal> list) {
        this.heartRateTotal = list;
    }

    public List<SleepTotal> getSleepTotal() {
        return this.sleepTotal;
    }

    public String getTimeZone() {
        return this.timeZone;
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

    public void setTimeZone(String str) {
        this.timeZone = str;
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

    public List<HeartRateRiseAlarmTotal> getHeartRateRiseAlarmTotal() {
        return this.heartRateRiseAlarmTotal;
    }

    public void setHeartRateRiseAlarmTotal(List<HeartRateRiseAlarmTotal> list) {
        this.heartRateRiseAlarmTotal = list;
    }

    public List<MenstrualTotal> getMenstrualTotal() {
        return this.menstrualTotal;
    }

    public void setMenstrualTotal(List<MenstrualTotal> list) {
        this.menstrualTotal = list;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/dataSync/health/addHealthStat";
    }

    public String toString() {
        return "AddHealthStatReq{}";
    }
}
