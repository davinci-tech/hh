package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.secure.android.common.util.UrlUtil;
import com.huawei.secure.android.common.webview.UriUtil;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.DensityUtil;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class at {
    private static String b = "";

    /* renamed from: a, reason: collision with root package name */
    public List<String> f10908a = new ArrayList();

    public boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (d(str) || b(str)) {
            return true;
        }
        return e(str);
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return g(str) || h(str) || i(str) || c(str);
    }

    private static boolean g(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith(b() + DensityUtil.getStringById(R$string.CLOUD_PRIVACY_URL));
    }

    private static String b() {
        if (TextUtils.isEmpty(b)) {
            b = WatchFaceHttpUtil.b("privacy");
        }
        return b;
    }

    private static boolean h(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith(b() + DensityUtil.getStringById(R$string.CLOUD_TERM_URL));
    }

    private static boolean i(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith(b() + DensityUtil.getStringById(R$string.CLOUD_CHANGE_URL));
    }

    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (!str.startsWith(b() + DensityUtil.getStringById(R$string.CLOUD_MEMBERSHIP_URL))) {
            if (!str.startsWith(b() + DensityUtil.getStringById(R$string.CLOUD_MONSERVICE_URL))) {
                if (!str.startsWith(b() + DensityUtil.getStringById(R$string.WATCHFACE_CLOUD_MEMBERSHIP_URL))) {
                    if (!str.startsWith(b() + DensityUtil.getStringById(R$string.WATCHFACE_CLOUD_MONSERVICE_URL))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean d(String str) {
        return "file:///android_asset/openSource.html".equals(str) || "file:///android_asset/openSourceOverseas.html".equals(str) || "file:///android_asset/watchfaceOpenSource.html".equals(str) || "file:///android_asset/watchfaceOpenSourceOverseas.html".equals(str);
    }

    public boolean e(String str) {
        return a(str, this.f10908a);
    }

    public List<String> a() {
        return this.f10908a;
    }

    public static boolean a(String str, List<String> list) {
        if (ArrayUtils.isEmpty(list)) {
            HwLog.e("SupportType", "checkIsUrlHostInWhiteList whiteList is empty");
            return false;
        }
        String[] strArr = new String[list.size()];
        list.toArray(strArr);
        boolean isUrlHostSameWhitelist = UriUtil.isUrlHostSameWhitelist(str, strArr);
        boolean isHttpsUrl = UrlUtil.isHttpsUrl(str);
        HwLog.i("SupportType", "checkIsUrlHostInWhiteList urlHostInWhitelist : " + isUrlHostSameWhitelist + ",httpsUrl:" + isHttpsUrl);
        return isUrlHostSameWhitelist && isHttpsUrl;
    }

    public static boolean b(String str, List<String> list) {
        if (ArrayUtils.isEmpty(list)) {
            HwLog.e("SupportType", "checkIsUrlHostAndPathInWhiteList whiteList is empty");
            return false;
        }
        for (String str2 : list) {
            if (str2.endsWith("/")) {
                if (UriUtil.isUrlHostMatchWhitelist(str, str2) && str.startsWith(str2)) {
                    return true;
                }
            } else if (UriUtil.isUrlHostAndPathMatchWhitelist(str, str2)) {
                return true;
            }
        }
        HwLog.i("SupportType", "checkIsUrlHostAndPathInWhiteList : false");
        return false;
    }

    public static at f(String str) {
        at atVar = new at();
        try {
            a(atVar, new JSONObject(str));
            return atVar;
        } catch (JSONException e) {
            HwLog.e("SupportType", "parseSupportType exception " + HwLog.printException((Exception) e));
            return null;
        }
    }

    private static void a(at atVar, JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.has("whiteList") ? jSONObject.getJSONArray("whiteList") : null;
            if (jSONArray != null) {
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    atVar.f10908a.add(jSONArray.getString(i));
                }
            }
        } catch (JSONException e) {
            atVar.f10908a = null;
            HwLog.e("SupportType", "parseWhitelist exception " + HwLog.printException((Exception) e));
        }
    }
}
