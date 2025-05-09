package com.huawei.hwcloudmodel.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.unite.ActiveHourBasic;
import com.huawei.hwcloudmodel.model.unite.ExerciseTimeBasic;
import com.huawei.hwcloudmodel.model.unite.GoalAchieveBasic;
import com.huawei.hwcloudmodel.model.unite.SportBasicInfo;

/* loaded from: classes7.dex */
public class SportTotalData {

    @SerializedName("activeHourBasic")
    private ActiveHourBasic activeHourBasic;
    private Integer dataSource;
    private Long deviceCode;

    @SerializedName("exerciseTimeBasic")
    private ExerciseTimeBasic exerciseTimeBasic;

    @SerializedName("goalAchieveBasic")
    private GoalAchieveBasic goalAchieveBasic;
    private Integer recordDay;
    private SportBasicInfo sportBasicInfo;
    private Integer sportType;
    private String timeZone;

    public Integer getRecordDay() {
        return this.recordDay;
    }

    public void setRecordDay(Integer num) {
        this.recordDay = num;
    }

    public Integer getDataSource() {
        return this.dataSource;
    }

    public void setDataSource(Integer num) {
        this.dataSource = num;
    }

    public Integer getSportType() {
        return this.sportType;
    }

    public void setSportType(Integer num) {
        this.sportType = num;
    }

    public Long getDeviceCode() {
        return this.deviceCode;
    }

    public void setDeviceCode(Long l) {
        this.deviceCode = l;
    }

    public SportBasicInfo getSportBasicInfo() {
        return this.sportBasicInfo;
    }

    public void setSportBasicInfo(SportBasicInfo sportBasicInfo) {
        this.sportBasicInfo = sportBasicInfo;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String str) {
        this.timeZone = str;
    }

    public ExerciseTimeBasic getExerciseTimeBasic() {
        return this.exerciseTimeBasic;
    }

    public void setExerciseTimeBasic(ExerciseTimeBasic exerciseTimeBasic) {
        this.exerciseTimeBasic = exerciseTimeBasic;
    }

    public ActiveHourBasic getActiveHourBasic() {
        return this.activeHourBasic;
    }

    public void setActiveHourBasic(ActiveHourBasic activeHourBasic) {
        this.activeHourBasic = activeHourBasic;
    }

    public GoalAchieveBasic getGoalAchieveBasic() {
        return this.goalAchieveBasic;
    }

    public void setGoalAchieveBasic(GoalAchieveBasic goalAchieveBasic) {
        this.goalAchieveBasic = goalAchieveBasic;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("SportTotalData{recordDay=");
        stringBuffer.append(this.recordDay).append(", dataSource=").append(this.dataSource).append(", sportType=").append(this.sportType).append(", deviceCode=").append(this.deviceCode).append(", sportBasicInfo=").append(this.sportBasicInfo).append(", exerciseTimeBasic=").append(this.exerciseTimeBasic).append(", activeHourBasic=").append(this.activeHourBasic).append(", goalAchieveBasic=").append(this.goalAchieveBasic).append(", timeZone='").append(this.timeZone).append("'}");
        return stringBuffer.toString();
    }
}
