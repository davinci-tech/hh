package com.huawei.health.plan.model.intplan;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.PlanInputInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.LogAnonymous;
import java.util.List;

/* loaded from: classes3.dex */
public class PlanInputInfoBean {
    private static final String TAG = "PlanInputInfoBean";

    @SerializedName("bodyParts")
    private List<Integer> mBodyParts;

    @SerializedName(ParsedFieldTag.EXPERIENCE)
    private int mExperience;

    @SerializedName("goalType")
    private int mGoalType;

    @SerializedName("isRunFlag")
    private int mIsRunFlag;

    @SerializedName("isSkipRopeFlag")
    private int mIsSkipRopeFlag;

    @SerializedName("userInfo")
    private UserProfileBean mUserInfo;

    public PlanInputInfoBean() {
    }

    public PlanInputInfoBean(IntPlan intPlan) {
        PlanInputInfo planInputInfo = intPlan.getPlanInputInfo();
        if (planInputInfo != null) {
            this.mGoalType = planInputInfo.getGoalType();
            this.mExperience = planInputInfo.getExperience();
            this.mBodyParts = planInputInfo.getBodyParts();
            if (planInputInfo.getUserInfo() != null) {
                this.mUserInfo = new UserProfileBean(planInputInfo.getUserInfo());
            }
            this.mIsRunFlag = planInputInfo.getIsRunFlag();
            this.mIsSkipRopeFlag = planInputInfo.getIsSkipRopeFlag();
        }
    }

    public int getGoalType() {
        return this.mGoalType;
    }

    public void setGoalType(int i) {
        this.mGoalType = i;
    }

    public List<Integer> getBodyParts() {
        return this.mBodyParts;
    }

    public void setBodyParts(List<Integer> list) {
        this.mBodyParts = list;
    }

    public int getExperience() {
        return this.mExperience;
    }

    public void setExperience(int i) {
        this.mExperience = i;
    }

    public UserProfileBean getUserInfo() {
        return this.mUserInfo;
    }

    public void setUserInfo(UserProfileBean userProfileBean) {
        this.mUserInfo = userProfileBean;
    }

    public int getIsRunFlag() {
        return this.mIsRunFlag;
    }

    public void setIsRunFlag(int i) {
        this.mIsRunFlag = i;
    }

    public int getIsSkipRopeFlag() {
        return this.mIsSkipRopeFlag;
    }

    public void setIsSkipRopeFlag(int i) {
        this.mIsSkipRopeFlag = i;
    }

    public String toString() {
        try {
            return new Gson().toJson(this);
        } catch (IllegalArgumentException e) {
            LogUtil.h(TAG, "toString() ", LogAnonymous.b((Throwable) e));
            return "";
        }
    }
}
