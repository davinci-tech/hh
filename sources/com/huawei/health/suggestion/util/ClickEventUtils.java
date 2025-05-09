package com.huawei.health.suggestion.util;

import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.PlanWorkout;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import defpackage.mmw;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class ClickEventUtils {

    public enum ClickEventType {
        ALL_RUN_COURSE,
        RUN_COURSE,
        ALL_RUN_PLAN,
        RECOMMENDED_RUN_PLAN,
        MY_ALL_RUN_COURSE,
        AI_RUN_COURSE,
        PLAN_TAB,
        START_OLD_RUN_PLAN,
        START_AI_RUN_PLAN,
        JUMP_VIP,
        AI_RUN_PLAN_REPORT,
        MY_ALL_WALK_COURSE
    }

    public static void e(ClickEventType clickEventType, HashMap<String, Object> hashMap) {
        if (clickEventType == null) {
            LogUtil.h("ClickEventUtils", "doAction params is empty.");
        }
        if (hashMap == null || hashMap.isEmpty()) {
            b(clickEventType);
            return;
        }
        switch (AnonymousClass3.e[clickEventType.ordinal()]) {
            case 1:
                if (hashMap.get("courseCategory") instanceof Integer) {
                    JumpUtil.c(BaseApplication.getContext(), ((Integer) hashMap.get("courseCategory")).intValue());
                    break;
                }
                break;
            case 2:
                c(hashMap);
                break;
            case 3:
                if (hashMap.get("runPlanInfo") instanceof mmw) {
                    JumpUtil.c(BaseApplication.getContext(), (mmw) hashMap.get("runPlanInfo"));
                    break;
                }
                break;
            case 4:
                b(hashMap);
                break;
            case 5:
                a(hashMap);
                break;
            case 6:
                if ((hashMap.get("currentPlan") instanceof Plan) && (hashMap.get("planWorkout") instanceof FitWorkout)) {
                    JumpUtil.d(BaseApplication.getActivity(), (Plan) hashMap.get("currentPlan"), (FitWorkout) hashMap.get("planWorkout"));
                    break;
                }
                break;
            case 7:
                e(hashMap);
                break;
            case 8:
                if (hashMap.get("myCourseType") instanceof Integer) {
                    JumpUtil.b(BaseApplication.getContext(), ((Integer) hashMap.get("myCourseType")).intValue());
                    break;
                }
                break;
        }
    }

    private static void b(ClickEventType clickEventType) {
        switch (clickEventType) {
            case AI_RUN_COURSE:
                JumpUtil.e(BaseApplication.getContext());
                break;
            case PLAN_TAB:
                JumpUtil.c(BaseApplication.getContext());
                break;
            case JUMP_VIP:
                JumpUtil.i(BaseApplication.getContext());
                break;
        }
    }

    private static void a(HashMap<String, Object> hashMap) {
        if ((hashMap.get("currentPlan") instanceof Plan) && (hashMap.get("planWorkout") instanceof PlanWorkout)) {
            JumpUtil.d((Plan) hashMap.get("currentPlan"), (PlanWorkout) hashMap.get("planWorkout"));
        }
    }

    private static void c(HashMap<String, Object> hashMap) {
        if ((hashMap.get("fitWorkout") instanceof FitWorkout) && (hashMap.get("biMap") instanceof Map)) {
            JumpUtil.c(BaseApplication.getContext(), (FitWorkout) hashMap.get("fitWorkout"), (Map<String, Object>) hashMap.get("biMap"));
        }
    }

    private static void b(HashMap<String, Object> hashMap) {
        if (hashMap.get("myCourseType") instanceof Integer) {
            JumpUtil.d(BaseApplication.getContext(), ((Integer) hashMap.get("myCourseType")).intValue());
        }
    }

    private static void e(HashMap<String, Object> hashMap) {
        if (hashMap.get("currentPlan") instanceof Plan) {
            JumpUtil.a(1, BaseApplication.getContext(), IntPlan.PlanType.AI_RUN_PLAN.getType(), ((Plan) hashMap.get("currentPlan")).acquireId());
        }
    }
}
