package defpackage;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.sport.utils.ResultUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.networkclient.ResultCallback;
import com.huawei.operation.utils.CloudParamKeys;
import health.compact.a.GRSManager;
import health.compact.a.LogAnonymous;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Utils;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class fix {
    public static void a(final String str, final JSONObject jSONObject, final DataCallback dataCallback) {
        if (dataCallback == null) {
            LogUtil.e("Suggestion_DietCloudApiHelper", "postGrs, callback == null");
            return;
        }
        if (!Utils.i()) {
            LogUtil.a("Suggestion_DietCloudApiHelper", "fetch HEALTH_CLOUD from no_cloud user");
            return;
        }
        if (HandlerExecutor.b()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: fix.2
                @Override // java.lang.Runnable
                public void run() {
                    fix.a(str, jSONObject, dataCallback);
                }
            });
            return;
        }
        HealthDataCloudFactory healthDataCloudFactory = new HealthDataCloudFactory(BaseApplication.getContext());
        final String str2 = GRSManager.a(BaseApplication.getContext()).getUrl("healthCloudUrl") + str;
        Map<String, Object> body = healthDataCloudFactory.getBody((Map) moj.b(jSONObject == null ? null : jSONObject.toString(), new TypeToken<Map<String, Object>>() { // from class: fix.4
        }.getType()));
        d(body);
        lqi.d().b(str2, healthDataCloudFactory.getHeaders(), lql.b(body), String.class, new ResultCallback<String>() { // from class: fix.1
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str3) {
                try {
                    fix.b(str2, new JSONObject(str3), dataCallback);
                } catch (JSONException e) {
                    ReleaseLogUtil.b("Suggestion_DietCloudApiHelper", " postGrs success failed.", e.getMessage());
                    fix.c(str2, new Exception(e), dataCallback);
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                fix.c(str2, new Exception(th), dataCallback);
            }
        });
    }

    private static void d(Map<String, Object> map) {
        map.put("language", mtj.e(null));
        map.put("timeZone", ggl.b());
        map.put(CloudParamKeys.CLIENT_TYPE, Integer.valueOf(nsn.b()));
        map.put("countryCode", GRSManager.a(BaseApplication.getContext()).getCommonCountryCode());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(String str, JSONObject jSONObject, DataCallback dataCallback) {
        int i = 9999;
        String d = ResultUtil.d(9999);
        if (jSONObject != null) {
            LogUtil.d("Suggestion_DietCloudApiHelper", "data = ", jSONObject);
            i = jSONObject.optInt("resultCode");
            ReleaseLogUtil.b("Suggestion_DietCloudApiHelper", "resultCode = ", Integer.valueOf(i));
            if (i == 0) {
                if (dataCallback != null) {
                    dataCallback.onSuccess(jSONObject);
                }
                LogUtil.d("Suggestion_DietCloudApiHelper", str, jSONObject.toString());
            } else if (dataCallback != null) {
                dataCallback.onFailure(i, jSONObject.optString("resultDesc"));
            }
            d = "";
        } else if (dataCallback != null) {
            dataCallback.onFailure(9999, d);
        }
        LogUtil.d("Suggestion_DietCloudApiHelper", str, ResultUtil.d(i), d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(String str, Exception exc, DataCallback dataCallback) {
        if (dataCallback != null) {
            dataCallback.onFailure(9999, ResultUtil.d(9999));
        }
        LogUtil.e("Suggestion_DietCloudApiHelper", "resultCode=", 9999);
        ReleaseLogUtil.c("Suggestion_DietCloudApiHelper", str, ResultUtil.d(9999), exc.getMessage());
    }

    public static fiy a(JSONObject jSONObject) {
        fiy fiyVar = new fiy();
        if (jSONObject == null) {
            return fiyVar;
        }
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("recommendDetail");
            return optJSONObject == null ? fiyVar : (fiy) HiJsonUtil.e(optJSONObject.toString(), fiy.class);
        } catch (JsonSyntaxException e) {
            ReleaseLogUtil.c("Suggestion_DietCloudApiHelper", "toRecipes exception: ", LogAnonymous.b((Throwable) e));
            return fiyVar;
        }
    }
}
