package com.huawei.basefitnessadvice.model.intplan;

import com.huawei.basefitnessadvice.model.Introduction;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.operation.utils.TodoTaskInterface;

/* loaded from: classes.dex */
public interface IntPlan extends TodoTaskInterface {
    Plan getCompatiblePlan();

    IntDayPlan getDayInfo(int i, int i2);

    String getFinalReport();

    Goal getGoal(String str);

    Introduction getIntroduction();

    PlanMetaInfo getMetaInfo();

    String getPlanId();

    PlanInputInfo getPlanInputInfo();

    String getPlanTempId();

    PlanTimeInfo getPlanTimeInfo();

    PlanType getPlanType();

    StatInfo getStat(String str);

    String getTimeZone();

    int getTransactionStatus();

    IntWeekPlan getWeekInfoByIdx(int i);

    IntWeekPlan getWeekInfoByOrder(int i);

    String getWeekReport();

    public enum PlanType {
        FIT_PLAN(0),
        RUN_PLAN(999),
        AI_FITNESS_PLAN(1),
        AI_RUN_PLAN(2),
        NA_PLAN(-1);

        private int type;

        PlanType(int i) {
            this.type = i;
        }

        public int getType() {
            return this.type;
        }

        public static PlanType getPlanType(int i) {
            for (PlanType planType : values()) {
                if (planType.getType() == i) {
                    return planType;
                }
            }
            return null;
        }
    }
}
