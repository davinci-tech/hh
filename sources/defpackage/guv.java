package defpackage;

import com.huawei.caloriecalculate.ActivityCaloriesCalculateApi;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import health.compact.a.Services;

/* loaded from: classes4.dex */
public class guv {
    private ActivityCaloriesCalculateApi d;
    private int c = 0;
    private float e = 60.0f;
    private final gur b = new gur();

    /* renamed from: a, reason: collision with root package name */
    private final gur f12950a = new gus();

    public void d(UserInfomation userInfomation, long j, int i) {
        this.c = i;
        if (userInfomation == null) {
            userInfomation = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        }
        if (userInfomation != null) {
            float weightOrDefaultValue = userInfomation.getWeightOrDefaultValue();
            if (weightOrDefaultValue > 0.0f) {
                this.e = weightOrDefaultValue;
            }
        }
        ActivityCaloriesCalculateApi activityCaloriesCalculateApi = (ActivityCaloriesCalculateApi) Services.c("BaseSportModel", ActivityCaloriesCalculateApi.class);
        this.d = activityCaloriesCalculateApi;
        activityCaloriesCalculateApi.initUserInfo(bdw.d(userInfomation));
        d(j, i);
        LogUtil.a("Track_CalorieManager", "mStartSportTime ", Long.valueOf(j), " mSportType ", Integer.valueOf(i));
    }

    public void d(long j, int i) {
        ActivityCaloriesCalculateApi activityCaloriesCalculateApi = this.d;
        if (activityCaloriesCalculateApi != null) {
            activityCaloriesCalculateApi.initCalculateCalories(j, i);
        }
    }

    public void d(float f, long j, float f2, float f3) {
        int i = this.c;
        if (i == 260) {
            this.b.b(f, j, this.e);
            this.f12950a.b(f, j, this.e);
        } else {
            this.b.a(this.d, f2, f3, i);
            this.f12950a.a(this.d, f2, f3, this.c);
        }
    }

    public void e(float f) {
        this.b.b(f);
        this.f12950a.b(f);
    }

    public float b() {
        return this.f12950a.c();
    }

    public float c() {
        return this.b.c();
    }

    public void b(float f) {
        this.f12950a.a(f);
    }

    public void a(float f) {
        this.b.a(f);
    }

    public void d(float f) {
        this.f12950a.e(f);
    }

    public void c(float f) {
        this.b.e(f);
    }
}
