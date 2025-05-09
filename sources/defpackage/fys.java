package defpackage;

import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseLongArray;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.DayInfo;
import com.huawei.basefitnessadvice.model.intplan.Goal;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.IntWeekPlan;
import com.huawei.basefitnessadvice.model.intplan.LeaveInfo;
import com.huawei.basefitnessadvice.model.intplan.RecordData;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.plan.model.intplan.LeavePlanCalendarBean;
import com.huawei.health.plan.model.intplan.ReplacePlanBean;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.weight.bean.WeightTargetDifferences;
import com.huawei.hihealth.HiDataFilter;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import health.compact.a.CommonUtils;
import health.compact.a.HiDateUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public class fys {
    public static List<IntAction> b(IntPlan intPlan, int i, int i2) {
        IntDayPlan dayByOrder;
        if (intPlan == null) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "getCourseList plan is null");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        IntWeekPlan weekInfoByOrder = intPlan.getWeekInfoByOrder(i);
        if (weekInfoByOrder == null || (dayByOrder = weekInfoByOrder.getDayByOrder(i2)) == null) {
            return arrayList;
        }
        for (int i3 = 0; i3 < dayByOrder.getInPlanActionCnt(); i3++) {
            IntAction inPlanAction = dayByOrder.getInPlanAction(i3);
            if (c(inPlanAction)) {
                arrayList.add(inPlanAction);
            }
        }
        return arrayList;
    }

    public static List<IntAction> e(IntPlan intPlan, int i, int i2) {
        IntDayPlan dayByOrder;
        if (intPlan == null) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "getOutPlanCourseList plan is null");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        IntWeekPlan weekInfoByOrder = intPlan.getWeekInfoByOrder(i);
        if (weekInfoByOrder == null || (dayByOrder = weekInfoByOrder.getDayByOrder(i2)) == null) {
            return arrayList;
        }
        for (int i3 = 0; i3 < dayByOrder.getOutPlanActionCnt(); i3++) {
            IntAction outPlanAction = dayByOrder.getOutPlanAction(i3);
            if (c(outPlanAction)) {
                arrayList.add(outPlanAction);
            }
        }
        return arrayList;
    }

    public static RecordData a(List<RecordData> list, String str) {
        if (list == null) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(list.get(i).getWorkoutId())) {
                return list.get(i);
            }
        }
        return null;
    }

    public static boolean e(IntDayPlan intDayPlan, IntAction.ActionType actionType) {
        return !d(intDayPlan, actionType).isEmpty();
    }

    public static List<IntAction> d(IntDayPlan intDayPlan, IntAction.ActionType actionType) {
        ArrayList arrayList = new ArrayList();
        if (intDayPlan == null) {
            return arrayList;
        }
        for (int i = 0; i < intDayPlan.getInPlanActionCnt(); i++) {
            IntAction inPlanAction = intDayPlan.getInPlanAction(i);
            if (inPlanAction != null && inPlanAction.getActionType() == actionType) {
                arrayList.add(inPlanAction);
            }
        }
        for (int i2 = 0; i2 < intDayPlan.getOutPlanActionCnt(); i2++) {
            IntAction outPlanAction = intDayPlan.getOutPlanAction(i2);
            if (outPlanAction != null && outPlanAction.getActionType() == actionType) {
                arrayList.add(outPlanAction);
            }
        }
        return arrayList;
    }

    public static boolean a(IntPlan intPlan, int i, int i2) {
        return intPlan.getDayInfo(i, i2) != null;
    }

    public static boolean c(IntWeekPlan intWeekPlan, float f) {
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < intWeekPlan.getDayCount(); i3++) {
            IntDayPlan dayByIdx = intWeekPlan.getDayByIdx(i3);
            if (dayByIdx != null) {
                if (dayByIdx.getPunchFlag() == 1) {
                    i2 += dayByIdx.getInPlanActionCnt();
                    i += dayByIdx.getInPlanActionCnt();
                } else {
                    for (int i4 = 0; i4 < dayByIdx.getInPlanActionCnt(); i4++) {
                        IntAction inPlanAction = dayByIdx.getInPlanAction(i4);
                        if (c(inPlanAction)) {
                            i++;
                            if (inPlanAction.getPunchFlag() == 1) {
                                i2++;
                            }
                        }
                    }
                }
            }
        }
        return i <= 0 || ((float) i2) / ((float) i) > f;
    }

    private static boolean c(IntAction intAction) {
        return intAction != null && (IntAction.ActionType.RUN.equals(intAction.getActionType()) || IntAction.ActionType.WORKOUT.equals(intAction.getActionType()));
    }

    public static List<IntAction> b(IntPlan intPlan) {
        IntDayPlan dayByOrder;
        int g = ase.g(intPlan);
        int d = gib.d(Calendar.getInstance().get(7));
        ArrayList arrayList = new ArrayList();
        IntWeekPlan weekInfoByOrder = intPlan.getWeekInfoByOrder(g);
        if (weekInfoByOrder == null || (dayByOrder = weekInfoByOrder.getDayByOrder(d)) == null) {
            return arrayList;
        }
        for (int i = 0; i < dayByOrder.getInPlanActionCnt(); i++) {
            IntAction inPlanAction = dayByOrder.getInPlanAction(i);
            if (inPlanAction != null && !"Race".equals(inPlanAction.getActionId()) && ((inPlanAction.getActionType() == IntAction.ActionType.RUN || inPlanAction.getActionType() == IntAction.ActionType.WORKOUT) && inPlanAction.getActionId() != null)) {
                arrayList.add(inPlanAction);
            }
        }
        return arrayList;
    }

    public static List<IntAction> c(IntPlan intPlan) {
        List<IntAction> b = b(intPlan, ase.g(intPlan), gib.d(Calendar.getInstance().get(7)));
        ArrayList arrayList = new ArrayList();
        for (IntAction intAction : b) {
            if (intAction != null && intAction.getPunchFlag() != 1 && !"Race".equals(intAction.getActionId())) {
                arrayList.add(intAction);
            }
        }
        return arrayList;
    }

    public static void c(IntPlan intPlan, int i, int i2, final IBaseResponseCallback iBaseResponseCallback) {
        ase.e(intPlan, i, i2, new IBaseResponseCallback() { // from class: fza
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i3, Object obj) {
                fys.a(IBaseResponseCallback.this, i3, obj);
            }
        });
    }

    static /* synthetic */ void a(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        if (koq.e(obj, RecordData.class)) {
            List list = (List) obj;
            if (koq.b(list)) {
                iBaseResponseCallback.d(0, false);
                return;
            }
            for (int i2 = 0; i2 < list.size(); i2++) {
                if (((RecordData) list.get(i2)).getIsInPlan() == 0) {
                    iBaseResponseCallback.d(0, true);
                    return;
                }
            }
            iBaseResponseCallback.d(0, false);
        }
    }

    public static boolean a(IntDayPlan intDayPlan) {
        if (intDayPlan == null) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "isDayAllClockIn intDayPlan is null");
            return false;
        }
        int inPlanActionCnt = intDayPlan.getInPlanActionCnt();
        for (int i = 0; i < inPlanActionCnt; i++) {
            IntAction inPlanAction = intDayPlan.getInPlanAction(i);
            if (inPlanAction != null && inPlanAction.getPunchFlag() <= 0) {
                return false;
            }
        }
        return true;
    }

    public static List<String> a(List<IntAction> list) {
        if (koq.b(list)) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (IntAction intAction : list) {
            if (intAction != null) {
                String actionId = intAction.getActionId();
                if (!TextUtils.isEmpty(actionId)) {
                    arrayList.add(actionId);
                }
            }
        }
        return arrayList;
    }

    public static WorkoutRecord d(List<WorkoutRecord> list, RecordData recordData) {
        if (koq.b(list) || recordData == null) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "getWorkoutRecord list ", list, " recordData ", recordData);
            return null;
        }
        String workoutId = recordData.getWorkoutId();
        if (TextUtils.isEmpty(workoutId)) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "getWorkoutRecord workoutId ", workoutId);
            return null;
        }
        for (WorkoutRecord workoutRecord : list) {
            if (workoutRecord != null && workoutId.equals(workoutRecord.acquireWorkoutId()) && workoutRecord.acquireExerciseTime() / 1000 == recordData.getEndTime() / 1000) {
                return workoutRecord;
            }
        }
        return null;
    }

    public static List<WorkoutRecord> d(String str) {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "getWorkoutRecordList api is null");
            return Collections.emptyList();
        }
        return courseApi.getWorkoutRecords(str, new UiCallback<List<WorkoutRecord>>() { // from class: fys.4
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str2) {
                ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "getWorkoutRecordList onFailure errorCode ", Integer.valueOf(i), " errorInfo ", str2);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<WorkoutRecord> list) {
                ReleaseLogUtil.b("R_IntPlanDetailCourseUtil", "getWorkoutRecordList onSuccess recordList ", list);
            }
        });
    }

    public static mnr b(String str) {
        mnr mnrVar = new mnr();
        mnrVar.a(str);
        mnrVar.b(IntAction.ActionType.WORKOUT.getType());
        return mnrVar;
    }

    public static moc a(List<mnr> list, String str, int i, int i2, int i3) {
        moc mocVar = new moc();
        mocVar.b(str);
        mocVar.a(i3);
        mocVar.b(i2);
        mocVar.d(i);
        mocVar.d(list);
        return mocVar;
    }

    public static void c() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "setNeedUpdateCurrentPlan api is null");
        } else {
            planApi.setNeedUpdateCurrentPlan();
        }
    }

    public static void c(moc mocVar, final ResponseCallback<Object> responseCallback) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "updateAction api is null");
            if (responseCallback == null) {
                ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "updateAction callback is null");
                return;
            } else {
                responseCallback.onResponse(-1, "");
                return;
            }
        }
        planApi.updateAction(mocVar, new UiCallback<String>() { // from class: fys.3
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "updateAction onFailure errorCode ", Integer.valueOf(i), " errorInfo ", str);
                ResponseCallback responseCallback2 = ResponseCallback.this;
                if (responseCallback2 == null) {
                    ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "updateAction onFailure callback is null");
                } else {
                    responseCallback2.onResponse(i, str);
                }
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                ResponseCallback responseCallback2 = ResponseCallback.this;
                if (responseCallback2 == null) {
                    ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "updateAction onSuccess callback is null data ", str);
                } else {
                    responseCallback2.onResponse(0, str);
                }
            }
        });
    }

    public static void e(ReplacePlanBean replacePlanBean, final ResponseCallback<Object> responseCallback) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "replacePlanSchedule api is null");
            if (responseCallback == null) {
                ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "replacePlanSchedule callback is null");
                return;
            } else {
                responseCallback.onResponse(-1, "");
                return;
            }
        }
        planApi.replacePlanSchedule(replacePlanBean, new UiCallback<Boolean>() { // from class: fys.1
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "replacePlanSchedule onFailure errorCode ", Integer.valueOf(i), " errorInfo ", str);
                ResponseCallback responseCallback2 = ResponseCallback.this;
                if (responseCallback2 == null) {
                    ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "replacePlanSchedule onFailure callback is null");
                } else {
                    responseCallback2.onResponse(i, str);
                }
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(Boolean bool) {
                ResponseCallback responseCallback2 = ResponseCallback.this;
                if (responseCallback2 == null) {
                    ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "replacePlanSchedule onSuccess callback is null isSuccess ", bool);
                } else {
                    responseCallback2.onResponse(0, bool);
                }
            }
        });
    }

    public static void e(IntPlan intPlan, int i, int i2, final ResponseCallback<Object> responseCallback) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null || intPlan == null) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "leavePlan api ", planApi, " intPlan ", intPlan);
            if (responseCallback == null) {
                ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "leavePlan callback is null");
                return;
            } else {
                responseCallback.onResponse(-1, "");
                return;
            }
        }
        IntPlan.PlanType planType = intPlan.getPlanType();
        LeavePlanCalendarBean.Builder planId = new LeavePlanCalendarBean.Builder().planId(intPlan.getPlanId());
        if (planType == null) {
            planType = IntPlan.PlanType.AI_FITNESS_PLAN;
        }
        planApi.leavePlan(planId.category(Integer.valueOf(planType.getType())).leaveInfo(new LeaveInfo.Builder().startDate(String.valueOf(i)).dayNum(Integer.valueOf(i2)).build()).build(), new UiCallback<IntPlan>() { // from class: fys.5
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i3, String str) {
                ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "leavePlan onFailure errorCode ", Integer.valueOf(i3), " errorInfo ", str);
                ResponseCallback responseCallback2 = ResponseCallback.this;
                if (responseCallback2 == null) {
                    ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "leavePlan onFailure callback is null");
                } else {
                    responseCallback2.onResponse(i3, str);
                }
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(IntPlan intPlan2) {
                ResponseCallback responseCallback2 = ResponseCallback.this;
                if (responseCallback2 == null) {
                    ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "leavePlan onSuccess callback is null data ", intPlan2);
                } else {
                    responseCallback2.onResponse(0, intPlan2);
                }
            }
        });
    }

    public static void a(IntPlan intPlan, int i, final ResponseCallback<Object> responseCallback) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null || intPlan == null) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "cancelLeave api ", planApi, " intPlan ", intPlan);
            if (responseCallback == null) {
                ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "cancelLeave callback is null");
                return;
            } else {
                responseCallback.onResponse(-1, "");
                return;
            }
        }
        IntPlan.PlanType planType = intPlan.getPlanType();
        LeavePlanCalendarBean.Builder planId = new LeavePlanCalendarBean.Builder().planId(intPlan.getPlanId());
        if (planType == null) {
            planType = IntPlan.PlanType.AI_FITNESS_PLAN;
        }
        planApi.cancelLeave(planId.category(Integer.valueOf(planType.getType())).leaveInfo(new LeaveInfo.Builder().startDate(String.valueOf(i)).build()).build(), new UiCallback<IntPlan>() { // from class: fys.2
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str) {
                ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "cancelLeave onFailure errorCode ", Integer.valueOf(i2), " errorInfo ", str);
                ResponseCallback responseCallback2 = ResponseCallback.this;
                if (responseCallback2 == null) {
                    ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "cancelLeave onFailure callback is null");
                } else {
                    responseCallback2.onResponse(i2, str);
                }
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(IntPlan intPlan2) {
                ResponseCallback responseCallback2 = ResponseCallback.this;
                if (responseCallback2 == null) {
                    ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "cancelLeave onSuccess callback is null data ", intPlan2);
                } else {
                    responseCallback2.onResponse(0, intPlan2);
                }
            }
        });
    }

    public static void a(IntPlan intPlan, int i, int i2, final ResponseCallback<Object> responseCallback) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null || intPlan == null) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "updatePlanCalendar api ", planApi, " plan ", intPlan);
            if (responseCallback == null) {
                ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "updatePlanCalendar callback is null");
                return;
            } else {
                responseCallback.onResponse(-1, "");
                return;
            }
        }
        IntPlan.PlanType planType = intPlan.getPlanType();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DayInfo.Builder().adjustDay(String.valueOf(i)).dayStatus(Integer.valueOf(i2)).build());
        planApi.updatePlanCalendar(new LeavePlanCalendarBean.Builder().planId(intPlan.getPlanId()).category(Integer.valueOf(planType == null ? IntPlan.PlanType.AI_FITNESS_PLAN.getType() : planType.getType())).days(arrayList).operationType(1).build(), new UiCallback<IntPlan>() { // from class: fys.8
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i3, String str) {
                ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "updatePlanCalendar onFailure errorCode ", Integer.valueOf(i3), " errorInfo ", str);
                ResponseCallback responseCallback2 = ResponseCallback.this;
                if (responseCallback2 == null) {
                    ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "updatePlanCalendar onFailure callback is null");
                } else {
                    responseCallback2.onResponse(i3, str);
                }
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(IntPlan intPlan2) {
                ResponseCallback responseCallback2 = ResponseCallback.this;
                if (responseCallback2 == null) {
                    ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "updatePlanCalendar onSuccess callback is null data ", intPlan2);
                } else {
                    responseCallback2.onResponse(0, intPlan2);
                }
            }
        });
    }

    public static void b(String str, int i, List<DayInfo> list, final UiCallback<IntPlan> uiCallback) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null || str == null) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "adjustPlanSchedule api ", planApi, " planId ", str);
            if (uiCallback == null) {
                ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "adjustPlanSchedule callback is null");
                return;
            } else {
                uiCallback.onFailure(-1, "");
                return;
            }
        }
        ReleaseLogUtil.b("R_IntPlanDetailCourseUtil", "adjustPlanSchedule:", ghb.e(list));
        planApi.updatePlanCalendar(new LeavePlanCalendarBean.Builder().planId(str).category(Integer.valueOf(i)).operationType(2).days(list).build(), new UiCallback<IntPlan>() { // from class: fys.9
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str2) {
                ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "adjustPlanSchedule onFailure errorCode ", Integer.valueOf(i2), " errorInfo ", str2);
                UiCallback uiCallback2 = UiCallback.this;
                if (uiCallback2 == null) {
                    ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "adjustPlanSchedule onFailure callback is null");
                } else {
                    uiCallback2.onFailure(i2, str2);
                }
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(IntPlan intPlan) {
                UiCallback uiCallback2 = UiCallback.this;
                if (uiCallback2 == null) {
                    ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "adjustPlanSchedule onSuccess callback is null");
                } else {
                    uiCallback2.onSuccess(intPlan);
                }
            }
        });
    }

    private static HiDataReadProOption e(HiDataReadOption hiDataReadOption, double d) {
        return HiDataReadProOption.builder().e(hiDataReadOption).d(HiJsonUtil.e(new HiDataFilter(new HiDataFilter.DataFilterExpression(47401, "=", d)))).e();
    }

    private static int e(int i) {
        return CommonUtils.h(HiHealthDataType.b(i));
    }

    private static void aIy_(SparseLongArray sparseLongArray, HiHealthData hiHealthData, int i) {
        if (Double.compare(hiHealthData.getValue(), e(i)) == 0) {
            sparseLongArray.append(i, hiHealthData.getStartTime());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(Object obj, int i, ResponseCallback<Object> responseCallback) {
        if (!(obj instanceof SparseArray)) {
            responseCallback.onResponse(i, new SparseLongArray());
            return;
        }
        List<HiHealthData> list = (List) ((SparseArray) obj).get(47401);
        if (koq.b(list)) {
            responseCallback.onResponse(i, new SparseLongArray());
            return;
        }
        SparseLongArray sparseLongArray = new SparseLongArray();
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                aIy_(sparseLongArray, hiHealthData, 10016);
                aIy_(sparseLongArray, hiHealthData, 10017);
                aIy_(sparseLongArray, hiHealthData, 10019);
                aIy_(sparseLongArray, hiHealthData, 10020);
            }
        }
        responseCallback.onResponse(i, sparseLongArray);
    }

    public static void b(final ResponseCallback<Object> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "getLastMenstrual callback is null");
            return;
        }
        if (fnz.b() != 1) {
            responseCallback.onResponse(-1, new SparseLongArray());
            return;
        }
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, HiDateUtil.f(HiDateUtil.a(HiDateUtil.c(System.currentTimeMillis(), -15))));
        hiDataReadOption.setType(new int[]{47401});
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setCount(1);
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(e(hiDataReadOption, e(10016)));
        arrayList.add(e(hiDataReadOption, e(10017)));
        arrayList.add(e(hiDataReadOption, e(10019)));
        arrayList.add(e(hiDataReadOption, e(10020)));
        HiHealthNativeApi.a(BaseApplication.e()).readHiHealthDataEx(arrayList, new HiDataReadResultListener() { // from class: fys.6
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                fys.a(obj, i, (ResponseCallback<Object>) ResponseCallback.this);
            }
        });
    }

    public static int aIA_(SparseLongArray sparseLongArray) {
        if (sparseLongArray == null) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "getMenstrualStatus array is null");
            return 0;
        }
        int b = DateFormatUtil.b(sparseLongArray.get(10016));
        int b2 = DateFormatUtil.b(sparseLongArray.get(10017));
        int b3 = DateFormatUtil.b(System.currentTimeMillis());
        if (b > 0 && b2 > 0 && b <= b3 && b3 <= b2) {
            return 1;
        }
        int b4 = DateFormatUtil.b(sparseLongArray.get(10019));
        int b5 = DateFormatUtil.b(sparseLongArray.get(10020));
        return (b4 <= 0 || b5 <= 0 || b4 > b3 || b3 > b5) ? 0 : 2;
    }

    public static int aIz_(SparseLongArray sparseLongArray) {
        if (sparseLongArray == null) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "getMenstrualLastDate array is null");
            return 0;
        }
        int b = DateFormatUtil.b(sparseLongArray.get(10016));
        int b2 = DateFormatUtil.b(sparseLongArray.get(10017));
        int b3 = DateFormatUtil.b(System.currentTimeMillis());
        if (b > 0 && b2 > 0 && b <= b3 && b3 <= b2) {
            return b2;
        }
        int b4 = DateFormatUtil.b(sparseLongArray.get(10019));
        int b5 = DateFormatUtil.b(sparseLongArray.get(10020));
        if (b4 <= 0 || b5 <= 0 || b4 > b3 || b3 > b5) {
            return 0;
        }
        return b5;
    }

    public static boolean aIB_(int i, SparseLongArray sparseLongArray) {
        int b;
        if (sparseLongArray == null) {
            return false;
        }
        int e = jfa.e("intelligent_plan", "MENSTRUAL_SHOW_DIALOG_LAST_TIME", 0);
        if (i != 1) {
            return i == 2 && (b = DateFormatUtil.b(sparseLongArray.get(10019))) > 0 && e < b;
        }
        int b2 = DateFormatUtil.b(sparseLongArray.get(10016));
        return b2 > 0 && e < b2;
    }

    public static void d(int i) {
        jfa.c("intelligent_plan", "MENSTRUAL_SHOW_DIALOG_LAST_TIME", i);
    }

    public static float a(IntPlan intPlan) {
        if (intPlan == null) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "getWeightGoalForPlan intPlan is null");
            return 0.0f;
        }
        Goal goal = intPlan.getGoal("weight");
        if (goal == null) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "getWeightGoalForPlan goal is null");
            return 0.0f;
        }
        Object goalDst = goal.getGoalDst();
        if (!(goalDst instanceof Float)) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "getWeightGoalForPlan object ", goalDst);
            return 0.0f;
        }
        return ((Float) goalDst).floatValue();
    }

    public static void c(final IntPlan intPlan, final ResponseCallback<Boolean> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "examineWeightManagerGoal callback is null");
            return;
        }
        if (intPlan == null) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "examineWeightManagerGoal intPlan is null");
            responseCallback.onResponse(-1, false);
            return;
        }
        if (intPlan.getMetaInfo() == null) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "examineWeightManagerGoal planMetaInfo is null");
            responseCallback.onResponse(-1, false);
            return;
        }
        final float a2 = a(intPlan);
        ReleaseLogUtil.b("R_IntPlanDetailCourseUtil", "examineWeightManagerGoal goal ", Float.valueOf(a2));
        if (a2 <= 0.0f) {
            responseCallback.onResponse(-1, false);
            return;
        }
        if (intPlan.getPlanTimeInfo() == null) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "examineWeightManagerGoal planTimeInfo is null");
            responseCallback.onResponse(-1, false);
            return;
        }
        if (!jfa.a()) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "examineWeightManagerGoal isPullAllStatus false");
            responseCallback.onResponse(-1, false);
            return;
        }
        String planId = intPlan.getPlanId();
        String d = jfa.d("intelligent_plan", "STOP_PLAN_SHOW_DIALOG_LAST_PLAN_ID", "");
        LogUtil.c("R_IntPlanDetailCourseUtil", "examineWeightManagerGoal planId ", planId, " cachePlanId ", d);
        if (!TextUtils.isEmpty(d) && d.equals(planId)) {
            responseCallback.onResponse(0, false);
            return;
        }
        if (!LanguageUtil.m(BaseApplication.e())) {
            responseCallback.onResponse(0, false);
        } else if (HandlerExecutor.b()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: fzb
                @Override // java.lang.Runnable
                public final void run() {
                    fys.c(IntPlan.this, (ResponseCallback<Boolean>) responseCallback);
                }
            });
        } else {
            grz.b((ResponseCallback<WeightTargetDifferences>) new ResponseCallback() { // from class: fyz
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    fys.d(ResponseCallback.this, a2, i, (WeightTargetDifferences) obj);
                }
            });
        }
    }

    static /* synthetic */ void d(final ResponseCallback responseCallback, final float f, int i, WeightTargetDifferences weightTargetDifferences) {
        LogUtil.c("R_IntPlanDetailCourseUtil", "examineWeightManagerGoal differences ", weightTargetDifferences);
        if (weightTargetDifferences == null) {
            responseCallback.onResponse(-1, false);
            return;
        }
        WeightTargetDifferences.WeightTargetType d = weightTargetDifferences.d();
        ReleaseLogUtil.b("R_IntPlanDetailCourseUtil", "examineWeightManagerGoal weightTargetType ", d);
        if (WeightTargetDifferences.WeightTargetType.WEIGHT_LOSS != d) {
            responseCallback.onResponse(0, true);
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: fyu
                @Override // java.lang.Runnable
                public final void run() {
                    kot.a().c(new ResponseCallback() { // from class: fyy
                        @Override // com.huawei.hwbasemgr.ResponseCallback
                        public final void onResponse(int i2, Object obj) {
                            fys.b(r1, r2, i2, (Float) obj);
                        }
                    });
                }
            });
        }
    }

    static /* synthetic */ void b(float f, ResponseCallback responseCallback, int i, Float f2) {
        LogUtil.c("R_IntPlanDetailCourseUtil", "examineWeightManagerGoal floatData ", f2, " goal ", Float.valueOf(f));
        if (f2 == null) {
            responseCallback.onResponse(-1, false);
        } else {
            responseCallback.onResponse(0, Boolean.valueOf(f != f2.floatValue()));
        }
    }

    public static void e(IntPlan intPlan) {
        if (intPlan == null) {
            ReleaseLogUtil.a("R_IntPlanDetailCourseUtil", "setLastStopPlanShowDialog intPlan is null");
            return;
        }
        String planId = intPlan.getPlanId();
        LogUtil.c("R_IntPlanDetailCourseUtil", "setLastStopPlanShowDialog planId ", planId);
        jfa.f("intelligent_plan", "STOP_PLAN_SHOW_DIALOG_LAST_PLAN_ID", planId);
    }

    public static boolean a() {
        boolean a2 = jfa.a();
        ReleaseLogUtil.b("R_IntPlanDetailCourseUtil", "isNeedRefreshPlan isPullAllStatus ", Boolean.valueOf(a2));
        if (!a2) {
            return false;
        }
        long d = jfa.d("intelligent_plan", "LAST_REFRESH_PLAN_TIME_MILLIS", 0L);
        ReleaseLogUtil.b("R_IntPlanDetailCourseUtil", "isNeedRefreshPlan timeMillis ", Long.valueOf(d));
        return !jdl.ac(d) || Math.abs(System.currentTimeMillis() - d) >= 3600000;
    }

    public static void e() {
        jfa.b("intelligent_plan", "LAST_REFRESH_PLAN_TIME_MILLIS", System.currentTimeMillis());
    }
}
