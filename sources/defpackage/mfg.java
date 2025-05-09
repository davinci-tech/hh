package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import com.huawei.ui.commonui.R$anim;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mfg {
    public static boolean a(int i) {
        return 259 == i;
    }

    public static boolean b(int i) {
        return 258 == i || 264 == i || 280 == i;
    }

    public static boolean d(int i) {
        return 283 == i;
    }

    public static boolean f(int i) {
        return 262 == i || 266 == i;
    }

    public static boolean g(int i) {
        return 257 == i;
    }

    public static int b(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            LogUtil.b("AchieveUtils", "string2Int e=", e.getMessage());
            return 0;
        }
    }

    public static String e() {
        Locale locale = BaseApplication.getContext().getResources().getConfiguration().locale;
        String str = ProfileRequestConstants.X_LANGUAGE_VALUE;
        if (locale == null) {
            LogUtil.h("AchieveUtils", "getLanguage, locale == null");
            return ProfileRequestConstants.X_LANGUAGE_VALUE;
        }
        String e = mtj.e(locale);
        if (e == null) {
            LogUtil.h("AchieveUtils", "getSupportLanguageName get null");
            return ProfileRequestConstants.X_LANGUAGE_VALUE;
        }
        if ("en".equals(e)) {
            str = "en_US";
        } else if (!"zh-CN".equals(e)) {
            str = e;
        }
        String a2 = a(str);
        LogUtil.a("AchieveUtils", "getConvertLanguage is ", a2);
        return a2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static String a(String str) {
        char c;
        if (str == null) {
            return str;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1945443668:
                if (str.equals("b+my+Qaag")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 3248:
                if (str.equals("eu")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3301:
                if (str.equals("gl")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 3414:
                if (str.equals("ka")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 3424:
                if (str.equals("kk")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 3426:
                if (str.equals("km")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 3459:
                if (str.equals("lo")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case IEventListener.EVENT_ID_NOTICE_DIALOG_SHOW /* 3500 */:
                if (str.equals("my")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 3588:
                if (str.equals("pt")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 3670:
                if (str.equals("si")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 3749:
                if (str.equals("uz")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
        }
        return str;
    }

    public static void b(Context context) {
        if (context == null) {
            LogUtil.h("AchieveUtils", "saveCurrentLanguage context is null");
        } else {
            mct.b(context, "_current_language", context.getResources().getConfiguration().locale.getLanguage());
        }
    }

    public static boolean e(Context context) {
        if (context != null) {
            return !mct.b(context, "_current_language").equals(context.getResources().getConfiguration().locale.getLanguage());
        }
        LogUtil.h("AchieveUtils", "isLocalLanguageChanged context is null");
        return false;
    }

    public static boolean a(Context context, String str) {
        boolean contains = mlb.a().contains(str);
        LogUtil.a("AchieveUtils", "medalResourceIsComplete isOverseaLocalMedal:", Boolean.valueOf(contains));
        if (contains) {
            return true;
        }
        return c(mct.b(context, "_medalPngStatusDownload"), str, context);
    }

    public static int b() {
        return ThirdLoginDataStorageUtil.getTokenTypeValue();
    }

    public static long c(long j, boolean z) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j + a(TimeZone.getDefault()));
        if (z) {
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
        } else {
            calendar.set(11, 23);
            calendar.set(12, 59);
            calendar.set(13, 59);
            calendar.set(14, 999);
        }
        return calendar.getTimeInMillis();
    }

    public static int a(TimeZone timeZone) {
        return TimeZone.getTimeZone("Asia/Shanghai").getRawOffset() - timeZone.getRawOffset();
    }

    public static boolean e(String str) {
        return !TextUtils.isEmpty(str);
    }

    public static String a(Context context) {
        try {
            PackageManager packageManager = BaseApplication.getContext().getPackageManager();
            return String.valueOf(packageManager.getApplicationLabel(packageManager.getApplicationInfo(BaseApplication.getContext().getPackageName(), 0)));
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.b("AchieveUtils", "getApplicationName e=", e.getMessage());
            return "";
        }
    }

    public static boolean b(String str, String str2, Context context, String str3) {
        int i = 0;
        if (context == null || TextUtils.isEmpty(str2)) {
            return false;
        }
        try {
            i = Integer.parseInt(str2);
        } catch (NumberFormatException unused) {
            LogUtil.b("AchieveUtils", "NumberFormatException");
        }
        if (str == null || i <= 19) {
            return true;
        }
        return c(str3, str2, context);
    }

    public static void a(String str, Context context) {
        if (context == null) {
            LogUtil.c("AchieveUtils", "refreshSharePrefrence context is null");
            return;
        }
        String e = e(mct.b(context, "_medalPngStatusDownload"), str);
        if (TextUtils.isEmpty(e)) {
            LogUtil.c("AchieveUtils", "refreshSharePrefrence failed");
        } else {
            mct.b(context, "_medalPngStatusDownload", e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String e(java.lang.String r4, java.lang.String r5) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 != 0) goto L6d
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 == 0) goto Ld
            goto L6d
        Ld:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: org.json.JSONException -> L58
            r0.<init>(r4)     // Catch: org.json.JSONException -> L58
            java.util.HashMap r4 = new java.util.HashMap     // Catch: org.json.JSONException -> L5a
            r1 = 5
            r4.<init>(r1)     // Catch: org.json.JSONException -> L5a
            java.lang.String r1 = "grayListStyle"
            r2 = 0
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)     // Catch: org.json.JSONException -> L5a
            r4.put(r1, r3)     // Catch: org.json.JSONException -> L5a
            java.lang.String r1 = "lightListStyle"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)     // Catch: org.json.JSONException -> L5a
            r4.put(r1, r3)     // Catch: org.json.JSONException -> L5a
            java.lang.String r1 = "grayDetailStyle"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)     // Catch: org.json.JSONException -> L5a
            r4.put(r1, r3)     // Catch: org.json.JSONException -> L5a
            java.lang.String r1 = "lightDetailStyle"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)     // Catch: org.json.JSONException -> L5a
            r4.put(r1, r3)     // Catch: org.json.JSONException -> L5a
            java.lang.String r1 = "shareImageUrl"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: org.json.JSONException -> L5a
            r4.put(r1, r2)     // Catch: org.json.JSONException -> L5a
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: org.json.JSONException -> L5a
            r1.<init>(r4)     // Catch: org.json.JSONException -> L5a
            boolean r4 = r0.has(r5)     // Catch: org.json.JSONException -> L5a
            if (r4 == 0) goto L54
            r0.remove(r5)     // Catch: org.json.JSONException -> L5a
        L54:
            r0.put(r5, r1)     // Catch: org.json.JSONException -> L5a
            goto L65
        L58:
            r4 = 0
            r0 = r4
        L5a:
            java.lang.String r4 = "parseJsonData JSONException!"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            java.lang.String r5 = "AchieveUtils"
            com.huawei.hwlogsmodel.LogUtil.a(r5, r4)
        L65:
            if (r0 != 0) goto L68
            goto L6d
        L68:
            java.lang.String r4 = r0.toString()
            goto L6f
        L6d:
            java.lang.String r4 = ""
        L6f:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.mfg.e(java.lang.String, java.lang.String):java.lang.String");
    }

    public static boolean c(String str, String str2, Context context) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(str2)) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(str2);
                    int d = d(ParsedFieldTag.GRAY_LIST_STYLE, jSONObject2);
                    int d2 = d(ParsedFieldTag.LIGHT_LIST_STYLE, jSONObject2);
                    int d3 = d(ParsedFieldTag.LIGHT_DETAIL_STYLE, jSONObject2);
                    int d4 = d(ParsedFieldTag.GRAY_DETAIL_STYLE, jSONObject2);
                    if (d2 != 0 && d != 0 && d3 != 0 && d4 != 0) {
                        if (mlb.d(mlb.d(str2, ParsedFieldTag.LIGHT_LIST_STYLE)) && mlb.d(mlb.d(str2, ParsedFieldTag.LIGHT_DETAIL_STYLE))) {
                            LogUtil.a("AchieveUtils", "parseJsonData Id=", str2);
                            return true;
                        }
                        a(str2, context);
                        return false;
                    }
                }
            } catch (JSONException unused) {
                LogUtil.a("AchieveUtils", "parseJsonData JSONException!");
            }
        }
        return false;
    }

    private static int d(String str, JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.has(str)) {
            try {
                return jSONObject.getInt(str);
            } catch (JSONException unused) {
                LogUtil.b("AchieveUtils", "judgeValidKey Exception");
            }
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String d(java.lang.String r1, java.lang.String r2) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 != 0) goto L35
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 == 0) goto Ld
            goto L35
        Ld:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: org.json.JSONException -> L20
            r0.<init>(r1)     // Catch: org.json.JSONException -> L20
            boolean r1 = r0.has(r2)     // Catch: org.json.JSONException -> L22
            if (r1 == 0) goto L1b
            r0.remove(r2)     // Catch: org.json.JSONException -> L22
        L1b:
            r1 = 0
            r0.put(r2, r1)     // Catch: org.json.JSONException -> L22
            goto L2d
        L20:
            r1 = 0
            r0 = r1
        L22:
            java.lang.String r1 = "parseTextureJsonData JSONException!"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            java.lang.String r2 = "AchieveUtils"
            com.huawei.hwlogsmodel.LogUtil.a(r2, r1)
        L2d:
            if (r0 != 0) goto L30
            goto L35
        L30:
            java.lang.String r1 = r0.toString()
            goto L37
        L35:
            java.lang.String r1 = ""
        L37:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.mfg.d(java.lang.String, java.lang.String):java.lang.String");
    }

    public static void c(Map<Integer, String> map, Context context) {
        if (map == null || context == null) {
            return;
        }
        map.put(1, context.getResources().getString(R.string._2130840690_res_0x7f020c72));
        map.put(2, context.getResources().getString(R.string._2130840691_res_0x7f020c73));
        map.put(3, context.getResources().getString(R.string._2130840692_res_0x7f020c74));
        map.put(4, context.getResources().getString(R.string._2130840693_res_0x7f020c75));
        map.put(5, context.getResources().getString(R.string._2130840694_res_0x7f020c76));
    }

    public static boolean e(int i) {
        return !Utils.o() && (b(i) || a(i) || f(i) || d(i));
    }

    public static void c(Context context) {
        LogUtil.a("AchieveUtils", "overridePendingNoAnim currentTimeMillis ", Long.valueOf(System.currentTimeMillis()));
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(0, R$anim.activity_no_animation_short);
        }
    }

    public static boolean c(int i) {
        boolean z = true;
        if (!CommonUtil.aq() && !CommonUtil.as()) {
            return true;
        }
        if (i == 0) {
            z = feg.a();
        } else if (i == 1) {
            z = feg.e();
        }
        LogUtil.a("AchieveUtils", "isShareShowAccount type=", Integer.valueOf(i), " isShareShowAccount=", Boolean.valueOf(z));
        return z;
    }
}
