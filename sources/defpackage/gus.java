package defpackage;

import com.huawei.caloriecalculate.ActivityCaloriesCalculateApi;

/* loaded from: classes4.dex */
public class gus extends gur {
    @Override // defpackage.gur
    protected float b(ActivityCaloriesCalculateApi activityCaloriesCalculateApi, bdy bdyVar) {
        return Math.max(activityCaloriesCalculateApi != null ? (float) activityCaloriesCalculateApi.calculateCalories(System.currentTimeMillis(), bdyVar) : 0.0f, 0.0f);
    }

    @Override // defpackage.gur
    protected String e() {
        return "Track_ActiveCalorieCalculate";
    }
}
