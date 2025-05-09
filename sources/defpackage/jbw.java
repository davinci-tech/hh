package defpackage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hihealth.util.ReleaseLogUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentUtils;
import health.compact.a.GRSManager;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.File;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jbw {
    private static final String d = File.separator + "productmap" + File.separator + "product_map.json";

    public static boolean c(Context context) {
        if (context == null) {
            LogUtil.h("HiH_ProductMapConfigureUtil", "isNeedUpdateProductMapConfig context is null");
            return false;
        }
        boolean z = System.currentTimeMillis() - CommonUtil.g(SharedPreferenceManager.b(context, "productMapConfig", "product_map_config_last_update_time")) > 86400000;
        LogUtil.a("HiH_ProductMapConfigureUtil", "isNeedUpdateProductMapConfig isNeedUpdate = ", Boolean.valueOf(z));
        return z;
    }

    public static boolean d(Context context, int i) {
        String str;
        LogUtil.a("HiH_ProductMapConfigureUtil", "enter updateProductMapConfig retryCount = ", Integer.valueOf(i));
        if (context == null) {
            LogUtil.h("HiH_ProductMapConfigureUtil", "updateProductMapConfig context is null");
            return false;
        }
        if (i < 0) {
            LogUtil.h("HiH_ProductMapConfigureUtil", "updateProductMapConfig retryCount < 0");
            return false;
        }
        String noCheckUrl = GRSManager.a(context).getNoCheckUrl("getBatchPluginUrl", GRSManager.a(context).getCommonCountryCode());
        if (TextUtils.isEmpty(noCheckUrl)) {
            LogUtil.h("HiH_ProductMapConfigureUtil", "updateProductMapConfig url is empty");
            return false;
        }
        LogUtil.a("HiH_ProductMapConfigureUtil", "url = ", noCheckUrl);
        String c = jbj.c(noCheckUrl + "com.huawei.health_common_config", "ProductMap=ProductMapConfig");
        if (TextUtils.isEmpty(c) || Constants.NULL.equalsIgnoreCase(c)) {
            LogUtil.h("HiH_ProductMapConfigureUtil", "updateProductMapConfig jsonResult is empty");
            return d(context, i - 1);
        }
        LogUtil.a("HiH_ProductMapConfigureUtil", "jsonResult = ", c);
        try {
            str = context.getFilesDir().getCanonicalPath() + d;
        } catch (IOException unused) {
            LogUtil.b("HiH_ProductMapConfigureUtil", "updateProductMapConfig getCanonicalPath IOException");
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("HiH_ProductMapConfigureUtil", "updateProductMapConfig filePath is empty");
            return false;
        }
        String e = e(c, RecommendConstants.VER);
        if (!d(context, e)) {
            LogUtil.a("HiH_ProductMapConfigureUtil", "product map config not need update");
            return ProductMapParseUtil.a(context, str);
        }
        boolean b = jbj.b(e(c, RecommendConstants.DOWNLOAD_URL), str);
        LogUtil.a("HiH_ProductMapConfigureUtil", "updateProductMapConfig isDownloadSuccess = ", Boolean.valueOf(b));
        if (!b) {
            return d(context, i - 1);
        }
        SharedPreferenceManager.e(context, "productMapConfig", "product_map_config_last_version", e, new StorageParams());
        if (!EnvironmentUtils.b()) {
            LogUtil.a("HiH_ProductMapConfigureUtil", "start to send product_map_update broadcast");
            Intent intent = new Intent("com.huawei.health.action.PRODUCT_MAP_UPDATE");
            intent.setPackage(BaseApplication.d());
            BaseApplication.e().sendOrderedBroadcast(intent, LocalBroadcast.c);
        }
        return c(context, str);
    }

    private static boolean c(Context context, String str) {
        boolean a2 = ProductMapParseUtil.a(context, str);
        LogUtil.a("HiH_ProductMapConfigureUtil", "parseJsonFileToSp isParseSuccess = ", Boolean.valueOf(a2));
        if (a2) {
            SharedPreferenceManager.e(context, "productMapConfig", "product_map_config_last_update_time", Long.toString(System.currentTimeMillis()), new StorageParams());
        }
        return a2;
    }

    private static String e(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if ("product_map".equals(jSONObject.getString(RecommendConstants.FILE_ID))) {
                    return jSONObject.getString(str2);
                }
            }
            return "";
        } catch (JSONException unused) {
            LogUtil.b("HiH_ProductMapConfigureUtil", "parseJsonString JSONException");
            return "";
        }
    }

    private static boolean d(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            String b = SharedPreferenceManager.b(context, "productMapConfig", "product_map_config_last_version");
            ReleaseLogUtil.b("HiH_ProductMapConfigureUtil", "lastVer:", b, " downloadVer:", str);
            if (!TextUtils.isEmpty(b) && str.compareTo(b) <= 0) {
                return false;
            }
        }
        return true;
    }
}
