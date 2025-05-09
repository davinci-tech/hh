package defpackage;

import com.huawei.caloriecalculate.ActivityCaloriesCalculateApi;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.bdy;
import health.compact.a.CalculateCaloriesUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes4.dex */
public class gur {
    protected float e = 0.0f;
    private float d = 0.0f;
    private long b = 0;

    public void a(ActivityCaloriesCalculateApi activityCaloriesCalculateApi, float f, float f2, int i) {
        this.e = this.d + b(activityCaloriesCalculateApi, e(f, f2, i));
        LogUtil.a(e(), "updateCalorie, ", Float.valueOf(this.e));
    }

    public void b(float f, long j, float f2) {
        if (j - this.b <= 0) {
            ReleaseLogUtil.d(e(), "updateCalorie : duration <= 0");
            return;
        }
        this.e += (float) CalculateCaloriesUtils.b(f / 1000.0f, f2);
        this.b = j;
        LogUtil.a(e(), "updateClimbCalorie, ", Float.valueOf(this.e), " sportDuration, ", Long.valueOf(this.b));
    }

    public float c() {
        return this.e;
    }

    public void a(float f) {
        this.e = f;
        LogUtil.a(e(), "setCalorie: ", Float.valueOf(f));
    }

    public void e(float f) {
        this.d = f;
        this.e = f;
        LogUtil.a(e(), "setRecoveryCalorie: ", Float.valueOf(this.d));
    }

    public void b(float f) {
        this.e *= f;
        LogUtil.a(e(), "changeRatioOfCalories: ", Float.valueOf(this.e));
    }

    protected float b(ActivityCaloriesCalculateApi activityCaloriesCalculateApi, bdy bdyVar) {
        return Math.max(activityCaloriesCalculateApi != null ? (float) activityCaloriesCalculateApi.calculateTotalCalories(System.currentTimeMillis(), bdyVar) : 0.0f, 0.0f);
    }

    private bdy e(float f, float f2, int i) {
        if (i == 264) {
            return new bdy.c().e(f2).e();
        }
        return new bdy.c().e(f2).a(f).e();
    }

    protected String e() {
        return "Track_CalorieCalculate";
    }
}
