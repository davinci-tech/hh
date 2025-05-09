package health.compact.a;

import android.content.Context;
import com.huawei.caloriecalculate.ActivityCaloriesCalculateApi;
import com.huawei.health.connectivity.extendstepcounter.ExtendStepDataManager;
import com.huawei.health.manager.CaloriesManager$CaloriesData;
import com.huawei.health.manager.common.SingleDayBaseManager;
import com.huawei.health.manager.util.UserInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import defpackage.bdw;
import defpackage.jdl;
import java.util.Date;

/* loaded from: classes.dex */
public class CaloriesManager extends SingleDayBaseManager {
    private static CaloriesManager c;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private DistanceManager f13107a;
    private CaloriesManager$CaloriesData b;
    private ExtendStepDataManager d;
    private int f;
    private int g;
    private int h;
    private long i;
    private double j;
    private UserInfo l;

    private CaloriesManager(Context context, DistanceManager distanceManager, ExtendStepDataManager extendStepDataManager) {
        super(context);
        this.l = null;
        this.f13107a = null;
        this.i = 0L;
        this.f = 0;
        this.h = 0;
        this.j = 0.0d;
        this.g = 0;
        if (distanceManager == null) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_CaloriesManager", "distanceManager is null");
            return;
        }
        this.f13107a = distanceManager;
        this.d = extendStepDataManager;
        com.huawei.hwlogsmodel.LogUtil.a("Step_CaloriesManager", "init DistanceManager context=", context);
        this.b = a();
        this.l = UserInfo.d();
        com.huawei.hwlogsmodel.LogUtil.a("Step_CaloriesManager", "init mIsSupportExtendStep=", Boolean.valueOf(this.mIsSupportExtendStep));
    }

    public static CaloriesManager e(Context context, DistanceManager distanceManager, ExtendStepDataManager extendStepDataManager) {
        CaloriesManager caloriesManager;
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_CaloriesManager", "context = null");
            context = BaseApplication.getContext();
        }
        if (distanceManager == null) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_CaloriesManager", "distanceManager = null");
            distanceManager = DistanceManager.a(context);
        }
        synchronized (e) {
            if (c == null) {
                c = new CaloriesManager(context, distanceManager, extendStepDataManager);
            }
            caloriesManager = c;
        }
        return caloriesManager;
    }

    private void b() throws Exception {
        checkDataConsistency(this.b.d(), this.mEventClock.c());
    }

    @Override // com.huawei.health.manager.common.SingleDayBaseManager
    public void reduceClass() {
        synchronized (e) {
            super.reduceClass();
            a(System.currentTimeMillis());
        }
    }

    public void a(long j) {
        synchronized (e) {
            if (this.b == null) {
                com.huawei.hwlogsmodel.LogUtil.h("Step_CaloriesManager", "mCaloriesData is null");
                return;
            }
            this.mEventClock.b(j);
            try {
                b();
            } catch (Exception unused) {
                this.b.e();
                this.b.d(j, 0.0d);
                com.huawei.hwlogsmodel.LogUtil.h("Step_CaloriesManager", "clearDiff checkDataConsistency error:", " caloriesDiff reset zero");
            }
            this.b.e();
        }
    }

    private CaloriesManager$CaloriesData a() {
        CaloriesManager$CaloriesData caloriesManager$CaloriesData = new CaloriesManager$CaloriesData();
        com.huawei.hwlogsmodel.LogUtil.a("Step_CaloriesManager", "initDiffCaloriesfromFile");
        if (this.mContext == null) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_CaloriesManager", "initDiffCaloriesfromFile mContext null");
            return caloriesManager$CaloriesData;
        }
        String[] h = SharedPerferenceUtils.h(this.mContext);
        try {
        } catch (NumberFormatException e2) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_CaloriesManager", "initDiffCaloriesfromFile ", e2.getMessage());
            caloriesManager$CaloriesData.e();
        }
        if (h == null) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_CaloriesManager", "initDiffCaloriesfromFile diffCalArray null");
            return caloriesManager$CaloriesData;
        }
        long parseLong = Long.parseLong(h[0]);
        if (jdl.d(System.currentTimeMillis(), parseLong)) {
            caloriesManager$CaloriesData.e = parseLong;
            caloriesManager$CaloriesData.b = Double.parseDouble(h[1]);
            com.huawei.hwlogsmodel.LogUtil.a("Step_CaloriesManager", "initDiffCaloriesfromFile timestampRecord=", Long.valueOf(parseLong));
        }
        return caloriesManager$CaloriesData;
    }

    public void c(long j, int i, int i2) {
        synchronized (e) {
            if (this.b == null) {
                com.huawei.hwlogsmodel.LogUtil.h("Step_CaloriesManager", "CaloriesData is null");
                return;
            }
            com.huawei.hwlogsmodel.LogUtil.a("Step_CaloriesManager", "syncCalories eventTimestamp=", Long.valueOf(j), " steps=", Integer.valueOf(i), " calDB ", Integer.valueOf(i2));
            this.mEventClock.b(j);
            try {
                b();
            } catch (Exception unused) {
                this.b.e();
                this.b.d(j, 0.0d);
                com.huawei.hwlogsmodel.LogUtil.h("Step_CaloriesManager", "syncCalories checkDataConsistency error:", " caloriesDiff reset eventTimestamp=", Long.valueOf(j));
            }
            int c2 = c(j, i, true);
            this.g = Math.max(i2, this.g);
            if (i2 > c2) {
                this.b.e(i2 - c2);
                a(j, this.b.b);
            }
        }
    }

    private void a(long j, double d) {
        if (this.mContext != null) {
            SharedPerferenceUtils.c(this.mContext, j, d);
            com.huawei.hwlogsmodel.LogUtil.a("Step_CaloriesManager", "saveCaloriesToFile total db caloriesdiff mIsSupportExtendStep", Boolean.valueOf(this.mIsSupportExtendStep), " diffCal ", Double.valueOf(d));
        }
    }

    public int c(long j, int i) {
        double d = this.g % 1000;
        double abs = Math.abs(this.b.b - this.j);
        ExtendStepDataManager extendStepDataManager = this.d;
        int i2 = extendStepDataManager != null ? extendStepDataManager.i() : 0;
        if (i == 0 || Math.abs(i - this.f) > 25 || d + abs > 1000.0d || (i2 != 0 && Math.abs(i2 - this.h) > 0)) {
            this.g = c(j, i, false);
            this.f = i;
            this.j = this.b.b;
            this.h = i2;
        }
        return this.g;
    }

    public int c(long j, int i, boolean z) {
        double d;
        synchronized (e) {
            if (this.b == null) {
                com.huawei.hwlogsmodel.LogUtil.h("Step_CaloriesManager", "CaloriesData is null");
                return 0;
            }
            this.mEventClock.b(j);
            try {
                b();
            } catch (Exception unused) {
                this.b.e();
                this.b.d(j, 0.0d);
                com.huawei.hwlogsmodel.LogUtil.h("Step_CaloriesManager", "acquireDistance checkDataConsistency error:", " caloriesDiff reset eventTimestamp=", Long.valueOf(j));
            }
            double b = b(i);
            if (this.mIsSupportExtendStep) {
                d = this.d.c() + this.b.b;
                if (!z && b > d) {
                    d = b;
                }
            } else {
                d = this.b.b + b;
            }
            int i2 = (int) d;
            long b2 = com.huawei.hwlogsmodel.LogUtil.b(2000, this.i);
            if (b2 != -1) {
                com.huawei.hwlogsmodel.LogUtil.a("Step_CaloriesManager", "acquireDistance stepsSum =", Integer.valueOf(i), " mIsSupportExtendStep = ", Boolean.valueOf(this.mIsSupportExtendStep), " isForceTruth ", Boolean.valueOf(z), " stepCal ", Double.valueOf(b), " calNow ", Integer.valueOf(i2));
                this.i = b2;
            }
            return i2;
        }
    }

    private double b(int i) {
        if (i == 0) {
            return 0.0d;
        }
        ActivityCaloriesCalculateApi activityCaloriesCalculateApi = (ActivityCaloriesCalculateApi) Services.c("BaseSportModel", ActivityCaloriesCalculateApi.class);
        bdw.e eVar = new bdw.e();
        UserInfo userInfo = this.l;
        if (userInfo != null) {
            eVar.d(userInfo.b()).c(this.l.c() / 100.0f).b(this.l.j()).c(this.l.e());
        }
        activityCaloriesCalculateApi.initUserInfo(eVar.a());
        activityCaloriesCalculateApi.initCalculateCalories(jdl.t(new Date().getTime()), -1);
        if (activityCaloriesCalculateApi != null) {
            return activityCaloriesCalculateApi.calculateCaloriesFromSteps(System.currentTimeMillis(), i) * 1000.0d;
        }
        return 0.0d;
    }
}
