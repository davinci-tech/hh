package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.network.inner.api.NetworkService;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import health.compact.a.AuthorizationUtils;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.File;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jaa {
    private static final String e = File.separator + NetworkService.Constants.GRS_SERVICE + File.separator + "grs_third_party_config.json";

    public static boolean e(Context context) {
        if (context == null) {
            LogUtil.h("GrsConfigInteractor", "isNeedUpdateGrsConfig context is null");
            return false;
        }
        if (!AuthorizationUtils.a(context)) {
            LogUtil.h("GrsConfigInteractor", "getAuthorizationStatus is false");
            return false;
        }
        boolean z = System.currentTimeMillis() - CommonUtil.g(SharedPreferenceManager.b(context, Integer.toString(10000), "grs_config_last_update_time")) > 86400000;
        LogUtil.a("GrsConfigInteractor", "isNeedUpdateGrsConfig isNeedUpdate = ", Boolean.valueOf(z));
        return z;
    }

    public static boolean b(Context context) {
        if (context == null) {
            LogUtil.h("GrsConfigInteractor", "isNeedDeleteGrsConfig context is null");
            return false;
        }
        String b = SharedPreferenceManager.b(context, Integer.toString(10000), "grs_config_delete");
        LogUtil.a("GrsConfigInteractor", "isNeedDeleteGrsConfig, isNeedDelete:", b);
        return "true".equals(b);
    }

    public static boolean e(Context context, int i) {
        String str;
        LogUtil.a("GrsConfigInteractor", "enter updateGrsConfig retryCount = ", Integer.valueOf(i));
        if (context == null) {
            LogUtil.h("GrsConfigInteractor", "updateGrsConfig context is null");
            return false;
        }
        if (i < 0) {
            LogUtil.h("GrsConfigInteractor", "updateGrsConfig retryCount < 0");
            return false;
        }
        String noCheckUrl = GRSManager.a(context).getNoCheckUrl("getNewBatchPluginUrl", GRSManager.a(context).getCommonCountryCode());
        if (TextUtils.isEmpty(noCheckUrl)) {
            LogUtil.h("GrsConfigInteractor", "updateGrsConfig url is empty");
            return false;
        }
        String c = jbj.c(noCheckUrl + "com.huawei.health_common_config", "resources=grs_third_party_config");
        if (TextUtils.isEmpty(c) || Constants.NULL.equalsIgnoreCase(c)) {
            LogUtil.h("GrsConfigInteractor", "updateGrsConfig jsonResult is empty");
            return e(context, i - 1);
        }
        String c2 = c(c);
        if (TextUtils.isEmpty(c2)) {
            LogUtil.h("GrsConfigInteractor", "updateGrsConfig downloadUrl is empty");
            return e(context, i - 1);
        }
        try {
            str = context.getFilesDir().getCanonicalPath() + e;
        } catch (IOException unused) {
            LogUtil.b("GrsConfigInteractor", "updateGrsConfig getCanonicalPath IOException");
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("GrsConfigInteractor", "updateGrsConfig filePath is empty");
            return false;
        }
        boolean b = jbj.b(c2, str);
        LogUtil.a("GrsConfigInteractor", "updateGrsConfig isDownloadSuccess = ", Boolean.valueOf(b));
        if (!b) {
            return e(context, i - 1);
        }
        boolean a2 = a(str);
        LogUtil.a("GrsConfigInteractor", "updateGrsConfig isParseSuccess = ", Boolean.valueOf(a2));
        if (a2) {
            SharedPreferenceManager.e(context, Integer.toString(10000), "grs_config_last_update_time", Long.toString(System.currentTimeMillis()), new StorageParams());
        }
        return a2;
    }

    public static void c(Context context) {
        if (context == null) {
            LogUtil.h("GrsConfigInteractor", "deleteGrsConfig context is null");
            return;
        }
        jah.c().e();
        try {
            File file = new File(context.getFilesDir().getCanonicalPath() + e);
            if (file.exists()) {
                if (file.delete()) {
                    LogUtil.h("GrsConfigInteractor", "deleteGrsConfig,file delete success!");
                    SharedPreferenceManager.c(context, Integer.toString(10000), "grs_config_delete");
                } else {
                    LogUtil.h("GrsConfigInteractor", "deleteGrsConfig,file delete fail!");
                }
            }
        } catch (IOException | SecurityException e2) {
            LogUtil.b("GrsConfigInteractor", e2.getMessage());
        }
    }

    private static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if ("grs_third_party_config".equals(jSONObject.getString(RecommendConstants.FILE_ID))) {
                    return jSONObject.getString(RecommendConstants.DOWNLOAD_URL);
                }
            }
            return "";
        } catch (JSONException unused) {
            LogUtil.b("GrsConfigInteractor", "parseJsonString JSONException");
            return "";
        }
    }

    private static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("GrsConfigInteractor", "parseJsonFile filePath is empty");
            return false;
        }
        String e2 = mrx.e(new File(str));
        if (TextUtils.isEmpty(e2)) {
            LogUtil.a("GrsConfigInteractor", "parseJsonFile urlJsonString is empty");
            return false;
        }
        try {
            JSONArray jSONArray = new JSONArray(e2);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.has(MedalConstants.EVENT_KEY) && jSONObject.has("value")) {
                    String string = jSONObject.getString(MedalConstants.EVENT_KEY);
                    String string2 = jSONObject.getString("value");
                    if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                        jah.c().a(string, string2);
                    }
                }
            }
            return true;
        } catch (JSONException unused) {
            LogUtil.b("GrsConfigInteractor", "parseJsonFile JSONException");
            return false;
        }
    }
}
