package com.huawei.health.device.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.sport.model.WorkoutRecord;
import defpackage.kxe;
import health.compact.a.CommonUtil;
import health.compact.a.utils.StringUtils;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class RecordAction {
    public static final String ACT_COST_TIME_TAG = "t=";
    public static final String ACT_FINISHED_RATE_TAG = "pct=";
    public static final String ACT_FINISHED_TAG = "val=";
    public static final String ACT_ID_TAG = "id=";
    public static final String ACT_NAME_TAG = "name=";
    public static final String ACT_TARGET_TAG = "tgt=";
    public static final String ACT_TARGET_TYPE_TAG = "typ=";
    public static final String ACT_TIMES_TAG = "times=";
    public static final String ACT_WEIGHT_TAG = "wt=";

    @SerializedName("actionCostTime")
    private long mActionCostTime;

    @SerializedName("actionId")
    private String mActionId;

    @SerializedName("actionIndex")
    private int mActionIndex;

    @SerializedName("actionName")
    private String mActionName;

    @SerializedName("actionTargetType")
    private int mActionTargetType;

    @SerializedName(WorkoutRecord.Extend.COURSE_TARGET_VALUE)
    private float mActionTargetValue;

    @SerializedName("actionTimes")
    private int mActionTimes;

    @SerializedName("actType")
    @Deprecated
    private String mActionType;

    @SerializedName("actionWeight")
    private double mActionWeight;

    @SerializedName("finishRate")
    private float mFinishRate;

    @SerializedName("finishedAct")
    private int mFinishedAction;

    @SerializedName("theoryAct")
    @Deprecated
    private float mTheoryAction;

    public RecordAction(String str, String str2, int i, float f, String str3) {
        this.mActionTargetType = -1;
        this.mActionId = str;
        this.mActionName = str2;
        this.mFinishedAction = i;
        this.mTheoryAction = f;
        this.mActionType = str3;
    }

    @Deprecated
    public RecordAction(String str, int i, float f, String str2) {
        this((String) null, str, i, f, str2);
    }

    public RecordAction(String str, String str2, int i, float f, int i2) {
        this.mActionId = str;
        this.mActionName = str2;
        this.mFinishedAction = i;
        this.mActionTargetValue = f;
        this.mActionTargetType = i2;
        this.mTheoryAction = f;
        this.mActionType = kxe.c(i2);
    }

    public String getActionName() {
        return this.mActionName;
    }

    public void setActionName(String str) {
        this.mActionName = str;
    }

    public int getFinishedAction() {
        return this.mFinishedAction;
    }

    public void setFinishedAction(int i) {
        this.mFinishedAction = i;
    }

    @Deprecated
    public float getTheoryAction() {
        return this.mTheoryAction;
    }

    public float getActionTargetValue() {
        if (CommonUtil.c(this.mActionTargetType) && !CommonUtil.c(this.mTheoryAction)) {
            if ("timer".equals(this.mActionType)) {
                this.mActionTargetValue = TimeUnit.MILLISECONDS.toSeconds((long) this.mTheoryAction);
            } else {
                this.mActionTargetValue = this.mTheoryAction;
            }
        }
        return this.mActionTargetValue;
    }

    public void setActionTargetValue(float f) {
        this.mActionTargetValue = f;
        if ("timer".equals(this.mActionType)) {
            this.mTheoryAction = TimeUnit.SECONDS.toMillis((long) f);
        } else {
            this.mTheoryAction = f;
        }
    }

    public void setTheoryAction(float f) {
        this.mTheoryAction = f;
    }

    public String getActionId() {
        return this.mActionId;
    }

    public void setActionId(String str) {
        this.mActionId = str;
    }

    public String getActionType() {
        return this.mActionType;
    }

    public void setActionType(String str) {
        this.mActionType = str;
    }

    public int getActionTargetType() {
        if (this.mActionTargetType == -1 || StringUtils.i(this.mActionType)) {
            this.mActionTargetType = kxe.e(this.mActionType);
        }
        return this.mActionTargetType;
    }

    public void setActionTargetType(int i) {
        this.mActionTargetType = i;
        this.mActionType = kxe.c(i);
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    @Deprecated
    public String getActType() {
        return this.mActionType;
    }

    public float getFinishRate() {
        return this.mFinishRate;
    }

    public void setFinishRate(float f) {
        this.mFinishRate = f;
    }

    public int getActionIndex() {
        return this.mActionIndex;
    }

    public void setActionIndex(int i) {
        this.mActionIndex = i;
    }

    public long getActionCostTime() {
        return this.mActionCostTime;
    }

    public void setActionCostTime(long j) {
        this.mActionCostTime = j;
    }

    public int getActionTimes() {
        return this.mActionTimes;
    }

    public void setActionTimes(int i) {
        this.mActionTimes = i;
    }

    public double getActionWeight() {
        return this.mActionWeight;
    }

    public void setActionWeight(double d) {
        this.mActionWeight = d;
    }

    @Deprecated
    public void setActType(String str) {
        this.mActionType = str;
        int e = kxe.e(str);
        if (e != -1) {
            this.mActionTargetType = e;
        }
    }

    public String getTrackFileString() {
        return "tp=act;k=" + getActionIndex() + ";typ=" + getActionTargetType() + ";name=" + getActionName() + ";id=" + getActionId() + ";val=" + getFinishedAction() + ";pct=" + getFinishRate() + ";tgt=" + getActionTargetValue() + ";t=" + getActionCostTime() + ";times=" + getActionTimes() + ";wt=" + getActionWeight() + ";" + System.lineSeparator();
    }
}
