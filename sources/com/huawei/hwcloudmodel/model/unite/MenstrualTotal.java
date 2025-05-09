package com.huawei.hwcloudmodel.model.unite;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes7.dex */
public class MenstrualTotal {

    @SerializedName("dataSource")
    private Integer mDataSource;

    @SerializedName("deviceCode")
    private Long mDeviceCode;

    @SerializedName("generateTime")
    private Long mGenerateTime;

    @SerializedName("menstrualBasic")
    private MenstrualBasic mMenstrualBasic;

    @SerializedName(ParsedFieldTag.RECORD_DAY)
    private Integer mRecordDay;

    @SerializedName("timeZone")
    private String mTimeZone;

    public Integer getRecordDay() {
        return this.mRecordDay;
    }

    public void setRecordDay(Integer num) {
        this.mRecordDay = num;
    }

    public Long getGenerateTime() {
        return this.mGenerateTime;
    }

    public void setGenerateTime(Long l) {
        this.mGenerateTime = l;
    }

    public Integer getDataSource() {
        return this.mDataSource;
    }

    public void setDataSource(Integer num) {
        this.mDataSource = num;
    }

    public Long getDeviceCode() {
        return this.mDeviceCode;
    }

    public void setDeviceCode(Long l) {
        this.mDeviceCode = l;
    }

    public MenstrualBasic getMenstrualBasic() {
        return this.mMenstrualBasic;
    }

    public void setMenstrualBasic(MenstrualBasic menstrualBasic) {
        this.mMenstrualBasic = menstrualBasic;
    }

    public String getTimeZone() {
        return this.mTimeZone;
    }

    public void setTimeZone(String str) {
        this.mTimeZone = str;
    }
}
