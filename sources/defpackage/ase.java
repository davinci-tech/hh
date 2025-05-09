package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.SparseArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.basefitnessadvice.model.DayDataForDevice;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.WeekDataForDevice;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.IntWeekPlan;
import com.huawei.basefitnessadvice.model.intplan.PlanMetaInfo;
import com.huawei.basefitnessadvice.model.intplan.PlanTimeInfo;
import com.huawei.basefitnessadvice.model.intplan.RecordData;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.weight.WeightApi;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import com.huawei.pluginfitnessadvice.plandata.CourseDataBean;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class ase {
    public static boolean f() {
        return gpn.d();
    }

    public static boolean k(IntPlan intPlan) {
        return intPlan != null && IntPlan.PlanType.AI_FITNESS_PLAN.equals(intPlan.getPlanType()) && d();
    }

    public static boolean d() {
        return f() && Utils.o();
    }

    public static boolean e() {
        if (Utils.o()) {
            return gsd.a("RECOMMEND_DIET_TIME_MILLIS");
        }
        return true;
    }

    public static boolean a() {
        return !Utils.o();
    }

    public static boolean b() {
        if (Utils.o()) {
            return gsd.a("DIET_ANALYSIS_TIME_MILLIS");
        }
        return true;
    }

    public static int b(Plan plan) {
        if (plan == null) {
            LogUtil.h("Suggestion_PlanUtil", "getTrainingDays plan is null");
            return 0;
        }
        List<moa> planWeekDataList = plan.getPlanWeekDataList();
        if (koq.b(planWeekDataList)) {
            LogUtil.h("Suggestion_PlanUtil", "getTrainingDays planWeekDataBeanList is empty");
            return 0;
        }
        int i = 0;
        for (moa moaVar : planWeekDataList) {
            if (moaVar != null) {
                i += moaVar.i();
            }
        }
        for (moa moaVar2 : planWeekDataList) {
            if (moaVar2 != null) {
                Iterator<mnu> it = moaVar2.j().iterator();
                while (it.hasNext()) {
                    i = a(it.next(), i);
                }
            }
        }
        if (i < 0) {
            return 0;
        }
        return i;
    }

    public static List<kwm> c(Context context, List<kwm> list) {
        if (koq.b(list)) {
            ReleaseLogUtil.c("Suggestion_PlanUtil", "times is empty");
            return new ArrayList();
        }
        List<kwm> c2 = c(context);
        if (koq.b(c2)) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (kwm kwmVar : list) {
            if (!c2.contains(kwmVar)) {
                arrayList.add(kwmVar);
            }
        }
        return arrayList;
    }

    public static void d(Context context, List<kwm> list) {
        if (list == null || context == null) {
            ReleaseLogUtil.c("Suggestion_PlanUtil", "context is null, or showTimes is null");
            return;
        }
        List<kwm> c2 = c(context);
        c2.addAll(list);
        Iterator<kwm> it = c2.iterator();
        while (it.hasNext()) {
            if (!DateUtils.isToday(it.next().b())) {
                it.remove();
            }
        }
        SharedPreferenceManager.c("ai_fitness_plan_module_id", d(context), HiJsonUtil.e(c2));
    }

    private static List<kwm> c(Context context) {
        ArrayList arrayList;
        String e = SharedPreferenceManager.e("ai_fitness_plan_module_id", d(context), "");
        if (TextUtils.isEmpty(e)) {
            return new ArrayList();
        }
        try {
            arrayList = (ArrayList) HiJsonUtil.b(e, new TypeToken<List<kwm>>() { // from class: ase.4
            }.getType());
        } catch (JsonIOException | JsonSyntaxException | IllegalStateException | NumberFormatException e2) {
            LogUtil.b("Suggestion_PlanUtil", "getCacheData catch all Exception ", LogAnonymous.b(e2));
            arrayList = null;
        }
        return arrayList == null ? new ArrayList() : arrayList;
    }

    private static String d(Context context) {
        return LoginInit.getInstance(context).getAccountInfo(1011) + "_ai_fitness_energy_replacement_card_show_time";
    }

    public static int a(mnu mnuVar, int i) {
        if (mnuVar == null) {
            return i;
        }
        for (CourseDataBean courseDataBean : mnuVar.a()) {
            if (courseDataBean != null && "Race".equals(courseDataBean.a())) {
                i--;
            }
        }
        return i;
    }

    public static int c(Plan plan) {
        int i = 0;
        if (plan == null) {
            LogUtil.h("Suggestion_PlanUtil", "getClockTimes plan is null");
            return 0;
        }
        List<moa> planWeekDataList = plan.getPlanWeekDataList();
        if (koq.b(planWeekDataList)) {
            LogUtil.h("Suggestion_PlanUtil", "getClockTimes planWeekDataBeanList is empty");
            return 0;
        }
        for (moa moaVar : planWeekDataList) {
            if (moaVar != null) {
                for (mnu mnuVar : moaVar.j()) {
                    if (mnuVar != null && mnuVar.e()) {
                        i++;
                    }
                }
            }
        }
        return i;
    }

    public static mnu d(Plan plan, int i, int i2) {
        List<mnu> j = d(i2, plan).j();
        mnu mnuVar = new mnu();
        if (koq.b(j)) {
            LogUtil.h("Suggestion_PlanUtil", "getPlanDayData planDayDataBeanList is empty");
            return mnuVar;
        }
        for (mnu mnuVar2 : j) {
            if (mnuVar2 != null && mnuVar2.c() == i) {
                return mnuVar2;
            }
        }
        return mnuVar;
    }

    public static int e(Plan plan) {
        return c(plan, System.currentTimeMillis());
    }

    public static int g(IntPlan intPlan) {
        return e(intPlan, System.currentTimeMillis());
    }

    public static int e(IntPlan intPlan, long j) {
        long startTime;
        if (intPlan == null) {
            ReleaseLogUtil.b("Suggestion_PlanUtil", "getPlanWeekNum null.");
            return 0;
        }
        if (intPlan.getPlanTimeInfo() != null) {
            startTime = intPlan.getPlanTimeInfo().getBeginDate();
        } else {
            startTime = intPlan.getCompatiblePlan() != null ? intPlan.getCompatiblePlan().getStartTime() : 0L;
        }
        return gib.a(TimeUnit.SECONDS.toMillis(startTime), null, j, null) + 1;
    }

    public static int c(Plan plan, long j) {
        if (plan == null) {
            return 0;
        }
        return gib.a(1000 * plan.getStartTime(), c(plan.getTimeZone()), j, null) + 1;
    }

    public static moa a(Plan plan) {
        return d(e(plan), plan);
    }

    public static moa d(int i, Plan plan) {
        moa moaVar = new moa();
        if (plan == null) {
            LogUtil.h("Suggestion_PlanUtil", "getPlanWeekData plan is null");
            return moaVar;
        }
        List<moa> planWeekDataList = plan.getPlanWeekDataList();
        if (koq.b(planWeekDataList)) {
            LogUtil.h("Suggestion_PlanUtil", "getPlanWeekData planWeekDataBeanList is empty");
            return moaVar;
        }
        for (moa moaVar2 : planWeekDataList) {
            if (moaVar2 != null && moaVar2.f() == i) {
                return moaVar2;
            }
        }
        return moaVar;
    }

    public static void e(Plan plan, int i, int i2, String str) {
        mnu d = d(plan, i2, i);
        List<CourseDataBean> a2 = d.a();
        if (koq.b(a2)) {
            LogUtil.h("Suggestion_PlanUtil", "setClockTime courseDataBeanList is null");
            return;
        }
        Iterator<CourseDataBean> it = a2.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            CourseDataBean next = it.next();
            if (next != null && str.equals(next.a())) {
                next.e(true);
                break;
            }
        }
        b(d, a2);
    }

    private static void b(mnu mnuVar, List<CourseDataBean> list) {
        boolean z;
        Iterator<CourseDataBean> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = true;
                break;
            }
            CourseDataBean next = it.next();
            if (next != null && !next.d()) {
                z = false;
                break;
            }
        }
        if (mnuVar.e()) {
            return;
        }
        mnuVar.b(z);
    }

    public static boolean e(Plan plan, int i) {
        List<mnu> j = d(i, plan).j();
        if (koq.b(j)) {
            LogUtil.h("Suggestion_PlanUtil", "isAllNoClockIn planDayDataBeanList is empty");
            return false;
        }
        for (mnu mnuVar : j) {
            if (mnuVar != null && mnuVar.e()) {
                return false;
            }
        }
        return true;
    }

    public static int c(int i, String str, int i2) {
        return (int) TimeUnit.MILLISECONDS.toSeconds(gib.a(TimeUnit.SECONDS.toMillis(i), str, i2));
    }

    public static TimeZone c(String str) {
        return gib.e(str);
    }

    public static int a(String str, long j) {
        return (int) TimeUnit.MILLISECONDS.toSeconds(gib.b(str, TimeUnit.SECONDS.toMillis(j)));
    }

    public static final long d(long j) {
        return gib.f(j);
    }

    public static final long d(Plan plan, int i) {
        if (plan == null) {
            return 0L;
        }
        long millis = TimeUnit.SECONDS.toMillis(plan.getStartTime());
        return TimeUnit.MILLISECONDS.toSeconds(Math.max(millis, gib.a(millis, gib.e(plan.getTimeZone()), i - 1)));
    }

    public static final long a(IntPlan intPlan, int i) {
        if (intPlan == null) {
            return 0L;
        }
        long millis = TimeUnit.SECONDS.toMillis(intPlan.getPlanTimeInfo().getBeginDate());
        return TimeUnit.MILLISECONDS.toSeconds(Math.max(millis, gib.b(millis, i - 1)));
    }

    public static final long a(Plan plan, int i, int i2) {
        return gib.a(TimeUnit.SECONDS.toMillis(ggl.b(plan.acquireStartDate(), "yyyy-MM-dd")), i - 1, i2);
    }

    public static final String d(Context context, Plan plan) {
        return (context == null || plan == null) ? "" : DateUtils.formatDateTime(context, TimeUnit.SECONDS.toMillis(ggl.b(plan.acquireStartDate(), "yyyy-MM-dd")), 24);
    }

    public static final long d(IntPlan intPlan, int i, int i2) {
        return gib.a(TimeUnit.SECONDS.toMillis(intPlan.getPlanTimeInfo().getBeginDate()), i - 1, i2);
    }

    public static final long a(Plan plan, int i) {
        if (plan == null) {
            return 0L;
        }
        return TimeUnit.MILLISECONDS.toSeconds(Math.min(TimeUnit.SECONDS.toMillis(plan.getEndTime()), gib.c(TimeUnit.SECONDS.toMillis(plan.getStartTime()), gib.e(plan.getTimeZone()), i - 1)));
    }

    public static final long e(IntPlan intPlan, int i) {
        long c2;
        if (intPlan == null || intPlan.getPlanTimeInfo() == null) {
            return 0L;
        }
        long millis = TimeUnit.SECONDS.toMillis(intPlan.getPlanTimeInfo().getBeginDate());
        long millis2 = TimeUnit.SECONDS.toMillis(intPlan.getPlanTimeInfo().getEndDate());
        if (IntPlan.PlanType.AI_RUN_PLAN.equals(intPlan.getPlanType())) {
            c2 = gib.c(millis, i - 1);
        } else {
            c2 = gib.c(millis, gib.e(intPlan.getTimeZone()), i - 1);
        }
        return TimeUnit.MILLISECONDS.toSeconds(Math.min(millis2, c2));
    }

    public static boolean d(Plan plan) {
        if (plan == null) {
            LogUtil.h("Suggestion_PlanUtil", "planNeedUpdate plan is null.");
            return true;
        }
        return c(e(plan), plan.acquireId());
    }

    public static boolean n(IntPlan intPlan) {
        if (intPlan == null) {
            LogUtil.h("Suggestion_PlanUtil", "planNeedUpdate intPlan is null.");
            return true;
        }
        return c(g(intPlan), intPlan.getPlanId());
    }

    public static boolean c(int i, String str) {
        int e = CommonUtil.e(ash.d("plan_update_week", str), 1);
        if (i > e) {
            ReleaseLogUtil.a("Suggestion_PlanUtil", "plan need update, currentWeek is:", Integer.valueOf(i), " updatedWeek is:", Integer.valueOf(e));
        }
        return i > e;
    }

    public static TreeSet<String> a(int i, IntPlan intPlan) {
        if (intPlan == null) {
            LogUtil.h("Suggestion_PlanUtil", "getPlanWeekCourseIdSet failed plan is null.");
            return new TreeSet<>();
        }
        return c(intPlan.getWeekInfoByOrder(i));
    }

    public static TreeSet<String> c(IntWeekPlan intWeekPlan) {
        TreeSet<String> treeSet = new TreeSet<>();
        if (intWeekPlan == null) {
            LogUtil.h("Suggestion_PlanUtil", "getPlanWeekCourseIdSet failed planWeekDataBean is null.");
            return treeSet;
        }
        for (int i = 0; i < intWeekPlan.getDayCount(); i++) {
            IntDayPlan dayByIdx = intWeekPlan.getDayByIdx(i);
            if (dayByIdx != null) {
                for (int i2 = 0; i2 < dayByIdx.getInPlanActionCnt(); i2++) {
                    IntAction inPlanAction = dayByIdx.getInPlanAction(i2);
                    if (inPlanAction != null && !"Race".equals(inPlanAction.getActionId())) {
                        treeSet.add(inPlanAction.getActionId());
                    }
                }
            }
        }
        LogUtil.h("Suggestion_PlanUtil", "getFitWorkoutList currentWeek is ", Integer.valueOf(intWeekPlan.getWeekOrder()), " treeSet size is ", Integer.valueOf(treeSet.size()));
        return treeSet;
    }

    public static boolean l(IntPlan intPlan) {
        if (intPlan != null && intPlan.getMetaInfo() != null) {
            if (IntPlan.PlanType.AI_RUN_PLAN.equals(intPlan.getPlanType()) || IntPlan.PlanType.RUN_PLAN.equals(intPlan.getPlanType())) {
                return true;
            }
            if (IntPlan.PlanType.FIT_PLAN.equals(intPlan.getPlanType()) && intPlan.getMetaInfo().getPlanCategory() == 1) {
                return true;
            }
        }
        return false;
    }

    public static boolean e(FitnessPackageInfo fitnessPackageInfo) {
        if (fitnessPackageInfo == null) {
            return false;
        }
        return IntPlan.PlanType.AI_RUN_PLAN.getType() == fitnessPackageInfo.getPlanType() || IntPlan.PlanType.RUN_PLAN.getType() == fitnessPackageInfo.getPlanType() || (fitnessPackageInfo.getPlanType() == IntPlan.PlanType.FIT_PLAN.getType() && fitnessPackageInfo.acquirePlanCategory() == 1);
    }

    public static boolean f(IntPlan intPlan) {
        return intPlan != null && intPlan.getMetaInfo() != null && IntPlan.PlanType.FIT_PLAN.equals(intPlan.getPlanType()) && (intPlan.getMetaInfo().getPlanCategory() == 0 || intPlan.getMetaInfo().getPlanCategory() == 2);
    }

    public static boolean d(FitnessPackageInfo fitnessPackageInfo) {
        if (fitnessPackageInfo != null && IntPlan.PlanType.FIT_PLAN.getType() == fitnessPackageInfo.getPlanType()) {
            return fitnessPackageInfo.acquirePlanCategory() == 0 || fitnessPackageInfo.acquirePlanCategory() == 2;
        }
        return false;
    }

    public static void d(IntPlan intPlan, String str, int... iArr) {
        if (intPlan == null || intPlan.getMetaInfo() == null) {
            return;
        }
        HashMap hashMap = new HashMap(6);
        hashMap.put("click", "1");
        hashMap.put("planName", intPlan.getMetaInfo().getName());
        hashMap.put("planId", intPlan.getPlanId());
        if (l(intPlan)) {
            hashMap.put("planType", 0);
        } else {
            hashMap.put("planType", 1);
        }
        if (intPlan.getStat("progress") != null) {
            hashMap.put("finishRate", intPlan.getStat("progress").getValue());
        } else {
            hashMap.put("finishRate", 0);
        }
        hashMap.put("enter", str);
        hashMap.putAll(g());
        gge.e("1120024", hashMap);
        if (iArr != null && iArr.length >= 3) {
            c(iArr[0], iArr[1], o(intPlan) ? 2 : 1, iArr[2]);
        } else {
            fhu.e().a();
        }
    }

    public static boolean o(IntPlan intPlan) {
        if (intPlan.getPlanTimeInfo() == null) {
            LogUtil.h("Suggestion_PlanUtil", "isPlanOverdue getPlanTimeInfo() == null.");
            return false;
        }
        int i = gib.i(intPlan.getPlanTimeInfo().getEndDate() * 1000);
        int i2 = gib.i(gib.b(System.currentTimeMillis()));
        LogUtil.a("Suggestion_PlanUtil", "isPlanOverdue currentDay:", Integer.valueOf(i2), " endDay", Integer.valueOf(i));
        return i2 > i;
    }

    public static void c(final int i, final int i2, final int i3, final int i4) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: asa
            @Override // java.lang.Runnable
            public final void run() {
                ase.b(i, i2, i3, i4);
            }
        });
    }

    static /* synthetic */ void b(int i, int i2, int i3, int i4) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        if (i == 0) {
            hashMap.put("type", "");
        } else {
            hashMap.put("type", Integer.valueOf(i));
        }
        hashMap.put(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE, Integer.valueOf(i2));
        hashMap.put("enableStatus", Integer.valueOf(i3));
        hashMap.put(ParamConstants.Param.VIEW_TYPE, Integer.valueOf(i4));
        hashMap.putAll(g());
        fhu.e().a();
        gge.e(AnalyticsValue.INT_PLAN_2030074.value(), hashMap);
    }

    private static Map<String, Object> g() {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(fhu.e().d())) {
            hashMap.put(WebViewHelp.BI_KEY_PULL_FROM, fhu.e().d());
        }
        if (!TextUtils.isEmpty(fhu.e().b())) {
            hashMap.put("resourceId", fhu.e().b());
        }
        if (!TextUtils.isEmpty(fhu.e().c())) {
            hashMap.put("pullOrder", fhu.e().c());
        }
        if (!TextUtils.isEmpty(fhu.e().j())) {
            hashMap.put("resourceName", fhu.e().j());
        }
        return hashMap;
    }

    public static int[] a(WorkoutRecord workoutRecord, IntPlan intPlan) {
        Calendar calendar = Calendar.getInstance();
        int[] iArr = new int[2];
        if (workoutRecord == null) {
            return iArr;
        }
        int planTrainDate = workoutRecord.getPlanTrainDate();
        ReleaseLogUtil.b("Suggestion_PlanUtil", "getClockParams trainPlanDate:", Integer.valueOf(planTrainDate));
        if (planTrainDate == 0) {
            return iArr;
        }
        long e = ggl.e(planTrainDate);
        calendar.setTimeInMillis(e);
        int d = gib.d(calendar.get(7));
        int e2 = e(intPlan, e);
        if (e2 < 1) {
            ReleaseLogUtil.c("Suggestion_PlanUtil", "weekNo:", Integer.valueOf(e2), " coursePlanTime:", Long.valueOf(e));
            if ((intPlan.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN) || intPlan.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN)) && !workoutRecord.isFitnessRecordFromDevice()) {
                e2 = g(intPlan);
                ReleaseLogUtil.b("Suggestion_PlanUtil", "new week num:", Integer.valueOf(e2));
            }
        }
        iArr[0] = e2;
        iArr[1] = d;
        return iArr;
    }

    public static void t(IntPlan intPlan) {
        if (intPlan == null) {
            ReleaseLogUtil.a("Suggestion_PlanUtil", "int plan is null.");
            return;
        }
        StringBuilder sb = new StringBuilder("compatiblePlan exist:");
        sb.append(intPlan.getCompatiblePlan() != null);
        sb.append(" timeZone:");
        sb.append(intPlan.getTimeZone());
        sb.append(" transactionStatus:");
        sb.append(intPlan.getTransactionStatus());
        if (intPlan.getPlanTimeInfo() != null) {
            PlanTimeInfo planTimeInfo = intPlan.getPlanTimeInfo();
            sb.append(" createTime:");
            sb.append(planTimeInfo.getCreateTime());
            sb.append(" finishTime:");
            sb.append(planTimeInfo.getFinishTime());
            sb.append(" remindTime:");
            sb.append(planTimeInfo.getRemindTime());
            sb.append(" beginDate:");
            sb.append(planTimeInfo.getBeginDate());
            sb.append(" endDate:");
            sb.append(planTimeInfo.getEndDate());
            sb.append(" reportTime:");
            sb.append(planTimeInfo.getReportTime());
            sb.append(" modifyTime:");
            sb.append(planTimeInfo.getModifyTime());
        } else {
            sb.append(" int plan time info is null.");
        }
        if (intPlan.getMetaInfo() != null) {
            PlanMetaInfo metaInfo = intPlan.getMetaInfo();
            sb.append(" planType:");
            sb.append(metaInfo.getPlanType().getType());
            sb.append(" planId:");
            sb.append(metaInfo.getPlanId());
            sb.append(" name:");
            sb.append(metaInfo.getName());
            sb.append(" planTempId:");
            sb.append(metaInfo.getPlanTempId());
            sb.append(" planCategory:");
            sb.append(metaInfo.getPlanCategory());
            sb.append(" displayStyle");
            sb.append(metaInfo.getDisplayStyle());
            sb.append(" exeDayNum:");
            sb.append(metaInfo.getExeDayNum());
            sb.append(" exeDays:");
            sb.append(metaInfo.getExeDayNum());
            sb.append(" weekCount:");
            sb.append(metaInfo.getWeekCount());
            sb.append(" dayCount:");
            sb.append(metaInfo.getDayCount());
            for (int i = 0; i < intPlan.getMetaInfo().getWeekCount(); i++) {
                IntWeekPlan weekInfoByIdx = intPlan.getWeekInfoByIdx(i);
                if (weekInfoByIdx == null) {
                    sb.append(" week info null:");
                    sb.append(i);
                } else {
                    sb.append(" week info order:");
                    sb.append(weekInfoByIdx.getWeekOrder());
                    sb.append(" index:");
                    sb.append(i);
                    sb.append(d(weekInfoByIdx));
                }
            }
        } else {
            sb.append("int plan meta info is null.");
        }
        ReleaseLogUtil.b("Suggestion_PlanUtil", sb.toString());
    }

    private static String d(IntWeekPlan intWeekPlan) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (int i2 = 0; i2 < intWeekPlan.getDayCount(); i2++) {
            IntDayPlan dayByIdx = intWeekPlan.getDayByIdx(i2);
            if (dayByIdx != null) {
                for (int i3 = 0; i3 < dayByIdx.getInPlanActionCnt(); i3++) {
                    IntAction inPlanAction = dayByIdx.getInPlanAction(i3);
                    if (inPlanAction != null && (IntAction.ActionType.RUN.equals(inPlanAction.getActionType()) || IntAction.ActionType.WORKOUT.equals(inPlanAction.getActionType()))) {
                        sb.append(" id:");
                        sb.append(inPlanAction.getActionId());
                        sb.append(" flag:");
                        sb.append(inPlanAction.getPunchFlag());
                        sb.append(" order:");
                        sb.append(dayByIdx.getDayOrder());
                        sb.append(" time:");
                        sb.append(inPlanAction.getPunchTime());
                    }
                }
                i = i + dayByIdx.getInPlanActionCnt() + dayByIdx.getOutPlanActionCnt();
            }
        }
        sb.append(" course num:");
        sb.append(i);
        return sb.toString();
    }

    public static boolean c() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("Suggestion_PlanUtil", "planApi == null.");
            return false;
        }
        IntPlan currentIntPlan = planApi.getCurrentIntPlan();
        if (currentIntPlan == null) {
            return false;
        }
        if (!currentIntPlan.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN) || o(currentIntPlan)) {
            return i();
        }
        return true;
    }

    private static boolean i() {
        WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
        if (weightApi == null) {
            LogUtil.b("Suggestion_PlanUtil", "setTodayFastLiteTip weightApi == null.");
            return false;
        }
        String fastingLiteSetting = weightApi.getFastingLiteSetting();
        if (TextUtils.isEmpty(fastingLiteSetting)) {
            return false;
        }
        return b(fastingLiteSetting);
    }

    private static boolean b(String str) {
        try {
            JsonObject asJsonObject = JsonParser.parseString(str).getAsJsonObject();
            if (asJsonObject.get("isAppEnabled") == null) {
                LogUtil.b("Suggestion_PlanUtil", "json object not contain isAppEnabled.");
                return false;
            }
            boolean asBoolean = asJsonObject.get("isAppEnabled").getAsBoolean();
            LogUtil.a("Suggestion_PlanUtil", "FastingLiteSetting:", asJsonObject.toString(), " isAppEnabled:", Boolean.valueOf(asBoolean));
            if (asBoolean) {
                return e(asJsonObject);
            }
            return false;
        } catch (Exception e) {
            LogUtil.b("Suggestion_PlanUtil", "can not parse.", LogAnonymous.b((Throwable) e));
            return false;
        }
    }

    private static boolean e(JsonObject jsonObject) {
        int asInt;
        JsonObject asJsonObject = jsonObject.getAsJsonObject("currentFastingLiteMode");
        return (asJsonObject == null || asJsonObject.get("id") == null || (asInt = asJsonObject.get("id").getAsInt()) == 0 || asInt == 1) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00e5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static defpackage.mob e(com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify r7) {
        /*
            mob r0 = new mob
            r0.<init>()
            if (r7 != 0) goto L8
            return r0
        L8:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            long r2 = r7.requestStartTime()
            r1.append(r2)
            java.lang.String r2 = "_"
            r1.append(r2)
            long r3 = r7.requestEndTime()
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            r0.c(r1)
            int r1 = r7.requestTotalDistance()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            r0.e(r1)
            long r3 = r7.requestTotalTime()
            r5 = 1000(0x3e8, double:4.94E-321)
            long r3 = r3 / r5
            r0.c(r3)
            long r3 = r7.requestEndTime()
            r0.e(r3)
            java.lang.String r1 = r7.requestRunCourseId()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L6b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            long r3 = r7.requestStartTime()
            r1.append(r3)
            r1.append(r2)
            long r2 = r7.requestEndTime()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.b(r1)
            goto L72
        L6b:
            java.lang.String r1 = r7.requestRunCourseId()
            r0.b(r1)
        L72:
            int r1 = r7.requestSportType()
            r0.c(r1)
            int r1 = r7.requestTotalCalories()
            float r1 = (float) r1
            r0.e(r1)
            int r1 = r7.requestTotalDistance()
            r0.a(r1)
            java.lang.String r1 = "trainPoint"
            r2 = 0
            int r1 = r7.getExtendDataInt(r1, r2)
            r0.d(r1)
            java.lang.String r1 = "completionRate"
            r3 = 0
            float r1 = r7.getExtendDataFloat(r1, r3)
            int r4 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r4 >= 0) goto L9f
            goto La0
        L9f:
            r3 = r1
        La0:
            r0.b(r3)
            java.lang.String r1 = "planInfo"
            java.lang.String r3 = ""
            java.lang.String r1 = r7.getExtendDataString(r1, r3)
            java.lang.String r4 = "plan_id"
            java.lang.String r4 = r7.getExtendDataString(r4, r3)
            boolean r5 = r3.equals(r1)
            if (r5 != 0) goto Ld2
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch: org.json.JSONException -> Lc6
            r5.<init>(r1)     // Catch: org.json.JSONException -> Lc6
            java.lang.String r1 = "planId"
            java.lang.String r1 = r5.optString(r1)     // Catch: org.json.JSONException -> Lc6
            goto Ld3
        Lc6:
            java.lang.String r1 = "toJson error"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            java.lang.String r5 = "Suggestion_PlanUtil"
            com.huawei.hwlogsmodel.LogUtil.b(r5, r1)
        Ld2:
            r1 = r3
        Ld3:
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto Lda
            goto Ldb
        Lda:
            r4 = r1
        Ldb:
            boolean r1 = android.text.TextUtils.isEmpty(r4)
            if (r1 == 0) goto Le5
            r0.e(r2)
            goto Le9
        Le5:
            r1 = 1
            r0.e(r1)
        Le9:
            float r1 = defpackage.grz.a()
            double r1 = (double) r1
            r0.e(r1)
            e(r7, r0, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ase.e(com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify):mob");
    }

    private static void e(MotionPathSimplify motionPathSimplify, mob mobVar, String str) {
        long e = ggl.e(motionPathSimplify.getExtendDataInt(HwExerciseConstants.JSON_NAME_PLAN_COURSE_TIME));
        if (!TextUtils.isEmpty(str) && e != 0) {
            mobVar.b(e);
        } else {
            mobVar.b(motionPathSimplify.requestStartTime());
        }
    }

    public static mob b(WorkoutRecord workoutRecord) {
        mob mobVar = new mob();
        if (workoutRecord == null) {
            return mobVar;
        }
        mobVar.c(workoutRecord.getDuration() / 1000);
        mobVar.b(workoutRecord.startTime());
        mobVar.e(workoutRecord.acquireExerciseTime());
        mobVar.b(workoutRecord.acquireWorkoutId());
        if (workoutRecord.isRunWorkout()) {
            mobVar.c(workoutRecord.acquireTrajectoryId());
        } else {
            mobVar.c(String.valueOf(workoutRecord.acquireExerciseTime()));
        }
        mobVar.c(10001);
        if (TextUtils.isEmpty(workoutRecord.acquirePlanId())) {
            mobVar.e(0);
        } else {
            mobVar.e(1);
        }
        mobVar.e(workoutRecord.acquireActualCalorie());
        mobVar.a(moe.j(workoutRecord.acquireActualDistance()));
        mobVar.d(workoutRecord.getTrainPoint());
        mobVar.b(workoutRecord.acquireFinishRate());
        mobVar.e(grz.a());
        HashMap hashMap = new HashMap();
        hashMap.put("name", workoutRecord.acquireWorkoutName());
        mobVar.e(moj.e(hashMap));
        return mobVar;
    }

    public static void e(IntPlan intPlan, int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            return;
        }
        if (intPlan == null) {
            ReleaseLogUtil.a("Suggestion_PlanUtil", "getRecordDataList plan is null");
            iBaseResponseCallback.d(-1, Collections.emptyList());
        } else if (!IntPlan.PlanType.AI_FITNESS_PLAN.equals(intPlan.getPlanType())) {
            iBaseResponseCallback.d(0, a(intPlan, i, i2));
        } else {
            Map<String, Long> e = e(intPlan, i, i2);
            c(e.get("START_TIME").longValue(), e.get("END_TIME").longValue(), iBaseResponseCallback);
        }
    }

    public static void c(final long j, final long j2, final IBaseResponseCallback iBaseResponseCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: asd
            @Override // java.lang.Runnable
            public final void run() {
                ase.d(j, j2, iBaseResponseCallback);
            }
        });
    }

    static /* synthetic */ void d(long j, long j2, IBaseResponseCallback iBaseResponseCallback) {
        ArrayList arrayList = new ArrayList();
        List<RecordData> c2 = c(j, j2);
        if (koq.c(c2)) {
            arrayList.addAll(c2);
            ReleaseLogUtil.b("Suggestion_PlanUtil", "getRecordListByTime workoutList.size", Integer.valueOf(c2.size()));
        }
        HiHealthNativeApi.a(arx.b()).fetchSequenceDataBySportType(a(j, j2), new a(arrayList, iBaseResponseCallback) { // from class: ase.3
        });
    }

    private static HiDataReadOption a(long j, long j2) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        ReleaseLogUtil.b("Suggestion_PlanUtil", "getHiDataReadOption weekOrder:", Long.valueOf(j), "dayOrder:", Long.valueOf(j2));
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setSortOrder(1);
        return hiDataReadOption;
    }

    private static Map<String, Long> e(IntPlan intPlan, int i, int i2) {
        long j;
        long j2;
        HashMap hashMap = new HashMap(2);
        long millis = TimeUnit.SECONDS.toMillis(intPlan.getPlanTimeInfo().getCreateTime());
        if (i2 != 0) {
            j = Math.max(d(intPlan, i, i2), millis);
            j2 = gib.d(j);
        } else {
            int g = g(intPlan);
            long max = Math.max(a(intPlan, g) * 1000, millis);
            long e = e(intPlan, g) * 1000;
            j = max;
            j2 = e;
        }
        ReleaseLogUtil.b("Suggestion_PlanUtil", "getRecordDataList startTime:", Long.valueOf(j), "endTime:", Long.valueOf(j2), "createTime:", Long.valueOf(millis));
        hashMap.put("START_TIME", Long.valueOf(j));
        hashMap.put("END_TIME", Long.valueOf(j2));
        return hashMap;
    }

    public static List<RecordData> c(long j, long j2) {
        CourseApi courseApi = (CourseApi) Services.c("CoursePlanService", CourseApi.class);
        List<WorkoutRecord> workoutRecords = courseApi.getWorkoutRecords(j, j2);
        if (koq.b(workoutRecords)) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList(workoutRecords.size());
        Iterator<WorkoutRecord> it = workoutRecords.iterator();
        while (it.hasNext()) {
            arrayList.add(courseApi.coverRecordData(b(it.next())));
        }
        return arrayList;
    }

    /* loaded from: classes3.dex */
    static class a implements HiDataReadResultListener {
        private final List<RecordData> b;
        private final IBaseResponseCallback d;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        public a(List<RecordData> list, IBaseResponseCallback iBaseResponseCallback) {
            this.d = iBaseResponseCallback;
            this.b = list;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            if (this.d == null || this.b == null) {
                ReleaseLogUtil.a("Suggestion_PlanUtil", "getRecordDataList mCallback == null");
                return;
            }
            List<HiHealthData> d = ase.d(obj, i);
            if (!koq.b(d)) {
                List d2 = ase.d(d);
                if (koq.c(d2)) {
                    ReleaseLogUtil.b("Suggestion_PlanUtil", "MyHiDataReadResultListener tempList.size", Integer.valueOf(d2.size()));
                    this.b.addAll(d2);
                }
                this.d.d(0, this.b);
                return;
            }
            LogUtil.a("Suggestion_PlanUtil", "MyHiDataReadResultListener isEmpty");
            this.d.d(0, this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<RecordData> d(List<HiHealthData> list) {
        if (koq.b(list)) {
            LogUtil.h("Suggestion_PlanUtil", "handleTrackData data ");
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            MotionPathSimplify e = kwz.e(it.next());
            if (e.requestSportDataSource() == 2) {
                ReleaseLogUtil.a("Suggestion_PlanUtil", "motionPathSimplify is user input.");
            } else if (e.requestAbnormalTrack() != 0) {
                ReleaseLogUtil.a("Suggestion_PlanUtil", "motionPathSimplify is abnormal.");
            } else if (e.requestDuplicated() != 0) {
                ReleaseLogUtil.a("Suggestion_PlanUtil", "motionPathSimplify is requestDuplicated.");
            } else if (TextUtils.isEmpty(e.requestRunCourseId())) {
                RecordData coverRecordData = ((CourseApi) Services.c("CoursePlanService", CourseApi.class)).coverRecordData(e(e));
                if (coverRecordData != null) {
                    arrayList.add(coverRecordData);
                }
            }
        }
        return arrayList;
    }

    public static List<HiHealthData> d(Object obj, int i) {
        if (!(obj instanceof SparseArray)) {
            LogUtil.h("Suggestion_PlanUtil", "data error.");
            return Collections.EMPTY_LIST;
        }
        SparseArray sparseArray = (SparseArray) obj;
        if (!koq.e(sparseArray.get(i), HiHealthData.class)) {
            LogUtil.h("Suggestion_PlanUtil", "getTrackData onResult obj not instanceof List");
            return Collections.EMPTY_LIST;
        }
        List<HiHealthData> list = (List) sparseArray.get(i);
        if (!list.isEmpty() && list.get(0) != null) {
            return list;
        }
        LogUtil.h("Suggestion_PlanUtil", "getTrackData size 0");
        return Collections.EMPTY_LIST;
    }

    private static List<RecordData> a(IntPlan intPlan, int i, int i2) {
        IntDayPlan dayByOrder;
        if (intPlan == null) {
            ReleaseLogUtil.a("Suggestion_PlanUtil", "getRecordDataList plan is null");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        IntWeekPlan weekInfoByOrder = intPlan.getWeekInfoByOrder(i);
        if (weekInfoByOrder == null || (dayByOrder = weekInfoByOrder.getDayByOrder(i2)) == null) {
            return arrayList;
        }
        for (int i3 = 0; i3 < dayByOrder.getRecordDataCnt(); i3++) {
            RecordData recordData = dayByOrder.getRecordData(i3);
            if (recordData != null) {
                arrayList.add(recordData);
            }
        }
        return arrayList;
    }

    public static int a(IntDayPlan intDayPlan) {
        if (intDayPlan == null) {
            return -1;
        }
        int dayStatus = intDayPlan.getDayStatus();
        return dayStatus <= 0 ? intDayPlan.getInPlanActionCnt() > 0 ? 0 : 1 : dayStatus;
    }

    public static Map<String, Integer> i(IntPlan intPlan) {
        int i;
        HashMap hashMap = new HashMap(2);
        int i2 = 0;
        if (intPlan != null) {
            i = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < intPlan.getMetaInfo().getWeekCount(); i4++) {
                Map<String, Integer> d = d(intPlan, intPlan.getWeekInfoByIdx(i4).getWeekOrder());
                Integer num = d.get("totalDuration");
                Integer num2 = d.get(BleConstants.TOTAL_CALORIES);
                i3 += num == null ? 0 : num.intValue();
                i += num2 == null ? 0 : num2.intValue();
            }
            i2 = i3;
        } else {
            i = 0;
        }
        hashMap.put("totalDuration", Integer.valueOf(i2));
        hashMap.put(BleConstants.TOTAL_CALORIES, Integer.valueOf(i));
        return hashMap;
    }

    public static Map<String, Integer> d(IntPlan intPlan, int i) {
        IntWeekPlan weekInfoByOrder = intPlan.getWeekInfoByOrder(i);
        HashMap hashMap = new HashMap(2);
        if (weekInfoByOrder == null) {
            hashMap.put("totalDuration", 0);
            hashMap.put(BleConstants.TOTAL_CALORIES, 0);
            return hashMap;
        }
        List<RecordData> c2 = c(intPlan, i, 0);
        float f = 0.0f;
        long j = 0;
        if (koq.c(c2)) {
            for (RecordData recordData : b(c2)) {
                LogUtil.a("Suggestion_PlanUtil", "recordData:", recordData.getRecordId(), " ", Integer.valueOf(recordData.getSportType()), " ", Float.valueOf(recordData.getActualCalorie()), " ", Long.valueOf(recordData.getDuration()));
                j += recordData.getDuration();
                f += recordData.getActualCalorie();
            }
        }
        float f2 = a(intPlan.getPlanId(), a(intPlan, i) * 1000, e(intPlan, i) * 1000).d;
        hashMap.put("totalDuration", Integer.valueOf(((int) (j + r0.e)) / 60));
        hashMap.put(BleConstants.TOTAL_CALORIES, Integer.valueOf((int) moe.b(f + f2)));
        return hashMap;
    }

    public static boolean b(IntPlan intPlan, int i, int i2) {
        ReleaseLogUtil.b("Suggestion_PlanUtil", "isPlanLastDay weekOrDer:", Integer.valueOf(i), "dayOrder:", Integer.valueOf(i2));
        if (intPlan == null) {
            LogUtil.h("Suggestion_PlanUtil", "isPlanLastDay planMetaInfo == null");
            return false;
        }
        PlanMetaInfo metaInfo = intPlan.getMetaInfo();
        if (metaInfo == null) {
            LogUtil.h("Suggestion_PlanUtil", "isPlanLastDay planMetaInfo == null");
            return false;
        }
        int weekCount = metaInfo.getWeekCount();
        ReleaseLogUtil.b("Suggestion_PlanUtil", "isPlanLastDay weekNumber:", Integer.valueOf(weekCount));
        if (weekCount != i) {
            LogUtil.a("Suggestion_PlanUtil", "isPlanLastDay weekNumber != weekOrDer");
            return false;
        }
        PlanTimeInfo planTimeInfo = intPlan.getPlanTimeInfo();
        if (planTimeInfo == null) {
            LogUtil.h("Suggestion_PlanUtil", "isPlanLastDay planTimeInfo == null");
            return false;
        }
        return d(i2, planTimeInfo.getEndDate());
    }

    private static boolean d(int i, long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j * 1000);
        int d = gib.d(calendar.get(7));
        ReleaseLogUtil.b("Suggestion_PlanUtil", "isPlanLastDay endPlanOfDay:", Integer.valueOf(d));
        return i == d;
    }

    public static boolean b(Plan plan, int i, int i2) {
        ReleaseLogUtil.b("Suggestion_PlanUtil", "isPlanLastDay weekOrDer:", Integer.valueOf(i), "dayOrder:", Integer.valueOf(i2));
        if (plan == null) {
            LogUtil.h("Suggestion_PlanUtil", "isPlanLastDay planMetaInfo == null");
            return false;
        }
        if (c(plan, plan.getEndTime() * 1000) != i) {
            LogUtil.a("Suggestion_PlanUtil", "isPlanLastDay weekNumber != weekOrDer");
            return false;
        }
        return d(i2, plan.getEndTime());
    }

    public static float a(IntPlan intPlan, int i, int i2, long j) {
        float f = 0.0f;
        for (RecordData recordData : b(c(intPlan, i, i2))) {
            LogUtil.a("Suggestion_PlanUtil", "recordData:", recordData.getRecordId(), " ", Integer.valueOf(recordData.getSportType()), " ", Float.valueOf(recordData.getActualCalorie()));
            f += recordData.getActualCalorie();
        }
        LogUtil.a("Suggestion_PlanUtil", "getDayPlanConsumeCalories weekOrder:", Integer.valueOf(i), " dayOrder:", Integer.valueOf(i2), " totalCalories:", Float.valueOf(f), " time:", Long.valueOf(j));
        return moe.b(f + a(intPlan.getPlanId(), j, j).d);
    }

    private static c a(String str, long j, long j2) {
        CourseApi courseApi = (CourseApi) Services.c("CoursePlanService", CourseApi.class);
        LogUtil.a("Suggestion_PlanUtil", "getTotalCaloriesInWeekPlan:", Long.valueOf(j), "  endTime:", Long.valueOf(j2));
        List<WorkoutRecord> workoutRecords = courseApi.getWorkoutRecords(str, gib.j(j), gib.j(j2));
        c cVar = new c();
        if (koq.c(workoutRecords)) {
            LogUtil.a("Suggestion_PlanUtil", "workoutRecords size = ", Integer.valueOf(workoutRecords.size()));
            for (WorkoutRecord workoutRecord : workoutRecords) {
                LogUtil.a("Suggestion_PlanUtil", "workoutRecord:", workoutRecord.acquireWorkoutId(), " ", Float.valueOf(workoutRecord.acquireActualCalorie()), " ", Integer.valueOf(workoutRecord.getDuration() / 1000));
                c.e(cVar, workoutRecord.acquireActualCalorie());
                c.e(cVar, workoutRecord.getDuration() / 1000);
            }
        } else {
            LogUtil.a("Suggestion_PlanUtil", "workoutRecords is empty.");
        }
        return cVar;
    }

    private static List<RecordData> b(List<RecordData> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            return arrayList;
        }
        for (RecordData recordData : list) {
            if (recordData != null && recordData.getSportType() > 0 && recordData.getIsInPlan() != 1) {
                arrayList.add(recordData);
            }
        }
        return arrayList;
    }

    /* loaded from: classes3.dex */
    static class c {
        private int d;
        private int e;

        private c() {
        }

        static /* synthetic */ int e(c cVar, float f) {
            int i = (int) (cVar.d + f);
            cVar.d = i;
            return i;
        }

        static /* synthetic */ int e(c cVar, int i) {
            int i2 = cVar.e + i;
            cVar.e = i2;
            return i2;
        }
    }

    public static List<RecordData> c(IntPlan intPlan, int i, int i2) {
        Map<String, Long> e = e(intPlan, i, i2);
        long longValue = e.get("START_TIME").longValue();
        long longValue2 = e.get("END_TIME").longValue();
        final ArrayList arrayList = new ArrayList();
        List<RecordData> c2 = c(longValue, longValue2);
        if (koq.c(c2)) {
            arrayList.addAll(c2);
        }
        HiDataReadOption a2 = a(longValue, longValue2);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        HiHealthNativeApi.a(arx.b()).fetchSequenceDataBySportType(a2, new HiDataReadResultListener() { // from class: ase.2
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i3, Object obj, int i4, int i5) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i3, int i4) {
                List<HiHealthData> d = ase.d(obj, i3);
                if (!koq.b(d)) {
                    List d2 = ase.d(d);
                    if (koq.c(d2)) {
                        arrayList.addAll(d2);
                    }
                    countDownLatch.countDown();
                    return;
                }
                countDownLatch.countDown();
            }
        });
        try {
            if (!countDownLatch.await(6000L, TimeUnit.MILLISECONDS)) {
                LogUtil.h("Suggestion_PlanUtil", "getWeekRecordList wait timeout");
            }
        } catch (InterruptedException unused) {
            LogUtil.b("Suggestion_PlanUtil", "interrupted while waiting for getWeekRecordList");
        }
        return arrayList;
    }

    public static boolean m(IntPlan intPlan) {
        String e = SharedPreferenceManager.e("MMKV_SUGGEST_MODULE_TAG", "DISTRIBUTE_COURSE_DEVICE", "");
        if (TextUtils.isEmpty(e)) {
            ReleaseLogUtil.b("Suggestion_PlanUtil", "isHasDistributeCourse distribute null");
            return false;
        }
        if (intPlan == null) {
            return false;
        }
        return e.equals(moj.e(r(intPlan)));
    }

    public static void q(IntPlan intPlan) {
        if (intPlan == null || !IntPlan.PlanType.AI_FITNESS_PLAN.equals(intPlan.getPlanType())) {
            LogUtil.a("Suggestion_PlanUtil", "setDistributeCourseSp null");
        } else {
            SharedPreferenceManager.c("MMKV_SUGGEST_MODULE_TAG", "DISTRIBUTE_COURSE_DEVICE", moj.e(r(intPlan)));
        }
    }

    private static WeekDataForDevice r(IntPlan intPlan) {
        if (intPlan == null || !IntPlan.PlanType.AI_FITNESS_PLAN.equals(intPlan.getPlanType())) {
            LogUtil.h("Suggestion_PlanUtil", "getCurrentWeekForDeviceCourseList null");
            return null;
        }
        int g = g(intPlan) - 1;
        IntWeekPlan weekInfoByIdx = intPlan.getWeekInfoByIdx(g);
        if (weekInfoByIdx == null) {
            LogUtil.h("Suggestion_PlanUtil", "getCurrentWeekForDeviceCourseList intWeekPlan null currentWeekCount", Integer.valueOf(g));
            return null;
        }
        ArrayList arrayList = new ArrayList(weekInfoByIdx.getDayCount());
        for (int i = 0; i < weekInfoByIdx.getDayCount(); i++) {
            IntDayPlan dayByIdx = weekInfoByIdx.getDayByIdx(i);
            if (dayByIdx != null) {
                DayDataForDevice dayDataForDevice = new DayDataForDevice();
                dayDataForDevice.setPunchFlag(dayByIdx.getPunchFlag());
                dayDataForDevice.setDayIndex(dayDataForDevice.getDayIndex());
                ArrayList arrayList2 = new ArrayList(dayByIdx.getInPlanActionCnt());
                for (int i2 = 0; i2 < dayByIdx.getInPlanActionCnt(); i2++) {
                    IntAction inPlanAction = dayByIdx.getInPlanAction(i2);
                    DayDataForDevice.IntCourseDayData intCourseDayData = new DayDataForDevice.IntCourseDayData();
                    intCourseDayData.setCourseId(inPlanAction.getActionId());
                    intCourseDayData.setPunchFlag(intCourseDayData.getPunchFlag());
                    arrayList2.add(intCourseDayData);
                }
                dayDataForDevice.setIntPlanDayDataList(arrayList2);
                arrayList.add(dayDataForDevice);
            }
        }
        WeekDataForDevice weekDataForDevice = new WeekDataForDevice();
        weekDataForDevice.setDayDataForDeviceList(arrayList);
        weekDataForDevice.setWeekIndex(weekInfoByIdx.getWeekOrder());
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "Suggestion_PlanUtil");
        if (deviceInfo != null) {
            weekDataForDevice.setDeviceUdId(deviceInfo.getDeviceUdid());
        }
        return weekDataForDevice;
    }

    private static PlanMetaInfo s(IntPlan intPlan) {
        if (intPlan == null) {
            ReleaseLogUtil.a("Suggestion_PlanUtil", "getPlanMetaInfo intPlan is null");
            return null;
        }
        return intPlan.getMetaInfo();
    }

    private static PlanTimeInfo p(IntPlan intPlan) {
        if (intPlan == null) {
            ReleaseLogUtil.a("Suggestion_PlanUtil", "getPlanTimeInfo intPlan is null");
            return null;
        }
        return intPlan.getPlanTimeInfo();
    }

    private static long b(long j) {
        return String.valueOf(j).matches("\\d{10}") ? j * 1000 : j;
    }

    public static int e(IntPlan intPlan) {
        PlanMetaInfo s = s(intPlan);
        if (s == null) {
            ReleaseLogUtil.a("Suggestion_PlanUtil", "getIntPlanDayCount planMetaInfo is null");
            return -1;
        }
        int dayCount = s.getDayCount();
        ReleaseLogUtil.b("Suggestion_PlanUtil", "getIntPlanDayCount dayCount ", Integer.valueOf(dayCount));
        return dayCount;
    }

    public static long c(IntPlan intPlan) {
        PlanTimeInfo p = p(intPlan);
        if (p == null) {
            ReleaseLogUtil.a("Suggestion_PlanUtil", "getIntPlanCreateTimeMillis planTimeInfo is null");
            return -1L;
        }
        long createTime = p.getCreateTime();
        ReleaseLogUtil.b("Suggestion_PlanUtil", "getIntPlanCreateTimeMillis createTime ", Long.valueOf(createTime));
        return b(createTime);
    }

    public static int b(IntPlan intPlan) {
        long c2 = c(intPlan);
        if (intPlan == null) {
            ReleaseLogUtil.a("Suggestion_PlanUtil", "getIntPlanCreateDate intPlan is null startTimeMillis ", Long.valueOf(c2));
            return DateFormatUtil.b(c2);
        }
        String timeZone = intPlan.getTimeZone();
        ReleaseLogUtil.b("Suggestion_PlanUtil", "getIntPlanCreateDate startTimeMillis ", Long.valueOf(c2), " timeZone ", timeZone);
        return DateFormatUtil.c(c2, jdl.d(timeZone));
    }

    public static long j(IntPlan intPlan) {
        PlanTimeInfo p = p(intPlan);
        if (p == null) {
            ReleaseLogUtil.a("Suggestion_PlanUtil", "getIntPlanStartTimeMillis planTimeInfo is null");
            return -1L;
        }
        long beginDate = p.getBeginDate();
        ReleaseLogUtil.b("Suggestion_PlanUtil", "getIntPlanStartTimeMillis beginDate ", Long.valueOf(beginDate));
        return b(beginDate);
    }

    public static int h(IntPlan intPlan) {
        long j = j(intPlan);
        if (intPlan == null) {
            ReleaseLogUtil.a("Suggestion_PlanUtil", "getIntPlanStartDate intPlan is null startTimeMillis ", Long.valueOf(j));
            return DateFormatUtil.b(j);
        }
        String timeZone = intPlan.getTimeZone();
        ReleaseLogUtil.b("Suggestion_PlanUtil", "getIntPlanStartDate startTimeMillis ", Long.valueOf(j), " timeZone ", timeZone);
        return DateFormatUtil.c(j, jdl.d(timeZone));
    }

    public static long a(IntPlan intPlan) {
        PlanTimeInfo p = p(intPlan);
        if (p == null) {
            ReleaseLogUtil.a("Suggestion_PlanUtil", "getIntPlanEndTimeMillis planTimeInfo is null");
            return -1L;
        }
        long endDate = p.getEndDate();
        ReleaseLogUtil.b("Suggestion_PlanUtil", "getIntPlanEndTimeMillis endDate ", Long.valueOf(endDate));
        return b(endDate);
    }

    public static int d(IntPlan intPlan) {
        long a2 = a(intPlan);
        if (intPlan == null) {
            ReleaseLogUtil.a("Suggestion_PlanUtil", "getIntPlanEndDate intPlan is null endTimeMillis ", Long.valueOf(a2));
            return DateFormatUtil.b(a2);
        }
        String timeZone = intPlan.getTimeZone();
        ReleaseLogUtil.b("Suggestion_PlanUtil", "getIntPlanEndDate endTimeMillis ", Long.valueOf(a2), " timeZone ", timeZone);
        return DateFormatUtil.c(a2, jdl.d(timeZone));
    }
}
