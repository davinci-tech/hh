package com.huawei.basichealthmodel.cloud.bean;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes3.dex */
public class Record {

    @SerializedName("challengeId")
    private Integer mChallengeId;

    @SerializedName("condition")
    private String mCondition;

    @SerializedName("dataSource")
    private Integer mDataSource;

    @SerializedName("id")
    private int mId;

    @SerializedName("isComplete")
    private int mIsComplete;

    @SerializedName("isRest")
    private int mIsRest;

    @SerializedName("lastTarget")
    private String mLastTarget;

    @SerializedName(ParsedFieldTag.RECORD_DAY)
    private int mRecordDay;

    @SerializedName("result")
    private String mResult;

    @SerializedName("target")
    private String mTarget;

    @SerializedName(ParsedFieldTag.TASK_TYPE)
    private Integer mTaskType;

    @SerializedName("timeZone")
    private String mTimeZone;

    @SerializedName("timestamp")
    private long mTimestamp;

    public void setResult(String str) {
        this.mResult = str;
    }

    public void setIsComplete(int i) {
        this.mIsComplete = i;
    }

    public void setIsRest(int i) {
        this.mIsRest = i;
    }

    public void setRecordDay(int i) {
        this.mRecordDay = i;
    }

    public void setTarget(String str) {
        this.mTarget = str;
    }

    public void setLastTarget(String str) {
        this.mLastTarget = str;
    }

    public void setTimestamp(long j) {
        this.mTimestamp = j;
    }

    public void setTimeZone(String str) {
        this.mTimeZone = str;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public void setChallengeId(Integer num) {
        this.mChallengeId = num;
    }

    public void setTaskType(Integer num) {
        this.mTaskType = num;
    }

    public void setDataSource(Integer num) {
        this.mDataSource = num;
    }
}
