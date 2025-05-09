package defpackage;

import android.text.TextUtils;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.callback.JudgeCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class ghq {
    public static void e(final JudgeCallback<Boolean> judgeCallback) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            if (judgeCallback != null) {
                judgeCallback.onJudgeCallback(false);
            }
            LogUtil.b("OperationActivity_PlanDataUtil", "initData, getRemindTime : planApi is null.");
            return;
        }
        planApi.getCurrentIntPlan(new UiCallback<IntPlan>() { // from class: ghq.2
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.b("OperationActivity_PlanDataUtil", "No current plan now.", Integer.valueOf(i), " ", str);
                ghq.d(JudgeCallback.this, false);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(IntPlan intPlan) {
                Object[] objArr = new Object[2];
                objArr[0] = "has current plan =";
                objArr[1] = Boolean.valueOf(intPlan != null);
                LogUtil.b("OperationActivity_PlanDataUtil", objArr);
                ghq.d(JudgeCallback.this, intPlan != null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(final JudgeCallback<Boolean> judgeCallback, final boolean z) {
        HandlerExecutor.e(new Runnable() { // from class: ghq.5
            @Override // java.lang.Runnable
            public void run() {
                JudgeCallback judgeCallback2 = JudgeCallback.this;
                if (judgeCallback2 != null) {
                    judgeCallback2.onJudgeCallback(Boolean.valueOf(z));
                }
            }
        });
    }

    public static void d(JudgeCallback<IntPlan> judgeCallback) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            if (judgeCallback != null) {
                judgeCallback.onJudgeCallback(null);
            }
            LogUtil.b("OperationActivity_PlanDataUtil", "initData, getRemindTime : planApi is null.");
        } else {
            IntPlan currentIntPlan = planApi.getCurrentIntPlan();
            if (judgeCallback != null) {
                judgeCallback.onJudgeCallback(currentIntPlan);
            }
            planApi.getCurrentIntPlan(new UiCallback<IntPlan>() { // from class: ghq.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.b("OperationActivity_PlanDataUtil", "No current plan now.", Integer.valueOf(i), " ", str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(IntPlan intPlan) {
                    Object[] objArr = new Object[2];
                    objArr[0] = "has current plan =";
                    objArr[1] = Boolean.valueOf(intPlan != null);
                    LogUtil.b("OperationActivity_PlanDataUtil", objArr);
                }
            });
        }
    }

    public static mmw a(String str, List<mmw> list) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("OperationActivity_PlanDataUtil", "selectPlan planTempId is null");
            return null;
        }
        if (str.contains("_")) {
            String[] split = str.split("_");
            if (koq.b(split, 1)) {
                return null;
            }
            str2 = split[0];
            str = split[1];
        } else {
            str2 = null;
        }
        try {
            int parseInt = Integer.parseInt(str);
            int parseInt2 = !TextUtils.isEmpty(str2) ? Integer.parseInt(str2) : -1;
            Iterator<mmw> it = list.iterator();
            while (it.hasNext()) {
                mmw next = it.next();
                if (next.c() == parseInt && (parseInt2 == -1 || parseInt2 == next.b())) {
                    return next;
                }
            }
            return null;
        } catch (NumberFormatException e) {
            LogUtil.b("OperationActivity_PlanDataUtil", "NumberFormatException : ", LogAnonymous.b((Throwable) e));
            return null;
        }
    }
}
