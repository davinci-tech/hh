package com.huawei.hwcloudmodel.model.unite;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes7.dex */
public class SportTotal {

    @SerializedName("activeHourBasic")
    private ActiveHourBasic mActiveHourBasic;

    @SerializedName("dataId")
    private Long mDataId;

    @SerializedName("dataSource")
    private Integer mDataSource;

    @SerializedName("deviceCode")
    private Long mDeviceCode;

    @SerializedName("exerciseTimeBasic")
    private ExerciseTimeBasic mExerciseTimeBasic;

    @SerializedName("goalAchieveBasic")
    private GoalAchieveBasic mGoalAchieveBasic;

    @SerializedName(ParsedFieldTag.RECORD_DAY)
    private Integer mRecordDay;

    @SerializedName("sportBasicInfo")
    private SportBasicInfo mSportBasicInfo;

    @SerializedName(BleConstants.SPORT_TYPE)
    private Integer mSportType;

    @SerializedName("timeZone")
    private String mTimeZone;

    public Integer getDataSource() {
        return this.mDataSource;
    }

    public void setDataSource(Integer num) {
        this.mDataSource = num;
    }

    public Integer getRecordDay() {
        return this.mRecordDay;
    }

    public void setRecordDay(Integer num) {
        this.mRecordDay = num;
    }

    public Integer getSportType() {
        return this.mSportType;
    }

    public void setSportType(Integer num) {
        this.mSportType = num;
    }

    public String getTimeZone() {
        return this.mTimeZone;
    }

    public void setTimeZone(String str) {
        this.mTimeZone = str;
    }

    public SportBasicInfo getSportBasicInfo() {
        return this.mSportBasicInfo;
    }

    public void setSportBasicInfo(SportBasicInfo sportBasicInfo) {
        this.mSportBasicInfo = sportBasicInfo;
    }

    public ExerciseTimeBasic getExerciseTimeBasic() {
        return this.mExerciseTimeBasic;
    }

    public void setExerciseTimeBasic(ExerciseTimeBasic exerciseTimeBasic) {
        this.mExerciseTimeBasic = exerciseTimeBasic;
    }

    public ActiveHourBasic getActiveHourBasic() {
        return this.mActiveHourBasic;
    }

    public void setActiveHourBasic(ActiveHourBasic activeHourBasic) {
        this.mActiveHourBasic = activeHourBasic;
    }

    public Long getDeviceCode() {
        return this.mDeviceCode;
    }

    public void setDeviceCode(Long l) {
        this.mDeviceCode = l;
    }

    public Long getDataId() {
        return this.mDataId;
    }

    public void setDataId(Long l) {
        this.mDataId = l;
    }

    public GoalAchieveBasic getGoalAchieveBasic() {
        return this.mGoalAchieveBasic;
    }

    public void setGoalAchieveBasic(GoalAchieveBasic goalAchieveBasic) {
        this.mGoalAchieveBasic = goalAchieveBasic;
    }

    public String toString() {
        return "SportTotal{dataId=" + this.mDataId + ", recordDay=" + this.mRecordDay + ", dataSource=" + this.mDataSource + ", sportType=" + this.mSportType + ", deviceCode=" + this.mDeviceCode + ", exerciseTimeBasic=" + this.mExerciseTimeBasic + ", activeHourBasic=" + this.mActiveHourBasic + ", mGoalAchieveBasic=" + this.mGoalAchieveBasic + ", sportBasicInfo=" + this.mSportBasicInfo + ", timeZone='" + this.mTimeZone + "'}";
    }
}
