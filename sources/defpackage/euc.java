package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.gson.Gson;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.FitnessDayPlan;
import com.huawei.basefitnessadvice.model.FitnessPlanCourse;
import com.huawei.basefitnessadvice.model.FitnessWeekPlan;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.PlanRecord;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.plan.model.UserFitnessPlanInfo;
import com.huawei.health.plan.model.intplan.IntPlanBean;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.ResultCallback;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import com.huawei.up.model.UserInfomation;
import defpackage.fev;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class euc {
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<String> f12323a;
    private ArrayList<FitnessPackageInfo> b;
    private ConcurrentHashMap<String, UserFitnessPlanInfo> c;
    private ArrayList<PlanRecord> e;
    private BroadcastReceiver f;
    private Handler g;
    private IntentFilter i;
    private LocalBroadcastManager j;

    private euc() {
        this.e = new ArrayList<>(10);
        this.i = new IntentFilter();
        this.f = new BroadcastReceiver() { // from class: euc.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                LogUtil.a("Suggestion_FitnessPackagePlanManager", "receive account broadcast ");
                if (context == null || intent == null) {
                    ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "BroadcastReceiver context == null || intent == null");
                } else if ("com.huawei.plugin.account.logout".equals(intent.getAction())) {
                    ReleaseLogUtil.e("Suggestion_FitnessPackagePlanManager", "receive account remove broadcast");
                    euc.this.f();
                }
            }
        };
        this.g = new Handler(Looper.getMainLooper());
        this.b = new ArrayList<>(10);
        this.f12323a = new ArrayList<>(10);
        this.c = new ConcurrentHashMap<>(10);
        this.i.addAction("com.huawei.plugin.account.logout");
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(arx.b());
        this.j = localBroadcastManager;
        localBroadcastManager.registerReceiver(this.f, this.i);
    }

    public static final euc e() {
        return c.d;
    }

    static class c {
        private static final euc d = new euc();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.b.clear();
        synchronized (d) {
            this.f12323a.clear();
        }
        this.c.clear();
        this.e.clear();
        etx.b().e();
    }

    public UserFitnessPlanInfo d(String str) {
        if (str == null) {
            return null;
        }
        return this.c.get(str);
    }

    public PlanRecord b(String str) {
        if (str == null) {
            ReleaseLogUtil.c("Suggestion_FitnessPackagePlanManager", "getFitnessPlanRecord planId == null");
            return null;
        }
        UserFitnessPlanInfo userFitnessPlanInfo = this.c.get(str);
        if (userFitnessPlanInfo == null) {
            ReleaseLogUtil.c("Suggestion_FitnessPackagePlanManager", "getFitnessPlanRecord such planId = ", str, " has no planrecord");
            return null;
        }
        return etz.d(userFitnessPlanInfo);
    }

    public PlanRecord c(String str) {
        if (str == null) {
            ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "getDoingPlanRecord planId == null");
            return null;
        }
        UserFitnessPlanInfo i = i();
        if (i == null) {
            ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "getDoingPlanRecord such planId = ", str, " has no planrecord");
            return null;
        }
        return etz.d(i);
    }

    public void a(final UiCallback<List<PlanRecord>> uiCallback) {
        LogUtil.a("Suggestion_FitnessPackagePlanManager", "getAllPlanRecords enter");
        ThreadPoolManager.d().execute(new Runnable() { // from class: euc.5
            @Override // java.lang.Runnable
            public void run() {
                euc.this.e = euc.e().c();
                if (Utils.j()) {
                    uiCallback.onSuccess(euc.this.e);
                } else {
                    ReleaseLogUtil.e("Suggestion_FitnessPackagePlanManager", "getAllPlanRecords not china version");
                    uiCallback.onFailure(9999, "No china version");
                }
            }
        });
    }

    public ArrayList<PlanRecord> c() {
        LogUtil.a("Suggestion_FitnessPackagePlanManager", "getPlanRecords enter");
        ArrayList<PlanRecord> arrayList = new ArrayList<>(10);
        if (Utils.j()) {
            Iterator<Map.Entry<String, UserFitnessPlanInfo>> it = this.c.entrySet().iterator();
            while (it.hasNext()) {
                PlanRecord d2 = etz.d(it.next().getValue());
                if (d2 != null) {
                    arrayList.add(d2);
                }
            }
        }
        this.e = arrayList;
        LogUtil.a("Suggestion_FitnessPackagePlanManager", "getPlanRecords leave");
        return arrayList;
    }

    private float a(WorkoutRecord workoutRecord) {
        if (workoutRecord == null) {
            return Float.MIN_VALUE;
        }
        float a2 = (float) a(workoutRecord.acquireWorkoutId());
        if (((int) a2) == 0) {
            return Float.MIN_VALUE;
        }
        return workoutRecord.acquireCalorie() / a2;
    }

    public float b(WorkoutRecord workoutRecord, UserInfomation userInfomation) {
        float f;
        float f2 = 0.0f;
        if (userInfomation != null) {
            f = a(workoutRecord);
            if (f < 0.0f) {
                f = userInfomation.getWeightOrDefaultValue();
            }
            LogUtil.c("Suggestion_FitnessPackagePlanManager", "getDayShouldCompleteCalorie userWeight = ", Float.valueOf(f));
        } else {
            f = 0.0f;
        }
        if (workoutRecord == null) {
            return 0.0f;
        }
        List<FitnessPlanCourse> c2 = c(workoutRecord);
        if (c2 != null) {
            for (FitnessPlanCourse fitnessPlanCourse : c2) {
                if (fitnessPlanCourse != null) {
                    f2 = (float) (f2 + (a(fitnessPlanCourse.acquireCourseId()) * f));
                }
            }
        }
        return f2;
    }

    public float e(WorkoutRecord workoutRecord, UserInfomation userInfomation) {
        float f;
        FitnessDayPlan fitnessDayPlan;
        if (userInfomation != null) {
            f = userInfomation.getWeightOrDefaultValue();
            LogUtil.c("Suggestion_FitnessPackagePlanManager", "getWeekShouldCompleteCalorie userWeight = ", Float.valueOf(f));
        } else {
            f = 0.0f;
        }
        if (workoutRecord == null) {
            return 0.0f;
        }
        UserFitnessPlanInfo userFitnessPlanInfo = this.c.get(workoutRecord.acquirePlanId());
        if (userFitnessPlanInfo == null) {
            ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "getWeekShouldCompleteCalorie planId is not exists: ", workoutRecord.acquirePlanId());
            return 0.0f;
        }
        long b = gib.b(ghz.a(workoutRecord.acquireWorkoutDate(), "yyyy-MM-dd") * 1000);
        List<FitnessWeekPlan> acquireWeekPlanList = userFitnessPlanInfo.acquireWeekPlanList();
        if (acquireWeekPlanList != null) {
            for (FitnessWeekPlan fitnessWeekPlan : acquireWeekPlanList) {
                if (fitnessWeekPlan != null && (fitnessDayPlan = fitnessWeekPlan.acquireWeekList().get(0)) != null && b - gib.b(fitnessDayPlan.acquireDate()) <= 604800000) {
                    return e(f, fitnessWeekPlan);
                }
            }
        }
        return 0.0f;
    }

    private float e(float f, FitnessWeekPlan fitnessWeekPlan) {
        List<FitnessPlanCourse> acquireDayPlanCourses;
        float f2 = 0.0f;
        if (fitnessWeekPlan == null || fitnessWeekPlan.acquireWeekList() == null) {
            ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "getCalorieInOneDay, weekPlan == null || weekPlan.acquireWeekList() == null");
            return 0.0f;
        }
        for (FitnessDayPlan fitnessDayPlan : fitnessWeekPlan.acquireWeekList()) {
            if (fitnessDayPlan != null && fitnessDayPlan.acquireDayPlanCourses() != null && (acquireDayPlanCourses = fitnessDayPlan.acquireDayPlanCourses()) != null) {
                Iterator<FitnessPlanCourse> it = acquireDayPlanCourses.iterator();
                while (it.hasNext()) {
                    FitnessPlanCourse next = it.next();
                    f2 = (float) (f2 + (next == null ? 0.0d : a(next.acquireCourseId()) * f));
                }
            }
        }
        return f2;
    }

    private List<FitnessPlanCourse> c(WorkoutRecord workoutRecord) {
        if (workoutRecord == null) {
            ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "getDayCourses, record == null");
            return null;
        }
        UserFitnessPlanInfo userFitnessPlanInfo = this.c.get(workoutRecord.acquirePlanId());
        if (userFitnessPlanInfo == null) {
            ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "getDayCourses planId is not exists: ", workoutRecord.acquirePlanId());
            return null;
        }
        List<FitnessWeekPlan> acquireWeekPlanList = userFitnessPlanInfo.acquireWeekPlanList();
        if (acquireWeekPlanList == null) {
            ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "getDayCourses, weekPlanList == null");
            return null;
        }
        long b = gib.b(ghz.a(workoutRecord.acquireWorkoutDate(), "yyyy-MM-dd") * 1000);
        for (FitnessWeekPlan fitnessWeekPlan : acquireWeekPlanList) {
            if (fitnessWeekPlan != null && fitnessWeekPlan.acquireWeekList() != null) {
                for (FitnessDayPlan fitnessDayPlan : fitnessWeekPlan.acquireWeekList()) {
                    if (fitnessDayPlan != null && b == gib.b(fitnessDayPlan.acquireDate())) {
                        LogUtil.c("Suggestion_FitnessPackagePlanManager", "find time is ", Long.valueOf(b));
                        return fitnessDayPlan.acquireDayPlanCourses();
                    }
                }
            }
        }
        return null;
    }

    public float d(WorkoutRecord workoutRecord) {
        LogUtil.a("Suggestion_FitnessPackagePlanManager", "updateLocalFitnessPkgInfo");
        if (workoutRecord == null) {
            ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "updateLocalFitnessPkgInfo invalid parameters, workoutRecord == null");
            return 0.0f;
        }
        UserFitnessPlanInfo i = i();
        if (i == null) {
            ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "updateLocalFitnessPkgInfo info == null");
            return 0.0f;
        }
        e(workoutRecord, i);
        LogUtil.a("Suggestion_FitnessPackagePlanManager", "burnedCalorie = ", Float.valueOf(etz.a(etz.e(i))));
        LogUtil.a("Suggestion_FitnessPackagePlanManager", "totalCalorie = ", i.acquireTotalCalorie());
        epo c2 = eve.c(i);
        String valueOf = String.valueOf(eve.e(c2));
        LogUtil.a("Suggestion_FitnessPackagePlanManager", "rate = ", valueOf);
        i.saveCompleteRate(valueOf);
        i.saveFinishTime(System.currentTimeMillis());
        this.c.put(i.acquirePlanId(), i);
        b(i, new UiCallback<Object>() { // from class: euc.10
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str) {
                ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "updateFitnessPlan onFailure errorCode = ", Integer.valueOf(i2));
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onSuccess(Object obj) {
                LogUtil.a("Suggestion_FitnessPackagePlanManager", "onSuccess data", obj);
            }
        });
        return eve.e(c2);
    }

    public float e(UserFitnessPlanInfo userFitnessPlanInfo) {
        if (userFitnessPlanInfo == null) {
            LogUtil.h("Suggestion_FitnessPackagePlanManager", "getPlanTotalCalorie info is null");
            return 0.0f;
        }
        return e(userFitnessPlanInfo.acquireWeekPlanList()) / 1000.0f;
    }

    public float c(FitnessPackageInfo fitnessPackageInfo) {
        if (fitnessPackageInfo == null) {
            LogUtil.h("Suggestion_FitnessPackagePlanManager", "getPackageTotalCalorie info is null");
            return 0.0f;
        }
        return e(fitnessPackageInfo.acquireFitnessWeekPlanList());
    }

    private float e(List<FitnessWeekPlan> list) {
        float f = 0.0f;
        if (koq.b(list)) {
            LogUtil.h("Suggestion_FitnessPackagePlanManager", "getTotalCalorie weekPlanList is empty");
            return 0.0f;
        }
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        if (userProfileMgrApi == null) {
            LogUtil.h("Suggestion_FitnessPackagePlanManager", "getTotalCalorie : userProfileMgrApi is null.");
            return 0.0f;
        }
        if (!l() && !userProfileMgrApi.waitInit()) {
            LogUtil.b("Suggestion_FitnessPackagePlanManager", "getTotalCalorie waitInit fail");
            return 0.0f;
        }
        UserInfomation userInfo = userProfileMgrApi.getUserInfo();
        if (userInfo == null) {
            LogUtil.h("Suggestion_FitnessPackagePlanManager", "getTotalCalorie userInfo is null");
            return 0.0f;
        }
        for (FitnessWeekPlan fitnessWeekPlan : list) {
            if (fitnessWeekPlan != null) {
                List<FitnessDayPlan> acquireWeekList = fitnessWeekPlan.acquireWeekList();
                if (koq.b(acquireWeekList)) {
                    LogUtil.h("Suggestion_FitnessPackagePlanManager", "dayPlanList is empty");
                } else {
                    Iterator<FitnessDayPlan> it = acquireWeekList.iterator();
                    while (it.hasNext()) {
                        f += b(userInfo, it.next());
                    }
                }
            }
        }
        return f;
    }

    private float b(UserInfomation userInfomation, FitnessDayPlan fitnessDayPlan) {
        float f = 0.0f;
        if (userInfomation == null || fitnessDayPlan == null) {
            ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "getDayPlanCalorie, userInfo == null || dayPlan == null");
            return 0.0f;
        }
        List<FitnessPlanCourse> acquireDayPlanCourses = fitnessDayPlan.acquireDayPlanCourses();
        if (acquireDayPlanCourses != null) {
            Iterator<FitnessPlanCourse> it = acquireDayPlanCourses.iterator();
            while (it.hasNext()) {
                if (it.next() != null) {
                    f += r1.acquireCalorie() * userInfomation.getWeightOrDefaultValue();
                }
            }
        }
        return f;
    }

    public double a(String str) {
        return euh.a().c(str);
    }

    private void e(WorkoutRecord workoutRecord, UserFitnessPlanInfo userFitnessPlanInfo) {
        long j;
        Date parse;
        if (workoutRecord == null || userFitnessPlanInfo == null) {
            ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "updatePlanInfo record == null || info == null");
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String valueOf = String.valueOf(workoutRecord.getPlanTrainDate());
        LogUtil.a("Suggestion_FitnessPackagePlanManager", "updatePlanInfo getPlanTrainDate", Integer.valueOf(workoutRecord.getPlanTrainDate()));
        if (TextUtils.isEmpty(valueOf)) {
            LogUtil.b("Suggestion_FitnessPackagePlanManager", "updatePlanInfo dayWorkoutPlanDate isEmpty");
            return;
        }
        try {
            parse = simpleDateFormat.parse(valueOf);
        } catch (ParseException e) {
            LogUtil.b("Suggestion_FitnessPackagePlanManager", "ParseException = ", LogAnonymous.b((Throwable) e));
        }
        if (parse != null) {
            j = parse.getTime();
            a(workoutRecord, userFitnessPlanInfo, j);
        }
        j = 0;
        a(workoutRecord, userFitnessPlanInfo, j);
    }

    private void a(WorkoutRecord workoutRecord, UserFitnessPlanInfo userFitnessPlanInfo, long j) {
        if (workoutRecord == null || userFitnessPlanInfo == null) {
            ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "updatePlanInfo record == null || info == null");
            return;
        }
        List<FitnessWeekPlan> acquireWeekPlanList = userFitnessPlanInfo.acquireWeekPlanList();
        if (acquireWeekPlanList == null) {
            return;
        }
        for (FitnessWeekPlan fitnessWeekPlan : acquireWeekPlanList) {
            if (fitnessWeekPlan != null) {
                for (FitnessDayPlan fitnessDayPlan : fitnessWeekPlan.acquireWeekList()) {
                    if (fitnessDayPlan != null && a(workoutRecord, j, fitnessDayPlan)) {
                        return;
                    }
                }
            }
        }
    }

    private boolean a(WorkoutRecord workoutRecord, long j, FitnessDayPlan fitnessDayPlan) {
        long a2 = gib.a(j);
        long a3 = gib.a(fitnessDayPlan.acquireDate());
        if (a2 != gib.a(workoutRecord.acquireExerciseTime()) && (e(workoutRecord) || b(workoutRecord))) {
            LogUtil.a("Suggestion_FitnessPackagePlanManager", "updatePlanInfo invalid workoutRecord");
            return true;
        }
        if (a2 != a3) {
            return false;
        }
        LogUtil.a("Suggestion_FitnessPackagePlanManager", "current == target, and value = ", Long.valueOf(a2));
        b(workoutRecord, fitnessDayPlan);
        return false;
    }

    private void b(WorkoutRecord workoutRecord, FitnessDayPlan fitnessDayPlan) {
        if (workoutRecord == null || fitnessDayPlan == null) {
            ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "updatePlanCourseInfo record == null || dayPlan == null");
            return;
        }
        List<FitnessPlanCourse> acquireDayPlanCourses = fitnessDayPlan.acquireDayPlanCourses();
        for (int i = 0; i < acquireDayPlanCourses.size(); i++) {
            FitnessPlanCourse fitnessPlanCourse = acquireDayPlanCourses.get(i);
            if (fitnessPlanCourse != null) {
                LogUtil.a("Suggestion_FitnessPackagePlanManager", "updatePlanCourseInfo workoutOrder:", Integer.valueOf(workoutRecord.acquireWorkoutOrder()), "courseId:", fitnessPlanCourse.acquireCourseId(), workoutRecord.acquireWorkoutId());
                if (fitnessPlanCourse.acquireCourseId().equals(workoutRecord.acquireWorkoutId()) && workoutRecord.acquireWorkoutOrder() == i + 1) {
                    c(workoutRecord, fitnessPlanCourse);
                }
            }
        }
    }

    private void c(WorkoutRecord workoutRecord, FitnessPlanCourse fitnessPlanCourse) {
        if (workoutRecord == null || fitnessPlanCourse == null) {
            ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "updateCourseInfo record == null || course == null");
            return;
        }
        if (fitnessPlanCourse.acquireFinishStatus()) {
            if (fitnessPlanCourse.acquireBurnedCalorie() < workoutRecord.acquireActualCalorie()) {
                fitnessPlanCourse.saveBurnedCalorie(workoutRecord.acquireActualCalorie());
            }
            if (fitnessPlanCourse.acquireWorkoutTime() < workoutRecord.getDuration()) {
                fitnessPlanCourse.saveWorkoutTime(workoutRecord.getDuration());
                return;
            }
            return;
        }
        LogUtil.a("Suggestion_FitnessPackagePlanManager", "update corresponding course = ", fitnessPlanCourse.acquireName());
        if (workoutRecord.acquireFinishRate() >= 60.0f) {
            fitnessPlanCourse.saveFinishStatus(true);
        }
        fitnessPlanCourse.saveBurnedCalorie(workoutRecord.acquireActualCalorie());
        fitnessPlanCourse.saveWorkoutTime(workoutRecord.getDuration());
    }

    public int d(long j) {
        UserFitnessPlanInfo i = i();
        if (i == null) {
            ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "getTodayTaskStatus info == null");
            return 0;
        }
        List<FitnessWeekPlan> acquireWeekPlanList = i.acquireWeekPlanList();
        if (acquireWeekPlanList == null) {
            return 0;
        }
        int i2 = 0;
        int i3 = 0;
        for (FitnessWeekPlan fitnessWeekPlan : acquireWeekPlanList) {
            if (fitnessWeekPlan != null) {
                for (FitnessDayPlan fitnessDayPlan : fitnessWeekPlan.acquireWeekList()) {
                    if (fitnessDayPlan != null && gib.b(fitnessDayPlan.acquireDate()) == gib.b(j)) {
                        LogUtil.c("Suggestion_FitnessPackagePlanManager", "find today exercise course");
                        List<FitnessPlanCourse> acquireDayPlanCourses = fitnessDayPlan.acquireDayPlanCourses();
                        int size = acquireDayPlanCourses == null ? 0 : acquireDayPlanCourses.size();
                        i2 = d(i2, acquireDayPlanCourses);
                        i3 = size;
                    }
                }
            }
        }
        if (i2 == 0) {
            return 0;
        }
        return i2 == i3 ? 2 : 1;
    }

    private int d(int i, List<FitnessPlanCourse> list) {
        if (list == null) {
            ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "getRealFinishWorkoutNumber, courseList == null");
            return 0;
        }
        for (FitnessPlanCourse fitnessPlanCourse : list) {
            if (fitnessPlanCourse != null && fitnessPlanCourse.acquireFinishStatus()) {
                i++;
            }
        }
        return i;
    }

    private boolean e(WorkoutRecord workoutRecord) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (accountInfo == null) {
            ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "hasExercised accountInfo == null");
            return true;
        }
        Plan d2 = d();
        if (d2 == null) {
            ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "hasExercised fitnessPlan == null");
            return true;
        }
        List<WorkoutRecord> a2 = eup.a(accountInfo, d2.acquireId(), workoutRecord.acquireWorkoutDate());
        if (a2.isEmpty()) {
            ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "hasExercised recordList.isEmpty()");
            return false;
        }
        long b = gib.b(workoutRecord.acquireExerciseTime());
        for (int i = 0; i < a2.size(); i++) {
            if (b == gib.b(a2.get(i).acquireExerciseTime())) {
                ReleaseLogUtil.e("Suggestion_FitnessPackagePlanManager", "hasExercised realZeroTime == historyZeroTime");
                return false;
            }
        }
        return true;
    }

    public boolean b(WorkoutRecord workoutRecord) {
        Plan d2 = d();
        if (d2 == null) {
            ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "hasWorkoutTimeBeyondPlanLastTime fitnessPlan == null");
            return true;
        }
        if (gib.b(workoutRecord.acquireExerciseTime()) <= gib.b(ghz.a(d2.getEndDate(), "yyyy-MM-dd") * 1000)) {
            return false;
        }
        ReleaseLogUtil.e("Suggestion_FitnessPackagePlanManager", "hasWorkoutTimeBeyondPlanLastTime plan expired");
        return true;
    }

    public UserFitnessPlanInfo i() {
        final UserFitnessPlanInfo[] userFitnessPlanInfoArr = {null};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ety.c().b(new UiCallback<epo>() { // from class: euc.6
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.a("Suggestion_FitnessPackagePlanManager", "readCurrentPlanFromIntPlan errorCode = ", Integer.valueOf(i), "errorInfo = ", str);
                countDownLatch.countDown();
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(epo epoVar) {
                if (epoVar != null && epoVar.b() != null) {
                    UserFitnessPlanInfo b = epoVar.b();
                    userFitnessPlanInfoArr[0] = b;
                    LogUtil.a("Suggestion_FitnessPackagePlanManager", "readCurrentPlanFromIntPlan onSuccess");
                    if (b.acquirePlanId() != null) {
                        euc.this.c.putIfAbsent(b.acquirePlanId(), b);
                    }
                } else {
                    LogUtil.a("Suggestion_FitnessPackagePlanManager", "readCurrentPlanFromIntPlan UserFitnessPlanInfo == null");
                }
                countDownLatch.countDown();
            }
        });
        try {
            if (!countDownLatch.await(3000L, TimeUnit.MILLISECONDS)) {
                LogUtil.h("Suggestion_FitnessPackagePlanManager", "readCurrentPlanFromIntPlan wait timeout");
            }
        } catch (InterruptedException unused) {
            LogUtil.b("Suggestion_FitnessPackagePlanManager", "interrupted while waiting for readCurrentPlanFromIntPlan");
        }
        return userFitnessPlanInfoArr[0];
    }

    public Plan d() {
        UserFitnessPlanInfo i;
        if (Utils.j() && (i = i()) != null) {
            return etz.a(i);
        }
        return null;
    }

    public void b(UserFitnessPlanInfo userFitnessPlanInfo, final UiCallback<Object> uiCallback) {
        LogUtil.a("Suggestion_FitnessPackagePlanManager", "updateFitnessPlan");
        if (h()) {
            uiCallback.onFailure(Constants.COORDINATE_FAIL_DEF, "this is overseas version");
            return;
        }
        userFitnessPlanInfo.savePlanStatus(0);
        userFitnessPlanInfo.saveFinishTime(System.currentTimeMillis());
        eqa.a().updateFitnessPackagePlan(userFitnessPlanInfo, new DataCallback() { // from class: euc.7
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str) {
                ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "updateFitnessPlan onFailure errorCode = ", Integer.valueOf(i));
                uiCallback.onFailure(euc.this.g, i, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                LogUtil.a("Suggestion_FitnessPackagePlanManager", "updateFitnessPlan success. data ", jSONObject);
                uiCallback.onSuccess(euc.this.g, jSONObject);
                etx.b().e();
            }
        });
    }

    public void e(String str) {
        final UserFitnessPlanInfo i;
        if (h() || (i = i()) == null) {
            return;
        }
        i.saveName(str);
        eqa.a().updateFitnessPackagePlan(i, new DataCallback() { // from class: euc.9
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i2, String str2) {
                ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "updateCurrentFitnessPlanName errorCode = ", Integer.valueOf(i2));
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                LogUtil.h("Suggestion_FitnessPackagePlanManager", "updateCurrentFitnessPlanName onSuccess");
                etx.b().e();
                euc.this.c(i);
                ary.a().e("PLAN_UPDATE");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(UserFitnessPlanInfo userFitnessPlanInfo) {
        LogUtil.a("Suggestion_FitnessPackagePlanManager", "updateLocalPlanRecord enter");
        if (userFitnessPlanInfo == null) {
            LogUtil.h("Suggestion_FitnessPackagePlanManager", "updateLocalPlanRecord info == null");
            return false;
        }
        PlanRecord d2 = etz.d(userFitnessPlanInfo);
        if (d2 == null) {
            LogUtil.h("Suggestion_FitnessPackagePlanManager", "updateLocalPlanRecord doingRecord == null");
            return false;
        }
        for (int i = 0; i < this.e.size(); i++) {
            if (this.e.get(i).acquirePlanId().equals(d2.acquirePlanId())) {
                this.e.remove(i);
                this.e.add(d2);
                this.c.put(userFitnessPlanInfo.acquirePlanId(), userFitnessPlanInfo);
                LogUtil.a("Suggestion_FitnessPackagePlanManager", "updateLocalPlanRecord success");
                return true;
            }
        }
        LogUtil.a("Suggestion_FitnessPackagePlanManager", "updateLocalPlanRecord fail");
        return false;
    }

    public void b(int i) {
        UserFitnessPlanInfo i2;
        if (h() || (i2 = i()) == null) {
            return;
        }
        i2.saveRemindTime(i);
        c(i);
        eqa.a().updateFitnessPackagePlan(i2, new DataCallback() { // from class: euc.8
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i3, String str) {
                ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "updateCurrentFitnessPlanRemindTime errorCode = ", Integer.valueOf(i3));
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                LogUtil.h("Suggestion_FitnessPackagePlanManager", "updateCurrentFitnessPlanRemindTime onSuccess");
                etx.b().e();
            }
        });
    }

    public void c(int i) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        epo j = j();
        if (j == null || accountInfo == null) {
            return;
        }
        etl.e(j, (Boolean) true, i);
    }

    private epo j() {
        LogUtil.a("Suggestion_FitnessPackagePlanManager", "getIntPlanImpl getCurrentIntPlan");
        epo a2 = ety.c().a();
        if (a2 != null) {
            return a2;
        }
        UserFitnessPlanInfo i = i();
        LogUtil.a("Suggestion_FitnessPackagePlanManager", "getIntPlanImpl convertFitnessPlan");
        return eve.c(i);
    }

    public boolean a() {
        return etl.c();
    }

    public int g() {
        return etl.b();
    }

    public void c(epx epxVar, UiCallback uiCallback) {
        int i;
        boolean z;
        if (h()) {
            uiCallback.onFailure(Constants.COORDINATE_FAIL_DEF, "this is overseas version");
            return;
        }
        if (uiCallback == null) {
            LogUtil.h("Suggestion_FitnessPackagePlanManager", "invalid parameters, callback is null");
            return;
        }
        String c2 = epxVar.c();
        long currentTimeMillis = System.currentTimeMillis();
        epu a2 = epxVar.a();
        if (a2 != null) {
            c2 = a2.a();
            currentTimeMillis = a2.b();
            i = a2.c();
            LogUtil.a("Suggestion_FitnessPackagePlanManager", "isGeneralPlan planTempId = ", c2, " startTime = ", Long.valueOf(currentTimeMillis), " remindTime = ", Integer.valueOf(i));
            z = true;
        } else {
            i = 1080;
            z = false;
        }
        int i2 = i;
        long j = currentTimeMillis;
        if (c2 == null || c2.isEmpty()) {
            LogUtil.h("Suggestion_FitnessPackagePlanManager", "invalid parameters, planTempId is null or empty");
            uiCallback.onFailure(-1, "invalid parameters, planTempId is null or empty");
            return;
        }
        FitnessPackageInfo d2 = euh.a().d(c2);
        if (d2 == null) {
            LogUtil.h("Suggestion_FitnessPackagePlanManager", "plan package not exists");
            uiCallback.onFailure(-1, "plan package not exists");
            return;
        }
        UserFitnessPlanInfo e = e(d2, j, i2, z);
        if (e == null) {
            LogUtil.h("Suggestion_FitnessPackagePlanManager", "user fitness plan info is null.");
            uiCallback.onFailure(this.g, -1, "user fitness plan info is null.");
        } else if (eve.b && !z) {
            e(e, (UiCallback<IntPlan>) uiCallback);
        } else {
            d((UiCallback<UserFitnessPlanInfo>) uiCallback, e);
        }
    }

    private void d(final UiCallback<UserFitnessPlanInfo> uiCallback, UserFitnessPlanInfo userFitnessPlanInfo) {
        eqa.a().createFitnessPackagePlan(userFitnessPlanInfo, new DataCallback() { // from class: euc.13
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str) {
                ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "createFitnessPackagePlan errorCode = ", Integer.valueOf(i), " errorInfo = ", str);
                uiCallback.onFailure(euc.this.g, i, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject == null) {
                    uiCallback.onFailure(euc.this.g, -1, "cloud has no doing plan data");
                    return;
                }
                String jSONObject2 = jSONObject.toString();
                LogUtil.a("Suggestion_FitnessPackagePlanManager", "createFitnessPackagePlan onSuccess data = ", jSONObject);
                euc.this.i(jSONObject2);
                UserFitnessPlanInfo userFitnessPlanInfo2 = (UserFitnessPlanInfo) new Gson().fromJson(jSONObject.optString("userFitnessPlanInfo"), UserFitnessPlanInfo.class);
                FitnessPackageInfo d2 = euh.a().d(userFitnessPlanInfo2.acquirePlanTempId());
                if (d2 != null) {
                    userFitnessPlanInfo2.saveCardPicture(d2.acquirePicture());
                }
                euc.this.c.put(userFitnessPlanInfo2.acquirePlanId(), userFitnessPlanInfo2);
                euc.this.e.add(etz.d(userFitnessPlanInfo2));
                ety.c().b(userFitnessPlanInfo2);
                euc.this.c(userFitnessPlanInfo2.acquireRemindTime());
                uiCallback.onSuccess(euc.this.g, userFitnessPlanInfo2);
            }
        });
    }

    private void e(final UserFitnessPlanInfo userFitnessPlanInfo, final UiCallback<IntPlan> uiCallback) {
        IntPlanBean intPlanBean = new IntPlanBean(eve.a(userFitnessPlanInfo));
        LogUtil.c("Suggestion_FitnessPackagePlanManager", "createIntPlan bean = ", intPlanBean);
        ety.c().e(intPlanBean, new UiCallback<IntPlan>() { // from class: euc.11
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.h("Suggestion_FitnessPackagePlanManager", "create new IntPlan fallback");
                euc.this.c(userFitnessPlanInfo, (UiCallback<IntPlan>) uiCallback);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(IntPlan intPlan) {
                LogUtil.c("Suggestion_FitnessPackagePlanManager", "create new IntPlan success.");
                uiCallback.onSuccess(intPlan);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(UserFitnessPlanInfo userFitnessPlanInfo, final UiCallback<IntPlan> uiCallback) {
        eqa.a().createFitnessPackagePlan(userFitnessPlanInfo, new DataCallback() { // from class: euc.1
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str) {
                LogUtil.b("Suggestion_FitnessPackagePlanManager", "createFitnessPackagePlan errorCode = ", Integer.valueOf(i), " errorInfo = ", str);
                uiCallback.onFailure(euc.this.g, i, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject == null) {
                    uiCallback.onFailure(euc.this.g, -1, "cloud has no doing plan data");
                    return;
                }
                String jSONObject2 = jSONObject.toString();
                LogUtil.a("Suggestion_FitnessPackagePlanManager", "createFallBack onSuccess data = ", jSONObject);
                euc.this.i(jSONObject2);
                UserFitnessPlanInfo userFitnessPlanInfo2 = (UserFitnessPlanInfo) new Gson().fromJson(jSONObject.optString("userFitnessPlanInfo"), UserFitnessPlanInfo.class);
                FitnessPackageInfo d2 = euh.a().d(userFitnessPlanInfo2.acquirePlanTempId());
                if (d2 != null) {
                    userFitnessPlanInfo2.saveCardPicture(d2.acquirePicture());
                }
                euc.this.c.put(userFitnessPlanInfo2.acquirePlanId(), userFitnessPlanInfo2);
                euc.this.e.add(etz.d(userFitnessPlanInfo2));
                epo b = ety.c().b(userFitnessPlanInfo2);
                euc.this.c(userFitnessPlanInfo2.acquireRemindTime());
                uiCallback.onSuccess(euc.this.g, b);
                ary.a().e("PLAN_CREATE");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(String str) {
        HashMap hashMap = new HashMap(10);
        if (gge.c()) {
            hashMap.put("create_time", Long.valueOf(System.currentTimeMillis()));
            hashMap.put("type", 1);
        }
        hashMap.put(ParsedFieldTag.GOAL, 3);
        hashMap.put("data", str);
        gge.e("1120008", hashMap);
    }

    public void e(final ResultCallback resultCallback) {
        if (resultCallback == null) {
            LogUtil.h("Suggestion_FitnessPackagePlanManager", "queryAllCompletedFitnessPlan callback == null");
        } else if (h()) {
            resultCallback.onResult(Constants.COORDINATE_FAIL_DEF, "this is overseas version");
        } else {
            eqa.a().queryFitnessPlanSummary(new DataCallback() { // from class: euc.2
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.d("Suggestion_FitnessPackagePlanManager", "queryAllCompletedFitnessPlan errorCode = ", Integer.valueOf(i));
                    resultCallback.onResult(i, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    LogUtil.c("Suggestion_FitnessPackagePlanManager", "queryAllCompletedFitnessPlan onSuccess");
                    synchronized (euc.d) {
                        euc.this.d(jSONObject);
                        euc.this.a(resultCallback);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final ResultCallback resultCallback) {
        if (koq.b(this.f12323a)) {
            LogUtil.a("Suggestion_FitnessPackagePlanManager", "cloud has no completed fitness plan");
            resultCallback.onResult(0, "mAllCompletedPlanIds.isEmpty()");
        } else {
            Iterator<String> it = this.f12323a.iterator();
            while (it.hasNext()) {
                eqa.a().queryFitnessPkgPlan(it.next(), new DataCallback() { // from class: euc.4
                    @Override // com.huawei.basefitnessadvice.callback.DataCallback
                    public void onFailure(int i, String str) {
                        LogUtil.h("Suggestion_FitnessPackagePlanManager", "errorCode = ", Integer.valueOf(i), "; errorInfo = ", str);
                        resultCallback.onResult(i, str);
                    }

                    @Override // com.huawei.basefitnessadvice.callback.DataCallback
                    public void onSuccess(JSONObject jSONObject) {
                        if (jSONObject == null) {
                            resultCallback.onResult(-1, "cloud has data");
                            return;
                        }
                        LogUtil.a("Suggestion_FitnessPackagePlanManager", "getCloudCompletedPlanDetails onSuccess");
                        euc.this.d(jSONObject.optString("userFitnessPlanInfo"), resultCallback);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, ResultCallback resultCallback) {
        UserFitnessPlanInfo userFitnessPlanInfo = (UserFitnessPlanInfo) new Gson().fromJson(str, UserFitnessPlanInfo.class);
        if (userFitnessPlanInfo != null) {
            String acquirePlanId = userFitnessPlanInfo.acquirePlanId();
            this.c.put(acquirePlanId, userFitnessPlanInfo);
            if (this.f12323a.size() > 0) {
                if (acquirePlanId.equals(this.f12323a.get(r1.size() - 1))) {
                    resultCallback.onResult(0, "has finished download plans");
                }
            }
            if (userFitnessPlanInfo.acquirePlanStatus() == 0) {
                etx.b().e();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h("Suggestion_FitnessPackagePlanManager", "getCloudCompletedPlanIds, data == null");
            return;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("userFitnessPlanList");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    String string = optJSONArray.optJSONObject(i).getString("planId");
                    if (!this.f12323a.contains(string)) {
                        this.f12323a.add(string);
                    }
                } catch (JSONException e) {
                    LogUtil.b("Suggestion_FitnessPackagePlanManager", LogAnonymous.b((Throwable) e));
                }
            }
        }
    }

    private UserFitnessPlanInfo e(FitnessPackageInfo fitnessPackageInfo, long j, int i, boolean z) {
        if (fitnessPackageInfo == null) {
            LogUtil.b("Suggestion_FitnessPackagePlanManager", "buildUserFitnessPackage info == null");
            return null;
        }
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        if (userProfileMgrApi == null) {
            LogUtil.h("Suggestion_FitnessPackagePlanManager", "buildUserFitnessPackage : userProfileMgrApi is null.");
            return null;
        }
        UserInfomation userInfo = userProfileMgrApi.getUserInfo();
        if (userInfo == null) {
            LogUtil.h("Suggestion_FitnessPackagePlanManager", "userInfo == null");
            return null;
        }
        float weightOrDefaultValue = userInfo.getWeightOrDefaultValue();
        UserFitnessPlanInfo userFitnessPlanInfo = new UserFitnessPlanInfo();
        userFitnessPlanInfo.savePlanTempId(fitnessPackageInfo.acquirePlanTempId());
        userFitnessPlanInfo.saveName(fitnessPackageInfo.acquireName());
        userFitnessPlanInfo.savePicture(fitnessPackageInfo.acquirePicture());
        userFitnessPlanInfo.saveCardPicture(fitnessPackageInfo.acquireCardPicture());
        userFitnessPlanInfo.saveDescription(fitnessPackageInfo.acquireDescription());
        userFitnessPlanInfo.saveRemindTime(i);
        if (z) {
            return a(fitnessPackageInfo, userFitnessPlanInfo, j);
        }
        return b(weightOrDefaultValue, fitnessPackageInfo, userFitnessPlanInfo, j);
    }

    private UserFitnessPlanInfo b(float f, FitnessPackageInfo fitnessPackageInfo, UserFitnessPlanInfo userFitnessPlanInfo, long j) {
        ArrayList arrayList = new ArrayList(10);
        List<FitnessWeekPlan> acquireFitnessWeekPlanList = fitnessPackageInfo.acquireFitnessWeekPlanList();
        if (koq.b(acquireFitnessWeekPlanList)) {
            LogUtil.h("Suggestion_FitnessPackagePlanManager", "convertFitnessPlan fitnessWeekPlanList == null");
            return null;
        }
        double d2 = 0.0d;
        int i = 0;
        for (FitnessWeekPlan fitnessWeekPlan : acquireFitnessWeekPlanList) {
            FitnessWeekPlan fitnessWeekPlan2 = new FitnessWeekPlan();
            fitnessWeekPlan2.saveWeekOrder(fitnessWeekPlan.acquireWeekOrder());
            fitnessWeekPlan2.saveWeekDesc(fitnessWeekPlan.acquireWeekDesc());
            fitnessWeekPlan2.saveWeekPeriod(fitnessWeekPlan.acquireWeekPeriod());
            ArrayList arrayList2 = new ArrayList(10);
            for (FitnessDayPlan fitnessDayPlan : fitnessWeekPlan.acquireWeekList()) {
                FitnessDayPlan fitnessDayPlan2 = (FitnessDayPlan) fitnessDayPlan.clone();
                fitnessDayPlan2.saveStartTime(j + (i * 86400000));
                arrayList2.add(fitnessDayPlan2);
                d2 = d(f, d2, fitnessDayPlan.acquireDayPlanCourses());
                i++;
            }
            fitnessWeekPlan2.saveWeekList(arrayList2);
            arrayList.add(fitnessWeekPlan2);
        }
        LogUtil.a("Suggestion_FitnessPackagePlanManager", "totalCalorie = ", Double.valueOf(d2), "cal");
        float f2 = ((float) d2) / 1000.0f;
        LogUtil.a("Suggestion_FitnessPackagePlanManager", "kCal = ", Float.valueOf(f2), ParsedFieldTag.K_CAL);
        userFitnessPlanInfo.saveTotalCalorie(String.valueOf(f2));
        userFitnessPlanInfo.saveWeekPlanList(arrayList);
        return userFitnessPlanInfo;
    }

    private UserFitnessPlanInfo a(FitnessPackageInfo fitnessPackageInfo, UserFitnessPlanInfo userFitnessPlanInfo, long j) {
        userFitnessPlanInfo.saveIntroduction(fitnessPackageInfo.acquireIntroduction());
        userFitnessPlanInfo.saveDisplayStyle(fitnessPackageInfo.acquireDisplayStyle());
        userFitnessPlanInfo.savePlanCategory(fitnessPackageInfo.acquirePlanCategory());
        userFitnessPlanInfo.saveBeginDate(j / 1000);
        ArrayList arrayList = new ArrayList(10);
        List<FitnessWeekPlan> acquireWeeklyPlanList = fitnessPackageInfo.acquireWeeklyPlanList();
        if (koq.b(acquireWeeklyPlanList)) {
            LogUtil.h("Suggestion_FitnessPackagePlanManager", "convertGeneralPlan fitnessWeekPlanList == null");
            return null;
        }
        for (FitnessWeekPlan fitnessWeekPlan : acquireWeeklyPlanList) {
            FitnessWeekPlan fitnessWeekPlan2 = new FitnessWeekPlan();
            fitnessWeekPlan2.saveWeekOrder(fitnessWeekPlan.acquireWeekOrder());
            ArrayList arrayList2 = new ArrayList(10);
            Iterator<FitnessDayPlan> it = fitnessWeekPlan.acquireDailyPlanList().iterator();
            int i = 0;
            while (it.hasNext()) {
                FitnessDayPlan fitnessDayPlan = (FitnessDayPlan) it.next().clone();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(j));
                int i2 = 7;
                int d2 = (gib.d(calendar.get(7)) + i) % 7;
                if (d2 != 0) {
                    i2 = d2;
                }
                fitnessDayPlan.saveDayOrder(i2);
                arrayList2.add(fitnessDayPlan);
                i++;
            }
            fitnessWeekPlan2.saveDailyPlanList(arrayList2);
            arrayList.add(fitnessWeekPlan2);
        }
        userFitnessPlanInfo.saveWeeklyPlanList(arrayList);
        return userFitnessPlanInfo;
    }

    private double d(float f, double d2, List<FitnessPlanCourse> list) {
        if (list != null) {
            for (FitnessPlanCourse fitnessPlanCourse : list) {
                if (fitnessPlanCourse != null) {
                    d2 += fitnessPlanCourse.acquireWorkoutRealCal() * f;
                }
            }
        }
        return d2;
    }

    private boolean h() {
        boolean b = fev.c.b();
        LogUtil.c("Suggestion_FitnessPackagePlanManager", "getIsOversea() = ", Boolean.valueOf(b));
        return b;
    }

    private boolean l() {
        return Looper.getMainLooper() == Looper.myLooper();
    }
}
