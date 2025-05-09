package com.huawei.health.suggestion.ui.fitness.mvp;

import com.huawei.haf.application.BaseApplication;
import com.huawei.health.suggestion.CoachController;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.utils.FoundationCommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class CaloriesConsumeHandler {
    private static final long c = TimeUnit.MINUTES.toMillis(1);

    /* renamed from: a, reason: collision with root package name */
    private c f3194a;
    private float b;
    private CaloriesRefreshCallback d;
    private Map<Long, HiHealthData> e = new HashMap(10);
    private boolean f = false;
    private float g;
    private float h;
    private float j;

    public interface CaloriesRefreshCallback {
        void onRefresh(float f);
    }

    public enum CaloriesSource {
        COURSE_PRESET,
        LINK_WEAR,
        EQUIPMENT
    }

    public CaloriesConsumeHandler(boolean z, boolean z2, CaloriesRefreshCallback caloriesRefreshCallback) {
        this.f3194a = new c(z, z2);
        this.d = caloriesRefreshCallback;
    }

    public float b() {
        return this.h;
    }

    public float d() {
        return this.b;
    }

    public boolean a() {
        return this.f;
    }

    public void c(float f, float f2, CaloriesSource caloriesSource) {
        CaloriesSource c2 = this.f3194a.c();
        if (c2.equals(caloriesSource)) {
            if (c2.equals(CaloriesSource.LINK_WEAR)) {
                this.f = true;
            }
            synchronized (this) {
                LogUtil.c("Suggestion_CaloriesConsumeHandler", "updateConsumeCalories:", Float.valueOf(f), " activeCalories:", Float.valueOf(f2), " mReferenceCalories:", Float.valueOf(this.g), " selectedSource:", c2, " mUsedSource:", this.f3194a.e);
                if (this.f3194a.e.equals(c2)) {
                    float f3 = f - this.g;
                    if (f3 < 0.0f) {
                        LogUtil.b("Suggestion_CaloriesConsumeHandler", "consumeCalories:", Float.valueOf(f), " mReferenceCalories:", Float.valueOf(this.g));
                    } else {
                        this.h += f3;
                    }
                    this.g = f;
                    float f4 = f2 - this.j;
                    if (f4 < 0.0f) {
                        LogUtil.b("Suggestion_CaloriesConsumeHandler", "activeCaloriesIncrement:", Float.valueOf(f2), " mReferenceActiveCalories:", Float.valueOf(this.j));
                    } else {
                        this.b += f4;
                        long currentTimeMillis = System.currentTimeMillis();
                        long j = c;
                        a((currentTimeMillis / j) * j, f4);
                    }
                    this.j = this.b;
                    CaloriesRefreshCallback caloriesRefreshCallback = this.d;
                    if (caloriesRefreshCallback != null) {
                        caloriesRefreshCallback.onRefresh(this.h);
                    }
                    CoachController.d().d(this.b);
                } else {
                    ReleaseLogUtil.e("Suggestion_CaloriesConsumeHandler", "switch source:", c2, " mUsedSource:", this.f3194a.e, " consumeCalories:", Float.valueOf(f), " mReferenceCalories:", Float.valueOf(this.g));
                    if (this.h == 0.0f) {
                        this.h = f;
                    }
                    if (this.b == 0.0f) {
                        this.b = f2;
                    }
                    this.g = f;
                    this.j = f2;
                    this.f3194a.b(c2);
                }
            }
        }
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        private boolean f3195a;
        private boolean b;
        private CaloriesSource e = CaloriesSource.COURSE_PRESET;

        c(boolean z, boolean z2) {
            this.b = z;
            this.f3195a = z2;
        }

        CaloriesSource c() {
            boolean h = CoachController.d().h();
            if (this.b && h) {
                return CaloriesSource.LINK_WEAR;
            }
            if (this.f3195a) {
                return CaloriesSource.EQUIPMENT;
            }
            return CaloriesSource.COURSE_PRESET;
        }

        void b(CaloriesSource caloriesSource) {
            this.e = caloriesSource;
        }
    }

    public Map<Long, HiHealthData> c() {
        return this.e;
    }

    private void a(long j, float f) {
        HiHealthData hiHealthData = this.e.get(Long.valueOf(j));
        if (hiHealthData != null) {
            hiHealthData.setValue(hiHealthData.getFloatValue() + f);
            return;
        }
        HiHealthData hiHealthData2 = new HiHealthData(4);
        hiHealthData2.setTimeInterval(j - TimeUnit.MINUTES.toMillis(1L), j);
        hiHealthData2.setValue(f);
        hiHealthData2.setDeviceUuid(FoundationCommonUtil.getAndroidId(BaseApplication.e()));
        this.e.put(Long.valueOf(j), hiHealthData2);
    }
}
