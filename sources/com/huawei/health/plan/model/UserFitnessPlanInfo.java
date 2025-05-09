package com.huawei.health.plan.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.basefitnessadvice.model.FitnessWeekPlan;
import com.huawei.basefitnessadvice.model.Introduction;
import com.huawei.health.plan.model.intplan.StatInfoBean;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.CommonUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class UserFitnessPlanInfo {
    private String cardImage;
    private String completeRate;
    private long createTime;
    private String description;
    private long finishTime;

    @SerializedName(ParsedFieldTag.BEGIN_DATE)
    private long mBeginDate;

    @SerializedName("displayStyle")
    private int mDisplayStyle;

    @SerializedName("endDate")
    private long mEndDate;

    @SerializedName("introduction")
    private Introduction mIntroduction;

    @SerializedName("planCategory")
    private int mPlanCategory;

    @SerializedName("statistics")
    private List<StatInfoBean> mStatistics;

    @SerializedName("timeZone")
    private String mTimeZone;

    @SerializedName("weeklyPlanList")
    private List<FitnessWeekPlan> mWeeklyPlanList;
    private long modifyTime;
    private String name;
    private String picture;
    private String planId;
    private String planTempId;
    private int remindTime;
    private int status;
    private String totalCalorie;
    private int transactionStatus;
    private List<FitnessWeekPlan> weekPlanList;

    public Introduction acquireIntroduction() {
        return this.mIntroduction;
    }

    public void saveIntroduction(Introduction introduction) {
        this.mIntroduction = introduction;
    }

    public int acquireDisplayStyle() {
        return this.mDisplayStyle;
    }

    public void saveDisplayStyle(int i) {
        this.mDisplayStyle = i;
    }

    public int acquirePlanCategory() {
        return this.mPlanCategory;
    }

    public void savePlanCategory(int i) {
        this.mPlanCategory = i;
    }

    public List<FitnessWeekPlan> acquireWeeklyPlanList() {
        return this.mWeeklyPlanList;
    }

    public void saveWeeklyPlanList(List<FitnessWeekPlan> list) {
        this.mWeeklyPlanList = list;
    }

    public List<StatInfoBean> acquireStatistics() {
        return this.mStatistics;
    }

    public void saveStatistics(List<StatInfoBean> list) {
        this.mStatistics = list;
    }

    public String acquireTimeZone() {
        return this.mTimeZone;
    }

    public void saveTimeZone(String str) {
        this.mTimeZone = str;
    }

    public String acquireTotalCalorie() {
        return this.totalCalorie;
    }

    public void saveTotalCalorie(String str) {
        this.totalCalorie = String.valueOf(Math.round(CommonUtil.j(str)));
    }

    public List<FitnessWeekPlan> acquireWeekPlanList() {
        List<FitnessWeekPlan> list = this.weekPlanList;
        return list == null ? this.mWeeklyPlanList : list;
    }

    public void saveWeekPlanList(List<FitnessWeekPlan> list) {
        this.weekPlanList = list;
    }

    public String acquireCardPicture() {
        return this.cardImage;
    }

    public void saveCardPicture(String str) {
        this.cardImage = str;
    }

    public String acquirePlanId() {
        return this.planId;
    }

    public void savePlanId(String str) {
        this.planId = str;
    }

    public String acquirePlanTempId() {
        return this.planTempId;
    }

    public void savePlanTempId(String str) {
        this.planTempId = str;
    }

    public String acquireName() {
        return this.name;
    }

    public void saveName(String str) {
        this.name = str;
    }

    public String acquireCompleteRate() {
        return this.completeRate;
    }

    public void saveCompleteRate(String str) {
        this.completeRate = str;
    }

    public int acquirePlanStatus() {
        return this.status;
    }

    public void savePlanStatus(int i) {
        this.status = i;
    }

    public String acquirePicture() {
        return this.picture;
    }

    public void savePicture(String str) {
        this.picture = str;
    }

    public String acquireDescription() {
        return this.description;
    }

    public void saveDescription(String str) {
        this.description = str;
    }

    public long acquireCreateTime() {
        return this.createTime;
    }

    public void saveCreateTime(long j) {
        this.createTime = j;
    }

    public long acquireModifyTime() {
        return this.modifyTime;
    }

    public void saveModifyTime(long j) {
        this.modifyTime = j;
    }

    public long acquireFinishTime() {
        return this.finishTime;
    }

    public void saveFinishTime(long j) {
        this.finishTime = j;
    }

    public long acquireBeginDate() {
        return this.mBeginDate;
    }

    public void saveBeginDate(long j) {
        this.mBeginDate = j;
    }

    public void saveEndDate(long j) {
        this.mEndDate = j;
    }

    public long acquireEndDate() {
        return this.mEndDate;
    }

    public int acquireRemindTime() {
        return this.remindTime;
    }

    public void saveRemindTime(int i) {
        this.remindTime = i;
    }

    public int acquireTransactionStatus() {
        return this.transactionStatus;
    }

    public void setTransactionStatus(int i) {
        this.transactionStatus = i;
    }
}
