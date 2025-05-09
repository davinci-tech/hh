package defpackage;

import com.huawei.caloriecalculate.ActivityCaloriesCalculateApi;
import com.huawei.health.trusport_ee.Activity;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.glf;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

@ApiDefine(uri = ActivityCaloriesCalculateApi.class)
/* loaded from: classes3.dex */
public class cct implements ActivityCaloriesCalculateApi {

    /* renamed from: a, reason: collision with root package name */
    private double f619a;
    private double b;
    private Activity c;
    private double d;
    private double e;
    private glh i;

    @Override // com.huawei.caloriecalculate.ActivityCaloriesCalculateApi
    public void initUserInfo(bdw bdwVar) {
        try {
            this.i = new glh();
            glb glbVar = new glb(bdwVar.c(), bdwVar.e(), bdwVar.a(), cck.c(bdwVar.b()));
            if (bdwVar.d() > 0) {
                this.i.b(bdwVar.d());
            }
            this.i.b(glbVar);
        } catch (Throwable th) {
            ReleaseLogUtil.d("R_ActivityCaloriesCalculateImpl", "initUserInfo, throwable: ", LogAnonymous.b(th));
        }
    }

    @Override // com.huawei.caloriecalculate.ActivityCaloriesCalculateApi
    public void initCalculateCalories(long j, int i) {
        Activity d = cck.d(i);
        this.c = d;
        if (d == Activity.TS_ACTIVITY_IDLE) {
            this.e += this.b;
            this.b = 0.0d;
            this.d += this.f619a;
            this.f619a = 0.0d;
        }
        try {
            this.i.b(j, this.c);
        } catch (Throwable th) {
            LogUtil.h("ActivityCaloriesCalculateImpl", "initCalculateCalories, throwable: ", th.getMessage());
        }
    }

    @Override // com.huawei.caloriecalculate.ActivityCaloriesCalculateApi
    public double calculateCalories(long j) {
        glh glhVar = this.i;
        if (glhVar == null) {
            LogUtil.h("ActivityCaloriesCalculateImpl", "calculateCalories, mTruSportEe == null");
            return 0.0d;
        }
        try {
            glhVar.e(j);
        } catch (Throwable th) {
            LogUtil.h("ActivityCaloriesCalculateImpl", "calculateCalories, throwable: ", th.getMessage());
        }
        return d();
    }

    @Override // com.huawei.caloriecalculate.ActivityCaloriesCalculateApi
    public double calculateCalories(long j, bdy bdyVar) {
        if (bdyVar == null) {
            LogUtil.h("ActivityCaloriesCalculateImpl", "calculateCalories, sampleData == null");
            return this.e + this.b;
        }
        if (this.i == null) {
            LogUtil.h("ActivityCaloriesCalculateImpl", "calculateCalories, mTruSportEe == null");
            return 0.0d;
        }
        e(j, bdyVar);
        return d();
    }

    private void e(long j, bdy bdyVar) {
        glf.c cVar = new glf.c(j);
        if (cck.a(bdyVar.e(), this.c)) {
            cVar.b(bdyVar.e());
        }
        if (cck.a(bdyVar.c())) {
            cVar.d(bdyVar.c());
        }
        if (cck.e(bdyVar.a())) {
            cVar.e(bdyVar.a());
        }
        try {
            this.i.e(cVar.d());
        } catch (Throwable th) {
            LogUtil.h("ActivityCaloriesCalculateImpl", "calculateCalories, throwable: ", th.getMessage());
        }
    }

    @Override // com.huawei.caloriecalculate.ActivityCaloriesCalculateApi
    public double calculateTotalCalories(long j, bdy bdyVar) {
        if (bdyVar == null) {
            LogUtil.h("ActivityCaloriesCalculateImpl", "calculateTotalCalories, sampleData == null");
            return this.d + this.f619a;
        }
        if (this.i == null) {
            LogUtil.h("ActivityCaloriesCalculateImpl", "calculateTotalCalories, mTruSportEe == null");
            return 0.0d;
        }
        e(j, bdyVar);
        return c();
    }

    @Override // com.huawei.caloriecalculate.ActivityCaloriesCalculateApi
    public double calculateCaloriesFromSteps(long j, int i) {
        if (this.i == null) {
            LogUtil.h("ActivityCaloriesCalculateImpl", "calculateCalories, mTruSportEe == null");
            return 0.0d;
        }
        b(j, i);
        return d();
    }

    private void b(long j, int i) {
        try {
            this.i.d(j, i);
        } catch (Throwable th) {
            LogUtil.h("ActivityCaloriesCalculateImpl", "calculateCaloriesFromSteps, throwable: ", th.getMessage());
        }
    }

    @Override // com.huawei.caloriecalculate.ActivityCaloriesCalculateApi
    public double calculateTotalCaloriesFromSteps(long j, int i) {
        if (this.i == null) {
            LogUtil.h("ActivityCaloriesCalculateImpl", "calculateCalories, mTruSportEe == null");
            return 0.0d;
        }
        b(j, i);
        return c();
    }

    @Override // com.huawei.caloriecalculate.ActivityCaloriesCalculateApi
    public float calculateCaloriesOnlyStep(long j) {
        glh glhVar = this.i;
        if (glhVar == null) {
            LogUtil.h("ActivityCaloriesCalculateImpl", "calculateCaloriesOnlyStep, mTruSportEe == null");
            return 0.0f;
        }
        try {
            return glhVar.b(j).floatValue();
        } catch (Throwable th) {
            LogUtil.h("ActivityCaloriesCalculateImpl", "calculateCaloriesOnlyStep, throwable: ", th.getMessage());
            return 0.0f;
        }
    }

    @Override // com.huawei.caloriecalculate.ActivityCaloriesCalculateApi
    public long calculateDurationByCalories(float f, int i, int i2) {
        glh glhVar = this.i;
        if (glhVar == null) {
            LogUtil.h("ActivityCaloriesCalculateImpl", "calculateDurationByCalories, mTruSportEe == null");
            return 0L;
        }
        try {
            return glhVar.e(f, cck.d(i), cck.b(i2));
        } catch (Throwable th) {
            LogUtil.h("ActivityCaloriesCalculateImpl", "calculateDurationByCalories, throwable: ", th.getMessage());
            return 0L;
        }
    }

    private double d() {
        double d;
        try {
        } catch (Throwable th) {
            LogUtil.h("ActivityCaloriesCalculateImpl", "getActiveCalories, throwable: ", th.getMessage());
        }
        if (this.i.e() != null) {
            d = this.i.e().getD();
            this.b = d;
            return this.e + d;
        }
        d = 0.0d;
        this.b = d;
        return this.e + d;
    }

    private double c() {
        double d;
        try {
        } catch (Throwable th) {
            LogUtil.h("ActivityCaloriesCalculateImpl", "getTotalCalories, throwable: ", th.getMessage());
        }
        if (this.i.e() != null) {
            d = this.i.e().getC();
            this.f619a = d;
            return this.d + d;
        }
        d = 0.0d;
        this.f619a = d;
        return this.d + d;
    }
}
