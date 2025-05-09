package defpackage;

import android.text.TextUtils;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.harmonyos.interwork.base.ability.IInitCallBack;
import com.huawei.harmonyos.interwork.base.content.Intent;
import com.huawei.health.distributedservice.api.DistributedApi;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.onehop.fasdk.model.DeviceConnectState;
import com.huawei.pluginfitnessadvice.FitWorkout;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes4.dex */
public class fnh implements IInitCallBack {

    /* renamed from: a, reason: collision with root package name */
    private String f12570a;
    private long b;
    private UiCallback<Integer> c;
    private FitWorkout d;
    private int e;

    public fnh(FitWorkout fitWorkout, String str, long j, UiCallback<Integer> uiCallback) {
        this.d = fitWorkout;
        this.f12570a = str;
        this.b = j;
        this.c = uiCallback;
    }

    public void b(int i) {
        this.e = i;
    }

    @Override // com.huawei.harmonyos.interwork.base.ability.IInitCallBack
    public void onInitSuccess(final String str) {
        if (this.d == null) {
            LogUtil.h("TrainDetailFaInitCallback", "onInitSuccess mFitWorkout is null");
            return;
        }
        LogUtil.a("TrainDetailFaInitCallback", "MyFAInitCallback onInitSuccess the deviceId: ", CommonUtil.l(str));
        if (TextUtils.isEmpty(this.d.acquireId())) {
            ReleaseLogUtil.c("TrainDetailFaInitCallback", "mWorkoutId == null");
            return;
        }
        LogUtil.a("TrainDetailFaInitCallback", "the planId:", this.f12570a);
        if (TextUtils.isEmpty(this.f12570a)) {
            c(str, null);
            return;
        }
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("TrainDetailFaInitCallback", "intPlanApi is null.");
        } else {
            planApi.getCurrentIntPlan(new UiCallback<IntPlan>() { // from class: fnh.1
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str2) {
                    LogUtil.h("TrainDetailFaInitCallback", "get CurrentPlan onFailure ,the code is:", Integer.valueOf(i), "the msg is:", str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(final IntPlan intPlan) {
                    if (intPlan == null) {
                        LogUtil.h("TrainDetailFaInitCallback", "the intPlan is null,can't transform planMessage with FA");
                    } else {
                        HandlerExecutor.e(new Runnable() { // from class: fnh.1.5
                            @Override // java.lang.Runnable
                            public void run() {
                                fnh.this.c(str, intPlan);
                            }
                        });
                    }
                }
            });
        }
    }

    @Override // com.huawei.harmonyos.interwork.base.ability.IInitCallBack
    public void onInitFailure(String str, int i) {
        LogUtil.h("TrainDetailFaInitCallback", "MyFAInitCallback onInitFailure,the deviceId: ", CommonUtil.l(str), " errCode: ", Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, IntPlan intPlan) {
        String format;
        DeviceConnectState deviceConnectState;
        LogUtil.a("TrainDetailFaInitCallback", "the startRemoteAbility executor start...");
        Intent intent = new Intent();
        if (intPlan == null || !intPlan.getPlanId().equals(this.f12570a)) {
            format = String.format(Locale.ENGLISH, "homevision://com.huawei.aifitness/course?source=1&courseId=%s&timestamp=%d&userId=-2", this.d.acquireId(), Long.valueOf(System.currentTimeMillis()));
        } else {
            WorkoutRecord workoutRecord = new WorkoutRecord();
            workoutRecord.setPlanTrainDate(ggl.b(this.b));
            format = String.format(Locale.ENGLISH, "homevision://com.huawei.aifitness/course?source=1&courseId=%s&timestamp=%d&planId=%s&planType=%d&weekSequence=%d&trainingDate=%d&userId=-2", this.d.acquireId(), Long.valueOf(System.currentTimeMillis()), this.f12570a, Integer.valueOf(intPlan.getPlanType().getType()), Integer.valueOf(ase.a(workoutRecord, intPlan)[0]), Long.valueOf(this.b));
        }
        ReleaseLogUtil.e("TrainDetailFaInitCallback", "MyFAIConnectCallBack connect startAbility intent schemeUrl: ", format);
        intent.setParam("scheme_url", format);
        DistributedApi distributedApi = (DistributedApi) Services.c("DistributedService", DistributedApi.class);
        int startRemoteAbility = distributedApi.startRemoteAbility(BaseApplication.e(), str, intent);
        HashMap hashMap = new HashMap(10);
        hashMap.put("workout_id", this.d.acquireId());
        hashMap.put("workout_name", this.d.acquireName());
        if (startRemoteAbility == 0) {
            gge.e(AnalyticsValue.FA_HOP_SUCCESS.value(), hashMap);
            fni.e(this.d.acquireId(), this.d.acquireName(), "2");
        } else {
            hashMap.put("result_code", Integer.valueOf(startRemoteAbility));
            gge.e(AnalyticsValue.FA_HOP_FAILUE.value(), hashMap);
        }
        if (startRemoteAbility == 0) {
            deviceConnectState = DeviceConnectState.CONNECTED;
        } else {
            deviceConnectState = DeviceConnectState.FAILURE;
        }
        distributedApi.updateConnectStatus(this.e, str, deviceConnectState);
        ReleaseLogUtil.e("TrainDetailFaInitCallback", "startAbilityResult:", Integer.valueOf(startRemoteAbility));
        UiCallback<Integer> uiCallback = this.c;
        if (uiCallback != null) {
            uiCallback.onSuccess(Integer.valueOf(startRemoteAbility));
        }
        LogUtil.a("TrainDetailFaInitCallback", "the startRemoteAbility executor end...");
    }
}
