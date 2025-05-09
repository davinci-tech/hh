package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.health.plan.model.data.intelligentplan.IntelligentPlanDataApi;
import com.huawei.health.sport.utils.ResultUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class euq implements IntelligentPlanDataApi {
    private Handler b = new Handler(Looper.getMainLooper());

    @Override // com.huawei.health.plan.model.data.intelligentplan.IntelligentPlanDataApi
    public void getAllPlans(int i, final UiCallback<mnw> uiCallback) {
        LogUtil.a("Suggestion_IntelligentPlanDataImpl", "getAllPlans type = ", Integer.valueOf(i));
        if (uiCallback == null) {
            LogUtil.b("Suggestion_IntelligentPlanDataImpl", "getAllPlans() callback == null");
            return;
        }
        if (LoginInit.getInstance(BaseApplication.getContext()) == null) {
            b(uiCallback, "getAllPlans");
        } else if (Utils.l()) {
            d(uiCallback, "getAllPlans");
        } else {
            eqa.a().getAllPlans(i, new DataCallback() { // from class: euq.1
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i2, String str) {
                    LogUtil.b("Suggestion_IntelligentPlanDataImpl", "getAllPlans fail errorCode = ", Integer.valueOf(i2));
                    uiCallback.onFailure(euq.this.b, i2, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    LogUtil.a("Suggestion_IntelligentPlanDataImpl", "getAllPlans onSuccess data");
                    if (jSONObject == null) {
                        LogUtil.b("Suggestion_IntelligentPlanDataImpl", "getAllPlans data null.");
                    } else {
                        uiCallback.onSuccess(euq.this.b, (mnw) new Gson().fromJson(jSONObject.toString(), new TypeToken<mnw>() { // from class: euq.1.4
                        }.getType()));
                    }
                }
            });
        }
    }

    private <T> void d(UiCallback<T> uiCallback, String str) {
        LogUtil.b("Suggestion_IntelligentPlanDataImpl", str, " is oversea no cloud");
        uiCallback.onFailure(99, str, " this version is oversea no cloud version");
    }

    @Override // com.huawei.health.plan.model.data.intelligentplan.IntelligentPlanDataApi
    public void getAchievementForecast(int i, int i2, int i3, int i4, final UiCallback<mog> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_IntelligentPlanDataImpl", "getAchievementForecast() callback == null");
            return;
        }
        if (LoginInit.getInstance(BaseApplication.getContext()) == null) {
            b(uiCallback, "getAchievementForecast");
        } else if (Utils.l()) {
            d(uiCallback, "getAchievementForecast");
        } else {
            eqa.a().getAchievementForecast(i, i2, i3, i4, new DataCallback() { // from class: euq.4
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i5, String str) {
                    LogUtil.b("Suggestion_IntelligentPlanDataImpl", "getAchievementForecast fail errorCode = ", Integer.valueOf(i5));
                    uiCallback.onFailure(euq.this.b, i5, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    LogUtil.a("Suggestion_IntelligentPlanDataImpl", "getAchievementForecast onSuccess data", jSONObject);
                    if (jSONObject != null) {
                        uiCallback.onSuccess(euq.this.b, (mog) new Gson().fromJson(jSONObject.toString(), new TypeToken<mog>() { // from class: euq.4.2
                        }.getType()));
                    } else {
                        LogUtil.b("Suggestion_IntelligentPlanDataImpl", "getAchievementForecast data null");
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.intelligentplan.IntelligentPlanDataApi
    public void getCurrentReportIndex(final UiCallback<Integer> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_IntelligentPlanDataImpl", "getCurrentReportIndex() callback is null");
            return;
        }
        if (LoginInit.getInstance(BaseApplication.getContext()) == null) {
            b(uiCallback, "getCurrentReportIndex");
        } else if (Utils.l()) {
            d(uiCallback, "getCurrentReportIndex");
        } else {
            eqa.a().getCurrentReportIndex(new DataCallback() { // from class: euq.3
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str) {
                    LogUtil.b("Suggestion_IntelligentPlanDataImpl", "getCurrentReportIndex fail errorCode = ", Integer.valueOf(i));
                    uiCallback.onFailure(euq.this.b, i, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        uiCallback.onSuccess(euq.this.b, Integer.valueOf(jSONObject.optInt("index")));
                    } else {
                        LogUtil.b("Suggestion_IntelligentPlanDataImpl", "getCurrentReportIndex data null");
                    }
                }
            });
        }
    }

    private <T> void b(UiCallback<T> uiCallback, String str) {
        uiCallback.onFailure(20001, ResultUtil.d(20001));
        LogUtil.b("Suggestion_IntelligentPlanDataImpl", str, ":getAccountInfo == null");
    }

    @Override // com.huawei.health.plan.model.data.intelligentplan.IntelligentPlanDataApi
    public void postFeedback(mof mofVar, final UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_IntelligentPlanDataImpl", "postFeedback() callback is null");
            return;
        }
        if (LoginInit.getInstance(BaseApplication.getContext()) == null) {
            b(uiCallback, "postFeedback");
        } else if (Utils.l()) {
            d(uiCallback, "postFeedback");
        } else {
            LogUtil.a("Suggestion_IntelligentPlanDataImpl", "postFeedback userFeedbackBean:", new Gson().toJson(mofVar));
            eqa.a().postFeedback(mofVar, new DataCallback() { // from class: euq.5
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.d("Suggestion_IntelligentPlanDataImpl", "postFeedback fail");
                    uiCallback.onFailure(euq.this.b, i, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    ReleaseLogUtil.e("Suggestion_IntelligentPlanDataImpl", "postFeedback success");
                    if (jSONObject != null) {
                        uiCallback.onSuccess(jSONObject.toString());
                    } else {
                        ReleaseLogUtil.c("Suggestion_IntelligentPlanDataImpl", "postFeedback data null");
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.intelligentplan.IntelligentPlanDataApi
    public void getTrainingReport(String str, int i, UiCallback<mny> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_IntelligentPlanDataImpl", "getTrainingReport() callback is null");
            return;
        }
        if (LoginInit.getInstance(BaseApplication.getContext()) == null) {
            b(uiCallback, "getTrainingReport");
        } else if (Utils.l()) {
            d(uiCallback, "getTrainingReport");
        } else {
            new euo().a(str, i, uiCallback);
        }
    }

    @Override // com.huawei.health.plan.model.data.intelligentplan.IntelligentPlanDataApi
    public void getCoachAdvice(String str, boolean z, final UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_IntelligentPlanDataImpl", "getCoachAdvice() callback is null");
            return;
        }
        if (LoginInit.getInstance(BaseApplication.getContext()) == null) {
            b(uiCallback, "getCoachAdvice");
        } else if (Utils.l()) {
            d(uiCallback, "getCoachAdvice");
        } else {
            eqa.a().getCoachAdvice(str, !z ? 1 : 0, new DataCallback() { // from class: euq.2
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str2) {
                    ReleaseLogUtil.d("Suggestion_IntelligentPlanDataImpl", "getCoachAdvice fail");
                    uiCallback.onFailure(euq.this.b, i, str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    ReleaseLogUtil.e("Suggestion_IntelligentPlanDataImpl", "getCoachAdvice onSuccess");
                    if (jSONObject == null) {
                        LogUtil.b("Suggestion_IntelligentPlanDataImpl", "getCoachAdvice data null");
                        return;
                    }
                    String optString = jSONObject.optString("coachAdviceParam");
                    if (!TextUtils.isEmpty(optString)) {
                        LogUtil.a("Suggestion_IntelligentPlanDataImpl", "AlgorithmInfos : coachAdviceParam = ", optString);
                    }
                    uiCallback.onSuccess(euq.this.b, jSONObject.optString("coachAdvice"));
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.intelligentplan.IntelligentPlanDataApi
    public void updatePlanDate(String str, final List<Integer> list, final List<Integer> list2, final UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_IntelligentPlanDataImpl", "updatePlanDate() callback is null");
            return;
        }
        if (LoginInit.getInstance(BaseApplication.getContext()) == null) {
            b(uiCallback, "updatePlanDate");
        } else if (Utils.l()) {
            d(uiCallback, "updatePlanDate");
        } else {
            eqa.a().updatePlanDate(str, list, list2, new DataCallback() { // from class: euq.9
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str2) {
                    LogUtil.h("Suggestion_IntelligentPlanDataImpl", "updatePlanDate fail");
                    uiCallback.onFailure(euq.this.b, i, str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    LogUtil.a("Suggestion_IntelligentPlanDataImpl", "updatePlanDate onSuccess data", jSONObject);
                    if (jSONObject == null) {
                        LogUtil.b("Suggestion_IntelligentPlanDataImpl", "updatePlanDate data null");
                        uiCallback.onSuccess(euq.this.b, "");
                        return;
                    }
                    String valueOf = String.valueOf(jSONObject.optInt("resultCode"));
                    if ("0".equals(valueOf)) {
                        etx.b().e();
                        Plan currentPlan = etr.b().getCurrentPlan();
                        if (currentPlan == null) {
                            uiCallback.onSuccess(euq.this.b, valueOf);
                            LogUtil.b("Suggestion_IntelligentPlanDataImpl", "updatePlanDate plan == null");
                            return;
                        } else {
                            currentPlan.setRunDate(list);
                            currentPlan.setExeDate(list2);
                            ett.i().n().d(arx.a(), currentPlan, currentPlan.acquireVersion());
                        }
                    }
                    uiCallback.onSuccess(euq.this.b, valueOf);
                }
            });
        }
    }
}
