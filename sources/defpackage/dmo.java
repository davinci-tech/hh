package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.hms.framework.common.ExceptionCode;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.utils.AppTypeUtils;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.GRSManager;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
class dmo {

    /* renamed from: a, reason: collision with root package name */
    private static String f11717a = null;
    private static Map<Integer, Long> c = null;
    private static long d = 0;
    private static final boolean e = false;

    dmo() {
    }

    static String a(List<Integer> list, String str, int i) {
        b(list, i);
        String e2 = e(list, i);
        if (e2 == null) {
            ReleaseLogUtil.b("MarketingRequestUtil", "doRequestSync failed, requestUrl is null!!!");
            return "";
        }
        long currentTimeMillis = System.currentTimeMillis();
        String str2 = (String) lqi.d().b(e2, a(), c(str), String.class);
        long currentTimeMillis2 = System.currentTimeMillis();
        LogUtil.c("MarketingRequestUtil", "doRequestSync(" + list + ", page:" + i + ") finished, time cost: " + (currentTimeMillis2 - currentTimeMillis) + ", length: " + (str2 == null ? 0 : str2.length()));
        return str2;
    }

    private static String e(List<Integer> list, int i) {
        if (list == null || list.isEmpty()) {
            LogUtil.e("MarketingRequestUtil", "positionIdList error, instance: " + list);
            return null;
        }
        StringBuilder sb = new StringBuilder(d());
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            if (list.size() == 1) {
                sb.append("/positionservice/v1/position/resourcesAnon?");
                sb.append(a(list, i));
            } else {
                sb.append("/positionservice/v1/resourcesAnon?");
                sb.append(d(list));
            }
        } else if (list.size() == 1) {
            sb.append("/positionservice/v1/position/resources?");
            sb.append(a(list, i));
        } else {
            sb.append("/positionservice/v1/resources?");
            sb.append(d(list));
        }
        return sb.toString();
    }

    private static String a(List<Integer> list, int i) {
        return "&limit=500&page=" + i + "&positionId=" + list.get(0);
    }

    private static String d(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            sb.append("&positionIds=");
            sb.append(intValue);
        }
        return sb.toString();
    }

    private static String d() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - d > 300000) {
            d = currentTimeMillis;
            f11717a = GRSManager.a(BaseApplication.getContext()).getUrl("marketingUrl");
            LogUtil.c("MarketingRequestUtil", "marketing url updated, time cost: " + (System.currentTimeMillis() - currentTimeMillis) + ", url: " + f11717a);
        }
        return f11717a;
    }

    private static HashMap<String, String> a() {
        Context context = BaseApplication.getContext();
        HashMap<String, String> hashMap = new HashMap<>(16);
        if (!LoginInit.getInstance(context).isBrowseMode()) {
            hashMap.put("x-huid", LoginInit.getInstance(context).getAccountInfo(1011));
            hashMap.put(CloudParamKeys.X_TOKEN_TYPE, String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
            hashMap.put(CloudParamKeys.X_TOKEN, LoginInit.getInstance(context).getAccountInfo(1008));
        }
        hashMap.put("Content-type", "application/json");
        hashMap.put(CloudParamKeys.X_CLIENT_VERSION, CommonUtil.c(context));
        hashMap.put(CloudParamKeys.X_SITE_ID, LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009));
        hashMap.put(CloudParamKeys.X_APP_ID, BaseApplication.getAppPackage());
        String deviceId = LoginInit.getInstance(context).getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = "clientnull";
        }
        hashMap.put(CloudParamKeys.X_DEVICE_ID, deviceId);
        hashMap.put(CloudParamKeys.X_DEVICE_TYPE, LoginInit.getInstance(BaseApplication.getContext()).getDeviceType());
        hashMap.put(CloudParamKeys.X_TS, String.valueOf(System.currentTimeMillis()));
        hashMap.put(CloudParamKeys.X_APP_TYPE, String.valueOf(AppTypeUtils.getAppType()));
        hashMap.put("x-caller-trace-id", String.valueOf(System.currentTimeMillis()) + Math.random());
        return hashMap;
    }

    private static HashMap<String, String> c(String str) {
        Context context = BaseApplication.getContext();
        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put(CloudParamKeys.CLIENT_TYPE, e());
        hashMap.put("clientVersion", e(context));
        hashMap.put("language", mtj.e(null));
        hashMap.put("countryCode", LoginInit.getInstance(context).getAccountInfo(1010));
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("extendMap", str);
            LogUtil.c("MarketingRequestUtil", "getCommonParams extendParam: ", str);
        }
        return hashMap;
    }

    private static String e(Context context) {
        String e2 = CommonUtil.e(context);
        return e2.contains(Constants.LINK) ? e2.substring(0, e2.indexOf(Constants.LINK)) : e2;
    }

    private static String e() {
        if (!EnvironmentInfo.k()) {
            return nsn.ae(BaseApplication.getContext()) ? "5" : SystemInfo.a() ? "1" : SystemInfo.d() ? SystemInfo.h() ? "2" : "1" : "3";
        }
        LogUtil.c("MarketingRequestUtil", "getClientType is mobile app engine");
        return "1";
    }

    private static void b(List<Integer> list, int i) {
        if (e) {
            long currentTimeMillis = System.currentTimeMillis();
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue = it.next().intValue() + (ExceptionCode.CRASH_EXCEPTION * i);
                if (c.containsKey(Integer.valueOf(intValue)) && c.get(Integer.valueOf(intValue)).longValue() - currentTimeMillis < 60000) {
                    LogUtil.a("MarketingRequestUtil", "request same data too often!!!, id: " + intValue);
                }
                c.put(Integer.valueOf(intValue), Long.valueOf(currentTimeMillis));
            }
        }
    }
}
