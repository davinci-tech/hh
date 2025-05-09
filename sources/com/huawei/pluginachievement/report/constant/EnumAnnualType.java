package com.huawei.pluginachievement.report.constant;

/* loaded from: classes6.dex */
public enum EnumAnnualType {
    REPORT_INITAL("reportInital"),
    REPORT_CYCLE("reportCycle"),
    REPORT_RUN("reportRun"),
    REPORT_STEP("reportStep"),
    REPORT_SLEEP("reportSleep"),
    REPORT_HEALTH("reportHealth"),
    REPORT_FITNESS("reportFitness"),
    REPORT_WEIGHT("reportWeight"),
    REPORT_REWARD("reportReward"),
    REPORT_MARATHON("reportMarathon"),
    REPORT_ACTIVITY("reportActivity"),
    REPORT_SUMARY("reportSumary"),
    REPORT_DIET("reportDiet"),
    REPORT_MUSIC("reportMusic"),
    REPORT_VIP("reportVIP"),
    REPORT_LIVING("reportLiving"),
    REPORT_CLIMB_HILL("reportClimbHill"),
    REPORT_BADMINTON("reportBadminton"),
    REPORT_JUMP_ROPE("reportJumpRope");

    private String mValue;

    EnumAnnualType(String str) {
        this.mValue = str;
    }

    public String value() {
        return this.mValue;
    }
}
