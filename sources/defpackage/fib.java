package defpackage;

import com.google.gson.Gson;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.plan.model.PlanStatistics;
import com.huawei.health.sport.utils.ResultUtil;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.health.suggestion.SuggestionBaseCallback;
import com.huawei.health.suggestion.util.HeartRateGetterUtil;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hwfoundationmodel.trackmodel.IReportDataCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class fib {
    private static volatile fib c;
    private IReportDataCallback d = null;
    private List<Integer> e = null;
    private HiSubscribeListener b = new HiSubscribeListener() { // from class: fib.4
        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            if (koq.c(list)) {
                LogUtil.a("Suggestion_PluginSuggestionHelper", "registerReportDataListener success");
                fib.this.e = list;
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            if (hiHealthData == null) {
                LogUtil.h("Suggestion_PluginSuggestionHelper", "onChange reportData HiHealthData is null!");
                return;
            }
            String metaData = hiHealthData.getMetaData();
            LogUtil.c("Suggestion_PluginSuggestionHelper", "onChange:", metaData);
            kon konVar = (kon) new Gson().fromJson(CommonUtil.z(metaData), kon.class);
            if (konVar == null) {
                return;
            }
            kvq kvqVar = new kvq();
            kvqVar.j(konVar.j());
            kvqVar.e(konVar.i());
            kvqVar.a(konVar.d());
            kvqVar.k(konVar.b());
            kvqVar.n(konVar.o());
            kvqVar.o(konVar.n());
            kvqVar.d(konVar.m());
            kvqVar.b(konVar.e());
            kvqVar.c(konVar.c());
            kvqVar.h((int) konVar.f());
            kvqVar.f(konVar.g());
            kvqVar.s((int) konVar.h());
            kvqVar.d(konVar.l());
            if (fib.this.d != null) {
                fib.this.d.onChange(kvqVar);
            }
        }
    };

    private fib() {
    }

    public static fib e() {
        if (c == null) {
            synchronized (PluginSuggestion.class) {
                if (c == null) {
                    c = new fib();
                }
            }
        }
        return c;
    }

    public boolean c() {
        if (arx.b() == null) {
            LogUtil.b("Suggestion_PluginSuggestionHelper", "isInitComplete error, please call the init function first.");
            return false;
        }
        if (LoginInit.getInstance(arx.b()).isBrowseMode()) {
            LogUtil.h("Suggestion_PluginSuggestionHelper", "browseMode");
            return true;
        }
        if (LoginInit.getInstance(arx.b()).getAccountInfo(1011) != null) {
            return true;
        }
        LogUtil.b("Suggestion_PluginSuggestionHelper", "isInitComplete error, pluginSuggestionAdapter get userid returns null.");
        return false;
    }

    public void a(String str, String str2, final UiCallback<FitWorkout> uiCallback) {
        if (uiCallback == null) {
            LogUtil.h("Suggestion_PluginSuggestionHelper", "callback is null");
            return;
        }
        if (!e().c()) {
            uiCallback.onFailure(-6, ResultUtil.d(-6));
            return;
        }
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_PluginSuggestionHelper", "getFitnessDetail : courseApi is null.");
        } else {
            courseApi.getCourseById(str, str2, null, new UiCallback<Workout>() { // from class: fib.3
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str3) {
                    uiCallback.onFailure(i, str3);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Workout workout) {
                    uiCallback.onSuccess(mod.a(workout));
                }
            });
        }
    }

    private int c(int i) {
        if (i == 2) {
            return 0;
        }
        if (i == 3) {
            return 3;
        }
        if (i == 4) {
            return -1;
        }
        LogUtil.h("Suggestion_PluginSuggestionHelper", "getPlanTypeByPageType : pageType illegal.");
        return -2;
    }

    public void d(int i, final UiCallback<Map> uiCallback) {
        if (uiCallback == null) {
            LogUtil.h("Suggestion_PluginSuggestionHelper", "callback is null");
            return;
        }
        final int c2 = c(i);
        if (c2 == -2) {
            LogUtil.b("Suggestion_PluginSuggestionHelper", "getPlanStatistics : planType illegal");
        } else {
            asc.e().b(new Runnable() { // from class: fib.5
                @Override // java.lang.Runnable
                public void run() {
                    PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
                    if (planApi == null) {
                        LogUtil.b("Suggestion_PluginSuggestionHelper", "getPlanStatistics : planApi is null.");
                    } else {
                        planApi.setPlanType(c2);
                        planApi.getPlanStatistics(new UiCallback<PlanStatistics>() { // from class: fib.5.2
                            @Override // com.huawei.basefitnessadvice.callback.UiCallback
                            public void onFailure(int i2, String str) {
                                LogUtil.a("Suggestion_PluginSuggestionHelper", "onFailure errorCode = ", Integer.valueOf(i2));
                            }

                            @Override // com.huawei.basefitnessadvice.callback.UiCallback
                            /* renamed from: b, reason: merged with bridge method [inline-methods] */
                            public void onSuccess(PlanStatistics planStatistics) {
                                LogUtil.a("Suggestion_PluginSuggestionHelper", "onSuccess");
                                if (planStatistics == null) {
                                    LogUtil.b("Suggestion_PluginSuggestionHelper", "planStatistics null.");
                                    return;
                                }
                                HashMap hashMap = new HashMap();
                                hashMap.put("calorie", Long.valueOf(planStatistics.acquireCalorie()));
                                hashMap.put("duration", Long.valueOf(planStatistics.acquireDuration()));
                                hashMap.put("totalPlan", Integer.valueOf(planStatistics.acquireTotalPlan()));
                                hashMap.put(BleConstants.TOTAL_DISTANCE, Double.valueOf(planStatistics.getTotalDistance()));
                                uiCallback.onSuccess(hashMap);
                            }
                        });
                    }
                }
            });
        }
    }

    public void c(IReportDataCallback iReportDataCallback) {
        d(iReportDataCallback);
    }

    public void d() {
        a();
    }

    public boolean d(IReportDataCallback iReportDataCallback) {
        HiHealthManager.d(BaseApplication.e()).subscribeHiHealthData(17, this.b);
        this.d = iReportDataCallback;
        return true;
    }

    public void a() {
        if (koq.c(this.e)) {
            LogUtil.a("Suggestion_PluginSuggestionHelper", "unRegisterReportDataListener");
            HiHealthManager.d(BaseApplication.e()).unSubscribeHiHealthData(this.e, new HiUnSubscribeListener() { // from class: fid
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public final void onResult(boolean z) {
                    fib.this.a(z);
                }
            });
        } else {
            LogUtil.h("Suggestion_PluginSuggestionHelper", "report data listener has unregister.");
        }
    }

    /* synthetic */ void a(boolean z) {
        LogUtil.a("Suggestion_PluginSuggestionHelper", "unregReportDataListener isSuccess = ", Boolean.valueOf(z));
        this.d = null;
    }

    public void a(SuggestionBaseCallback suggestionBaseCallback, boolean z) {
        HeartRateGetterUtil a2 = HeartRateGetterUtil.a();
        a2.c(suggestionBaseCallback);
        a2.c(0, z);
    }

    public void e(SuggestionBaseCallback suggestionBaseCallback, boolean z) {
        HeartRateGetterUtil a2 = HeartRateGetterUtil.a();
        a2.c(1, z);
        a2.a(suggestionBaseCallback);
    }
}
