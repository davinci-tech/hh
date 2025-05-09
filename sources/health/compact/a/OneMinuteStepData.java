package health.compact.a;

import com.huawei.caloriecalculate.ActivityCaloriesCalculateApi;
import com.huawei.health.manager.util.TimeUtil;
import com.huawei.health.manager.util.UserInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.openalliance.ad.constant.ConfigMapDefValues;
import defpackage.bdw;
import defpackage.bdy;
import defpackage.jdl;
import java.io.Serializable;
import java.util.List;

/* loaded from: classes.dex */
public class OneMinuteStepData implements Serializable {
    private static final long serialVersionUID = 4576504675226376880L;

    /* renamed from: a, reason: collision with root package name */
    private int f13133a;
    private int b;
    private long c;
    private int d;
    private int e;

    public OneMinuteStepData(int i, long j, int i2, int i3, int i4) {
        this.e = i;
        this.c = j;
        this.f13133a = i2;
        this.d = i3;
        this.b = i4;
    }

    public void e(int i, long j, int i2, int i3, int i4) {
        this.e = i;
        this.c = j;
        this.f13133a = i2;
        this.d = i3;
        this.b = i4;
    }

    private boolean d(long j) {
        int a2 = (int) TimeUtil.a(jdl.t(j));
        long j2 = this.c;
        return j2 >= ((long) a2) && j2 < ((long) (a2 + ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL));
    }

    public boolean i() {
        return d(System.currentTimeMillis());
    }

    public long a() {
        return this.c;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(this.c);
        stringBuffer.append(",");
        stringBuffer.append(this.e);
        stringBuffer.append(",");
        stringBuffer.append(this.f13133a);
        stringBuffer.append(",");
        stringBuffer.append(this.d);
        stringBuffer.append(",");
        stringBuffer.append(this.b);
        return stringBuffer.toString();
    }

    public int e() {
        return this.e;
    }

    public void b(int i, int i2) {
        this.f13133a += i;
        this.d += i2;
    }

    public void a(int i) {
        this.b = i;
    }

    public int b() {
        return this.f13133a;
    }

    public boolean h() {
        int i;
        if (this.d > 0) {
            this.b = 20004;
            return true;
        }
        if (this.f13133a > 0 || (i = this.b) == 20005) {
            return true;
        }
        return (i == 20003 || i == 20002) ? false : true;
    }

    public void c(String str, List<HiHealthData> list) {
        b(str, 2, this.f13133a, list);
    }

    public void a(String str, UserInfo userInfo, List<HiHealthData> list) {
        b(str, 4, e(userInfo), list);
    }

    public double e(UserInfo userInfo) {
        double calculateCalories;
        int i = this.b;
        if (i != 20005 && i != 20004 && this.f13133a == 0) {
            return 0.0d;
        }
        ActivityCaloriesCalculateApi activityCaloriesCalculateApi = (ActivityCaloriesCalculateApi) Services.c("BaseSportModel", ActivityCaloriesCalculateApi.class);
        bdw.e eVar = new bdw.e();
        if (userInfo != null) {
            eVar.d(userInfo.b()).c(userInfo.c() / 100.0f).b(userInfo.j()).c(userInfo.e());
        }
        activityCaloriesCalculateApi.initUserInfo(eVar.a());
        long j = this.c * 60000;
        long j2 = 60000 + j;
        float f = this.f13133a / 60.0f;
        int i2 = this.b;
        if (i2 == 20005) {
            activityCaloriesCalculateApi.initCalculateCalories(j, 259);
            calculateCalories = activityCaloriesCalculateApi.calculateCalories(j2);
        } else if (i2 == 20004) {
            activityCaloriesCalculateApi.initCalculateCalories(j, 261);
            calculateCalories = activityCaloriesCalculateApi.calculateCalories(j2);
        } else if (i2 == 20003) {
            activityCaloriesCalculateApi.initCalculateCalories(j, 258);
            calculateCalories = activityCaloriesCalculateApi.calculateCalories(j2, new bdy.c().e(f).e());
        } else {
            activityCaloriesCalculateApi.initCalculateCalories(j, 257);
            calculateCalories = activityCaloriesCalculateApi.calculateCalories(j2, new bdy.c().e(f).e());
        }
        return calculateCalories * 1000.0d;
    }

    public void d(String str, UserInfo userInfo, List<HiHealthData> list) {
        b(str, 3, b(userInfo), list);
    }

    public int b(UserInfo userInfo) {
        if (userInfo == null) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_OneMinuteStepData", "userInfo == null");
            return 0;
        }
        return (int) (CalculateDistanceUtils.a(this.f13133a, userInfo.c()) * 1000.0d);
    }

    public void a(String str, List<HiHealthData> list) {
        int i = this.d;
        if (i > 0) {
            b(str, 5, i, list);
        }
    }

    public void b(String str, List<HiHealthData> list) {
        int i = this.b;
        b(str, i, i, list);
    }

    private void b(String str, int i, double d, List<HiHealthData> list) {
        long j = this.c * 60000;
        if (!HiValidatorUtil.b(i, d)) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_OneMinuteStepData", "checkLocalDataValue false");
            return;
        }
        if (str == null || list == null) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_OneMinuteStepData", "(deviceUuid == null) || (dataList == null)");
            return;
        }
        HiHealthData hiHealthData = new HiHealthData(i);
        hiHealthData.setTimeInterval(j, 60000 + j);
        hiHealthData.setValue(d);
        hiHealthData.setDeviceUuid(str);
        list.add(hiHealthData);
    }

    public int c() {
        return this.d;
    }

    public int d() {
        return this.b;
    }
}
