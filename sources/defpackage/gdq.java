package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.huawei.basefitnessadvice.model.PlanRecord;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.receiver.NetworkStateReceiver;
import com.huawei.health.suggestion.ui.TrainReportActivity;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;

/* loaded from: classes4.dex */
public class gdq {
    private static boolean d = false;
    private static volatile gdq e;

    /* renamed from: a, reason: collision with root package name */
    private NetworkStateReceiver f12772a;
    private Context b = arx.b();

    public static gdq b() {
        if (e == null) {
            synchronized (gdq.class) {
                if (e == null) {
                    e = new gdq();
                }
            }
        }
        return e;
    }

    private gdq() {
    }

    public void c() {
        if (e()) {
            return;
        }
        b(true);
        this.f12772a = new NetworkStateReceiver();
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.setPriority(Integer.MAX_VALUE);
        this.b.registerReceiver(this.f12772a, intentFilter);
    }

    public void c(String str) {
        if (gdr.e(str) == 0) {
            a(str);
        } else if (gdr.e(str) == 3) {
            b(str);
        } else {
            LogUtil.h("Suggestion_PlanSwitchProxy", "planType unSupport");
        }
    }

    private void b(String str) {
        LogUtil.a("Suggestion_PlanSwitchProxy", "switchToFitnessPlanReport");
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("Suggestion_PlanSwitchProxy", "switchToFitnessPlanReport, getPlanProgress : planApi is null.");
            return;
        }
        planApi.setPlanType(3);
        PlanRecord planProgress = planApi.getPlanProgress(str, false);
        if (planProgress == null) {
            LogUtil.h("Suggestion_PlanSwitchProxy", "planRecord is null and planId = ", str);
            return;
        }
        if (planProgress.acquireWorkoutTimes() > 0) {
            try {
                LogUtil.a("Suggestion_PlanSwitchProxy", "go to TrainReportActivity");
                Intent intent = new Intent(arx.b(), (Class<?>) TrainReportActivity.class);
                intent.setFlags(268435456);
                intent.putExtra("plan", planApi.getjoinedPlanById(str));
                intent.putExtra("finish_plan", true);
                this.b.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("Suggestion_PlanSwitchProxy", "switchToFitnessPlanReport ActivityNotFoundException.");
            }
        }
    }

    private void a(String str) {
        LogUtil.a("Suggestion_PlanSwitchProxy", "switchToRunPlanReport");
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("Suggestion_PlanSwitchProxy", "switchToRunPlanReport, getPlanProgress : planApi is null.");
            return;
        }
        planApi.setPlanType(0);
        PlanRecord planProgress = planApi.getPlanProgress(str, true);
        if (planProgress == null) {
            LogUtil.h("Suggestion_PlanSwitchProxy", "PlanRecord is null when planId is ", str);
            return;
        }
        if (planProgress.acquireWorkoutTimes() != 0) {
            try {
                Intent intent = new Intent(arx.b(), (Class<?>) TrainReportActivity.class);
                intent.setFlags(268435456);
                intent.putExtra("plan", planApi.getjoinedPlanById(str));
                intent.putExtra("finish_plan", true);
                this.b.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("Suggestion_PlanSwitchProxy", "switchToRunPlanReport ActivityNotFoundException.");
            }
        }
    }

    private static boolean e() {
        return d;
    }

    private static void b(boolean z) {
        d = z;
    }
}
