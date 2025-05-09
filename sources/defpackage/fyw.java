package defpackage;

import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.IntWeekPlan;
import com.huawei.basefitnessadvice.model.intplan.PlanMetaInfo;
import com.huawei.basefitnessadvice.model.intplan.PlanTimeInfo;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* loaded from: classes.dex */
public class fyw {
    public static boolean l(IntPlan intPlan) {
        if (intPlan == null) {
            ReleaseLogUtil.d("Suggestion_IntPlanDataUtil", "isInPlanRange plan == null");
            return false;
        }
        int i = gib.i(intPlan.getPlanTimeInfo().getBeginDate() * 1000);
        int i2 = gib.i(intPlan.getPlanTimeInfo().getEndDate() * 1000);
        int i3 = gib.i(gib.b(System.currentTimeMillis()));
        return i3 >= i && i3 <= i2;
    }

    public static boolean k(IntPlan intPlan) {
        if (intPlan == null) {
            ReleaseLogUtil.d("Suggestion_IntPlanDataUtil", "isInPlanCreatingDay plan == null");
            return false;
        }
        int i = gib.i(intPlan.getPlanTimeInfo().getCreateTime() * 1000);
        int i2 = gib.i(gib.b(System.currentTimeMillis()));
        LogUtil.a("Suggestion_IntPlanDataUtil", "isInPlanCreatingDay createDay", Integer.valueOf(i), " currentDay", Integer.valueOf(i2));
        return i2 == i;
    }

    public static boolean m(IntPlan intPlan) {
        if (intPlan != null) {
            return gib.i(gib.b(System.currentTimeMillis())) == gib.i(intPlan.getPlanTimeInfo().getEndDate() * 1000);
        }
        ReleaseLogUtil.d("Suggestion_IntPlanDataUtil", "isInPlanEndDay plan == null");
        return false;
    }

    public static boolean x(IntPlan intPlan) {
        if (intPlan == null) {
            ReleaseLogUtil.d("Suggestion_IntPlanDataUtil", "isPlanOverdue plan == null");
            return false;
        }
        int i = gib.i(intPlan.getPlanTimeInfo().getEndDate() * 1000);
        int i2 = gib.i(gib.b(System.currentTimeMillis()));
        LogUtil.a("Suggestion_IntPlanDataUtil", "isPlanOverdue currentDay:", Integer.valueOf(i2), " endDay", Integer.valueOf(i));
        return i2 > i;
    }

    public static boolean q(IntPlan intPlan) {
        if (intPlan != null) {
            return intPlan.getStat("progress") != null && ((Float) intPlan.getStat("progress").getValue()).floatValue() >= 60.0f;
        }
        ReleaseLogUtil.d("Suggestion_IntPlanDataUtil", "isPlanFinishEnough plan == null");
        return false;
    }

    public static int h(IntPlan intPlan) {
        int g = ase.g(intPlan);
        int i = 0;
        for (int i2 = 0; i2 < intPlan.getMetaInfo().getWeekCount(); i2++) {
            IntWeekPlan weekInfoByIdx = intPlan.getWeekInfoByIdx(i2);
            if (weekInfoByIdx.getWeekOrder() < g && !fys.c(weekInfoByIdx, 0.2f)) {
                i++;
            }
        }
        LogUtil.a("Suggestion_IntPlanDataUtil", "getPlanAbsentWeekCount absentWeek:", Integer.valueOf(i));
        return i;
    }

    public static boolean r(IntPlan intPlan) {
        int g = ase.g(intPlan) - 1;
        LogUtil.a("Suggestion_IntPlanDataUtil", "isLastWeekExerciseBad lastWeek:", Integer.valueOf(g));
        for (int i = 0; i < intPlan.getMetaInfo().getWeekCount(); i++) {
            IntWeekPlan weekInfoByIdx = intPlan.getWeekInfoByIdx(i);
            if (weekInfoByIdx.getWeekOrder() == g && !fys.c(weekInfoByIdx, 0.2f)) {
                return true;
            }
        }
        return false;
    }

