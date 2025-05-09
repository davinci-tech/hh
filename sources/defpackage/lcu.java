package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.text.TextUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.MagicBuild;
import health.compact.a.Utils;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class lcu {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f14762a = {"ADT-AN00", "AGM3-AL09HN", "AGM3-W09HN", "AGM-W09HN", "ALA-AN70", "ANY-AN00", "ANY-TN00", "BRT-AN09", "BRT-W09", "CHL-AL00", "CHL-AN00", "CHL-TN00", "CMA-AN00", "CMA-AN40", "CMA-TN00", "DIO-AN00", "DIO-AN10", "DIO-TN00", "ELZ-AN00", "ELZ-AN10", "ELZ-AN20", "FNE-AN00", "FNE-TN00", "GIA-AN00", "GIA-TN00", "HEY-W09", "HJC-AN90", "HJC-L29", "HJC-LX9", "HPB-AN00", "JLH-AN00", "KJR-W09", "KKG-AN70", "KOB2-AL00HN", "KOB2-W09HN", "KOZ-AL00", "KOZ-AL00CM", "KOZ-AL40", "KRJ2-AN00", "KRJ2-W09", "LGE-AN00", "LGE-AN10", "LGE-AN20", "LSA-AN00", "MGI-AN00", "NEW-AN90", "NTH-AN00", "NTN-AN20", "NTX-AN00", "NTX-AN10", "NTX-AN90", "NTX-TN00", "NZA-AL00", "RKY-AN00", "RKY-TN00", "RMO-AN00", "RMO-TN00", "RNA-AN00", "RNA-TN00", "SDY-AN00", "TFY-AN00", "TFY-AN40", "TFY-TN00", "TNA-AN00", "TNA-AN00Q", "TNA-TN00", "VNE-AN00", "VNE-AN40", "VNE-AN60", "VNE-TN00", "YOK-AN10", "YOR-AN00", "YOR-AN10"};

    public static boolean e() {
        if (CommonUtil.bh() || c()) {
            return false;
        }
        return !CommonUtil.bf() || Utils.o() || Build.VERSION.SDK_INT >= 34;
    }

    public static boolean b() {
        if (CommonUtil.bh() || (c() && MagicBuild.d <= 36)) {
            return false;
        }
        return !CommonUtil.bf() || MagicBuild.d > 36 || Utils.o();
    }

    public static boolean c() {
        if (CommonUtil.bf()) {
            return Arrays.asList(f14762a).contains(Build.MODEL);
        }
        return false;
    }

    public static boolean d(Context context) {
        if (context == null) {
            LogUtil.h("KeepAppAliveUtils", "isAgreeWhite, context is null");
            return true;
        }
        Object systemService = context.getSystemService("power");
        if (systemService instanceof PowerManager) {
            return ((PowerManager) systemService).isIgnoringBatteryOptimizations(context.getPackageName());
        }
        return false;
    }

    public static boolean b(Context context) {
        if (context == null) {
            return false;
        }
        Intent intent = new Intent("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        return intent.resolveActivity(context.getPackageManager()) != null;
    }

    public static void d(Context context, int i) {
        if (context == null) {
            LogUtil.h("KeepAppAliveUtils", "requestWhiteSet, context is null");
            return;
        }
        try {
            Intent intent = new Intent("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            if (context instanceof Activity) {
                LogUtil.a("KeepAppAliveUtils", "context is activity");
                ((Activity) context).startActivityForResult(intent, i);
            } else {
                LogUtil.a("KeepAppAliveUtils", "context is not activity");
                context.startActivity(intent);
            }
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("KeepAppAliveUtils", "requestWhiteSet, exception");
        }
    }

    public static void c(Context context) {
        String lowerCase = Build.BRAND.toLowerCase();
        LogUtil.a("KeepAppAliveUtils", "goSettingSelfStart,brand: ", lowerCase);
        if (!lcy.b.containsKey(lowerCase)) {
            f(context);
            return;
        }
        if ("oppo".equals(lowerCase) && Build.VERSION.SDK_INT >= 31) {
            f(context);
            return;
        }
        List<String> list = lcy.b.get(lowerCase);
        if (list == null || list.size() == 0) {
            f(context);
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            String[] split = list.get(i).split(",");
            if (split.length >= 1) {
                String str = split[0];
                String str2 = split.length > 1 ? split[1] : "";
                if (c(context, str)) {
                    a(context, str, str2);
                    return;
                } else if (i == list.size() - 1) {
                    f(context);
                }
            }
        }
    }

    public static void e(Context context) {
        String lowerCase = Build.BRAND.toLowerCase();
        LogUtil.a("KeepAppAliveUtils", "goSettingBackRun,brand", lowerCase);
        if (!lcy.d.containsKey(lowerCase)) {
            f(context);
            return;
        }
        if ("samsung".equals(lowerCase)) {
            i(context);
            return;
        }
        List<String> list = lcy.d.get(lowerCase);
        if (list == null || list.size() == 0) {
            f(context);
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            String[] split = list.get(i).split(",");
            if (split.length >= 1) {
                String str = split[0];
                String str2 = split.length > 1 ? split[1] : "";
                if (c(context, str)) {
                    a(context, str, str2);
                    return;
                } else if (i == list.size() - 1) {
                    f(context);
                }
            }
        }
    }

    public static void a(Context context) {
        if (context == null) {
            LogUtil.a("KeepAppAliveUtils", "gotoAssociationStart,context is null");
            return;
        }
        String lowerCase = Build.BRAND.toLowerCase();
        String lowerCase2 = Build.MANUFACTURER.toLowerCase();
        LogUtil.a("KeepAppAliveUtils", "goSettingBackRun,brand", lowerCase, " ", lowerCase2);
        if (!lcy.e.containsKey(lowerCase) && !lcy.e.containsKey(lowerCase2)) {
            f(context);
            return;
        }
        List<String> list = lcy.e.get(lowerCase);
        if (CollectionUtils.d(list)) {
            list = lcy.e.get(lowerCase2);
        }
        if (CollectionUtils.d(list)) {
            f(context);
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            String[] split = list.get(i).split(",");
            if (split.length >= 1) {
                String str = split[0];
                String str2 = split.length > 1 ? split[1] : "";
                if (c(str, str2, context)) {
                    a(context, str, str2);
                    return;
                } else if (i == list.size() - 1) {
                    f(context);
                }
            }
        }
    }

    private static void a(Context context, String str, String str2) {
        try {
            d(context, str, str2);
        } catch (ActivityNotFoundException | SecurityException unused) {
            LogUtil.b("KeepAppAliveUtils", "goSetKeepAlive activity is not open");
            try {
                if (c(context, str)) {
                    LogUtil.b("KeepAppAliveUtils", "goSetKeepAlive, app is exits");
                    e(context, str);
                } else {
                    LogUtil.b("KeepAppAliveUtils", "goSetKeepAlive, app is not exits");
                    f(context);
                }
            } catch (ActivityNotFoundException unused2) {
                f(context);
            }
        }
    }

    private static void e(final Context context, final String str) {
        final Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage != null) {
            if (context instanceof Activity) {
                ((Activity) context).runOnUiThread(new Runnable() { // from class: lcu.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Intent intent = launchIntentForPackage;
                        String str2 = str;
                        Context context2 = context;
                        nsn.cLM_(intent, str2, context2, context2.getString(R.string._2130844022_res_0x7f021976));
                    }
                });
                return;
            }
            return;
        }
        f(context);
    }

    private static void d(final Context context, final String str, String str2) {
        final Intent intent = new Intent();
        intent.setComponent(new ComponentName(str, str2));
        intent.setFlags(268435456);
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            ActivityInfo resolveActivityInfo = intent.resolveActivityInfo(packageManager, 65536);
            if (resolveActivityInfo != null && resolveActivityInfo.exported) {
                if (context instanceof Activity) {
                    ((Activity) context).runOnUiThread(new Runnable() { // from class: lcu.3
                        @Override // java.lang.Runnable
                        public void run() {
                            Intent intent2 = intent;
                            String str3 = str;
                            Context context2 = context;
                            nsn.cLM_(intent2, str3, context2, context2.getString(R.string._2130844022_res_0x7f021976));
                        }
                    });
                    return;
                }
                return;
            } else if (c(context, str)) {
                e(context, str);
                return;
            } else {
                f(context);
                return;
            }
        }
        f(context);
    }

    private static boolean c(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(str, 8192);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    private static boolean c(String str, String str2, Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(str, str2));
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
        if (resolveActivity != null && resolveActivity.activityInfo != null) {
            return true;
        }
        LogUtil.h("KeepAppAliveUtils", "activity info is null");
        return false;
    }

    private static void i(Context context) {
        try {
            Intent intent = new Intent();
            intent.setFlags(268435456);
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", context.getPackageName(), null));
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("KeepAppAliveUtils", "startPhoneSetting exception");
        }
    }

    private static void f(Context context) {
        try {
            context.startActivity(new Intent("android.settings.SETTINGS"));
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("KeepAppAliveUtils", "startPhoneSetting exception");
        }
    }
}
