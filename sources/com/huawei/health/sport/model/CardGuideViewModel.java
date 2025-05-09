package com.huawei.health.sport.model;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.model.CardGuideViewModel;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import defpackage.grz;
import defpackage.gsd;
import defpackage.gsi;
import defpackage.ixx;
import defpackage.koq;
import defpackage.kot;
import defpackage.quh;
import defpackage.qul;
import health.compact.a.Services;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class CardGuideViewModel extends ViewModel {
    private WorkoutRecord c;
    private MutableLiveData<b> d = new MutableLiveData<>();

    public void b() {
        LogUtil.a("CardGuideViewModel", "getIntPlan start");
        final PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("CardGuideViewModel", "getCurrentPlan : planApi is null.");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: few
                @Override // java.lang.Runnable
                public final void run() {
                    CardGuideViewModel.this.e(planApi);
                }
            });
        }
    }

    public /* synthetic */ void e(PlanApi planApi) {
        planApi.getCurrentIntPlan(new UiCallback<IntPlan>() { // from class: com.huawei.health.sport.model.CardGuideViewModel.1
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.h("CardGuideViewModel", "errorCode = ", Integer.valueOf(i), " errorInfo = ", str);
                CardGuideViewModel.this.a();
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(IntPlan intPlan) {
                if (intPlan != null && intPlan.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN) {
                    LogUtil.h("CardGuideViewModel", "have ai fitness plan");
                } else {
                    LogUtil.a("CardGuideViewModel", "don't have ai fitness plan");
                    CardGuideViewModel.this.a();
                }
            }
        });
    }

    public MutableLiveData<b> d() {
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (!gsd.a()) {
            LogUtil.h("CardGuideViewModel", "getFatReductionShapingState is false ");
        } else {
            kot.a().b(new ResponseCallback() { // from class: ffa
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    CardGuideViewModel.this.a(i, (gsi) obj);
                }
            });
        }
    }

    public /* synthetic */ void a(int i, gsi gsiVar) {
        int g = gsiVar != null ? gsiVar.g() : 0;
        if (g != 1) {
            LogUtil.a("CardGuideViewModel", "getWeightManager WeightManagerType is : ", Integer.valueOf(g));
        } else {
            e();
        }
    }

    private void e() {
        final int b2 = DateFormatUtil.b(System.currentTimeMillis());
        LogUtil.a("CardGuideViewModel", "getDietRecord dayFormat ", Integer.valueOf(b2));
        ThreadPoolManager.d().execute(new Runnable() { // from class: fez
            @Override // java.lang.Runnable
            public final void run() {
                CardGuideViewModel.this.b(b2);
            }
        });
    }

    public /* synthetic */ void b(int i) {
        grz.c(i, i, 3000L, new ResponseCallback() { // from class: ffb
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                CardGuideViewModel.this.d(i2, (List) obj);
            }
        });
    }

    public /* synthetic */ void d(int i, List list) {
        LogUtil.a("CardGuideViewModel", "getDietData errorCode ", Integer.valueOf(i), " list ", list);
        if (koq.b(list)) {
            LogUtil.h("CardGuideViewModel", "getDietRecord list is empty ");
            return;
        }
        quh quhVar = (quh) list.get(0);
        if (quhVar == null) {
            LogUtil.h("CardGuideViewModel", "getDietRecord is null ");
        } else {
            a(quhVar);
        }
    }

    private void a(quh quhVar) {
        quhVar.d();
        List<qul> a2 = quhVar.a();
        float round = this.c != null ? Math.round(r0.acquireActualCalorie() / 1000.0f) : 0.0f;
        b bVar = new b();
        if (koq.b(a2)) {
            LogUtil.h("CardGuideViewModel", "meals is empty");
            b(bVar, round);
            return;
        }
        c(a2, bVar);
        if (!bVar.f()) {
            LogUtil.h("CardGuideViewModel", "this time has check in all meal of this time ,return");
        } else {
            LogUtil.a("CardGuideViewModel", "setDataToCard");
            b(bVar, round);
        }
    }

    private void c(List<qul> list, b bVar) {
        for (qul qulVar : list) {
            if (qulVar != null) {
                if (koq.b(qulVar.c())) {
                    LogUtil.h("CardGuideViewModel", "getFoodDescriptors is empty");
                } else {
                    int h = qulVar.h();
                    if (h == 10) {
                        bVar.d(true);
                    } else if (h == 20) {
                        bVar.b(true);
                    } else if (h == 30) {
                        bVar.c(true);
                    } else {
                        LogUtil.h("CardGuideViewModel", "handleFoodAction WhichMeal = ", Integer.valueOf(qulVar.h()));
                    }
                }
            }
        }
    }

    private void b(b bVar, float f) {
        bVar.o();
        bVar.e(f);
        this.d.postValue(bVar);
    }

    public void d(WorkoutRecord workoutRecord) {
        this.c = workoutRecord;
    }

    public void a(LifecycleOwner lifecycleOwner) {
        LogUtil.a("CardGuideViewModel", "removeObserve");
        this.d.removeObservers(lifecycleOwner);
    }

    public void c(int i) {
        HashMap hashMap = new HashMap(10);
        WorkoutRecord workoutRecord = this.c;
        int acquireCategory = workoutRecord != null ? workoutRecord.acquireCategory() : 0;
        if (acquireCategory == 0) {
            hashMap.put(BleConstants.SPORT_TYPE, 10001);
        } else {
            hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(acquireCategory));
        }
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("entrance", "1");
        LogUtil.a("CardGuideViewModel", "uploadBiEvent, SPORT_CALORIE_CONSUMPTION_2160140: ", hashMap.toString());
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SPORT_CALORIE_CONSUMPTION_2160140.value(), hashMap, 0);
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        public boolean f2991a;
        public int b;
        public boolean c;
        public boolean d;
        public float e;
        public int f;
        public int j;

        public float a() {
            return this.e;
        }

        public void e(float f) {
            this.e = f;
        }

        public boolean j() {
            return this.f2991a;
        }

        public void d(boolean z) {
            this.f2991a = z;
        }

        public boolean g() {
            return this.c;
        }

        public void b(boolean z) {
            this.c = z;
        }

        public boolean e() {
            return this.d;
        }

        public void c(boolean z) {
            this.d = z;
        }

        public int c() {
            return this.j;
        }

        public void d(int i) {
            this.j = i;
        }

        public int b() {
            return this.f;
        }

        public void e(int i) {
            this.f = i;
        }

        public int d() {
            return this.b;
        }

        public void a(int i) {
            this.b = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void o() {
            n();
            l();
            i();
        }

        private void n() {
            if (j()) {
                d(1);
            } else if (h() <= 10) {
                d(2);
            } else {
                d(3);
            }
        }

        private void l() {
            if (g()) {
                e(1);
            } else if (h() <= 20) {
                e(2);
            } else {
                e(3);
            }
        }

        private void i() {
            if (e()) {
                a(1);
            } else if (h() <= 30) {
                a(2);
            } else {
                a(3);
            }
        }

        private int h() {
            int i = Calendar.getInstance().get(11);
            if (i <= 9) {
                return 10;
            }
            return i <= 13 ? 20 : 30;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean f() {
            if (j() && g() && e()) {
                LogUtil.a("CardGuideViewModel", "has all meal");
                return false;
            }
            int h = h();
            if (h == 10 && j()) {
                LogUtil.a("CardGuideViewModel", "has morning");
                return false;
            }
            if (h == 20 && j() && g()) {
                LogUtil.a("CardGuideViewModel", "has morning and noon");
                return false;
            }
            if (h != 30 || !j() || !g() || !e()) {
                return true;
            }
            LogUtil.a("CardGuideViewModel", "has morning, noon and evening");
            return false;
        }
    }
}