    public static boolean n(IntPlan intPlan) {
        int i = i(intPlan);
        int weekCount = intPlan.getMetaInfo().getWeekCount();
        int i2 = weekCount - 1;
        if (i < weekCount) {
            return false;
        }
        IntWeekPlan weekInfoByIdx = intPlan.getWeekInfoByIdx(i2);
        for (int i3 = 0; i3 < weekInfoByIdx.getDayCount(); i3++) {
            IntDayPlan dayByIdx = weekInfoByIdx.getDayByIdx(i3);
            if (dayByIdx != null) {
                for (int i4 = 0; i4 < dayByIdx.getInPlanActionCnt(); i4++) {
                    IntAction inPlanAction = dayByIdx.getInPlanAction(i4);
                    if (inPlanAction != null && inPlanAction.getActionId() != null && !inPlanAction.getActionId().equals("") && inPlanAction.getPunchFlag() == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean w(IntPlan intPlan) {
        int g = ase.g(intPlan);
        for (int i = 0; i < intPlan.getMetaInfo().getWeekCount(); i++) {
            IntWeekPlan weekInfoByIdx = intPlan.getWeekInfoByIdx(i);
            if (weekInfoByIdx.getWeekOrder() == g) {
                for (int i2 = 0; i2 < weekInfoByIdx.getDayCount(); i2++) {
                    IntDayPlan dayByIdx = weekInfoByIdx.getDayByIdx(i2);
                    if (dayByIdx != null) {
                        for (int i3 = 0; i3 < dayByIdx.getInPlanActionCnt(); i3++) {
                            IntAction inPlanAction = dayByIdx.getInPlanAction(i3);
                            if (inPlanAction != null && inPlanAction.getActionId() != null && !inPlanAction.getActionId().equals("") && inPlanAction.getPunchFlag() == 0) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean a(IntPlan intPlan, int i) {
        IntWeekPlan weekInfoByOrder = intPlan.getWeekInfoByOrder(i);
        boolean z = false;
        if (weekInfoByOrder == null) {
            return false;
        }
        int i2 = 0;
        while (true) {
            if (i2 >= weekInfoByOrder.getDayCount()) {
                break;
            }
            IntDayPlan dayByIdx = weekInfoByOrder.getDayByIdx(i2);
            if (dayByIdx != null && dayByIdx.getInPlanActionCnt() > 0) {
                z = true;
                break;
            }
            i2++;
        }
        return !z;
    }

    public static boolean s(IntPlan intPlan) {
        int g = ase.g(intPlan);
        int i = g - 1;
        int i2 = 0;
        for (int i3 = 0; i3 < intPlan.getMetaInfo().getWeekCount(); i3++) {
            IntWeekPlan weekInfoByIdx = intPlan.getWeekInfoByIdx(i3);
            if ((weekInfoByIdx.getWeekOrder() == g - 2 || weekInfoByIdx.getWeekOrder() == i) && !fys.c(weekInfoByIdx, 0.2f)) {
                i2++;
            }
        }
        return i2 == 2;
    }

    public static boolean e(IntDayPlan intDayPlan, boolean z) {
        if (intDayPlan == null) {
            return false;
        }
        if (z && intDayPlan.getPunchFlag() == 1) {
            return true;
        }
        for (int i = 0; i < intDayPlan.getInPlanActionCnt(); i++) {
            IntAction inPlanAction = intDayPlan.getInPlanAction(i);
            if (inPlanAction == null) {
                return false;
            }
            if ((inPlanAction.getActionType() == IntAction.ActionType.RUN || inPlanAction.getActionType() == IntAction.ActionType.WORKOUT) && inPlanAction.getPunchFlag() == 1) {
                return true;
            }
        }
        for (int i2 = 0; i2 < intDayPlan.getOutPlanActionCnt(); i2++) {
            IntAction outPlanAction = intDayPlan.getOutPlanAction(i2);
            if (outPlanAction != null) {
                if ((outPlanAction.getActionType() == IntAction.ActionType.RUN || outPlanAction.getActionType() == IntAction.ActionType.WORKOUT) && outPlanAction.getPunchFlag() == 1) {
                    return true;
                }
            } else {
                LogUtil.b("Suggestion_IntPlanDataUtil", "action is null.");
            }
        }
        return false;
    }

    private static IntDayPlan d(IntPlan intPlan, int i) {
        int e = e(intPlan);
        if (e <= 0 || e <= i) {
            return null;
        }
        int i2 = e - i;
        int i3 = 0;
        int i4 = 0;
        while (i3 < intPlan.getMetaInfo().getWeekCount()) {
            IntWeekPlan weekInfoByIdx = intPlan.getWeekInfoByIdx(i3);
            int dayCount = weekInfoByIdx.getDayCount() + i4;
            if (dayCount >= i2) {
                return weekInfoByIdx.getDayByIdx((i2 - i4) - 1);
            }
            i3++;
            i4 = dayCount;
        }
        return null;
    }

    public static boolean c(IntPlan intPlan, int i) {
        return b(intPlan, i) > 0;
    }

    private static int b(IntPlan intPlan, int i) {
        if (i < 1) {
            return -1;
        }
        if (i == 1) {
            return e(intPlan, 0);
        }
        return e(intPlan, b(intPlan, i - 1));
    }

    private static int e(IntPlan intPlan, int i) {
        IntDayPlan d;
        if (i == -1) {
            return -1;
        }
        int i2 = i + 1;
        while (true) {
            d = d(intPlan, i2);
            if (d == null) {
                return -1;
            }
            if (fys.e(d, IntAction.ActionType.WORKOUT) || fys.e(d, IntAction.ActionType.RUN)) {
                break;
            }
            i2++;
        }
        if (!e(d, true)) {
            return i2;
        }
        LogUtil.a("Suggestion_IntPlanDataUtil", "check finish exercise lastDay:", Integer.valueOf(d.getDayOrder()));
        return -1;
    }

    public static int i(IntPlan intPlan) {
        return ase.g(intPlan);
    }

    public static int b(IntPlan intPlan, long j) {
        return ase.e(intPlan, j);
    }

    public static int e(IntPlan intPlan) {
        return (int) (((gib.b(System.currentTimeMillis()) - (intPlan.getPlanTimeInfo().getBeginDate() * 1000)) / 86400000) + 1);
    }

    public static boolean d(IntPlan intPlan) {
        IntWeekPlan weekInfoByIdx;
        if (intPlan == null) {
            return false;
        }
        for (int i = 0; i < intPlan.getMetaInfo().getWeekCount() && (weekInfoByIdx = intPlan.getWeekInfoByIdx(i)) != null; i++) {
            for (int i2 = 0; i2 < weekInfoByIdx.getDayCount(); i2++) {
                if (e(weekInfoByIdx.getDayByIdx(i2), false)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean f(IntPlan intPlan) {
        if (p(intPlan)) {
            return d(intPlan);
        }
        return false;
    }

    public static boolean p(IntPlan intPlan) {
        if (intPlan == null) {
            LogUtil.h("Suggestion_IntPlanDataUtil", "isIntPlan plan == null");
            return false;
        }
        if (intPlan.getPlanType() == IntPlan.PlanType.FIT_PLAN && intPlan.getMetaInfo() != null && intPlan.getMetaInfo().getDisplayStyle() == 2) {
            return true;
        }
        return intPlan.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN || intPlan.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN;
    }

    public static boolean t(IntPlan intPlan) {
        if (intPlan == null) {
            return false;
        }
        if (intPlan.getWeekInfoByIdx(0) == null) {
            return true;
        }
        return (intPlan.getTransactionStatus() == 0 || intPlan.getTransactionStatus() == 1) ? false : true;
    }

    public static boolean c(IntPlan intPlan) {
        IntWeekPlan weekInfoByOrder = intPlan.getWeekInfoByOrder(i(intPlan));
        if (weekInfoByOrder != null) {
            int d = gib.d(Calendar.getInstance().get(7));
            for (int dayCount = weekInfoByOrder.getDayCount(); dayCount >= 0; dayCount--) {
                IntDayPlan dayByIdx = weekInfoByOrder.getDayByIdx(dayCount);
                if (dayByIdx != null && dayByIdx.getInPlanActionCnt() > 0) {
                    return dayByIdx.getDayOrder() <= d;
                }
            }
        }
        return false;
    }

    public static boolean b(String str) {
        return str != null && new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(System.currentTimeMillis())).compareToIgnoreCase(str) < 0;
    }

    public static boolean a() {
        String b = ash.b("sync_cloud_data_status");
        if (!TextUtils.isEmpty(b)) {
            long y = CommonUtil.y(b);
            if (System.currentTimeMillis() - y < 60000) {
                LogUtil.a("Suggestion_IntPlanDataUtil", "sync data time mill:", Long.valueOf(y));
                return true;
            }
        }
        return false;
    }

    public static String b(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Suggestion_IntPlanDataUtil", "subUTF8String input is null.");
            return "";
        }
        if (str.getBytes(StandardCharsets.UTF_8).length <= i) {
            return str;
        }
        int length = str.length();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < length) {
            int i5 = i2 + 1;
            i4 += str.substring(i2, i5).getBytes(StandardCharsets.UTF_8).length;
            if (i4 <= i - "...".getBytes(StandardCharsets.UTF_8).length) {
                i3++;
            }
            i2 = i5;
        }
        return str.substring(0, i3).concat("...");
    }

    public static boolean a(IntPlan intPlan, String str) {
        return String.valueOf(i(intPlan)).equals(ash.d(str, intPlan.getPlanId()));
    }

    public static boolean o(IntPlan intPlan) {
        IntDayPlan dayInfo = intPlan.getDayInfo(i(intPlan), gib.d(Calendar.getInstance().get(7)));
        return dayInfo != null && dayInfo.getPunchFlag() == 1;
    }

    public static boolean j(IntPlan intPlan) {
        for (IntAction intAction : fys.b(intPlan, i(intPlan), gib.d(Calendar.getInstance().get(7)))) {
            if (intAction != null && intAction.getPunchFlag() == 1) {
                return true;
            }
        }
        return false;
    }

    public static SparseArray<IntDayPlan> aIw_(IntPlan intPlan) {
        int c;
        if (intPlan == null) {
            return new SparseArray<>();
        }
        PlanTimeInfo planTimeInfo = intPlan.getPlanTimeInfo();
        if (planTimeInfo == null) {
            return new SparseArray<>();
        }
        PlanMetaInfo metaInfo = intPlan.getMetaInfo();
        if (metaInfo == null) {
            return new SparseArray<>();
        }
        SparseArray<IntDayPlan> sparseArray = new SparseArray<>();
        long beginDate = planTimeInfo.getBeginDate() * 1000;
        int c2 = HiDateUtil.c(beginDate);
        int c3 = HiDateUtil.c(planTimeInfo.getEndDate() * 1000);
        int weekCount = metaInfo.getWeekCount();
        for (int i = 0; i < weekCount; i++) {
            IntWeekPlan weekInfoByIdx = intPlan.getWeekInfoByIdx(i);
            if (weekInfoByIdx != null) {
                int weekOrder = weekInfoByIdx.getWeekOrder();
                int dayCount = weekInfoByIdx.getDayCount();
                for (int i2 = 0; i2 < dayCount; i2++) {
                    IntDayPlan dayByIdx = weekInfoByIdx.getDayByIdx(i2);
                    if (dayByIdx != null && c2 <= (c = HiDateUtil.c(gib.e(beginDate, weekOrder, dayByIdx.getDayOrder()))) && c <= c3) {
                        sparseArray.append(c, dayByIdx);
                    }
                }
            }
        }
        return sparseArray;
    }

    public static int aIx_(SparseArray<IntDayPlan> sparseArray, int i) {
        if (sparseArray == null) {
            return 0;
        }
        int size = sparseArray.size();
        int c = HiDateUtil.c(System.currentTimeMillis());
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            int keyAt = sparseArray.keyAt(i3);
            if (keyAt >= c && ase.a(sparseArray.get(keyAt)) == i) {
                i2++;
            }
        }
        return i2;
    }

    public static boolean a(long j) {
        int c;
        long[] p = HiDateUtil.p(System.currentTimeMillis());
        return p.length > 1 && HiDateUtil.c(p[0]) <= (c = HiDateUtil.c(j)) && c <= HiDateUtil.c(p[1]);
    }

    public static boolean u(IntPlan intPlan) {
        if (intPlan == null || !IntPlan.PlanType.AI_FITNESS_PLAN.equals(intPlan.getPlanType())) {
            return false;
        }
        if (!a(intPlan, ase.g(intPlan))) {
            return w(intPlan) && c(intPlan);
        }
        ReleaseLogUtil.d("Suggestion_IntPlanDataUtil", "this week all rest.");
        return false;
    }

    public static boolean v(IntPlan intPlan) {
        if (intPlan == null) {
            return false;
        }
        if (!IntPlan.PlanType.AI_FITNESS_PLAN.equals(intPlan.getPlanType()) && !IntPlan.PlanType.AI_RUN_PLAN.equals(intPlan.getPlanType())) {
            return false;
        }
        if (!a(intPlan, ase.g(intPlan) - 1) || !intPlan.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN)) {
            return gib.b(intPlan.getPlanTimeInfo().getBeginDate() * 1000, 0) < gib.b(System.currentTimeMillis(), 0);
        }
        ReleaseLogUtil.d("Suggestion_IntPlanDataUtil", "last week all rest.");
        return false;
    }

    public static boolean e() {
        return nsn.h(ash.b("showReportTime")) < gib.c(System.currentTimeMillis(), 0);
    }

    public static boolean b() {
        return nsn.h(ash.b("showReportTime")) < gib.b(System.currentTimeMillis(), 0);
    }

    public static boolean a(IntPlan intPlan) {
        return u(intPlan) && e();
    }

    public static boolean b(IntPlan intPlan) {
        return v(intPlan) && b();
    }

    public static boolean d() {
        return (Utils.o() || LoginInit.getInstance(BaseApplication.e()).isKidAccount()) ? false : true;
    }
}
