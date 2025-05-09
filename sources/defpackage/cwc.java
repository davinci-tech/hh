package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.CommonUtil;
import health.compact.a.EzPluginManager;
import health.compact.a.Services;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class cwc {
    private static int a(int i) {
        if (i == -2 || i == 5 || i == 7 || i == 0 || i == 1 || i == 18 || i == 19 || i == 23 || i == 24 || i == 44 || i == 45) {
            return 2;
        }
        switch (i) {
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                return 2;
            default:
                return -1;
        }
    }

    private static int h(int i) {
        if (i == 2 || i == 3 || i == 20 || i == 21) {
            return 1;
        }
        if (i != 32) {
            switch (i) {
                case 8:
                case 9:
                    return 1;
                case 10:
                    break;
                default:
                    return -1;
            }
        }
        return 3;
    }

    public static int e(int i) {
        cvc pluginInfoByDeviceType = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByDeviceType(i);
        if (pluginInfoByDeviceType == null || pluginInfoByDeviceType.f() == null || pluginInfoByDeviceType.f().ah() == -1) {
            return -1;
        }
        return pluginInfoByDeviceType.f().ah();
    }

    public static boolean c(int i) {
        Map<String, String> g;
        msc c = EzPluginManager.a().c(i);
        if (c == null || c.g() == null || (g = c.g().g()) == null) {
            return false;
        }
        String str = g.get("upgrade_start");
        String str2 = g.get("upgrade_end");
        if (str == null || str2 == null) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            LogUtil.a("PluginCommonUtil", "isSupportUpdate mDeviceSupportStart = ", Long.valueOf(Long.parseLong(str)), ",time = ", Long.valueOf(currentTimeMillis), ",mDeviceSupportEnd = ", Long.valueOf(Long.parseLong(str2)));
            if (Long.parseLong(str) <= currentTimeMillis) {
                return Long.parseLong(str2) >= currentTimeMillis;
            }
            return false;
        } catch (NumberFormatException unused) {
            LogUtil.b("PluginCommonUtil", "isSupportUpdate Exception.");
            return false;
        }
    }

    public static boolean e(int i, boolean z, boolean z2) {
        msc c = EzPluginManager.a().c(i);
        if (c == null || c.g() == null) {
            return (!z || z2 || i == 12) ? false : true;
        }
        return a(c, i, z, z2);
    }

    private static boolean a(msc mscVar, int i, boolean z, boolean z2) {
        if (mscVar.g().i() == 1) {
            return true;
        }
        if (mscVar.g().i() != 2) {
            return (!z || z2 || i == 12) ? false : true;
        }
        LogUtil.a("PluginCommonUtil", "isDisplayUpdate support AutoDownload with WLAN is false");
        return false;
    }

    public static boolean e(Map<String, String> map) {
        if (map == null) {
            return true;
        }
        String b = b(map);
        if (TextUtils.isEmpty(b)) {
            return true;
        }
        int d = CommonUtil.d(BaseApplication.getContext());
        LogUtil.a("PluginCommonUtil", "isPublishVersion publishVersion : ", b, " currentVersion: ", Integer.valueOf(d));
        return e(b, d);
    }

    private static String b(Map<String, String> map) {
        LogUtil.a("PluginCommonUtil", "is domestic version");
        if (map.get("domestic") != null) {
            return map.get("domestic");
        }
        return null;
    }

    private static boolean e(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        for (String str2 : str.replace(" ", "").split(",")) {
            if (!TextUtils.isEmpty(str2) && c(str2, i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean c(String str, int i) {
        if (str.contains(Constants.LINK)) {
            String[] split = str.split(Constants.LINK);
            if (split.length != 2) {
                return false;
            }
            int c = jds.c(split[0], 10);
            int c2 = jds.c(split[1], 10);
            LogUtil.a("PluginCommonUtil", "isInPublishVersion beginVersion : ", Integer.valueOf(c), " endVersion: ", Integer.valueOf(c2));
            if (i >= c && i <= c2) {
                return true;
            }
        } else {
            int c3 = jds.c(str, 10);
            LogUtil.a("PluginCommonUtil", "isInPublishVersion numVersion : ", Integer.valueOf(c3));
            if (c3 == i) {
                return true;
            }
        }
        return false;
    }

    public static JSONObject d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("PluginCommonUtil", "getAiTipsProductByHiLinkId hiLinkDeviceId is empty");
            return null;
        }
        try {
            msc a2 = a(str);
            if (a2 == null) {
                LogUtil.h("PluginCommonUtil", "getAiTipsProductByHiLinkId ezPluginInfo is null");
                return null;
            }
            String string = new JSONObject(a2.g().e()).getString("ai_tips_product_" + str);
            if (TextUtils.isEmpty(string)) {
                LogUtil.h("PluginCommonUtil", "getAiTipsProductByHiLinkId hiLinkDeviceInfo is empty");
                return null;
            }
            return new JSONObject(string);
        } catch (JSONException unused) {
            LogUtil.b("PluginCommonUtil", "getAiTipsProductByHiLinkId JSONException");
            return null;
        }
    }

    private static msc a(String str) throws JSONException {
        List<msa> b = EzPluginManager.a().b();
        msc mscVar = null;
        if (b == null || b.isEmpty()) {
            LogUtil.h("PluginCommonUtil", "getEzPluginInfo indexInfoList is null or indexInfoList is empty");
            return null;
        }
        Iterator<msa> it = b.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            mscVar = b(it.next().b(), str);
            if (mscVar != null) {
                LogUtil.a("PluginCommonUtil", "getEzPluginInfo ezPluginInfo not null");
                break;
            }
        }
        return mscVar;
    }

    private static msc b(String str, String str2) throws JSONException {
        msc c = EzPluginManager.a().c(str);
        if (c == null) {
            LogUtil.h("PluginCommonUtil", "getPluginInfo pluginInfo is null");
            return null;
        }
        mrt g = c.g();
        if (g == null || TextUtils.isEmpty(g.e())) {
            LogUtil.h("PluginCommonUtil", "getPluginInfo pluginInfoForWear is null or aiTips is empty");
            return null;
        }
        if (new JSONObject(g.e()).has("ai_tips_product_" + str2)) {
            return c;
        }
        return null;
    }

    public static int b(int i) {
        int a2 = a(i);
        if (a2 == -1) {
            a2 = h(i);
        }
        if (a2 == -1) {
            a2 = d(i);
        }
        LogUtil.a("PluginCommonUtil", "getDeviceClassification() deviceType: ", Integer.valueOf(i), " deviceClassification: ", Integer.valueOf(a2));
        return a2;
    }

    private static int d(int i) {
        int e = e(i);
        if (e == 1) {
            return 2;
        }
        if (e == 2) {
            return 1;
        }
        return e == 5 ? 3 : -1;
    }
}
