package defpackage;

import android.text.TextUtils;
import com.amap.api.maps.model.MyLocationStyle;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.Goal;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.IntWeekPlan;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.plan.model.intplan.DayPlanBean;
import com.huawei.health.plan.model.intplan.GoalBean;
import com.huawei.health.plan.model.intplan.IntActionBean;
import com.huawei.health.plan.model.intplan.IntPlanBean;
import com.huawei.health.plan.model.intplan.IntWeekPlanBean;
import com.huawei.health.plan.model.intplan.PlanInputInfoBean;
import com.huawei.health.plan.model.intplan.PlanMetaInfoBean;
import com.huawei.health.plan.model.intplan.StatInfoBean;
import com.huawei.health.plan.model.intplan.UserProfileBean;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.weight.WeightApi;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.main.stories.health.weight.callback.WeightCallback;
import com.huawei.up.model.UserInfomation;
import com.huawei.utils.RiskBiAnalytics;
import defpackage.ffl;
import health.compact.a.CommonUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class fyd {

    /* renamed from: a, reason: collision with root package name */
    private static final Double f12690a = Double.valueOf(0.012d);
    private IntPlanBean b;
    private IntPlan d;

    public void d(IntPlan intPlan) {
        this.d = intPlan;
    }

    public boolean d(final JSONObject jSONObject, int i, long j) {
        if (this.d == null) {
            return false;
        }
        final boolean[] zArr = new boolean[1];
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            return false;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        planApi.updateIntPlanReport(this.d.getPlanId(), this.d.getPlanType().getType(), i, jSONObject.toString(), j, new UiCallback<String>() { // from class: fyd.1
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str) {
                ReleaseLogUtil.a("Suggestion_WeekReportManager", "updateIntPlanReport fail ", str);
                zArr[0] = false;
                RiskBiAnalytics.c(RiskBiAnalytics.FitPLANRiskType.RISK_TYPE_FINISH_PLAN.value(), "updateIntPlanReport fail plan id is : ", fyd.this.d.getPlanId(), "report is empty: ", Boolean.valueOf(TextUtils.isEmpty(jSONObject.toString())), "errorCode: ", Integer.valueOf(i2), "errorInfo is: ", str);
                countDownLatch.countDown();
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                LogUtil.a("Suggestion_WeekReportManager", "updateIntPlanReport success");
                zArr[0] = true;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(6000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("Suggestion_WeekReportManager", "updateIntPlanReport timeout ");
        }
        return zArr[0];
    }

    private String d(int i) {
        final String[] strArr = {""};
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            return "";
        }
        LogUtil.a("Suggestion_WeekReportManager", "getCoachAdvice reportType: " + i + " mIntPlanBean:" + this.b);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        planApi.getCoachAdvice(i, "", this.b, false, new UiCallback<String>() { // from class: fyd.4
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str) {
                long j;
                long j2;
                ReleaseLogUtil.a("Suggestion_WeekReportManager", "getCoachAdvice fail ", str);
                if (fyd.this.b == null || fyd.this.b.getTimeInfo() == null) {
                    j = 0;
                    j2 = 0;
                } else {
                    j = fyd.this.b.getTimeInfo().getBeginDate();
                    j2 = fyd.this.b.getTimeInfo().getEndDate();
                }
                RiskBiAnalytics.c(RiskBiAnalytics.FitPLANRiskType.RISK_TYPE_COACH_ADVICE_EMPTY.value(), "getCoachAdvice fail beginDate: ", Long.valueOf(j), "endDate: ", Long.valueOf(j2), "errorCode is :", Integer.valueOf(i2), "errorInfo is :", str);
                countDownLatch.countDown();
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                if (!TextUtils.isEmpty(str)) {
                    strArr[0] = str;
                }
                countDownLatch.countDown();
            }
        });
        try {
            if (!countDownLatch.await(6000L, TimeUnit.MILLISECONDS)) {
                LogUtil.h("Suggestion_WeekReportManager", "getCoachAdvice wait timeout");
            }
        } catch (InterruptedException unused) {
            LogUtil.b("Suggestion_WeekReportManager", "interrupted while waiting for getCoachAdvice");
        }
        return strArr[0];
    }

    public boolean b() {
        IntPlan intPlan = this.d;
        return intPlan != null && intPlan.getPlanTimeInfo().getReportTime() < gib.c(System.currentTimeMillis(), 0) && fyw.w(this.d) && fyw.c(this.d);
    }

    public boolean e() {
        IntPlan intPlan = this.d;
        return intPlan != null && intPlan.getPlanTimeInfo().getReportTime() < gib.b(System.currentTimeMillis(), 0);
    }

    public JSONObject d(int i, boolean z) {
        String str;
        if (this.d == null) {
            return new JSONObject();
        }
        this.b = new IntPlanBean();
        long currentTimeMillis = System.currentTimeMillis();
        int h = ase.h(this.d);
        long c = DateFormatUtil.c(h);
        int d = ase.d(this.d);
        long c2 = DateFormatUtil.c(d);
        long d2 = d(z, currentTimeMillis, c, i);
        long c3 = c(z, currentTimeMillis, c2, i);
        int e = jdl.e(d2, c3);
        int e2 = jdl.e(c, currentTimeMillis);
        LogUtil.a("Suggestion_WeekReportManager", "getReport startDate ", Integer.valueOf(h), " planStartTime ", Long.valueOf(c), " endDate ", Integer.valueOf(d), " planEndTime ", Long.valueOf(c2), " startTime ", Long.valueOf(d2), " endTime ", Long.valueOf(c3), " dayCount ", Integer.valueOf(e));
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("startTime", gib.j(d2));
            jSONObject.put("endTime", gib.j(c3));
            try {
                IntPlan intPlan = this.d;
                jSONObject.put("overview", b(intPlan, e2, ase.e(intPlan) - e2, d2));
                jSONObject.put("train", e(i, this.d, z, c3));
                List<quh> a2 = a(d2, c3);
                if (i == 1) {
                    str = "Suggestion_WeekReportManager";
                    try {
                        jSONObject.put("summary", b(this.d, d2, c3, z));
                        jSONObject.put("heatAnalysis", a(this.d, a2, d2, c3));
                        jSONObject.put("dietSummary", e(this.d, a2, e));
                        jSONObject.put("nutrientAnalysis", c(this.d, a2, e));
                    } catch (JSONException unused) {
                        LogUtil.b(str, "getReport error");
                        return jSONObject;
                    }
                } else {
                    str = "Suggestion_WeekReportManager";
                }
                jSONObject.put("weightTrend", d(d2, c3));
                e(i, this.d, a2, d2, c3);
                jSONObject.put("content", d(i));
            } catch (JSONException unused2) {
                str = "Suggestion_WeekReportManager";
                LogUtil.b(str, "getReport error");
                return jSONObject;
            }
        } catch (JSONException unused3) {
        }
        return jSONObject;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001f, code lost:
    
        r3 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
    
        if (defpackage.fyw.i(r2.d) == 1) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000e, code lost:
    
        if (defpackage.fyw.i(r2.d) == 2) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private long d(boolean r3, long r4, long r6, int r8) {
        /*
            r2 = this;
            r0 = 2
            if (r3 == 0) goto L11
            r3 = -1
            long r3 = defpackage.gib.b(r4, r3)
            com.huawei.basefitnessadvice.model.intplan.IntPlan r5 = r2.d
            int r5 = defpackage.fyw.i(r5)
            if (r5 != r0) goto L20
            goto L1f
        L11:
            r3 = 0
            long r3 = defpackage.gib.b(r4, r3)
            com.huawei.basefitnessadvice.model.intplan.IntPlan r5 = r2.d
            int r5 = defpackage.fyw.i(r5)
            r1 = 1
            if (r5 != r1) goto L20
        L1f:
            r3 = r6
        L20:
            if (r8 != r0) goto L23
            goto L24
        L23:
            r6 = r3
        L24:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fyd.d(boolean, long, long, int):long");
    }

    private long c(boolean z, long j, long j2, int i) {
        long a2;
        if (z) {
            a2 = gib.c(j, -1);
        } else {
            a2 = gib.a(j);
        }
        return i == 2 ? j2 < j ? j2 : j : a2;
    }

    private JSONObject b(IntPlan intPlan, int i, int i2, long j) {
        LogUtil.a("Suggestion_WeekReportManager", "getOverview");
        JSONObject jSONObject = new JSONObject();
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        UserProfileBean userProfileBean = new UserProfileBean();
        float c = c(userProfileMgrApi, userProfileBean);
        float c2 = c(j);
        Goal goal = intPlan.getGoal("weight");
        if (goal != null) {
            float floatValue = ((Float) goal.getGoalDst()).floatValue();
            c(floatValue, c);
            try {
                jSONObject.put("origin", String.valueOf(c2));
                jSONObject.put("current", String.valueOf(c));
                jSONObject.put(ParsedFieldTag.GOAL, String.valueOf(floatValue));
                jSONObject.put("expected", String.valueOf(b(c2, c, i, i2)));
                ArrayList arrayList = new ArrayList();
                arrayList.add(new GoalBean("currentWeight", Float.valueOf(c)));
                arrayList.add(new GoalBean("weightLoss", Float.valueOf(c2 - floatValue)));
                arrayList.add(new GoalBean("weight", Float.valueOf(floatValue)));
                userProfileBean.setWeight(c2);
                PlanInputInfoBean planInputInfoBean = new PlanInputInfoBean();
                planInputInfoBean.setUserInfo(userProfileBean);
                this.b.setPlanInput(planInputInfoBean);
                this.b.setGoals(arrayList);
            } catch (JSONException unused) {
                LogUtil.b("Suggestion_WeekReportManager", "getOverview error");
            }
        }
        return jSONObject;
    }

    private float c(long j) {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.e()).getUserPreference("custom.start_weight_base");
        float j2 = userPreference != null ? CommonUtil.j(userPreference.getValue()) : 0.0f;
        return j2 < 1.0f ? grz.c(gib.b(j), gib.d(j)) : j2;
    }

    private float c(UserProfileMgrApi userProfileMgrApi, UserProfileBean userProfileBean) {
        UserInfomation userInfo;
        if (userProfileMgrApi == null || (userInfo = userProfileMgrApi.getUserInfo()) == null) {
            return 0.0f;
        }
        JSONObject jSONObject = new JSONObject();
        float a2 = grz.a();
        try {
            jSONObject.put("height", userInfo.getHeight());
            jSONObject.put(CommonConstant.KEY_GENDER, userInfo.getGender());
            jSONObject.put("age", userInfo.getAge());
            jSONObject.put("weight", userInfo.getWeight());
            userProfileBean.setAge(userInfo.getAge());
            userProfileBean.setHeight(userInfo.getHeight());
            userProfileBean.setGender(userInfo.getGender());
            userProfileBean.setWeight(a2);
            return a2;
        } catch (JSONException unused) {
            LogUtil.b("Suggestion_WeekReportManager", "get userInfo error");
            return a2;
        }
    }

    private static void c(float f, float f2) {
        int i = f2 <= f ? 1 : 0;
        HashMap hashMap = new HashMap(1);
        hashMap.put(ParsedFieldTag.GOAL, Integer.valueOf(i));
        ixx.d().d(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), AnalyticsValue.INT_PLAN_1120048.value(), hashMap, 0);
    }

    private float b(float f, float f2, int i, int i2) {
        float f3 = (((f2 - f) * i2) / i) + f2;
        double d = f;
        float doubleValue = (float) (d - (((r9 / 7.0f) * f12690a.doubleValue()) * d));
        return (doubleValue > f2 || f3 > f2) ? f2 : doubleValue < f3 ? doubleValue : f3;
    }

    private JSONObject e(int i, IntPlan intPlan, boolean z, long j) {
        LogUtil.a("Suggestion_WeekReportManager", "getTrain");
        JSONObject jSONObject = new JSONObject();
        int b = b(z, intPlan, i);
        try {
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 1; i2 < b; i2++) {
                e(intPlan, jSONArray, i2);
            }
            jSONObject.put("list", jSONArray);
            jSONObject.put("complete", intPlan.getStat("progress").getValue());
        } catch (JSONException unused) {
            LogUtil.b("Suggestion_WeekReportManager", "getTrain error");
        }
        if (((RecordApi) Services.a("CoursePlanService", RecordApi.class)) == null) {
            LogUtil.h("Suggestion_WeekReportManager", "recordApi is null.");
            return jSONObject;
        }
        Map<String, Integer> i3 = ase.i(intPlan);
        jSONObject.put(ParsedFieldTag.NPES_SPORT_TIME, i3.get("totalDuration"));
        jSONObject.put("consumes", i3.get(BleConstants.TOTAL_CALORIES));
        return jSONObject;
    }

    private void e(IntPlan intPlan, JSONArray jSONArray, int i) throws JSONException {
        int i2;
        IntWeekPlan weekInfoByOrder = intPlan.getWeekInfoByOrder(i);
        int i3 = 0;
        if (weekInfoByOrder != null) {
            int i4 = 0;
            i2 = 0;
            for (int i5 = 0; i5 < weekInfoByOrder.getDayCount(); i5++) {
                IntDayPlan dayByIdx = weekInfoByOrder.getDayByIdx(i5);
                if (dayByIdx != null && dayByIdx.getInPlanActionCnt() > 0) {
                    i2 += dayByIdx.getPunchFlag() != 1 ? 0 : 1;
                    i4++;
                }
            }
            i3 = i4;
        } else {
            i2 = 0;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("total", i3);
        jSONObject.put("complete", i2);
        jSONArray.put(jSONObject);
    }

    private int b(boolean z, IntPlan intPlan, int i) {
        int i2;
        if (z) {
            i2 = fyw.i(intPlan);
        } else {
            i2 = fyw.i(intPlan) + 1;
        }
        return i == 2 ? intPlan.getMetaInfo().getWeekCount() + 1 : i2;
    }

    private List<quh> a(long j, long j2) {
        int b = DateFormatUtil.b(j);
        int b2 = DateFormatUtil.b(j2);
        final ArrayList arrayList = new ArrayList();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ReleaseLogUtil.b("Suggestion_WeekReportManager", "getDietRecordList startTime ", Long.valueOf(j), " endTime ", Long.valueOf(j2), " startDate ", Integer.valueOf(b), " endDate ", Integer.valueOf(b2));
        grz.b(b, b2, new ResponseCallback() { // from class: fyf
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                fyd.c(arrayList, countDownLatch, i, (List) obj);
            }
        });
        try {
            if (!countDownLatch.await(6000L, TimeUnit.MILLISECONDS)) {
                ReleaseLogUtil.a("Suggestion_WeekReportManager", "getDietRecordList wait timeout");
            }
        } catch (InterruptedException e) {
            ReleaseLogUtil.c("Suggestion_WeekReportManager", "getDietRecordList exception ", ExceptionUtils.d(e));
        }
        return arrayList;
    }

    static /* synthetic */ void c(List list, CountDownLatch countDownLatch, int i, List list2) {
        LogUtil.a("Suggestion_WeekReportManager", "getDietRecordList errorCode ", Integer.valueOf(i), " list ", list2);
        if (koq.c(list2) && ((quh) list2.get(0)) != null) {
            list.addAll(list2);
        }
        countDownLatch.countDown();
    }

    private JSONObject b(IntPlan intPlan, long j, long j2, boolean z) {
        LogUtil.a("Suggestion_WeekReportManager", "getSummary");
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_WeekReportManager", "courseApi is null");
            return new JSONObject();
        }
        int[] iArr = {0};
        ArrayList arrayList = new ArrayList();
        while (j <= j2) {
            IntDayPlan c = c(intPlan, j);
            if (c != null && c.getInPlanActionCnt() > 0) {
                b(c, arrayList);
            }
            j = jdl.y(j);
        }
        LogUtil.a("Suggestion_WeekReportManager", "begin to query summary");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        e(courseApi, arrayList, countDownLatch, iArr);
        b(countDownLatch);
        LogUtil.a("Suggestion_WeekReportManager", "end to query summary");
        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
        JSONObject jSONObject = new JSONObject();
        if (recordApi == null) {
            LogUtil.h("Suggestion_WeekReportManager", "recordApi is null.");
            return jSONObject;
        }
        c(jSONObject, intPlan, iArr, z);
        return jSONObject;
    }

    private void b(IntDayPlan intDayPlan, List<ffl> list) {
        for (int i = 0; i < intDayPlan.getInPlanActionCnt(); i++) {
            IntAction inPlanAction = intDayPlan.getInPlanAction(i);
            if (inPlanAction != null && !TextUtils.isEmpty(inPlanAction.getActionId())) {
                list.add(new ffl.d(inPlanAction.getActionId()).b());
            }
        }
    }

    private void b(CountDownLatch countDownLatch) {
        try {
            if (countDownLatch.await(6000L, TimeUnit.MILLISECONDS)) {
                return;
            }
            LogUtil.h("Suggestion_WeekReportManager", "getSummary wait timeout");
        } catch (InterruptedException unused) {
            LogUtil.b("Suggestion_WeekReportManager", "interrupted while waiting for getCourseById");
        }
    }

    private void e(CourseApi courseApi, List<ffl> list, final CountDownLatch countDownLatch, final int[] iArr) {
        courseApi.getCourseByIds(list, false, new UiCallback<List<FitWorkout>>() { // from class: fyd.2
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.h("Suggestion_WeekReportManager", "getCourseByIds fail,", str);
                countDownLatch.countDown();
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<FitWorkout> list2) {
                if (koq.b(list2)) {
                    LogUtil.a("Suggestion_WeekReportManager", "data is empty");
                    countDownLatch.countDown();
                    return;
                }
                LogUtil.a("Suggestion_WeekReportManager", "data is ", list2);
                for (FitWorkout fitWorkout : list2) {
                    int[] iArr2 = iArr;
                    iArr2[0] = iArr2[0] + fitWorkout.acquireDuration();
                }
                LogUtil.a("Suggestion_WeekReportManager", "total duration is ", Integer.valueOf(iArr[0]));
                countDownLatch.countDown();
            }
        });
    }

    private void c(JSONObject jSONObject, IntPlan intPlan, int[] iArr, boolean z) {
        int g;
        if (z) {
            g = ase.g(intPlan) - 1;
        } else {
            g = ase.g(intPlan);
        }
        Map<String, Integer> d = ase.d(intPlan, g);
        try {
            jSONObject.put(ParsedFieldTag.NPES_SPORT_TIME, d.get("totalDuration"));
            jSONObject.put("consumes", d.get(BleConstants.TOTAL_CALORIES));
            jSONObject.put(ParsedFieldTag.GOAL, iArr[0] / 60);
        } catch (JSONException unused) {
            LogUtil.b("Suggestion_WeekReportManager", "getSummary error");
        }
    }

    private JSONObject a(IntPlan intPlan, List<quh> list, long j, long j2) {
        JSONObject jSONObject;
        String str;
        int i;
        int e;
        JSONObject jSONObject2;
        fyd fydVar = this;
        String str2 = "Suggestion_WeekReportManager";
        LogUtil.a("Suggestion_WeekReportManager", "getHeatAnalysis");
        JSONObject jSONObject3 = new JSONObject();
        JSONObject jSONObject4 = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        long j3 = j;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (j3 <= j2) {
            try {
                IntDayPlan c = fydVar.c(intPlan, j3);
                quh e2 = fydVar.e(list, j3);
                if (c == null || e2 == null) {
                    jSONObject = jSONObject3;
                    str = str2;
                } else {
                    qts d = e2.d();
                    i3 = (int) (i3 + d.f());
                    try {
                        e = dpe.e(d);
                        jSONObject2 = new JSONObject();
                        jSONObject = jSONObject3;
                        str = str2;
                    } catch (JSONException unused) {
                        str = str2;
                        i = 1;
                        Object[] objArr = new Object[i];
                        objArr[0] = "getHeatAnalysis error";
                        LogUtil.b(str, objArr);
                        return jSONObject3;
                    }
                    try {
                        jSONObject2.put("time", DateFormatUtil.c(e2.c()));
                        jSONObject2.put("inTake", d.f());
                        jSONObject2.put("consumes", e);
                        jSONObject2.put("total", Math.abs(e - d.f()));
                        jSONObject2.put("rest", c.getInPlanActionCnt() == 0 ? 1 : 0);
                        jSONArray.put(jSONObject2);
                        i2++;
                        i4 += e;
                    } catch (JSONException unused2) {
                        jSONObject3 = jSONObject;
                        i = 1;
                        Object[] objArr2 = new Object[i];
                        objArr2[0] = "getHeatAnalysis error";
                        LogUtil.b(str, objArr2);
                        return jSONObject3;
                    }
                }
                j3 = jdl.y(j3);
                fydVar = this;
                str2 = str;
                jSONObject3 = jSONObject;
            } catch (JSONException unused3) {
                jSONObject = jSONObject3;
                str = str2;
                jSONObject3 = jSONObject;
                i = 1;
                Object[] objArr22 = new Object[i];
                objArr22[0] = "getHeatAnalysis error";
                LogUtil.b(str, objArr22);
                return jSONObject3;
            }
        }
        jSONObject = jSONObject3;
        str = str2;
        i = 1;
        try {
            int max = Math.max(1, i2);
            jSONObject4.put("inTake", i3 / max);
            jSONObject4.put("consumes", i4 / max);
            jSONObject4.put("total", Math.abs(i3 - i4) / max);
            jSONObject3 = jSONObject;
        } catch (JSONException unused4) {
            jSONObject3 = jSONObject;
            Object[] objArr222 = new Object[i];
            objArr222[0] = "getHeatAnalysis error";
            LogUtil.b(str, objArr222);
            return jSONObject3;
        }
        try {
            jSONObject3.put("overview", jSONObject4);
            jSONObject3.put("list", jSONArray);
        } catch (JSONException unused5) {
            i = 1;
            Object[] objArr2222 = new Object[i];
            objArr2222[0] = "getHeatAnalysis error";
            LogUtil.b(str, objArr2222);
            return jSONObject3;
        }
        return jSONObject3;
    }

    private JSONObject e(IntPlan intPlan, List<quh> list, int i) {
        JSONObject jSONObject = new JSONObject();
        LogUtil.a("Suggestion_WeekReportManager", "getDietSummary dayCount : " + i);
        if (intPlan == null || i == 0) {
            LogUtil.b("Suggestion_WeekReportManager", "getDietSummary currentPlan null or dayCount is 0");
            return jSONObject;
        }
        e(list, intPlan, jSONObject, i);
        return jSONObject;
    }

    private void e(List<quh> list, IntPlan intPlan, JSONObject jSONObject, int i) {
        try {
            double d = 0.0d;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            double d2 = 0.0d;
            for (quh quhVar : list) {
                if (c(intPlan, DateFormatUtil.c(quhVar.c())) != null) {
                    d += r5.c();
                    d2 += r5.f();
                    quhVar.d().a();
                    for (qul qulVar : quhVar.a()) {
                        int h = qulVar.h();
                        if (h == 10 || h == 11) {
                            i2 = (int) (i2 + qulVar.b());
                        } else if (h == 20 || h == 21) {
                            i3 = (int) (i3 + qulVar.b());
                        } else if (h == 30 || h == 31) {
                            i4 = (int) (i4 + qulVar.b());
                        }
                    }
                }
            }
            double d3 = i;
            double d4 = d / d3;
            jSONObject.put(ParsedFieldTag.GOAL, d4);
            jSONObject.put("type", 1);
            b(i, i2, i3, i4, jSONObject);
            jSONObject.put("content", a(d4, d2 / d3));
        } catch (JSONException unused) {
            LogUtil.b("Suggestion_WeekReportManager", "getDietSummary error");
        }
    }

    private void b(int i, int i2, int i3, int i4, JSONObject jSONObject) throws JSONException {
        JSONObject d = d(10, i2 / i);
        JSONObject d2 = d(20, i3 / i);
        JSONObject d3 = d(30, i4 / i);
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(d);
        jSONArray.put(d2);
        jSONArray.put(d3);
        jSONObject.put("list", jSONArray);
    }

    private JSONObject d(int i, int i2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("whichMeal", i);
        jSONObject.put("kiloCalorie", i2);
        return jSONObject;
    }

    private String a(double d, double d2) {
        final String[] strArr = {""};
        IntPlanBean intPlanBean = this.b;
        if (intPlanBean != null && intPlanBean.getPlanInput() != null && this.b.getPlanInput().getUserInfo() != null) {
            double weight = this.b.getPlanInput().getUserInfo().getWeight();
            double height = this.b.getPlanInput().getUserInfo().getHeight() / 100.0d;
            if (weight > 0.0d && height > 0.0d && d > 0.0d && d2 > 0.0d) {
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                fje.c().getDietSummaryCloud(weight, height, (int) d, (int) d2, new UiCallback<JSONObject>() { // from class: fyd.3
                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    public void onFailure(int i, String str) {
                        LogUtil.h("Suggestion_WeekReportManager", "errorCode:", Integer.valueOf(i), MyLocationStyle.ERROR_INFO, str);
                        countDownLatch.countDown();
                    }

                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    /* renamed from: b, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(JSONObject jSONObject) {
                        if (jSONObject != null) {
                            String optString = jSONObject.optString("dietSummary");
                            if (!TextUtils.isEmpty(optString)) {
                                strArr[0] = optString;
                            }
                        }
                        countDownLatch.countDown();
                    }
                });
                try {
                    if (!countDownLatch.await(6000L, TimeUnit.MILLISECONDS)) {
                        LogUtil.h("Suggestion_WeekReportManager", "getDietSummaryCloud wait timeout");
                    }
                } catch (InterruptedException unused) {
                    LogUtil.b("Suggestion_WeekReportManager", "interrupted while waiting for getDietSummaryCloud");
                }
            } else {
                LogUtil.h("Suggestion_WeekReportManager", "getDietSummaryCloud :", Double.valueOf(weight), ",", Double.valueOf(height), ",", Double.valueOf(d), ",", Double.valueOf(d2));
            }
        }
        return strArr[0];
    }

    private JSONObject c(IntPlan intPlan, List<quh> list, int i) {
        JSONObject jSONObject;
        String str;
        JSONObject jSONObject2;
        LogUtil.a("Suggestion_WeekReportManager", "getNutrientAnalysis");
        JSONObject jSONObject3 = new JSONObject();
        int b = DateFormatUtil.b(0L);
        try {
            float f = 0.0f;
            float f2 = 0.0f;
            float f3 = 0.0f;
            float f4 = 0.0f;
            float f5 = 0.0f;
            int i2 = 0;
            int i3 = 0;
            float f6 = 0.0f;
            for (quh quhVar : list) {
                int i4 = b;
                try {
                    IntDayPlan c = c(intPlan, DateFormatUtil.c(quhVar.c()));
                    if (c == null) {
                        b = i4;
                    } else {
                        Iterator<qul> it = quhVar.a().iterator();
                        int i5 = i3;
                        int i6 = i2;
                        float f7 = f5;
                        float f8 = f4;
                        float f9 = f3;
                        float f10 = f2;
                        float f11 = f6;
                        float f12 = f;
                        int i7 = i4;
                        while (it.hasNext()) {
                            for (quk qukVar : it.next().c()) {
                                if (c.getInPlanActionCnt() == 0) {
                                    if (i7 != quhVar.c()) {
                                        i5++;
                                        i7 = quhVar.c();
                                    }
                                    f7 += qukVar.e();
                                    f9 += qukVar.f();
                                    f10 += qukVar.d();
                                } else {
                                    if (i7 != quhVar.c()) {
                                        i6++;
                                        i7 = quhVar.c();
                                    }
                                    f8 += qukVar.e();
                                    f11 += qukVar.f();
                                    f12 += qukVar.d();
                                }
                            }
                        }
                        b = i7;
                        f = f12;
                        f6 = f11;
                        f2 = f10;
                        f3 = f9;
                        f4 = f8;
                        f5 = f7;
                        i2 = i6;
                        i3 = i5;
                    }
                } catch (JSONException unused) {
                    str = "Suggestion_WeekReportManager";
                    jSONObject = jSONObject3;
                    LogUtil.b(str, "getNutrientAnalysis error");
                    return jSONObject;
                }
            }
            JSONObject jSONObject4 = new JSONObject();
            JSONObject jSONObject5 = new JSONObject();
            if (i2 != 0) {
                float f13 = i2;
                jSONObject2 = jSONObject3;
                str = "Suggestion_WeekReportManager";
                try {
                    jSONObject4.put("carbohydrate", f / f13);
                    jSONObject4.put("protein", f6 / f13);
                    jSONObject4.put("fat", f4 / f13);
                } catch (JSONException unused2) {
                    jSONObject = jSONObject2;
                    LogUtil.b(str, "getNutrientAnalysis error");
                    return jSONObject;
                }
            } else {
                jSONObject2 = jSONObject3;
                str = "Suggestion_WeekReportManager";
            }
            if (i3 != 0) {
                float f14 = i3;
                jSONObject5.put("carbohydrate", f2 / f14);
                jSONObject5.put("protein", f3 / f14);
                jSONObject5.put("fat", f5 / f14);
            }
            jSONObject = jSONObject2;
            try {
                jSONObject.put("trainingDay", jSONObject4);
                jSONObject.put("restDay", jSONObject5);
            } catch (JSONException unused3) {
                LogUtil.b(str, "getNutrientAnalysis error");
                return jSONObject;
            }
        } catch (JSONException unused4) {
        }
        return jSONObject;
    }

    private JSONArray d(long j, long j2) {
        LogUtil.a("Suggestion_WeekReportManager", "getWeightTrend");
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(gib.b(j), gib.d(j2));
        hiAggregateOption.setAggregateType(0);
        hiAggregateOption.setGroupUnitType(0);
        WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
        JSONArray jSONArray = new JSONArray();
        long[] jArr = {0};
        if (weightApi != null) {
            e(jArr, weightApi, hiAggregateOption, jSONArray);
        }
        return jSONArray;
    }

    private void e(final long[] jArr, WeightApi weightApi, HiAggregateOption hiAggregateOption, final JSONArray jSONArray) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        weightApi.getWeightData(hiAggregateOption, new WeightCallback() { // from class: fyd.5
            @Override // com.huawei.ui.main.stories.health.weight.callback.WeightCallback
            public void getWeight(ArrayList<qtm> arrayList) {
                fyd.this.d(arrayList, jSONArray, jArr);
                countDownLatch.countDown();
            }
        });
        try {
            if (countDownLatch.await(6000L, TimeUnit.MILLISECONDS)) {
                return;
            }
            LogUtil.h("Suggestion_WeekReportManager", "getWeightData wait timeout");
        } catch (InterruptedException unused) {
            LogUtil.b("Suggestion_WeekReportManager", "interrupted while waiting for getWeightTrend");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(ArrayList<qtm> arrayList, JSONArray jSONArray, long[] jArr) {
        IntPlanBean intPlanBean;
        if (arrayList != null && arrayList.size() > 0) {
            c(arrayList, jArr, jSONArray);
        }
        if (jSONArray.length() > 0) {
            try {
                if (jSONArray.getJSONObject(jSONArray.length() - 1) == null || (intPlanBean = this.b) == null || intPlanBean.getPlanInput() == null || this.b.getPlanInput().getUserInfo() == null) {
                    return;
                }
                this.b.getPlanInput().getUserInfo().setBfr(r3.getLong("fatRate"));
            } catch (JSONException unused) {
                LogUtil.b("Suggestion_WeekReportManager", "getWeight and bfr error");
            }
        }
    }

    private void c(ArrayList<qtm> arrayList, long[] jArr, JSONArray jSONArray) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            qtm qtmVar = arrayList.get(size);
            if (gib.b(jArr[0]) != gib.b(qtmVar.c())) {
                try {
                    jArr[0] = qtmVar.c();
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("time", qtmVar.c());
                    jSONObject.put("weight", qtmVar.e());
                    jSONObject.put("fatRate", qtmVar.b());
                    jSONArray.put(jSONObject);
                } catch (JSONException unused) {
                    LogUtil.b("Suggestion_WeekReportManager", "getWeightData error");
                }
            }
        }
    }

    private IntDayPlan c(IntPlan intPlan, long j) {
        if (intPlan == null) {
            return null;
        }
        int b = jdl.b(DateFormatUtil.c(ase.h(intPlan)), j);
        int n = jdl.n(j);
        IntWeekPlan weekInfoByOrder = intPlan.getWeekInfoByOrder(b);
        if (weekInfoByOrder != null) {
            return weekInfoByOrder.getDayByOrder(n);
        }
        return null;
    }

    private List<IntActionBean> c(IntPlan intPlan, final quh quhVar, long j) {
        final ArrayList arrayList = new ArrayList();
        if (Utils.o()) {
            c(arrayList, quhVar);
            return arrayList;
        }
        fit fitVar = new fit();
        String valueOf = String.valueOf(1000);
        qts d = quhVar.d();
        if (d != null) {
            valueOf = String.valueOf(Math.min(Math.max(1000, Math.round(d.c())), 10000));
        }
        fitVar.e(valueOf);
        String format = new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(j));
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        fje.c().getFoodRecommendDetail(intPlan.getPlanId(), format, fitVar, new UiCallback<fiy>() { // from class: fyd.9
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.h("Suggestion_WeekReportManager", "errorCode:", Integer.valueOf(i), MyLocationStyle.ERROR_INFO, str);
                countDownLatch.countDown();
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(fiy fiyVar) {
                fyd.this.b(fiyVar, (List<IntActionBean>) arrayList, quhVar);
                countDownLatch.countDown();
            }
        });
        try {
            if (!countDownLatch.await(6000L, TimeUnit.MILLISECONDS)) {
                LogUtil.h("Suggestion_WeekReportManager", "getFoodRecommendDetail wait timeout");
            }
        } catch (InterruptedException unused) {
            LogUtil.b("Suggestion_WeekReportManager", "interrupted while waiting for getScheduleAction");
        }
        return arrayList;
    }

    private void c(List<IntActionBean> list, quh quhVar) {
        List<qul> a2 = quhVar.a();
        if (koq.b(a2)) {
            LogUtil.h("Suggestion_WeekReportManager", "handleFoodAction() meals is empty");
            return;
        }
        for (qul qulVar : a2) {
            IntActionBean e = e(qulVar);
            int h = qulVar.h();
            if (h == 10 || h == 11) {
                e.setPunchTime(0L);
            } else if (h == 20 || h == 21) {
                e.setPunchTime(1L);
            } else if (h == 30 || h == 31) {
                e.setPunchTime(2L);
            } else {
                e.setPunchTime(0L);
                LogUtil.h("Suggestion_WeekReportManager", "handleFoodAction WhichMeal = ", Integer.valueOf(qulVar.h()));
            }
            list.add(e);
        }
    }

    private IntActionBean e(qul qulVar) {
        IntActionBean intActionBean = new IntActionBean();
        intActionBean.setActionId(Integer.toString(qulVar.h()));
        intActionBean.setActionType(2);
        intActionBean.setPunchFlag(1);
        return intActionBean;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(fiy fiyVar, List<IntActionBean> list, quh quhVar) {
        if (fiyVar != null) {
            if (fiyVar.e() != null && fiyVar.e().size() > 0) {
                for (fiu fiuVar : fiyVar.e()) {
                    IntActionBean intActionBean = new IntActionBean();
                    intActionBean.setActionId(fiuVar.a());
                    intActionBean.setActionType(2);
                    intActionBean.setPunchFlag(a(quhVar, fiuVar, 10));
                    intActionBean.setPunchTime(0L);
                    list.add(intActionBean);
                }
            }
            if (fiyVar.c() != null && fiyVar.c().size() > 0) {
                for (fiu fiuVar2 : fiyVar.c()) {
                    IntActionBean intActionBean2 = new IntActionBean();
                    intActionBean2.setActionId(fiuVar2.a());
                    intActionBean2.setActionType(2);
                    intActionBean2.setPunchFlag(a(quhVar, fiuVar2, 20));
                    intActionBean2.setPunchTime(1L);
                    list.add(intActionBean2);
                }
            }
            if (fiyVar.a() == null || fiyVar.a().size() <= 0) {
                return;
            }
            for (fiu fiuVar3 : fiyVar.a()) {
                IntActionBean intActionBean3 = new IntActionBean();
                intActionBean3.setActionId(fiuVar3.a());
                intActionBean3.setActionType(2);
                intActionBean3.setPunchFlag(a(quhVar, fiuVar3, 30));
                intActionBean3.setPunchTime(2L);
                list.add(intActionBean3);
            }
        }
    }

    private quh e(List<quh> list, long j) {
        int b = DateFormatUtil.b(j);
        for (quh quhVar : list) {
            if (quhVar != null && quhVar.c() == b) {
                return quhVar;
            }
        }
        return null;
    }

    private int a(quh quhVar, fiu fiuVar, int i) {
        if (quhVar == null) {
            return 0;
        }
        for (qul qulVar : quhVar.a()) {
            for (quk qukVar : qulVar.c()) {
                if (qulVar.h() == i && qukVar.a().equals(fiuVar.a())) {
                    return 1;
                }
            }
        }
        return 0;
    }

    private List<StatInfoBean> d(quh quhVar, long j) {
        ArrayList arrayList = new ArrayList();
        float c = grz.c(gib.b(j), gib.d(j));
        if (c > 0.0f) {
            arrayList.add(new StatInfoBean("currentWeight", Float.valueOf(c)));
        }
        qts d = quhVar.d();
        if (d != null) {
            arrayList.add(new StatInfoBean("inTakeCalorie", Float.valueOf(d.f())));
            arrayList.add(new StatInfoBean("sportConsumeCalorie", Float.valueOf(d.e())));
            arrayList.add(new StatInfoBean("restingCalorie", Float.valueOf(0.0f)));
        }
        return arrayList;
    }

    private List<DayPlanBean> d(IntPlan intPlan, List<quh> list, long j, long j2) {
        ArrayList arrayList = new ArrayList();
        while (j <= j2) {
            IntDayPlan c = c(intPlan, j);
            quh e = e(list, j);
            if (c != null && e != null) {
                DayPlanBean dayPlanBean = new DayPlanBean();
                dayPlanBean.setDayOrder(c.getDayOrder());
                dayPlanBean.setScheduledAction(c(intPlan, e, j));
                dayPlanBean.setStatistics(d(e, j));
                arrayList.add(dayPlanBean);
            }
            LogUtil.a("Suggestion_WeekReportManager", "getDayPlans time:", Long.valueOf(j));
            j = jdl.y(j);
        }
        return arrayList;
    }

    private void e(int i, IntPlan intPlan, List<quh> list, long j, long j2) {
        int i2;
        long j3 = j;
        LogUtil.a("Suggestion_WeekReportManager", "setWeekPlans startTime:", Long.valueOf(j), " endTime:", Long.valueOf(j2));
        ArrayList arrayList = new ArrayList();
        if (i == 2) {
            while (j3 <= j2) {
                long c = jdl.c(j3, 2, 0);
                long b = jdl.b(j3, 2, 0);
                List<DayPlanBean> d = d(intPlan, list, c, b);
                IntWeekPlanBean intWeekPlanBean = new IntWeekPlanBean();
                intWeekPlanBean.setWeekOrder(fyw.b(intPlan, j3));
                intWeekPlanBean.setDayPlans(d);
                arrayList.add(intWeekPlanBean);
                LogUtil.a("Suggestion_WeekReportManager", "setWeekPlans time:", Long.valueOf(j3), " finalStartTime:", Long.valueOf(c), " finalEndTime:", Long.valueOf(b));
                j3 = jdl.c(j3, 2, 1);
            }
            this.b.setWeekPlans(arrayList);
            PlanMetaInfoBean planMetaInfoBean = new PlanMetaInfoBean();
            planMetaInfoBean.setPlanId(intPlan.getPlanId());
            this.b.setMetaInfo(planMetaInfoBean);
            return;
        }
        long c2 = DateFormatUtil.c(ase.h(intPlan));
        int b2 = jdl.b(c2, j3);
        int i3 = b2 + 1;
        if (i3 > 1) {
            long b3 = i3 == 2 ? c2 : gib.b(j3, -1);
            long c3 = gib.c(b3, 0);
            LogUtil.a("Suggestion_WeekReportManager", "weekOrder:", Integer.valueOf(i3), " lastStartTime:", Long.valueOf(b3), " lastEndTime:", Long.valueOf(c3));
            i2 = i3;
            List<DayPlanBean> d2 = d(intPlan, a(b3, c3), b3, c3);
            IntWeekPlanBean intWeekPlanBean2 = new IntWeekPlanBean();
            intWeekPlanBean2.setWeekOrder(b2);
            intWeekPlanBean2.setDayPlans(d2);
            arrayList.add(intWeekPlanBean2);
        } else {
            i2 = i3;
        }
        List<DayPlanBean> d3 = d(intPlan, list, j, j2);
        IntWeekPlanBean intWeekPlanBean3 = new IntWeekPlanBean();
        intWeekPlanBean3.setWeekOrder(i2);
        intWeekPlanBean3.setDayPlans(d3);
        arrayList.add(intWeekPlanBean3);
        this.b.setWeekPlans(arrayList);
        PlanMetaInfoBean planMetaInfoBean2 = new PlanMetaInfoBean();
        planMetaInfoBean2.setPlanId(intPlan.getPlanId());
        this.b.setMetaInfo(planMetaInfoBean2);
    }
}
