package com.huawei.openalliance.ad.utils;

import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.MapKeyNames;
import com.huawei.openalliance.ad.constant.OsType;
import com.huawei.openalliance.ad.constant.RTCMethods;
import com.huawei.openalliance.ad.constant.Scheme;
import com.huawei.openalliance.ad.constant.WhiteListPkgList;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.hz;
import com.huawei.openalliance.ad.ib;
import com.huawei.openalliance.ad.ic;
import com.huawei.openalliance.ad.ie;
import com.huawei.openalliance.ad.ik;
import com.huawei.openalliance.ad.il;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.inter.data.AdLandingPageData;
import com.huawei.openalliance.ad.inter.data.IAd;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.iq;
import com.huawei.openalliance.ad.ms;
import com.huawei.openalliance.ad.os;
import com.huawei.opendevice.open.TransparencyStatementActivity;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public abstract class ao {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<Integer, Integer> f7587a;

    public static boolean j() {
        return true;
    }

    private static float r(Context context) {
        try {
            return Settings.System.getFloat(context.getContentResolver(), "font_scale", -1.0f);
        } catch (Throwable th) {
            ho.c("HiAdTools", "get font err: %s", th.getClass().getSimpleName());
            return -1.0f;
        }
    }

    public static boolean q(Context context) {
        return d.c(context) >= 600;
    }

    public static boolean p(Context context) {
        if (b(context)) {
            return fh.b(context).aL();
        }
        return false;
    }

    public static boolean o(Context context) {
        return m(context) >= 1.3f;
    }

    public static boolean n(Context context) {
        return m(context) >= 1.75f;
    }

    public static float m(Context context) {
        Configuration configuration;
        if (context == null) {
            return -1.0f;
        }
        float r = r(context);
        if (r > 0.0f) {
            return r;
        }
        Resources resources = context.getResources();
        if (resources == null || (configuration = resources.getConfiguration()) == null) {
            return -1.0f;
        }
        return configuration.fontScale;
    }

    public static boolean l(Context context) {
        if (context == null || Build.VERSION.SDK_INT < 29) {
            return false;
        }
        Object systemService = context.getSystemService("uimode");
        return (systemService instanceof UiModeManager) && ((UiModeManager) systemService).getNightMode() == 2;
    }

    public static boolean k(Context context) {
        try {
            Intent intent = new Intent(Constants.GMS_AD_SETTING_ACTION);
            intent.addFlags(268435456);
            intent.setPackage("com.google.android.gms");
            intent.setClipData(Constants.CLIP_DATA);
            context.startActivity(intent);
            return true;
        } catch (Throwable unused) {
            ho.d("HiAdTools", "open GMS ads setting exception.");
            return false;
        }
    }

    public static boolean j(Context context) {
        String m = d.m(context);
        Integer h = cz.h(m);
        if (h != null && h.intValue() >= 40000300) {
            return true;
        }
        ho.b("HiAdTools", "hms not installed or low version, current version: %s", m);
        return false;
    }

    public static boolean i() {
        return cz.a(com.huawei.openalliance.ad.e.a(), -111111) >= 30476300;
    }

    public static void i(Context context) {
        if (context != null && com.huawei.openalliance.ad.bz.b(context)) {
            ms.a(context).a(RTCMethods.REMOVE_EXSPLASH_BLOCK, null, null, null);
        }
    }

    public static boolean h() {
        return cz.a(com.huawei.openalliance.ad.e.a(), -111111) >= 30470106;
    }

    public static int h(Context context) {
        try {
            int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", OsType.ANDROID);
            if (identifier > 0) {
                return context.getResources().getDimensionPixelSize(identifier);
            }
        } catch (Throwable th) {
            ho.c("HiAdTools", "getStatusBarHeight err: %s", th.getClass().getSimpleName());
        }
        return 0;
    }

    public static boolean g() {
        return !TextUtils.isEmpty(com.huawei.openalliance.ad.e.a());
    }

    public static Integer g(Context context) {
        Integer a2 = bn.a(context, context.getApplicationContext().getPackageName(), "hw_ads_sdk_type");
        if (ho.a()) {
            ho.a("HiAdTools", "sdkType:%s", a2);
        }
        return a2;
    }

    private static boolean f(Context context, String str) {
        String str2;
        if (context == null) {
            str2 = "processWhyEvent context is null, return";
        } else {
            if (!com.huawei.openalliance.ad.bz.a(context).d()) {
                if (Constants.WHY_THIS_AD_DEFAULT_URL.equalsIgnoreCase(str)) {
                    str = Constants.THIRD_ADINFO_DP + context.getPackageName();
                }
                if (cz.b(str)) {
                    String str3 = Constants.THIRD_ADINFO_DP + context.getPackageName();
                    ho.a("HiAdTools", "processWhyEvent cloud download url is empty, use default!");
                    return d(context, str3);
                }
                ho.a("HiAdTools", "processWhyEvent url = %s", str);
                return cz.j(str) ? a(context, str) : d(context, str);
            }
            str2 = "china rom should not call gotoWhyThisAdPage method";
        }
        ho.c("HiAdTools", str2);
        return false;
    }

    public static boolean f() {
        boolean z = ck.b(Constants.NETWORK_CLIENT_CLASS) && ck.b(Constants.NETWORK_REQ_BODY_PROVIDER);
        return !z ? ck.c(Constants.NETWORK_CLIENT_CLASS) && ck.c(Constants.NETWORK_REQ_BODY_PROVIDER) : z;
    }

    public static Integer f(Context context) {
        Integer a2 = bn.a(context, i.e(context.getApplicationContext()), "ppskit_ver_code");
        if (ho.a()) {
            ho.a("HiAdTools", "ppsKitVerCode:%s", a2);
        }
        return a2;
    }

    private static boolean e(Context context, String str) {
        if (!cz.j(str)) {
            ho.c("HiAdTools", "url is invalid");
            return false;
        }
        Boolean o = HiAd.a(context).o();
        if (o != null && o.booleanValue()) {
            ho.a("HiAdTools", "try open in browser");
            if (c(context, str)) {
                ho.a("HiAdTools", "open in browser success");
                return true;
            }
        }
        Intent intent = new Intent();
        intent.setPackage(context.getPackageName());
        intent.setClass(context, TransparencyStatementActivity.class);
        intent.putExtra(MapKeyNames.DSA_URL, str);
        intent.setFlags(268435456);
        dd.a(context, intent);
        return true;
    }

    public static boolean e(Context context) {
        if (context == null) {
            return false;
        }
        Integer f = f(context);
        if (f != null && f.intValue() >= 30466100) {
            return true;
        }
        ho.b("HiAdTools", "hms version is too low to support query ins app.");
        return false;
    }

    public static boolean e() {
        return ck.c(Constants.CONSENT_SDK) && ck.a(Constants.CONSENT_SDK, Constants.CONSENT_SYNC, (Class<?>[]) null);
    }

    private static boolean d(Context context, String str) {
        String str2;
        if (cz.b(str)) {
            str2 = "openLinkByDeepLink deepLinkUrl is null, return";
        } else {
            try {
                Intent intent = new Intent();
                intent.addFlags(268435456);
                intent.setPackage(context.getPackageName());
                intent.setData(Uri.parse(str));
                intent.setClipData(Constants.CLIP_DATA);
                context.startActivity(intent);
                return true;
            } catch (Throwable th) {
                str2 = "openLinkByDeepLink " + th.getClass().getSimpleName();
            }
        }
        ho.c("HiAdTools", str2);
        return false;
    }

    public static boolean d(Context context) {
        if (context == null) {
            return false;
        }
        Integer f = f(context);
        if (f != null && f.intValue() >= 30462300) {
            return true;
        }
        ho.b("HiAdTools", "hms version is too low to support kit preload.");
        return false;
    }

    public static boolean d() {
        try {
            return new ar().a();
        } catch (Throwable th) {
            ho.d("HiAdTools", "check okhttp error:%s", th.getClass().getSimpleName());
            return false;
        }
    }

    private static String d(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (Throwable unused) {
            ho.c("HiAdTools", "decode failed");
            return "";
        }
    }

    public static int d(Context context, float f) {
        if (context == null || f <= 0.0f) {
            return 0;
        }
        return (int) ((f / context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    private static boolean c(Context context, String str) {
        String str2;
        if (context == null || !cz.j(str)) {
            str2 = "param is error, return";
        } else {
            try {
                Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str));
                PackageManager packageManager = context.getPackageManager();
                if (packageManager == null) {
                    return false;
                }
                if (bg.a(packageManager.queryIntentActivities(intent, 65536))) {
                    ho.c("HiAdTools", "No any browser installed");
                    return false;
                }
                intent.setFlags(268468224);
                context.startActivity(intent);
                return true;
            } catch (Throwable th) {
                str2 = "openLinkInBrowser " + th.getClass().getSimpleName();
            }
        }
        ho.c("HiAdTools", str2);
        return false;
    }

    public static boolean c(Context context) {
        if (context == null) {
            return false;
        }
        Integer f = f(context);
        if (f != null && f.intValue() >= 30445100) {
            return true;
        }
        ho.b("HiAdTools", "hms version is too low to support switch next install way.");
        return false;
    }

    public static String c(String str) {
        if (ho.a()) {
            ho.a("HiAdTools", "filterUrl, originUrl: %s", str);
        }
        return TextUtils.isEmpty(str) ? str : str.replace(" ", "");
    }

    public static long c() {
        return System.currentTimeMillis();
    }

    public static int c(Context context, float f) {
        if (context != null && f > 0.0f) {
            return (int) ((f * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
        }
        return 0;
    }

    public static boolean b(Context context, String str) {
        if (cz.b(str)) {
            ho.c("HiAdTools", "openHmsSetting, deepLink is empty.");
        }
        try {
            Intent intent = new Intent();
            intent.addFlags(268435456);
            intent.setPackage(i.e(context));
            intent.setData(Uri.parse(str));
            intent.setClipData(Constants.CLIP_DATA);
            context.startActivity(intent);
            return true;
        } catch (Throwable th) {
            ho.c("HiAdTools", "openHmsSetting error: %s", th.getClass().getSimpleName());
            return false;
        }
    }

    public static boolean b(Context context, Uri uri) {
        if (context == null || uri == null) {
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(uri.getAuthority(), 0);
        if (resolveContentProvider == null) {
            ho.d("HiAdTools", "Invalid param");
            return false;
        }
        ApplicationInfo applicationInfo = resolveContentProvider.applicationInfo;
        if (applicationInfo == null) {
            return false;
        }
        String str = applicationInfo.packageName;
        ho.b("HiAdTools", "Target provider service's package name is : " + str);
        if (str == null) {
            return false;
        }
        boolean z = packageManager.checkSignatures(context.getPackageName(), str) == 0 || (applicationInfo.flags & 1) == 1;
        if (z) {
            return z;
        }
        String e = i.e(context, str);
        boolean isEmpty = TextUtils.isEmpty(e);
        ho.b("HiAdTools", "is sign empty: %s", Boolean.valueOf(isEmpty));
        return !isEmpty ? WhiteListPkgList.isTrustApp(context, str, e) : z;
    }

    public static boolean b(Context context) {
        if (context == null) {
            return false;
        }
        if (com.huawei.openalliance.ad.bz.a(context).d() || !com.huawei.openalliance.ad.bz.b(context)) {
            return true;
        }
        String a2 = d.a(context, i.e(context));
        Integer h = cz.h(a2);
        if (h != null && h.intValue() >= 40000300) {
            return true;
        }
        ho.b("HiAdTools", "hms is not installed or hms version is too low, version is: " + a2);
        return false;
    }

    public static String b(String str) {
        return a(str).format(new Date());
    }

    public static long b() {
        long maxMemory = Runtime.getRuntime().maxMemory() - (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
        if (ho.a()) {
            ho.a("HiAdTools", "unUsedMemory is: %s", String.valueOf(maxMemory));
        }
        return maxMemory;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int b(boolean r2, java.lang.String r3) {
        /*
            boolean r0 = com.huawei.openalliance.ad.utils.cz.b(r3)
            if (r0 != 0) goto L1f
            java.lang.String r0 = "_"
            java.lang.String[] r3 = r3.split(r0)
            int r0 = r3.length
            r1 = 2
            if (r0 != r1) goto L1f
            r0 = 1
            r3 = r3[r0]     // Catch: java.lang.NumberFormatException -> L18
            int r3 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.NumberFormatException -> L18
            goto L20
        L18:
            java.lang.String r3 = "HiAdTools"
            java.lang.String r0 = "get emui version error!"
            com.huawei.openalliance.ad.ho.c(r3, r0)
        L1f:
            r3 = 0
        L20:
            if (r2 == 0) goto L28
            r0 = 4
            if (r3 <= r0) goto L28
            r2 = 11
            return r2
        L28:
            if (r2 == 0) goto L45
            java.util.Map<java.lang.Integer, java.lang.Integer> r2 = com.huawei.openalliance.ad.utils.ao.f7587a
            java.lang.Integer r0 = java.lang.Integer.valueOf(r3)
            boolean r0 = r2.containsKey(r0)
            if (r0 == 0) goto L45
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.Object r2 = r2.get(r3)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            return r2
        L45:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.utils.ao.b(boolean, java.lang.String):int");
    }

    public static int b(Context context, float f) {
        if (context == null || f <= 0.0f) {
            return 0;
        }
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static boolean a(int[] iArr, int i) {
        return iArr != null && iArr.length == i;
    }

    public static boolean a(boolean z, boolean z2, String str) {
        return z && z2 && !cz.b(str);
    }

    public static boolean a(boolean z, String str) {
        ho.b("HiAdTools", "dsa switch on:%s, url:%s", Boolean.valueOf(z), dl.a(str));
        return z && !cz.b(str);
    }

    public static boolean a(VideoInfo videoInfo) {
        return videoInfo != null && 3 == videoInfo.getVideoPlayMode();
    }

    public static boolean a(AdLandingPageData adLandingPageData) {
        return (adLandingPageData == null || !adLandingPageData.isShowPageTitle() || os.f(adLandingPageData.f()) != 3 || adLandingPageData.getAppInfo() == null || TextUtils.isEmpty(adLandingPageData.getAppInfo().getAppName())) ? false : true;
    }

    public static boolean a(Context context, String str) {
        String str2;
        if (cz.j(str)) {
            try {
                Intent intent = new Intent();
                intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
                intent.setData(Uri.parse(str));
                intent.setFlags(268468224);
                dd.a(context, intent);
                return true;
            } catch (Throwable th) {
                str2 = "openLinkInBrowser " + th.getClass().getSimpleName();
            }
        } else {
            str2 = "url is error, return";
        }
        ho.c("HiAdTools", str2);
        return false;
    }

    public static boolean a(Context context, IAd iAd) {
        if (context == null || iAd == null) {
            return false;
        }
        boolean isTransparencyOpen = iAd.isTransparencyOpen();
        String transparencyTplUrl = iAd.getTransparencyTplUrl();
        return a(isTransparencyOpen, transparencyTplUrl) ? e(context, transparencyTplUrl) : f(context, a(iAd));
    }

    public static boolean a(Context context, ContentRecord contentRecord) {
        if (context == null || contentRecord == null) {
            return false;
        }
        boolean bc = contentRecord.bc();
        String bb = contentRecord.bb();
        return a(bc, bb) ? e(context, bb) : f(context, a(contentRecord));
    }

    public static Date a(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.get(5) - i);
        calendar.set(11, 24);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static SimpleDateFormat a(String str) {
        try {
            return new SimpleDateFormat(str, Locale.ENGLISH);
        } catch (Throwable unused) {
            return new SimpleDateFormat(str);
        }
    }

    private static String a(String str, String str2) {
        Matcher matcher;
        try {
            matcher = Pattern.compile(str).matcher(str2);
        } catch (RuntimeException e) {
            ho.d("HiAdTools", "getVersionByPattern RuntimeException: %s", e.getClass().getSimpleName());
        } catch (Exception e2) {
            ho.d("HiAdTools", "getVersionByPattern Exception: %s", e2.getClass().getSimpleName());
        }
        if (matcher.find()) {
            return matcher.group(0);
        }
        ho.c("HiAdTools", "can not find versionName: %s by pattern: %s", str2, str);
        return "";
    }

    private static String a(IAd iAd) {
        String adChoiceUrl = iAd.getAdChoiceUrl();
        if (TextUtils.isEmpty(adChoiceUrl)) {
            adChoiceUrl = iAd.getWhyThisAd();
        }
        return d(adChoiceUrl);
    }

    private static String a(ContentRecord contentRecord) {
        String ak = contentRecord.ak();
        if (TextUtils.isEmpty(ak)) {
            ak = contentRecord.Z();
        }
        return d(ak);
    }

    public static String a(Context context, VideoInfo videoInfo, iq iqVar, String str) {
        ib ibVar;
        if (context == null || videoInfo == null || !f()) {
            return null;
        }
        ho.a("HiAdTools", "getProxyUrl for: %s", dl.a(videoInfo.g()));
        Context applicationContext = context.getApplicationContext();
        try {
            ik.a(applicationContext);
            ie.a().b();
            il ilVar = new il(new hz(applicationContext, str), new com.huawei.openalliance.ad.di(applicationContext, str));
            ilVar.a();
            ic icVar = new ic(applicationContext, ilVar, ik.a(), iqVar);
            icVar.a(applicationContext);
            ibVar = new ib(applicationContext, ilVar, icVar);
        } catch (Throwable th) {
            ho.c("HiAdTools", "CreativeHttpServer boot failed, error: %s", th.getClass().getSimpleName());
            ibVar = null;
        }
        if (ibVar == null) {
            return null;
        }
        return ibVar.a(videoInfo);
    }

    public static String a(Context context, VideoInfo videoInfo, iq iqVar) {
        return a(context, videoInfo, iqVar, Constants.NATIVE_CACHE);
    }

    public static String a(Context context, VideoInfo videoInfo) {
        if (context == null || videoInfo == null || TextUtils.isEmpty(videoInfo.getVideoDownloadUrl())) {
            return null;
        }
        String videoDownloadUrl = videoInfo.getVideoDownloadUrl();
        if (videoDownloadUrl.startsWith(Scheme.CONTENT.toString())) {
            ho.b("HiAdTools", "start with content");
            return videoDownloadUrl;
        }
        com.huawei.openalliance.ad.dk a2 = com.huawei.openalliance.ad.dh.a(context, "normal");
        return a2.c(a2.e(videoInfo.getVideoDownloadUrl()));
    }

    public static String a() {
        return UUID.randomUUID().toString();
    }

    public static Cursor a(Context context, Uri uri) {
        if (context == null || uri == null || !b(context, uri)) {
            return null;
        }
        return context.getContentResolver().query(uri, null, null, null, null);
    }

    public static Intent a(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler) {
        if (context == null) {
            return null;
        }
        return Build.VERSION.SDK_INT >= 33 ? context.registerReceiver(broadcastReceiver, intentFilter, str, handler, 2) : context.registerReceiver(broadcastReceiver, intentFilter, str, handler);
    }

    public static Intent a(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (context == null) {
            return null;
        }
        return Build.VERSION.SDK_INT >= 33 ? context.registerReceiver(broadcastReceiver, intentFilter, 2) : context.registerReceiver(broadcastReceiver, intentFilter);
    }

    public static int a(Context context, float f) {
        if (context != null && f > 0.0f) {
            return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
        }
        return 0;
    }

    public static int a(Context context) {
        StringBuilder sb;
        String a2;
        int i = 0;
        try {
            a2 = dd.a("ro.build.version.emui");
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("getEmuiVersion RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.d("HiAdTools", sb.toString());
            return i;
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("getEmuiVersion Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.d("HiAdTools", sb.toString());
            return i;
        }
        if (TextUtils.isEmpty(a2)) {
            return 0;
        }
        String a3 = a("^EmotionUI_[0-9]+", a2);
        i = !TextUtils.isEmpty(a3) ? b(false, a3) : b(true, a("^MagicUI_[0-9]+", a2));
        return i;
    }

    static {
        HashMap hashMap = new HashMap();
        f7587a = hashMap;
        hashMap.put(2, 9);
        hashMap.put(3, 10);
        hashMap.put(4, 11);
    }
}
