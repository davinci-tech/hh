package com.huawei.health.manager;

import android.os.Bundle;
import com.huawei.caloriecalculate.ActivityCaloriesCalculateApi;
import com.huawei.health.manager.util.UserInfo;
import com.huawei.hwfoundationmodel.trackmodel.StepRateData;
import defpackage.bdw;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class LiteAutoTrackDataManager {

    /* renamed from: a, reason: collision with root package name */
    private ActivityCaloriesCalculateApi f2770a;
    private long b;
    private int c;
    private int d;
    private long e;
    private int i;
    private float j = 0.5f;

    private void e() {
        float f;
        UserInfo d = UserInfo.d();
        bdw.e eVar = new bdw.e();
        if (d != null) {
            f = d.c() / 100.0f;
            eVar.d(d.b()).c(f).b(d.j()).c(d.e());
        } else {
            f = 1.7f;
        }
        ActivityCaloriesCalculateApi activityCaloriesCalculateApi = (ActivityCaloriesCalculateApi) Services.c("BaseSportModel", ActivityCaloriesCalculateApi.class);
        this.f2770a = activityCaloriesCalculateApi;
        activityCaloriesCalculateApi.initUserInfo(eVar.a());
        float f2 = f * 0.42f;
        this.j = f2;
        ReleaseLogUtil.e("Track_LiteAutoTrackDataManager", "init mUserStride is ", Float.valueOf(f2));
    }

    public void e(int i) {
        this.c = 180000 + i;
        ReleaseLogUtil.e("Track_LiteAutoTrackDataManager", "init startTime is ", Integer.valueOf(i));
        e();
    }

    public void b(int i) {
        this.e = System.currentTimeMillis();
        this.d = i;
        ReleaseLogUtil.e("Track_LiteAutoTrackDataManager", "pre sport save steps base step is", Integer.valueOf(i));
    }

    public void d(int i) {
        this.b = System.currentTimeMillis() - this.e;
        int i2 = this.d;
        if (i2 > i) {
            ReleaseLogUtil.e("Track_LiteAutoTrackDataManager", "day is past one day");
            this.i = i;
        } else {
            this.i = i - i2;
            ReleaseLogUtil.e("Track_LiteAutoTrackDataManager", "pre sport total steps ", Integer.valueOf(i));
        }
    }

    public Bundle akm_() {
        float f;
        Bundle bundle = new Bundle();
        bundle.putLong("autoTrackTime", this.c);
        long currentTimeMillis = System.currentTimeMillis();
        int round = Math.round(this.i * (this.c / 180000.0f));
        ActivityCaloriesCalculateApi activityCaloriesCalculateApi = this.f2770a;
        if (activityCaloriesCalculateApi != null) {
            activityCaloriesCalculateApi.initCalculateCalories(currentTimeMillis - this.c, -1);
            f = ((float) this.f2770a.calculateCaloriesFromSteps(currentTimeMillis, round)) * 1000.0f;
        } else {
            f = 0.0f;
        }
        bundle.putParcelableArrayList("autoTrackStepList", d(currentTimeMillis));
        bundle.putFloat("autoTrackCalories", f);
        bundle.putInt("autoTrackStep", round);
        int round2 = Math.round(round * this.j);
        bundle.putInt("autoTrackDistance", round2);
        ReleaseLogUtil.e("Track_LiteAutoTrackDataManager", "constructBundle total time is", Integer.valueOf(this.c), "distance is ", Integer.valueOf(round2), "step is ", Integer.valueOf(this.i), "calorie is ", Float.valueOf(f));
        return bundle;
    }

    private ArrayList<StepRateData> d(long j) {
        ArrayList<StepRateData> arrayList = new ArrayList<>(5);
        long j2 = j - this.c;
        int round = Math.round(this.i / ((this.b / 1000.0f) / 60.0f));
        while (true) {
            j2 += 60000;
            if (j2 > j) {
                return arrayList;
            }
            arrayList.add(new StepRateData(j2, round));
        }
    }
}
