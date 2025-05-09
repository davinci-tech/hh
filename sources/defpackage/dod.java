package defpackage;

import android.content.Context;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dod {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void d(Context context, SingleDailyMomentContent singleDailyMomentContent, boolean z, int i, ResourceBriefInfo resourceBriefInfo) {
        char c;
        if (singleDailyMomentContent == null) {
            return;
        }
        String buttonLinkType = z ? singleDailyMomentContent.getButtonLinkType() : singleDailyMomentContent.getCoverLinkType();
        if (buttonLinkType == null) {
            return;
        }
        String buttonLinkValue = z ? singleDailyMomentContent.getButtonLinkValue() : singleDailyMomentContent.getCoverLinkValue();
        String c2 = eil.c(buttonLinkValue, 5001, i + 1, singleDailyMomentContent, resourceBriefInfo);
        buttonLinkType.hashCode();
        switch (buttonLinkType.hashCode()) {
            case -1591322833:
                if (buttonLinkType.equals(SingleDailyMomentContent.ACTIVITY_TYPE)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -658498292:
                if (buttonLinkType.equals(SingleDailyMomentContent.INFORMATION_TYPE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 85327:
                if (buttonLinkType.equals(SingleDailyMomentContent.URL_TYPE)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1592613294:
                if (buttonLinkType.equals(SingleDailyMomentContent.SLEEP_MUSIC_TYPE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 2024262715:
                if (buttonLinkType.equals(SingleDailyMomentContent.COURSE_TYPE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c != 0) {
            if (c == 1) {
                a(context, buttonLinkValue);
                return;
            }
            if (c != 2) {
                if (c == 3) {
                    if (z) {
                        return;
                    }
                    c(c2);
                    return;
                } else {
                    if (c != 4) {
                        return;
                    }
                    if (z) {
                        b(context, buttonLinkValue);
                        return;
                    } else {
                        e(context, buttonLinkValue);
                        return;
                    }
                }
            }
        }
        c(c2);
    }

    private static void e(final Context context, String str) {
        if (context == null || str == null) {
            return;
        }
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.a("DailyMomentJumpUtils", "get courseApi fail");
        } else {
            courseApi.getCourseById(str, null, null, new UiCallback<Workout>() { // from class: dod.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str2) {
                    LogUtil.a("DailyMomentJumpUtils", "get course fail");
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Workout workout) {
                    dod.c(context, mod.a(workout));
                }
            });
        }
    }

    public static void c(Context context, FitWorkout fitWorkout) {
        c(context, fitWorkout, "DailyMoment");
    }

    public static void c(Context context, FitWorkout fitWorkout, String str) {
        if (fitWorkout == null) {
            return;
        }
        mmp mmpVar = new mmp(fitWorkout.acquireId());
        mmpVar.d(str);
        mod.b(context, fitWorkout, mmpVar);
    }

    public static void b(final Context context, String str) {
        if (context == null || str == null) {
            return;
        }
        final PluginSuggestion pluginSuggestion = (PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class);
        if (pluginSuggestion == null) {
            LogUtil.a("DailyMomentJumpUtils", "get pluginSuggestion fail");
        } else {
            pluginSuggestion.getFitWorkout(str, null, new UiCallback<FitWorkout>() { // from class: dod.5
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str2) {
                    LogUtil.a("DailyMomentJumpUtils", "get course fail");
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(FitWorkout fitWorkout) {
                    if (fitWorkout == null) {
                        LogUtil.a("DailyMomentJumpUtils", "data is null");
                    } else {
                        PluginSuggestion pluginSuggestion2 = PluginSuggestion.this;
                        pluginSuggestion2.jumpToStartTrain(context, fitWorkout, pluginSuggestion2);
                    }
                }
            });
        }
    }

    private static void a(Context context, String str) {
        final HashMap<String, String> a2 = doc.a(context);
        final HashMap<String, String> b = doc.b(context);
        a2.put(CommonUtil.PAGE_TYPE, String.valueOf(1));
        a2.put("id", str);
        GRSManager.a(context).e("messageCenterUrl", new GrsQueryCallback() { // from class: dod.2
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str2) {
                LogUtil.a("DailyMomentJumpUtils", "message center key: " + str2);
                jei.d(str2 + "/messageCenter/information/v2/queryByIdAnon", a2, b, new HttpResCallback() { // from class: dod.2.2
                    @Override // com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback
                    public void onFinished(int i, String str3) {
                        if (i != 200) {
                            LogUtil.a("DailyMomentJumpUtils", "query information by id failed");
                            return;
                        }
                        LogUtil.a("DailyMomentJumpUtils", "query information by id result: " + str3);
                        try {
                            dod.c(new JSONObject(str3).getJSONObject("information").getString("url"));
                        } catch (JSONException unused) {
                            LogUtil.a("DailyMomentJumpUtils", "parse json failed");
                        }
                    }
                });
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.a("DailyMomentJumpUtils", "GRS query message center failed");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(String str) {
        LogUtil.a("DailyMomentJumpUtils", str);
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            marketRouterApi.router(str);
        }
    }
}
