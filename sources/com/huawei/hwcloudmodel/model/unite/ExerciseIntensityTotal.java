package com.huawei.hwcloudmodel.model.unite;

/* loaded from: classes9.dex */
public class ExerciseIntensityTotal {
    private Integer dataSource;
    private Long deviceCode;
    private ExerciseIntensityBasic exerciseIntensityBasic;
    private Long generateTime;
    private Integer recordDay;
    private String timeZone;

    public Integer fetchRecordDay() {
        return this.recordDay;
    }

    public void configRecordDay(Integer num) {
        this.recordDay = num;
    }

    public Long fetchGenerateTime() {
        return this.generateTime;
    }

    public void configGenerateTime(Long l) {
        this.generateTime = l;
    }

    public Integer fetchDataSource() {
        return this.dataSource;
    }

    public void configDataSource(Integer num) {
        this.dataSource = num;
    }

    public Long fetchDeviceCode() {
        return this.deviceCode;
    }

    public void configDeviceCode(Long l) {
        this.deviceCode = l;
    }

    public ExerciseIntensityBasic fetchExerciseIntensityBasic() {
        return this.exerciseIntensityBasic;
    }

    public void configExerciseIntensityBasic(ExerciseIntensityBasic exerciseIntensityBasic) {
        this.exerciseIntensityBasic = exerciseIntensityBasic;
    }

    public String fetchTimeZone() {
        return this.timeZone;
    }

    public void configTimeZone(String str) {
        this.timeZone = str;
    }
}
