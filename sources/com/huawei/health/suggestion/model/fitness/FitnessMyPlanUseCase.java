package com.huawei.health.suggestion.model.fitness;

import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import java.util.List;

/* loaded from: classes8.dex */
public class FitnessMyPlanUseCase {
    private List<FitnessPackageInfo> mFitnessPackageInfoList;
    private IntPlan mIntPlan;
    private List<Plan> mPlanList;

    public List<FitnessPackageInfo> getFitnessPackageInfoList() {
        return this.mFitnessPackageInfoList;
    }

    public void setFitnessPackageInfoList(List<FitnessPackageInfo> list) {
        this.mFitnessPackageInfoList = list;
    }

    public List<Plan> getPlanList() {
        return this.mPlanList;
    }

    public void setPlanList(List<Plan> list) {
        this.mPlanList = list;
    }

    public IntPlan getIntPlan() {
        return this.mIntPlan;
    }

    public void setIntPlan(IntPlan intPlan) {
        this.mIntPlan = intPlan;
    }
}
