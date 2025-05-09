package defpackage;

import androidx.core.util.Consumer;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.PlanInfo;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.plan.model.PlanStatistics;
import com.huawei.health.suggestion.ui.callback.UiPagingCallback;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.up.model.UserInfomation;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class gbg {
    private int c = -1;

    public void e(final Consumer<Plan> consumer) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: gbl
            @Override // java.lang.Runnable
            public final void run() {
                gbg.this.f(consumer);
            }
        });
    }

    public void c(final Consumer<Plan> consumer) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: gbm
            @Override // java.lang.Runnable
            public final void run() {
                gbg.this.g(consumer);
            }
        });
    }

    public void b(final Consumer<PlanStatistics> consumer, final int i) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: gbn
            @Override // java.lang.Runnable
            public final void run() {
                gbg.this.e(consumer, i);
            }
        });
    }

    public void a(final Consumer<List<PlanInfo>> consumer) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: gbf
            @Override // java.lang.Runnable
            public final void run() {
                gbg.this.b(consumer);
            }
        });
    }

    public void d(final Consumer<List<mns>> consumer) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: gbi
            @Override // java.lang.Runnable
            public final void run() {
                gbg.this.i(consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h, reason: merged with bridge method [inline-methods] */
    public void f(Consumer<Plan> consumer) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("Suggestion_PlanModel", "getMyFitnessPlansInfo, getCurrentPlan : planApi is null.");
            return;
        }
        final ArrayList arrayList = new ArrayList(1);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        planApi.getCurrentIntPlan(new UiPagingCallback<IntPlan>() { // from class: gbg.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccessFirst(IntPlan intPlan) {
                if (intPlan != null && intPlan.getPlanType().equals(IntPlan.PlanType.FIT_PLAN)) {
                    arrayList.add(intPlan.getCompatiblePlan());
                }
                countDownLatch.countDown();
            }

            @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
            public void onFailureFirst(int i, String str) {
                countDownLatch.countDown();
            }
        });
        try {
            if (!countDownLatch.await(5000L, TimeUnit.MILLISECONDS)) {
                LogUtil.h("Suggestion_PlanModel", "getIntelligentRunPlan wait timeout");
            }
        } catch (InterruptedException unused) {
            LogUtil.b("Suggestion_PlanModel", "interrupted while waiting for getDietRecordList");
        }
        Plan plan = arrayList.isEmpty() ? null : (Plan) arrayList.get(0);
        if (consumer != null) {
            consumer.accept(plan);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n, reason: merged with bridge method [inline-methods] */
    public void g(Consumer<Plan> consumer) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("Suggestion_PlanModel", "getMyFitnessPlansInfo, getCurrentPlan : planApi is null.");
            return;
        }
        final ArrayList arrayList = new ArrayList(1);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        planApi.getCurrentIntPlan(new UiPagingCallback<IntPlan>() { // from class: gbg.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccessFirst(IntPlan intPlan) {
                if (intPlan != null && (intPlan.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN) || intPlan.getPlanType().equals(IntPlan.PlanType.RUN_PLAN))) {
                    arrayList.add(intPlan.getCompatiblePlan());
                }
                countDownLatch.countDown();
            }

            @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
            public void onFailureFirst(int i, String str) {
                countDownLatch.countDown();
            }
        });
        try {
            if (!countDownLatch.await(5000L, TimeUnit.MILLISECONDS)) {
                LogUtil.h("Suggestion_PlanModel", "getIntelligentRunPlan wait timeout");
            }
        } catch (InterruptedException unused) {
            LogUtil.b("Suggestion_PlanModel", "interrupted while waiting for getDietRecordList");
        }
        Plan plan = arrayList.isEmpty() ? null : (Plan) arrayList.get(0);
        if (consumer != null) {
            consumer.accept(plan);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void e(final Consumer<PlanStatistics> consumer, final int i) {
        if (consumer == null) {
            LogUtil.h("Suggestion_PlanModel", "queryPlanStatistics consumer = null");
        } else {
            fib.e().d(i, new UiCallback<Map>() { // from class: gbg.2
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i2, String str) {
                    LogUtil.h("Suggestion_PlanModel", "queryPlanStatistics errorCode = ", Integer.valueOf(i2));
                    consumer.accept(null);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Map map) {
                    if (map == null) {
                        LogUtil.h("Suggestion_PlanModel", "queryPlanStatistics Success but value = null");
                        consumer.accept(null);
                        return;
                    }
                    LogUtil.a("Suggestion_PlanModel", "queryPlanStatistics Success");
                    PlanStatistics planStatistics = new PlanStatistics();
                    planStatistics.saveDuration(map.get("duration") == null ? 0L : ((Long) map.get("duration")).longValue());
                    planStatistics.saveCalorie(map.get("calorie") != null ? ((Long) map.get("calorie")).longValue() : 0L);
                    planStatistics.saveTotalPlan(map.get("totalPlan") == null ? 0 : ((Integer) map.get("totalPlan")).intValue());
                    planStatistics.setTotalDistance(map.get(BleConstants.TOTAL_DISTANCE) == null ? 0.0d : ((Double) map.get(BleConstants.TOTAL_DISTANCE)).doubleValue());
                    planStatistics.saveType(i);
                    consumer.accept(planStatistics);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public void b(final Consumer<List<PlanInfo>> consumer) {
        e();
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("Suggestion_PlanModel", "refreshFitnessPlan, getRecommendPlans : planApi is null.");
        } else {
            planApi.setPlanType(3);
            planApi.getRecommedPlans(this.c, new UiCallback<List<PlanInfo>>() { // from class: gbg.3
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("Suggestion_PlanModel", "onFailure errorCode = ", Integer.valueOf(i), " errorInfo = ", str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<PlanInfo> list) {
                    LogUtil.a("Suggestion_PlanModel", "refreshFitnessPlan mCurrentPlan is onSuccess");
                    if (!koq.b(list)) {
                        LogUtil.c("Suggestion_PlanModel", "refreshFitnessPlan List<PlanInfo>", Integer.valueOf(list.size()));
                        Consumer consumer2 = consumer;
                        if (consumer2 != null) {
                            consumer2.accept(list);
                            return;
                        }
                        return;
                    }
                    LogUtil.h("Suggestion_PlanModel", "refreshFitnessPlan data is null");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l, reason: merged with bridge method [inline-methods] */
    public void i(final Consumer<List<mns>> consumer) {
        LogUtil.a("Suggestion_PlanModel", "refreshRunPlan getAllPlans enter!");
        final ArrayList arrayList = new ArrayList(10);
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("Suggestion_PlanModel", "refreshRunPlan, getCurrentPlan : planApi is null.");
        } else {
            planApi.getAllPlans(0, new UiCallback<mnw>() { // from class: gbg.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.b("Suggestion_PlanModel", "getAllPlans onFailure");
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(mnw mnwVar) {
                    gbg.this.d(mnwVar, arrayList, consumer);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(mnw mnwVar, List<mns> list, Consumer<List<mns>> consumer) {
        LogUtil.a("Suggestion_PlanModel", "refreshRunPlan getAllPlans success!");
        list.clear();
        if (mnwVar == null) {
            LogUtil.b("Suggestion_PlanModel", "getAllPlans onSuccess data is null.");
            return;
        }
        if (koq.b(mnwVar.b())) {
            LogUtil.b("Suggestion_PlanModel", "getRunRecommendPlan getAllPlans onSuccess data.list is null.");
            return;
        }
        HashSet<String> hashSet = new HashSet<>();
        for (mmw mmwVar : mnwVar.b()) {
            if (mmwVar != null) {
                hashSet.add(c(mmwVar.b()));
            }
        }
        if (koq.b(hashSet)) {
            LogUtil.h("Suggestion_PlanModel", "PlanRunViewHolder init RunPlanInfo null");
            return;
        }
        b(mnwVar, hashSet, list);
        if (koq.b(list)) {
            LogUtil.h("Suggestion_PlanModel", "refreshFitnessPlan data is null");
        } else if (consumer != null) {
            consumer.accept(list);
        }
    }

    private void b(mnw mnwVar, HashSet<String> hashSet, List<mns> list) {
        Iterator<String> it = hashSet.iterator();
        while (it.hasNext()) {
            String next = it.next();
            ArrayList arrayList = new ArrayList(hashSet.size());
            for (mmw mmwVar : mnwVar.b()) {
                if (next == null || mmwVar == null) {
                    LogUtil.b("Suggestion_PlanModel", "getRunRecommendPlan name == null || info == null");
                } else if (next.equals(c(mmwVar.b()))) {
                    arrayList.add(mmwVar);
                }
            }
            mns mnsVar = new mns();
            mnsVar.d(next);
            mnsVar.d(arrayList);
            mnsVar.b(arrayList.get(0).b());
            if (!list.contains(mnsVar)) {
                list.add(mnsVar);
            }
        }
    }

    private String c(int i) {
        if (i == 777) {
            return ffy.d(BaseApplication.e(), R.string._2130845285_res_0x7f021e65, new Object[0]);
        }
        if (i != 888) {
            return i != 999 ? "" : ffy.d(BaseApplication.e(), R.string._2130845283_res_0x7f021e63, new Object[0]);
        }
        return ffy.d(BaseApplication.e(), R.string._2130845284_res_0x7f021e64, new Object[0]);
    }

    private void e() {
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        if (userProfileMgrApi == null) {
            LogUtil.b("Suggestion_PlanModel", "getUserInfo : userProfileMgrApi is null.");
        } else {
            userProfileMgrApi.getUserInfo(new BaseResponseCallback() { // from class: gbo
                @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
                public final void onResponse(int i, Object obj) {
                    gbg.this.a(i, (UserInfomation) obj);
                }
            });
        }
    }

    /* synthetic */ void a(int i, UserInfomation userInfomation) {
        if (userInfomation == null) {
            LogUtil.h("Suggestion_PlanModel", "requestDailyData getUserInfo failed");
        } else if (userInfomation.isGenderValid()) {
            this.c = userInfomation.getGender();
        }
    }
}
