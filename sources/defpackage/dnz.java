package defpackage;

import android.content.Context;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.functionsetcard.dailymoment.operation.IGetTitleCallback;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dnz {
    private static Map<String, FitWorkout> b = new HashMap();

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, a> f11744a = new HashMap();
    private static Map<String, a> e = new HashMap();
    private static Map<String, a> d = new HashMap();

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static a c(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -1591322833:
                if (str.equals(SingleDailyMomentContent.ACTIVITY_TYPE)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -658498292:
                if (str.equals(SingleDailyMomentContent.INFORMATION_TYPE)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 85327:
                if (str.equals(SingleDailyMomentContent.URL_TYPE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1592613294:
                if (str.equals(SingleDailyMomentContent.SLEEP_MUSIC_TYPE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            return f11744a.get(str2);
        }
        if (c == 1) {
            return e.get(str2);
        }
        if (c != 2) {
            return null;
        }
        return d.get(str2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String e(Context context, SingleDailyMomentContent singleDailyMomentContent, IGetTitleCallback iGetTitleCallback) {
        char c;
        String coverLinkType = singleDailyMomentContent.getCoverLinkType();
        String coverLinkValue = singleDailyMomentContent.getCoverLinkValue();
        if (singleDailyMomentContent.getButtonLinkType() != null && singleDailyMomentContent.getButtonLinkType().equals(SingleDailyMomentContent.SLEEP_MUSIC_TYPE)) {
            coverLinkValue = singleDailyMomentContent.getButtonLinkValue();
            coverLinkType = SingleDailyMomentContent.SLEEP_MUSIC_TYPE;
        }
        a c2 = c(coverLinkType, coverLinkValue);
        if (c2 != null) {
            return c2.c();
        }
        switch (coverLinkType.hashCode()) {
            case -1591322833:
                if (coverLinkType.equals(SingleDailyMomentContent.ACTIVITY_TYPE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -658498292:
                if (coverLinkType.equals(SingleDailyMomentContent.INFORMATION_TYPE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 85327:
                if (coverLinkType.equals(SingleDailyMomentContent.URL_TYPE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1592613294:
                if (coverLinkType.equals(SingleDailyMomentContent.SLEEP_MUSIC_TYPE)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 2024262715:
                if (coverLinkType.equals(SingleDailyMomentContent.COURSE_TYPE)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            a(singleDailyMomentContent.getCoverLinkValue(), e(iGetTitleCallback));
            return null;
        }
        if (c == 1) {
            e(context, singleDailyMomentContent.getCoverLinkValue(), c(iGetTitleCallback));
            return null;
        }
        if (c != 2) {
            return null;
        }
        c(context, singleDailyMomentContent.getButtonLinkValue(), c(iGetTitleCallback));
        return null;
    }

    private static UiCallback<a> c(final IGetTitleCallback iGetTitleCallback) {
        return new UiCallback<a>() { // from class: dnz.2
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                IGetTitleCallback.this.onFailure(i, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(a aVar) {
                if (aVar == null) {
                    IGetTitleCallback.this.onSuccess(null);
                } else {
                    IGetTitleCallback.this.onSuccess(aVar.c());
                }
            }
        };
    }

    private static UiCallback<Workout> e(final IGetTitleCallback iGetTitleCallback) {
        return new UiCallback<Workout>() { // from class: dnz.5
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                IGetTitleCallback.this.onFailure(i, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(Workout workout) {
                if (workout == null) {
                    IGetTitleCallback.this.onSuccess(null);
                } else {
                    IGetTitleCallback.this.onSuccess(workout.acquireName());
                }
            }
        };
    }

    public static void a(final String str, final UiCallback<Workout> uiCallback) {
        LogUtil.a("PageDetailManager", "fetchCourseInfo");
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            return;
        }
        courseApi.getCourseById(str, null, null, new UiCallback<Workout>() { // from class: dnz.1
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str2) {
                LogUtil.a("PageDetailManager", "fetchCourseInfo onFailure");
                UiCallback uiCallback2 = UiCallback.this;
                if (uiCallback2 != null) {
                    uiCallback2.onFailure(i, str2);
                }
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(Workout workout) {
                LogUtil.a("PageDetailManager", "fetchCourseInfo onFailure");
                FitWorkout a2 = mod.a(workout);
                if (a2 != null) {
                    dnz.b.put(str, a2);
                    UiCallback uiCallback2 = UiCallback.this;
                    if (uiCallback2 != null) {
                        uiCallback2.onSuccess(a2);
                        return;
                    }
                    return;
                }
                onFailure(0, "data is not Fitworkout");
            }
        });
    }

    public static void e(Context context, final String str, final UiCallback<a> uiCallback) {
        LogUtil.a("PageDetailManager", "fetchInformationInfo");
        final HashMap<String, String> a2 = doc.a(context);
        a2.put("id", str);
        final HashMap<String, String> b2 = doc.b(context);
        GRSManager.a(BaseApplication.getContext()).e("messageCenterUrl", new GrsQueryCallback() { // from class: dnz.3
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str2) {
                jei.d(str2 + "/messageCenter/information/v2/queryByIdAnon", a2, b2, new HttpResCallback() { // from class: dnz.3.5
                    @Override // com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback
                    public void onFinished(int i, String str3) {
                        if (i != 200) {
                            if (uiCallback != null) {
                                uiCallback.onFailure(0, "query information by id failed");
                                return;
                            }
                            return;
                        }
                        try {
                            JSONObject jSONObject = new JSONObject(str3).getJSONObject("information");
                            a aVar = new a(jSONObject.getString("title"), jSONObject.getString("url"));
                            dnz.f11744a.put(str, aVar);
                            if (uiCallback != null) {
                                uiCallback.onSuccess(aVar);
                            }
                        } catch (JSONException unused) {
                            if (uiCallback != null) {
                                uiCallback.onFailure(0, "information json exception");
                            }
                        }
                    }
                });
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                UiCallback uiCallback2 = uiCallback;
                if (uiCallback2 != null) {
                    uiCallback2.onFailure(0, "information get url failed");
                }
            }
        });
    }

    public static void c(Context context, final String str, final UiCallback<a> uiCallback) {
        LogUtil.a("PageDetailManager", "fetchAudioInfo");
        final HashMap<String, String> a2 = doc.a(context);
        a2.put(CommonUtil.PAGE_TYPE, String.valueOf(1));
        a2.put("Id", str);
        final HashMap<String, String> b2 = doc.b(context);
        GRSManager.a(BaseApplication.getContext()).e("messageCenterUrl", new GrsQueryCallback() { // from class: dnz.4
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str2) {
                jei.d(str2 + "/messageCenter/getSleepAudioById", a2, b2, new HttpResCallback() { // from class: dnz.4.5
                    @Override // com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback
                    public void onFinished(int i, String str3) {
                        if (200 != i) {
                            if (uiCallback != null) {
                                uiCallback.onFailure(0, "query audio info by id failed");
                                return;
                            }
                            return;
                        }
                        try {
                            JSONObject jSONObject = new JSONObject(str3).getJSONObject("sleepAudio");
                            a aVar = new a(jSONObject.getString("name"), jSONObject.getString("audioUrl"));
                            dnz.e.put(str, aVar);
                            if (uiCallback != null) {
                                uiCallback.onSuccess(aVar);
                            }
                        } catch (JSONException unused) {
                            if (uiCallback != null) {
                                uiCallback.onFailure(0, "audio info json exception");
                            }
                        }
                    }
                });
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                UiCallback uiCallback2 = uiCallback;
                if (uiCallback2 != null) {
                    uiCallback2.onFailure(0, "audio info get url failed");
                }
            }
        });
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private String f11748a;
        private String c;

        public a(String str, String str2) {
            this.f11748a = str;
            this.c = str2;
        }

        public String c() {
            return this.f11748a;
        }

        public String toString() {
            return "PageDetail{title='" + this.f11748a + "', url='" + this.c + "'}";
        }
    }
}
