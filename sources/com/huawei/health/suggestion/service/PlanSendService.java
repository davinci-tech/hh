package com.huawei.health.suggestion.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.amap.api.maps.model.MyLocationStyle;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.service.PlanSendService;
import com.huawei.health.suggestion.ui.callback.UiPagingCallback;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.utils.RiskBiAnalytics;
import defpackage.ase;
import defpackage.ash;
import defpackage.cun;
import defpackage.fyc;
import defpackage.fyw;
import defpackage.gij;
import defpackage.gim;
import defpackage.jdx;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public class PlanSendService extends Service {
    private final IBinder b = new a(null);

    /* renamed from: a, reason: collision with root package name */
    private Handler f3042a = new b(Looper.getMainLooper(), this);
    private AtomicInteger e = new AtomicInteger(0);

    @Override // android.app.Service
    public void onCreate() {
        ReleaseLogUtil.e("Suggestion_PlanSendService", "onCreate");
        super.onCreate();
        f();
    }

    private void f() {
        jdx.b(new Runnable() { // from class: fjk
            @Override // java.lang.Runnable
            public final void run() {
                PlanSendService.this.d();
            }
        });
    }

    public /* synthetic */ void d() {
        ReleaseLogUtil.e("Suggestion_PlanSendService", "send plan info to device enter.");
        this.e.set(0);
        this.f3042a.removeMessages(1);
        this.f3042a.sendEmptyMessageDelayed(1, TimeUnit.MINUTES.toMillis(3L));
        if (!gij.c()) {
            ReleaseLogUtil.d("Suggestion_PlanSendService", "sendPlanToDevice failed with device not connected or not support.");
            return;
        }
        IntPlan c = c();
        if (c == null) {
            ReleaseLogUtil.d("Suggestion_PlanSendService", "sendPlanToDevice failed with null plan.");
            a();
            return;
        }
        IntPlan.PlanType planType = c.getPlanType();
        if (planType != null && e() != IntPlan.PlanType.NA_PLAN.getType() && planType.getType() != e()) {
            a();
            ReleaseLogUtil.e("Suggestion_PlanSendService", "sendPlanToDevice planType:", Integer.valueOf(planType.getType()), "getNeedFinishPlanType:", Integer.valueOf(e()));
        }
        a(c);
    }

    private int e() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            ReleaseLogUtil.d("Suggestion_PlanSendService", "get plan api failed.");
            return IntPlan.PlanType.NA_PLAN.getType();
        }
        return planApi.getNeedFinishPlanType();
    }

    private void a(IntPlan intPlan) {
        if (ase.n(intPlan)) {
            ReleaseLogUtil.d("Suggestion_PlanSendService", "plan current week need to update, please check.");
        } else {
            c(intPlan, 3);
        }
    }

    private void a() {
        final PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            ReleaseLogUtil.d("Suggestion_PlanSendService", "get plan api failed.");
            return;
        }
        final DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "Suggestion_PlanSendService");
        if (deviceInfo == null) {
            ReleaseLogUtil.d("Suggestion_PlanSendService", "deviceInfo is null.");
        } else if (planApi.isNeedSendFinishPlanToDevice(deviceInfo.getDeviceUdid())) {
            ReleaseLogUtil.d("Suggestion_PlanSendService", "checkNeedToSendFinishCmd isNeedSendFinishPlanToDevice.");
            gim.c(IntPlan.PlanType.NA_PLAN.getType()).a(planApi.getNeedSendFinishPlanId(), planApi.getNeedFinishPlanType(), true, new IBaseResponseCallback() { // from class: fji
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    PlanSendService.e(PlanApi.this, deviceInfo, i, obj);
                }
            });
        }
    }

    public static /* synthetic */ void e(PlanApi planApi, DeviceInfo deviceInfo, int i, Object obj) {
        ReleaseLogUtil.d("Suggestion_PlanSendService", "sendFinishPlan finish is.", Integer.valueOf(i), obj);
        if (i != 1) {
            planApi.updateSendFinishPlanDevice(deviceInfo.getDeviceUdid());
        }
        int intValue = obj instanceof Integer ? ((Integer) obj).intValue() : 0;
        if (i == 0 && intValue == 0) {
            return;
        }
        ReleaseLogUtil.d("Suggestion_PlanSendService", "sendFinishPlan errorCode is :", Integer.valueOf(i));
        RiskBiAnalytics.c(RiskBiAnalytics.FitPLANRiskType.RISK_TYPE_SEND_PLAN_DEVICE.value(), "sendFinishPlan errorCode is :", Integer.valueOf(i), "responseCode is :", Integer.valueOf(intValue));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(IntPlan intPlan, int i) {
        if (i <= 0) {
            ReleaseLogUtil.d("Suggestion_PlanSendService", "planHandleShake failed with current try times :", Integer.valueOf(i));
            RiskBiAnalytics.c(RiskBiAnalytics.FitPLANRiskType.RISK_TYPE_SEND_PLAN_DEVICE.value(), "planHandleShake fail");
            this.f3042a.sendEmptyMessage(1);
            return;
        }
        ReleaseLogUtil.d("Suggestion_PlanSendService", "begin planHandleShake current try:", Integer.valueOf(i), "plan:", intPlan.toString(), "planType", Integer.valueOf(intPlan.getPlanType().getType()));
        if (IntPlan.PlanType.AI_RUN_PLAN.equals(intPlan.getPlanType())) {
            if (!TextUtils.isEmpty(ash.b("SEND_SUCCESS"))) {
                a(intPlan, i);
                return;
            } else {
                e(intPlan, 2, i);
                return;
            }
        }
        e(intPlan, 2, i);
    }

    private void a(final IntPlan intPlan, final int i) {
        gim.c(IntPlan.PlanType.AI_RUN_PLAN.getType()).d(intPlan.getCompatiblePlan(), true, new IBaseResponseCallback() { // from class: fjm
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                PlanSendService.this.b(i, intPlan, i2, obj);
            }
        });
    }

    public /* synthetic */ void b(int i, IntPlan intPlan, int i2, Object obj) {
        ReleaseLogUtil.d("Suggestion_PlanSendService", "end planHandleShake with response errorCode is:", Integer.valueOf(i2), "response object is", obj, " tryTimes:", Integer.valueOf(i));
        int intValue = obj instanceof Integer ? ((Integer) obj).intValue() : 1;
        if (i2 != 0) {
            RiskBiAnalytics.c(RiskBiAnalytics.FitPLANRiskType.RISK_TYPE_SEND_PLAN_DEVICE.value(), "sendPlanHandleShake errorCode is :", Integer.valueOf(i2));
            c(intPlan, i - 1);
        } else if (intValue == 1) {
            RiskBiAnalytics.c(RiskBiAnalytics.FitPLANRiskType.RISK_TYPE_SEND_PLAN_DEVICE.value(), "sendPlanHandleShake responseCode is :", Integer.valueOf(intValue));
            c(intPlan, i - 1);
        } else if (intValue == 3 || intValue == 2) {
            e(intPlan, intValue, i);
        } else {
            this.f3042a.sendEmptyMessage(1);
        }
    }

    private void e(IntPlan intPlan, int i, int i2) {
        boolean x = fyw.x(intPlan);
        ReleaseLogUtil.e("Suggestion_PlanSendService", "pushPlanData pushType ", Integer.valueOf(i), " tryTimes ", Integer.valueOf(i2), " isPlanOverdue ", Boolean.valueOf(x));
        if (x) {
            return;
        }
        c(intPlan, new AnonymousClass2(i2, intPlan, i));
    }

    /* renamed from: com.huawei.health.suggestion.service.PlanSendService$2, reason: invalid class name */
    public class AnonymousClass2 extends UiCallback<List<FitWorkout>> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ IntPlan f3043a;
        final /* synthetic */ int c;
        final /* synthetic */ int e;

        AnonymousClass2(int i, IntPlan intPlan, int i2) {
            this.c = i;
            this.f3043a = intPlan;
            this.e = i2;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            ReleaseLogUtil.c("Suggestion_PlanSendService", "pushPlanData failed with get workout failed current ", Integer.valueOf(this.c));
            RiskBiAnalytics.c(RiskBiAnalytics.FitPLANRiskType.RISK_TYPE_SEND_PLAN_DEVICE.value(), "getWorkouts errorCode is :", Integer.valueOf(i), "errorInfo is :", str);
            PlanSendService.this.c(this.f3043a, this.c - 1);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<FitWorkout> list) {
            if (list == null) {
                list = Collections.EMPTY_LIST;
            }
            final List<FitWorkout> list2 = list;
            ReleaseLogUtil.e("Suggestion_PlanSendService", "pushPlanData with get workout:", Integer.valueOf(list2.size()), " current try times:", Integer.valueOf(this.c));
            final IntPlan intPlan = this.f3043a;
            final int i = this.e;
            final int i2 = this.c;
            jdx.b(new Runnable() { // from class: fjn
                @Override // java.lang.Runnable
                public final void run() {
                    PlanSendService.AnonymousClass2.this.d(intPlan, list2, i, i2);
                }
            });
        }

        public /* synthetic */ void d(IntPlan intPlan, List list, int i, int i2) {
            PlanSendService.this.a(intPlan, (List<FitWorkout>) list, i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final IntPlan intPlan, List<FitWorkout> list, int i, final int i2) {
        gim.c(intPlan.getPlanType().getType()).d(intPlan, list, i, true, new IBaseResponseCallback() { // from class: fjj
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i3, Object obj) {
                PlanSendService.this.a(i2, intPlan, i3, obj);
            }
        });
    }

    public /* synthetic */ void a(int i, IntPlan intPlan, int i2, Object obj) {
        ReleaseLogUtil.d("Suggestion_PlanSendService", "end pushPlanData with response errorCode is:", Integer.valueOf(i2), "response object is", obj, " tryTimes:", Integer.valueOf(i));
        int intValue = obj instanceof Integer ? ((Integer) obj).intValue() : 0;
        if (i2 != 0) {
            RiskBiAnalytics.c(RiskBiAnalytics.FitPLANRiskType.RISK_TYPE_SEND_PLAN_DEVICE.value(), "pushPlanData errorCode is :", Integer.valueOf(i2), "responseCode is :", Integer.valueOf(intValue));
            c(intPlan, i - 1);
        } else {
            ase.q(intPlan);
            if (IntPlan.PlanType.AI_RUN_PLAN.equals(intPlan.getPlanType())) {
                ash.a("SEND_SUCCESS", "true");
            }
            this.f3042a.sendEmptyMessage(1);
        }
    }

    private void c(IntPlan intPlan, UiCallback<List<FitWorkout>> uiCallback) {
        fyc.d(intPlan, uiCallback);
    }

    private IntPlan c() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            return null;
        }
        final IntPlan[] intPlanArr = {null};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        planApi.b(new UiPagingCallback<IntPlan>() { // from class: com.huawei.health.suggestion.service.PlanSendService.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccessFirst(IntPlan intPlan) {
                if (intPlan != null && (intPlan.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN) || IntPlan.PlanType.AI_FITNESS_PLAN.equals(intPlan.getPlanType()))) {
                    intPlanArr[0] = intPlan;
                }
                countDownLatch.countDown();
            }

            @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
            public void onFailureFirst(int i, String str) {
                ReleaseLogUtil.d("Suggestion_PlanSendService", "getCurrentIntPlan errorCode", Integer.valueOf(i), MyLocationStyle.ERROR_INFO, str);
                RiskBiAnalytics.c(RiskBiAnalytics.FitPLANRiskType.RISK_TYPE_SEND_PLAN_DEVICE.value(), "getCurrentIntPlan failure errorCode is :", Integer.valueOf(i), "errorInfo :", str);
                countDownLatch.countDown();
            }
        });
        try {
            if (!countDownLatch.await(5000L, TimeUnit.MILLISECONDS)) {
                LogUtil.h("Suggestion_PlanSendService", "getIntelligentRunPlan wait timeout");
            }
        } catch (InterruptedException unused) {
            LogUtil.b("Suggestion_PlanSendService", "interrupted while waiting for getDietRecordList");
        }
        return intPlanArr[0];
    }

    public void b() {
        if (this.e.get() > 0) {
            f();
        } else {
            this.f3042a.removeMessages(1);
            stopSelf();
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        LogUtil.a("Suggestion_PlanSendService", "onStartCommand");
        this.e.incrementAndGet();
        this.f3042a.sendEmptyMessage(1);
        return 2;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.b;
    }

    static class a extends Binder {
        private a() {
        }

        /* synthetic */ a(AnonymousClass2 anonymousClass2) {
            this();
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ReleaseLogUtil.e("Suggestion_PlanSendService", "onDestroy");
    }

    static class b extends BaseHandler<PlanSendService> {
        b(Looper looper, PlanSendService planSendService) {
            super(looper, planSendService);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: ayi_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(PlanSendService planSendService, Message message) {
            if (message.what != 1) {
                return;
            }
            planSendService.b();
        }
    }
}
