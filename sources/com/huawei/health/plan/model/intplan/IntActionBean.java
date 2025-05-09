package com.huawei.health.plan.model.intplan;

import com.google.gson.annotations.SerializedName;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.phoneservice.feedbackcommon.utils.AsCache;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import defpackage.mnt;

/* loaded from: classes3.dex */
public class IntActionBean {

    @SerializedName("actionId")
    private String mActionId;

    @SerializedName("actionInfo")
    private mnt mActionInfo;

    @SerializedName(ParsedFieldTag.ACTION_TYPE)
    private int mActionType;

    @SerializedName(AsCache.FEED_BACK_CACHE_FILE_NAME)
    private int mFeedback;

    @SerializedName("punchFlag")
    private int mPunchFlag;

    @SerializedName("punchTime")
    private long mPunchTime;

    @SerializedName("recordId")
    private String mRecordId;

    public IntActionBean() {
        this.mPunchTime = -1L;
        this.mFeedback = -1;
    }

    public IntActionBean(IntAction intAction) {
        this.mPunchTime = -1L;
        this.mFeedback = -1;
        this.mActionType = intAction.getActionType().getType();
        this.mActionId = intAction.getActionId();
        this.mPunchFlag = intAction.getPunchFlag();
        this.mPunchTime = intAction.getPunchTime();
        this.mRecordId = intAction.getRecordId();
        this.mFeedback = intAction.getFeedback();
        this.mActionInfo = intAction.getActionInfo();
    }

    public int getActionType() {
        return this.mActionType;
    }

    public void setActionType(int i) {
        this.mActionType = i;
    }

    public String getActionId() {
        return this.mActionId;
    }

    public void setActionId(String str) {
        this.mActionId = str;
    }

    public int getPunchFlag() {
        return this.mPunchFlag;
    }

    public void setPunchFlag(int i) {
        this.mPunchFlag = i;
    }

    public long getPunchTime() {
        return this.mPunchTime;
    }

    public void setPunchTime(long j) {
        this.mPunchTime = j;
    }

    public String getRecordId() {
        return this.mRecordId;
    }

    public void setRecordId(String str) {
        this.mRecordId = str;
    }

    public int getFeedback() {
        return this.mFeedback;
    }

    public void setFeedback(int i) {
        this.mFeedback = i;
    }

    public mnt getActionInfo() {
        return this.mActionInfo;
    }

    public void setActionInfo(mnt mntVar) {
        this.mActionInfo = mntVar;
    }
}
