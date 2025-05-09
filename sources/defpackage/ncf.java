package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.multisimservice.model.MultiSimDeviceInfo;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.activity.WebViewActivity;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.MagicBuild;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class ncf {
    private static final int[] c = {85, 91};

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str) || str.length() < 5) {
            return false;
        }
        String substring = str.substring(0, 5);
        return ("46000".equals(substring) || "46002".equals(substring) || "46004".equals(substring)) || ("46007".equals(substring) || "46008".equals(substring) || "46020".equals(substring));
    }

    public static boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("46001") || str.startsWith("46006") || str.startsWith("46009");
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("46003") || str.startsWith("46005") || str.startsWith("46011") || str.startsWith("46012");
    }

    public static void j(Context context) {
        if (context == null) {
            LogUtil.h("MultiSimUtils", "openNetWorkSettingView context is null");
            return;
        }
        try {
            context.startActivity(new Intent("android.settings.SETTINGS"));
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("MultiSimUtils", "openNetWorkSettingView ActivityNotFoundException");
        }
    }

    public static List<Map<String, Object>> a(Context context) {
        String[] strArr;
        ArrayList arrayList = new ArrayList(2);
        if (context == null) {
            return arrayList;
        }
        if (Build.VERSION.SDK_INT > 29) {
            strArr = new String[]{"android.permission.READ_PHONE_NUMBERS"};
        } else {
            strArr = new String[]{"android.permission.READ_PHONE_STATE"};
        }
        if (!jdi.c(context, strArr)) {
            LogUtil.h("MultiSimUtils", "getDefaultSimCardInfo missing phonePermission");
            return arrayList;
        }
        List<SubscriptionInfo> activeSubscriptionInfoList = SubscriptionManager.from(context).getActiveSubscriptionInfoList();
        if (activeSubscriptionInfoList == null || activeSubscriptionInfoList.size() == 0) {
            LogUtil.a("MultiSimUtils", "getActiveSimCardInfo can not get subscriptionInfoList");
            e(context, arrayList);
            return arrayList;
        }
        for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
            HashMap hashMap = new HashMap(16);
            StringBuilder sb = new StringBuilder();
            sb.append(subscriptionInfo.getMcc());
            String str = "";
            sb.append("");
            String sb2 = sb.toString();
            String str2 = subscriptionInfo.getMnc() + "";
            CharSequence carrierName = subscriptionInfo.getCarrierName();
            if (carrierName != null) {
                str = carrierName.toString();
            }
            int simSlotIndex = subscriptionInfo.getSimSlotIndex();
            hashMap.put("number", subscriptionInfo.getNumber());
            hashMap.put("imsi", e(context, subscriptionInfo.getSubscriptionId()));
            hashMap.put("mccmnc", sb2 + str2);
            hashMap.put("spn", str);
            hashMap.put("slotId", Integer.valueOf(simSlotIndex));
            LogUtil.a("MultiSimUtils", "getActiveSimCardInfo slotId = ", Integer.valueOf(simSlotIndex));
            arrayList.add(hashMap);
        }
        a(arrayList);
        return arrayList;
    }

    private static void a(List<Map<String, Object>> list) {
        Collections.sort(list, new Comparator<Map<String, Object>>() { // from class: ncf.1
            @Override // java.util.Comparator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public int compare(Map<String, Object> map, Map<String, Object> map2) {
                Integer num;
                int i = 0;
                if (!(map.get("slotId") instanceof Integer)) {
                    num = 0;
                } else {
                    num = (Integer) map.get("slotId");
                }
                if (map2.get("slotId") instanceof Integer) {
                    i = (Integer) map2.get("slotId");
                }
                return num.compareTo(i);
            }
        });
    }

    private static void e(Context context, List<Map<String, Object>> list) {
        String str;
        String subscriberId;
        LogUtil.a("MultiSimUtils", "getDefaultSimCardInfo enter");
        Object systemService = context.getSystemService("phone");
        if (!(systemService instanceof TelephonyManager)) {
            LogUtil.h("MultiSimUtils", "getDefaultSimCardInfo telephonyManagerObject is not instanceof TelephonyManager");
            return;
        }
        TelephonyManager telephonyManager = (TelephonyManager) systemService;
        if (telephonyManager.getSimState() == 1) {
            LogUtil.h("MultiSimUtils", "sim is absent");
            return;
        }
        Map<String, Object> hashMap = new HashMap<>(16);
        try {
            str = telephonyManager.getLine1Number();
        } catch (SecurityException unused) {
            LogUtil.b("MultiSimUtils", "getDefaultSimCardInfo SecurityException.");
            str = "";
        }
        if (CommonUtil.bh() || e()) {
            subscriberId = telephonyManager.getSubscriberId();
        } else {
            subscriberId = telephonyManager.getSimOperator();
        }
        Object networkOperator = telephonyManager.getNetworkOperator();
        Object networkOperatorName = telephonyManager.getNetworkOperatorName();
        hashMap.put("number", str);
        hashMap.put("imsi", subscriberId);
        hashMap.put("mccmnc", networkOperator);
        hashMap.put("spn", networkOperatorName);
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(subscriberId)) {
            LogUtil.h("MultiSimUtils", "getDefaultSimCardInfo number and subscriberId is empty");
        } else {
            list.add(hashMap);
        }
    }

    public static String e(Context context, int i) {
        if (context == null) {
            LogUtil.h("MultiSimUtils", "context is null");
            return "";
        }
        Object systemService = context.getSystemService("phone");
        if (!(systemService instanceof TelephonyManager)) {
            LogUtil.h("MultiSimUtils", "getImsi telephonyManagerObject is not instanceof TelephonyManager");
            return "";
        }
        TelephonyManager createForSubscriptionId = ((TelephonyManager) systemService).createForSubscriptionId(i);
        if (createForSubscriptionId == null) {
            return "";
        }
        if (CommonUtil.bh() || e()) {
            return createForSubscriptionId.getSubscriberId();
        }
        return createForSubscriptionId.getSimOperator();
    }

    public static String b(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("MultiSimUtils", "context or imsi is null");
            return "";
        }
        if (b(str)) {
            return context.getResources().getString(R.string._2130847988_res_0x7f0228f4);
        }
        if (d(str)) {
            return context.getResources().getString(R.string._2130847989_res_0x7f0228f5);
        }
        return a(str) ? context.getResources().getString(R.string._2130847990_res_0x7f0228f6) : "";
    }

    public static void a(Context context, String str, String str2) {
        Intent launchIntentForPackage;
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("MultiSimUtils", "startAppByPackageName context or imsi is null");
            return;
        }
        try {
            if (b(str)) {
                launchIntentForPackage = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL);
                launchIntentForPackage.setData(Uri.parse(str2));
            } else if (d(str)) {
                if (!TextUtils.isEmpty(str2)) {
                    launchIntentForPackage = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL);
                    launchIntentForPackage.setData(Uri.parse(i(context, str2)));
                } else {
                    LogUtil.h("MultiSimUtils", "startAppByPackageName operator is default CUCC");
                    launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage("com.sinovatech.unicom.ui");
                }
            } else if (a(str)) {
                if (!TextUtils.isEmpty(str2)) {
                    launchIntentForPackage = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL);
                    launchIntentForPackage.setData(Uri.parse("ctclient://startapp/android/open?LinkType=5&Link=" + str2));
                } else {
                    LogUtil.h("MultiSimUtils", "startAppByPackageName operator is default CTCC");
                    launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage("com.ct.client");
                }
            } else {
                LogUtil.h("MultiSimUtils", "startAppByPackageName is not operator imsi");
                return;
            }
            if (launchIntentForPackage != null) {
                context.startActivity(launchIntentForPackage);
            } else {
                LogUtil.h("MultiSimUtils", "startAppByPackageName intent is null");
            }
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("MultiSimUtils", "startAppByPackageName ActivityNotFoundException");
        }
    }

    public static boolean i(String str) {
        return b(str) || d(str) || a(str);
    }

    public static String c(Context context, String str, int i) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("MultiSimUtils", "getOperatorAppName context or imsi is null");
            return "";
        }
        if (b(str)) {
            if (i == 1) {
                return context.getString(R.string._2130847994_res_0x7f0228fa);
            }
            return context.getString(R.string._2130847988_res_0x7f0228f4);
        }
        if (d(str)) {
            return context.getString(R.string._2130847995_res_0x7f0228fb);
        }
        return a(str) ? context.getString(R.string._2130847996_res_0x7f0228fc) : "";
    }

    public static String a(String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            return b(str) ? i == 1 ? "/appdl/C107024875" : "/appdl/C10097559" : d(str) ? "/appdl/C10059351" : a(str) ? "/appdl/C38660" : "";
        }
        LogUtil.h("MultiSimUtils", "getOperatorAppId imsi is null");
        return "";
    }

    public static String e(String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            return b(str) ? i == 1 ? "com.cmic.heduohao" : "com.greenpoint.android.mc10086.activity" : d(str) ? "com.sinovatech.unicom.ui" : a(str) ? "com.ct.client" : "";
        }
        LogUtil.h("MultiSimUtils", "getOperatorPackageName imsi is null");
        return "";
    }

    private static int e(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("MultiSimUtils", "getAppVersionCode context or packageName is null");
            return 0;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
            return 0;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("MultiSimUtils", "getAppVersionCode NameNotFoundException.");
            return 0;
        }
    }

    public static boolean a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("MultiSimUtils", "isNewOperatorVersion context or packageName is null");
            return false;
        }
        int e = e(context, str);
        LogUtil.a("MultiSimUtils", "isNewOperatorVersion versionCode = ", Integer.valueOf(e));
        return "com.cmic.heduohao".equals(str) ? e >= 2022051716 : "com.sinovatech.unicom.ui".equals(str) ? e >= 102 : "com.ct.client".equals(str) ? e >= 8600 : "com.greenpoint.android.mc10086.activity".equals(str) && e >= 87000;
    }

    private static String i(Context context, String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("oemName", "huawei");
        hashMap.put("packageName", "com.huawei.com");
        hashMap.put("serviceType", "1");
        hashMap.put("terminalType", "2");
        hashMap.put("machineType", "57");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        for (Map.Entry entry : hashMap.entrySet()) {
            String stringBuffer2 = stringBuffer.toString();
            if (!stringBuffer2.contains((CharSequence) entry.getKey())) {
                if (stringBuffer2.contains("?")) {
                    stringBuffer.append("&" + ((String) entry.getKey()) + "=" + ((String) entry.getValue()));
                } else {
                    stringBuffer.append("?" + ((String) entry.getKey()) + "=" + ((String) entry.getValue()));
                }
            }
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("openType", "url");
            jSONObject.put("title", context.getResources().getString(R.string._2130850304_res_0x7f023200));
            jSONObject.put("openUrl", stringBuffer.toString());
        } catch (JSONException unused) {
            LogUtil.b("MultiSimUtils", "getCuccUrl JSONException");
        }
        return "chinaunicom://?open=" + jSONObject.toString();
    }

    public static Bitmap csL_(boolean z, String str, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("MultiSimUtils", "getOperatorBitmap imsi is null");
            return null;
        }
        if (jfu.m(d())) {
            return csM_(null, z, str, i, d());
        }
        LogUtil.h("MultiSimUtils", "getOperatorBitmap is not plugin download");
        return null;
    }

    private static Bitmap csM_(Bitmap bitmap, boolean z, String str, int i, int i2) {
        String j = jfu.j(i2);
        LogUtil.a("MultiSimUtils", "getOperatorBitmapgFromPlugin uuid = ", j);
        if (((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(j)) {
            cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(j);
            if (pluginInfoByUuid != null) {
                String b = b(pluginInfoByUuid, z, str, i);
                LogUtil.a("MultiSimUtils", "getOperatorBitmapgFromPlugin imageName = ", b);
                return !TextUtils.isEmpty(b) ? ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImageName(pluginInfoByUuid, b) : bitmap;
            }
            LogUtil.h("MultiSimUtils", "getOperatorBitmapgFromPlugin devicePluginInfo is null");
            return bitmap;
        }
        LogUtil.h("MultiSimUtils", "getOperatorBitmapgFromPlugin isPluginAvaiable is false");
        return bitmap;
    }

    private static String b(cvc cvcVar, boolean z, String str, int i) {
        boolean m = LanguageUtil.m(BaseApplication.getContext());
        if (b(str)) {
            if (z) {
                if (m) {
                    return cvcVar.f().k();
                }
                return cvcVar.f().bc();
            }
            if (i == 1) {
                if (m) {
                    return cvcVar.f().n();
                }
                return cvcVar.f().o();
            }
            if (m) {
                return cvcVar.f().m();
            }
            return cvcVar.f().o();
        }
        if (d(str)) {
            if (z) {
                if (m) {
                    return cvcVar.f().y();
                }
                return cvcVar.f().bc();
            }
            if (m) {
                return cvcVar.f().u();
            }
            return cvcVar.f().x();
        }
        if (!a(str)) {
            LogUtil.h("MultiSimUtils", "getImageName is not operator imsi");
            return "";
        }
        if (z) {
            if (m) {
                return cvcVar.f().s();
            }
            return cvcVar.f().bc();
        }
        if (m) {
            return cvcVar.f().t();
        }
        return cvcVar.f().r();
    }

    public static String d(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("MultiSimUtils", "getOperatoUrl imsi is null");
            return "";
        }
        if (jfu.m(d())) {
            String j = jfu.j(d());
            LogUtil.a("MultiSimUtils", "getOperatoUrl uuid = ", j);
            if (((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(j)) {
                cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(j);
                if (pluginInfoByUuid != null) {
                    return d(pluginInfoByUuid, str, i);
                }
                LogUtil.h("MultiSimUtils", "getOperatoUrl devicePluginInfo is null");
            } else {
                LogUtil.h("MultiSimUtils", "getOperatoUrl isPluginAvaiable is false");
            }
        } else {
            LogUtil.h("MultiSimUtils", "getOperatoUrl is not plugin download");
        }
        return "";
    }

    private static String d(cvc cvcVar, String str, int i) {
        if (b(str)) {
            return i == 1 ? "heduohao://action=GoToApplyEsim#huawei" : cvcVar.f().p();
        }
        if (d(str)) {
            if (i == 1) {
                return cvcVar.f().ab();
            }
            return cvcVar.f().w();
        }
        if (!a(str)) {
            LogUtil.h("MultiSimUtils", "getOperatorUrl is not operator imsi");
            return "";
        }
        if (i == 1) {
            return cvcVar.f().v();
        }
        return cvcVar.f().q();
    }

    public static String c(String str) {
        String j = jfu.j(d());
        return mrv.d + j + File.separator + j + File.separator + "declaration" + File.separator + str;
    }

    public static boolean b() {
        DeviceCapability d = cvs.d();
        if (d != null) {
            return d.isSupportNewEsim();
        }
        return false;
    }

    public static String d(Context context) {
        if (Utils.o()) {
            return String.format(Locale.ENGLISH, "/handbook/Jumppage/EMUI8.0/C001B001/en-US/index.html?lang=%s&devicetype=eSIM_GLL", CommonUtil.u());
        }
        return LanguageUtil.m(context) ? "/SmartWear/eSIM_GLL/EMUI8.0/C001B001/zh-CN/index.html" : "/SmartWear/eSIM_GLL/EMUI8.0/C001B001/en-US/index.html";
    }

    public static void e(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str2)) {
            LogUtil.h("MultiSimUtils", "startWebView context is null or url is empty");
            return;
        }
        try {
            Intent intent = new Intent(context, (Class<?>) WebViewActivity.class);
            intent.putExtra("url", str2);
            intent.putExtra("title", str);
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("MultiSimUtils", "startWebView ActivityNotFoundException");
        }
    }

    public static void a(String str, String str2) {
        SharedPreferenceManager.e(BaseApplication.getContext(), "share_preference_esim_accode", "KEY_ACCODE", str, (StorageParams) null);
        SharedPreferenceManager.e(BaseApplication.getContext(), "share_preference_esim_accode", "KEY_ACCODE_SAVE_TIME", str2, (StorageParams) null);
    }

    public static void b(String str, boolean z) {
        String value;
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        String valueOf = String.valueOf(System.currentTimeMillis());
        if (z) {
            linkedHashMap.put("click", "1");
            linkedHashMap.put("startTime", valueOf);
            linkedHashMap.put("openMethod", str);
            value = OperationKey.HEALTH_APP_ESIM_MANAGER_60010001.value();
        } else {
            linkedHashMap.put("endTime", valueOf);
            value = OperationKey.HEALTH_APP_ESIM_MANAGER_60010002.value();
        }
        OpAnalyticsUtil.getInstance().setEvent2nd(value, linkedHashMap);
    }

    public static void c(int i, boolean z) {
        String value;
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        linkedHashMap.put("status", String.valueOf(i));
        if (z) {
            value = OperationKey.HEALTH_APP_ESIM_MANAGER_60010003.value();
        } else {
            value = OperationKey.HEALTH_APP_ESIM_MANAGER_60010004.value();
        }
        OpAnalyticsUtil.getInstance().setEvent2nd(value, linkedHashMap);
    }

    public static boolean h(String str) {
        if (!TextUtils.isEmpty(str)) {
            return "04".equals(str);
        }
        LogUtil.h("MultiSimUtils", "isTs43Es openMethod is empty");
        return false;
    }

    public static String e(Context context) {
        String an;
        if (CommonUtil.br()) {
            an = CommonUtil.h();
        } else if (CommonUtil.bh()) {
            an = CommonUtil.m(context);
        } else {
            an = CommonUtil.an();
        }
        if (TextUtils.isEmpty(an)) {
            String replace = UUID.randomUUID().toString().replace(Constants.LINK, "");
            LogUtil.a("MultiSimUtils", "getPhoneTerminalId random uuid");
            return replace;
        }
        LogUtil.a("MultiSimUtils", "getPhoneTerminalId deviceId is not empty");
        return an;
    }

    public static boolean csO_(Intent intent) {
        if (intent == null) {
            LogUtil.h("MultiSimUtils", "isDeviceSupportCurFunction intent is null");
            return false;
        }
        DeviceInfo deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
        if (deviceInfo == null) {
            LogUtil.h("MultiSimUtils", "isDeviceSupportCurFunction deviceInfo is null. ");
            return false;
        }
        Map<String, DeviceCapability> a2 = jfq.c().a(1, deviceInfo.getDeviceIdentify(), "MultiSimUtils");
        if (a2 != null && !a2.isEmpty()) {
            DeviceCapability deviceCapability = a2.get(deviceInfo.getDeviceIdentify());
            if (deviceCapability != null && (deviceCapability.isSupportNewEsim() || deviceCapability.isSupportEsim() || deviceCapability.isSupportMultiSim())) {
                return true;
            }
        } else {
            LogUtil.h("MultiSimUtils", "isDeviceSupportCurFunction queryDeviceCapability is null. ");
        }
        return false;
    }

    public static boolean f(Context context) {
        if (context == null) {
            LogUtil.h("MultiSimUtils", "isSameImsi context is null");
            return false;
        }
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), "share_preference_esim_accode", "imsi");
        if (TextUtils.isEmpty(b)) {
            LogUtil.h("MultiSimUtils", "isSameImsi oldImsi is empty");
            return false;
        }
        List<Map<String, Object>> a2 = a(context);
        if (a2 != null && a2.size() > 0) {
            Iterator<Map<String, Object>> it = a2.iterator();
            while (it.hasNext()) {
                Object obj = it.next().get("imsi");
                String str = obj instanceof String ? (String) obj : "";
                if (!TextUtils.isEmpty(str) && TextUtils.equals(knl.d(str), b)) {
                    LogUtil.a("MultiSimUtils", "imsi is equals");
                    return true;
                }
            }
        }
        return false;
    }

    public static int b(Context context) {
        SubscriptionManager.from(context);
        int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
        List<SubscriptionInfo> activeSubscriptionInfoList = SubscriptionManager.from(context).getActiveSubscriptionInfoList();
        if (activeSubscriptionInfoList == null || activeSubscriptionInfoList.size() == 0) {
            LogUtil.h("MultiSimUtils", "getPrimaryCardSlotId can not get subscriptionInfoList");
            return 0;
        }
        for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
            if (subscriptionInfo.getSubscriptionId() == defaultDataSubscriptionId) {
                return subscriptionInfo.getSimSlotIndex();
            }
        }
        return 0;
    }

    public static void i(Context context) {
        PackageInfo packageInfo;
        if (context == null) {
            LogUtil.h("MultiSimUtils", "startWearOsApp context is null");
            return;
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            packageInfo = packageManager.getPackageInfo("com.google.android.wearable.app.cn", 0);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("MultiSimUtils", "onClick() androidWearNameCn, error");
            try {
                packageInfo = packageManager.getPackageInfo("com.google.android.wearable.app", 0);
            } catch (PackageManager.NameNotFoundException unused2) {
                LogUtil.b("MultiSimUtils", "onClick() androidWearName, error");
                packageInfo = null;
            }
        }
        if (packageInfo == null) {
            LogUtil.h("MultiSimUtils", "startWearOsApp packageInfo is null");
        } else {
            csR_(context, packageInfo);
        }
    }

    private static void csR_(Context context, PackageInfo packageInfo) {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(packageInfo.packageName);
        ResolveInfo next = context.getPackageManager().queryIntentActivities(intent, 0).iterator().next();
        if (next != null) {
            String str = next.activityInfo.packageName;
            String str2 = next.activityInfo.name;
            try {
                Intent intent2 = new Intent("android.intent.action.MAIN");
                intent2.addCategory("android.intent.category.LAUNCHER");
                intent2.setComponent(new ComponentName(str, str2));
                context.startActivity(intent2);
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("MultiSimUtils", "startWearOs ActivityNotFoundException");
            }
        }
    }

    public static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("MultiSimUtils", "getContainsEsimUpperCaseText text is empty");
            return "";
        }
        if (!str.contains("eSIM")) {
            return str;
        }
        if (str.startsWith("eSIM")) {
            return "eSIM" + str.substring(str.indexOf("eSIM") + 4).toUpperCase(Locale.ENGLISH);
        }
        if (str.endsWith("eSIM")) {
            return str.substring(0, str.indexOf("eSIM")).toUpperCase(Locale.ENGLISH) + "eSIM";
        }
        int indexOf = str.indexOf("eSIM");
        return str.substring(0, indexOf).toUpperCase(Locale.ENGLISH) + "eSIM" + str.substring(indexOf + 4).toUpperCase(Locale.ENGLISH);
    }

    public static void csP_(Context context, View view, int i) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (context == null || view == null) {
            LogUtil.h("MultiSimUtils", "setViewPosition context or view is null");
            return;
        }
        int j = nsn.j();
        int r = nsn.r(context);
        int i2 = ((j - r) * 2) / 5;
        int i3 = i / 2;
        LogUtil.a("MultiSimUtils", "setViewPosition screenHeight = ", Integer.valueOf(j), ",statusBarHeight = ", Integer.valueOf(r));
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        } else {
            marginLayoutParams = new ViewGroup.MarginLayoutParams(layoutParams);
        }
        marginLayoutParams.setMargins(0, i2 - i3, 0, 0);
        marginLayoutParams.width = i;
        marginLayoutParams.height = i;
        view.setLayoutParams(marginLayoutParams);
    }

    public static void g(Context context) {
        boolean z = MagicBuild.f13130a;
        LogUtil.a("MultiSimUtils", "savePermissionException isNewHonorRemoveMagic = ", Boolean.valueOf(z));
        if (z) {
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10008), "is_esim_permission_exception", "", (StorageParams) null);
            if (context == null) {
                LogUtil.h("MultiSimUtils", "savePermissionException context is null");
                return;
            }
            Object systemService = context.getSystemService("phone");
            if (!(systemService instanceof TelephonyManager)) {
                LogUtil.h("MultiSimUtils", "savePermissionException object is not instanceof TelephonyManager");
                return;
            }
            try {
                ((TelephonyManager) systemService).getSubscriberId();
            } catch (SecurityException unused) {
                LogUtil.b("MultiSimUtils", "savePermissionException SecurityException");
                SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10008), "is_esim_permission_exception", "is_esim_permission_exception", (StorageParams) null);
            }
        }
    }

    public static boolean e() {
        if (!CommonUtil.bf()) {
            return false;
        }
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10008), "is_esim_permission_exception");
        LogUtil.a("MultiSimUtils", "isNewHonor savedPermissionStatus = ", b);
        return TextUtils.isEmpty(b);
    }

    public static nbv e(String str, String str2) {
        nbv nbvVar = new nbv();
        nbvVar.b(str);
        nbvVar.a(str2);
        return nbvVar;
    }

    public static nbw e(MultiSimDeviceInfo multiSimDeviceInfo) {
        nbw nbwVar = new nbw();
        if (multiSimDeviceInfo == null) {
            LogUtil.h("MultiSimUtils", "getPairedDevice multiSimDeviceInfo is null");
            return nbwVar;
        }
        nbwVar.d(3);
        nbwVar.e(multiSimDeviceInfo.getEID());
        nbwVar.c(multiSimDeviceInfo.getProductName());
        nbwVar.f(multiSimDeviceInfo.getDeviceSerialNumber());
        nbwVar.b(multiSimDeviceInfo.getDeviceIMEI());
        DeviceInfo a2 = jpt.a("MultiSimUtils");
        if (a2 != null) {
            String deviceModel = a2.getDeviceModel();
            String softVersion = a2.getSoftVersion();
            LogUtil.a("MultiSimUtils", "getPairedDevice deviceModel = ", deviceModel, ", deviceSoftVersion = ", softVersion);
            nbwVar.a(deviceModel);
            nbwVar.d(softVersion);
        }
        return nbwVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0079 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<java.util.Map<java.lang.String, java.lang.Object>> c(android.content.Context r11) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 2
            r0.<init>(r1)
            java.lang.String r1 = "MultiSimUtils"
            if (r11 != 0) goto L14
            java.lang.String r11 = "getSupportAccountManageSimCardList context is null"
            java.lang.Object[] r11 = new java.lang.Object[]{r11}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r11)
            return r0
        L14:
            java.util.List r2 = a(r11)
            if (r2 == 0) goto L7d
            boolean r3 = r2.isEmpty()
            if (r3 == 0) goto L21
            goto L7d
        L21:
            r3 = 0
            r4 = r3
        L23:
            int r5 = r2.size()
            if (r4 >= r5) goto L7c
            java.lang.Object r5 = r2.get(r4)
            java.util.Map r5 = (java.util.Map) r5
            if (r5 != 0) goto L3b
            java.lang.String r5 = "getSupportAccountManageSimCardList map is null"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r5)
            goto L79
        L3b:
            java.lang.String r6 = "slotId"
            boolean r7 = r5.containsKey(r6)
            if (r7 == 0) goto L52
            java.lang.Object r6 = r5.get(r6)
            boolean r7 = r6 instanceof java.lang.Integer
            if (r7 == 0) goto L52
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            goto L53
        L52:
            r6 = r4
        L53:
            java.lang.String r7 = "ODSA_SUPPORT_CHANGE_MANAGE_SUBSCRIPTION"
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r3)
            java.lang.Boolean r7 = defpackage.lop.a(r11, r7, r6, r8)
            boolean r7 = r7.booleanValue()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.String r8 = ", isDisplay = "
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r7)
            java.lang.String r10 = "getSupportAccountManageSimCardList slotId = "
            java.lang.Object[] r6 = new java.lang.Object[]{r10, r6, r8, r9}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r6)
            if (r7 == 0) goto L79
            r0.add(r5)
        L79:
            int r4 = r4 + 1
            goto L23
        L7c:
            return r0
        L7d:
            java.lang.String r11 = "getSupportAccountManageSimCardList sim cards is none"
            java.lang.Object[] r11 = new java.lang.Object[]{r11}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r11)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ncf.c(android.content.Context):java.util.List");
    }

    public static ArrayList<String> d(Context context, String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (context == null) {
            LogUtil.h("MultiSimUtils", "getStandaloneNumCardTypeList context is null.");
            return arrayList;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("MultiSimUtils", "getStandaloneNumCardTypeList operatorName is empty.");
            return arrayList;
        }
        LogUtil.a("MultiSimUtils", "getStandaloneNumCardTypeList operatorName = ", str);
        if (str.equals(context.getString(R.string._2130847988_res_0x7f0228f4))) {
            arrayList.add(HiAnalyticsConstant.KeyAndValue.NUMBER_01);
            arrayList.add("07");
        } else if (str.equals(context.getString(R.string._2130847989_res_0x7f0228f5))) {
            arrayList.add(HiAnalyticsConstant.KeyAndValue.NUMBER_01);
        } else if (str.equals(context.getString(R.string._2130847990_res_0x7f0228f6))) {
            LogUtil.a("MultiSimUtils", "getStandaloneNumCardTypeList provider ctcc.");
        } else {
            LogUtil.h("MultiSimUtils", "getStandaloneNumCardTypeList else branch.");
        }
        return arrayList;
    }

    public static int d() {
        DeviceInfo a2 = jpt.a("MultiSimUtils");
        if (a2 == null) {
            return 57;
        }
        int i = 0;
        while (true) {
            int[] iArr = c;
            if (i >= iArr.length) {
                return 57;
            }
            if (a2.getProductType() == iArr[i]) {
                return 85;
            }
            i++;
        }
    }

    public static String c(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("MultiSimUtils", "getHotLine context is null or operatorName is empty.");
            return "";
        }
        if (str.equals(context.getString(R.string._2130847988_res_0x7f0228f4))) {
            return "10086";
        }
        if (str.equals(context.getString(R.string._2130847989_res_0x7f0228f5))) {
            return "10010";
        }
        if (str.equals(context.getString(R.string._2130847990_res_0x7f0228f6))) {
            return context.getString(R.string._2130848141_res_0x7f02298d);
        }
        LogUtil.h("MultiSimUtils", "getHotLine else branch.");
        return "";
    }

    public static void csQ_(Activity activity, int i) {
        if (activity == null) {
            LogUtil.h("MultiSimUtils", "startAppSettingActivity, context is null");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        activity.startActivityForResult(intent, i);
    }

    public static void csN_(Activity activity, int i) {
        if (activity == null) {
            LogUtil.h("MultiSimUtils", "goToNetWorkSettingPage null");
        } else {
            activity.startActivityForResult(new Intent("android.settings.SETTINGS"), i);
        }
    }
}
