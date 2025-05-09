package com.huawei.haf.router;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.router.core.AppRouterHelper;
import com.huawei.haf.router.facade.service.ExtrasFormatService;
import health.compact.a.LogUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouterUtils {
    private AppRouterUtils() {
    }

    public static Map<String, String> zv_(Uri uri) {
        if (uri == null) {
            return Collections.emptyMap();
        }
        String encodedQuery = uri.getEncodedQuery();
        if (encodedQuery == null) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap();
        int i = 0;
        do {
            int indexOf = encodedQuery.indexOf(38, i);
            if (indexOf == -1) {
                indexOf = encodedQuery.length();
            }
            int indexOf2 = encodedQuery.indexOf(61, i);
            if (indexOf2 > indexOf || indexOf2 == -1) {
                indexOf2 = indexOf;
            }
            String substring = encodedQuery.substring(i, indexOf2);
            if (!TextUtils.isEmpty(substring)) {
                hashMap.put(Uri.decode(substring), Uri.decode(indexOf2 == indexOf ? "" : encodedQuery.substring(indexOf2 + 1, indexOf)));
            }
            i = indexOf + 1;
        } while (i < encodedQuery.length());
        return hashMap;
    }

    public static Uri zs_(Intent intent) {
        if (intent == null) {
            return null;
        }
        Uri uri = (Uri) intent.getParcelableExtra("NTwe23AtQb8U");
        return uri != null ? uri : intent.getData();
    }

    public static boolean zt_(Intent intent) {
        return (intent == null || intent.getBundleExtra("FT3uI8SpK7Be") == null) ? false : true;
    }

    public static void zu_(Context context, Intent intent) {
        intent.setExtrasClassLoader(BaseApplication.e().getClassLoader());
        Bundle bundleExtra = intent.getBundleExtra("FT3uI8SpK7Be");
        if (bundleExtra == null) {
            LogUtil.a("HAF_AppRouterUtils", "bundle == null, intent=", intent);
        } else {
            intent.removeExtra("FT3uI8SpK7Be");
            AppRouterResultReceiver.zl_(context, bundleExtra);
        }
    }

    public static void zw_(Intent intent, Guidepost guidepost, NaviCallback naviCallback) {
        intent.putExtra("FT3uI8SpK7Be", AppRouterResultReceiver.zr_(guidepost, naviCallback));
    }

    public static boolean a(Guidepost guidepost) {
        return (guidepost.f() & 2097152) == 2097152;
    }

    public static boolean e(Guidepost guidepost) {
        return (guidepost.f() & 33554432) == 33554432;
    }

    public static boolean b(Guidepost guidepost) {
        return (guidepost.f() & 16777216) == 16777216;
    }

    public static boolean d(Guidepost guidepost) {
        return (guidepost.f() & AppRouterExtras.COLDSTART) == 67108864;
    }

    public static String b(String str) {
        int indexOf;
        return (TextUtils.isEmpty(str) || str.charAt(0) != '/' || (indexOf = str.indexOf(47, 1)) == -1) ? "" : str.substring(1, indexOf);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int a(String str) {
        char c;
        int i;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int i2 = 0;
        for (String str2 : str.toUpperCase(Locale.ENGLISH).split("\\|")) {
            if (!TextUtils.isEmpty(str2)) {
                str2.hashCode();
                switch (str2.hashCode()) {
                    case -1117852922:
                        if (str2.equals("DOWNLOAD_AUTO")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1117298545:
                        if (str2.equals("DOWNLOAD_TIPS")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case -298906263:
                        if (str2.equals("DOWNLOAD_IGNORE")) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case 64093495:
                        if (str2.equals("CHINA")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case 72611657:
                        if (str2.equals("LOGIN")) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case 104549103:
                        if (str2.equals("MULTIPLE_INSTANCES")) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    case 159503326:
                        if (str2.equals("COLDSTART")) {
                            c = 6;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1920132015:
                        if (str2.equals("RUNNING_PROCESS")) {
                            c = 7;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                switch (c) {
                    case 0:
                        i = 4194304;
                        break;
                    case 1:
                        i = 2097152;
                        break;
                    case 2:
                        i = 1048576;
                        break;
                    case 3:
                        i = 33554432;
                        break;
                    case 4:
                        i = 16777216;
                        break;
                    case 5:
                        i = 65536;
                        break;
                    case 6:
                        i = AppRouterExtras.COLDSTART;
                        break;
                    case 7:
                        i = 131072;
                        break;
                    default:
                        i = d(str2);
                        break;
                }
                i2 |= i;
            }
        }
        return i2;
    }

    private static int d(String str) {
        int fromExtrasString;
        if (str.startsWith("0X")) {
            try {
                fromExtrasString = Integer.parseInt(str.substring(2), 16);
            } catch (NumberFormatException e) {
                LogUtil.a("HAF_AppRouterUtils", "parseCustomExtras ex=", LogUtil.a(e));
            }
        } else {
            ExtrasFormatService extrasFormatService = (ExtrasFormatService) AppRouterHelper.e(ExtrasFormatService.class);
            if (extrasFormatService != null) {
                fromExtrasString = extrasFormatService.fromExtrasString(str);
            }
            fromExtrasString = 0;
        }
        int i = fromExtrasString & 65535;
        if (i == 0) {
            LogUtil.a("HAF_AppRouterUtils", "parseCustomExtras error, strExtras=", str);
        }
        return i;
    }

    public static String a(int i) {
        if (i <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(32);
        if ((i & 65536) == 65536) {
            d(sb, "MULTIPLE_INSTANCES");
        }
        if ((i & 131072) == 131072) {
            d(sb, "RUNNING_PROCESS");
        }
        if ((i & 1048576) == 1048576) {
            d(sb, "DOWNLOAD_IGNORE");
        }
        if ((i & 2097152) == 2097152) {
            d(sb, "DOWNLOAD_TIPS");
        }
        if ((i & 4194304) == 4194304) {
            d(sb, "DOWNLOAD_AUTO");
        }
        if ((i & 16777216) == 16777216) {
            d(sb, "LOGIN");
        }
        if ((i & 33554432) == 33554432) {
            d(sb, "CHINA");
        }
        if ((i & AppRouterExtras.COLDSTART) == 67108864) {
            d(sb, "COLDSTART");
        }
        d(sb, i & 65535);
        return sb.toString();
    }

    private static void d(StringBuilder sb, int i) {
        if (i > 0) {
            ExtrasFormatService extrasFormatService = (ExtrasFormatService) AppRouterHelper.e(ExtrasFormatService.class);
            String formatCustomString = extrasFormatService != null ? extrasFormatService.formatCustomString(i) : null;
            if (!TextUtils.isEmpty(formatCustomString)) {
                d(sb, formatCustomString);
                return;
            }
            d(sb, "0X" + Integer.toHexString(i).toUpperCase(Locale.ENGLISH));
        }
    }

    private static void d(StringBuilder sb, String str) {
        if (sb.length() > 0) {
            sb.append('|');
        }
        sb.append(str);
    }
}
